package com.narendra.sams.core.domain;

import java.util.Date;
import java.util.Set;

public class AcademicYearFeeInstallment {
    private AcademicYearFee academicYearFee;
    private Set<AcademicYearFeeInstallmentDetail> academicYearFeeInstallmentDetails;
    private Date dueDate;
    private Long id;
    private Installment installment;
    private LateFeeRule lateFeeRule;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Installment getInstallment() {
        return this.installment;
    }

    public void setInstallment(Installment installment) {
        this.installment = installment;
    }

    public AcademicYearFee getAcademicYearFee() {
        return this.academicYearFee;
    }

    public void setAcademicYearFee(AcademicYearFee academicYearFee) {
        this.academicYearFee = academicYearFee;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Set<AcademicYearFeeInstallmentDetail> getAcademicYearFeeInstallmentDetails() {
        return this.academicYearFeeInstallmentDetails;
    }

    public void setAcademicYearFeeInstallmentDetails(Set<AcademicYearFeeInstallmentDetail> academicYearFeeInstallmentDetails) {
        this.academicYearFeeInstallmentDetails = academicYearFeeInstallmentDetails;
    }

    public long getTotal() {
        long sum = 0;
        if (this.academicYearFeeInstallmentDetails == null || this.academicYearFeeInstallmentDetails.isEmpty()) {
            return 0;
        }
        for (AcademicYearFeeInstallmentDetail academicYearFeeInstallmentDetail : this.academicYearFeeInstallmentDetails) {
            if (academicYearFeeInstallmentDetail.getAmount() != null) {
                sum += academicYearFeeInstallmentDetail.getAmount().longValue();
            }
        }
        return sum;
    }

    public LateFeeRule getLateFeeRule() {
        return this.lateFeeRule;
    }

    public void setLateFeeRule(LateFeeRule lateFeeRule) {
        this.lateFeeRule = lateFeeRule;
    }
}
