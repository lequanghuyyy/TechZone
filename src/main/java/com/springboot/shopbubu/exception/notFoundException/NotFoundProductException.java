package com.springboot.shopbubu.exception.notFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundProductException extends RuntimeException {
    public NotFoundProductException(String message) {
        super(message);
    }
}
