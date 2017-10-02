package com.narendra.sams.web.restws.fee.vo;

import java.util.List;

public class FeeHeadVO {
    private Long discount = Long.valueOf(0);
    private Long feeDiscountId;
    private Long headFee = Long.valueOf(0);
    private Long headId;
    private String headName;
    private Long installmentTotal = Long.valueOf(0);
    private List<InstallmentFeeVO> installments;
    private Long payable = Long.valueOf(0);

    public Long getInstallmentTotal() {
        if (this.installments != null) {
            for (InstallmentFeeVO installmentFeeVO : this.installments) {
                if (installmentFeeVO.getAmount() != null) {
                    this.installmentTotal = Long.valueOf(this.installmentTotal.longValue() + installmentFeeVO.getAmount().longValue());
                }
            }
        }
        return this.installmentTotal;
    }

    public void setInstallmentTotal(Long installmentTotal) {
        this.installmentTotal = installmentTotal;
    }

    public Long getHeadId() {
        return this.headId;
    }

    public void setHeadId(Long headId) {
        this.headId = headId;
    }

    public String getHeadName() {
        return this.headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public Long getHeadFee() {
        return this.headFee;
    }

    public void setHeadFee(Long headFee) {
        this.headFee = headFee;
    }

    public Long getDiscount() {
        return this.discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Long getPayable() {
        this.payable = Long.valueOf(this.headFee.longValue() - this.discount.longValue());
        return this.payable;
    }

    public List<InstallmentFeeVO> getInstallments() {
        return this.installments;
    }

    public void setInstallments(List<InstallmentFeeVO> installments) {
        this.installments = installments;
    }

    public Long getFeeDiscountId() {
        return this.feeDiscountId;
    }

    public void setFeeDiscountId(Long feeDiscountId) {
        this.feeDiscountId = feeDiscountId;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FeeHeadVO [headId=").append(this.headId).append(", headName=").append(this.headName).append(", headFee=").append(this.headFee).append(", discount=").append(this.discount).append(", payable=").append(this.payable).append(", feeDiscountId=").append(this.feeDiscountId).append(", installments=").append(this.installments).append("]");
        return builder.toString();
    }
}
