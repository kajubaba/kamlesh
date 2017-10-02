package com.narendra.sams.academics.domain;

import java.util.List;

public class AssessmentActivity {
    private List<AssessmentSkill> assessmentSkills;
    private Long displayOrder;
    private Long id;
    private String name;
    private List<AssessmentActivity> subActivities;

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

    public Long getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    public List<AssessmentSkill> getAssessmentSkills() {
        return this.assessmentSkills;
    }

    public void setAssessmentSkills(List<AssessmentSkill> assessmentSkills) {
        this.assessmentSkills = assessmentSkills;
    }

    public List<AssessmentActivity> getSubActivities() {
        return this.subActivities;
    }

    public void setSubActivities(List<AssessmentActivity> subActivities) {
        this.subActivities = subActivities;
    }
}
