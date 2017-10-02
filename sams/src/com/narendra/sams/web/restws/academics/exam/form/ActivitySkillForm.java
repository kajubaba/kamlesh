package com.narendra.sams.web.restws.academics.exam.form;

public class ActivitySkillForm {
    private Long activityId;
    private String displayName;
    private Long displayOrder;
    private Boolean doNotDisplayOnScorecard;
    private Long id;
    private String name;

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

    public Long getActivityId() {
        return this.activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getDisplayOrder() {
        return this.displayOrder;
    }

    public Boolean getDoNotDisplayOnScorecard() {
        return this.doNotDisplayOnScorecard;
    }

    public void setDoNotDisplayOnScorecard(Boolean doNotDisplayOnScorecard) {
        this.doNotDisplayOnScorecard = doNotDisplayOnScorecard;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }
}
