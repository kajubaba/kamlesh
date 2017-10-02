package com.narendra.sams.academics.service;

import com.narendra.sams.academics.exam.domain.EvaluationTerm;
import java.util.List;

public interface EvaluationTermService {
    void addCoScholasticEvaluationTerm(EvaluationTerm evaluationTerm, Long l);

    void addScholasticEvaluationTerm(EvaluationTerm evaluationTerm, Long l);

    void deleteEvaluationTerm(Long l);

    List<EvaluationTerm> getCoScholasticEvaluationTerms(Long l);

    List<EvaluationTerm> getCoScholasticEvaluationTermsOfExamPattern(Long l);

    EvaluationTerm getEvaluationTerm(Long l);

    List<EvaluationTerm> getEvaluationTerms(Long l);

    List<EvaluationTerm> getScholasticEvaluationTerms(Long l);

    List<EvaluationTerm> getScholasticEvaluationTermsOfExamPattern(Long l);

    void updateEvaluationTerm(EvaluationTerm evaluationTerm, Long l);
}
