package com.narendra.sams.admission.domain;

public class CourseDiscount {
    private Long discount;
    private String studentFirstName;
    private String studentId;
    private String studentLastName;
    private Long totalFee;

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return this.studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return this.studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public Long getTotalFee() {
        if (this.totalFee == null) {
            return Long.valueOf(0);
        }
        return this.totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Long getDiscount() {
        if (this.discount == null) {
            return Long.valueOf(0);
        }
        return this.discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }
}
