package com.narendra.sams.core.exception;

public class OperationCanNotSucceedException extends Exception {
    private static final long serialVersionUID = 5560901311657113316L;
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OperationCanNotSucceedException() {
        this.message = "Your operation can not succedded, some of the required inputs are missing";
    }

    public OperationCanNotSucceedException(String message) {
        this.message = message;
    }
}
