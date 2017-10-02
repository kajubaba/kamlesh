package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.domain.CourseYear;

public class CourseYearwiseDiscount {
    private Course course;
    private CourseYear courseYear;
    private Long discountGiven;

    public CourseYearwiseDiscount(Long courseId, String courseName, Long courseYearId, Short courseYearName, Long discountGiven) {
        this.course = new Course();
        this.course.setId(courseId);
        this.course.setDisplayName(courseName);
        this.courseYear = new CourseYear();
        this.courseYear.setId(courseYearId);
        this.courseYear.setName(courseYearName);
        this.discountGiven = discountGiven;
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

    public Long getDiscountGiven() {
        return this.discountGiven;
    }

    public void setDiscountGiven(long discountGiven) {
        this.discountGiven = Long.valueOf(discountGiven);
    }
}
