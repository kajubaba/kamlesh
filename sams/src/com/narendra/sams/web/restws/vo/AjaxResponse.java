package com.narendra.sams.web.restws.vo;

import com.narendra.sams.web.utils.AJAXResponseStatus;

public class AjaxResponse {
    private Long generatedId;
    private String status;

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

    public static AjaxResponse successResponse() {
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(AJAXResponseStatus.SUCCESS);
        return ajaxResponse;
    }
}
