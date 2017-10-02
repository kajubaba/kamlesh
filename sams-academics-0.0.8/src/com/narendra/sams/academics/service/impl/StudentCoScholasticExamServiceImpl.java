package com.narendra.sams.academics.service.impl;

import com.narendra.sams.acad.service.StudentSectionService;
import com.narendra.sams.academics.dao.StudentCoScholasticExamDAO;
import com.narendra.sams.academics.exam.domain.ActivitySkill;
import com.narendra.sams.academics.exam.domain.CoScholasticActivity;
import com.narendra.sams.academics.exam.domain.EvaluationType;
import com.narendra.sams.academics.exam.domain.SkillIndicator;
import com.narendra.sams.academics.exam.domain.StudentCoScholasticAssessmentStatus;
import com.narendra.sams.academics.exam.domain.StudentCoScholasticScore;
import com.narendra.sams.academics.exam.domain.coscholastic.Activity;
import com.narendra.sams.academics.exam.domain.coscholastic.AssessmentCriteria;
import com.narendra.sams.academics.exam.domain.coscholastic.Skill;
import com.narendra.sams.academics.service.CoScholasticAspectService;
import com.narendra.sams.academics.service.ExamPatternClassService;
import com.narendra.sams.academics.service.StudentCoScholasticExamService;
import com.narendra.sams.admission.domain.ClassHistory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;

public class StudentCoScholasticExamServiceImpl implements StudentCoScholasticExamService {
    private CoScholasticAspectService coScholasticAspectService;
    private ExamPatternClassService examPatternClassService;
    private StudentCoScholasticExamDAO studentCoScholasticExamDAO;
    private StudentSectionService studentSectionService;

    public StudentSectionService getStudentSectionService() {
        return this.studentSectionService;
    }

    public void setStudentSectionService(StudentSectionService studentSectionService) {
        this.studentSectionService = studentSectionService;
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

    public CoScholasticAspectService getCoScholasticAspectService() {
        return this.coScholasticAspectService;
    }

    public void setCoScholasticAspectService(CoScholasticAspectService coScholasticAspectService) {
        this.coScholasticAspectService = coScholasticAspectService;
    }

    public List<Activity> getStudentCoScholasticScore(Long studentId, Long classId, Long termId) {
        EvaluationType evaluationType = this.coScholasticAspectService.getCoScholasticEvaluationType(this.examPatternClassService.getExamPatternOfClass(classId).getId());
        List<StudentCoScholasticScore> studentCoScholasticScores = this.studentCoScholasticExamDAO.getStudentCoScholasticScore(studentId, classId, termId);
        if (studentCoScholasticScores == null || studentCoScholasticScores.isEmpty()) {
            List<Activity> activities = prepareActivityVO(this.coScholasticAspectService.getActivities(evaluationType.getId()));
            Collections.sort(activities, new BeanComparator("displayOrder", new NullComparator()));
            return activities;
        }
        Map<Long, Activity> activities2 = new HashMap();
        for (StudentCoScholasticScore studentCoScholasticScore : studentCoScholasticScores) {
            Activity activity;
            Skill skill;
            AssessmentCriteria assessmentCriteria;
            if (studentCoScholasticScore.getSkillIndicator().getActivitySkill().getActivity().getParentActivity() != null) {
                activity = (Activity) activities2.get(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getActivity().getParentActivity().getId());
                Activity subActivity;
                if (activity == null) {
                    activity = prepareActivity(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getActivity().getParentActivity());
                    activities2.put(activity.getId(), activity);
                    subActivity = prepareActivity(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getActivity());
                    activity.getSubActivitiesMap().put(subActivity.getId(), subActivity);
                    skill = prepareSkill(studentCoScholasticScore.getSkillIndicator().getActivitySkill());
                    subActivity.getSkillsMap().put(skill.getId(), skill);
                    assessmentCriteria = prepareAssessmentCriteria(studentCoScholasticScore);
                    skill.getAssessmentCriteriaMap().put(assessmentCriteria.getScoreId(), assessmentCriteria);
                } else {
                    subActivity = (Activity) activity.getSubActivitiesMap().get(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getActivity().getId());
                    if (subActivity == null) {
                        subActivity = prepareActivity(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getActivity());
                        activity.getSubActivitiesMap().put(subActivity.getId(), subActivity);
                        skill = prepareSkill(studentCoScholasticScore.getSkillIndicator().getActivitySkill());
                        subActivity.getSkillsMap().put(skill.getId(), skill);
                        assessmentCriteria = prepareAssessmentCriteria(studentCoScholasticScore);
                        skill.getAssessmentCriteriaMap().put(assessmentCriteria.getScoreId(), assessmentCriteria);
                    } else {
                        skill = (Skill) subActivity.getSkillsMap().get(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getId());
                        if (skill == null) {
                            skill = prepareSkill(studentCoScholasticScore.getSkillIndicator().getActivitySkill());
                            subActivity.getSkillsMap().put(skill.getId(), skill);
                            assessmentCriteria = prepareAssessmentCriteria(studentCoScholasticScore);
                            skill.getAssessmentCriteriaMap().put(assessmentCriteria.getScoreId(), assessmentCriteria);
                        } else {
                            assessmentCriteria = prepareAssessmentCriteria(studentCoScholasticScore);
                            skill.getAssessmentCriteriaMap().put(assessmentCriteria.getScoreId(), assessmentCriteria);
                        }
                    }
                }
            } else {
                activity = (Activity) activities2.get(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getActivity().getId());
                if (activity == null) {
                    activity = prepareActivity(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getActivity());
                    activities2.put(activity.getId(), activity);
                    skill = prepareSkill(studentCoScholasticScore.getSkillIndicator().getActivitySkill());
                    activity.getSkillsMap().put(skill.getId(), skill);
                    assessmentCriteria = prepareAssessmentCriteria(studentCoScholasticScore);
                    skill.getAssessmentCriteriaMap().put(assessmentCriteria.getScoreId(), assessmentCriteria);
                } else {
                    skill = (Skill) activity.getSkillsMap().get(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getId());
                    if (skill == null) {
                        skill = prepareSkill(studentCoScholasticScore.getSkillIndicator().getActivitySkill());
                        activity.getSkillsMap().put(skill.getId(), skill);
                        assessmentCriteria = prepareAssessmentCriteria(studentCoScholasticScore);
                        skill.getAssessmentCriteriaMap().put(assessmentCriteria.getScoreId(), assessmentCriteria);
                    } else {
                        assessmentCriteria = prepareAssessmentCriteria(studentCoScholasticScore);
                        skill.getAssessmentCriteriaMap().put(assessmentCriteria.getScoreId(), assessmentCriteria);
                    }
                }
            }
        }
        return new ArrayList(activities2.values());
    }

    public List<Activity> getStudentCoScholasticScoreNew(Long studentId, Long classId, Long termId) {
        List<Activity> masterActivities = prepareActivityVO(this.coScholasticAspectService.getActivities(this.coScholasticAspectService.getCoScholasticEvaluationType(this.examPatternClassService.getExamPatternOfClass(classId).getId()).getId()));
        Collections.sort(masterActivities, new BeanComparator("displayOrder", new NullComparator()));
        List<StudentCoScholasticScore> studentCoScholasticScores = this.studentCoScholasticExamDAO.getStudentCoScholasticScore(studentId, classId, termId);
        if (studentCoScholasticScores == null) {
            return masterActivities;
        }
        List<Activity> scoredActivities = null;
        if (!(studentCoScholasticScores == null || studentCoScholasticScores.isEmpty())) {
            Map<Long, Activity> activities = new HashMap();
            for (StudentCoScholasticScore studentCoScholasticScore : studentCoScholasticScores) {
                Activity activity;
                Skill skill;
                AssessmentCriteria assessmentCriteria;
                if (studentCoScholasticScore.getSkillIndicator().getActivitySkill().getActivity().getParentActivity() != null) {
                    activity = (Activity) activities.get(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getActivity().getParentActivity().getId());
                    Activity subActivity;
                    if (activity == null) {
                        activity = prepareActivity(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getActivity().getParentActivity());
                        activities.put(activity.getId(), activity);
                        subActivity = prepareActivity(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getActivity());
                        activity.getSubActivitiesMap().put(subActivity.getId(), subActivity);
                        skill = prepareSkill(studentCoScholasticScore.getSkillIndicator().getActivitySkill());
                        subActivity.getSkillsMap().put(skill.getId(), skill);
                        assessmentCriteria = prepareAssessmentCriteria(studentCoScholasticScore);
                        skill.getAssessmentCriteriaMap().put(assessmentCriteria.getCriteriaId(), assessmentCriteria);
                    } else {
                        subActivity = (Activity) activity.getSubActivitiesMap().get(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getActivity().getId());
                        if (subActivity == null) {
                            subActivity = prepareActivity(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getActivity());
                            activity.getSubActivitiesMap().put(subActivity.getId(), subActivity);
                            skill = prepareSkill(studentCoScholasticScore.getSkillIndicator().getActivitySkill());
                            subActivity.getSkillsMap().put(skill.getId(), skill);
                            assessmentCriteria = prepareAssessmentCriteria(studentCoScholasticScore);
                            skill.getAssessmentCriteriaMap().put(assessmentCriteria.getCriteriaId(), assessmentCriteria);
                        } else {
                            skill = (Skill) subActivity.getSkillsMap().get(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getId());
                            if (skill == null) {
                                skill = prepareSkill(studentCoScholasticScore.getSkillIndicator().getActivitySkill());
                                subActivity.getSkillsMap().put(skill.getId(), skill);
                                assessmentCriteria = prepareAssessmentCriteria(studentCoScholasticScore);
                                skill.getAssessmentCriteriaMap().put(assessmentCriteria.getCriteriaId(), assessmentCriteria);
                            } else {
                                assessmentCriteria = prepareAssessmentCriteria(studentCoScholasticScore);
                                skill.getAssessmentCriteriaMap().put(assessmentCriteria.getCriteriaId(), assessmentCriteria);
                            }
                        }
                    }
                } else {
                    activity = (Activity) activities.get(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getActivity().getId());
                    if (activity == null) {
                        activity = prepareActivity(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getActivity());
                        activities.put(activity.getId(), activity);
                        skill = prepareSkill(studentCoScholasticScore.getSkillIndicator().getActivitySkill());
                        activity.getSkillsMap().put(skill.getId(), skill);
                        assessmentCriteria = prepareAssessmentCriteria(studentCoScholasticScore);
                        skill.getAssessmentCriteriaMap().put(assessmentCriteria.getCriteriaId(), assessmentCriteria);
                    } else {
                        skill = (Skill) activity.getSkillsMap().get(studentCoScholasticScore.getSkillIndicator().getActivitySkill().getId());
                        if (skill == null) {
                            skill = prepareSkill(studentCoScholasticScore.getSkillIndicator().getActivitySkill());
                            activity.getSkillsMap().put(skill.getId(), skill);
                            assessmentCriteria = prepareAssessmentCriteria(studentCoScholasticScore);
                            skill.getAssessmentCriteriaMap().put(assessmentCriteria.getCriteriaId(), assessmentCriteria);
                        } else {
                            assessmentCriteria = prepareAssessmentCriteria(studentCoScholasticScore);
                            skill.getAssessmentCriteriaMap().put(assessmentCriteria.getCriteriaId(), assessmentCriteria);
                        }
                    }
                }
            }
            scoredActivities = new ArrayList(activities.values());
        }
        if (scoredActivities == null) {
            return masterActivities;
        }
        if (masterActivities == null) {
            return scoredActivities;
        }
        addAdditionalActivities(masterActivities, scoredActivities);
        return scoredActivities;
    }

    private void addAdditionalActivities(List<Activity> masterActivities, List<Activity> scoredActivities) {
        for (Activity masterActivity : masterActivities) {
            Boolean masterActivityFound = Boolean.FALSE;
            if (!(scoredActivities == null || scoredActivities.isEmpty())) {
                for (Activity scoredActivity : scoredActivities) {
                    if (masterActivity.getId().equals(scoredActivity.getId())) {
                        masterActivityFound = Boolean.TRUE;
                        if (masterActivity.getSubActivitiesMap() != null && !masterActivity.getSubActivitiesMap().isEmpty()) {
                            for (Long masterSubActivityId : masterActivity.getSubActivitiesMap().keySet()) {
                                Activity masterSubActivity = (Activity) masterActivity.getSubActivitiesMap().get(masterSubActivityId);
                                if (((Activity) scoredActivity.getSubActivitiesMap().get(masterSubActivityId)) == null) {
                                    scoredActivity.getSubActivitiesMap().put(masterSubActivityId, masterSubActivity);
                                }
                            }
                        } else if (!(masterActivity.getSkillsMap() == null || masterActivity.getSkillsMap().isEmpty())) {
                            for (Long masterSkillId : masterActivity.getSkillsMap().keySet()) {
                                Skill masterSkill = (Skill) masterActivity.getSkillsMap().get(masterSkillId);
                                Skill scoredSkill = (Skill) scoredActivity.getSkillsMap().get(masterSkillId);
                                if (scoredSkill == null) {
                                    scoredActivity.getSkillsMap().put(masterSkillId, masterSkill);
                                } else {
                                    for (Long masterCriteriaId : masterSkill.getAssessmentCriteriaMap().keySet()) {
                                        AssessmentCriteria masterCriteria = (AssessmentCriteria) masterSkill.getAssessmentCriteriaMap().get(masterCriteriaId);
                                        if (((AssessmentCriteria) scoredSkill.getAssessmentCriteriaMap().get(masterCriteriaId)) == null) {
                                            scoredSkill.getAssessmentCriteriaMap().put(masterCriteriaId, masterCriteria);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!masterActivityFound.booleanValue()) {
                scoredActivities.add(masterActivity);
            }
        }
    }

    private List<Activity> prepareActivityVO(Collection<CoScholasticActivity> coScholasticActivities) {
        if (coScholasticActivities == null) {
            return null;
        }
        List<Activity> activities = new ArrayList();
        for (CoScholasticActivity coScholasticActivity : coScholasticActivities) {
            Activity activity = new Activity();
            activity.setId(coScholasticActivity.getId());
            activity.setName(coScholasticActivity.getActivityName());
            activity.setDisplayName(coScholasticActivity.getActivityDisplayName());
            activity.setDisplayOrder(coScholasticActivity.getDisplaySeqNo());
            if (!(coScholasticActivity.getSubActivities() == null || coScholasticActivity.getSubActivities().isEmpty())) {
                List<Activity> subActivities = prepareActivityVO(coScholasticActivity.getSubActivities());
                Collections.sort(subActivities, new BeanComparator("displayOrder", new NullComparator()));
                if (subActivities != null) {
                    for (Activity subActivity : subActivities) {
                        activity.getSubActivitiesMap().put(subActivity.getId(), subActivity);
                    }
                }
            }
            List<Skill> skills = prepareSkillVO(coScholasticActivity.getActivitySkills());
            Collections.sort(skills, new BeanComparator("displayOrder", new NullComparator()));
            if (skills != null) {
                for (Skill skill : skills) {
                    activity.getSkillsMap().put(skill.getId(), skill);
                }
            }
            activities.add(activity);
        }
        return activities;
    }

    private List<Skill> prepareSkillVO(Collection<ActivitySkill> skills) {
        if (skills == null) {
            return null;
        }
        List<Skill> skillList = new ArrayList();
        for (ActivitySkill activityskill : skills) {
            Skill skill = new Skill();
            skill.setId(activityskill.getId());
            skill.setName(activityskill.getSkillName());
            skill.setDisplayName(activityskill.getSkillDisplayName());
            skill.setDisplayOrder(activityskill.getDisplaySeqNo());
            skill.setIsAdditional(activityskill.getIsAdditional());
            List<AssessmentCriteria> assessmentCriterias = prepareAssessmentCriteria(activityskill.getIndicators());
            Collections.sort(assessmentCriterias, new BeanComparator("displayOrder", new NullComparator()));
            if (assessmentCriterias != null) {
                for (AssessmentCriteria assessmentCriteria : assessmentCriterias) {
                    skill.getAssessmentCriteriaMap().put(assessmentCriteria.getCriteriaId(), assessmentCriteria);
                }
            }
            skillList.add(skill);
        }
        return skillList;
    }

    private List<AssessmentCriteria> prepareAssessmentCriteria(Collection<SkillIndicator> skillIndicators) {
        if (skillIndicators == null) {
            return null;
        }
        List<AssessmentCriteria> assessmentCriterias = new ArrayList();
        for (SkillIndicator skillIndicator : skillIndicators) {
            AssessmentCriteria assessmentCriteria = new AssessmentCriteria();
            assessmentCriteria.setCriteriaId(skillIndicator.getId());
            assessmentCriteria.setName(skillIndicator.getName());
            assessmentCriteria.setDisplayName(skillIndicator.getDisplayName());
            assessmentCriteria.setDisplayOrder(skillIndicator.getDisplaySeqNo());
            assessmentCriterias.add(assessmentCriteria);
        }
        return assessmentCriterias;
    }

    private Skill prepareSkill(ActivitySkill activitySkill) {
        Skill skill = new Skill();
        skill.setId(activitySkill.getId());
        skill.setName(activitySkill.getSkillName());
        skill.setDisplayName(activitySkill.getSkillDisplayName());
        skill.setDisplayOrder(activitySkill.getDisplaySeqNo());
        skill.setIsAdditional(activitySkill.getIsAdditional());
        return skill;
    }

    private Activity prepareActivity(CoScholasticActivity coScholasticActivity) {
        Activity activity = new Activity();
        activity.setId(coScholasticActivity.getId());
        activity.setName(coScholasticActivity.getActivityName());
        activity.setDisplayName(coScholasticActivity.getActivityDisplayName());
        activity.setDisplayOrder(coScholasticActivity.getDisplaySeqNo());
        return activity;
    }

    private AssessmentCriteria prepareAssessmentCriteria(StudentCoScholasticScore studentCoScholasticScore) {
        AssessmentCriteria assessmentCriteria = new AssessmentCriteria();
        assessmentCriteria.setCriteriaId(studentCoScholasticScore.getSkillIndicator().getId());
        assessmentCriteria.setScoreId(studentCoScholasticScore.getId());
        assessmentCriteria.setName(studentCoScholasticScore.getSkillIndicator().getDisplayName());
        assessmentCriteria.setDisplayName(studentCoScholasticScore.getSkillIndicator().getDisplayName());
        assessmentCriteria.setCriteriaId(studentCoScholasticScore.getSkillIndicator().getId());
        assessmentCriteria.setDisplayOrder(studentCoScholasticScore.getSkillIndicator().getDisplaySeqNo());
        assessmentCriteria.setFreeTextValue(studentCoScholasticScore.getFreeTextValue());
        if (studentCoScholasticScore.getMarksObtained() != null) {
            assessmentCriteria.setScore(Long.valueOf(studentCoScholasticScore.getMarksObtained().longValue()));
        }
        if (studentCoScholasticScore.getGradeScalePoint() != null) {
            assessmentCriteria.setGradeScalePointId(studentCoScholasticScore.getGradeScalePoint().getId());
        }
        return assessmentCriteria;
    }

    public void saveStudentCoScholaticScore(List<StudentCoScholasticScore> coScholasticScores, Long studentId, Long studentClassId, Long termId) {
        if (coScholasticScores != null) {
            this.studentCoScholasticExamDAO.saveStudentCoScholaticScore(coScholasticScores, studentId, studentClassId, termId);
        }
    }

    public void updateStudentCoScholaticScore(List<StudentCoScholasticScore> coScholasticScores, Long studentId, Long studentClassId, Long termId) {
        if (coScholasticScores != null) {
            this.studentCoScholasticExamDAO.updateStudentCoScholaticScore(coScholasticScores, studentId, studentClassId, termId);
        }
    }

    public List<StudentCoScholasticAssessmentStatus> getStduentsWithStatus(Long academicYearClassId, Long sectionId, Long evaluationTermId) {
        List<ClassHistory> classHistories = this.studentSectionService.getStudents(academicYearClassId, sectionId);
        if (classHistories == null) {
            return null;
        }
        List<StudentCoScholasticAssessmentStatus> students = new ArrayList();
        for (ClassHistory classHistory : classHistories) {
            StudentCoScholasticAssessmentStatus assessmentStatus = new StudentCoScholasticAssessmentStatus();
            assessmentStatus.setStudentId(classHistory.getStudent().getId());
            assessmentStatus.setStudentName(classHistory.getStudent().getFullName());
            assessmentStatus.setStudentAssignedId(classHistory.getStudent().getStudentId());
            assessmentStatus.setStudentFatherName(classHistory.getStudent().getFatherName());
            if ("male".equalsIgnoreCase(classHistory.getStudent().getGender())) {
                assessmentStatus.setStudentGender("Male");
            } else {
                assessmentStatus.setStudentGender("Female");
            }
            students.add(assessmentStatus);
        }
        if (students.isEmpty()) {
            return students;
        }
        for (StudentCoScholasticAssessmentStatus student : students) {
            List<StudentCoScholasticScore> studentCoScholasticScores = this.studentCoScholasticExamDAO.getStudentCoScholasticScore(student.getStudentId(), academicYearClassId, evaluationTermId);
            if (studentCoScholasticScores == null) {
                student.setPercent(Long.valueOf(0));
            } else {
                int notNullCount = 0;
                for (StudentCoScholasticScore studentCoScholasticScore : studentCoScholasticScores) {
                    if (studentCoScholasticScore.getMarksObtained() != null) {
                        notNullCount++;
                    } else if (studentCoScholasticScore.getGradeScalePoint() != null) {
                        notNullCount++;
                    }
                }
                if (notNullCount != 0) {
                    student.setPercent(Long.valueOf((long) ((notNullCount * 100) / studentCoScholasticScores.size())));
                } else {
                    student.setPercent(Long.valueOf(0));
                }
            }
        }
        return students;
    }
}
