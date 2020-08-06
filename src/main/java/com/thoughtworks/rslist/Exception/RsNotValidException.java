package com.thoughtworks.rslist.Exception;

public class RsNotValidException extends RuntimeException {
    private String message;

    public RsNotValidException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
