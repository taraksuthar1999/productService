package com.example.productservice.exceptions;

import java.net.PortUnreachableException;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message){
        super(message);
    }
}
