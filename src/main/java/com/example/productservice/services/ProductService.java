package com.example.productservice.services;

import com.example.productservice.dtos.SortDirection;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductService {
    Product create(Product product);

    Page<Product> getAll(int page, int size, String sortBy, SortDirection direction);

    Product getById(Long id);

    Product update(Long id,Product product);

    Product replace(Long id, Product product);

    void delete(Long id);

}
