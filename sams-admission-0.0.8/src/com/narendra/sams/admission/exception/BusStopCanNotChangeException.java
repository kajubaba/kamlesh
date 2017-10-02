package com.narendra.sams.admission.exception;

public class BusStopCanNotChangeException extends Exception {
    private static final long serialVersionUID = -3071063941419948222L;
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BusStopCanNotChangeException(String message) {
        this.message = message;
    }
}
