package com.narendra.sams.web.restws.academics.vo;

public class UPAFooterBean {
    private String grade;
    private String marksRange;

    public UPAFooterBean(String marksRange, String grade) {
        this.marksRange = marksRange;
        this.grade = grade;
    }

    public String getMarksRange() {
        return this.marksRange;
    }

    public void setMarksRange(String marksRange) {
        this.marksRange = marksRange;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
