package com.narendra.sams.admission.exception;

public class NoCourseYearFoundException extends Exception {
    private static final long serialVersionUID = -1448163635250643657L;
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NoCourseYearFoundException() {
        this.message = "No course year found";
    }

    public NoCourseYearFoundException(String message) {
        this.message = message;
    }
}
