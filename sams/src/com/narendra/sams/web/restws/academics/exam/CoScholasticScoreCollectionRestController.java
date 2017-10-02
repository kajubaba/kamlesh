package com.narendra.sams.web.restws.academics.exam;

import com.narendra.sams.academics.domain.GradeScalePoint;
import com.narendra.sams.academics.exam.domain.EvaluationType;
import com.narendra.sams.academics.exam.domain.SkillIndicator;
import com.narendra.sams.academics.exam.domain.StudentCoScholasticAssessmentStatus;
import com.narendra.sams.academics.exam.domain.StudentCoScholasticScore;
import com.narendra.sams.academics.exam.domain.coscholastic.Activity;
import com.narendra.sams.academics.service.AssessmentTypeService;
import com.narendra.sams.academics.service.GradeService;
import com.narendra.sams.academics.service.StudentCoScholasticExamService;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.academics.controllerhelper.AssessmentTypeVOMapper;
import com.narendra.sams.web.restws.academics.controllerhelper.GradeScalePointVOMapper;
import com.narendra.sams.web.restws.academics.exam.form.CoScholasticScoreForm;
import com.narendra.sams.web.restws.academics.exam.form.CriteriaScoreForm;
import com.narendra.sams.web.restws.academics.exam.vo.CoScholasticScoreCollectionVO;
import com.narendra.sams.web.restws.academics.exam.vo.GradeScalePointVO;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/academics/scorecollection/co-scholastic/score"})
public class CoScholasticScoreCollectionRestController {
    @Autowired
    private AssessmentTypeService assessmentTypeService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private StudentCoScholasticExamService studentCoScholasticExamService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/get"})
    public CoScholasticScoreCollectionVO getStduentScore(Long studentId, Long classId, Long termId) {
        CoScholasticScoreCollectionVO coScholasticScoreCollectionVO = new CoScholasticScoreCollectionVO();
        EvaluationType evaluationType = this.assessmentTypeService.getCoScholasticEvaluationType(classId);
        List<GradeScalePointVO> gradeScalePointVOs = GradeScalePointVOMapper.prepareGradeScalePointVOs(this.gradeService.getGradeScalePoints(evaluationType.getGradeScale().getId()));
        List<Activity> activities = this.studentCoScholasticExamService.getStudentCoScholasticScoreNew(studentId, classId, termId);
        Collections.sort(activities, new BeanComparator("displayOrder", new NullComparator()));
        coScholasticScoreCollectionVO.setActivities(activities);
        coScholasticScoreCollectionVO.setGradeScalePointVOs(gradeScalePointVOs);
        coScholasticScoreCollectionVO.setAssessmentTypeVO(AssessmentTypeVOMapper.prepareAssessmentTypeVO(evaluationType));
        return coScholasticScoreCollectionVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/getStudents"})
    public List<StudentCoScholasticAssessmentStatus> getStduents(Long classId, Long sectionId, Long termId) {
        List<StudentCoScholasticAssessmentStatus> studentList = this.studentCoScholasticExamService.getStduentsWithStatus(classId, sectionId, termId);
        Collections.sort(studentList, new BeanComparator("studentName", new NullComparator()));
        return studentList;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxSuccessResponse saveStudentCoScholasticScore(@RequestBody CoScholasticScoreForm coScholasticScoreForm) {
        if (coScholasticScoreForm.getCriteriaScores() != null) {
            List<StudentCoScholasticScore> studentCoScholasticScores = new ArrayList();
            for (CriteriaScoreForm criteriaScoreForm : coScholasticScoreForm.getCriteriaScores()) {
                StudentCoScholasticScore studentCoScholasticScore = new StudentCoScholasticScore();
                studentCoScholasticScore.setId(criteriaScoreForm.getScoreId());
                SkillIndicator skillIndicator = new SkillIndicator();
                skillIndicator.setId(criteriaScoreForm.getCriteriaId());
                studentCoScholasticScore.setSkillIndicator(skillIndicator);
                studentCoScholasticScore.setMarksObtained(criteriaScoreForm.getScore());
                studentCoScholasticScore.setFreeTextValue(criteriaScoreForm.getFreeTextValue());
                if (criteriaScoreForm.getGradeScalePointId() != null) {
                    GradeScalePoint gradeScalePoint = new GradeScalePoint();
                    gradeScalePoint.setId(criteriaScoreForm.getGradeScalePointId());
                    studentCoScholasticScore.setGradeScalePoint(gradeScalePoint);
                }
                studentCoScholasticScores.add(studentCoScholasticScore);
            }
            if (((StudentCoScholasticScore) studentCoScholasticScores.get(0)).getId() == null) {
                this.studentCoScholasticExamService.saveStudentCoScholaticScore(studentCoScholasticScores, coScholasticScoreForm.getStudentId(), coScholasticScoreForm.getStudentClassId(), coScholasticScoreForm.getTermId());
            } else {
                this.studentCoScholasticExamService.updateStudentCoScholaticScore(studentCoScholasticScores, coScholasticScoreForm.getStudentId(), coScholasticScoreForm.getStudentClassId(), coScholasticScoreForm.getTermId());
            }
        }
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }
}
