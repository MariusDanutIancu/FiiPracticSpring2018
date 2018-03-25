package com.healthcare.main.boundry.exception;

public class NotFoundException extends HealthGenericException{

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
