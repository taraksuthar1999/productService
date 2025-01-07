
package com.example.productservice.exceptions;
public class ProductCreationFailedException extends RuntimeException {
    public ProductCreationFailedException(String message) {
        super(message);
    }
}
