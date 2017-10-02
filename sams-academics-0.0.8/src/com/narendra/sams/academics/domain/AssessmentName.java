package com.narendra.sams.academics.domain;

public class AssessmentName {
    private Long assessmentTermId;
    private Long displayOrder;
    private Long id;
    private Boolean isArbitrary = Boolean.FALSE;
    private Boolean isDefault;
    private Boolean isFAAssessment;
    private Float marks;
    private Long maxMarks;
    private String name;
    private Long weightageInFinalExam;

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

    public Long getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Boolean getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getIsArbitrary() {
        return this.isArbitrary;
    }

    public void setIsArbitrary(Boolean isArbitrary) {
        this.isArbitrary = isArbitrary;
    }

    public Float getMarks() {
        return this.marks;
    }

    public Long getMaxMarks() {
        return this.maxMarks;
    }

    public void setMaxMarks(Long maxMarks) {
        this.maxMarks = maxMarks;
    }

    public void setMarks(Float marks) {
        this.marks = marks;
    }

    public Long getAssessmentTermId() {
        return this.assessmentTermId;
    }

    public void setAssessmentTermId(Long assessmentTermId) {
        this.assessmentTermId = assessmentTermId;
    }

    public Boolean getIsFAAssessment() {
        return this.isFAAssessment;
    }

    public void setIsFAAssessment(Boolean isFAAssessment) {
        this.isFAAssessment = isFAAssessment;
    }

    public Long getWeightageInFinalExam() {
        return this.weightageInFinalExam;
    }

    public void setWeightageInFinalExam(Long weightageInFinalExam) {
        this.weightageInFinalExam = weightageInFinalExam;
    }
}
