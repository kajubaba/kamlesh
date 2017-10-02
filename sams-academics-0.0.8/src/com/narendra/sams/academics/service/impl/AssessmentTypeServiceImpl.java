package com.narendra.sams.academics.service.impl;

import com.narendra.sams.academics.dao.AssessmentTypeDAO;
import com.narendra.sams.academics.exam.domain.EvaluationType;
import com.narendra.sams.academics.service.AssessmentTypeService;

public class AssessmentTypeServiceImpl implements AssessmentTypeService {
    private AssessmentTypeDAO assessmentTypeDAO;

    public AssessmentTypeDAO getAssessmentTypeDAO() {
        return this.assessmentTypeDAO;
    }

    public void setAssessmentTypeDAO(AssessmentTypeDAO assessmentTypeDAO) {
        this.assessmentTypeDAO = assessmentTypeDAO;
    }

    public EvaluationType getScholasticEvaluationType(Long academicYearClassId) {
        return this.assessmentTypeDAO.getScholasticEvaluationType(academicYearClassId);
    }

    public EvaluationType getCoScholasticEvaluationType(Long academicYearClassId) {
        return this.assessmentTypeDAO.getCoScholasticEvaluationType(academicYearClassId);
    }

    public EvaluationType getCoScholasticEvaluationTypeOfExamPattern(Long examPatternId) {
        return this.assessmentTypeDAO.getCoScholasticEvaluationTypeOfExamPattern(examPatternId);
    }

    public EvaluationType getScholasticEvaluationTypeOfExamPattern(Long examPatternId) {
        return this.assessmentTypeDAO.getScholasticEvaluationTypeOfExamPattern(examPatternId);
    }

    public void updateAssessmentGradeAndScoringMethod(Long assessmentTypeId, Long gradeScaleId, String scoringMethod, String gradeCalculationMethod, Long maxMarks, Boolean isFASABasedExam, Boolean isTermBasedAssessment, Boolean useAssessmentWeightageAsMaxMarks) {
        this.assessmentTypeDAO.updateAssessmentGradeAndScoringMethod(assessmentTypeId, gradeScaleId, scoringMethod, gradeCalculationMethod, maxMarks, isFASABasedExam, isTermBasedAssessment, useAssessmentWeightageAsMaxMarks);
    }

    public EvaluationType getEvaluationType(Long id) {
        return this.assessmentTypeDAO.getEvaluationType(id);
    }
}
