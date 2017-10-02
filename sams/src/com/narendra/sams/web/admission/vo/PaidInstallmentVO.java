package com.narendra.sams.web.admission.vo;

import java.util.List;

public class PaidInstallmentVO {
    private Short installmentId;
    private List<FeePaymentHeadVO> paidFees;

    public Short getInstallmentId() {
        return this.installmentId;
    }

    public void setInstallmentId(Short installmentId) {
        this.installmentId = installmentId;
    }

    public List<FeePaymentHeadVO> getPaidFees() {
        return this.paidFees;
    }

    public void setPaidFees(List<FeePaymentHeadVO> paidFees) {
        this.paidFees = paidFees;
    }
}
