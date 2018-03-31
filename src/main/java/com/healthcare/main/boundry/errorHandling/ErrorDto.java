package com.healthcare.main.boundry.errorHandling;

/**
 *
 */
public class ErrorDto
{
    private int statusCode;
    private String message;

    public ErrorDto() {}

    /**
     *
     * @param statusCode
     * @param message
     */
    public ErrorDto(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     *
     * @return
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     *
     * @param statusCode
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
