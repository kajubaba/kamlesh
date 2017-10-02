package com.narendra.sams.academics.domain;

import java.util.List;

public class AssessmentTerm {
    private List<AssessmentName> assessmentNames;
    private Long displayOrder;
    private Long id;
    private Boolean isArbitrary = Boolean.FALSE;
    private String name;

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

    public List<AssessmentName> getAssessmentNames() {
        return this.assessmentNames;
    }

    public void setAssessmentNames(List<AssessmentName> assessmentNames) {
        this.assessmentNames = assessmentNames;
    }

    public Boolean getIsArbitrary() {
        return this.isArbitrary;
    }

    public void setIsArbitrary(Boolean isArbitrary) {
        this.isArbitrary = isArbitrary;
    }
}
