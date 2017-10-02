package com.narendra.sams.web.restws.academics.exam;

import com.narendra.sams.academics.exam.domain.EvaluationTerm;
import com.narendra.sams.academics.exam.domain.EvaluationType;
import com.narendra.sams.academics.service.AssessmentTypeService;
import com.narendra.sams.academics.service.EvaluationTermService;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.academics.controllerhelper.EvaluationTermVOMapper;
import com.narendra.sams.web.restws.academics.exam.form.EvaluationTermForm;
import com.narendra.sams.web.restws.academics.exam.vo.EvaluationTermVO;
import com.narendra.sams.web.restws.academics.exam.vo.ExamPatternVO;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/academics/exam/evalterm"})
public class EvaluationTermRestController {
    @Autowired
    private AssessmentTypeService assessmentTypeService;
    @Autowired
    private EvaluationTermService evaluationTermService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/byet"})
    public List<EvaluationTermVO> getEvaluationTerms(Long etId) {
        return EvaluationTermVOMapper.prepareEvaluationTermVOs(this.evaluationTermService.getEvaluationTerms(etId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/co-sch/{classId}"})
    public List<EvaluationTermVO> getCoScholasticEvaluationTerms(@PathVariable Long classId) {
        return EvaluationTermVOMapper.prepareEvaluationTermVOs(this.evaluationTermService.getCoScholasticEvaluationTerms(classId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/scholastic/{examPatternId}"})
    public ExamPatternVO getScholasticEvaluationTermsOfExamPattern(@PathVariable Long examPatternId) {
        EvaluationType evaluationType = this.assessmentTypeService.getScholasticEvaluationTypeOfExamPattern(examPatternId);
        ExamPatternVO examPatternVO = new ExamPatternVO();
        examPatternVO.setId(evaluationType.getEvaluationScheme().getId());
        examPatternVO.setName(evaluationType.getEvaluationScheme().getSchemeName());
        examPatternVO.setEvaluationTypeId(evaluationType.getId());
        examPatternVO.setEvaluationTypeName(evaluationType.getDisplayName());
        examPatternVO.setScholasticEvaluationTerms(EvaluationTermVOMapper.prepareEvaluationTermVOs(evaluationType.getEvaluationTerms()));
        return examPatternVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/coscholastic/{examPatternId}"})
    public ExamPatternVO getCoScholasticEvaluationTermsOfExamPattern(@PathVariable Long examPatternId) {
        EvaluationType evaluationType = this.assessmentTypeService.getCoScholasticEvaluationTypeOfExamPattern(examPatternId);
        ExamPatternVO examPatternVO = new ExamPatternVO();
        examPatternVO.setId(evaluationType.getEvaluationScheme().getId());
        examPatternVO.setName(evaluationType.getEvaluationScheme().getSchemeName());
        examPatternVO.setEvaluationTypeId(evaluationType.getId());
        examPatternVO.setEvaluationTypeName(evaluationType.getDisplayName());
        examPatternVO.setScholasticEvaluationTerms(EvaluationTermVOMapper.prepareEvaluationTermVOs(evaluationType.getEvaluationTerms()));
        return examPatternVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/sch/{classId}"})
    public ExamPatternVO getScholasticEvaluationTerms(@PathVariable Long classId) {
        ExamPatternVO examPatternVO = new ExamPatternVO();
        EvaluationType evaluationType = this.assessmentTypeService.getScholasticEvaluationType(classId);
        if (evaluationType != null) {
            examPatternVO.setUseAssessmentWeightageAsMaxMarks(evaluationType.getUseAssessmentWeightageAsMaxMarks());
            examPatternVO.setScholasticEvaluationTerms(EvaluationTermVOMapper.prepareEvaluationTermVOs(this.evaluationTermService.getScholasticEvaluationTerms(classId)));
        }
        return examPatternVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxSuccessResponse saveEvaluationTerm(@RequestBody EvaluationTermForm evaluationTermForm) {
        EvaluationTerm evaluationTerm = new EvaluationTerm();
        evaluationTerm.setId(evaluationTermForm.getId());
        evaluationTerm.setTermName(evaluationTermForm.getName());
        evaluationTerm.setTermDisplayName(evaluationTermForm.getDisplayName());
        evaluationTerm.setDisplayOrder(evaluationTermForm.getDisplayOrder());
        evaluationTerm.setWeightageInFinalAssessment(evaluationTermForm.getWeightage());
        evaluationTerm.setWorkingDays(evaluationTermForm.getWorkingDays());
        if (evaluationTerm.getId() == null) {
            EvaluationType evaluationType = new EvaluationType();
            evaluationType.setId(evaluationTermForm.getEvaluationTypeId());
            evaluationTerm.setEvaluationType(evaluationType);
            if (evaluationTermForm.getIsScholastic().booleanValue()) {
                this.evaluationTermService.addScholasticEvaluationTerm(evaluationTerm, LoggedinUserAssistant.getLoggedInUserId());
            } else {
                this.evaluationTermService.addCoScholasticEvaluationTerm(evaluationTerm, LoggedinUserAssistant.getLoggedInUserId());
            }
        } else {
            this.evaluationTermService.updateEvaluationTerm(evaluationTerm, LoggedinUserAssistant.getLoggedInUserId());
        }
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/delete/{termId}"})
    public AjaxSuccessResponse deleteEvaluationTerm(@PathVariable Long termId) {
        this.evaluationTermService.deleteEvaluationTerm(termId);
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }
}
