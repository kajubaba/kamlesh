package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.domain.CourseYear;

public class MiscActivityCourseYearFeeSummary {
    private long confirmAdmissions;
    private Course course;
    private CourseYear courseYear;
    private long depositedFee;
    private Long miscActivityCourseYearId;

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CourseYear getCourseYear() {
        return this.courseYear;
    }

    public void setCourseYear(CourseYear courseYear) {
        this.courseYear = courseYear;
    }

    public long getConfirmAdmissions() {
        return this.confirmAdmissions;
    }

    public void setConfirmAdmissions(long confirmAdmissions) {
        this.confirmAdmissions = confirmAdmissions;
    }

    public long getDepositedFee() {
        return this.depositedFee;
    }

    public void setDepositedFee(long depositedFee) {
        this.depositedFee = depositedFee;
    }

    public Long getMiscActivityCourseYearId() {
        return this.miscActivityCourseYearId;
    }

    public void setMiscActivityCourseYearId(Long miscActivityCourseYearId) {
        this.miscActivityCourseYearId = miscActivityCourseYearId;
    }
}
