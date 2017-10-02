package com.narendra.sams.web.restws.academics.controllerhelper;

import com.narendra.sams.academics.domain.GradeScalePoint;
import com.narendra.sams.web.restws.academics.exam.vo.GradeScalePointVO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;

public class GradeScalePointVOMapper {
    public static List<GradeScalePointVO> prepareGradeScalePointVOs(List<GradeScalePoint> gradeScalePoints) {
        List<GradeScalePointVO> gradeScalePointVOs = new ArrayList();
        if (gradeScalePoints != null) {
            for (GradeScalePoint gradeScalePoint : gradeScalePoints) {
                GradeScalePointVO gradeScalePointVO = new GradeScalePointVO();
                gradeScalePointVO.setId(gradeScalePoint.getId());
                gradeScalePointVO.setGrade(gradeScalePoint.getGrade());
                gradeScalePointVO.setGradeMeaning(gradeScalePoint.getGradeMeaning());
                gradeScalePointVO.setGradPointFrom(gradeScalePoint.getGradPointFrom());
                gradeScalePointVO.setGradPointTo(gradeScalePoint.getGradPointTo());
                gradeScalePointVO.setMarksFrom(gradeScalePoint.getMarksFrom());
                gradeScalePointVO.setMarksTo(gradeScalePoint.getMarksTo());
                gradeScalePointVO.setDisplayOrder(gradeScalePoint.getDisplaySeqNo());
                gradeScalePointVOs.add(gradeScalePointVO);
            }
            Collections.sort(gradeScalePointVOs, new BeanComparator("displayOrder", new NullComparator()));
        }
        return gradeScalePointVOs;
    }
}
