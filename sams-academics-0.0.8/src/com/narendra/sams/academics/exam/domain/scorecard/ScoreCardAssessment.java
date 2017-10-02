package com.narendra.sams.academics.exam.domain.scorecard;

public class ScoreCardAssessment {
    private String grade;
    private String name;
    private Integer subjectMarksObtained;

    public Integer getSubjectMarksObtained() {
        return this.subjectMarksObtained;
    }

    public void setSubjectMarksObtained(Integer subjectMarksObtained) {
        this.subjectMarksObtained = subjectMarksObtained;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
