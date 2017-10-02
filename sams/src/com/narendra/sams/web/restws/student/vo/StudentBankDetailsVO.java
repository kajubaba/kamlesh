package com.narendra.sams.web.restws.student.vo;

public class StudentBankDetailsVO {
    private String acNo;
    private Long bankDetailId;
    private Long bankId;
    private String bankName;
    private String branchName;
    private String ifsc;
    private Long studentId;

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getBankId() {
        return this.bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIfsc() {
        return this.ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getAcNo() {
        return this.acNo;
    }

    public void setAcNo(String acNo) {
        this.acNo = acNo;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Long getBankDetailId() {
        return this.bankDetailId;
    }

    public void setBankDetailId(Long bankDetailId) {
        this.bankDetailId = bankDetailId;
    }
}
