package com.narendra.sams.core.domain;

public class CourseYearFeeTotal {
    private Long courseId;
    private Long newStudentFee;
    private Long regularStudentFee;

    public CourseYearFeeTotal(Long courseId, Long newStudentFee, Long regularStudentFee) {
        this.courseId = courseId;
        this.newStudentFee = newStudentFee;
        this.regularStudentFee = regularStudentFee;
    }

    public Long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getNewStudentFee() {
        return this.newStudentFee;
    }

    public void setNewStudentFee(Long newStudentFee) {
        this.newStudentFee = newStudentFee;
    }

    public Long getRegularStudentFee() {
        return this.regularStudentFee;
    }

    public void setRegularStudentFee(Long regularStudentFee) {
        this.regularStudentFee = regularStudentFee;
    }
}
