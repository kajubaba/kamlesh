package com.narendra.sams.web.restws.academics.controllerhelper;

import com.narendra.sams.academics.exam.domain.EvaluationTerm;
import com.narendra.sams.web.restws.academics.exam.vo.EvaluationTermVO;
import com.narendra.sams.web.restws.academics.mapper.vo.TermAssessmentVOMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;

public class EvaluationTermVOMapper {
    public static EvaluationTermVO prepareEvaluationTermVO(EvaluationTerm evaluationTerm) {
        EvaluationTermVO evaluationTermVO = new EvaluationTermVO();
        if (evaluationTerm != null) {
            evaluationTermVO.setId(evaluationTerm.getId());
            evaluationTermVO.setName(evaluationTerm.getTermName());
            evaluationTermVO.setDisplayName(evaluationTerm.getTermDisplayName());
            evaluationTermVO.setDisplayOrder(evaluationTerm.getDisplayOrder());
            evaluationTermVO.setWeightage(evaluationTerm.getWeightageInFinalAssessment());
            evaluationTermVO.setWorkingDays(evaluationTerm.getWorkingDays());
        }
        return evaluationTermVO;
    }

    public static List<EvaluationTermVO> prepareEvaluationTermVOs(Collection<EvaluationTerm> evaluationTerms) {
        List<EvaluationTermVO> evaluationTermsVOs = new ArrayList();
        if (evaluationTerms != null) {
            for (EvaluationTerm evaluationTerm : evaluationTerms) {
                EvaluationTermVO evaluationTermVO = prepareEvaluationTermVO(evaluationTerm);
                if (!(evaluationTerm.getTermAssessments() == null || evaluationTerm.getTermAssessments().isEmpty())) {
                    evaluationTermVO.setAssessments(TermAssessmentVOMapper.prepareTermAssessmentVOs(new ArrayList(evaluationTerm.getTermAssessments())));
                }
                evaluationTermsVOs.add(evaluationTermVO);
            }
            Collections.sort(evaluationTermsVOs, new BeanComparator("displayOrder", new NullComparator()));
        }
        return evaluationTermsVOs;
    }
}
