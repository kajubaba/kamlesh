package com.narendra.sams.academics.exam.domain.scorecard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentScholasticAssessmentScoreCard {
    private Map<Long, ScorecardSubject> subjectMap = new HashMap();
    private List<ScorecardSubject> subjects;
    private Map<Long, ScoreCardTerm> termMap = new HashMap();
    private List<ScoreCardTerm> terms;

    public List<ScorecardSubject> getSubjects() {
        this.subjects = new ArrayList(this.subjectMap.values());
        return this.subjects;
    }

    public void setSubjects(List<ScorecardSubject> subjects) {
        this.subjects = subjects;
    }

    public List<ScoreCardTerm> getTerms() {
        this.terms = new ArrayList(this.termMap.values());
        return this.terms;
    }

    public void setTerms(List<ScoreCardTerm> terms) {
        this.terms = terms;
    }

    public Map<Long, ScorecardSubject> getSubjectMap() {
        return this.subjectMap;
    }

    public void setSubjectMap(Map<Long, ScorecardSubject> subjectMap) {
        this.subjectMap = subjectMap;
    }

    public Map<Long, ScoreCardTerm> getTermMap() {
        return this.termMap;
    }

    public void setTermMap(Map<Long, ScoreCardTerm> termMap) {
        this.termMap = termMap;
    }
}
