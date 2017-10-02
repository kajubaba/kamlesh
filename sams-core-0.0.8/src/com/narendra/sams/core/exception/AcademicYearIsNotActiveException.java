package com.narendra.sams.core.exception;

public class AcademicYearIsNotActiveException extends Exception {
    private static final long serialVersionUID = 8580386938645275589L;
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AcademicYearIsNotActiveException() {
        this.message = "Academic Year is not active. This operation is allowed on 'active' academic year only";
    }

    public AcademicYearIsNotActiveException(String message) {
        this.message = message;
    }
}
