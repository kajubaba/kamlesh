package com.narendra.sams.web.restws.fee.vo;

import java.util.List;

public class DueFeeStudentsOfClassVO {
    private String academicYear;
    private String admissionStatus;
    private String className;
    private String dueDate;
    private long paidFeeSum;
    private long payableFeeSum;
    private List<StudentDueFeeVO> students;
    private long unpaidFeeSum;

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public List<StudentDueFeeVO> getStudents() {
        return this.students;
    }

    public void setStudents(List<StudentDueFeeVO> students) {
        this.students = students;
    }

    public String getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public long getPayableFeeSum() {
        return this.payableFeeSum;
    }

    public void setPayableFeeSum(long payableFeeSum) {
        this.payableFeeSum = payableFeeSum;
    }

    public long getPaidFeeSum() {
        return this.paidFeeSum;
    }

    public void setPaidFeeSum(long paidFeeSum) {
        this.paidFeeSum = paidFeeSum;
    }

    public long getUnpaidFeeSum() {
        return this.unpaidFeeSum;
    }

    public void setUnpaidFeeSum(long unpaidFeeSum) {
        this.unpaidFeeSum = unpaidFeeSum;
    }

    public String getAdmissionStatus() {
        return this.admissionStatus;
    }

    public void setAdmissionStatus(String admissionStatus) {
        this.admissionStatus = admissionStatus;
    }
}
