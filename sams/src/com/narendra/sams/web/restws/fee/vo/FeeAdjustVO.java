package com.narendra.sams.web.restws.fee.vo;

import java.util.ArrayList;
import java.util.List;

public class FeeAdjustVO {
    private Long academicYearClassId;
    private Long academicYearFeeId;
    private String comments;
    private Long commentsId;
    private Boolean customized;
    private List<FeeHeadVO> feeHeads;
    private List<InstallmentDueDateVO> installmentDueDates = new ArrayList();
    private List<LateFeeRuleVO> lateFeeRuleVOs = new ArrayList();
    private int prevInstallmentCount = 0;
    private Long studentId;

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public List<FeeHeadVO> getFeeHeads() {
        return this.feeHeads;
    }

    public void setFeeHeads(List<FeeHeadVO> feeHeads) {
        this.feeHeads = feeHeads;
    }

    public Long getAcademicYearClassId() {
        return this.academicYearClassId;
    }

    public void setAcademicYearClassId(Long academicYearClassId) {
        this.academicYearClassId = academicYearClassId;
    }

    public Boolean getCustomized() {
        return this.customized;
    }

    public void setCustomized(Boolean customized) {
        this.customized = customized;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CustomizeFeeVO [studentId=").append(this.studentId).append(", academicYearClassId=").append(this.academicYearClassId).append(", feeHeads=").append(this.feeHeads).append("]");
        return builder.toString();
    }

    public Long getCommentsId() {
        return this.commentsId;
    }

    public void setCommentsId(Long commentsId) {
        this.commentsId = commentsId;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getAcademicYearFeeId() {
        return this.academicYearFeeId;
    }

    public void setAcademicYearFeeId(Long academicYearFeeId) {
        this.academicYearFeeId = academicYearFeeId;
    }

    public List<InstallmentDueDateVO> getInstallmentDueDates() {
        return this.installmentDueDates;
    }

    public void setInstallmentDueDates(List<InstallmentDueDateVO> installmentDueDates) {
        this.installmentDueDates = installmentDueDates;
    }

    public List<LateFeeRuleVO> getLateFeeRuleVOs() {
        return this.lateFeeRuleVOs;
    }

    public void setLateFeeRuleVOs(List<LateFeeRuleVO> lateFeeRuleVOs) {
        this.lateFeeRuleVOs = lateFeeRuleVOs;
    }

    public int getPrevInstallmentCount() {
        return this.prevInstallmentCount;
    }

    public void setPrevInstallmentCount(int prevInstallmentCount) {
        this.prevInstallmentCount = prevInstallmentCount;
    }
}
