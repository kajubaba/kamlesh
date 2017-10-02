package com.narendra.sams.academics.exam.domain;

import com.narendra.sams.academics.domain.ClassSubject;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.AcademicYearClass;

public class ScholasticScore {
    private ClassSubject classSubject;
    private Long id;
    private Float marksObtained;
    private Student student;
    private AcademicYearClass studentClass;
    private TermAssessment termAssessment;

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

    public AcademicYearClass getStudentClass() {
        return this.studentClass;
    }

    public void setStudentClass(AcademicYearClass studentClass) {
        this.studentClass = studentClass;
    }

    public ClassSubject getClassSubject() {
        return this.classSubject;
    }

    public void setClassSubject(ClassSubject classSubject) {
        this.classSubject = classSubject;
    }

    public TermAssessment getTermAssessment() {
        return this.termAssessment;
    }

    public void setTermAssessment(TermAssessment termAssessment) {
        this.termAssessment = termAssessment;
    }

    public Float getMarksObtained() {
        return this.marksObtained;
    }

    public void setMarksObtained(Float marksObtained) {
        this.marksObtained = marksObtained;
    }
}
