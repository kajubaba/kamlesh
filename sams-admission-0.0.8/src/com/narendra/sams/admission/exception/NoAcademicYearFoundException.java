package com.narendra.sams.admission.exception;

public class NoAcademicYearFoundException extends Exception {
    private static final long serialVersionUID = 3386437920826877513L;
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NoAcademicYearFoundException() {
        this.message = "No academic year found";
    }

    public NoAcademicYearFoundException(String message) {
        this.message = message;
    }
}
