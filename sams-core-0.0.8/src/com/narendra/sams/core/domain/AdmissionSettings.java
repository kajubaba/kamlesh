package com.narendra.sams.core.domain;

public class AdmissionSettings {
    public static String ID_GENERATION_METHOD_CUSTOMIZED = "Method_1";
    public static String ID_GENERATION_METHOD_INCREMENTAL = "Incremental";
    private AcademicYear activeAcademicYear;
    private Boolean busStopMandatory;
    private String confirmStudentIdPrefix;
    private Boolean formNoMandatory;
    private Long nextStudentId;
    private String registeredStudentIdPrefix;
    private String studentIdGenerationMethod;

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

    public String getConfirmStudentIdPrefix() {
        return this.confirmStudentIdPrefix;
    }

    public void setConfirmStudentIdPrefix(String confirmStudentIdPrefix) {
        this.confirmStudentIdPrefix = confirmStudentIdPrefix;
    }

    public Long getNextStudentId() {
        return this.nextStudentId;
    }

    public void setNextStudentId(Long nextStudentId) {
        this.nextStudentId = nextStudentId;
    }

    public AcademicYear getActiveAcademicYear() {
        return this.activeAcademicYear;
    }

    public void setActiveAcademicYear(AcademicYear activeAcademicYear) {
        this.activeAcademicYear = activeAcademicYear;
    }

    public Boolean getBusStopMandatory() {
        return this.busStopMandatory;
    }

    public void setBusStopMandatory(Boolean busStopMandatory) {
        this.busStopMandatory = busStopMandatory;
    }

    public Boolean getFormNoMandatory() {
        return this.formNoMandatory;
    }

    public void setFormNoMandatory(Boolean formNoMandatory) {
        this.formNoMandatory = formNoMandatory;
    }
}
