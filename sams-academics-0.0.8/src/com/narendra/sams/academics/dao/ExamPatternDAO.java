package com.narendra.sams.academics.dao;

import com.narendra.sams.academics.exam.domain.EvaluationScheme;
import java.util.List;

public interface ExamPatternDAO {
    void addExamPattern(EvaluationScheme evaluationScheme, Long l, Long l2);

    void deleteExamPattern(Long l);

    EvaluationScheme getExamPattern(Long l);

    List<EvaluationScheme> getExamPatterns(Long l);

    List<EvaluationScheme> getMasterExamPatterns(Long l);
}
