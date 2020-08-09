package com.thoughtworks.rslist.Exception;

public class IndexOutOfRange extends RuntimeException {
    private String message;

    public IndexOutOfRange(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
