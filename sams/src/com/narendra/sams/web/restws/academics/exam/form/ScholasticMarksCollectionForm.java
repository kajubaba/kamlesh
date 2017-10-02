package com.narendra.sams.web.restws.academics.exam.form;

import com.narendra.sams.academics.exam.domain.StudentScore;
import java.util.List;

public class ScholasticMarksCollectionForm {
    private List<StudentScore> studentScores;

    public List<StudentScore> getStudentScores() {
        return this.studentScores;
    }

    public void setStudentScores(List<StudentScore> studentScores) {
        this.studentScores = studentScores;
    }
}
