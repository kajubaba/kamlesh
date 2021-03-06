package com.narendra.sams.web.restws.fee.vo;

public class HeadwisePaidFeeVO {
    private Long academicYearId;
    private String academicYearName;
    private Long feeHeadId;
    private String feeHeadName;
    private Long paidFee;

    public Long getFeeHeadId() {
        return this.feeHeadId;
    }

    public void setFeeHeadId(Long feeHeadId) {
        this.feeHeadId = feeHeadId;
    }

    public String getFeeHeadName() {
        return this.feeHeadName;
    }

    public void setFeeHeadName(String feeHeadName) {
        this.feeHeadName = feeHeadName;
    }

    public Long getPaidFee() {
        return this.paidFee;
    }

    public void setPaidFee(Long paidFee) {
        this.paidFee = paidFee;
    }

    public String getAcademicYearName() {
        return this.academicYearName;
    }

    public void setAcademicYearName(String academicYearName) {
        this.academicYearName = academicYearName;
    }

    public Long getAcademicYearId() {
        return this.academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }
}
