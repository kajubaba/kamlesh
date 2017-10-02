package com.narendra.sams.web.restws.academics.exam.vo;

import com.narendra.sams.academics.exam.domain.coscholastic.Activity;
import java.util.List;

public class CoScholasticScoreCollectionVO {
    private List<Activity> activities;
    private AssessmentTypeVO assessmentTypeVO;
    private List<GradeScalePointVO> gradeScalePointVOs;

    public AssessmentTypeVO getAssessmentTypeVO() {
        return this.assessmentTypeVO;
    }

    public void setAssessmentTypeVO(AssessmentTypeVO assessmentTypeVO) {
        this.assessmentTypeVO = assessmentTypeVO;
    }

    public List<GradeScalePointVO> getGradeScalePointVOs() {
        return this.gradeScalePointVOs;
    }

    public void setGradeScalePointVOs(List<GradeScalePointVO> gradeScalePointVOs) {
        this.gradeScalePointVOs = gradeScalePointVOs;
    }

    public List<Activity> getActivities() {
        return this.activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
