package com.narendra.sams.web.restws.academics.vo;

public class SignatureBean {
    private String termName;

    public SignatureBean(String termName) {
        this.termName = termName;
    }

    public String getTermName() {
        return this.termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }
}
