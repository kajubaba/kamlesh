package com.narendra.sams.academics.exam.domain;

public class StudentScore {
    private String fatherName;
    private Float marksObtained;
    private Long scoreId;
    private Long studentDBId;
    private String studentGender;
    private String studentId;
    private String studentName;
    private String studentSection;

    public Long getScoreId() {
        return this.scoreId;
    }

    public void setScoreId(Long scoreId) {
        this.scoreId = scoreId;
    }

    public Long getStudentDBId() {
        return this.studentDBId;
    }

    public void setStudentDBId(Long studentDBId) {
        this.studentDBId = studentDBId;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSection() {
        return this.studentSection;
    }

    public void setStudentSection(String studentSection) {
        this.studentSection = studentSection;
    }

    public String getStudentGender() {
        return this.studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public Float getMarksObtained() {
        return this.marksObtained;
    }

    public void setMarksObtained(Float marksObtained) {
        this.marksObtained = marksObtained;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
}
