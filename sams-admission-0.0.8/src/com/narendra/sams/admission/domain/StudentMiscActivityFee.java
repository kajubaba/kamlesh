package com.narendra.sams.admission.domain;

public class StudentMiscActivityFee {
    private Long paidFee;
    private String studentId;

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Long getPaidFee() {
        return this.paidFee;
    }

    public void setPaidFee(Long paidFee) {
        this.paidFee = paidFee;
    }
}
