package com.example.productservice.exceptions;

public class FailedToRemoveFromCartException extends RuntimeException {
    public FailedToRemoveFromCartException(String message) {
        super(message);
    }
}
