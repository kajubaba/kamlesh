package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.StudentCategoryDAO;
import com.narendra.sams.core.domain.StudentCategory;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StudentCategoryDAOImpl extends HibernateDaoSupport implements StudentCategoryDAO {
    public List<StudentCategory> getActiveStudentCategories(Long instituteId) {
        Criteria crt = getSession().createCriteria(StudentCategory.class);
        crt.addOrder(Order.asc("name"));
        crt.add(Restrictions.eq("active", Boolean.TRUE));
        crt.add(Restrictions.eq("institute.id", instituteId));
        return crt.list();
    }

    public List<StudentCategory> getAllStudentCategories(Long instituteId) {
        Criteria crt = getSession().createCriteria(StudentCategory.class);
        crt.addOrder(Order.asc("name"));
        crt.add(Restrictions.eq("institute.id", instituteId));
        return crt.list();
    }

    public Long addStudentCategory(StudentCategory studentCategory, Long userId) {
        UserView user = (UserView) getHibernateTemplate().load(UserView.class, userId);
        studentCategory.setCreatedBy(user);
        studentCategory.setModifiedBy(user);
        studentCategory.setCreatedDateTime(DateUtil.getSystemDateTime());
        studentCategory.setModifiedDateTime(DateUtil.getSystemDateTime());
        return (Long) getHibernateTemplate().save(studentCategory);
    }

    public void updateStudentCategory(StudentCategory studentCategory, Long userId) {
        StudentCategory persistStudentCategory = (StudentCategory) getHibernateTemplate().load(StudentCategory.class, studentCategory.getId());
        UserView user = (UserView) getHibernateTemplate().load(UserView.class, userId);
        persistStudentCategory.setName(studentCategory.getName());
        persistStudentCategory.setActive(studentCategory.getActive());
        persistStudentCategory.setModifiedBy(user);
        persistStudentCategory.setModifiedDateTime(DateUtil.getSystemDateTime());
    }

    public StudentCategory getStudentCategory(Long studentCategoryId) {
        return (StudentCategory) getHibernateTemplate().get(StudentCategory.class, studentCategoryId);
    }

    public StudentCategory getStudentCategoryByName(String categoryName, Long instituteId) {
        Criteria crt = getSession().createCriteria(StudentCategory.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.add(Restrictions.eq("name", categoryName));
        return (StudentCategory) crt.uniqueResult();
    }
}
