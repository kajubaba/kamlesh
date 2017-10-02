package com.narendra.sams.academics.service;

import com.narendra.sams.academics.exam.domain.EvaluationType;

public interface AssessmentTypeService {
    EvaluationType getCoScholasticEvaluationType(Long l);

    EvaluationType getCoScholasticEvaluationTypeOfExamPattern(Long l);

    EvaluationType getEvaluationType(Long l);

    EvaluationType getScholasticEvaluationType(Long l);

    EvaluationType getScholasticEvaluationTypeOfExamPattern(Long l);

    void updateAssessmentGradeAndScoringMethod(Long l, Long l2, String str, String str2, Long l3, Boolean bool, Boolean bool2, Boolean bool3);
}
