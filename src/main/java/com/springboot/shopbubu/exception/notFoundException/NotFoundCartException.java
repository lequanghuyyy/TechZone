package com.springboot.shopbubu.exception.notFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundCartException extends RuntimeException {
    public NotFoundCartException(String message) {
        super(message);
    }
}
