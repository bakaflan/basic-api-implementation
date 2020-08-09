package com.thoughtworks.rslist.Exception;

public class LocalDateTimeException extends RuntimeException {
    private String message;

    public LocalDateTimeException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}