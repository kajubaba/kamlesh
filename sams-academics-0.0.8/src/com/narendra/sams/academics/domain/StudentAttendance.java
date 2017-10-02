package com.narendra.sams.academics.domain;

import com.narendra.sams.academics.exam.domain.EvaluationTerm;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.AcademicYearClass;

public class StudentAttendance {
    private AcademicYearClass academicYearClass;
    private Long attendance;
    private EvaluationTerm evaluationTerm;
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

    public EvaluationTerm getEvaluationTerm() {
        return this.evaluationTerm;
    }

    public void setEvaluationTerm(EvaluationTerm evaluationTerm) {
        this.evaluationTerm = evaluationTerm;
    }

    public Long getAttendance() {
        return this.attendance;
    }

    public void setAttendance(Long attendance) {
        this.attendance = attendance;
    }

    public AcademicYearClass getAcademicYearClass() {
        return this.academicYearClass;
    }

    public void setAcademicYearClass(AcademicYearClass academicYearClass) {
        this.academicYearClass = academicYearClass;
    }
}
