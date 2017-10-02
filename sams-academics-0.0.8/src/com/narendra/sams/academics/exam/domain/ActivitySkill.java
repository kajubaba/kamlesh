package com.narendra.sams.academics.exam.domain;

import java.util.Set;

public class ActivitySkill {
    private CoScholasticActivity activity;
    private Long displaySeqNo;
    private Boolean doNotDisplayOnScoreCard;
    private Long id;
    private Set<SkillIndicator> indicators;
    private Boolean isAdditional;
    private ActivitySkill parentSkill;
    private String skillDisplayName;
    private String skillName;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return this.skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillDisplayName() {
        return this.skillDisplayName;
    }

    public void setSkillDisplayName(String skillDisplayName) {
        this.skillDisplayName = skillDisplayName;
    }

    public Long getDisplaySeqNo() {
        return this.displaySeqNo;
    }

    public void setDisplaySeqNo(Long displaySeqNo) {
        this.displaySeqNo = displaySeqNo;
    }

    public ActivitySkill getParentSkill() {
        return this.parentSkill;
    }

    public void setParentSkill(ActivitySkill parentSkill) {
        this.parentSkill = parentSkill;
    }

    public CoScholasticActivity getActivity() {
        return this.activity;
    }

    public void setActivity(CoScholasticActivity activity) {
        this.activity = activity;
    }

    public Set<SkillIndicator> getIndicators() {
        return this.indicators;
    }

    public void setIndicators(Set<SkillIndicator> indicators) {
        this.indicators = indicators;
    }

    public Boolean getDoNotDisplayOnScoreCard() {
        return this.doNotDisplayOnScoreCard;
    }

    public void setDoNotDisplayOnScoreCard(Boolean doNotDisplayOnScoreCard) {
        this.doNotDisplayOnScoreCard = doNotDisplayOnScoreCard;
    }

    public Boolean getIsAdditional() {
        return this.isAdditional;
    }

    public void setIsAdditional(Boolean isAdditional) {
        this.isAdditional = isAdditional;
    }
}
