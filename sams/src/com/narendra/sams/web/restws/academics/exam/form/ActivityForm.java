package com.narendra.sams.web.restws.academics.exam.form;

public class ActivityForm {
    private String displayName;
    private Long displayOrder;
    private Long evaluationTypeId;
    private Long id;
    private Boolean isSkillBasedAssessment;
    private String name;
    private Long parentActivityId;

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

    public Long getParentActivityId() {
        return this.parentActivityId;
    }

    public void setParentActivityId(Long parentActivityId) {
        this.parentActivityId = parentActivityId;
    }

    public Long getEvaluationTypeId() {
        return this.evaluationTypeId;
    }

    public void setEvaluationTypeId(Long evaluationTypeId) {
        this.evaluationTypeId = evaluationTypeId;
    }

    public Long getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Boolean getIsSkillBasedAssessment() {
        return this.isSkillBasedAssessment;
    }

    public void setIsSkillBasedAssessment(Boolean isSkillBasedAssessment) {
        this.isSkillBasedAssessment = isSkillBasedAssessment;
    }
}
