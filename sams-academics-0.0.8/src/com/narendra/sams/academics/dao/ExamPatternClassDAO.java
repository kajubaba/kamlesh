package com.narendra.sams.academics.dao;

import com.narendra.sams.academics.exam.domain.EvaluationSchemeClass;
import java.util.Collection;
import java.util.List;

public interface ExamPatternClassDAO {
    void addClassesInExamPattern(Collection<Integer> collection, Long l);

    List<EvaluationSchemeClass> getAllClassesAddedInExamPattern(Long l);

    EvaluationSchemeClass getExamPatternClass(Long l);

    List<EvaluationSchemeClass> getExamPatternClasses(Long l);

    void removeClassFromExamPattern(Long l);
}
