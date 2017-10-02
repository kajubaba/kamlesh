package com.narendra.sams.web.restws.academics.controllerhelper;

import com.narendra.sams.academics.exam.domain.EvaluationScheme;
import com.narendra.sams.web.restws.academics.exam.vo.ExamPatternVO;
import java.util.ArrayList;
import java.util.List;

public class ExamPatternControllerHelper {
    public static List<ExamPatternVO> prepareExamPatternVOs(List<EvaluationScheme> evaluationSchemes) {
        if (evaluationSchemes == null) {
            return null;
        }
        List<ExamPatternVO> examPatternVOs = new ArrayList();
        for (EvaluationScheme evaluationScheme : evaluationSchemes) {
            ExamPatternVO examPatternVO = new ExamPatternVO();
            examPatternVO.setId(evaluationScheme.getId());
            examPatternVO.setName(evaluationScheme.getSchemeName());
            if (!(evaluationScheme.getEvaluationTypes() == null || evaluationScheme.getEvaluationTypes().isEmpty())) {
                examPatternVO.setEvaluationAspectCount(Integer.valueOf(evaluationScheme.getEvaluationTypes().size()));
            }
            if (!(evaluationScheme.getAppliedOnClasses() == null || evaluationScheme.getAppliedOnClasses().isEmpty())) {
                examPatternVO.setAppliedOnClassesCount(Integer.valueOf(evaluationScheme.getAppliedOnClasses().size()));
            }
            examPatternVOs.add(examPatternVO);
        }
        return examPatternVOs;
    }
}
