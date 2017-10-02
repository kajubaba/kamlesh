package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.Installment;
import com.narendra.sams.core.domain.LateFeeRule;
import com.narendra.sams.core.domain.UserView;
import java.util.Date;
import java.util.Set;

public class CustomizeInstallment {
    private AcademicYearClass academicYearClass;
    private AcademicYearFee academicYearFee;
    private UserView createdBy;
    private Date createdDate;
    private Set<CustomizeInstallmentDetail> customizeInstallmentDetails;
    private Set<DaysOverdue> daysOverdues;
    private Date dueDate;
    private Set<FeeTransaction> feeTransactions;
    private Long id;
    private Installment installment;
    private Boolean isFeePaid;
    private LateFeeRule lateFeeRule;
    private UserView modifiedBy;
    private Date modifiedDate;
    private Student student;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public AcademicYearClass getAcademicYearClass() {
        return this.academicYearClass;
    }

    public void setAcademicYearClass(AcademicYearClass academicYearClass) {
        this.academicYearClass = academicYearClass;
    }

    public Installment getInstallment() {
        return this.installment;
    }

    public void setInstallment(Installment installment) {
        this.installment = installment;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Set<CustomizeInstallmentDetail> getCustomizeInstallmentDetails() {
        return this.customizeInstallmentDetails;
    }

    public void setCustomizeInstallmentDetails(Set<CustomizeInstallmentDetail> customizeInstallmentDetails) {
        this.customizeInstallmentDetails = customizeInstallmentDetails;
    }

    public long getTotalFee() {
        long sum = 0;
        if (this.customizeInstallmentDetails != null) {
            for (CustomizeInstallmentDetail customizeInstallmentDetail : this.customizeInstallmentDetails) {
                if (!(customizeInstallmentDetail.getAmount() == null || "Bus Fee".equalsIgnoreCase(customizeInstallmentDetail.getFeeHead().getName()))) {
                    sum += customizeInstallmentDetail.getAmount().longValue();
                }
            }
        }
        return sum;
    }

    public long getTotalBusFee() {
        long sum = 0;
        if (this.customizeInstallmentDetails != null) {
            for (CustomizeInstallmentDetail customizeInstallmentDetail : this.customizeInstallmentDetails) {
                if (customizeInstallmentDetail.getAmount() != null && "Bus Fee".equalsIgnoreCase(customizeInstallmentDetail.getFeeHead().getName())) {
                    sum += customizeInstallmentDetail.getAmount().longValue();
                }
            }
        }
        return sum;
    }

    public UserView getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(UserView createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public UserView getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(UserView modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Boolean getIsFeePaid() {
        return this.isFeePaid;
    }

    public void setIsFeePaid(Boolean isFeePaid) {
        this.isFeePaid = isFeePaid;
    }

    public AcademicYearFee getAcademicYearFee() {
        return this.academicYearFee;
    }

    public void setAcademicYearFee(AcademicYearFee academicYearFee) {
        this.academicYearFee = academicYearFee;
    }

    public LateFeeRule getLateFeeRule() {
        return this.lateFeeRule;
    }

    public void setLateFeeRule(LateFeeRule lateFeeRule) {
        this.lateFeeRule = lateFeeRule;
    }

    public Set<DaysOverdue> getDaysOverdues() {
        return this.daysOverdues;
    }

    public void setDaysOverdues(Set<DaysOverdue> daysOverdues) {
        this.daysOverdues = daysOverdues;
    }

    public Set<FeeTransaction> getFeeTransactions() {
        return this.feeTransactions;
    }

    public void setFeeTransactions(Set<FeeTransaction> feeTransactions) {
        this.feeTransactions = feeTransactions;
    }
}
