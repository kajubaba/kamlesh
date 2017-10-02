package com.narendra.sams.web.restws.academics.exam.form;

public class AssessmentGradeAndScoringMethodForm {
    private Long assessmentTypeId;
    private String gradeCalculationMethod;
    private Long gradeScaleId;
    private Boolean isFASABasedExam;
    private Boolean isTermBasedAssessment;
    private Long maxMarks;
    private String scoringMethod;
    private Boolean useAssessmentWeightageAsMaxMarks;

    public Long getAssessmentTypeId() {
        return this.assessmentTypeId;
    }

    public void setAssessmentTypeId(Long assessmentTypeId) {
        this.assessmentTypeId = assessmentTypeId;
    }

    public Long getGradeScaleId() {
        return this.gradeScaleId;
    }

    public void setGradeScaleId(Long gradeScaleId) {
        this.gradeScaleId = gradeScaleId;
    }

    public String getScoringMethod() {
        return this.scoringMethod;
    }

    public void setScoringMethod(String scoringMethod) {
        this.scoringMethod = scoringMethod;
    }

    public String getGradeCalculationMethod() {
        return this.gradeCalculationMethod;
    }

    public void setGradeCalculationMethod(String gradeCalculationMethod) {
        this.gradeCalculationMethod = gradeCalculationMethod;
    }

    public Long getMaxMarks() {
        return this.maxMarks;
    }

    public void setMaxMarks(Long maxMarks) {
        this.maxMarks = maxMarks;
    }

    public Boolean getIsFASABasedExam() {
        return this.isFASABasedExam;
    }

    public void setIsFASABasedExam(Boolean isFASABasedExam) {
        this.isFASABasedExam = isFASABasedExam;
    }

    public Boolean getIsTermBasedAssessment() {
        return this.isTermBasedAssessment;
    }

    public void setIsTermBasedAssessment(Boolean isTermBasedAssessment) {
        this.isTermBasedAssessment = isTermBasedAssessment;
    }

    public Boolean getUseAssessmentWeightageAsMaxMarks() {
        return this.useAssessmentWeightageAsMaxMarks;
    }

    public void setUseAssessmentWeightageAsMaxMarks(Boolean useAssessmentWeightageAsMaxMarks) {
        this.useAssessmentWeightageAsMaxMarks = useAssessmentWeightageAsMaxMarks;
    }
}
