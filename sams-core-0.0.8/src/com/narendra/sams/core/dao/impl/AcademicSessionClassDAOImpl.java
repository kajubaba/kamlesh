package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.AcademicSessionClassDAO;
import com.narendra.sams.core.domain.AcademicYearClass;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AcademicSessionClassDAOImpl extends HibernateDaoSupport implements AcademicSessionClassDAO {
    public AcademicYearClass getClass(Long id) {
        return (AcademicYearClass) getHibernateTemplate().get(AcademicYearClass.class, id);
    }
}
