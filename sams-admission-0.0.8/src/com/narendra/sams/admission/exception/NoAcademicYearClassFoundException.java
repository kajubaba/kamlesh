package com.narendra.sams.admission.exception;

public class NoAcademicYearClassFoundException extends Exception {
    private static final long serialVersionUID = 92804998946892934L;
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NoAcademicYearClassFoundException() {
        this.message = "No course year found";
    }

    public NoAcademicYearClassFoundException(String message) {
        this.message = message;
    }
}
