package com.springboot.shopbubu.exception;

public class IllegalArgumentException extends RuntimeException {
    public  IllegalArgumentException(String message) {
        super(message);
    }
}
