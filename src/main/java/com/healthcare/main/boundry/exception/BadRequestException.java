package com.healthcare.main.boundry.exception;

public class BadRequestException extends HealthGenericException{

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
