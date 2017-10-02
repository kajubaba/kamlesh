package com.narendra.sams.web.restws.admission.vo;

public class AjaxSuccessResponse {
    private Long generatedId;
    private String status = "Ok";

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getGeneratedId() {
        return this.generatedId;
    }

    public void setGeneratedId(Long generatedId) {
        this.generatedId = generatedId;
    }
}
