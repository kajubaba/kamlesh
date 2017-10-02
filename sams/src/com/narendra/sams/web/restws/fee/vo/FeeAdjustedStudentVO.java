package com.narendra.sams.web.restws.fee.vo;

public class FeeAdjustedStudentVO {
    private String adjustedBy;
    private String adjustedOn;
    private String adjustmentComments;
    private Long discountGiven;
    private String feeAdjustedClass;
    private Long feeAdjustedClassId;
    private Long id;
    private Long noOfInstallments;
    private String schemeName;
    private String studentId;
    private String studentName;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFeeAdjustedClass() {
        return this.feeAdjustedClass;
    }

    public void setFeeAdjustedClass(String feeAdjustedClass) {
        this.feeAdjustedClass = feeAdjustedClass;
    }

    public Long getNoOfInstallments() {
        return this.noOfInstallments;
    }

    public void setNoOfInstallments(Long noOfInstallments) {
        this.noOfInstallments = noOfInstallments;
    }

    public Long getFeeAdjustedClassId() {
        return this.feeAdjustedClassId;
    }

    public void setFeeAdjustedClassId(Long feeAdjustedClassId) {
        this.feeAdjustedClassId = feeAdjustedClassId;
    }

    public String getSchemeName() {
        return this.schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getAdjustedBy() {
        return this.adjustedBy;
    }

    public void setAdjustedBy(String adjustedBy) {
        this.adjustedBy = adjustedBy;
    }

    public String getAdjustedOn() {
        return this.adjustedOn;
    }

    public void setAdjustedOn(String adjustedOn) {
        this.adjustedOn = adjustedOn;
    }

    public String getAdjustmentComments() {
        return this.adjustmentComments;
    }

    public void setAdjustmentComments(String adjustmentComments) {
        this.adjustmentComments = adjustmentComments;
    }

    public Long getDiscountGiven() {
        return this.discountGiven;
    }

    public void setDiscountGiven(Long discountGiven) {
        this.discountGiven = discountGiven;
    }
}
