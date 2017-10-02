package com.narendra.sams.core.domain;

import java.util.Set;

public class AcademicYearFee {
    private AcademicYear academicYear;
    private Set<AcademicYearFeeDetail> academicYearFeeDetails;
    private Set<AcademicYearFeeInstallment> academicYearFeeInstallments;
    private AdmissionType admissionType;
    private CourseYear courseYear;
    private CourseYearSetting courseYearSetting;
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public CourseYear getCourseYear() {
        return this.courseYear;
    }

    public void setCourseYear(CourseYear courseYear) {
        this.courseYear = courseYear;
    }

    public AdmissionType getAdmissionType() {
        return this.admissionType;
    }

    public void setAdmissionType(AdmissionType admissionType) {
        this.admissionType = admissionType;
    }

    public Long getTotalFee() {
        long totalFee = 0;
        if (!(this.academicYearFeeDetails == null || this.academicYearFeeDetails.isEmpty())) {
            for (AcademicYearFeeDetail academicYearFeeDetail : this.academicYearFeeDetails) {
                if (academicYearFeeDetail.getAmount() != null) {
                    totalFee += academicYearFeeDetail.getAmount().longValue();
                }
            }
        }
        return Long.valueOf(totalFee);
    }

    public Set<AcademicYearFeeDetail> getAcademicYearFeeDetails() {
        return this.academicYearFeeDetails;
    }

    public void setAcademicYearFeeDetails(Set<AcademicYearFeeDetail> academicYearFeeDetails) {
        this.academicYearFeeDetails = academicYearFeeDetails;
    }

    public CourseYearSetting getCourseYearSetting() {
        return this.courseYearSetting;
    }

    public void setCourseYearSetting(CourseYearSetting courseYearSetting) {
        this.courseYearSetting = courseYearSetting;
    }

    public Set<AcademicYearFeeInstallment> getAcademicYearFeeInstallments() {
        return this.academicYearFeeInstallments;
    }

    public void setAcademicYearFeeInstallments(Set<AcademicYearFeeInstallment> academicYearFeeInstallments) {
        this.academicYearFeeInstallments = academicYearFeeInstallments;
    }
}
