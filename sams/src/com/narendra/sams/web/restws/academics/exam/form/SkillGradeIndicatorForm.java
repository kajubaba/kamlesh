package com.narendra.sams.web.restws.academics.exam.form;

public class SkillGradeIndicatorForm {
    private Long displayOrder;
    private String grade;
    private Long gradePointId;
    private Long id;
    private Long indicatorId;
    private String overallIndicator;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGradePointId() {
        return this.gradePointId;
    }

    public void setGradePointId(Long gradePointId) {
        this.gradePointId = gradePointId;
    }

    public Long getIndicatorId() {
        return this.indicatorId;
    }

    public void setIndicatorId(Long indicatorId) {
        this.indicatorId = indicatorId;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getOverallIndicator() {
        return this.overallIndicator;
    }

    public void setOverallIndicator(String overallIndicator) {
        this.overallIndicator = overallIndicator;
    }

    public Long getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }
}
