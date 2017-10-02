package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.domain.Installment;
import java.io.Serializable;

public class FeeTransactionDetail implements Serializable {
    private static final long serialVersionUID = 894932285639141090L;
    private Long amount;
    private FeeHead feeHead;
    private FeeTransaction feeTransaction;
    private Long id;
    private Installment installment;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FeeTransaction getFeeTransaction() {
        return this.feeTransaction;
    }

    public void setFeeTransaction(FeeTransaction feeTransaction) {
        this.feeTransaction = feeTransaction;
    }

    public Installment getInstallment() {
        return this.installment;
    }

    public void setInstallment(Installment installment) {
        this.installment = installment;
    }

    public FeeHead getFeeHead() {
        return this.feeHead;
    }

    public void setFeeHead(FeeHead feeHead) {
        this.feeHead = feeHead;
    }

    public Long getAmount() {
        return this.amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
