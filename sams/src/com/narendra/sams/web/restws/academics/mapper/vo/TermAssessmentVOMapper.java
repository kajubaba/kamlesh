package com.narendra.sams.web.restws.academics.mapper.vo;

import com.narendra.sams.academics.exam.domain.TermAssessment;
import com.narendra.sams.web.restws.academics.exam.vo.TermAssessmentVO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;

public class TermAssessmentVOMapper {
    public static TermAssessmentVO prepareTermAssessmentVO(TermAssessment termAssessment) {
        TermAssessmentVO termAssessmentVO = new TermAssessmentVO();
        if (termAssessment != null) {
            termAssessmentVO.setId(termAssessment.getId());
            termAssessmentVO.setName(termAssessment.getName());
            termAssessmentVO.setDisplayName(termAssessment.getDisplayName());
            termAssessmentVO.setWeightage(termAssessment.getWeightageInAcademicSession());
            termAssessmentVO.setDisplayOrder(termAssessment.getDisplayOrder());
            termAssessmentVO.setMaxMarks(termAssessment.getMaxMarks());
        }
        return termAssessmentVO;
    }

    public static List<TermAssessmentVO> prepareTermAssessmentVOs(List<TermAssessment> termAssessments) {
        List<TermAssessmentVO> termAssessmentVOs = new ArrayList();
        if (termAssessments != null) {
            for (TermAssessment termAssessment : termAssessments) {
                termAssessmentVOs.add(prepareTermAssessmentVO(termAssessment));
            }
            Collections.sort(termAssessmentVOs, new BeanComparator("displayOrder", new NullComparator()));
        }
        return termAssessmentVOs;
    }
}
