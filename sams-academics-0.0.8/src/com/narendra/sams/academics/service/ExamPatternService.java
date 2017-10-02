package com.narendra.sams.academics.service;

import com.narendra.sams.academics.exam.domain.EvaluationScheme;
import java.util.List;

public interface ExamPatternService {
    void createExamPattern(Long l, String str, Long l2, Long l3, Long l4);

    void deleteExamPattern(Long l);

    EvaluationScheme getExamPattern(Long l);

    List<EvaluationScheme> getExamPatterns(Long l);

    List<EvaluationScheme> getMasterExamPatterns(Long l);
}
