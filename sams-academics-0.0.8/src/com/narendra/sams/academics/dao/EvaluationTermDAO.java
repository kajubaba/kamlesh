package com.narendra.sams.academics.dao;

import com.narendra.sams.academics.exam.domain.EvaluationTerm;
import java.util.List;

public interface EvaluationTermDAO {
    void addEvaluationTerm(EvaluationTerm evaluationTerm, Long l);

    void deleteEvaluationTerm(Long l);

    EvaluationTerm getEvaluationTerm(Long l);

    List<EvaluationTerm> getEvaluationTerms(Long l);

    void updateEvaluationTerm(EvaluationTerm evaluationTerm, Long l);
}
