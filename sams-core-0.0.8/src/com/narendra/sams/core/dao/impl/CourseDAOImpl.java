package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.address.dao.UserViewDAO;
import com.narendra.sams.core.dao.AffiliationAuthorityDAO;
import com.narendra.sams.core.dao.CourseDAO;
import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.domain.CourseYear;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CourseDAOImpl extends HibernateDaoSupport implements CourseDAO {
    private AffiliationAuthorityDAO affiliationAuthorityDAO;
    private UserViewDAO userViewDAO;

    public UserViewDAO getUserViewDAO() {
        return this.userViewDAO;
    }

    public void setUserViewDAO(UserViewDAO userViewDAO) {
        this.userViewDAO = userViewDAO;
    }

    public AffiliationAuthorityDAO getAffiliationAuthorityDAO() {
        return this.affiliationAuthorityDAO;
    }

    public void setAffiliationAuthorityDAO(AffiliationAuthorityDAO affiliationAuthorityDAO) {
        this.affiliationAuthorityDAO = affiliationAuthorityDAO;
    }

    public List<Course> getAllCourses(Long instituteId) {
        Criteria crt = getSession().createCriteria(Course.class);
        crt.add(Restrictions.eq("institute.id", instituteId)).addOrder(Order.asc("name"));
        return crt.list();
    }

    public Course getCourse(Long courseId) {
        return (Course) getHibernateTemplate().get(Course.class, courseId);
    }

    public Long addCourse(Course course, Long userId) {
        UserView user = this.userViewDAO.loadUser(userId);
        Date date = DateUtil.getSystemDateTime();
        course.setCreatedBy(user);
        course.setModifiedBy(user);
        course.setCreatedDate(date);
        course.setModifiedDate(date);
        return (Long) getHibernateTemplate().save(course);
    }

    public void updateCourse(Course course, Long userId) {
        Course persistCourse = (Course) getHibernateTemplate().load(Course.class, course.getId());
        UserView user = this.userViewDAO.loadUser(userId);
        persistCourse.setName(course.getName());
        persistCourse.setDisplayName(course.getDisplayName());
        persistCourse.setDuration(persistCourse.getDuration());
        persistCourse.setModifiedBy(user);
        persistCourse.setModifiedDate(DateUtil.getSystemDateTime());
        if (!(course.getAffiliatedTo() == null || course.getAffiliatedTo().getId() == null)) {
            persistCourse.setAffiliatedTo(this.affiliationAuthorityDAO.loadAffiliationAuthority(course.getAffiliatedTo().getId()));
        }
        getHibernateTemplate().update(persistCourse);
    }

    public Boolean isCourseNameExist(Long affiliationAuthorityId, String courseName) {
        Criteria courseCrt = getSession().createCriteria(Course.class);
        courseCrt.add(Restrictions.eq("affiliatedTo.id", affiliationAuthorityId));
        courseCrt.add(Restrictions.eq("name", courseName).ignoreCase());
        List<Object> list = courseCrt.list();
        if (list == null || list.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Course loadCourseByNameAndAffiliationAthority(Long affiliationAuthorityId, String courseName) {
        Criteria courseCrt = getSession().createCriteria(Course.class);
        courseCrt.add(Restrictions.eq("affiliatedTo.id", affiliationAuthorityId));
        courseCrt.add(Restrictions.eq("name", courseName).ignoreCase());
        return (Course) courseCrt.uniqueResult();
    }

    public List<Course> getCourses(Collection<Long> ids) {
        Criteria crt = getSession().createCriteria(Course.class);
        crt.add(Restrictions.in("id", ids));
        return crt.list();
    }

    public CourseYear loadCourseYear(Long id) {
        return (CourseYear) getHibernateTemplate().load(CourseYear.class, id);
    }
}
