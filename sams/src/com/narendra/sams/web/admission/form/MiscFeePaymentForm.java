package com.narendra.sams.web.admission.form;

import java.util.List;

public class MiscFeePaymentForm {
    private String comments;
    private Long miscActivityCourseYearId;
    private List<MiscFeePaymentFormDetail> miscFeePaymentFormDetails;
    private Long receiptHeaderId;
    private Long studentClassId;
    private Long studentId;

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStudentClassId() {
        return this.studentClassId;
    }

    public void setStudentClassId(Long studentClassId) {
        this.studentClassId = studentClassId;
    }

    public Long getMiscActivityCourseYearId() {
        return this.miscActivityCourseYearId;
    }

    public void setMiscActivityCourseYearId(Long miscActivityCourseYearId) {
        this.miscActivityCourseYearId = miscActivityCourseYearId;
    }

    public List<MiscFeePaymentFormDetail> getMiscFeePaymentFormDetails() {
        return this.miscFeePaymentFormDetails;
    }

    public void setMiscFeePaymentFormDetails(List<MiscFeePaymentFormDetail> miscFeePaymentFormDetails) {
        this.miscFeePaymentFormDetails = miscFeePaymentFormDetails;
    }

    public Long getReceiptHeaderId() {
        return this.receiptHeaderId;
    }

    public void setReceiptHeaderId(Long receiptHeaderId) {
        this.receiptHeaderId = receiptHeaderId;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
