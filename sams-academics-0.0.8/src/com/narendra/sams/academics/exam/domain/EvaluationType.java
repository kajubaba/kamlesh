package com.narendra.sams.academics.exam.domain;

import com.narendra.sams.academics.domain.GradeScale;
import java.util.HashSet;
import java.util.Set;

public class EvaluationType {
    private Set<CoScholasticActivity> coScholasticActivities;
    private String description;
    private String displayName;
    private EvaluationScheme evaluationScheme;
    private Set<EvaluationTerm> evaluationTerms;
    private String gradeCalculationMethod;
    private GradeScale gradeScale;
    private Long id;
    private Boolean isFASABasedExam;
    private Boolean isIndicatorBased;
    private Boolean isScholastic;
    private Boolean isSkillBased;
    private Boolean isTermBasedAssessment;
    private String name;
    private String scoringMethod;
    private Boolean useAssessmentWeightageAsMaxMarks;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EvaluationScheme getEvaluationScheme() {
        return this.evaluationScheme;
    }

    public void setEvaluationScheme(EvaluationScheme evaluationScheme) {
        this.evaluationScheme = evaluationScheme;
    }

    public Boolean getIsScholastic() {
        return this.isScholastic;
    }

    public void setIsScholastic(Boolean isScholastic) {
        this.isScholastic = isScholastic;
    }

    public Set<EvaluationTerm> getEvaluationTerms() {
        return this.evaluationTerms;
    }

    public void setEvaluationTerms(Set<EvaluationTerm> evaluationTerms) {
        this.evaluationTerms = evaluationTerms;
    }

    public Set<CoScholasticActivity> getCoScholasticActivities() {
        if (this.coScholasticActivities == null) {
            return null;
        }
        Set<CoScholasticActivity> activities = new HashSet();
        for (CoScholasticActivity coScholasticActivity : this.coScholasticActivities) {
            if (coScholasticActivity.getParentActivity() == null) {
                activities.add(coScholasticActivity);
            }
        }
        return activities;
    }

    public void setCoScholasticActivities(Set<CoScholasticActivity> coScholasticActivities) {
        this.coScholasticActivities = coScholasticActivities;
    }

    public Boolean getIsIndicatorBased() {
        return this.isIndicatorBased;
    }

    public void setIsIndicatorBased(Boolean isIndicatorBased) {
        this.isIndicatorBased = isIndicatorBased;
    }

    public Boolean getIsSkillBased() {
        return this.isSkillBased;
    }

    public void setIsSkillBased(Boolean isSkillBased) {
        this.isSkillBased = isSkillBased;
    }

    public GradeScale getGradeScale() {
        return this.gradeScale;
    }

    public void setGradeScale(GradeScale gradeScale) {
        this.gradeScale = gradeScale;
    }

    public String getScoringMethod() {
        return this.scoringMethod;
    }

    public void setScoringMethod(String scoringMethod) {
        this.scoringMethod = scoringMethod;
    }

    public String getGradeCalculationMethod() {
        return this.gradeCalculationMethod;
    }

    public void setGradeCalculationMethod(String gradeCalculationMethod) {
        this.gradeCalculationMethod = gradeCalculationMethod;
    }

    public Boolean getIsFASABasedExam() {
        return this.isFASABasedExam;
    }

    public void setIsFASABasedExam(Boolean isFASABasedExam) {
        this.isFASABasedExam = isFASABasedExam;
    }

    public Boolean getIsTermBasedAssessment() {
        return this.isTermBasedAssessment;
    }

    public void setIsTermBasedAssessment(Boolean isTermBasedAssessment) {
        this.isTermBasedAssessment = isTermBasedAssessment;
    }

    public Boolean getUseAssessmentWeightageAsMaxMarks() {
        return this.useAssessmentWeightageAsMaxMarks;
    }

    public void setUseAssessmentWeightageAsMaxMarks(Boolean useAssessmentWeightageAsMaxMarks) {
        this.useAssessmentWeightageAsMaxMarks = useAssessmentWeightageAsMaxMarks;
    }
}
