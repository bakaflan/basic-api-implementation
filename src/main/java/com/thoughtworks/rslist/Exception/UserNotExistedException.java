package com.thoughtworks.rslist.Exception;

public class UserNotExistedException extends RuntimeException {
    private String message;

    public UserNotExistedException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}