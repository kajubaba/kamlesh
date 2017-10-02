package com.narendra.sams.web.restws.vo;

public class InstituteSettingVO {
    private AdmissionSettingsVO admissionSettings;
    private String affiliationNo;
    private EnquirySettingsVO enquirySettings;
    private FeeSettingsVO feeSettings;
    private Long id;
    private String instituteName;
    private String isIdGenerationMethodSame;
    private String line1;
    private String line2;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdmissionSettingsVO getAdmissionSettings() {
        return this.admissionSettings;
    }

    public void setAdmissionSettings(AdmissionSettingsVO admissionSettings) {
        this.admissionSettings = admissionSettings;
    }

    public FeeSettingsVO getFeeSettings() {
        return this.feeSettings;
    }

    public void setFeeSettings(FeeSettingsVO feeSettings) {
        this.feeSettings = feeSettings;
    }

    public EnquirySettingsVO getEnquirySettings() {
        return this.enquirySettings;
    }

    public void setEnquirySettings(EnquirySettingsVO enquirySettings) {
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

    public String getIsIdGenerationMethodSame() {
        return this.isIdGenerationMethodSame;
    }

    public void setIsIdGenerationMethodSame(String isIdGenerationMethodSame) {
        this.isIdGenerationMethodSame = isIdGenerationMethodSame;
    }
}
