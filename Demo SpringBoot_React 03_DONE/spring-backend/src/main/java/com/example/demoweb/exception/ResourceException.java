package com.example.demoweb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ResourceException(String message) {
        super(message);
    }
}
