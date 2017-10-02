package com.narendra.sams.web.restws.academics.exam.form;

import java.util.List;

public class CoScholasticScoreForm {
    private List<CriteriaScoreForm> criteriaScores;
    private Long studentClassId;
    private Long studentId;
    private Long termId;

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStudentClassId() {
        return this.studentClassId;
    }

    public void setStudentClassId(Long studentClassId) {
        this.studentClassId = studentClassId;
    }

    public Long getTermId() {
        return this.termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    public List<CriteriaScoreForm> getCriteriaScores() {
        return this.criteriaScores;
    }

    public void setCriteriaScores(List<CriteriaScoreForm> criteriaScores) {
        this.criteriaScores = criteriaScores;
    }
}
