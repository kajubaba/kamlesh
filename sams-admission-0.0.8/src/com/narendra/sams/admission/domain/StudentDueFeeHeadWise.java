package com.narendra.sams.admission.domain;

import java.util.List;

public class StudentDueFeeHeadWise {
    private String academicYear;
    private List<HeadWiseDueFee> headWiseDueFees;
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

    public List<HeadWiseDueFee> getHeadWiseDueFees() {
        return this.headWiseDueFees;
    }

    public void setHeadWiseDueFees(List<HeadWiseDueFee> headWiseDueFees) {
        this.headWiseDueFees = headWiseDueFees;
    }
}
