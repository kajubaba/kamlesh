package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.domain.CourseYear;

public class CourseYearwiseBusFee {
    private Long busFee;
    private Course course;
    private CourseYear courseYear;

    public CourseYearwiseBusFee(Long courseId, String courseName, Long courseYearId, Short courseYearName, Long busFee) {
        this.course = new Course();
        this.course.setId(courseId);
        this.course.setDisplayName(courseName);
        this.courseYear = new CourseYear();
        this.courseYear.setId(courseYearId);
        this.courseYear.setName(courseYearName);
        this.busFee = busFee;
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

    public Long getBusFee() {
        return this.busFee;
    }

    public void setBusFee(Long busFee) {
        this.busFee = busFee;
    }
}
