package com.example.productservice.exceptions;

public class CartCreationFailedException extends RuntimeException {
    public CartCreationFailedException(String message) {
        super(message);
    }
}
