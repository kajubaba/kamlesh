package com.narendra.sams.web.restws.academics.exam.form;

public class CriteriaScoreForm {
    private Long criteriaId;
    private String freeTextValue;
    private Long gradeScalePointId;
    private Long score;
    private Long scoreId;

    public Long getScoreId() {
        return this.scoreId;
    }

    public void setScoreId(Long scoreId) {
        this.scoreId = scoreId;
    }

    public Long getCriteriaId() {
        return this.criteriaId;
    }

    public void setCriteriaId(Long criteriaId) {
        this.criteriaId = criteriaId;
    }

    public Long getScore() {
        return this.score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getGradeScalePointId() {
        return this.gradeScalePointId;
    }

    public void setGradeScalePointId(Long gradeScalePointId) {
        this.gradeScalePointId = gradeScalePointId;
    }

    public String getFreeTextValue() {
        return this.freeTextValue;
    }

    public void setFreeTextValue(String freeTextValue) {
        this.freeTextValue = freeTextValue;
    }
}
