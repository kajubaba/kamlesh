package com.narendra.sams.web.restws.academics.exam.vo;

public class GradeScalePointVO {
    private Long displayOrder;
    private Float gradPointFrom;
    private Float gradPointTo;
    private String grade;
    private String gradeMeaning;
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

    public Long getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }
}
