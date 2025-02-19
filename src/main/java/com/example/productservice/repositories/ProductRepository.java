package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.apache.hc.core5.http.impl.routing.PathRoute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    Product save(Product product);

    @Override
    Optional<Product> findById(Long id);

    @Override
    boolean existsById(Long id);

    Page<Product> findAllByIsDeletedFalse(Pageable pageable);

    Optional<Product> findByTitleAndIsDeletedIsFalse(String title);
    Optional<Product> findBySkuAndIsDeletedIsFalse(String sku);

    Optional<Product> findByIsDeletedIsFalseAndSkuAndIdIsNot(String sku, Long id);

    Page<Product> findAllByIsDeletedFalseAndTitleContainingIgnoreCase(String query, Pageable pageable);
}

