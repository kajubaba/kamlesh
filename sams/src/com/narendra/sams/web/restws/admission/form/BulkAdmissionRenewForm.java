package com.narendra.sams.web.restws.admission.form;

import java.util.Collection;

public class BulkAdmissionRenewForm {
    private Long academicYearClassId;
    private Long affiliationAuthorityId;
    private Boolean copyAdmissionScheme;
    private Boolean copyBusStop;
    private Collection<Long> studentIds;

    public Long getAffiliationAuthorityId() {
        return this.affiliationAuthorityId;
    }

    public void setAffiliationAuthorityId(Long affiliationAuthorityId) {
        this.affiliationAuthorityId = affiliationAuthorityId;
    }

    public Long getAcademicYearClassId() {
        return this.academicYearClassId;
    }

    public void setAcademicYearClassId(Long academicYearClassId) {
        this.academicYearClassId = academicYearClassId;
    }

    public Boolean getCopyBusStop() {
        return this.copyBusStop;
    }

    public void setCopyBusStop(Boolean copyBusStop) {
        this.copyBusStop = copyBusStop;
    }

    public Boolean getCopyAdmissionScheme() {
        return this.copyAdmissionScheme;
    }

    public void setCopyAdmissionScheme(Boolean copyAdmissionScheme) {
        this.copyAdmissionScheme = copyAdmissionScheme;
    }

    public Collection<Long> getStudentIds() {
        return this.studentIds;
    }

    public void setStudentIds(Collection<Long> studentIds) {
        this.studentIds = studentIds;
    }
}
