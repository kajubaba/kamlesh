package com.narendra.sams.web.restws.academics.exam;

import com.narendra.sams.academics.domain.GradeScale;
import com.narendra.sams.academics.service.GradeService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.academics.exam.vo.GradeScaleVO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/academics/exam/grade"})
public class GardeRestController {
    @Autowired
    private GradeService gradeService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<GradeScaleVO> getEvaluationTerms(Long etId) {
        return prepareGradeScaleVOs(this.gradeService.getGradeScales(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    private List<GradeScaleVO> prepareGradeScaleVOs(List<GradeScale> gradeScales) {
        List<GradeScaleVO> gradeScaleVOs = new ArrayList();
        if (gradeScales != null) {
            for (GradeScale gradeScale : gradeScales) {
                GradeScaleVO gradeScaleVO = new GradeScaleVO();
                gradeScaleVO.setId(gradeScale.getId());
                gradeScaleVO.setName(gradeScale.getName());
                gradeScaleVOs.add(gradeScaleVO);
            }
        }
        return gradeScaleVOs;
    }
}
