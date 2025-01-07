package com.example.productservice.exceptions;

public class ProductDeleteFailedException extends RuntimeException {
    public ProductDeleteFailedException(String message) {
        super(message);
    }
}
