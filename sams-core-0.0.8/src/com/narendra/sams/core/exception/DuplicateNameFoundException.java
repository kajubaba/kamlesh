package com.narendra.sams.core.exception;

public class DuplicateNameFoundException extends Exception {
    private static final long serialVersionUID = 5560901311657113316L;
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DuplicateNameFoundException() {
        this.message = "Duplicate name found";
    }

    public DuplicateNameFoundException(String message) {
        this.message = message;
    }
}
