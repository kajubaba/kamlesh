package com.narendra.sams.web.restws.vo;

import java.util.List;

public class AcademicSessionAdmissionSchemeVO {
    private List<AdmissionSchemeDetailVO> admissionSchemeDetails;
    private Long admissionSchemeId;
    private String admissionSchemeName;
    private String assignedBy;
    private String assignedOn;
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdmissionSchemeId() {
        return this.admissionSchemeId;
    }

    public void setAdmissionSchemeId(Long admissionSchemeId) {
        this.admissionSchemeId = admissionSchemeId;
    }

    public String getAdmissionSchemeName() {
        return this.admissionSchemeName;
    }

    public void setAdmissionSchemeName(String admissionSchemeName) {
        this.admissionSchemeName = admissionSchemeName;
    }

    public String getAssignedBy() {
        return this.assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public String getAssignedOn() {
        return this.assignedOn;
    }

    public void setAssignedOn(String assignedOn) {
        this.assignedOn = assignedOn;
    }

    public List<AdmissionSchemeDetailVO> getAdmissionSchemeDetails() {
        return this.admissionSchemeDetails;
    }

    public void setAdmissionSchemeDetails(List<AdmissionSchemeDetailVO> admissionSchemeDetails) {
        this.admissionSchemeDetails = admissionSchemeDetails;
    }
}
