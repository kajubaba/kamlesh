package com.narendra.sams.academics.domain;

import java.util.Set;

public class GradeScale {
    private Set<GradeScalePoint> gradeScalePoints;
    private Long id;
    private Boolean isSystemGradeScale;
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

    public Boolean getIsSystemGradeScale() {
        return this.isSystemGradeScale;
    }

    public void setIsSystemGradeScale(Boolean isSystemGradeScale) {
        this.isSystemGradeScale = isSystemGradeScale;
    }

    public Set<GradeScalePoint> getGradeScalePoints() {
        return this.gradeScalePoints;
    }

    public void setGradeScalePoints(Set<GradeScalePoint> gradeScalePoints) {
        this.gradeScalePoints = gradeScalePoints;
    }
}
