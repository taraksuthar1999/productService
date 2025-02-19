package com.example.productservice.repositories;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);
    Optional<Category> findBySku(String sku);

    @Override
    Category save(Category  category);


    // jpa query example
    @Query("select c.name from Category c")
    List<String> something();

    // native query example
    @Query(value = NativeQuery.GET_PRODUCT_BY_CATEGORY_NAME,
    nativeQuery = true)
    List<Product> somesome(@Param("categoryName") String categoryName);
}
