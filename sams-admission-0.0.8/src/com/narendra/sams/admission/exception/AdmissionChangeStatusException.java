package com.narendra.sams.admission.exception;

public class AdmissionChangeStatusException extends Exception {
    private static final long serialVersionUID = 6433215535386341022L;
    private String message;

    public AdmissionChangeStatusException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
