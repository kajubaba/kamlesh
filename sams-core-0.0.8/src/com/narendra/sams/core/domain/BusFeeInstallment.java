package com.narendra.sams.core.domain;

import java.util.Date;
import java.util.Set;

public class BusFeeInstallment {
    public static String SETUP_TYPE_MANUAL = "Manual";
    public static String SETUP_TYPE_PERCENTAGE = "Percentage";
    private AcademicYear academicYear;
    private Set<BusFeeInstallmentDetail> busFeeInstallmentDetails;
    private UserView createdBy;
    private Date createdDate;
    private Long id;
    private Long installmentCount;
    private UserView modifiedBy;
    private Date modifiedDateTime;
    private String setupType;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public Long getInstallmentCount() {
        return this.installmentCount;
    }

    public void setInstallmentCount(Long installmentCount) {
        this.installmentCount = installmentCount;
    }

    public Set<BusFeeInstallmentDetail> getBusFeeInstallmentDetails() {
        return this.busFeeInstallmentDetails;
    }

    public void setBusFeeInstallmentDetails(Set<BusFeeInstallmentDetail> busFeeInstallmentDetails) {
        this.busFeeInstallmentDetails = busFeeInstallmentDetails;
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

    public Date getModifiedDateTime() {
        return this.modifiedDateTime;
    }

    public void setModifiedDateTime(Date modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    public String getSetupType() {
        return this.setupType;
    }

    public void setSetupType(String setupType) {
        this.setupType = setupType;
    }
}
