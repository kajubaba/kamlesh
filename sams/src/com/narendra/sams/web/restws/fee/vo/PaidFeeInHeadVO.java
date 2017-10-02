package com.narendra.sams.web.restws.fee.vo;

import com.narendra.sams.admission.domain.DateWisePaidFee;
import java.util.List;

public class PaidFeeInHeadVO {
    private String headName;
    private List<DateWisePaidFee> paidFees;

    public String getHeadName() {
        return this.headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public List<DateWisePaidFee> getPaidFees() {
        return this.paidFees;
    }

    public void setPaidFees(List<DateWisePaidFee> paidFees) {
        this.paidFees = paidFees;
    }
}
