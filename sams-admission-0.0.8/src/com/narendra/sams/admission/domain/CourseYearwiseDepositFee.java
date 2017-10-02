package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.domain.CourseYear;

public class CourseYearwiseDepositFee {
    private Course course;
    private CourseYear courseYear;
    private Long depositedFee;

    public CourseYearwiseDepositFee(Long courseId, String courseName, Long courseYearId, Short courseYearName, Long depositedFee) {
        this.course = new Course();
        this.course.setId(courseId);
        this.course.setDisplayName(courseName);
        this.courseYear = new CourseYear();
        this.courseYear.setId(courseYearId);
        this.courseYear.setName(courseYearName);
        this.depositedFee = depositedFee;
    }

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

    public Long getDepositedFee() {
        return this.depositedFee;
    }

    public void setDepositedFee(Long depositedFee) {
        this.depositedFee = depositedFee;
    }
}
