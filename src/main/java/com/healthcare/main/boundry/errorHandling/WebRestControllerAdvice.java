package com.healthcare.main.boundry.errorHandling;


import com.healthcare.main.boundry.exception.BadRequestException;
import com.healthcare.main.boundry.exception.MethodNotAllowedException;
import com.healthcare.main.boundry.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebRestControllerAdvice
{
    private static final Logger LOGGER = LoggerFactory.getLogger(WebRestControllerAdvice.class);

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFoundException(NotFoundException ex)
    {
        LOGGER.error("NOT_FOUND error encountered in {} : {}", ex.getStackTrace()[0], ex.getMessage());
        return this.generateErrorDto(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private ErrorDto handleBadRequestException(BadRequestException ex)
    {
        LOGGER.error("BAD_REQUEST error encountered in {} : {}", ex.getStackTrace()[0], ex.getMessage());
        return this.generateErrorDto(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    private ErrorDto handleMethodNotAllowedException(MethodNotAllowedException ex)
    {
        LOGGER.error("METHOD_NOT_ALLOWED error encountered in {} : {}", ex.getStackTrace()[0], ex.getMessage());
        return this.generateErrorDto(HttpStatus.METHOD_NOT_ALLOWED, ex);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    private ErrorDto defaultHandler(Exception ex)
    {
        LOGGER.error("INTERNAL_SERVER_ERROR error encountered in {} : {}", ex.getStackTrace()[0], ex.getMessage());
        return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    private ErrorDto generateErrorDto(HttpStatus status, Exception ex)
    {
        return new ErrorDto(status.value(), ex.getMessage());
    }
}
