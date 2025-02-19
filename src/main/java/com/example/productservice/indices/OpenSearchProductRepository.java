package com.example.productservice.indices;

import com.example.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface OpenSearchProductRepository extends ElasticsearchRepository<Product,Long> {
    Page<Product> findAllByIsDeletedFalseAndTitleContainingIgnoreCase(String query, Pageable pageable);

    @Override
    Product save(Product product);
    @Override
    Optional<Product> findById(Long id);

    void deleteById(Long id);


}
