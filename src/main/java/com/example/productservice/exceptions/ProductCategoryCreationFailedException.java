package com.example.productservice.exceptions;

public class ProductCategoryCreationFailedException extends RuntimeException {
    public ProductCategoryCreationFailedException(String message) {
        super(message);
    }
}
