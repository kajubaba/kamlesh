package com.narendra.sams.academics.exam.domain.scorecard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScorecardSubject {
    private String subjectName;
    private Map<Long, ScoreCardTerm> termMap;
    private List<ScoreCardTerm> terms;

    public String getSubjectName() {
        return this.subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<ScoreCardTerm> getTerms() {
        this.terms = new ArrayList(this.termMap.values());
        return this.terms;
    }

    public void setTerms(List<ScoreCardTerm> terms) {
        this.terms = terms;
    }

    public Map<Long, ScoreCardTerm> getTermMap() {
        return this.termMap;
    }

    public void setTermMap(Map<Long, ScoreCardTerm> termMap) {
        this.termMap = termMap;
    }
}
