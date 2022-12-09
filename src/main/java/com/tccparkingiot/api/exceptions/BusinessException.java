package com.tccparkingiot.api.exceptions;

public class BusinessException extends RuntimeException{


    private static final long serialVersionUID = -7260994291437392167L;

    public BusinessException(String message) {
        super(message);
    }
}
