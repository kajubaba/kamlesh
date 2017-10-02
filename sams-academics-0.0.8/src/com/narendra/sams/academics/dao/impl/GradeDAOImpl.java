package com.narendra.sams.academics.dao.impl;

import com.narendra.sams.academics.dao.GradeDAO;
import com.narendra.sams.academics.domain.GradeScale;
import com.narendra.sams.academics.domain.GradeScalePoint;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class GradeDAOImpl extends HibernateDaoSupport implements GradeDAO {
    public List<GradeScale> getGradeScales(Long instituteId) {
        Criteria crt = getSession().createCriteria(GradeScale.class);
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }

    public List<GradeScalePoint> getGradeScalePoints(Long gradeScaleId) {
        Criteria crt = getSession().createCriteria(GradeScalePoint.class);
        crt.add(Restrictions.eq("gradeScale.id", gradeScaleId));
        crt.addOrder(Order.asc("grade"));
        return crt.list();
    }
}
