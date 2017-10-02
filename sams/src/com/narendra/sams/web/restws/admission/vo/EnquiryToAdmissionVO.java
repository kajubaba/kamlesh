package com.narendra.sams.web.restws.admission.vo;

import com.narendra.sams.web.restws.admission.form.Admission;

public class EnquiryToAdmissionVO {
    private Admission admission;
    private Boolean isAlreadyAdmissionTaken;
    private Boolean isEnquiryExists;

    public Boolean getIsEnquiryExists() {
        return this.isEnquiryExists;
    }

    public void setIsEnquiryExists(Boolean isEnquiryExists) {
        this.isEnquiryExists = isEnquiryExists;
    }

    public Boolean getIsAlreadyAdmissionTaken() {
        return this.isAlreadyAdmissionTaken;
    }

    public void setIsAlreadyAdmissionTaken(Boolean isAlreadyAdmissionTaken) {
        this.isAlreadyAdmissionTaken = isAlreadyAdmissionTaken;
    }

    public Admission getAdmission() {
        return this.admission;
    }

    public void setAdmission(Admission admission) {
        this.admission = admission;
    }
}
