package com.narendra.sams.web.restws.vo;

public class AdmissionSettingsVO {
    private Long admissionAcademicSessionId;
    private String confirmStudentIdPrefix;
    private Long nextStudentId;
    private String registeredStudentIdPrefix;
    private String studentIdGenerationMethod;

    public Long getAdmissionAcademicSessionId() {
        return this.admissionAcademicSessionId;
    }

    public void setAdmissionAcademicSessionId(Long admissionAcademicSessionId) {
        this.admissionAcademicSessionId = admissionAcademicSessionId;
    }

    public String getConfirmStudentIdPrefix() {
        return this.confirmStudentIdPrefix;
    }

    public void setConfirmStudentIdPrefix(String confirmStudentIdPrefix) {
        this.confirmStudentIdPrefix = confirmStudentIdPrefix;
    }

    public String getRegisteredStudentIdPrefix() {
        return this.registeredStudentIdPrefix;
    }

    public void setRegisteredStudentIdPrefix(String registeredStudentIdPrefix) {
        this.registeredStudentIdPrefix = registeredStudentIdPrefix;
    }

    public String getStudentIdGenerationMethod() {
        return this.studentIdGenerationMethod;
    }

    public void setStudentIdGenerationMethod(String studentIdGenerationMethod) {
        this.studentIdGenerationMethod = studentIdGenerationMethod;
    }

    public Long getNextStudentId() {
        return this.nextStudentId;
    }

    public void setNextStudentId(Long nextStudentId) {
        this.nextStudentId = nextStudentId;
    }
}
