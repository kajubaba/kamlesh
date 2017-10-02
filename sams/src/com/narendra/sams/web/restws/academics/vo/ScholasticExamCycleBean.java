package com.narendra.sams.web.restws.academics.vo;

public class ScholasticExamCycleBean {
    private String cycle1;
    private String cycle10;
    private String cycle2;
    private String cycle3;
    private String cycle4;
    private String cycle5;
    private String cycle6;
    private String cycle7;
    private String cycle8;
    private String cycle9;
    private String subject;

    public ScholasticExamCycleBean(String subject, String cycle1, String cycle2, String cycle3, String cycle4, String cycle5, String cycle6, String cycle7, String cycle8, String cycle9, String cycle10) {
        this.subject = subject;
        this.cycle1 = cycle1;
        this.cycle2 = cycle2;
        this.cycle3 = cycle3;
        this.cycle4 = cycle4;
        this.cycle5 = cycle5;
        this.cycle6 = cycle6;
        this.cycle7 = cycle7;
        this.cycle8 = cycle8;
        this.cycle9 = cycle9;
        this.cycle10 = cycle10;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCycle1() {
        return this.cycle1;
    }

    public void setCycle1(String cycle1) {
        this.cycle1 = cycle1;
    }

    public String getCycle2() {
        return this.cycle2;
    }

    public void setCycle2(String cycle2) {
        this.cycle2 = cycle2;
    }

    public String getCycle3() {
        return this.cycle3;
    }

    public void setCycle3(String cycle3) {
        this.cycle3 = cycle3;
    }

    public String getCycle4() {
        return this.cycle4;
    }

    public void setCycle4(String cycle4) {
        this.cycle4 = cycle4;
    }

    public String getCycle5() {
        return this.cycle5;
    }

    public void setCycle5(String cycle5) {
        this.cycle5 = cycle5;
    }

    public String getCycle6() {
        return this.cycle6;
    }

    public void setCycle6(String cycle6) {
        this.cycle6 = cycle6;
    }

    public String getCycle7() {
        return this.cycle7;
    }

    public void setCycle7(String cycle7) {
        this.cycle7 = cycle7;
    }

    public String getCycle8() {
        return this.cycle8;
    }

    public void setCycle8(String cycle8) {
        this.cycle8 = cycle8;
    }

    public String getCycle9() {
        return this.cycle9;
    }

    public void setCycle9(String cycle9) {
        this.cycle9 = cycle9;
    }

    public String getCycle10() {
        return this.cycle10;
    }

    public void setCycle10(String cycle10) {
        this.cycle10 = cycle10;
    }
}
