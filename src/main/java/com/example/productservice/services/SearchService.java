package com.example.productservice.services;

import com.example.productservice.dtos.SortDirection;
import com.example.productservice.models.Product;
import org.springframework.data.domain.Page;

public interface SearchService {
    Page<Product> searchProducts(String query, int pageNumber, int pageSize, String sortBy, SortDirection direction);
}
