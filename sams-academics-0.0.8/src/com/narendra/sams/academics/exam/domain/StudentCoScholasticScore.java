package com.narendra.sams.academics.exam.domain;

import com.narendra.sams.academics.domain.GradeScalePoint;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.AcademicYearClass;

public class StudentCoScholasticScore {
    private EvaluationTerm evaluationTerm;
    private String freeTextValue;
    private GradeScalePoint gradeScalePoint;
    private Long id;
    private Long marksObtained;
    private SkillIndicator skillIndicator;
    private Student student;
    private AcademicYearClass studentClass;

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

    public SkillIndicator getSkillIndicator() {
        return this.skillIndicator;
    }

    public void setSkillIndicator(SkillIndicator skillIndicator) {
        this.skillIndicator = skillIndicator;
    }

    public EvaluationTerm getEvaluationTerm() {
        return this.evaluationTerm;
    }

    public void setEvaluationTerm(EvaluationTerm evaluationTerm) {
        this.evaluationTerm = evaluationTerm;
    }

    public Long getMarksObtained() {
        return this.marksObtained;
    }

    public void setMarksObtained(Long marksObtained) {
        this.marksObtained = marksObtained;
    }

    public GradeScalePoint getGradeScalePoint() {
        return this.gradeScalePoint;
    }

    public void setGradeScalePoint(GradeScalePoint gradeScalePoint) {
        this.gradeScalePoint = gradeScalePoint;
    }

    public String getFreeTextValue() {
        return this.freeTextValue;
    }

    public void setFreeTextValue(String freeTextValue) {
        this.freeTextValue = freeTextValue;
    }
}
