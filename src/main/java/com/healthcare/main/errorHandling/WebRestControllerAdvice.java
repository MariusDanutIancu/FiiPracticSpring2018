package com.healthcare.main.errorHandling;


import com.healthcare.main.exception.BadRequestException;
import com.healthcare.main.exception.MethodNotAllowedException;
import com.healthcare.main.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebRestControllerAdvice
{
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFoundException(NotFoundException ex)
    {
        return this.generateErrorDto(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private ErrorDto handleBadRequestException(BadRequestException ex)
    {
        return this.generateErrorDto(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    private ErrorDto handleMethodNotAllowedException(MethodNotAllowedException ex)
    {
        return this.generateErrorDto(HttpStatus.METHOD_NOT_ALLOWED, ex);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    private ErrorDto defaultHandler(Exception ex)
    {
        return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    private ErrorDto generateErrorDto(HttpStatus status, Exception ex)
    {
        return new ErrorDto(status.value(), ex.getMessage());
    }
}
