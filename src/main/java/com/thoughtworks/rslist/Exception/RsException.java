package com.thoughtworks.rslist.Exception;

public class RsException extends RuntimeException {
    private String message;

    public RsException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
