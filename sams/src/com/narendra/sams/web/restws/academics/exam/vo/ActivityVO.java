package com.narendra.sams.web.restws.academics.exam.vo;

import java.util.List;

public class ActivityVO {
    private String displayName;
    private Long displayOrder;
    private Long id;
    private Boolean isSkillBasedAssessment;
    private String name;
    private Boolean scorable;
    private List<SkillVO> skills;
    private List<ActivityVO> subActivities;

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

    public Boolean getScorable() {
        return this.scorable;
    }

    public void setScorable(Boolean scorable) {
        this.scorable = scorable;
    }

    public List<ActivityVO> getSubActivities() {
        return this.subActivities;
    }

    public void setSubActivities(List<ActivityVO> subActivities) {
        this.subActivities = subActivities;
    }

    public List<SkillVO> getSkills() {
        return this.skills;
    }

    public void setSkills(List<SkillVO> skills) {
        this.skills = skills;
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
