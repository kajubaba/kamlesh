package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.domain.CourseYear;

public class CourseYearwiseAdmissionCount {
    private Course course;
    private CourseYear courseYear;
    private long newAdmissionCount;
    private long newAdmissionFee;
    private long regularAdmissionCount;
    private long regularAdmissionFee;
    private long totalAdmissionCount;

    public CourseYearwiseAdmissionCount(Long courseId, String courseName, Long courseYearId, Short courseYearName) {
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

    public long getNewAdmissionCount() {
        return this.newAdmissionCount;
    }

    public void setNewAdmissionCount(long newAdmissionCount) {
        this.newAdmissionCount = newAdmissionCount;
    }

    public long getRegularAdmissionCount() {
        return this.regularAdmissionCount;
    }

    public void setRegularAdmissionCount(long regularAdmissionCount) {
        this.regularAdmissionCount = regularAdmissionCount;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AdmissionTypeCount [course=").append(this.course).append(", courseYear=").append(this.courseYear).append(", newAdmissionCount=").append(this.newAdmissionCount).append(", regularAdmissionCount=").append(this.regularAdmissionCount).append("]");
        return builder.toString();
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

    public long getTotalAdmissionCount() {
        return this.totalAdmissionCount;
    }

    public void setTotalAdmissionCount(long totalAdmissionCount) {
        this.totalAdmissionCount = totalAdmissionCount;
    }
}
