package com.ums.exceptions;

import org.springframework.http.ResponseEntity;

import java.util.Date;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String message) {
        super(message);
    }
}
