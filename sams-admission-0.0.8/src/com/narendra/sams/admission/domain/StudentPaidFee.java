package com.narendra.sams.admission.domain;

public class StudentPaidFee {
    private Long amountPaid;
    private String className;
    private String fatherName;
    private String studentAssignedId;
    private Long studentId;
    private String studentName;

    public StudentPaidFee(Long studentId, String studentAssignedId, String studentName, String className, Long amountPaid, String fatherName) {
        this.studentId = studentId;
        this.studentAssignedId = studentAssignedId;
        this.studentName = studentName;
        this.fatherName = fatherName;
        this.className = className;
        this.amountPaid = amountPaid;
    }

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

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getAmountPaid() {
        return this.amountPaid;
    }

    public void setAmountPaid(Long amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
}
