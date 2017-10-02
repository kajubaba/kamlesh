package com.narendra.sams.academics.domain;

import java.util.ArrayList;
import java.util.List;

public class AssessmentSkill {
    private List<AssessmentCriteria> assessmentCriterias;
    private Long displayOrder;
    private Boolean doNotDisplayOnScoreCard;
    private Long id;
    private Boolean isAdditional;
    private String name;
    private List<String> scores = new ArrayList();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDoNotDisplayOnScoreCard() {
        return this.doNotDisplayOnScoreCard;
    }

    public void setDoNotDisplayOnScoreCard(Boolean doNotDisplayOnScoreCard) {
        this.doNotDisplayOnScoreCard = doNotDisplayOnScoreCard;
    }

    public List<AssessmentCriteria> getAssessmentCriterias() {
        return this.assessmentCriterias;
    }

    public void setAssessmentCriterias(List<AssessmentCriteria> assessmentCriterias) {
        this.assessmentCriterias = assessmentCriterias;
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

    public List<String> getScores() {
        return this.scores;
    }

    public void setScores(List<String> scores) {
        this.scores = scores;
    }

    public Boolean getIsAdditional() {
        return this.isAdditional;
    }

    public void setIsAdditional(Boolean isAdditional) {
        this.isAdditional = isAdditional;
    }
}
