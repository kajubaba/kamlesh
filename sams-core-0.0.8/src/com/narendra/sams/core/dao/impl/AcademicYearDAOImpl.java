package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.AcademicYearDAO;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearAdmissionCount;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AcademicYearCourse;
import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.domain.CourseYearType;
import com.narendra.sams.core.domain.InstituteSetting;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.util.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AcademicYearDAOImpl extends HibernateDaoSupport implements AcademicYearDAO {
    public UserView loadUser(Long userId) {
        return (UserView) getHibernateTemplate().load(UserView.class, userId);
    }

    public List<AcademicYear> getFeeAcademicYears(Long instituteId) {
        Criteria criteria = getSession().createCriteria(AcademicYear.class);
        criteria.add(Restrictions.eq("institute.id", instituteId));
        criteria.add(Restrictions.eq("activeFee", Boolean.TRUE));
        criteria.addOrder(Order.desc("orderNo"));
        return criteria.list();
    }

    public AcademicYear getActiveAcademicYearForAdmission(Long instituteId) {
        return ((InstituteSetting) getSession().createCriteria(InstituteSetting.class).add(Restrictions.eq("institute.id", instituteId)).uniqueResult()).getAdmissionSettings().getActiveAcademicYear();
    }

    public AcademicYear getActiveAcademicYearForEnquiry(Long instituteId) {
        return (AcademicYear) getSession().createCriteria(AcademicYear.class).add(Restrictions.eq("institute.id", instituteId)).add(Restrictions.eq("activeForEnquiry", Boolean.TRUE)).uniqueResult();
    }

    public Long addAcademicYear(AcademicYear academicYear, Long userId) {
        UserView user = loadUser(userId);
        Date date = DateUtil.getSystemDateTime();
        academicYear.setCreatedBy(user);
        academicYear.setModifiedBy(user);
        academicYear.setCreatedDate(date);
        academicYear.setModifiedDate(date);
        return (Long) getHibernateTemplate().save(academicYear);
    }

    public void saveAcademicYearConfiguration(Long academicYearId) {
        AcademicYearAdmissionCount academicYearAdmissionCount = new AcademicYearAdmissionCount();
        academicYearAdmissionCount.setAcademicYear(loadAcademicYear(academicYearId));
        academicYearAdmissionCount.setAdmissionCount(Long.valueOf(0));
        academicYearAdmissionCount.setTransactionCount(Long.valueOf(0));
        academicYearAdmissionCount.setTempAdmissionCount(Long.valueOf(0));
        getHibernateTemplate().save(academicYearAdmissionCount);
    }

    public AcademicYear getAcademicYearById(long id) {
        return (AcademicYear) getHibernateTemplate().get(AcademicYear.class, Long.valueOf(id));
    }

    public List<AcademicYear> getAllAcademicYears(Long instituteId) {
        return getSession().createCriteria(AcademicYear.class).add(Restrictions.eq("institute.id", instituteId)).list();
    }

    public List<AcademicYear> getActiveAcademicYears(Long instituteId) {
        Criteria crt = getSession().createCriteria(AcademicYear.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.add(Restrictions.ne("status", AcademicYear.STATUS_IN_ACTIVE));
        crt.addOrder(Order.desc("orderNo"));
        return crt.list();
    }

    public Boolean isAcademicYearNameExists(Long instituteId, String name) {
        Criteria ayCrt = getSession().createCriteria(AcademicYear.class);
        ayCrt.setProjection(Projections.property("id"));
        ayCrt.add(Restrictions.eq("name", name)).add(Restrictions.eq("institute.id", instituteId));
        List<Object> list = ayCrt.list();
        if (list == null || list.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public AcademicYear getAcademicYearByName(Long instituteId, String name) {
        return (AcademicYear) getSession().createCriteria(AcademicYear.class).add(Restrictions.eq("institute.id", instituteId)).add(Restrictions.eq("name", name)).uniqueResult();
    }

    public void updateAcademicYear(AcademicYear academicYear, Long userId) throws DuplicateNameFoundException {
        AcademicYear persistAcademicYear = (AcademicYear) getHibernateTemplate().load(AcademicYear.class, academicYear.getId());
        UserView user = loadUser(userId);
        persistAcademicYear.setModifiedDate(DateUtil.getSystemDateTime());
        persistAcademicYear.setModifiedBy(user);
        persistAcademicYear.setName(academicYear.getName());
        persistAcademicYear.setFromDate(academicYear.getFromDate());
        persistAcademicYear.setToDate(academicYear.getToDate());
        persistAcademicYear.setOrderNo(academicYear.getOrderNo());
        getHibernateTemplate().save(persistAcademicYear);
    }

    public List<AcademicYearClass> getActiveClassess(Long courseId, Long academicYearId) {
        return getSession().createCriteria(AcademicYearClass.class).createAlias("courseYear", "courseYear").createAlias("courseYearSetting", "courseYearSetting").add(Restrictions.eq("courseYearSetting.active", Boolean.TRUE)).add(Restrictions.eq("academicYear.id", academicYearId)).add(Restrictions.eq("courseYear.course.id", courseId)).addOrder(Order.asc("name")).list();
    }

    public List<AcademicYearClass> getActiveAcademicYearClassess(Long affiliationAuthorityId, Long academicYearId) {
        return getSession().createCriteria(AcademicYearClass.class).createAlias("courseYear", "courseYear", 0).createAlias("courseYear.course", "course", 0).createAlias("course.affiliatedTo", "affiliatedTo", 0).createAlias("courseYearSetting", "courseYearSetting").add(Restrictions.eq("courseYearSetting.active", Boolean.TRUE)).add(Restrictions.eq("academicYear.id", academicYearId)).add(Restrictions.eq("affiliatedTo.id", affiliationAuthorityId)).addOrder(Order.asc("displayName")).addOrder(Order.asc("course.name")).list();
    }

    public List<Course> getActiveCourses(Long academicYearId, Long affiliationAuthorityId) {
        List<AcademicYearCourse> academicYearCourses = getSession().createCriteria(AcademicYearCourse.class).createAlias("course", "course").add(Restrictions.eq("academicYear.id", academicYearId)).add(Restrictions.eq("course.affiliatedTo.id", affiliationAuthorityId)).list();
        if (academicYearCourses == null || academicYearCourses.isEmpty()) {
            return null;
        }
        List<Course> courses = new ArrayList();
        for (AcademicYearCourse academicYearCourse : academicYearCourses) {
            courses.add(academicYearCourse.getCourse());
        }
        return courses;
    }

    public AcademicYearClass loadAcademicYearClass(Long id) {
        return (AcademicYearClass) getHibernateTemplate().load(AcademicYearClass.class, id);
    }

    public AcademicYear loadAcademicYear(Long academicYearId) {
        return (AcademicYear) getHibernateTemplate().load(AcademicYear.class, academicYearId);
    }

    public List<InstituteSetting> getInstituteSettings() {
        return getSession().createCriteria(InstituteSetting.class).list();
    }

    public List<AcademicYearClass> getPromotionClasses(Long courseId, Long academicYearId) {
        Criteria crt = getSession().createCriteria(AcademicYearClass.class);
        crt.createAlias("courseYear", "courseYear").createAlias("courseYearSetting", "courseYearSetting").createAlias("courseYearSetting.courseYearType", "courseYearType").add(Restrictions.eq("active", Boolean.TRUE)).add(Restrictions.eq("academicYear.id", academicYearId)).add(Restrictions.eq("courseYear.course.id", courseId)).addOrder(Order.asc("name")).add(Restrictions.eq("courseYearType.id", CourseYearType.SEMESTER_DB_ID));
        crt.setProjection(Projections.projectionList().add(Projections.property("id")).add(Projections.property("displayName")).add(Projections.min("name")).add(Projections.groupProperty("courseYear.id")));
        Iterator it = crt.list().iterator();
        List<AcademicYearClass> academicYearClasses = null;
        if (it.hasNext()) {
            academicYearClasses = new ArrayList();
            while (it.hasNext()) {
                Object[] col = (Object[]) it.next();
                AcademicYearClass academicYearClass = new AcademicYearClass();
                academicYearClass.setId((Long) col[0]);
                academicYearClass.setDisplayName((String) col[1]);
                academicYearClasses.add(academicYearClass);
            }
        }
        return academicYearClasses;
    }

    public AcademicYear getAcademicYearByOrder(Short orderNo, Long instituteId) {
        Criteria crt = getSession().createCriteria(AcademicYear.class);
        crt.add(Restrictions.eq("orderNo", orderNo));
        crt.add(Restrictions.eq("institute.id", instituteId));
        return (AcademicYear) crt.uniqueResult();
    }

    public List<AcademicYearClass> getAcademicYearClasses(Long academicYearId) {
        Criteria crt = getSession().createCriteria(AcademicYearClass.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        return crt.list();
    }

    public void publishAcademicYear(Long academicSessionId) {
        AcademicYear persistAcademicYear = (AcademicYear) getHibernateTemplate().load(AcademicYear.class, academicSessionId);
        persistAcademicYear.setStatus(AcademicYear.STATUS_PUBLISHED);
        getHibernateTemplate().update(persistAcademicYear);
    }
}
