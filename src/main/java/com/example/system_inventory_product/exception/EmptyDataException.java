package com.example.system_inventory_product.exception;

public class EmptyDataException extends RuntimeException {
    public EmptyDataException(String message) {
        super(message);
    }

    public EmptyDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
