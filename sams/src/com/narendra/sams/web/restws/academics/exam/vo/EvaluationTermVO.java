package com.narendra.sams.web.restws.academics.exam.vo;

import java.util.List;

public class EvaluationTermVO {
    private List<TermAssessmentVO> assessments;
    private String displayName;
    private Long displayOrder;
    private Long id;
    private String name;
    private Long weightage;
    private Long workingDays;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    public List<TermAssessmentVO> getAssessments() {
        return this.assessments;
    }

    public void setAssessments(List<TermAssessmentVO> assessments) {
        this.assessments = assessments;
    }

    public Long getWeightage() {
        return this.weightage;
    }

    public void setWeightage(Long weightage) {
        this.weightage = weightage;
    }

    public Long getWorkingDays() {
        return this.workingDays;
    }

    public void setWorkingDays(Long workingDays) {
        this.workingDays = workingDays;
    }
}
