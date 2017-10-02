package com.narendra.sams.web.restws.admission.form;

public class RenewAdmissionForm {
    private Long academicYearClassId;
    private Long admissionSchemeId;
    private Long affiliationAuthorityId;
    private Long busStopId;
    private String formNo;
    private Long studentId;

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getBusStopId() {
        return this.busStopId;
    }

    public void setBusStopId(Long busStopId) {
        this.busStopId = busStopId;
    }

    public String getFormNo() {
        return this.formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

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

    public Long getAdmissionSchemeId() {
        return this.admissionSchemeId;
    }

    public void setAdmissionSchemeId(Long admissionSchemeId) {
        this.admissionSchemeId = admissionSchemeId;
    }
}
