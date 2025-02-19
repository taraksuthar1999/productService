package com.example.productservice.services;

import com.example.productservice.dtos.SortDirection;
import com.example.productservice.dtos.fakestore.FakeStoreCreateProductRequestDto;
import com.example.productservice.dtos.fakestore.FakeStoreGetProductResponseDto;
import com.example.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductImpl implements ProductService {

    private RestTemplate restTemplate;

    public FakeStoreProductImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product create(Product product) {
        FakeStoreCreateProductRequestDto request = FakeStoreCreateProductRequestDto.fromProduct(product);
        FakeStoreGetProductResponseDto fakeStoreCreateProductResponseDto = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                request,
                FakeStoreGetProductResponseDto.class
        );
        return fakeStoreCreateProductResponseDto.toProduct();
    }

    @Override
    public void delete(Long id) {

    }

//    @Override
//    public Page<Product> getAll() {
////        throw new RuntimeException();
//        FakeStoreGetProductResponseDto[] response = restTemplate.getForObject(
//                "https://fakestoreapi.com/products",
//                FakeStoreGetProductResponseDto[].class
//        );
//
//        List<Product> products = new ArrayList<>();
//        for(FakeStoreGetProductResponseDto fakeStoreGetProductResponseDto : response){
//            products.add(fakeStoreGetProductResponseDto.toProduct());
//        }
//
//        return null;
//    }

    @Override
    public Page<Product> getAll(int page, int size, String sortBy, SortDirection direction) {
        return null;
    }

    @Override
    public Product getById(Long id) {
        FakeStoreGetProductResponseDto response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id,
                FakeStoreGetProductResponseDto.class
        );
        return response.toProduct();
    }

    @Override
    public Product update(Long id,Product product) {
        HttpEntity<FakeStoreCreateProductRequestDto> request = new HttpEntity<>(FakeStoreCreateProductRequestDto.fromProduct(product));
        ResponseEntity<FakeStoreGetProductResponseDto> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/"+id,
                HttpMethod.PATCH,
                request,
                FakeStoreGetProductResponseDto.class
        );
        return response.getBody().toProduct();
    }

    @Override
    public Product replace(Long id,Product product){
        HttpEntity<FakeStoreCreateProductRequestDto> request = new HttpEntity<>( FakeStoreCreateProductRequestDto.fromProduct(product));

       ResponseEntity<FakeStoreGetProductResponseDto> response = restTemplate.exchange(
               "https://fakestoreapi.com/products/"+id,
                HttpMethod.PUT,
                request,
                FakeStoreGetProductResponseDto.class
        );

       return response.getBody().toProduct();
    }
}
