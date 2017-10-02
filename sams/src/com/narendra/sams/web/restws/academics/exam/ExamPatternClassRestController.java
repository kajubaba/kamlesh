package com.narendra.sams.web.restws.academics.exam;

import com.narendra.sams.academics.exam.domain.EvaluationSchemeClass;
import com.narendra.sams.academics.service.ExamPatternClassService;
import com.narendra.sams.academics.service.ExamPatternService;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.admission.vo.AcademicYearClassVO;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/academics/exam/pattern/class"})
public class ExamPatternClassRestController {
    @Autowired
    private ExamPatternClassService examPatternClassService;
    @Autowired
    private ExamPatternService examPatternService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list/{examPatternId}"})
    public List<AcademicYearClassVO> getClasses(@PathVariable Long examPatternId) {
        return prepareClassVOs(this.examPatternClassService.getExamPatternClasses(examPatternId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/notadded/{examPatternId}"})
    public List<AcademicYearClassVO> getNotAddedClasses(@PathVariable Long examPatternId) {
        return prepareNotAddededClassVOs(this.examPatternClassService.getNotAssociatedClasses(this.examPatternService.getExamPattern(examPatternId).getAcademicYear().getId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/delete/{examPatternClassId}"})
    public AjaxSuccessResponse deleteClass(@PathVariable Long examPatternClassId) {
        this.examPatternClassService.removeClassFromExamPattern(examPatternClassId);
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/import/{examPatternId}"})
    public AjaxSuccessResponse importClasses(@PathVariable Long examPatternId, @RequestBody List<Integer> selectedClasses) {
        this.examPatternClassService.addClassesInExamPattern(selectedClasses, examPatternId);
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }

    private List<AcademicYearClassVO> prepareClassVOs(List<EvaluationSchemeClass> evaluationSchemeClasses) {
        List<AcademicYearClassVO> academicYearClassVOs = new ArrayList();
        if (evaluationSchemeClasses != null) {
            for (EvaluationSchemeClass evaluationSchemeClass : evaluationSchemeClasses) {
                AcademicYearClassVO academicYearClassVO = new AcademicYearClassVO();
                academicYearClassVO.setClassId(evaluationSchemeClass.getId());
                academicYearClassVO.setClassName(evaluationSchemeClass.getAcademicYearClass().getDisplayName());
                academicYearClassVOs.add(academicYearClassVO);
            }
        }
        return academicYearClassVOs;
    }

    private List<AcademicYearClassVO> prepareNotAddededClassVOs(List<AcademicYearClass> academicYearClasses) {
        List<AcademicYearClassVO> academicYearClassVOs = new ArrayList();
        if (academicYearClasses != null) {
            for (AcademicYearClass academicYearClass : academicYearClasses) {
                AcademicYearClassVO academicYearClassVO = new AcademicYearClassVO();
                academicYearClassVO.setClassId(academicYearClass.getId());
                academicYearClassVO.setClassName(academicYearClass.getDisplayName());
                academicYearClassVOs.add(academicYearClassVO);
            }
        }
        return academicYearClassVOs;
    }
}
