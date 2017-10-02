package com.narendra.sams.academics.service;

import com.narendra.sams.academics.exam.domain.TermAssessment;
import java.util.List;

public interface TermAssessmentService {
    void addTermAssessment(TermAssessment termAssessment, Long l);

    void deleteTermAssessment(Long l);

    TermAssessment getTermAssessment(Long l);

    List<TermAssessment> getTermAssessments(Long l);

    void updateTermAssessment(TermAssessment termAssessment, Long l);
}
