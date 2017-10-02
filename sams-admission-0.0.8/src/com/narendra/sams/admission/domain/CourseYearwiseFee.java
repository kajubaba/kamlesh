package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.domain.CourseYear;

public class CourseYearwiseFee {
    private Long academicYearId;
    private Course course;
    private CourseYear courseYear;
    private long newAdmissionFee;
    private long regularAdmissionFee;

    public CourseYearwiseFee(Long courseId, String courseName, Long courseYearId, Short courseYearName) {
        this.course = new Course();
        this.course.setId(courseId);
        this.course.setDisplayName(courseName);
        this.courseYear = new CourseYear();
        this.courseYear.setId(courseYearId);
        this.courseYear.setName(courseYearName);
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

    public long getNewAdmissionFee() {
        return this.newAdmissionFee;
    }

    public void setNewAdmissionFee(long newAdmissionFee) {
        this.newAdmissionFee = newAdmissionFee;
    }

    public long getRegularAdmissionFee() {
        return this.regularAdmissionFee;
    }

    public void setRegularAdmissionFee(long regularAdmissionFee) {
        this.regularAdmissionFee = regularAdmissionFee;
    }

    public Long getAcademicYearId() {
        return this.academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }
}
