package com.narendra.sams.web.utils;

import java.util.List;

public class DueFeeNoticeModel {
    private String academicYear;
    private String comments;
    private List<DueFeeNoticeInstallmentDetail> dueInstallments;
    private String header;
    private String noticeDate;
    private String studentClass;
    private String studentId;
    private String studentName;
    private String subHeader;

    public String getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSubHeader() {
        return this.subHeader;
    }

    public void setSubHeader(String subHeader) {
        this.subHeader = subHeader;
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

    public String getStudentClass() {
        return this.studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public List<DueFeeNoticeInstallmentDetail> getDueInstallments() {
        return this.dueInstallments;
    }

    public void setDueInstallments(List<DueFeeNoticeInstallmentDetail> dueInstallments) {
        this.dueInstallments = dueInstallments;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getNoticeDate() {
        return this.noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }
}
