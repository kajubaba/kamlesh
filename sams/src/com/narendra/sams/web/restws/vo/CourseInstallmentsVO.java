package com.narendra.sams.web.restws.vo;

import com.narendra.sams.web.admin.vo.FeeHeadWiseInstallmentVO;
import com.narendra.sams.web.admin.vo.InstallmentDueDate;
import java.util.List;

public class CourseInstallmentsVO {
    private Long academicYearFeeId;
    private Short admissionTypeId;
    private String courseName;
    private String courseType;
    private Long courseYearSettingId;
    private List<FeeHeadWiseInstallmentVO> headwiseInstallments;
    private List<InstallmentDueDate> installmentDueDates;
    private Long installments;
    private Long intake;
    private Boolean isActive;

    public Long getCourseYearSettingId() {
        return this.courseYearSettingId;
    }

    public void setCourseYearSettingId(Long courseYearSettingId) {
        this.courseYearSettingId = courseYearSettingId;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return this.courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
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

    public List<FeeHeadWiseInstallmentVO> getHeadwiseInstallments() {
        return this.headwiseInstallments;
    }

    public void setHeadwiseInstallments(List<FeeHeadWiseInstallmentVO> headwiseInstallments) {
        this.headwiseInstallments = headwiseInstallments;
    }

    public List<InstallmentDueDate> getInstallmentDueDates() {
        return this.installmentDueDates;
    }

    public void setInstallmentDueDates(List<InstallmentDueDate> installmentDueDates) {
        this.installmentDueDates = installmentDueDates;
    }

    public Long getAcademicYearFeeId() {
        return this.academicYearFeeId;
    }

    public void setAcademicYearFeeId(Long academicYearFeeId) {
        this.academicYearFeeId = academicYearFeeId;
    }

    public Long getInstallments() {
        return this.installments;
    }

    public void setInstallments(Long installments) {
        this.installments = installments;
    }

    public Short getAdmissionTypeId() {
        return this.admissionTypeId;
    }

    public void setAdmissionTypeId(Short admissionTypeId) {
        this.admissionTypeId = admissionTypeId;
    }
}
