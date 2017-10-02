package com.narendra.sams.academics.service.impl;

import com.narendra.sams.academics.dao.CoScholasticAspectDAO;
import com.narendra.sams.academics.exam.domain.ActivitySkill;
import com.narendra.sams.academics.exam.domain.CoScholasticActivity;
import com.narendra.sams.academics.exam.domain.EvaluationType;
import com.narendra.sams.academics.exam.domain.SkillIndicator;
import com.narendra.sams.academics.exam.domain.SkillIndicatorGradePointMap;
import com.narendra.sams.academics.service.CoScholasticAspectService;
import java.util.List;

public class CoScholasticAspectServiceImpl implements CoScholasticAspectService {
    private CoScholasticAspectDAO coScholasticAspectDAO;

    public CoScholasticAspectDAO getCoScholasticAspectDAO() {
        return this.coScholasticAspectDAO;
    }

    public void setCoScholasticAspectDAO(CoScholasticAspectDAO coScholasticAspectDAO) {
        this.coScholasticAspectDAO = coScholasticAspectDAO;
    }

    public List<CoScholasticActivity> getActivities(Long evaluationTypeId) {
        return this.coScholasticAspectDAO.getActivities(evaluationTypeId);
    }

    public Long addSkillIndicator(SkillIndicator skillIndicator, Long userId) {
        return this.coScholasticAspectDAO.addSkillIndicator(skillIndicator, userId);
    }

    public void updateSkillIndicator(SkillIndicator skillIndicator, Long userId) {
        this.coScholasticAspectDAO.updateSkillIndicator(skillIndicator, userId);
    }

    public void deleteSkillIndicator(Long indicatorId) {
        this.coScholasticAspectDAO.deleteSkillIndicator(indicatorId);
    }

    public Long addActivitySkill(ActivitySkill activitySkill, Long userId) {
        return this.coScholasticAspectDAO.addActivitySkill(activitySkill, userId);
    }

    public void updateActivitySkill(ActivitySkill activitySkill, Long userId) {
        this.coScholasticAspectDAO.updateActivitySkill(activitySkill, userId);
    }

    public void deleteSkill(Long skillId) {
        this.coScholasticAspectDAO.deleteSkill(skillId);
    }

    public Long addActivity(CoScholasticActivity coScholasticActivity, Long userId) {
        return this.coScholasticAspectDAO.addActivity(coScholasticActivity, userId);
    }

    public void updateActivity(CoScholasticActivity coScholasticActivity, Long userId) {
        this.coScholasticAspectDAO.updateActivity(coScholasticActivity, userId);
    }

    public void deleteActivity(Long activityId) {
        this.coScholasticAspectDAO.deleteActivity(activityId);
    }

    public EvaluationType getCoScholasticEvaluationType(Long examPatternId) {
        return this.coScholasticAspectDAO.getCoScholasticEvaluationType(examPatternId);
    }

    public void saveOrUpdateGradeToIndicatorMapping(List<SkillIndicatorGradePointMap> skillGradePointMaps) {
        this.coScholasticAspectDAO.saveOrUpdateGradeToIndicatorMapping(skillGradePointMaps);
    }

    public List<SkillIndicatorGradePointMap> getIndicatorOverallIndicator(Long skillIndicatorId) {
        return this.coScholasticAspectDAO.getIndicatorOverallIndicator(skillIndicatorId);
    }
}
