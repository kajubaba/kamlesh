package com.narendra.sams.academics.service.impl;

import com.narendra.sams.academics.dao.TermAssessmentDAO;
import com.narendra.sams.academics.exam.domain.TermAssessment;
import com.narendra.sams.academics.service.TermAssessmentService;
import java.util.List;

public class TermAssessmentServiceImpl implements TermAssessmentService {
    private TermAssessmentDAO termAssessmentDAO;

    public TermAssessmentDAO getTermAssessmentDAO() {
        return this.termAssessmentDAO;
    }

    public void setTermAssessmentDAO(TermAssessmentDAO termAssessmentDAO) {
        this.termAssessmentDAO = termAssessmentDAO;
    }

    public List<TermAssessment> getTermAssessments(Long termId) {
        return this.termAssessmentDAO.getTermAssessments(termId);
    }

    public void updateTermAssessment(TermAssessment termAssessment, Long userId) {
        this.termAssessmentDAO.updateTermAssessment(termAssessment, userId);
    }

    public void addTermAssessment(TermAssessment termAssessment, Long userId) {
        this.termAssessmentDAO.addTermAssessment(termAssessment, userId);
    }

    public void deleteTermAssessment(Long termAssessmentId) {
        this.termAssessmentDAO.deleteTermAssessment(termAssessmentId);
    }

    public TermAssessment getTermAssessment(Long id) {
        return this.termAssessmentDAO.getTermAssessment(id);
    }
}
