package com.narendra.sams.web.restws.form;

public class InstituteSettingForm {
    private AdmissionSettingsForm admissionSettings;
    private String affiliationNo;
    private EnquirySettingsForm enquirySettings;
    private FeeSettingsForm feeSettings;
    private Long id;
    private String instituteName;
    private Boolean isIdGenerationMethodSame;
    private String line1;
    private String line2;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdmissionSettingsForm getAdmissionSettings() {
        return this.admissionSettings;
    }

    public void setAdmissionSettings(AdmissionSettingsForm admissionSettings) {
        this.admissionSettings = admissionSettings;
    }

    public FeeSettingsForm getFeeSettings() {
        return this.feeSettings;
    }

    public void setFeeSettings(FeeSettingsForm feeSettings) {
        this.feeSettings = feeSettings;
    }

    public EnquirySettingsForm getEnquirySettings() {
        return this.enquirySettings;
    }

    public void setEnquirySettings(EnquirySettingsForm enquirySettings) {
        this.enquirySettings = enquirySettings;
    }

    public String getInstituteName() {
        return this.instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getLine1() {
        return this.line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return this.line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getAffiliationNo() {
        return this.affiliationNo;
    }

    public void setAffiliationNo(String affiliationNo) {
        this.affiliationNo = affiliationNo;
    }

    public Boolean getIsIdGenerationMethodSame() {
        return this.isIdGenerationMethodSame;
    }

    public void setIsIdGenerationMethodSame(Boolean isIdGenerationMethodSame) {
        this.isIdGenerationMethodSame = isIdGenerationMethodSame;
    }
}
