package com.narendra.sams.academics.dao.impl;

import com.narendra.sams.academics.dao.ExamPatternDAO;
import com.narendra.sams.academics.exam.domain.EvaluationScheme;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ExamPatternDAOImpl extends HibernateDaoSupport implements ExamPatternDAO {
    public List<EvaluationScheme> getMasterExamPatterns(Long instituteId) {
        Criteria crt = getSession().createCriteria(EvaluationScheme.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.add(Restrictions.eq("isMaster", Boolean.TRUE));
        crt.addOrder(Order.asc("schemeName"));
        return crt.list();
    }

    public List<EvaluationScheme> getExamPatterns(Long academicYearId) {
        Criteria crt = getSession().createCriteria(EvaluationScheme.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("isMaster", Boolean.FALSE));
        crt.addOrder(Order.asc("schemeName"));
        return crt.list();
    }

    public EvaluationScheme getExamPattern(Long examPatternId) {
        return (EvaluationScheme) getHibernateTemplate().get(EvaluationScheme.class, examPatternId);
    }

    public void addExamPattern(EvaluationScheme evaluationScheme, Long instituteId, Long userId) {
        UserView user = (UserView) getHibernateTemplate().load(UserView.class, userId);
        evaluationScheme.setInstitute((Institute) getHibernateTemplate().load(Institute.class, instituteId));
        evaluationScheme.setCreatedByUser(user);
        evaluationScheme.setLastModifiedByUser(user);
        evaluationScheme.setCreatedDateTime(DateUtil.getSystemDateTime());
        evaluationScheme.setLastModifiedDateTime(DateUtil.getSystemDateTime());
        getHibernateTemplate().save(evaluationScheme);
    }

    public void deleteExamPattern(Long examPatternId) {
        getHibernateTemplate().delete(getHibernateTemplate().get(EvaluationScheme.class, examPatternId));
    }
}
