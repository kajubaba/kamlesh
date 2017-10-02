package com.narendra.sams.academics.dao.impl;

import com.narendra.sams.academics.dao.CoScholasticAspectDAO;
import com.narendra.sams.academics.exam.domain.ActivitySkill;
import com.narendra.sams.academics.exam.domain.CoScholasticActivity;
import com.narendra.sams.academics.exam.domain.EvaluationType;
import com.narendra.sams.academics.exam.domain.SkillIndicator;
import com.narendra.sams.academics.exam.domain.SkillIndicatorGradePointMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CoScholasticAspectDAOImpl extends HibernateDaoSupport implements CoScholasticAspectDAO {
    public List<CoScholasticActivity> getActivities(Long evaluationTypeId) {
        Criteria crt = getSession().createCriteria(CoScholasticActivity.class);
        crt.add(Restrictions.eq("evaluationType.id", evaluationTypeId));
        crt.add(Restrictions.isNull("parentActivity"));
        crt.addOrder(Order.asc("displaySeqNo"));
        return crt.list();
    }

    public Long addSkillIndicator(SkillIndicator skillIndicator, Long userId) {
        return (Long) getHibernateTemplate().save(skillIndicator);
    }

    public void updateSkillIndicator(SkillIndicator skillIndicator, Long userId) {
        SkillIndicator persistSkillIndicator = (SkillIndicator) getHibernateTemplate().load(SkillIndicator.class, skillIndicator.getId());
        persistSkillIndicator.setName(skillIndicator.getName());
        persistSkillIndicator.setDisplayName(skillIndicator.getDisplayName());
        persistSkillIndicator.setDisplaySeqNo(skillIndicator.getDisplaySeqNo());
        getHibernateTemplate().update(persistSkillIndicator);
    }

    public void deleteSkillIndicator(Long indicatorId) {
        getHibernateTemplate().delete(getHibernateTemplate().get(SkillIndicator.class, indicatorId));
    }

    public Long addActivitySkill(ActivitySkill activitySkill, Long userId) {
        return (Long) getHibernateTemplate().save(activitySkill);
    }

    public void updateActivitySkill(ActivitySkill activitySkill, Long userId) {
        ActivitySkill persistSkill = (ActivitySkill) getHibernateTemplate().load(ActivitySkill.class, activitySkill.getId());
        persistSkill.setSkillName(activitySkill.getSkillName());
        persistSkill.setSkillDisplayName(activitySkill.getSkillDisplayName());
        persistSkill.setDisplaySeqNo(activitySkill.getDisplaySeqNo());
        persistSkill.setDoNotDisplayOnScoreCard(activitySkill.getDoNotDisplayOnScoreCard());
        getHibernateTemplate().update(persistSkill);
    }

    public void deleteSkill(Long skillId) {
        getHibernateTemplate().delete(getHibernateTemplate().get(ActivitySkill.class, skillId));
    }

    public Long addActivity(CoScholasticActivity coScholasticActivity, Long userId) {
        return (Long) getHibernateTemplate().save(coScholasticActivity);
    }

    public void updateActivity(CoScholasticActivity coScholasticActivity, Long userId) {
        CoScholasticActivity persistActivity = (CoScholasticActivity) getHibernateTemplate().load(CoScholasticActivity.class, coScholasticActivity.getId());
        persistActivity.setActivityName(coScholasticActivity.getActivityName());
        persistActivity.setActivityDisplayName(coScholasticActivity.getActivityDisplayName());
        persistActivity.setDisplaySeqNo(coScholasticActivity.getDisplaySeqNo());
        persistActivity.setIsSkillBasedAssessment(coScholasticActivity.getIsSkillBasedAssessment());
        getHibernateTemplate().update(persistActivity);
    }

    public void deleteActivity(Long activityId) {
        getHibernateTemplate().delete(getHibernateTemplate().get(CoScholasticActivity.class, activityId));
    }

    public EvaluationType getCoScholasticEvaluationType(Long examPatternId) {
        Criteria crt = getSession().createCriteria(EvaluationType.class);
        crt.add(Restrictions.eq("evaluationScheme.id", examPatternId));
        crt.add(Restrictions.eq("isScholastic", Boolean.FALSE));
        return (EvaluationType) crt.uniqueResult();
    }

    public void saveOrUpdateGradeToIndicatorMapping(List<SkillIndicatorGradePointMap> skillGradePointMaps) {
        getHibernateTemplate().saveOrUpdateAll(skillGradePointMaps);
    }

    public List<SkillIndicatorGradePointMap> getIndicatorOverallIndicator(Long skillIndicatorId) {
        Criteria crt = getSession().createCriteria(SkillIndicatorGradePointMap.class);
        crt.add(Restrictions.eq("skillIndicator.id", skillIndicatorId));
        return crt.list();
    }
}
