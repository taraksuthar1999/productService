package com.example.productservice.exceptions;

public class ProductUpdateFaildException extends RuntimeException{
    public ProductUpdateFaildException(String message) {
        super(message);
    }
}
