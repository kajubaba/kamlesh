package com.narendra.sams.academics.dao;

import com.narendra.sams.academics.exam.domain.EvaluationType;
import java.util.List;

public interface AssessmentTypeDAO {
    EvaluationType getCoScholasticEvaluationType(Long l);

    EvaluationType getCoScholasticEvaluationTypeOfExamPattern(Long l);

    EvaluationType getEvaluationType(Long l);

    List<EvaluationType> getEvaluationTypes(Long l);

    EvaluationType getScholasticEvaluationType(Long l);

    EvaluationType getScholasticEvaluationTypeOfExamPattern(Long l);

    void updateAssessmentGradeAndScoringMethod(Long l, Long l2, String str, String str2, Long l3, Boolean bool, Boolean bool2, Boolean bool3);
}
