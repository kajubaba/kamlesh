package com.narendra.sams.academics.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AssessmentCriteria {
    private Long displayOrder;
    private List<String> gradeBasedScores = new ArrayList();
    private Map<String, String> gradeToIndicatorMap;
    private Long id;
    private List<Long> markBasedScores = new ArrayList();
    private String name;
    private Map<Long, String> termGradeBasedScores = new LinkedHashMap();
    private Map<Long, Long> termMarkBasedScores = new LinkedHashMap();

    public Map<String, String> getGradeToIndicatorMap() {
        return this.gradeToIndicatorMap;
    }

    public void setGradeToIndicatorMap(Map<String, String> gradeToIndicatorMap) {
        this.gradeToIndicatorMap = gradeToIndicatorMap;
    }

    public Map<Long, Long> getTermMarkBasedScores() {
        return this.termMarkBasedScores;
    }

    public void setTermMarkBasedScores(Map<Long, Long> termMarkBasedScores) {
        this.termMarkBasedScores = termMarkBasedScores;
    }

    public Map<Long, String> getTermGradeBasedScores() {
        return this.termGradeBasedScores;
    }

    public void setTermGradeBasedScores(Map<Long, String> termGradeBasedScores) {
        this.termGradeBasedScores = termGradeBasedScores;
    }

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

    public List<Long> getMarkBasedScores() {
        if (!(this.termMarkBasedScores == null || this.termMarkBasedScores.isEmpty())) {
            this.markBasedScores = new ArrayList(this.termMarkBasedScores.values());
        }
        return this.markBasedScores;
    }

    public List<String> getGradeBasedScores() {
        if (!(this.termGradeBasedScores == null || this.termGradeBasedScores.isEmpty())) {
            this.gradeBasedScores = new ArrayList(this.termGradeBasedScores.values());
        }
        return this.gradeBasedScores;
    }
}
