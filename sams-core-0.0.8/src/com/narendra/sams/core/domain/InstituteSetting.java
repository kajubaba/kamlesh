package com.narendra.sams.core.domain;

import java.util.Date;

public class InstituteSetting {
    private AdmissionSettings admissionSettings;
    private EnquirySettings enquirySettings;
    private FeeSettings feeSettings;
    private Long id;
    private Institute institute;
    private Boolean isIdGenerationMethodSame;
    private UserView modifiedBy;
    private Date modifiedDate;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public EnquirySettings getEnquirySettings() {
        return this.enquirySettings;
    }

    public void setEnquirySettings(EnquirySettings enquirySettings) {
        this.enquirySettings = enquirySettings;
    }

    public AdmissionSettings getAdmissionSettings() {
        return this.admissionSettings;
    }

    public void setAdmissionSettings(AdmissionSettings admissionSettings) {
        this.admissionSettings = admissionSettings;
    }

    public FeeSettings getFeeSettings() {
        return this.feeSettings;
    }

    public void setFeeSettings(FeeSettings feeSettings) {
        this.feeSettings = feeSettings;
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

    public Boolean getIsIdGenerationMethodSame() {
        return this.isIdGenerationMethodSame;
    }

    public void setIsIdGenerationMethodSame(Boolean isIdGenerationMethodSame) {
        this.isIdGenerationMethodSame = isIdGenerationMethodSame;
    }
}
