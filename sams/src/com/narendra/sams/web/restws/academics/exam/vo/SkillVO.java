package com.narendra.sams.web.restws.academics.exam.vo;

import java.util.List;

public class SkillVO {
    private String displayName;
    private Long displayOrder;
    private Boolean doNotDisplayOnScoreCard;
    private Long id;
    private List<SkillIndicatorVO> indicators;
    private Boolean isDefault;
    private String name;
    private Boolean scorable;
    private List<SkillGradeIndicatorVO> skillGradeIndicatorVOs;

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

    public List<SkillIndicatorVO> getIndicators() {
        return this.indicators;
    }

    public void setIndicators(List<SkillIndicatorVO> indicators) {
        this.indicators = indicators;
    }

    public Boolean getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Long getDisplayOrder() {
        return this.displayOrder;
    }

    public Boolean getDoNotDisplayOnScoreCard() {
        return this.doNotDisplayOnScoreCard;
    }

    public void setDoNotDisplayOnScoreCard(Boolean doNotDisplayOnScoreCard) {
        this.doNotDisplayOnScoreCard = doNotDisplayOnScoreCard;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    public List<SkillGradeIndicatorVO> getSkillGradeIndicatorVOs() {
        return this.skillGradeIndicatorVOs;
    }

    public void setSkillGradeIndicatorVOs(List<SkillGradeIndicatorVO> skillGradeIndicatorVOs) {
        this.skillGradeIndicatorVOs = skillGradeIndicatorVOs;
    }
}
