package com.narendra.sams.academics.domain;

public class GradeScalePoint {
    private Long displaySeqNo;
    private Float gradPointFrom;
    private Float gradPointTo;
    private String grade;
    private String gradeMeaning;
    private Float gradePoint;
    private GradeScale gradeScale;
    private Long id;
    private Float marksFrom;
    private Float marksTo;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getMarksFrom() {
        return this.marksFrom;
    }

    public void setMarksFrom(Float marksFrom) {
        this.marksFrom = marksFrom;
    }

    public Float getMarksTo() {
        return this.marksTo;
    }

    public void setMarksTo(Float marksTo) {
        this.marksTo = marksTo;
    }

    public Float getGradPointFrom() {
        return this.gradPointFrom;
    }

    public void setGradPointFrom(Float gradPointFrom) {
        this.gradPointFrom = gradPointFrom;
    }

    public Float getGradPointTo() {
        return this.gradPointTo;
    }

    public void setGradPointTo(Float gradPointTo) {
        this.gradPointTo = gradPointTo;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGradeMeaning() {
        return this.gradeMeaning;
    }

    public void setGradeMeaning(String gradeMeaning) {
        this.gradeMeaning = gradeMeaning;
    }

    public GradeScale getGradeScale() {
        return this.gradeScale;
    }

    public void setGradeScale(GradeScale gradeScale) {
        this.gradeScale = gradeScale;
    }

    public Long getDisplaySeqNo() {
        return this.displaySeqNo;
    }

    public void setDisplaySeqNo(Long displaySeqNo) {
        this.displaySeqNo = displaySeqNo;
    }

    public Float getGradePoint() {
        return this.gradePoint;
    }

    public void setGradePoint(Float gradePoint) {
        this.gradePoint = gradePoint;
    }
}
