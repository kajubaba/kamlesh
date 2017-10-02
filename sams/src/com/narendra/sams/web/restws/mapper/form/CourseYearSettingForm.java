package com.narendra.sams.web.restws.mapper.form;

public class CourseYearSettingForm {
    private Boolean active;
    private Long courseYearSettingId;
    private Long courseYearTypeId;
    private Long intake;

    public Long getCourseYearSettingId() {
        return this.courseYearSettingId;
    }

    public void setCourseYearSettingId(Long courseYearSettingId) {
        this.courseYearSettingId = courseYearSettingId;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getCourseYearTypeId() {
        return this.courseYearTypeId;
    }

    public void setCourseYearTypeId(Long courseYearTypeId) {
        this.courseYearTypeId = courseYearTypeId;
    }

    public Long getIntake() {
        return this.intake;
    }

    public void setIntake(Long intake) {
        this.intake = intake;
    }
}
