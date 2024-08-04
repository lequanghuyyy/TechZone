package com.springboot.shopbubu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderAlreadyShippedException extends RuntimeException {
    public OrderAlreadyShippedException(String message) {
        super(message);
    }
}

