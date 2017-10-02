package com.narendra.sams.academics.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AssessmentSubject {
    private List<AssessmentName> assessmentNames;
    private Long displayOrder;
    private Float gradePoint;
    private List<String> grades = new ArrayList();
    private Long id;
    private String name;
    private Map<Long, String> scoreGradeMap = new LinkedHashMap();
    private Map<Long, Float> scoreMarksMap = new LinkedHashMap();

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

    public List<String> getGrades() {
        if (!(this.scoreGradeMap == null || this.scoreGradeMap.isEmpty())) {
            this.grades = new ArrayList(this.scoreGradeMap.values());
        }
        return this.grades;
    }

    public Map<Long, Float> getScoreMarksMap() {
        return this.scoreMarksMap;
    }

    public void setScoreMarksMap(Map<Long, Float> scoreMarksMap) {
        this.scoreMarksMap = scoreMarksMap;
    }

    public void setGrades(List<String> grades) {
        this.grades = grades;
    }

    public Map<Long, String> getScoreGradeMap() {
        return this.scoreGradeMap;
    }

    public void setScoreGradeMap(Map<Long, String> scoreGradeMap) {
        this.scoreGradeMap = scoreGradeMap;
    }

    public List<AssessmentName> getAssessmentNames() {
        return this.assessmentNames;
    }

    public void setAssessmentNames(List<AssessmentName> assessmentNames) {
        this.assessmentNames = assessmentNames;
    }

    public Float getGradePoint() {
        return this.gradePoint;
    }

    public void setGradePoint(Float gradePoint) {
        this.gradePoint = gradePoint;
    }
}
