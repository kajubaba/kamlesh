package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.IdGenerationMethodDAO;
import com.narendra.sams.core.domain.IDGenerationMethod;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class IdGenerationMethodDAOImpl extends HibernateDaoSupport implements IdGenerationMethodDAO {
    public IDGenerationMethod getAcademicSessionClassIDGenerationMethod(Long academicSessionClassId) {
        Criteria crt = getSession().createCriteria(IDGenerationMethod.class);
        crt.createAlias("academicYearClassIDGenMethods", "academicYearClassIDGenMethod");
        crt.createAlias("academicYearClassIDGenMethod.academicYearClass", "academicYearClass");
        crt.add(Restrictions.eq("academicYearClass.id", academicSessionClassId));
        return (IDGenerationMethod) crt.uniqueResult();
    }

    public void updateNextNo(Long methodId, Long nextNo) {
        IDGenerationMethod idGenerationMethod = (IDGenerationMethod) getHibernateTemplate().load(IDGenerationMethod.class, methodId);
        idGenerationMethod.setNextNo(nextNo);
        getHibernateTemplate().update(idGenerationMethod);
    }
}
