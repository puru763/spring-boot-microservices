package com.programmingtechie.productservice.exception;

public class ProductRetrievalException extends RuntimeException {
    public ProductRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}