package com.narendra.sams.academics.service;

import com.narendra.sams.academics.exam.domain.EvaluationScheme;
import com.narendra.sams.academics.exam.domain.EvaluationSchemeClass;
import com.narendra.sams.core.domain.AcademicYearClass;
import java.util.Collection;
import java.util.List;

public interface ExamPatternClassService {
    void addClassesInExamPattern(Collection<Integer> collection, Long l);

    List<EvaluationSchemeClass> getExamPatternClasses(Long l);

    EvaluationScheme getExamPatternOfClass(Long l);

    List<AcademicYearClass> getNotAssociatedClasses(Long l);

    void removeClassFromExamPattern(Long l);
}
