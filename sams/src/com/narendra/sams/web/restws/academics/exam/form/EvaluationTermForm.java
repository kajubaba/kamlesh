package com.narendra.sams.web.restws.academics.exam.form;

public class EvaluationTermForm {
    private String displayName;
    private Long displayOrder;
    private Long evaluationTypeId;
    private Long id;
    private Boolean isScholastic;
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

    public Long getWeightage() {
        return this.weightage;
    }

    public void setWeightage(Long weightage) {
        this.weightage = weightage;
    }

    public Long getEvaluationTypeId() {
        return this.evaluationTypeId;
    }

    public void setEvaluationTypeId(Long evaluationTypeId) {
        this.evaluationTypeId = evaluationTypeId;
    }

    public Boolean getIsScholastic() {
        return this.isScholastic;
    }

    public void setIsScholastic(Boolean isScholastic) {
        this.isScholastic = isScholastic;
    }

    public Long getWorkingDays() {
        return this.workingDays;
    }

    public void setWorkingDays(Long workingDays) {
        this.workingDays = workingDays;
    }
}
