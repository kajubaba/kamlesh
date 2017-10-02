package com.narendra.sams.academics.domain;

import com.narendra.sams.academics.exam.domain.EvaluationType;

public class AssessmentTypeGradeScale {
    private EvaluationType evaluationType;
    private GradeScale gradeScale;
    private Long id;
    private String scoringMethod;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GradeScale getGradeScale() {
        return this.gradeScale;
    }

    public void setGradeScale(GradeScale gradeScale) {
        this.gradeScale = gradeScale;
    }

    public EvaluationType getEvaluationType() {
        return this.evaluationType;
    }

    public void setEvaluationType(EvaluationType evaluationType) {
        this.evaluationType = evaluationType;
    }

    public String getScoringMethod() {
        return this.scoringMethod;
    }

    public void setScoringMethod(String scoringMethod) {
        this.scoringMethod = scoringMethod;
    }
}
