package com.narendra.sams.academics.domain;

public class ConversionRule {
    private String Grade;
    private Long displayOrder;
    private float from;
    private String gradeMeaning;
    private Float gradePoint;
    private float to;

    public float getFrom() {
        return this.from;
    }

    public void setFrom(float from) {
        this.from = from;
    }

    public float getTo() {
        return this.to;
    }

    public void setTo(float to) {
        this.to = to;
    }

    public String getGrade() {
        return this.Grade;
    }

    public void setGrade(String grade) {
        this.Grade = grade;
    }

    public Float getGradePoint() {
        return this.gradePoint;
    }

    public void setGradePoint(Float gradePoint) {
        this.gradePoint = gradePoint;
    }

    public String getGradeMeaning() {
        return this.gradeMeaning;
    }

    public void setGradeMeaning(String gradeMeaning) {
        this.gradeMeaning = gradeMeaning;
    }

    public Long getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }
}
