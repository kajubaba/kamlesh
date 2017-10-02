package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.StudentStatusDAO;
import com.narendra.sams.core.domain.StudentStatus;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StudentStatusDAOImpl extends HibernateDaoSupport implements StudentStatusDAO {
    public List<StudentStatus> getAllStatusList(Long instituteId) {
        Criteria crt = getSession().createCriteria(StudentStatus.class);
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }

    public StudentStatus getStudentStatusByName(String categoryName, Long instituteId) {
        Criteria crt = getSession().createCriteria(StudentStatus.class);
        crt.add(Restrictions.eq("name", categoryName));
        return (StudentStatus) crt.uniqueResult();
    }

    public List<StudentStatus> getActiveStatusList() {
        Criteria crt = getSession().createCriteria(StudentStatus.class);
        crt.add(Restrictions.eq("active", Boolean.TRUE));
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }

    public List<StudentStatus> getStudentStatusCanAssignToStudent() {
        Criteria crt = getSession().createCriteria(StudentStatus.class);
        crt.add(Restrictions.eq("active", Boolean.TRUE));
        crt.add(Restrictions.eq("canAssignToStudent", Boolean.TRUE));
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }

    public StudentStatus getStaudentStatus(Long statusId) {
        return (StudentStatus) getHibernateTemplate().get(StudentStatus.class, statusId);
    }

    public Long addStatus(StudentStatus studentStatus, Long userId) {
        return (Long) getHibernateTemplate().save(studentStatus);
    }

    public void updateStatus(StudentStatus studentStatus, Long userId) {
        StudentStatus persistStatus = (StudentStatus) getHibernateTemplate().load(StudentStatus.class, studentStatus.getId());
        persistStatus.setName(studentStatus.getName());
        persistStatus.setActive(studentStatus.getActive());
        getHibernateTemplate().update(persistStatus);
    }

    public void deleteStatus(Long statusId) {
        getHibernateTemplate().delete((StudentStatus) getHibernateTemplate().load(StudentStatus.class, statusId));
    }
}
