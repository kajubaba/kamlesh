package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearFee;
import java.io.Serializable;

public class FeeCustomizeComments implements Serializable {
    private static final long serialVersionUID = -5489430791039639871L;
    private AcademicYear academicYear;
    private AcademicYearFee academicYearFee;
    private String comments;
    private Long id;
    private Student student;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public AcademicYearFee getAcademicYearFee() {
        return this.academicYearFee;
    }

    public void setAcademicYearFee(AcademicYearFee academicYearFee) {
        this.academicYearFee = academicYearFee;
    }
}
