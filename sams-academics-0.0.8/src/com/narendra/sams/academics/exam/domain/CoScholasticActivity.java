package com.narendra.sams.academics.exam.domain;

import java.util.Set;

public class CoScholasticActivity {
    private String activityDisplayName;
    private String activityName;
    private Set<ActivitySkill> activitySkills;
    private Long displaySeqNo;
    private EvaluationType evaluationType;
    private Long id;
    private Boolean isSkillBasedAssessment;
    private CoScholasticActivity parentActivity;
    private Set<CoScholasticActivity> subActivities;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDisplayName() {
        return this.activityDisplayName;
    }

    public void setActivityDisplayName(String activityDisplayName) {
        this.activityDisplayName = activityDisplayName;
    }

    public Long getDisplaySeqNo() {
        return this.displaySeqNo;
    }

    public void setDisplaySeqNo(Long displaySeqNo) {
        this.displaySeqNo = displaySeqNo;
    }

    public CoScholasticActivity getParentActivity() {
        return this.parentActivity;
    }

    public void setParentActivity(CoScholasticActivity parentActivity) {
        this.parentActivity = parentActivity;
    }

    public EvaluationType getEvaluationType() {
        return this.evaluationType;
    }

    public void setEvaluationType(EvaluationType evaluationType) {
        this.evaluationType = evaluationType;
    }

    public Set<ActivitySkill> getActivitySkills() {
        return this.activitySkills;
    }

    public void setActivitySkills(Set<ActivitySkill> activitySkills) {
        this.activitySkills = activitySkills;
    }

    public Set<CoScholasticActivity> getSubActivities() {
        return this.subActivities;
    }

    public void setSubActivities(Set<CoScholasticActivity> subActivities) {
        this.subActivities = subActivities;
    }

    public Boolean getIsSkillBasedAssessment() {
        return this.isSkillBasedAssessment;
    }

    public void setIsSkillBasedAssessment(Boolean isSkillBasedAssessment) {
        this.isSkillBasedAssessment = isSkillBasedAssessment;
    }
}
