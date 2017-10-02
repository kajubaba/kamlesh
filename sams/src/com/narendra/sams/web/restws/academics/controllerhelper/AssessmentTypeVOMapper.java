package com.narendra.sams.web.restws.academics.controllerhelper;

import com.narendra.sams.academics.exam.domain.EvaluationType;
import com.narendra.sams.web.restws.academics.exam.vo.AssessmentTypeVO;

public class AssessmentTypeVOMapper {
    public static AssessmentTypeVO prepareAssessmentTypeVO(EvaluationType evaluationType) {
        AssessmentTypeVO assessmentTypeVO = new AssessmentTypeVO();
        assessmentTypeVO.setId(evaluationType.getId());
        assessmentTypeVO.setScoringMethod(evaluationType.getScoringMethod());
        assessmentTypeVO.setGradeCalculationMethod(evaluationType.getGradeCalculationMethod());
        return assessmentTypeVO;
    }
}
