package com.narendra.sams.web.restws.academics.exam.vo;

import com.narendra.sams.academics.exam.domain.StudentScore;
import java.util.List;

public class ScholasticScoreCollectionVO {
    private Long maxMarks;
    private List<StudentScore> studentScores;

    public Long getMaxMarks() {
        return this.maxMarks;
    }

    public void setMaxMarks(Long maxMarks) {
        this.maxMarks = maxMarks;
    }

    public List<StudentScore> getStudentScores() {
        return this.studentScores;
    }

    public void setStudentScores(List<StudentScore> studentScores) {
        this.studentScores = studentScores;
    }
}
