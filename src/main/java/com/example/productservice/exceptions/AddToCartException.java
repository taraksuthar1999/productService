package com.example.productservice.exceptions;

public class AddToCartException extends RuntimeException {
    public AddToCartException(String message) {
        super(message);
    }
}
