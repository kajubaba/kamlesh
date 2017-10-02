package com.narendra.sams.admission.dao.impl;

import com.narendra.sams.admission.dao.StudentActivityLogDAO;
import com.narendra.sams.admission.domain.StudentActivityLog;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StudentActivityLogDAOImpl extends HibernateDaoSupport implements StudentActivityLogDAO {
    public void addActivityLog(StudentActivityLog studentActivityLog) {
        getHibernateTemplate().save(studentActivityLog);
    }
}
