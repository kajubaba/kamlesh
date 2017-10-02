package com.narendra.sams.academics.exam.domain.scorecard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreCardTerm {
    private Map<Long, ScoreCardAssessment> assessmentMap = new HashMap();
    private List<ScoreCardAssessment> assessments;
    private String termName;

    public List<ScoreCardAssessment> getAssessments() {
        this.assessments = new ArrayList(this.assessmentMap.values());
        return this.assessments;
    }

    public void setAssessments(List<ScoreCardAssessment> assessments) {
        this.assessments = assessments;
    }

    public String getTermName() {
        return this.termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public Map<Long, ScoreCardAssessment> getAssessmentMap() {
        return this.assessmentMap;
    }

    public void setAssessmentMap(Map<Long, ScoreCardAssessment> assessmentMap) {
        this.assessmentMap = assessmentMap;
    }
}
