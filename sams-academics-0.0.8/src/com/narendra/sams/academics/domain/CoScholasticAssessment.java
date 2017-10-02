package com.narendra.sams.academics.domain;

import java.util.List;

public class CoScholasticAssessment {
    private List<AssessmentActivity> assessmentActivities;
    private List<AssessmentName> assessmentNames;
    private List<ConversionRule> conversionRules;
    private String gradeConversionMethod;
    private Boolean isMarkBasedAssessment;

    public List<AssessmentActivity> getAssessmentActivities() {
        return this.assessmentActivities;
    }

    public void setAssessmentActivities(List<AssessmentActivity> assessmentActivities) {
        this.assessmentActivities = assessmentActivities;
    }

    public List<AssessmentName> getAssessmentNames() {
        return this.assessmentNames;
    }

    public void setAssessmentNames(List<AssessmentName> assessmentNames) {
        this.assessmentNames = assessmentNames;
    }

    public Boolean getIsMarkBasedAssessment() {
        return this.isMarkBasedAssessment;
    }

    public void setIsMarkBasedAssessment(Boolean isMarkBasedAssessment) {
        this.isMarkBasedAssessment = isMarkBasedAssessment;
    }

    public List<ConversionRule> getConversionRules() {
        return this.conversionRules;
    }

    public void setConversionRules(List<ConversionRule> conversionRules) {
        this.conversionRules = conversionRules;
    }

    public String getGradeConversionMethod() {
        return this.gradeConversionMethod;
    }

    public void setGradeConversionMethod(String gradeConversionMethod) {
        this.gradeConversionMethod = gradeConversionMethod;
    }
}
