package com.narendra.sams.academics.exam.domain;

import java.util.Set;

public class SkillIndicator {
    private ActivitySkill activitySkill;
    private String displayName;
    private Long displaySeqNo;
    private Long id;
    private String name;
    private Set<SkillIndicatorGradePointMap> skillIndicatorGradePointMaps;

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

    public ActivitySkill getActivitySkill() {
        return this.activitySkill;
    }

    public void setActivitySkill(ActivitySkill activitySkill) {
        this.activitySkill = activitySkill;
    }

    public Long getDisplaySeqNo() {
        return this.displaySeqNo;
    }

    public void setDisplaySeqNo(Long displaySeqNo) {
        this.displaySeqNo = displaySeqNo;
    }

    public Set<SkillIndicatorGradePointMap> getSkillIndicatorGradePointMaps() {
        return this.skillIndicatorGradePointMaps;
    }

    public void setSkillIndicatorGradePointMaps(Set<SkillIndicatorGradePointMap> skillIndicatorGradePointMaps) {
        this.skillIndicatorGradePointMaps = skillIndicatorGradePointMaps;
    }
}
