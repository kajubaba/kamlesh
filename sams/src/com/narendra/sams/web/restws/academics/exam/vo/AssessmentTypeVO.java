package com.narendra.sams.web.restws.academics.exam.vo;

import java.util.List;

public class AssessmentTypeVO {
    private List<EvaluationTermVO> assessmentTerms;
    private String displayName;
    private Long examPatternId;
    private String examPatternName;
    private String gradeCalculationMethod;
    private Long gradeScaleId;
    private Long id;
    private Boolean isFASABasedExam;
    private Boolean isTermBasedAssessment;
    private String name;
    private String scoringMethod;
    private Boolean useAssessmentWeightageAsMaxMarks;

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

    public List<EvaluationTermVO> getAssessmentTerms() {
        return this.assessmentTerms;
    }

    public void setAssessmentTerms(List<EvaluationTermVO> assessmentTerms) {
        this.assessmentTerms = assessmentTerms;
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

    public Long getExamPatternId() {
        return this.examPatternId;
    }

    public void setExamPatternId(Long examPatternId) {
        this.examPatternId = examPatternId;
    }

    public String getExamPatternName() {
        return this.examPatternName;
    }

    public void setExamPatternName(String examPatternName) {
        this.examPatternName = examPatternName;
    }

    public String getGradeCalculationMethod() {
        return this.gradeCalculationMethod;
    }

    public void setGradeCalculationMethod(String gradeCalculationMethod) {
        this.gradeCalculationMethod = gradeCalculationMethod;
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
