package com.narendra.sams.web.restws.academics.exam;

import com.narendra.sams.academics.exam.domain.EvaluationType;
import com.narendra.sams.academics.service.AssessmentTypeService;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.academics.controllerhelper.EvaluationTermVOMapper;
import com.narendra.sams.web.restws.academics.exam.form.AssessmentGradeAndScoringMethodForm;
import com.narendra.sams.web.restws.academics.exam.vo.AssessmentTypeVO;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/academics/exam/assessment-type"})
public class AssessmentTypeRestController {
    @Autowired
    private AssessmentTypeService assessmentTypeService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/saveGradeSclaeAndScoringMetod"})
    public AjaxSuccessResponse updateAssessmentGradeAndScoringMethod(@RequestBody AssessmentGradeAndScoringMethodForm assessmentGradeAndScoringMethodForm) {
        this.assessmentTypeService.updateAssessmentGradeAndScoringMethod(assessmentGradeAndScoringMethodForm.getAssessmentTypeId(), assessmentGradeAndScoringMethodForm.getGradeScaleId(), assessmentGradeAndScoringMethodForm.getScoringMethod(), assessmentGradeAndScoringMethodForm.getGradeCalculationMethod(), assessmentGradeAndScoringMethodForm.getMaxMarks(), assessmentGradeAndScoringMethodForm.getIsFASABasedExam(), assessmentGradeAndScoringMethodForm.getIsTermBasedAssessment(), assessmentGradeAndScoringMethodForm.getUseAssessmentWeightageAsMaxMarks());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/coscholastic/exam-pattern/{examPatternId}"})
    public AssessmentTypeVO getCoScholasticEvaluationTermsOfExamPattern(@PathVariable Long examPatternId) {
        EvaluationType evaluationType = this.assessmentTypeService.getCoScholasticEvaluationTypeOfExamPattern(examPatternId);
        AssessmentTypeVO assessmentTypeVO = new AssessmentTypeVO();
        assessmentTypeVO.setId(evaluationType.getId());
        assessmentTypeVO.setName(evaluationType.getEvaluationScheme().getSchemeName());
        assessmentTypeVO.setDisplayName(evaluationType.getDisplayName());
        assessmentTypeVO.setExamPatternId(evaluationType.getEvaluationScheme().getId());
        assessmentTypeVO.setExamPatternName(evaluationType.getEvaluationScheme().getSchemeName());
        assessmentTypeVO.setScoringMethod(evaluationType.getScoringMethod());
        assessmentTypeVO.setGradeCalculationMethod(evaluationType.getGradeCalculationMethod());
        if (evaluationType.getGradeScale() != null) {
            assessmentTypeVO.setGradeScaleId(evaluationType.getGradeScale().getId());
        }
        assessmentTypeVO.setAssessmentTerms(EvaluationTermVOMapper.prepareEvaluationTermVOs(evaluationType.getEvaluationTerms()));
        return assessmentTypeVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/scholastic/exam-pattern/{examPatternId}"})
    public AssessmentTypeVO getScholasticEvaluationTermsOfExamPattern(@PathVariable Long examPatternId) {
        EvaluationType evaluationType = this.assessmentTypeService.getScholasticEvaluationTypeOfExamPattern(examPatternId);
        AssessmentTypeVO assessmentTypeVO = new AssessmentTypeVO();
        assessmentTypeVO.setId(evaluationType.getId());
        assessmentTypeVO.setName(evaluationType.getEvaluationScheme().getSchemeName());
        assessmentTypeVO.setDisplayName(evaluationType.getDisplayName());
        assessmentTypeVO.setExamPatternId(evaluationType.getEvaluationScheme().getId());
        assessmentTypeVO.setExamPatternName(evaluationType.getEvaluationScheme().getSchemeName());
        assessmentTypeVO.setScoringMethod(evaluationType.getScoringMethod());
        assessmentTypeVO.setGradeCalculationMethod(evaluationType.getGradeCalculationMethod());
        assessmentTypeVO.setUseAssessmentWeightageAsMaxMarks(evaluationType.getUseAssessmentWeightageAsMaxMarks());
        assessmentTypeVO.setIsFASABasedExam(evaluationType.getIsFASABasedExam());
        assessmentTypeVO.setIsTermBasedAssessment(evaluationType.getIsTermBasedAssessment());
        if (evaluationType.getGradeScale() != null) {
            assessmentTypeVO.setGradeScaleId(evaluationType.getGradeScale().getId());
        }
        assessmentTypeVO.setAssessmentTerms(EvaluationTermVOMapper.prepareEvaluationTermVOs(evaluationType.getEvaluationTerms()));
        return assessmentTypeVO;
    }
}
