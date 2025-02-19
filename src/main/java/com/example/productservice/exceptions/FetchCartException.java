package com.example.productservice.exceptions;

public class FetchCartException extends RuntimeException {
    public FetchCartException(String message) {
        super(message);
    }
}
