package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;

import java.util.List;


public interface ProductService {
    Product create(Product product);

    List<Product> getAll();

    Product getById(Long id);

    Product update(Long id,Product product);

    Product replace(Long id, Product product);

    void delete(Long id);

}
