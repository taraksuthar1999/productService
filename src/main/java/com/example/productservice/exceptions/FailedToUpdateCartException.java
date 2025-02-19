package com.example.productservice.exceptions;

public class FailedToUpdateCartException extends RuntimeException {
    public FailedToUpdateCartException(String message) {
        super(message);
    }
}
