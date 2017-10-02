package com.narendra.sams.web.restws.academics.vo;

public class GradePointBean {
    private String grade1;
    private String grade2;
    private String gradePoint1;
    private String gradePoint2;
    private String marksRange1;
    private String marksRange2;

    public GradePointBean(String grade1, String grade2, String marksRange1, String marksRange2, String gradePoint1, String gradePoint2) {
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.marksRange1 = marksRange1;
        this.marksRange2 = marksRange2;
        this.gradePoint1 = gradePoint1;
        this.gradePoint2 = gradePoint2;
    }

    public String getGrade1() {
        return this.grade1;
    }

    public void setGrade1(String grade1) {
        this.grade1 = grade1;
    }

    public String getGrade2() {
        return this.grade2;
    }

    public void setGrade2(String grade2) {
        this.grade2 = grade2;
    }

    public String getMarksRange1() {
        return this.marksRange1;
    }

    public void setMarksRange1(String marksRange1) {
        this.marksRange1 = marksRange1;
    }

    public String getMarksRange2() {
        return this.marksRange2;
    }

    public void setMarksRange2(String marksRange2) {
        this.marksRange2 = marksRange2;
    }

    public String getGradePoint1() {
        return this.gradePoint1;
    }

    public void setGradePoint1(String gradePoint1) {
        this.gradePoint1 = gradePoint1;
    }

    public String getGradePoint2() {
        return this.gradePoint2;
    }

    public void setGradePoint2(String gradePoint2) {
        this.gradePoint2 = gradePoint2;
    }
}
