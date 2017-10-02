package com.narendra.sams.academics.service.impl;

import com.narendra.sams.academics.dao.ExamPatternDAO;
import com.narendra.sams.academics.exam.domain.ActivitySkill;
import com.narendra.sams.academics.exam.domain.CoScholasticActivity;
import com.narendra.sams.academics.exam.domain.EvaluationScheme;
import com.narendra.sams.academics.exam.domain.EvaluationTerm;
import com.narendra.sams.academics.exam.domain.EvaluationType;
import com.narendra.sams.academics.exam.domain.SkillIndicator;
import com.narendra.sams.academics.exam.domain.TermAssessment;
import com.narendra.sams.academics.service.ExamPatternService;
import com.narendra.sams.core.domain.AcademicYear;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExamPatternServiceImpl implements ExamPatternService {
    private ExamPatternDAO examPatternDAO;

    public ExamPatternDAO getExamPatternDAO() {
        return this.examPatternDAO;
    }

    public void setExamPatternDAO(ExamPatternDAO examPatternDAO) {
        this.examPatternDAO = examPatternDAO;
    }

    public List<EvaluationScheme> getExamPatterns(Long academicYearId) {
        return this.examPatternDAO.getExamPatterns(academicYearId);
    }

    public List<EvaluationScheme> getMasterExamPatterns(Long instituteId) {
        return this.examPatternDAO.getMasterExamPatterns(instituteId);
    }

    public void createExamPattern(Long baseExamPatternId, String examPatternName, Long academicSessionId, Long instituteId, Long userId) {
        EvaluationScheme newExamPattern = copyExamPattern(this.examPatternDAO.getExamPattern(baseExamPatternId));
        AcademicYear academicYear = new AcademicYear();
        academicYear.setId(academicSessionId);
        newExamPattern.setAcademicYear(academicYear);
        newExamPattern.setSchemeName(examPatternName);
        newExamPattern.setIsMaster(Boolean.FALSE);
        this.examPatternDAO.addExamPattern(newExamPattern, instituteId, userId);
    }

    private EvaluationScheme copyExamPattern(EvaluationScheme existingExamPattern) {
        EvaluationScheme newExamPattern = new EvaluationScheme();
        if (!(existingExamPattern.getEvaluationTypes() == null || existingExamPattern.getEvaluationTypes().isEmpty())) {
            Set<EvaluationType> newEvaluationTypes = new HashSet();
            for (EvaluationType evaluationType : existingExamPattern.getEvaluationTypes()) {
                EvaluationType newEvaluationType = copyEvaluationType(evaluationType);
                newEvaluationType.setEvaluationScheme(newExamPattern);
                newEvaluationTypes.add(newEvaluationType);
            }
            newExamPattern.setEvaluationTypes(newEvaluationTypes);
        }
        return newExamPattern;
    }

    private EvaluationType copyEvaluationType(EvaluationType existingEvaluationType) {
        EvaluationType newEvaluationType = new EvaluationType();
        newEvaluationType.setName(existingEvaluationType.getName());
        newEvaluationType.setDisplayName(existingEvaluationType.getDisplayName());
        newEvaluationType.setDescription(existingEvaluationType.getDescription());
        newEvaluationType.setIsScholastic(existingEvaluationType.getIsScholastic());
        newEvaluationType.setIsIndicatorBased(existingEvaluationType.getIsIndicatorBased());
        newEvaluationType.setIsSkillBased(existingEvaluationType.getIsSkillBased());
        newEvaluationType.setScoringMethod(existingEvaluationType.getScoringMethod());
        newEvaluationType.setGradeCalculationMethod(existingEvaluationType.getGradeCalculationMethod());
        newEvaluationType.setUseAssessmentWeightageAsMaxMarks(existingEvaluationType.getUseAssessmentWeightageAsMaxMarks());
        newEvaluationType.setIsFASABasedExam(existingEvaluationType.getIsFASABasedExam());
        newEvaluationType.setIsTermBasedAssessment(existingEvaluationType.getIsTermBasedAssessment());
        if (!(existingEvaluationType.getEvaluationTerms() == null || existingEvaluationType.getEvaluationTerms().isEmpty())) {
            Set<EvaluationTerm> newEvaluationTerms = new HashSet();
            for (EvaluationTerm evaluationTerm : existingEvaluationType.getEvaluationTerms()) {
                EvaluationTerm newEvaluationTerm = copyEvaluationTerm(evaluationTerm);
                newEvaluationTerm.setEvaluationType(newEvaluationType);
                newEvaluationTerms.add(newEvaluationTerm);
            }
            newEvaluationType.setEvaluationTerms(newEvaluationTerms);
        }
        if (!(existingEvaluationType.getCoScholasticActivities() == null || existingEvaluationType.getCoScholasticActivities().isEmpty())) {
            Set<CoScholasticActivity> newCoScholasticActivities = new HashSet();
            for (CoScholasticActivity existingCoScholasticActivity : existingEvaluationType.getCoScholasticActivities()) {
                CoScholasticActivity newCoScholasticActivity = copyCoScholasticActivity(existingCoScholasticActivity);
                newCoScholasticActivity.setEvaluationType(newEvaluationType);
                newCoScholasticActivities.add(newCoScholasticActivity);
            }
            newEvaluationType.setCoScholasticActivities(newCoScholasticActivities);
        }
        return newEvaluationType;
    }

    private CoScholasticActivity copyCoScholasticActivity(CoScholasticActivity existingcoScholasticActivity) {
        CoScholasticActivity newCoScholasticActivity = new CoScholasticActivity();
        newCoScholasticActivity.setActivityName(existingcoScholasticActivity.getActivityName());
        newCoScholasticActivity.setActivityDisplayName(existingcoScholasticActivity.getActivityDisplayName());
        newCoScholasticActivity.setDisplaySeqNo(existingcoScholasticActivity.getDisplaySeqNo());
        newCoScholasticActivity.setIsSkillBasedAssessment(existingcoScholasticActivity.getIsSkillBasedAssessment());
        if (!(existingcoScholasticActivity.getSubActivities() == null || existingcoScholasticActivity.getSubActivities().isEmpty())) {
            Set<CoScholasticActivity> newSubActivities = new HashSet();
            for (CoScholasticActivity subActivity : existingcoScholasticActivity.getSubActivities()) {
                CoScholasticActivity newSubActivity = copyCoScholasticActivity(subActivity);
                newSubActivity.setParentActivity(newCoScholasticActivity);
                newSubActivities.add(newSubActivity);
            }
            newCoScholasticActivity.setSubActivities(newSubActivities);
        }
        if (!(existingcoScholasticActivity.getActivitySkills() == null || existingcoScholasticActivity.getActivitySkills().isEmpty())) {
            Set<ActivitySkill> newActivitySkills = new HashSet();
            for (ActivitySkill existingActivitySkill : existingcoScholasticActivity.getActivitySkills()) {
                ActivitySkill newActivitySkill = copyActivitySkill(existingActivitySkill);
                newActivitySkill.setActivity(newCoScholasticActivity);
                newActivitySkills.add(newActivitySkill);
            }
            existingcoScholasticActivity.setActivitySkills(newActivitySkills);
        }
        return newCoScholasticActivity;
    }

    private ActivitySkill copyActivitySkill(ActivitySkill existingActivitySkill) {
        ActivitySkill newActivitySkill = new ActivitySkill();
        newActivitySkill.setSkillName(existingActivitySkill.getSkillName());
        newActivitySkill.setSkillDisplayName(existingActivitySkill.getSkillDisplayName());
        newActivitySkill.setDisplaySeqNo(existingActivitySkill.getDisplaySeqNo());
        newActivitySkill.setDoNotDisplayOnScoreCard(existingActivitySkill.getDoNotDisplayOnScoreCard());
        newActivitySkill.setIsAdditional(existingActivitySkill.getIsAdditional());
        if (!(existingActivitySkill.getIndicators() == null || existingActivitySkill.getIndicators().isEmpty())) {
            Set<SkillIndicator> newSkillIndicators = new HashSet();
            for (SkillIndicator skillIndicator : existingActivitySkill.getIndicators()) {
                SkillIndicator newSkillIndicator = copySkillIndicator(skillIndicator);
                newSkillIndicator.setActivitySkill(newActivitySkill);
                newSkillIndicators.add(newSkillIndicator);
            }
            newActivitySkill.setIndicators(newSkillIndicators);
        }
        return newActivitySkill;
    }

    private SkillIndicator copySkillIndicator(SkillIndicator skillIndicator) {
        SkillIndicator newSkillIndicator = new SkillIndicator();
        newSkillIndicator.setName(skillIndicator.getName());
        newSkillIndicator.setDisplayName(skillIndicator.getDisplayName());
        newSkillIndicator.setDisplaySeqNo(skillIndicator.getDisplaySeqNo());
        return newSkillIndicator;
    }

    private EvaluationTerm copyEvaluationTerm(EvaluationTerm existingEvaluationTerm) {
        EvaluationTerm newEvaluationTerm = new EvaluationTerm();
        newEvaluationTerm.setTermName(existingEvaluationTerm.getTermName());
        newEvaluationTerm.setTermDisplayName(existingEvaluationTerm.getTermDisplayName());
        newEvaluationTerm.setDisplayOrder(existingEvaluationTerm.getDisplayOrder());
        newEvaluationTerm.setWeightageInFinalAssessment(existingEvaluationTerm.getWeightageInFinalAssessment());
        newEvaluationTerm.setWorkingDays(existingEvaluationTerm.getWorkingDays());
        if (!(existingEvaluationTerm.getTermAssessments() == null || existingEvaluationTerm.getTermAssessments().isEmpty())) {
            Set<TermAssessment> newTermAssessments = new HashSet();
            for (TermAssessment termAssessment : existingEvaluationTerm.getTermAssessments()) {
                TermAssessment newTermAssessment = copyTermAssessment(termAssessment);
                newTermAssessment.setEvaluationTerm(newEvaluationTerm);
                newTermAssessments.add(newTermAssessment);
            }
            newEvaluationTerm.setTermAssessments(newTermAssessments);
        }
        return newEvaluationTerm;
    }

    private TermAssessment copyTermAssessment(TermAssessment existingTermAssessment) {
        TermAssessment newTermAssessment = new TermAssessment();
        newTermAssessment.setName(existingTermAssessment.getName());
        newTermAssessment.setDisplayName(existingTermAssessment.getDisplayName());
        newTermAssessment.setDisplayOrder(existingTermAssessment.getDisplayOrder());
        newTermAssessment.setAssessmentMonth(existingTermAssessment.getAssessmentMonth());
        newTermAssessment.setWeightageInAcademicSession(existingTermAssessment.getWeightageInAcademicSession());
        newTermAssessment.setMaxMarks(existingTermAssessment.getMaxMarks());
        return newTermAssessment;
    }

    public EvaluationScheme getExamPattern(Long examPatternId) {
        return this.examPatternDAO.getExamPattern(examPatternId);
    }

    public void deleteExamPattern(Long examPatternId) {
        this.examPatternDAO.deleteExamPattern(examPatternId);
    }
}
