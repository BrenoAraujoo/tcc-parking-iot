package com.tccparkingiot.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityInUseException extends RuntimeException{

    private static final long serialVersionUID = -8876824266227730137L;

    public EntityInUseException(String message) {
        super(message);
    }
}
