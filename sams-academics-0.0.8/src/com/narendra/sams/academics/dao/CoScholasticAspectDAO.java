package com.narendra.sams.academics.dao;

import com.narendra.sams.academics.exam.domain.ActivitySkill;
import com.narendra.sams.academics.exam.domain.CoScholasticActivity;
import com.narendra.sams.academics.exam.domain.EvaluationType;
import com.narendra.sams.academics.exam.domain.SkillIndicator;
import com.narendra.sams.academics.exam.domain.SkillIndicatorGradePointMap;
import java.util.List;

public interface CoScholasticAspectDAO {
    Long addActivity(CoScholasticActivity coScholasticActivity, Long l);

    Long addActivitySkill(ActivitySkill activitySkill, Long l);

    Long addSkillIndicator(SkillIndicator skillIndicator, Long l);

    void deleteActivity(Long l);

    void deleteSkill(Long l);

    void deleteSkillIndicator(Long l);

    List<CoScholasticActivity> getActivities(Long l);

    EvaluationType getCoScholasticEvaluationType(Long l);

    List<SkillIndicatorGradePointMap> getIndicatorOverallIndicator(Long l);

    void saveOrUpdateGradeToIndicatorMapping(List<SkillIndicatorGradePointMap> list);

    void updateActivity(CoScholasticActivity coScholasticActivity, Long l);

    void updateActivitySkill(ActivitySkill activitySkill, Long l);

    void updateSkillIndicator(SkillIndicator skillIndicator, Long l);
}
