package com.thoughtworks.rslist.Error;

public class RsNotValidException extends RuntimeException {
    private String message;

    public RsNotValidException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
