package com.narendra.sams.academics.exam.domain;

import com.narendra.sams.core.domain.AcademicYearClass;

public class EvaluationSchemeClass {
    private AcademicYearClass academicYearClass;
    private EvaluationScheme evaluationScheme;
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EvaluationScheme getEvaluationScheme() {
        return this.evaluationScheme;
    }

    public void setEvaluationScheme(EvaluationScheme evaluationScheme) {
        this.evaluationScheme = evaluationScheme;
    }

    public AcademicYearClass getAcademicYearClass() {
        return this.academicYearClass;
    }

    public void setAcademicYearClass(AcademicYearClass academicYearClass) {
        this.academicYearClass = academicYearClass;
    }
}
