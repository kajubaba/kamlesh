package com.narendra.sams.academics.exam.domain;

public class StudentCoScholasticAssessmentStatus {
    private Long percent;
    private String studentAssignedId;
    private String studentFatherName;
    private String studentGender;
    private Long studentId;
    private String studentName;

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentAssignedId() {
        return this.studentAssignedId;
    }

    public void setStudentAssignedId(String studentAssignedId) {
        this.studentAssignedId = studentAssignedId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGender() {
        return this.studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentFatherName() {
        return this.studentFatherName;
    }

    public void setStudentFatherName(String studentFatherName) {
        this.studentFatherName = studentFatherName;
    }

    public Long getPercent() {
        return this.percent;
    }

    public void setPercent(Long percent) {
        this.percent = percent;
    }
}
