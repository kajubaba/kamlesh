package com.narendra.sams.web.restws.communication.form;

public class ConversationsSearchForm {
    private String frm;
    private Long mode;
    private String to;
    private Long usr;

    public String getFrm() {
        return this.frm;
    }

    public void setFrm(String frm) {
        this.frm = frm;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Long getUsr() {
        return this.usr;
    }

    public void setUsr(Long usr) {
        this.usr = usr;
    }

    public Long getMode() {
        return this.mode;
    }

    public void setMode(Long mode) {
        this.mode = mode;
    }
}
