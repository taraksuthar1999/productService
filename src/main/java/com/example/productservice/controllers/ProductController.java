package com.example.productservice.controllers;

import com.example.productservice.dtos.ResponseDto;
import com.example.productservice.dtos.ResponseStatus;
import com.example.productservice.dtos.SortDirection;
import com.example.productservice.dtos.product.ProductResponseDto;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import com.example.productservice.services.RedisPageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/products")
public class ProductController {

    private ProductService productService;

    private RedisPageService redisPageService;

    public ProductController(ProductService productService, RedisPageService redisPageService) {
        this.productService = productService;
        this.redisPageService = redisPageService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<Page<ProductResponseDto>>> getAllProducts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") SortDirection direction,
            @RequestHeader(value = "Authorization", required = false) String token){


        String key = "products_"+page+"_"+size+"_"+sortBy+"_"+direction;
        Page<ProductResponseDto> response = null;
        if(redisPageService.hasPage(key)){
            response = redisPageService.getPage(key, ProductResponseDto.class);
        }else {
            response = productService.getAll(page, size, sortBy, direction)
                    .map(ProductResponseDto::fromProduct);
            redisPageService.savePage(key, response);
        }
        return ResponseEntity.ok()
                .body(new ResponseDto<>(
                        com.example.productservice.dtos.ResponseStatus.SUCCESS,
                        "Products fetched successfully.",
                        response));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ProductResponseDto>> getProduct(@PathVariable Long id){
        Product product = productService.getById(id);
        ProductResponseDto response = ProductResponseDto.fromProduct(product);
        return ResponseEntity.ok()
                .body(new ResponseDto<>(
                        ResponseStatus.SUCCESS,
                        "Product fetched successfully.",
                        response));
    }

}
