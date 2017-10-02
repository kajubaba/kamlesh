package com.narendra.sams.academics.exam.domain.coscholastic;

public class AssessmentCriteria {
    private Long criteriaId;
    private String displayName;
    private Long displayOrder;
    private String freeTextValue;
    private Long gradeScalePointId;
    private String name;
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

    public Long getScore() {
        return this.score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
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
