package com.narendra.sams.core.domain;

import java.util.Date;
import java.util.Set;

public class AcademicYearAdmissionScheme {
    private AcademicYear academicYear;
    private AdmissionScheme admissionScheme;
    private UserView createdBy;
    private Date createdDateTime;
    private Long id;
    private UserView modifiedBy;
    private Date modifiedDateTime;
    private Set<AcademicSessionAdmisionSchemeDetail> schemeDetails;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdmissionScheme getAdmissionScheme() {
        return this.admissionScheme;
    }

    public void setAdmissionScheme(AdmissionScheme admissionScheme) {
        this.admissionScheme = admissionScheme;
    }

    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public UserView getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(UserView createdBy) {
        this.createdBy = createdBy;
    }

    public UserView getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(UserView modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedDateTime() {
        return this.createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Date getModifiedDateTime() {
        return this.modifiedDateTime;
    }

    public void setModifiedDateTime(Date modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    public Set<AcademicSessionAdmisionSchemeDetail> getSchemeDetails() {
        return this.schemeDetails;
    }

    public void setSchemeDetails(Set<AcademicSessionAdmisionSchemeDetail> schemeDetails) {
        this.schemeDetails = schemeDetails;
    }
}
