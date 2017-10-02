package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.CourseYearTypeDAO;
import com.narendra.sams.core.domain.CourseYearType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CourseYearTypeDAOImpl extends HibernateDaoSupport implements CourseYearTypeDAO {
    public List<CourseYearType> getAllCourseYearTypes() {
        Criteria crt = getSession().createCriteria(CourseYearType.class);
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }

    public CourseYearType loadCourseYearType(Long id) {
        return (CourseYearType) getHibernateTemplate().load(CourseYearType.class, id);
    }
}
