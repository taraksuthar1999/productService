package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.apache.hc.core5.http.impl.routing.PathRoute;
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

    List<Product> findAll();

    List<Product> findAllByIsDeletedFalse();
}

