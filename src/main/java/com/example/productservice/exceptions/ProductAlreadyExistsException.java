package com.example.productservice.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String s) {
        super(s);
    }
}
