package com.springboot.shopbubu.exception.notFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundCategoryException extends RuntimeException {
    public NotFoundCategoryException(String message) {
        super(message);
    }
}
