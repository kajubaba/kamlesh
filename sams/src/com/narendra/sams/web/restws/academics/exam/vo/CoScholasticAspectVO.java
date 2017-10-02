package com.narendra.sams.web.restws.academics.exam.vo;

import java.util.ArrayList;
import java.util.List;

public class CoScholasticAspectVO {
    private List<ActivityVO> activities = new ArrayList();
    private Long evaluationTypeId;
    private Boolean isGradeToIndicatorBasedExam;
    private Boolean isIndicatorBased;
    private Boolean isSkillBased;

    public Long getEvaluationTypeId() {
        return this.evaluationTypeId;
    }

    public void setEvaluationTypeId(Long evaluationTypeId) {
        this.evaluationTypeId = evaluationTypeId;
    }

    public List<ActivityVO> getActivities() {
        return this.activities;
    }

    public void setActivities(List<ActivityVO> activities) {
        this.activities = activities;
    }

    public Boolean getIsSkillBased() {
        return this.isSkillBased;
    }

    public void setIsSkillBased(Boolean isSkillBased) {
        this.isSkillBased = isSkillBased;
    }

    public Boolean getIsIndicatorBased() {
        return this.isIndicatorBased;
    }

    public void setIsIndicatorBased(Boolean isIndicatorBased) {
        this.isIndicatorBased = isIndicatorBased;
    }

    public Boolean getIsGradeToIndicatorBasedExam() {
        return this.isGradeToIndicatorBasedExam;
    }

    public void setIsGradeToIndicatorBasedExam(Boolean isGradeToIndicatorBasedExam) {
        this.isGradeToIndicatorBasedExam = isGradeToIndicatorBasedExam;
    }
}
