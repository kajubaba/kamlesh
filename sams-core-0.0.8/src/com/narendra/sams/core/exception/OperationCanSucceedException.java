package com.narendra.sams.core.exception;

public class OperationCanSucceedException extends Exception {
    private static final long serialVersionUID = 5560901311657113316L;
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OperationCanSucceedException() {
        this.message = "Your operation can be succeed";
    }

    public OperationCanSucceedException(String message) {
        this.message = message;
    }
}
