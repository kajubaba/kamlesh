package com.narendra.sams.admission.domain;

import com.narendra.sams.admission.utils.LateFeeCalculator;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearFeeInstallment;
import com.narendra.sams.core.domain.Installment;
import com.narendra.sams.core.domain.UserView;
import java.util.Date;

public class DaysOverdue {
    private AcademicYearFeeInstallment academicYearFeeInstallment;
    private Boolean calculate;
    private String comments;
    private CustomizeInstallment customizeInstallment;
    private int daysOverdue;
    private Integer discount;
    private AcademicYear feeAcademicYear;
    private Long id;
    private Installment installment;
    private UserView modifiedBy;
    private Date modifiedDateTime;
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

    public AcademicYear getFeeAcademicYear() {
        return this.feeAcademicYear;
    }

    public void setFeeAcademicYear(AcademicYear feeAcademicYear) {
        this.feeAcademicYear = feeAcademicYear;
    }

    public Installment getInstallment() {
        return this.installment;
    }

    public void setInstallment(Installment installment) {
        this.installment = installment;
    }

    public int getDaysOverdue() {
        return this.daysOverdue;
    }

    public void setDaysOverdue(int daysOverdue) {
        this.daysOverdue = daysOverdue;
    }

    public Integer getDiscount() {
        return this.discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Boolean getCalculate() {
        return this.calculate;
    }

    public void setCalculate(Boolean calculate) {
        this.calculate = calculate;
    }

    public long getLateFee() {
        if (this.customizeInstallment != null) {
            return LateFeeCalculator.calculateLateFee((long) this.daysOverdue, this.customizeInstallment.getLateFeeRule());
        }
        return LateFeeCalculator.calculateLateFee((long) this.daysOverdue, this.academicYearFeeInstallment.getLateFeeRule());
    }

    public UserView getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(UserView modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDateTime() {
        return this.modifiedDateTime;
    }

    public void setModifiedDateTime(Date modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public AcademicYearFeeInstallment getAcademicYearFeeInstallment() {
        return this.academicYearFeeInstallment;
    }

    public void setAcademicYearFeeInstallment(AcademicYearFeeInstallment academicYearFeeInstallment) {
        this.academicYearFeeInstallment = academicYearFeeInstallment;
    }

    public CustomizeInstallment getCustomizeInstallment() {
        return this.customizeInstallment;
    }

    public void setCustomizeInstallment(CustomizeInstallment customizeInstallment) {
        this.customizeInstallment = customizeInstallment;
    }
}
