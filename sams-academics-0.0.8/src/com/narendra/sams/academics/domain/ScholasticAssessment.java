package com.narendra.sams.academics.domain;

import java.util.List;

public class ScholasticAssessment {
    private List<AssessmentSubject> assessmentSubjects;
    private List<AssessmentTerm> assessmentTerms;
    private List<ConversionRule> conversionRules;
    private Boolean isFASABasedAssessment;
    private Boolean termBasedAssessment;

    public List<AssessmentSubject> getAssessmentSubjects() {
        return this.assessmentSubjects;
    }

    public void setAssessmentSubjects(List<AssessmentSubject> assessmentSubjects) {
        this.assessmentSubjects = assessmentSubjects;
    }

    public List<AssessmentTerm> getAssessmentTerms() {
        return this.assessmentTerms;
    }

    public void setAssessmentTerms(List<AssessmentTerm> assessmentTerms) {
        this.assessmentTerms = assessmentTerms;
    }

    public Boolean getTermBasedAssessment() {
        return this.termBasedAssessment;
    }

    public void setTermBasedAssessment(Boolean termBasedAssessment) {
        this.termBasedAssessment = termBasedAssessment;
    }

    public List<ConversionRule> getConversionRules() {
        return this.conversionRules;
    }

    public void setConversionRules(List<ConversionRule> conversionRules) {
        this.conversionRules = conversionRules;
    }

    public Boolean getIsFASABasedAssessment() {
        return this.isFASABasedAssessment;
    }

    public void setIsFASABasedAssessment(Boolean isFASABasedAssessment) {
        this.isFASABasedAssessment = isFASABasedAssessment;
    }
}
