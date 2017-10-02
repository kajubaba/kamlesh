package com.narendra.sams.core.domain;

import java.io.Serializable;
import java.util.Set;

public class CourseYearSetting implements Serializable {
    private static final long serialVersionUID = 1538064992544461992L;
    private AcademicYear academicYear;
    private Set<AcademicYearClass> academicYearClasses;
    private Set<AcademicYearFee> academicYearFees;
    private Boolean active;
    private CourseYear courseYear;
    private CourseYearType courseYearType;
    private Long id;
    private Long intake;

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

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getIntake() {
        return this.intake;
    }

    public void setIntake(Long intake) {
        this.intake = intake;
    }

    public CourseYearType getCourseYearType() {
        return this.courseYearType;
    }

    public void setCourseYearType(CourseYearType courseYearType) {
        this.courseYearType = courseYearType;
    }

    public Set<AcademicYearClass> getAcademicYearClasses() {
        return this.academicYearClasses;
    }

    public void setAcademicYearClasses(Set<AcademicYearClass> academicYearClasses) {
        this.academicYearClasses = academicYearClasses;
    }

    public Set<AcademicYearFee> getAcademicYearFees() {
        return this.academicYearFees;
    }

    public void setAcademicYearFees(Set<AcademicYearFee> academicYearFees) {
        this.academicYearFees = academicYearFees;
    }
}
