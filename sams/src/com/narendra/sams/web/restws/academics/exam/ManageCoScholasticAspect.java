package com.narendra.sams.web.restws.academics.exam;

import com.narendra.sams.academics.domain.GradeScalePoint;
import com.narendra.sams.academics.exam.domain.ActivitySkill;
import com.narendra.sams.academics.exam.domain.CoScholasticActivity;
import com.narendra.sams.academics.exam.domain.EvaluationType;
import com.narendra.sams.academics.exam.domain.SkillIndicator;
import com.narendra.sams.academics.exam.domain.SkillIndicatorGradePointMap;
import com.narendra.sams.academics.service.AssessmentTypeService;
import com.narendra.sams.academics.service.CoScholasticAspectService;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.academics.exam.form.ActivityForm;
import com.narendra.sams.web.restws.academics.exam.form.ActivitySkillForm;
import com.narendra.sams.web.restws.academics.exam.form.SkillGradeIndicatorForm;
import com.narendra.sams.web.restws.academics.exam.form.SkillGradeIndicatorListForm;
import com.narendra.sams.web.restws.academics.exam.form.SkillIndicatorForm;
import com.narendra.sams.web.restws.academics.exam.vo.ActivityVO;
import com.narendra.sams.web.restws.academics.exam.vo.CoScholasticAspectVO;
import com.narendra.sams.web.restws.academics.exam.vo.SkillGradeIndicatorVO;
import com.narendra.sams.web.restws.academics.exam.vo.SkillIndicatorVO;
import com.narendra.sams.web.restws.academics.exam.vo.SkillVO;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/academics/exam/manage/csa"})
public class ManageCoScholasticAspect {
    @Autowired
    private AssessmentTypeService assessmentTypeService;
    @Autowired
    private CoScholasticAspectService coScholasticAspectService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{examPatternId}/activities"})
    public CoScholasticAspectVO getCoScholasticActivities(@PathVariable Long examPatternId) {
        CoScholasticAspectVO coScholasticAspectVO = new CoScholasticAspectVO();
        EvaluationType evaluationType = this.coScholasticAspectService.getCoScholasticEvaluationType(examPatternId);
        Set<GradeScalePoint> gradeScalePoints = null;
        if (evaluationType.getGradeScale() != null) {
            gradeScalePoints = evaluationType.getGradeScale().getGradeScalePoints();
        }
        List<CoScholasticActivity> activities = this.coScholasticAspectService.getActivities(evaluationType.getId());
        coScholasticAspectVO.setEvaluationTypeId(evaluationType.getId());
        coScholasticAspectVO.setIsSkillBased(evaluationType.getIsSkillBased());
        coScholasticAspectVO.setIsIndicatorBased(evaluationType.getIsIndicatorBased());
        if ("GRADE_TO_INDICATOR_BASED".equals(evaluationType.getScoringMethod())) {
            coScholasticAspectVO.setIsGradeToIndicatorBasedExam(Boolean.TRUE);
        } else {
            coScholasticAspectVO.setIsGradeToIndicatorBasedExam(Boolean.FALSE);
        }
        coScholasticAspectVO.setActivities(prepareActivityVO(activities, gradeScalePoints));
        return coScholasticAspectVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{evaluationTypeId}/{skillIndicatorId}"})
    public List<SkillGradeIndicatorVO> getOverallIndicator(@PathVariable Long evaluationTypeId, @PathVariable Long skillIndicatorId) {
        return prepareSkillGradeIndicatorVO(skillIndicatorId, this.assessmentTypeService.getEvaluationType(evaluationTypeId).getGradeScale().getGradeScalePoints(), this.coScholasticAspectService.getIndicatorOverallIndicator(skillIndicatorId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save/gradeindicator"})
    public AjaxSuccessResponse saveGradeIndicator(@RequestBody SkillGradeIndicatorListForm gradeIndicatorListForm) {
        List<SkillIndicatorGradePointMap> skillGradePointMaps = new ArrayList();
        for (SkillGradeIndicatorForm skillGradeIndicatorForm : gradeIndicatorListForm.getSkillGradeIndicatorForms()) {
            SkillIndicatorGradePointMap skillGradePointMap = new SkillIndicatorGradePointMap();
            skillGradePointMap.setId(skillGradeIndicatorForm.getId());
            skillGradePointMap.setOverallIndicator(skillGradeIndicatorForm.getOverallIndicator());
            SkillIndicator skillIndicator = new SkillIndicator();
            skillIndicator.setId(skillGradeIndicatorForm.getIndicatorId());
            skillGradePointMap.setSkillIndicator(skillIndicator);
            GradeScalePoint gradeScalePoint = new GradeScalePoint();
            gradeScalePoint.setId(skillGradeIndicatorForm.getGradePointId());
            skillGradePointMap.setGradeScalePoint(gradeScalePoint);
            skillGradePointMaps.add(skillGradePointMap);
        }
        this.coScholasticAspectService.saveOrUpdateGradeToIndicatorMapping(skillGradePointMaps);
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save/activityskill"})
    public AjaxSuccessResponse saveActivitySkill(@RequestBody ActivitySkillForm activitySkillForm) {
        ActivitySkill activitySkill = new ActivitySkill();
        activitySkill.setId(activitySkillForm.getId());
        activitySkill.setSkillName(activitySkillForm.getName());
        activitySkill.setSkillDisplayName(activitySkillForm.getDisplayName());
        activitySkill.setDisplaySeqNo(activitySkillForm.getDisplayOrder());
        if (activitySkillForm.getDoNotDisplayOnScorecard() == null) {
            activitySkill.setDoNotDisplayOnScoreCard(Boolean.FALSE);
        } else {
            activitySkill.setDoNotDisplayOnScoreCard(activitySkillForm.getDoNotDisplayOnScorecard());
        }
        CoScholasticActivity coScholasticActivity = new CoScholasticActivity();
        coScholasticActivity.setId(activitySkillForm.getActivityId());
        activitySkill.setActivity(coScholasticActivity);
        if (activitySkill.getId() == null) {
            this.coScholasticAspectService.addActivitySkill(activitySkill, LoggedinUserAssistant.getLoggedInUserId());
        } else {
            this.coScholasticAspectService.updateActivitySkill(activitySkill, LoggedinUserAssistant.getLoggedInUserId());
        }
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/delete/skill/{skillId}"})
    public AjaxSuccessResponse deleteActivitySkill(@PathVariable Long skillId) {
        this.coScholasticAspectService.deleteSkill(skillId);
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save/indicator"})
    public AjaxSuccessResponse saveSkillIndicator(@RequestBody SkillIndicatorForm skillIndicatorForm) {
        SkillIndicator skillIndicator = new SkillIndicator();
        skillIndicator.setId(skillIndicatorForm.getId());
        skillIndicator.setName(skillIndicatorForm.getName());
        skillIndicator.setDisplayName(skillIndicatorForm.getDisplayName());
        skillIndicator.setDisplaySeqNo(skillIndicatorForm.getDisplayOrder());
        ActivitySkill activitySkill = new ActivitySkill();
        activitySkill.setId(skillIndicatorForm.getSkillId());
        skillIndicator.setActivitySkill(activitySkill);
        if (skillIndicator.getId() == null) {
            this.coScholasticAspectService.addSkillIndicator(skillIndicator, null);
        } else {
            this.coScholasticAspectService.updateSkillIndicator(skillIndicator, null);
        }
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/delete/indicator/{indicatorId}"})
    public AjaxSuccessResponse deleteSkillIndicator(@PathVariable Long indicatorId) {
        this.coScholasticAspectService.deleteSkillIndicator(indicatorId);
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save/activity"})
    public AjaxSuccessResponse saveActivity(@RequestBody ActivityForm activityForm) {
        CoScholasticActivity coScholasticActivity = new CoScholasticActivity();
        coScholasticActivity.setId(activityForm.getId());
        coScholasticActivity.setActivityName(activityForm.getName());
        coScholasticActivity.setActivityDisplayName(activityForm.getDisplayName());
        coScholasticActivity.setDisplaySeqNo(activityForm.getDisplayOrder());
        coScholasticActivity.setIsSkillBasedAssessment(activityForm.getIsSkillBasedAssessment());
        if (coScholasticActivity.getId() == null) {
            if (activityForm.getParentActivityId() != null) {
                CoScholasticActivity parentActivity = new CoScholasticActivity();
                parentActivity.setId(activityForm.getParentActivityId());
                coScholasticActivity.setParentActivity(parentActivity);
            }
            EvaluationType evaluationType = new EvaluationType();
            evaluationType.setId(activityForm.getEvaluationTypeId());
            coScholasticActivity.setEvaluationType(evaluationType);
            this.coScholasticAspectService.addActivity(coScholasticActivity, LoggedinUserAssistant.getLoggedInUserId());
        } else {
            this.coScholasticAspectService.updateActivity(coScholasticActivity, LoggedinUserAssistant.getLoggedInUserId());
        }
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/delete/activity/{activityId}"})
    public AjaxSuccessResponse deleteActivity(@PathVariable Long activityId) {
        this.coScholasticAspectService.deleteActivity(activityId);
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }

    private List<ActivityVO> prepareActivityVO(Collection<CoScholasticActivity> activities, Set<GradeScalePoint> gradeScalePoints) {
        if (activities == null) {
            return null;
        }
        List<ActivityVO> activityVos = new ArrayList();
        for (CoScholasticActivity activity : activities) {
            ActivityVO activityVO = new ActivityVO();
            activityVO.setId(activity.getId());
            activityVO.setName(activity.getActivityName());
            activityVO.setDisplayName(activity.getActivityDisplayName());
            activityVO.setDisplayOrder(activity.getDisplaySeqNo());
            activityVO.setIsSkillBasedAssessment(activity.getIsSkillBasedAssessment());
            if (activity.getSubActivities() != null) {
                List<ActivityVO> subActivityVos = prepareActivityVO(activity.getSubActivities(), gradeScalePoints);
                Collections.sort(subActivityVos, new BeanComparator("displayOrder", new NullComparator()));
                activityVO.setSubActivities(subActivityVos);
            }
            activityVO.setSkills(prepareSkillVO(activity.getActivitySkills(), gradeScalePoints));
            activityVos.add(activityVO);
        }
        Collections.sort(activityVos, new BeanComparator("displayOrder", new NullComparator()));
        return activityVos;
    }

    private List<SkillVO> prepareSkillVO(Collection<ActivitySkill> skills, Set<GradeScalePoint> set) {
        if (skills == null) {
            return null;
        }
        List<SkillVO> skillVos = new ArrayList();
        for (ActivitySkill skill : skills) {
            SkillVO skillVO = new SkillVO();
            skillVO.setId(skill.getId());
            skillVO.setName(skill.getSkillName());
            skillVO.setDisplayName(skill.getSkillDisplayName());
            skillVO.setDisplayOrder(skill.getDisplaySeqNo());
            skillVO.setDoNotDisplayOnScoreCard(skill.getDoNotDisplayOnScoreCard());
            skillVO.setIndicators(prepareIndicatorVO(skill.getIndicators()));
            skillVos.add(skillVO);
        }
        Collections.sort(skillVos, new BeanComparator("displayOrder", new NullComparator()));
        return skillVos;
    }

    private List<SkillGradeIndicatorVO> prepareSkillGradeIndicatorVO(Long skillIndicatoryId, Set<GradeScalePoint> gradeScalePoints, List<SkillIndicatorGradePointMap> skillGradePointMaps) {
        List<SkillGradeIndicatorVO> skillGradeIndicatorVOs = new ArrayList();
        if (gradeScalePoints != null) {
            for (GradeScalePoint gradeScalePoint : gradeScalePoints) {
                SkillGradeIndicatorVO skillGradeIndicatorVO = new SkillGradeIndicatorVO();
                skillGradeIndicatorVO.setGradePointId(gradeScalePoint.getId());
                skillGradeIndicatorVO.setIndicatorId(skillIndicatoryId);
                skillGradeIndicatorVO.setGrade(gradeScalePoint.getGrade());
                skillGradeIndicatorVO.setDisplayOrder(gradeScalePoint.getDisplaySeqNo());
                skillGradeIndicatorVOs.add(skillGradeIndicatorVO);
            }
        }
        if (skillGradePointMaps != null) {
            for (SkillIndicatorGradePointMap skillGradePointMap : skillGradePointMaps) {
                SkillGradeIndicatorVO gradeIndicatorVO = null;
                for (SkillGradeIndicatorVO skillGradeIndicatorVO2 : skillGradeIndicatorVOs) {
                    if (skillGradePointMap.getGradeScalePoint().getId().equals(skillGradeIndicatorVO2.getGradePointId())) {
                        gradeIndicatorVO = skillGradeIndicatorVO2;
                        break;
                    }
                }
                gradeIndicatorVO.setId(skillGradePointMap.getId());
                gradeIndicatorVO.setOverallIndicator(skillGradePointMap.getOverallIndicator());
            }
        }
        Collections.sort(skillGradeIndicatorVOs, new BeanComparator("displayOrder", new NullComparator()));
        return skillGradeIndicatorVOs;
    }

    private List<SkillIndicatorVO> prepareIndicatorVO(Collection<SkillIndicator> skillIndicators) {
        if (skillIndicators == null) {
            return null;
        }
        List<SkillIndicatorVO> indicatorVOs = new ArrayList();
        for (SkillIndicator skillIndicator : skillIndicators) {
            SkillIndicatorVO indicatorVO = new SkillIndicatorVO();
            indicatorVO.setId(skillIndicator.getId());
            indicatorVO.setName(skillIndicator.getName());
            indicatorVO.setDisplayName(skillIndicator.getDisplayName());
            indicatorVO.setDisplayOrder(skillIndicator.getDisplaySeqNo());
            indicatorVOs.add(indicatorVO);
        }
        Collections.sort(indicatorVOs, new BeanComparator("displayOrder", new NullComparator()));
        return indicatorVOs;
    }
}
