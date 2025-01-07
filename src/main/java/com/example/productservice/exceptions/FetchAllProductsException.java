package com.example.productservice.exceptions;

public class FetchAllProductsException extends RuntimeException {
    public FetchAllProductsException(String message) {
        super(message);
    }
}
