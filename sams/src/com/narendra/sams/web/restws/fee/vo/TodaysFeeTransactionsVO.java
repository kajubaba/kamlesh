package com.narendra.sams.web.restws.fee.vo;

import java.util.List;

public class TodaysFeeTransactionsVO {
    private String endDate;
    private List<FeeTransactionVO> feeTransactions;
    private String startDate;

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<FeeTransactionVO> getFeeTransactions() {
        return this.feeTransactions;
    }

    public void setFeeTransactions(List<FeeTransactionVO> feeTransactions) {
        this.feeTransactions = feeTransactions;
    }
}
