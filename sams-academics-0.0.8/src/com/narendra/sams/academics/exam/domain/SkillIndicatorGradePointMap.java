package com.narendra.sams.academics.exam.domain;

import com.narendra.sams.academics.domain.GradeScalePoint;

public class SkillIndicatorGradePointMap {
    private GradeScalePoint gradeScalePoint;
    private Long id;
    private String overallIndicator;
    private SkillIndicator skillIndicator;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SkillIndicator getSkillIndicator() {
        return this.skillIndicator;
    }

    public void setSkillIndicator(SkillIndicator skillIndicator) {
        this.skillIndicator = skillIndicator;
    }

    public GradeScalePoint getGradeScalePoint() {
        return this.gradeScalePoint;
    }

    public void setGradeScalePoint(GradeScalePoint gradeScalePoint) {
        this.gradeScalePoint = gradeScalePoint;
    }

    public String getOverallIndicator() {
        return this.overallIndicator;
    }

    public void setOverallIndicator(String overallIndicator) {
        this.overallIndicator = overallIndicator;
    }
}
