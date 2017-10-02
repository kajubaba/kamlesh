package com.narendra.sams.web.restws.student;

public class ChangeRequestResponse {
    private String message;
    private Boolean showAlert;
    private String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getShowAlert() {
        return this.showAlert;
    }

    public void setShowAlert(Boolean showAlert) {
        this.showAlert = showAlert;
    }
}
