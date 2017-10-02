package com.narendra.sams.web.restws.academics.exam.vo;

public class StudentRollNoVO {
    private String fatherName;
    private Long id;
    private String rollNo;
    private Long studentDBId;
    private String studentGender;
    private String studentId;
    private String studentName;
    private String studentSection;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
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

    public String getRollNo() {
        return this.rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
}
