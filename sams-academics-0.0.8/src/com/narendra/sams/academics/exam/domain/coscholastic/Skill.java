package com.narendra.sams.academics.exam.domain.coscholastic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;

public class Skill {
    private Map<Long, AssessmentCriteria> assessmentCriteriaMap = new HashMap();
    private List<AssessmentCriteria> assessmentCriterias;
    private String displayName;
    private Long displayOrder;
    private Long id;
    private Boolean isAdditional;
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

    public Long getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Map<Long, AssessmentCriteria> getAssessmentCriteriaMap() {
        return this.assessmentCriteriaMap;
    }

    public void setAssessmentCriteriaMap(Map<Long, AssessmentCriteria> assessmentCriteriaMap) {
        this.assessmentCriteriaMap = assessmentCriteriaMap;
    }

    public List<AssessmentCriteria> getAssessmentCriterias() {
        if (this.assessmentCriteriaMap != null) {
            this.assessmentCriterias = new ArrayList(this.assessmentCriteriaMap.values());
            Collections.sort(this.assessmentCriterias, new BeanComparator("displayOrder", new NullComparator()));
        }
        return this.assessmentCriterias;
    }

    public Boolean getIsAdditional() {
        return this.isAdditional;
    }

    public void setIsAdditional(Boolean isAdditional) {
        this.isAdditional = isAdditional;
    }
}
