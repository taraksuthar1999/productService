package com.example.productservice.repositories;

public interface NativeQuery {
    String GET_PRODUCT_BY_CATEGORY_NAME = "select * from product p join category c where c.name = :categoryName";
}
