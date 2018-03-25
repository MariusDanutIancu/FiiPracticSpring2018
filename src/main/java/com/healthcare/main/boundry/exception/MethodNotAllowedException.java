package com.healthcare.main.boundry.exception;

public class MethodNotAllowedException extends HealthGenericException{
    public MethodNotAllowedException(String message) {
        super(message);
    }

    public MethodNotAllowedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
