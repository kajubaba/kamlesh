package com.narendra.sams.academics.exam.domain;

public class TermAssessment {
    private String assessmentMonth;
    private String displayName;
    private Long displayOrder;
    private EvaluationTerm evaluationTerm;
    private Long id;
    private Long maxMarks;
    private String name;
    private Long weightageInAcademicSession;

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

    public String getAssessmentMonth() {
        return this.assessmentMonth;
    }

    public void setAssessmentMonth(String assessmentMonth) {
        this.assessmentMonth = assessmentMonth;
    }

    public Long getWeightageInAcademicSession() {
        return this.weightageInAcademicSession;
    }

    public void setWeightageInAcademicSession(Long weightageInAcademicSession) {
        this.weightageInAcademicSession = weightageInAcademicSession;
    }

    public EvaluationTerm getEvaluationTerm() {
        return this.evaluationTerm;
    }

    public void setEvaluationTerm(EvaluationTerm evaluationTerm) {
        this.evaluationTerm = evaluationTerm;
    }

    public Long getMaxMarks() {
        return this.maxMarks;
    }

    public void setMaxMarks(Long maxMarks) {
        this.maxMarks = maxMarks;
    }
}
