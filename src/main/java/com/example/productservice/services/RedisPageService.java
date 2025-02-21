package com.example.productservice.services;

import com.example.productservice.dtos.PageDto;
import com.example.productservice.dtos.product.ProductResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisPageService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;  // Jackson for JSON conversion

    private final String HASH_KEY = "pageCache";
    private final String CONTENT_KEY = "_content";
    private final String PAGEABLE_KEY = "_pageable";

    public <T> void saveObject(String key, T object) {
        try {
            redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(object));
            redisTemplate.expire(key,360*1000, TimeUnit.MILLISECONDS);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing object", e);
        }
    }

    public <T> T getObject(String key, Class<T> clazz) {
        try {
            return objectMapper.readValue((String)redisTemplate.opsForValue().get(key), clazz);
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing object", e);
        }
    }

    public boolean hasObject(String key) {
        return redisTemplate.opsForValue().getOperations().hasKey(key);
    }

    public <T> void savePage(String key, Page<T> page) {
        try {
            List<String> StringifyContent = page.getContent().stream().map((T product) -> {
                try {
                    return objectMapper.writeValueAsString(product);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Error serializing product object", e);
                }
            }).toList();
            PageDto pageDto = new PageDto();
            pageDto.setPageNumber(page.getPageable().getPageNumber());
            pageDto.setPageSize(page.getPageable().getPageSize());
            pageDto.setTotal(page.getTotalElements());
            System.out.println("Stringify Content: "+StringifyContent);
            redisTemplate.opsForList().rightPushAll(key+CONTENT_KEY,StringifyContent);
            redisTemplate.opsForHash().put(HASH_KEY, key+PAGEABLE_KEY, objectMapper.writeValueAsString(pageDto));
            redisTemplate.expire(HASH_KEY,120*1000, TimeUnit.MILLISECONDS);
            redisTemplate.expire(key+CONTENT_KEY,120*1000, TimeUnit.MILLISECONDS);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing Page object", e);
        }
    }

    public <T> Page<T> getPage(String key, Class<T> clazz) {
        try {

            // Extract fields
            List<T> content = redisTemplate.opsForList().range(key+CONTENT_KEY, 0, -1).stream().map((String product) -> {
                try {
                    return objectMapper.readValue(product, clazz);
                } catch (IOException e) {
                    throw new RuntimeException("Error deserializing product object", e);
                }
            }).toList();
            PageDto pageable = objectMapper.readValue((String)redisTemplate.opsForHash().get(HASH_KEY, key+PAGEABLE_KEY), PageDto.class);

            return new PageImpl<>(content, PageRequest.of(pageable.getPageNumber(),pageable.getPageSize()), pageable.getTotal());
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing Page object "+ e.getMessage());
        }
    }

    public boolean hasPage(String key) {
        return redisTemplate.opsForHash().hasKey(HASH_KEY, key+PAGEABLE_KEY) && redisTemplate.hasKey(key+CONTENT_KEY);
    }
}

