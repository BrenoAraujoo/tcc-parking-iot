package com.tccparkingiot.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -8876824266227730137L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
