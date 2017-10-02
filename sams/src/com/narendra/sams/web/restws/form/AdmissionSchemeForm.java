package com.narendra.sams.web.restws.form;

public class AdmissionSchemeForm {
    private Boolean active;
    private Long schemeId;
    private String schemeName;

    public Long getSchemeId() {
        return this.schemeId;
    }

    public void setSchemeId(Long schemeId) {
        this.schemeId = schemeId;
    }

    public String getSchemeName() {
        return this.schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
