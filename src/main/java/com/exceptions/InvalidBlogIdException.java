package com.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // Automatically sets HTTP status 400 when this exception is thrown.
public class InvalidBlogIdException extends RuntimeException {
    public InvalidBlogIdException(String message) {
        super(message);
    }
}
