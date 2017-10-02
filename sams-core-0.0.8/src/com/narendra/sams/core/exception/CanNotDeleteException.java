package com.narendra.sams.core.exception;

public class CanNotDeleteException extends Exception {
    private static final long serialVersionUID = 5560901311657113316L;
    private String message;

    public String getMessage() {
        return this.message;
    }

    public CanNotDeleteException(String message) {
        this.message = message;
    }
}
