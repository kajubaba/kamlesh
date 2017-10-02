package com.narendra.sams.academics.service.impl;

import com.narendra.sams.academics.dao.ExamPatternDAO;
import com.narendra.sams.academics.dao.StudentCoScholasticExamDAO;
import com.narendra.sams.academics.domain.AssessmentActivity;
import com.narendra.sams.academics.domain.AssessmentCriteria;
import com.narendra.sams.academics.domain.AssessmentName;
import com.narendra.sams.academics.domain.AssessmentSkill;
import com.narendra.sams.academics.domain.AssessmentSubject;
import com.narendra.sams.academics.domain.AssessmentTerm;
import com.narendra.sams.academics.domain.ClassSubject;
import com.narendra.sams.academics.domain.CoScholasticAssessment;
import com.narendra.sams.academics.domain.ConversionRule;
import com.narendra.sams.academics.domain.GradeConversionMethod;
import com.narendra.sams.academics.domain.GradeScale;
import com.narendra.sams.academics.domain.GradeScalePoint;
import com.narendra.sams.academics.domain.ScholasticAssessment;
import com.narendra.sams.academics.domain.ScoreCard;
import com.narendra.sams.academics.domain.ScoringMethod;
import com.narendra.sams.academics.exam.domain.ActivitySkill;
import com.narendra.sams.academics.exam.domain.CoScholasticActivity;
import com.narendra.sams.academics.exam.domain.EvaluationScheme;
import com.narendra.sams.academics.exam.domain.EvaluationTerm;
import com.narendra.sams.academics.exam.domain.EvaluationType;
import com.narendra.sams.academics.exam.domain.ScholasticScore;
import com.narendra.sams.academics.exam.domain.SkillIndicator;
import com.narendra.sams.academics.exam.domain.SkillIndicatorGradePointMap;
import com.narendra.sams.academics.exam.domain.StudentCoScholasticScore;
import com.narendra.sams.academics.exam.domain.TermAssessment;
import com.narendra.sams.academics.service.ClassSubjectService;
import com.narendra.sams.academics.service.ExamPatternClassService;
import com.narendra.sams.academics.service.ScholasticScoreService;
import com.narendra.sams.academics.service.ScoreCardService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;

public class ScoreCardServiceImpl implements ScoreCardService {
    private ClassSubjectService classSubjectService;
    private ExamPatternClassService examPatternClassService;
    private ExamPatternDAO examPatternDAO;
    private ScholasticScoreService scholasticScoreService;
    private StudentCoScholasticExamDAO studentCoScholasticExamDAO;

    public ExamPatternDAO getExamPatternDAO() {
        return this.examPatternDAO;
    }

    public void setExamPatternDAO(ExamPatternDAO examPatternDAO) {
        this.examPatternDAO = examPatternDAO;
    }

    public StudentCoScholasticExamDAO getStudentCoScholasticExamDAO() {
        return this.studentCoScholasticExamDAO;
    }

    public void setStudentCoScholasticExamDAO(StudentCoScholasticExamDAO studentCoScholasticExamDAO) {
        this.studentCoScholasticExamDAO = studentCoScholasticExamDAO;
    }

    public ExamPatternClassService getExamPatternClassService() {
        return this.examPatternClassService;
    }

    public void setExamPatternClassService(ExamPatternClassService examPatternClassService) {
        this.examPatternClassService = examPatternClassService;
    }

    public ClassSubjectService getClassSubjectService() {
        return this.classSubjectService;
    }

    public void setClassSubjectService(ClassSubjectService classSubjectService) {
        this.classSubjectService = classSubjectService;
    }

    public ScholasticScoreService getScholasticScoreService() {
        return this.scholasticScoreService;
    }

    public void setScholasticScoreService(ScholasticScoreService scholasticScoreService) {
        this.scholasticScoreService = scholasticScoreService;
    }

    public ScoreCard getBlankScoreCard(Long examPatternId) {
        EvaluationScheme evaluationScheme = this.examPatternDAO.getExamPattern(examPatternId);
        if (evaluationScheme == null) {
            return null;
        }
        EvaluationType coScholasticAssessment = null;
        EvaluationType scholasticAssessment = null;
        for (EvaluationType evaluationType : evaluationScheme.getEvaluationTypes()) {
            if (!evaluationType.getIsScholastic().booleanValue()) {
                coScholasticAssessment = evaluationType;
                break;
            }
        }
        for (EvaluationType evaluationType2 : evaluationScheme.getEvaluationTypes()) {
            if (evaluationType2.getIsScholastic().booleanValue()) {
                scholasticAssessment = evaluationType2;
                break;
            }
        }
        ScoreCard scoreCard = new ScoreCard();
        if (!(scholasticAssessment == null || scholasticAssessment.getEvaluationTerms() == null)) {
            prepareBlankScholasticAssessment(scholasticAssessment, scoreCard);
        }
        if (coScholasticAssessment == null || coScholasticAssessment.getEvaluationTerms() == null) {
            return scoreCard;
        }
        prepareBlankCoScholasticAssessment(coScholasticAssessment, scoreCard);
        return scoreCard;
    }

    public ScoreCard getBlankScholasticScoreCard(Long examPatternId) {
        EvaluationScheme evaluationScheme = this.examPatternDAO.getExamPattern(examPatternId);
        if (evaluationScheme == null) {
            return null;
        }
        EvaluationType scholasticAssessment = null;
        for (EvaluationType evaluationType : evaluationScheme.getEvaluationTypes()) {
            if (evaluationType.getIsScholastic().booleanValue()) {
                scholasticAssessment = evaluationType;
                break;
            }
        }
        ScoreCard scoreCard = new ScoreCard();
        if (scholasticAssessment == null || scholasticAssessment.getEvaluationTerms() == null) {
            return scoreCard;
        }
        prepareBlankScholasticAssessment(scholasticAssessment, scoreCard);
        return scoreCard;
    }

    private ScoreCard getBlankScholasticScoreCardOfOneEvaluationTerm(Long examPatternId, Long evaluationTermId) {
        EvaluationScheme evaluationScheme = this.examPatternDAO.getExamPattern(examPatternId);
        if (evaluationScheme == null) {
            return null;
        }
        EvaluationType scholasticAssessment = null;
        for (EvaluationType evaluationType : evaluationScheme.getEvaluationTypes()) {
            if (evaluationType.getIsScholastic().booleanValue()) {
                scholasticAssessment = evaluationType;
                break;
            }
        }
        ScoreCard scoreCard = new ScoreCard();
        if (scholasticAssessment == null || scholasticAssessment.getEvaluationTerms() == null) {
            return scoreCard;
        }
        prepareBlankScholasticAssessmentOfOneEvaluationTerm(scholasticAssessment, scoreCard, evaluationTermId);
        return scoreCard;
    }

    private void prepareBlankCoScholasticAssessment(EvaluationType coScholasticAssessment, ScoreCard scoreCard) {
        scoreCard.setCoScholasticAssessment(new CoScholasticAssessment());
        scoreCard.getCoScholasticAssessment().setGradeConversionMethod(coScholasticAssessment.getGradeCalculationMethod());
        if (!(scoreCard.getCoScholasticAssessment().getGradeConversionMethod() == null || coScholasticAssessment.getGradeScale().getGradeScalePoints() == null || coScholasticAssessment.getGradeScale().getGradeScalePoints().isEmpty())) {
            List<ConversionRule> conversionRules = new ArrayList();
            for (GradeScalePoint gradeScalePoint : coScholasticAssessment.getGradeScale().getGradeScalePoints()) {
                ConversionRule conversionRule = new ConversionRule();
                if (GradeConversionMethod.AVERAGE.equals(scoreCard.getCoScholasticAssessment().getGradeConversionMethod())) {
                    if (gradeScalePoint.getGradPointFrom() != null) {
                        conversionRule.setFrom(gradeScalePoint.getGradPointFrom().floatValue());
                    }
                    if (gradeScalePoint.getGradPointTo() != null) {
                        conversionRule.setTo(gradeScalePoint.getGradPointTo().floatValue());
                    }
                } else if (GradeConversionMethod.MARKS_TO_GRADE.equals(scoreCard.getCoScholasticAssessment().getGradeConversionMethod())) {
                    if (gradeScalePoint.getMarksFrom() != null) {
                        conversionRule.setFrom(gradeScalePoint.getMarksFrom().floatValue());
                    }
                    if (gradeScalePoint.getMarksTo() != null) {
                        conversionRule.setTo(gradeScalePoint.getMarksTo().floatValue());
                    }
                }
                conversionRule.setGradeMeaning(gradeScalePoint.getGradeMeaning());
                conversionRule.setGrade(gradeScalePoint.getGrade());
                conversionRule.setDisplayOrder(gradeScalePoint.getDisplaySeqNo());
                conversionRules.add(conversionRule);
            }
            Collections.sort(conversionRules, new BeanComparator("displayOrder", new NullComparator()));
            scoreCard.getCoScholasticAssessment().setConversionRules(conversionRules);
        }
        if (ScoringMethod.MARKS_BASED.equals(coScholasticAssessment.getScoringMethod())) {
            scoreCard.getCoScholasticAssessment().setIsMarkBasedAssessment(Boolean.TRUE);
        } else {
            scoreCard.getCoScholasticAssessment().setIsMarkBasedAssessment(Boolean.FALSE);
        }
        List<AssessmentName> assessmentNames = new ArrayList();
        for (EvaluationTerm evaluationTerm : coScholasticAssessment.getEvaluationTerms()) {
            AssessmentName assessmentName = new AssessmentName();
            assessmentName.setId(evaluationTerm.getId());
            if (evaluationTerm.getTermDisplayName() != null) {
                assessmentName.setName(evaluationTerm.getTermDisplayName());
            } else {
                assessmentName.setName(evaluationTerm.getTermName());
            }
            assessmentName.setDisplayOrder(evaluationTerm.getDisplayOrder());
            assessmentNames.add(assessmentName);
        }
        Collections.sort(assessmentNames, new BeanComparator("displayOrder", new NullComparator()));
        scoreCard.getCoScholasticAssessment().setAssessmentNames(assessmentNames);
        scoreCard.getCoScholasticAssessment().setAssessmentActivities(prepareAssessmentActivities(coScholasticAssessment.getCoScholasticActivities(), assessmentNames, scoreCard.getCoScholasticAssessment().getIsMarkBasedAssessment()));
    }

    private void prepareBlankScholasticAssessment(EvaluationType scholasticAssessment, ScoreCard scoreCard) {
        scoreCard.setScholasticAssessment(new ScholasticAssessment());
        scoreCard.getScholasticAssessment().setTermBasedAssessment(scholasticAssessment.getIsTermBasedAssessment());
        scoreCard.getScholasticAssessment().setIsFASABasedAssessment(scholasticAssessment.getIsFASABasedExam());
        if (scholasticAssessment.getEvaluationTerms() == null) {
            return;
        }
        if (scholasticAssessment.getEvaluationTerms() == null || !scholasticAssessment.getEvaluationTerms().isEmpty()) {
            scoreCard.getScholasticAssessment().setConversionRules(prepareConversionRules(scholasticAssessment.getGradeScale()));
            List<AssessmentTerm> assessmentTerms = new ArrayList();
            Long termCount = Long.valueOf(0);
            for (EvaluationTerm evaluationTerm : scholasticAssessment.getEvaluationTerms()) {
                if (evaluationTerm.getTermAssessments() != null && (evaluationTerm.getTermAssessments() == null || !evaluationTerm.getTermAssessments().isEmpty())) {
                    AssessmentTerm assessmentTerm = prepareAssessmentTerm(evaluationTerm, scholasticAssessment.getIsFASABasedExam(), termCount);
                    if (assessmentTerm != null) {
                        assessmentTerms.add(assessmentTerm);
                    }
                    termCount = Long.valueOf(termCount.longValue() + 1);
                }
            }
            Collections.sort(assessmentTerms, new BeanComparator("displayOrder", new NullComparator()));
            if (scholasticAssessment.getIsFASABasedExam().booleanValue()) {
                AssessmentTerm arbitraryAssessmentTerm = createArbitraryTerm(assessmentTerms, termCount);
                if (arbitraryAssessmentTerm != null) {
                    assessmentTerms.add(arbitraryAssessmentTerm);
                }
            }
            scoreCard.getScholasticAssessment().setAssessmentTerms(assessmentTerms);
            scoreCard.getScholasticAssessment().setAssessmentSubjects(prepareDummySubjects(scoreCard.getScholasticAssessment()));
        }
    }

    private void prepareBlankScholasticAssessmentOfOneEvaluationTerm(EvaluationType scholasticAssessment, ScoreCard scoreCard, Long evaluationTermId) {
        scoreCard.setScholasticAssessment(new ScholasticAssessment());
        scoreCard.getScholasticAssessment().setTermBasedAssessment(scholasticAssessment.getIsTermBasedAssessment());
        scoreCard.getScholasticAssessment().setIsFASABasedAssessment(scholasticAssessment.getIsFASABasedExam());
        if (scholasticAssessment.getEvaluationTerms() == null) {
            return;
        }
        if (scholasticAssessment.getEvaluationTerms() == null || !scholasticAssessment.getEvaluationTerms().isEmpty()) {
            scoreCard.getScholasticAssessment().setConversionRules(prepareConversionRules(scholasticAssessment.getGradeScale()));
            List<AssessmentTerm> assessmentTerms = new ArrayList();
            EvaluationTerm selectedEvaluationTerm = null;
            for (EvaluationTerm evaluationTerm : scholasticAssessment.getEvaluationTerms()) {
                if (evaluationTermId.equals(evaluationTerm.getId())) {
                    selectedEvaluationTerm = evaluationTerm;
                    break;
                }
            }
            if (!(selectedEvaluationTerm == null || selectedEvaluationTerm.getTermAssessments() == null || (selectedEvaluationTerm.getTermAssessments() != null && selectedEvaluationTerm.getTermAssessments().isEmpty()))) {
                AssessmentTerm assessmentTerm = prepareAssessmentTerm(selectedEvaluationTerm, scholasticAssessment.getIsFASABasedExam(), Long.valueOf(0));
                if (assessmentTerm != null) {
                    assessmentTerms.add(assessmentTerm);
                }
            }
            Collections.sort(assessmentTerms, new BeanComparator("displayOrder", new NullComparator()));
            scoreCard.getScholasticAssessment().setAssessmentTerms(assessmentTerms);
            scoreCard.getScholasticAssessment().setAssessmentSubjects(prepareDummySubjects(scoreCard.getScholasticAssessment()));
        }
    }

    private AssessmentTerm prepareAssessmentTerm(EvaluationTerm evaluationTerm, Boolean isFASABasedAssessment, Long termCount) {
        if (evaluationTerm == null) {
            return null;
        }
        AssessmentTerm assessmentTerm = new AssessmentTerm();
        assessmentTerm.setId(evaluationTerm.getId());
        if (evaluationTerm.getTermDisplayName() != null) {
            assessmentTerm.setName(evaluationTerm.getTermDisplayName());
        } else {
            assessmentTerm.setName(evaluationTerm.getTermName());
        }
        assessmentTerm.setDisplayOrder(evaluationTerm.getDisplayOrder());
        if (evaluationTerm.getTermAssessments() == null || evaluationTerm.getTermAssessments().isEmpty()) {
            return assessmentTerm;
        }
        List<AssessmentName> assessmentNames = new ArrayList();
        for (TermAssessment termAssessment : evaluationTerm.getTermAssessments()) {
            AssessmentName assessmentName = new AssessmentName();
            assessmentName.setId(termAssessment.getId());
            assessmentName.setAssessmentTermId(assessmentTerm.getId());
            assessmentName.setMaxMarks(termAssessment.getMaxMarks());
            assessmentName.setWeightageInFinalExam(termAssessment.getWeightageInAcademicSession());
            if (termAssessment.getDisplayName() != null) {
                assessmentName.setName(termAssessment.getDisplayName());
            } else {
                assessmentName.setName(termAssessment.getName());
            }
            assessmentName.setDisplayOrder(termAssessment.getDisplayOrder());
            assessmentNames.add(assessmentName);
        }
        BeanComparator beanComparator = new BeanComparator("displayOrder", new NullComparator());
        Collections.sort(assessmentNames, beanComparator);
        if (isFASABasedAssessment.booleanValue()) {
            AssessmentName extraAassessmentName = new AssessmentName();
            extraAassessmentName.setId(prepareUniqueId(termCount));
            extraAassessmentName.setAssessmentTermId(assessmentTerm.getId());
            extraAassessmentName.setDisplayOrder(Long.valueOf((long) (evaluationTerm.getTermAssessments().size() + 1)));
            extraAassessmentName.setIsArbitrary(Boolean.TRUE);
            int count = 0;
            Long arbitraryAssessmentWeightage = Long.valueOf(0);
            for (AssessmentName assessmentName2 : assessmentNames) {
                if (count == 0) {
                    extraAassessmentName.setName(assessmentName2.getName());
                } else {
                    extraAassessmentName.setName(extraAassessmentName.getName() + "+" + assessmentName2.getName());
                }
                arbitraryAssessmentWeightage = Long.valueOf(arbitraryAssessmentWeightage.longValue() + assessmentName2.getWeightageInFinalExam().longValue());
                count++;
            }
            extraAassessmentName.setWeightageInFinalExam(arbitraryAssessmentWeightage);
            assessmentNames.add(extraAassessmentName);
            Collections.sort(assessmentNames, beanComparator);
        }
        assessmentTerm.setAssessmentNames(assessmentNames);
        return assessmentTerm;
    }

    private List<AssessmentSubject> prepareDummySubjects(ScholasticAssessment scholasticAssessment) {
        List<AssessmentSubject> list = null;
        if (!(scholasticAssessment == null || scholasticAssessment.getAssessmentTerms() == null || scholasticAssessment.getAssessmentTerms().isEmpty())) {
            list = new ArrayList();
            for (int i = 0; i < 5; i++) {
                AssessmentSubject assessmentSubject = new AssessmentSubject();
                assessmentSubject.setName("Subject-" + (i + 1));
                assessmentSubject.setDisplayOrder(Long.valueOf((long) (i + 1)));
                for (AssessmentTerm assessmentTerm : scholasticAssessment.getAssessmentTerms()) {
                    if (!(assessmentTerm.getAssessmentNames() == null || assessmentTerm.getAssessmentNames().isEmpty())) {
                        for (AssessmentName assessmentName : assessmentTerm.getAssessmentNames()) {
                            assessmentSubject.getGrades().add("");
                        }
                    }
                }
                list.add(assessmentSubject);
            }
        }
        return list;
    }

    private List<ConversionRule> prepareConversionRules(GradeScale gradeScale) {
        List<ConversionRule> list = null;
        if (!(gradeScale == null || gradeScale.getGradeScalePoints() == null || gradeScale.getGradeScalePoints().isEmpty())) {
            list = new ArrayList();
            for (GradeScalePoint gradeScalePoint : gradeScale.getGradeScalePoints()) {
                ConversionRule conversionRule = new ConversionRule();
                if (gradeScalePoint.getMarksFrom() != null) {
                    conversionRule.setFrom(gradeScalePoint.getMarksFrom().floatValue());
                }
                if (gradeScalePoint.getMarksTo() != null) {
                    conversionRule.setTo(gradeScalePoint.getMarksTo().floatValue());
                }
                conversionRule.setGrade(gradeScalePoint.getGrade());
                conversionRule.setGradePoint(gradeScalePoint.getGradePoint());
                conversionRule.setGradeMeaning(gradeScalePoint.getGradeMeaning());
                conversionRule.setDisplayOrder(gradeScalePoint.getDisplaySeqNo());
                list.add(conversionRule);
            }
            Collections.sort(list, new BeanComparator("displayOrder", new NullComparator()));
        }
        return list;
    }

    private AssessmentTerm createArbitraryTerm(List<AssessmentTerm> assessmentTerms, Long termCount) {
        if (assessmentTerms == null || (assessmentTerms != null && assessmentTerms.isEmpty())) {
            return null;
        }
        AssessmentTerm arbitraryAssessmentTerm = new AssessmentTerm();
        arbitraryAssessmentTerm.setId(Long.valueOf(-1));
        arbitraryAssessmentTerm.setDisplayOrder(Long.valueOf((long) (assessmentTerms.size() + 1)));
        arbitraryAssessmentTerm.setIsArbitrary(Boolean.TRUE);
        int count = 0;
        for (AssessmentTerm assessmentTerm : assessmentTerms) {
            if (count == 0) {
                arbitraryAssessmentTerm.setName(assessmentTerm.getName());
            } else {
                arbitraryAssessmentTerm.setName(arbitraryAssessmentTerm.getName() + "+" + assessmentTerm.getName());
            }
            count++;
        }
        arbitraryAssessmentTerm.setAssessmentNames(new ArrayList());
        AssessmentName overallFA = new AssessmentName();
        overallFA.setId(prepareUniqueId(termCount));
        overallFA.setAssessmentTermId(arbitraryAssessmentTerm.getId());
        overallFA.setName("FA");
        overallFA.setDisplayOrder(Long.valueOf(1));
        arbitraryAssessmentTerm.getAssessmentNames().add(overallFA);
        AssessmentName overallSA = new AssessmentName();
        overallSA.setId(prepareUniqueId(Long.valueOf(termCount.longValue() + 1)));
        overallSA.setAssessmentTermId(arbitraryAssessmentTerm.getId());
        overallSA.setName("SA");
        overallSA.setDisplayOrder(Long.valueOf(2));
        arbitraryAssessmentTerm.getAssessmentNames().add(overallSA);
        AssessmentName overallGrade = new AssessmentName();
        overallGrade.setId(prepareUniqueId(Long.valueOf(termCount.longValue() + 2)));
        overallGrade.setAssessmentTermId(arbitraryAssessmentTerm.getId());
        overallGrade.setName("Overlall Grade");
        overallGrade.setDisplayOrder(Long.valueOf(3));
        arbitraryAssessmentTerm.getAssessmentNames().add(overallGrade);
        return arbitraryAssessmentTerm;
    }

    private Long prepareUniqueId(Long count) {
        return Long.valueOf(-1 - count.longValue());
    }

    public ScoreCard getStudentScoreCard(Long studentId, Long studentClassId) {
        EvaluationScheme evaluationScheme = this.examPatternClassService.getExamPatternOfClass(studentClassId);
        if (evaluationScheme == null) {
            return null;
        }
        ScoreCard scoreCard = getBlankScoreCard(evaluationScheme.getId());
        prepareStudentCoScholasticScoreCard(scoreCard, studentId, studentClassId);
        prepareStudentScholasticScoreCard(scoreCard, studentId, studentClassId);
        return scoreCard;
    }

    public ScoreCard getStudentScholasticScoreCard(Long studentId, Long studentClassId) {
        EvaluationScheme evaluationScheme = this.examPatternClassService.getExamPatternOfClass(studentClassId);
        if (evaluationScheme == null) {
            return null;
        }
        ScoreCard scoreCard = getBlankScholasticScoreCard(evaluationScheme.getId());
        prepareStudentScholasticScoreCard(scoreCard, studentId, studentClassId);
        return scoreCard;
    }

    public ScoreCard getStudentScholasticScoreCardOfOneTerm(Long studentId, Long studentClassId, Long evaluationTermId) {
        EvaluationScheme evaluationScheme = this.examPatternClassService.getExamPatternOfClass(studentClassId);
        if (evaluationScheme == null) {
            return null;
        }
        ScoreCard scoreCard = getBlankScholasticScoreCardOfOneEvaluationTerm(evaluationScheme.getId(), evaluationTermId);
        prepareStudentScholasticScoreCard(scoreCard, studentId, studentClassId);
        return scoreCard;
    }

    private void prepareStudentScholasticScoreCardOfOneTerm(ScoreCard scoreCard, Long studentId, Long studentClassId, Long evaluationTermId) {
        scoreCard.getScholasticAssessment().setAssessmentSubjects(null);
        List<ClassSubject> classSubjects = this.classSubjectService.getExamClassSubjects(studentClassId);
        if (classSubjects != null && !classSubjects.isEmpty()) {
            List<AssessmentSubject> assessmentSubjects = new ArrayList();
            for (ClassSubject classSubject : classSubjects) {
                Float arbitraryAssessmentMarks;
                Long weightageSum;
                String grade;
                AssessmentSubject assessmentSubject = new AssessmentSubject();
                assessmentSubject.setId(classSubject.getId());
                assessmentSubject.setName(classSubject.getSubjectName());
                assessmentSubject.setAssessmentNames(new ArrayList());
                AssessmentTerm arbitraryAssessmentTerm = null;
                for (AssessmentTerm assessmentTerm : scoreCard.getScholasticAssessment().getAssessmentTerms()) {
                    if (!assessmentTerm.getIsArbitrary().booleanValue()) {
                        if (assessmentTerm.getAssessmentNames() == null || (assessmentTerm.getAssessmentNames() != null && assessmentTerm.getAssessmentNames().isEmpty())) {
                            break;
                        }
                        AssessmentName arbitrayAssessmentName = null;
                        for (AssessmentName assessmentName : assessmentTerm.getAssessmentNames()) {
                            AssessmentName subjectAssessment = new AssessmentName();
                            subjectAssessment.setId(assessmentName.getId());
                            subjectAssessment.setAssessmentTermId(assessmentTerm.getId());
                            subjectAssessment.setName(assessmentName.getName());
                            subjectAssessment.setDisplayOrder(assessmentName.getDisplayOrder());
                            subjectAssessment.setIsArbitrary(assessmentName.getIsArbitrary());
                            subjectAssessment.setIsDefault(assessmentName.getIsDefault());
                            if (subjectAssessment.getIsArbitrary().booleanValue()) {
                                arbitrayAssessmentName = subjectAssessment;
                                assessmentSubject.getAssessmentNames().add(subjectAssessment);
                            } else {
                                ScholasticScore scholasticScore = this.scholasticScoreService.getStudnetScholasticScore(studentId, studentClassId, subjectAssessment.getId(), assessmentSubject.getId());
                                if (scholasticScore != null) {
                                    subjectAssessment.setMarks(convertMarksOutOfHundred(scholasticScore.getMarksObtained(), assessmentName.getMaxMarks()));
                                }
                                assessmentSubject.getAssessmentNames().add(subjectAssessment);
                            }
                        }
                        if (scoreCard.getScholasticAssessment().getIsFASABasedAssessment().booleanValue()) {
                            arbitraryAssessmentMarks = null;
                            weightageSum = Long.valueOf(0);
                            for (AssessmentName assessmentName2 : assessmentSubject.getAssessmentNames()) {
                                if (!(assessmentName2.getIsArbitrary().booleanValue() || !assessmentTerm.getId().equals(assessmentName2.getAssessmentTermId()) || assessmentName2.getMarks() == null)) {
                                    if (arbitraryAssessmentMarks == null) {
                                        arbitraryAssessmentMarks = Float.valueOf(0.0f);
                                    }
                                    arbitraryAssessmentMarks = Float.valueOf(arbitraryAssessmentMarks.floatValue() + calcualtePercentageOfMarks(assessmentName2.getMarks(), assessmentName2.getWeightageInFinalExam()).floatValue());
                                    weightageSum = Long.valueOf(weightageSum.longValue() + assessmentName2.getWeightageInFinalExam().longValue());
                                }
                            }
                            if (!(arbitraryAssessmentMarks == null || arbitraryAssessmentMarks.floatValue() == 0.0f)) {
                                arbitraryAssessmentMarks = Float.valueOf(arbitraryAssessmentMarks.floatValue() * ((float) (100 / weightageSum.longValue())));
                            }
                            arbitrayAssessmentName.setMarks(arbitraryAssessmentMarks);
                        }
                        for (AssessmentName assessmentName22 : assessmentSubject.getAssessmentNames()) {
                            grade = convertIntoGrade(assessmentName22.getMarks(), scoreCard.getScholasticAssessment().getConversionRules());
                            assessmentSubject.getScoreMarksMap().put(assessmentName22.getId(), assessmentName22.getMarks());
                            assessmentSubject.getScoreGradeMap().put(assessmentName22.getId(), grade);
                        }
                    } else {
                        arbitraryAssessmentTerm = assessmentTerm;
                    }
                }
                if (scoreCard.getScholasticAssessment().getIsFASABasedAssessment().booleanValue()) {
                    arbitraryAssessmentMarks = null;
                    weightageSum = Long.valueOf(0);
                    for (AssessmentName assessmentName222 : assessmentSubject.getAssessmentNames()) {
                        if (!(assessmentName222.getIsArbitrary().booleanValue() || assessmentName222.getMarks() == null)) {
                            if (arbitraryAssessmentMarks == null) {
                                arbitraryAssessmentMarks = Float.valueOf(0.0f);
                            }
                            arbitraryAssessmentMarks = Float.valueOf(arbitraryAssessmentMarks.floatValue() + ((assessmentName222.getMarks().floatValue() * ((float) assessmentName222.getWeightageInFinalExam().longValue())) / 100.0f));
                            weightageSum = Long.valueOf(weightageSum.longValue() + assessmentName222.getWeightageInFinalExam().longValue());
                        }
                    }
                    if (!(arbitraryAssessmentMarks == null || arbitraryAssessmentMarks.floatValue() == 0.0f)) {
                        arbitraryAssessmentMarks = Float.valueOf(arbitraryAssessmentMarks.floatValue() * ((float) (100 / weightageSum.longValue())));
                    }
                    ((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(0)).setMarks(arbitraryAssessmentMarks);
                    grade = convertIntoGrade(((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(0)).getMarks(), scoreCard.getScholasticAssessment().getConversionRules());
                    assessmentSubject.getScoreMarksMap().put(((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(0)).getId(), ((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(0)).getMarks());
                    assessmentSubject.getScoreGradeMap().put(((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(0)).getId(), grade);
                }
                assessmentSubjects.add(assessmentSubject);
            }
            scoreCard.getScholasticAssessment().setAssessmentSubjects(assessmentSubjects);
        }
    }

    private void prepareStudentScholasticScoreCard(ScoreCard scoreCard, Long studentId, Long studentClassId) {
        scoreCard.getScholasticAssessment().setAssessmentSubjects(null);
        List<ClassSubject> classSubjects = this.classSubjectService.getExamClassSubjects(studentClassId);
        if (classSubjects != null && !classSubjects.isEmpty()) {
            List<AssessmentSubject> assessmentSubjects = new ArrayList();
            for (ClassSubject classSubject : classSubjects) {
                String grade;
                AssessmentSubject assessmentSubject = new AssessmentSubject();
                assessmentSubject.setId(classSubject.getId());
                assessmentSubject.setName(classSubject.getSubjectName());
                assessmentSubject.setAssessmentNames(new ArrayList());
                AssessmentTerm arbitraryAssessmentTerm = null;
                for (AssessmentTerm assessmentTerm : scoreCard.getScholasticAssessment().getAssessmentTerms()) {
                    if (!assessmentTerm.getIsArbitrary().booleanValue()) {
                        if (assessmentTerm.getAssessmentNames() == null || (assessmentTerm.getAssessmentNames() != null && assessmentTerm.getAssessmentNames().isEmpty())) {
                            break;
                        }
                        AssessmentName arbitrayAssessmentName = null;
                        for (AssessmentName assessmentName : assessmentTerm.getAssessmentNames()) {
                            AssessmentName subjectAssessment = new AssessmentName();
                            subjectAssessment.setId(assessmentName.getId());
                            subjectAssessment.setAssessmentTermId(assessmentTerm.getId());
                            subjectAssessment.setName(assessmentName.getName());
                            subjectAssessment.setDisplayOrder(assessmentName.getDisplayOrder());
                            subjectAssessment.setIsArbitrary(assessmentName.getIsArbitrary());
                            subjectAssessment.setWeightageInFinalExam(assessmentName.getWeightageInFinalExam());
                            subjectAssessment.setIsDefault(assessmentName.getIsDefault());
                            if (subjectAssessment.getIsArbitrary().booleanValue()) {
                                arbitrayAssessmentName = subjectAssessment;
                                assessmentSubject.getAssessmentNames().add(subjectAssessment);
                            } else {
                                ScholasticScore scholasticScore = this.scholasticScoreService.getStudnetScholasticScore(studentId, studentClassId, subjectAssessment.getId(), assessmentSubject.getId());
                                if (scholasticScore != null) {
                                    subjectAssessment.setMarks(convertMarksOutOfHundred(scholasticScore.getMarksObtained(), assessmentName.getMaxMarks()));
                                }
                                assessmentSubject.getAssessmentNames().add(subjectAssessment);
                            }
                        }
                        if (scoreCard.getScholasticAssessment().getIsFASABasedAssessment().booleanValue()) {
                            arbitrayAssessmentName.setMarks(calculateMarksOfArbitraryAssessmentOfTerm(assessmentSubject.getAssessmentNames(), assessmentTerm.getId()));
                        }
                        for (AssessmentName assessmentName2 : assessmentSubject.getAssessmentNames()) {
                            grade = convertIntoGrade(assessmentName2.getMarks(), scoreCard.getScholasticAssessment().getConversionRules());
                            assessmentSubject.getScoreMarksMap().put(assessmentName2.getId(), assessmentName2.getMarks());
                            assessmentSubject.getScoreGradeMap().put(assessmentName2.getId(), grade);
                        }
                    } else {
                        arbitraryAssessmentTerm = assessmentTerm;
                    }
                }
                if (arbitraryAssessmentTerm != null && scoreCard.getScholasticAssessment().getIsFASABasedAssessment().booleanValue()) {
                    ((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(0)).setMarks(calculateOverallMarks(assessmentSubject.getAssessmentNames(), Boolean.TRUE));
                    String overallFAGrade = convertIntoGrade(((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(0)).getMarks(), scoreCard.getScholasticAssessment().getConversionRules());
                    assessmentSubject.getScoreMarksMap().put(((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(0)).getId(), ((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(0)).getMarks());
                    assessmentSubject.getScoreGradeMap().put(((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(0)).getId(), overallFAGrade);
                    ((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(1)).setMarks(calculateOverallMarks(assessmentSubject.getAssessmentNames(), Boolean.FALSE));
                    String overallSAGrade = convertIntoGrade(((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(1)).getMarks(), scoreCard.getScholasticAssessment().getConversionRules());
                    assessmentSubject.getScoreMarksMap().put(((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(1)).getId(), ((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(1)).getMarks());
                    assessmentSubject.getScoreGradeMap().put(((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(1)).getId(), overallSAGrade);
                    ((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(2)).setMarks(calculateOverallMarks(assessmentSubject.getAssessmentNames(), null));
                    grade = convertIntoGrade(((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(2)).getMarks(), scoreCard.getScholasticAssessment().getConversionRules());
                    assessmentSubject.getScoreMarksMap().put(((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(2)).getId(), ((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(2)).getMarks());
                    assessmentSubject.getScoreGradeMap().put(((AssessmentName) arbitraryAssessmentTerm.getAssessmentNames().get(2)).getId(), grade);
                    assessmentSubject.setGradePoint(convertIntoGradePoint(grade, scoreCard.getScholasticAssessment().getConversionRules()));
                }
                assessmentSubjects.add(assessmentSubject);
            }
            scoreCard.getScholasticAssessment().setAssessmentSubjects(assessmentSubjects);
        }
    }

    private Float calculateOverallMarks(List<AssessmentName> assessmentNames, Boolean isFA) {
        Float arbitraryAssessmentMarks = null;
        Long weightageSum = Long.valueOf(0);
        int index = -1;
        for (AssessmentName assessmentName : assessmentNames) {
            index++;
            if (!assessmentName.getIsArbitrary().booleanValue()) {
                if (isFA != null) {
                    if (isFA.booleanValue()) {
                        if (index != 2) {
                            if (index == 6) {
                            }
                        }
                    } else if (index != 0) {
                        if (index != 1) {
                            if (index != 4) {
                                if (index == 5) {
                                }
                            }
                        }
                    }
                }
                if (assessmentName.getMarks() != null) {
                    if (arbitraryAssessmentMarks == null) {
                        arbitraryAssessmentMarks = Float.valueOf(0.0f);
                    }
                    arbitraryAssessmentMarks = Float.valueOf(arbitraryAssessmentMarks.floatValue() + calcualtePercentageOfMarks(assessmentName.getMarks(), assessmentName.getWeightageInFinalExam()).floatValue());
                }
                weightageSum = Long.valueOf(weightageSum.longValue() + assessmentName.getWeightageInFinalExam().longValue());
            }
        }
        if (arbitraryAssessmentMarks == null || arbitraryAssessmentMarks.floatValue() == 0.0f) {
            return arbitraryAssessmentMarks;
        }
        return Float.valueOf(arbitraryAssessmentMarks.floatValue() * (100.0f / weightageSum.floatValue()));
    }

    private Float calculateMarksOfArbitraryAssessmentOfTerm(List<AssessmentName> assessmentNames, Long termId) {
        Float arbitraryAssessmentMarks = null;
        Long weightageSum = Long.valueOf(0);
        for (AssessmentName assessmentName : assessmentNames) {
            if (!assessmentName.getIsArbitrary().booleanValue() && termId.equals(assessmentName.getAssessmentTermId())) {
                if (assessmentName.getMarks() != null) {
                    if (arbitraryAssessmentMarks == null) {
                        arbitraryAssessmentMarks = Float.valueOf(0.0f);
                    }
                    arbitraryAssessmentMarks = Float.valueOf(arbitraryAssessmentMarks.floatValue() + calcualtePercentageOfMarks(assessmentName.getMarks(), assessmentName.getWeightageInFinalExam()).floatValue());
                }
                weightageSum = Long.valueOf(weightageSum.longValue() + assessmentName.getWeightageInFinalExam().longValue());
            }
        }
        if (arbitraryAssessmentMarks == null || arbitraryAssessmentMarks.floatValue() == 0.0f) {
            return arbitraryAssessmentMarks;
        }
        return Float.valueOf(arbitraryAssessmentMarks.floatValue() * (100.0f / weightageSum.floatValue()));
    }

    private Float calcualtePercentageOfMarks(Float marks, Long percentage) {
        return Float.valueOf((marks.floatValue() * ((float) percentage.longValue())) / 100.0f);
    }

    private Float convertMarksOutOfHundred(Float marks, Long percentage) {
        if (marks == null) {
            return null;
        }
        return Float.valueOf(marks.floatValue() * Float.valueOf(100.0f / percentage.floatValue()).floatValue());
    }

    private void prepareStudentCoScholasticScoreCard(ScoreCard scoreCard, Long studentId, Long studentClassId) {
        if (scoreCard.getCoScholasticAssessment().getAssessmentActivities() != null && !scoreCard.getCoScholasticAssessment().getAssessmentActivities().isEmpty()) {
            for (AssessmentActivity assessmentActivity : scoreCard.getCoScholasticAssessment().getAssessmentActivities()) {
                if (assessmentActivity.getSubActivities() == null || assessmentActivity.getSubActivities().isEmpty()) {
                    prepareStudentScore(assessmentActivity, scoreCard.getCoScholasticAssessment().getIsMarkBasedAssessment(), studentId, studentClassId);
                } else {
                    for (AssessmentActivity subActivity : assessmentActivity.getSubActivities()) {
                        prepareStudentScore(subActivity, scoreCard.getCoScholasticAssessment().getIsMarkBasedAssessment(), studentId, studentClassId);
                    }
                }
            }
            if (GradeConversionMethod.AVERAGE.equals(scoreCard.getCoScholasticAssessment().getGradeConversionMethod()) && scoreCard.getCoScholasticAssessment().getAssessmentActivities() != null && !scoreCard.getCoScholasticAssessment().getAssessmentActivities().isEmpty()) {
                for (AssessmentActivity assessmentActivity2 : scoreCard.getCoScholasticAssessment().getAssessmentActivities()) {
                    if (assessmentActivity2.getSubActivities() == null || assessmentActivity2.getSubActivities().isEmpty()) {
                        calculateAverage(assessmentActivity2, scoreCard.getCoScholasticAssessment().getAssessmentNames(), scoreCard.getCoScholasticAssessment().getConversionRules());
                    } else {
                        for (AssessmentActivity subActivity2 : assessmentActivity2.getSubActivities()) {
                            calculateAverage(subActivity2, scoreCard.getCoScholasticAssessment().getAssessmentNames(), scoreCard.getCoScholasticAssessment().getConversionRules());
                        }
                    }
                }
            }
        }
    }

    private void calculateAverage(AssessmentActivity assessmentActivity, List<AssessmentName> assessmentNames, List<ConversionRule> conversionRules) {
        if (assessmentActivity != null && assessmentActivity.getAssessmentSkills() != null && !assessmentActivity.getAssessmentSkills().isEmpty()) {
            for (AssessmentSkill assessmentSkill : assessmentActivity.getAssessmentSkills()) {
                if (assessmentSkill.getAssessmentCriterias() == null || assessmentSkill.getAssessmentCriterias().isEmpty()) {
                    for (AssessmentName assessmentName : assessmentNames) {
                        assessmentSkill.getScores().add("");
                    }
                } else {
                    for (AssessmentName assessmentName2 : assessmentNames) {
                        float marksSum = 0.0f;
                        for (AssessmentCriteria assessmentCriteria : assessmentSkill.getAssessmentCriterias()) {
                            if (!(assessmentCriteria.getTermMarkBasedScores() == null || assessmentCriteria.getTermMarkBasedScores().isEmpty())) {
                                Long marks = (Long) assessmentCriteria.getTermMarkBasedScores().get(assessmentName2.getId());
                                if (marks != null) {
                                    marksSum += (float) marks.longValue();
                                }
                            }
                        }
                        if (marksSum != 0.0f) {
                            float marksAverage = marksSum / ((float) assessmentSkill.getAssessmentCriterias().size());
                            if (conversionRules != null && !conversionRules.isEmpty()) {
                                for (ConversionRule conversionRule : conversionRules) {
                                    if (conversionRule.getFrom() <= marksAverage && marksAverage <= conversionRule.getTo()) {
                                        assessmentSkill.getScores().add(conversionRule.getGrade());
                                        break;
                                    }
                                }
                            }
                        } else {
                            assessmentSkill.getScores().add("");
                        }
                    }
                }
            }
        }
    }

    private void prepareStudentScore(AssessmentActivity assessmentActivity, Boolean isMarkBasedAssessment, Long studentId, Long studentClassId) {
        if (assessmentActivity != null && assessmentActivity.getAssessmentSkills() != null && !assessmentActivity.getAssessmentSkills().isEmpty()) {
            for (AssessmentSkill assessmentSkill : assessmentActivity.getAssessmentSkills()) {
                if (!(assessmentSkill.getAssessmentCriterias() == null || assessmentSkill.getAssessmentCriterias().isEmpty())) {
                    for (AssessmentCriteria assessmentCriteria : assessmentSkill.getAssessmentCriterias()) {
                        StudentCoScholasticScore studentCoScholasticScore;
                        if (isMarkBasedAssessment.booleanValue()) {
                            if (!(assessmentCriteria.getTermMarkBasedScores() == null || assessmentCriteria.getTermMarkBasedScores().isEmpty())) {
                                for (Long termId : assessmentCriteria.getTermMarkBasedScores().keySet()) {
                                    studentCoScholasticScore = this.studentCoScholasticExamDAO.getStudentCoScholasticScore(studentId, studentClassId, termId, assessmentCriteria.getId());
                                    if (studentCoScholasticScore != null) {
                                        assessmentCriteria.getTermMarkBasedScores().put(termId, studentCoScholasticScore.getMarksObtained());
                                    }
                                }
                            }
                        } else if (!(assessmentCriteria.getTermGradeBasedScores() == null || assessmentCriteria.getTermGradeBasedScores().isEmpty())) {
                            for (Long termId2 : assessmentCriteria.getTermGradeBasedScores().keySet()) {
                                studentCoScholasticScore = this.studentCoScholasticExamDAO.getStudentCoScholasticScore(studentId, studentClassId, termId2, assessmentCriteria.getId());
                                if (studentCoScholasticScore != null) {
                                    if (assessmentSkill.getIsAdditional() != null && assessmentSkill.getIsAdditional().booleanValue()) {
                                        assessmentCriteria.getTermGradeBasedScores().put(termId2, studentCoScholasticScore.getFreeTextValue());
                                    } else if (studentCoScholasticScore.getGradeScalePoint() != null) {
                                        assessmentCriteria.getTermGradeBasedScores().put(termId2, studentCoScholasticScore.getGradeScalePoint().getGrade());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private String convertIntoGrade(Float marks, List<ConversionRule> conversionRules) {
        if (marks == null) {
            return "";
        }
        int roundedMarks = Math.round(marks.floatValue());
        for (ConversionRule conversionRule : conversionRules) {
            if (conversionRule.getFrom() <= ((float) roundedMarks) && ((float) roundedMarks) <= conversionRule.getTo()) {
                return conversionRule.getGrade();
            }
        }
        return "";
    }

    private Float convertIntoGradePoint(String grade, List<ConversionRule> conversionRules) {
        if (grade == null || conversionRules == null) {
            return null;
        }
        for (ConversionRule conversionRule : conversionRules) {
            if (grade.equals(conversionRule.getGrade())) {
                return conversionRule.getGradePoint();
            }
        }
        return null;
    }

    private List<AssessmentActivity> prepareAssessmentActivities(Collection<CoScholasticActivity> coScholasticActivities, List<AssessmentName> assessmentNames, Boolean isMarksBasedAssessment) {
        if (coScholasticActivities == null) {
            return null;
        }
        List<AssessmentActivity> assessmentActivities = new ArrayList();
        for (CoScholasticActivity coScholasticActivity : coScholasticActivities) {
            AssessmentActivity assessmentActivity = new AssessmentActivity();
            assessmentActivity.setId(coScholasticActivity.getId());
            if (coScholasticActivity.getActivityDisplayName() != null) {
                assessmentActivity.setName(coScholasticActivity.getActivityDisplayName());
            } else {
                assessmentActivity.setName(coScholasticActivity.getActivityName());
            }
            assessmentActivity.setDisplayOrder(coScholasticActivity.getDisplaySeqNo());
            if (coScholasticActivity.getSubActivities() != null) {
                assessmentActivity.setSubActivities(prepareAssessmentActivities(coScholasticActivity.getSubActivities(), assessmentNames, isMarksBasedAssessment));
            }
            assessmentActivities.add(assessmentActivity);
            assessmentActivity.setAssessmentSkills(prepareAssessmentSkills(coScholasticActivity.getActivitySkills(), assessmentNames, isMarksBasedAssessment));
        }
        Collections.sort(assessmentActivities, new BeanComparator("displayOrder", new NullComparator()));
        return assessmentActivities;
    }

    private List<AssessmentSkill> prepareAssessmentSkills(Collection<ActivitySkill> skills, List<AssessmentName> assessmentNames, Boolean isMarksBasedAssessment) {
        if (skills == null) {
            return null;
        }
        List<AssessmentSkill> assessmentSkills = new ArrayList();
        for (ActivitySkill activityskill : skills) {
            AssessmentSkill assessmentSkill = new AssessmentSkill();
            assessmentSkill.setId(activityskill.getId());
            if (activityskill.getSkillDisplayName() != null) {
                assessmentSkill.setName(activityskill.getSkillDisplayName());
            } else {
                assessmentSkill.setName(activityskill.getSkillName());
            }
            assessmentSkill.setDoNotDisplayOnScoreCard(activityskill.getDoNotDisplayOnScoreCard());
            assessmentSkill.setIsAdditional(activityskill.getIsAdditional());
            assessmentSkill.setDisplayOrder(activityskill.getDisplaySeqNo());
            assessmentSkill.setAssessmentCriterias(prepareAssessmentCriterias(activityskill.getIndicators(), assessmentNames, isMarksBasedAssessment));
            assessmentSkills.add(assessmentSkill);
        }
        Collections.sort(assessmentSkills, new BeanComparator("displayOrder", new NullComparator()));
        return assessmentSkills;
    }

    private Map<String, String> prepareGradeToIndicatorMap(SkillIndicator skillIndicator) {
        Map<String, String> map = new HashMap();
        if (skillIndicator.getSkillIndicatorGradePointMaps() == null) {
            return null;
        }
        if (skillIndicator.getSkillIndicatorGradePointMaps().isEmpty()) {
            return null;
        }
        for (SkillIndicatorGradePointMap skillGradePointMap : skillIndicator.getSkillIndicatorGradePointMaps()) {
            map.put(skillGradePointMap.getGradeScalePoint().getGrade(), skillGradePointMap.getOverallIndicator());
        }
        return map;
    }

    private List<AssessmentCriteria> prepareAssessmentCriterias(Collection<SkillIndicator> skillIndicators, List<AssessmentName> assessmentNames, Boolean isMarksBasedAssessment) {
        if (skillIndicators == null) {
            return null;
        }
        List<AssessmentCriteria> assessmentCriterias = new ArrayList();
        for (SkillIndicator skillIndicator : skillIndicators) {
            AssessmentCriteria assessmentCriteria = new AssessmentCriteria();
            assessmentCriteria.setId(skillIndicator.getId());
            if (skillIndicator.getDisplayName() != null) {
                assessmentCriteria.setName(skillIndicator.getDisplayName());
            } else {
                assessmentCriteria.setName(skillIndicator.getName());
            }
            assessmentCriteria.setDisplayOrder(skillIndicator.getDisplaySeqNo());
            if (assessmentNames != null) {
                if (isMarksBasedAssessment.booleanValue()) {
                    for (AssessmentName assessmentName : assessmentNames) {
                        assessmentCriteria.getTermMarkBasedScores().put(assessmentName.getId(), null);
                    }
                } else {
                    for (AssessmentName assessmentName2 : assessmentNames) {
                        assessmentCriteria.getTermGradeBasedScores().put(assessmentName2.getId(), null);
                    }
                }
            }
            assessmentCriteria.setGradeToIndicatorMap(prepareGradeToIndicatorMap(skillIndicator));
            assessmentCriterias.add(assessmentCriteria);
        }
        Collections.sort(assessmentCriterias, new BeanComparator("displayOrder", new NullComparator()));
        return assessmentCriterias;
    }

    public static void main(String[] args) {
        Float marks = Float.valueOf(22.0f);
        Float mult = Float.valueOf(100.0f / ((float) Long.valueOf(30).longValue()));
        System.out.println(mult);
        System.out.println(Float.valueOf(marks.floatValue() * mult.floatValue()));
    }
}
