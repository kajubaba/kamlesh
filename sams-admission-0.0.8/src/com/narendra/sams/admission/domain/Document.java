package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.UserView;
import java.util.Date;

public class Document {
    private AcademicYear academicYear;
    private AdmissionType admissionType;
    private String docCategory;
    private Long id;
    private Date lastModifiedOn;
    private UserView lastUpdatedBy;
    private Boolean mandatory;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocCategory() {
        return this.docCategory;
    }

    public void setDocCategory(String docCategory) {
        this.docCategory = docCategory;
    }

    public Boolean getMandatory() {
        return this.mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public AdmissionType getAdmissionType() {
        return this.admissionType;
    }

    public void setAdmissionType(AdmissionType admissionType) {
        this.admissionType = admissionType;
    }

    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public UserView getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public void setLastUpdatedBy(UserView lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastModifiedOn() {
        return this.lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public int hashCode() {
        return (this.id == null ? 0 : this.id.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Document)) {
            return false;
        }
        Document other = (Document) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
            return true;
        } else if (this.id.equals(other.id)) {
            return true;
        } else {
            return false;
        }
    }
}
