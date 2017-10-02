package com.narendra.sams.academics.exam.domain;

import java.util.Set;

public class EvaluationTerm {
    private Long displayOrder;
    private EvaluationType evaluationType;
    private Long id;
    private Boolean isArbitrary;
    private Set<TermAssessment> termAssessments;
    private String termDisplayName;
    private String termName;
    private Long weightageInFinalAssessment;
    private Long workingDays;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTermName() {
        return this.termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermDisplayName() {
        return this.termDisplayName;
    }

    public void setTermDisplayName(String termDisplayName) {
        this.termDisplayName = termDisplayName;
    }

    public EvaluationType getEvaluationType() {
        return this.evaluationType;
    }

    public void setEvaluationType(EvaluationType evaluationType) {
        this.evaluationType = evaluationType;
    }

    public Long getWeightageInFinalAssessment() {
        return this.weightageInFinalAssessment;
    }

    public void setWeightageInFinalAssessment(Long weightageInFinalAssessment) {
        this.weightageInFinalAssessment = weightageInFinalAssessment;
    }

    public Set<TermAssessment> getTermAssessments() {
        return this.termAssessments;
    }

    public void setTermAssessments(Set<TermAssessment> termAssessments) {
        this.termAssessments = termAssessments;
    }

    public Long getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Boolean getIsArbitrary() {
        return this.isArbitrary;
    }

    public void setIsArbitrary(Boolean isArbitrary) {
        this.isArbitrary = isArbitrary;
    }

    public Long getWorkingDays() {
        return this.workingDays;
    }

    public void setWorkingDays(Long workingDays) {
        this.workingDays = workingDays;
    }
}
