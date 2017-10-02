package com.narendra.sams.web.restws.academics.vo;

public class FirstToFifthScholasticBean {
    private String subject;
    private String valTerm1;
    private String valTerm2;
    private String valTerm3;

    public FirstToFifthScholasticBean(String subject, String valTerm1, String valTerm2, String valTerm3) {
        this.subject = subject;
        this.valTerm1 = valTerm1;
        this.valTerm2 = valTerm2;
        this.valTerm3 = valTerm3;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getValTerm1() {
        return this.valTerm1;
    }

    public void setValTerm1(String valTerm1) {
        this.valTerm1 = valTerm1;
    }

    public String getValTerm2() {
        return this.valTerm2;
    }

    public void setValTerm2(String valTerm2) {
        this.valTerm2 = valTerm2;
    }

    public String getValTerm3() {
        return this.valTerm3;
    }

    public void setValTerm3(String valTerm3) {
        this.valTerm3 = valTerm3;
    }
}
