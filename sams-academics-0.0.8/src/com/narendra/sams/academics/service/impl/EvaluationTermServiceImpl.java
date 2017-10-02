package com.narendra.sams.academics.service.impl;

import com.narendra.sams.academics.dao.AssessmentTypeDAO;
import com.narendra.sams.academics.dao.EvaluationTermDAO;
import com.narendra.sams.academics.exam.domain.EvaluationTerm;
import com.narendra.sams.academics.exam.domain.EvaluationType;
import com.narendra.sams.academics.exam.domain.TermAssessment;
import com.narendra.sams.academics.service.AssessmentTypeService;
import com.narendra.sams.academics.service.EvaluationTermService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EvaluationTermServiceImpl implements EvaluationTermService {
    private AssessmentTypeDAO assessmentTypeDAO;
    private AssessmentTypeService assessmentTypeService;
    private EvaluationTermDAO evaluationTermDAO;

    public EvaluationTermDAO getEvaluationTermDAO() {
        return this.evaluationTermDAO;
    }

    public void setEvaluationTermDAO(EvaluationTermDAO evaluationTermDAO) {
        this.evaluationTermDAO = evaluationTermDAO;
    }

    public AssessmentTypeService getAssessmentTypeService() {
        return this.assessmentTypeService;
    }

    public void setAssessmentTypeService(AssessmentTypeService assessmentTypeService) {
        this.assessmentTypeService = assessmentTypeService;
    }

    public AssessmentTypeDAO getAssessmentTypeDAO() {
        return this.assessmentTypeDAO;
    }

    public void setAssessmentTypeDAO(AssessmentTypeDAO assessmentTypeDAO) {
        this.assessmentTypeDAO = assessmentTypeDAO;
    }

    public List<EvaluationTerm> getEvaluationTerms(Long evaluationTypeId) {
        return this.evaluationTermDAO.getEvaluationTerms(evaluationTypeId);
    }

    public List<EvaluationTerm> getCoScholasticEvaluationTerms(Long academicYearClassId) {
        EvaluationType evaluationType = this.assessmentTypeService.getCoScholasticEvaluationType(academicYearClassId);
        if (evaluationType == null) {
            return null;
        }
        return getEvaluationTerms(evaluationType.getId());
    }

    public List<EvaluationTerm> getScholasticEvaluationTerms(Long academicYearClassId) {
        EvaluationType evaluationType = this.assessmentTypeService.getScholasticEvaluationType(academicYearClassId);
        if (evaluationType == null) {
            return null;
        }
        return getEvaluationTerms(evaluationType.getId());
    }

    public List<EvaluationTerm> getScholasticEvaluationTermsOfExamPattern(Long examPatternId) {
        return getEvaluationTerms(this.assessmentTypeDAO.getScholasticEvaluationTypeOfExamPattern(examPatternId).getId());
    }

    public List<EvaluationTerm> getCoScholasticEvaluationTermsOfExamPattern(Long examPatternId) {
        return getEvaluationTerms(this.assessmentTypeDAO.getCoScholasticEvaluationTypeOfExamPattern(examPatternId).getId());
    }

    public void addScholasticEvaluationTerm(EvaluationTerm evaluationTerm, Long userId) {
        TermAssessment termAssessment = new TermAssessment();
        termAssessment.setDisplayName("Assessment-1");
        termAssessment.setName("Assessment-1");
        termAssessment.setDisplayOrder(Long.valueOf(1));
        termAssessment.setEvaluationTerm(evaluationTerm);
        Set<TermAssessment> termAssessments = new HashSet();
        termAssessments.add(termAssessment);
        evaluationTerm.setTermAssessments(termAssessments);
        this.evaluationTermDAO.addEvaluationTerm(evaluationTerm, userId);
    }

    public void addCoScholasticEvaluationTerm(EvaluationTerm evaluationTerm, Long userId) {
        this.evaluationTermDAO.addEvaluationTerm(evaluationTerm, userId);
    }

    public void updateEvaluationTerm(EvaluationTerm evaluationTerm, Long userId) {
        this.evaluationTermDAO.updateEvaluationTerm(evaluationTerm, userId);
    }

    public void deleteEvaluationTerm(Long termId) {
        this.evaluationTermDAO.deleteEvaluationTerm(termId);
    }

    public EvaluationTerm getEvaluationTerm(Long id) {
        return this.evaluationTermDAO.getEvaluationTerm(id);
    }
}
