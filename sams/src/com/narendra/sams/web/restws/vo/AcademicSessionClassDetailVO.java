package com.narendra.sams.web.restws.vo;

import com.narendra.sams.web.restws.form.ClassHeadFeeForm;
import java.util.List;

public class AcademicSessionClassDetailVO {
    private Long academicSessionCourseYearId;
    private Long academicYearId;
    private List<ClassHeadFeeForm> feeDetails;
    private Long id;
    private Long intake;
    private Boolean isActive;
    private Boolean isFeeConfigured;
    private String name;
    private Long newAdmissionFee;
    private String newAdmissionFeeInstallmentConfigured;
    private Long regularAdmissionFee;
    private String regularAdmissionFeeInstallmentConfigured;
    private String type;
    private Long typeId;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIntake() {
        return this.intake;
    }

    public void setIntake(Long intake) {
        this.intake = intake;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Long getNewAdmissionFee() {
        return this.newAdmissionFee;
    }

    public void setNewAdmissionFee(Long newAdmissionFee) {
        this.newAdmissionFee = newAdmissionFee;
    }

    public Long getRegularAdmissionFee() {
        return this.regularAdmissionFee;
    }

    public void setRegularAdmissionFee(Long regularAdmissionFee) {
        this.regularAdmissionFee = regularAdmissionFee;
    }

    public List<ClassHeadFeeForm> getFeeDetails() {
        return this.feeDetails;
    }

    public void setFeeDetails(List<ClassHeadFeeForm> feeDetails) {
        this.feeDetails = feeDetails;
    }

    public Long getAcademicYearId() {
        return this.academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public Long getAcademicSessionCourseYearId() {
        return this.academicSessionCourseYearId;
    }

    public void setAcademicSessionCourseYearId(Long academicSessionCourseYearId) {
        this.academicSessionCourseYearId = academicSessionCourseYearId;
    }

    public Long getTypeId() {
        return this.typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Boolean getIsFeeConfigured() {
        return this.isFeeConfigured;
    }

    public void setIsFeeConfigured(Boolean isFeeConfigured) {
        this.isFeeConfigured = isFeeConfigured;
    }

    public String getNewAdmissionFeeInstallmentConfigured() {
        return this.newAdmissionFeeInstallmentConfigured;
    }

    public void setNewAdmissionFeeInstallmentConfigured(String newAdmissionFeeInstallmentConfigured) {
        this.newAdmissionFeeInstallmentConfigured = newAdmissionFeeInstallmentConfigured;
    }

    public String getRegularAdmissionFeeInstallmentConfigured() {
        return this.regularAdmissionFeeInstallmentConfigured;
    }

    public void setRegularAdmissionFeeInstallmentConfigured(String regularAdmissionFeeInstallmentConfigured) {
        this.regularAdmissionFeeInstallmentConfigured = regularAdmissionFeeInstallmentConfigured;
    }
}
