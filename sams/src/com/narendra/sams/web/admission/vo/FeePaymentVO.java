package com.narendra.sams.web.admission.vo;

import java.util.List;

public class FeePaymentVO {
    private Long academicYearFeeInstallmentId;
    private Long custFeeInstallmentId;
    private List<FeePaymentHeadVO> feePaymentHeadVOs;
    private long totalDeposited;
    private long totalFee;

    public List<FeePaymentHeadVO> getFeePaymentHeadVOs() {
        return this.feePaymentHeadVOs;
    }

    public void setFeePaymentHeadVOs(List<FeePaymentHeadVO> feePaymentHeadVOs) {
        this.feePaymentHeadVOs = feePaymentHeadVOs;
    }

    public void sumTotal() {
        if (this.feePaymentHeadVOs != null) {
            for (FeePaymentHeadVO feePaymentHeadVO : this.feePaymentHeadVOs) {
                this.totalFee += feePaymentHeadVO.getAmount();
                this.totalDeposited += feePaymentHeadVO.getDeplositFee();
            }
        }
    }

    public long getTotalFee() {
        return this.totalFee;
    }

    public long getTotalDeposited() {
        return this.totalDeposited;
    }

    public long getTotalDue() {
        return this.totalFee - this.totalDeposited;
    }

    public Long getAcademicYearFeeInstallmentId() {
        return this.academicYearFeeInstallmentId;
    }

    public void setAcademicYearFeeInstallmentId(Long academicYearFeeInstallmentId) {
        this.academicYearFeeInstallmentId = academicYearFeeInstallmentId;
    }

    public Long getCustFeeInstallmentId() {
        return this.custFeeInstallmentId;
    }

    public void setCustFeeInstallmentId(Long custFeeInstallmentId) {
        this.custFeeInstallmentId = custFeeInstallmentId;
    }
}
