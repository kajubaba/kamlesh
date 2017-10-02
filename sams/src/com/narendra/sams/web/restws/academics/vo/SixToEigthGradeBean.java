package com.narendra.sams.web.restws.academics.vo;

public class SixToEigthGradeBean {
    private String grade;
    private String gradePoint;
    private String marksRange;

    public SixToEigthGradeBean(String marksRange, String grade, String gradePoint) {
        this.marksRange = marksRange;
        this.grade = grade;
        this.gradePoint = gradePoint;
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

    public String getGradePoint() {
        return this.gradePoint;
    }

    public void setGradePoint(String gradePoint) {
        this.gradePoint = gradePoint;
    }
}
