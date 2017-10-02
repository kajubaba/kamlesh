package com.narendra.sams.web.restws.academics.exam.vo;

import java.util.List;

public class ExamPatternVO {
    private Integer appliedOnClassesCount;
    private List<EvaluationTermVO> coScholasticEvaluationTerms;
    private Integer evaluationAspectCount;
    private Long evaluationTypeId;
    private String evaluationTypeName;
    private Long id;
    private String name;
    private List<EvaluationTermVO> scholasticEvaluationTerms;
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

    public Integer getEvaluationAspectCount() {
        return this.evaluationAspectCount;
    }

    public void setEvaluationAspectCount(Integer evaluationAspectCount) {
        this.evaluationAspectCount = evaluationAspectCount;
    }

    public Integer getAppliedOnClassesCount() {
        return this.appliedOnClassesCount;
    }

    public void setAppliedOnClassesCount(Integer appliedOnClassesCount) {
        this.appliedOnClassesCount = appliedOnClassesCount;
    }

    public List<EvaluationTermVO> getScholasticEvaluationTerms() {
        return this.scholasticEvaluationTerms;
    }

    public void setScholasticEvaluationTerms(List<EvaluationTermVO> scholasticEvaluationTerms) {
        this.scholasticEvaluationTerms = scholasticEvaluationTerms;
    }

    public List<EvaluationTermVO> getCoScholasticEvaluationTerms() {
        return this.coScholasticEvaluationTerms;
    }

    public void setCoScholasticEvaluationTerms(List<EvaluationTermVO> coScholasticEvaluationTerms) {
        this.coScholasticEvaluationTerms = coScholasticEvaluationTerms;
    }

    public Long getEvaluationTypeId() {
        return this.evaluationTypeId;
    }

    public void setEvaluationTypeId(Long evaluationTypeId) {
        this.evaluationTypeId = evaluationTypeId;
    }

    public String getEvaluationTypeName() {
        return this.evaluationTypeName;
    }

    public void setEvaluationTypeName(String evaluationTypeName) {
        this.evaluationTypeName = evaluationTypeName;
    }

    public Boolean getUseAssessmentWeightageAsMaxMarks() {
        return this.useAssessmentWeightageAsMaxMarks;
    }

    public void setUseAssessmentWeightageAsMaxMarks(Boolean useAssessmentWeightageAsMaxMarks) {
        this.useAssessmentWeightageAsMaxMarks = useAssessmentWeightageAsMaxMarks;
    }
}
