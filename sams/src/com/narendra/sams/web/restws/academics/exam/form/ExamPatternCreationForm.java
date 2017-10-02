package com.narendra.sams.web.restws.academics.exam.form;

public class ExamPatternCreationForm {
    private Long academicSessionId;
    private Long baseExamPatternId;
    private String examPatternName;

    public Long getBaseExamPatternId() {
        return this.baseExamPatternId;
    }

    public void setBaseExamPatternId(Long baseExamPatternId) {
        this.baseExamPatternId = baseExamPatternId;
    }

    public String getExamPatternName() {
        return this.examPatternName;
    }

    public void setExamPatternName(String examPatternName) {
        this.examPatternName = examPatternName;
    }

    public Long getAcademicSessionId() {
        return this.academicSessionId;
    }

    public void setAcademicSessionId(Long academicSessionId) {
        this.academicSessionId = academicSessionId;
    }
}
