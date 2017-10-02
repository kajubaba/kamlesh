package com.narendra.sams.admission.domain;

import java.util.List;

public class StudentDueFee {
    private String academicYear;
    private List<StudentDueInstallment> dueInstallments;
    private String studentClass;
    private String studentId;
    private String studentName;

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

    public String getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getStudentClass() {
        return this.studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public List<StudentDueInstallment> getDueInstallments() {
        return this.dueInstallments;
    }

    public void setDueInstallments(List<StudentDueInstallment> dueInstallments) {
        this.dueInstallments = dueInstallments;
    }
}
