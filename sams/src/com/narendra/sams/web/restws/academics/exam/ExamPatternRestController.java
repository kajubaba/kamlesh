package com.narendra.sams.web.restws.academics.exam;

import com.narendra.sams.academics.exam.domain.EvaluationScheme;
import com.narendra.sams.academics.service.ExamPatternService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.academics.controllerhelper.ExamPatternControllerHelper;
import com.narendra.sams.web.restws.academics.exam.form.ExamPatternCreationForm;
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
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/academics/exam/patterns"})
public class ExamPatternRestController {
    @Autowired
    private ExamPatternService examPatternService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST})
    public AjaxSuccessResponse createExamPattern(@RequestBody ExamPatternCreationForm examPatternCreationForm) {
        this.examPatternService.createExamPattern(examPatternCreationForm.getBaseExamPatternId(), examPatternCreationForm.getExamPatternName(), examPatternCreationForm.getAcademicSessionId(), UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId(), LoggedinUserAssistant.getLoggedInUserId());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/masters"})
    public List<ExamPatternVO> getMasterExamPatterns() {
        return ExamPatternControllerHelper.prepareExamPatternVOs(this.examPatternService.getMasterExamPatterns(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET})
    public List<ExamPatternVO> getExamPatterns(Long academicYearId) {
        return ExamPatternControllerHelper.prepareExamPatternVOs(this.examPatternService.getExamPatterns(academicYearId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{examPatternId}"})
    public ExamPatternVO getExamPattern(@PathVariable Long examPatternId) {
        EvaluationScheme evaluationScheme = this.examPatternService.getExamPattern(examPatternId);
        ExamPatternVO examPatternVO = new ExamPatternVO();
        examPatternVO.setId(evaluationScheme.getId());
        examPatternVO.setName(evaluationScheme.getSchemeName());
        return examPatternVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.DELETE}, value = {"/{examPatternId}"})
    public AjaxSuccessResponse deleteExamPattern(@PathVariable Long examPatternId) {
        this.examPatternService.deleteExamPattern(examPatternId);
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }
}
