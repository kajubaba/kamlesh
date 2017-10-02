package com.narendra.sams.admission.exception;

public class ClassCanNotChangeException extends Exception {
    private static final long serialVersionUID = -6422256381874940077L;
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ClassCanNotChangeException(String message) {
        this.message = message;
    }
}
