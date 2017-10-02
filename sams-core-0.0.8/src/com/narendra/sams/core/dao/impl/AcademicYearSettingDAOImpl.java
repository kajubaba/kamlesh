package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.AcademicYearSettingDAO;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AcademicYearCourse;
import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.domain.CourseYearSetting;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AcademicYearSettingDAOImpl extends HibernateDaoSupport implements AcademicYearSettingDAO {
    public List<AcademicYearCourse> getAcademicYearCourses(Long academicYearId, Long affiliationAuthorityId) {
        Criteria crt = getSession().createCriteria(AcademicYearCourse.class);
        crt.createAlias("course", "course");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("course.affiliatedTo.id", affiliationAuthorityId));
        crt.add(Restrictions.eq("active", Boolean.TRUE));
        crt.add(Restrictions.gt("course.duration", Short.valueOf((short) 1)));
        return crt.list();
    }

    public List<AcademicYearCourse> getAcademicYearCourses(Long academicYearId) {
        Criteria crt = getSession().createCriteria(AcademicYearCourse.class);
        crt.createAlias("course", "course");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        return crt.list();
    }

    public List<Course> getActiveCourses(Long academicYearId, Long affiliationAuthorityId) {
        Criteria courseCrt = getSession().createCriteria(Course.class);
        courseCrt.createAlias("academicYears", "academicYearCourse");
        courseCrt.add(Restrictions.eq("affiliatedTo.id", affiliationAuthorityId));
        courseCrt.add(Restrictions.eq("academicYearCourse.active", Boolean.TRUE));
        courseCrt.add(Restrictions.eq("academicYearCourse.academicYear.id", academicYearId));
        return courseCrt.list();
    }

    public List<CourseYearSetting> getAllCourseYearSettings(Long courseId, Long academicYearId) {
        Criteria courseYearSettingsCrt = getSession().createCriteria(CourseYearSetting.class);
        courseYearSettingsCrt.createAlias("courseYear", "courseYear");
        courseYearSettingsCrt.add(Restrictions.eq("academicYear.id", academicYearId));
        courseYearSettingsCrt.add(Restrictions.eq("courseYear.course.id", courseId));
        courseYearSettingsCrt.addOrder(Order.asc("courseYear.order"));
        return courseYearSettingsCrt.list();
    }

    public void removeCourseFromAcademicYear(Long academicYearCourseId) {
        getHibernateTemplate().delete(getHibernateTemplate().load(AcademicYearCourse.class, academicYearCourseId));
    }

    public List<Course> getNotAddedCourses(Long academicYearId, Long affiliationAuthorityId) {
        Criteria academicYearCourseCrt = getSession().createCriteria(AcademicYearCourse.class);
        academicYearCourseCrt.createAlias("course", "course");
        academicYearCourseCrt.createAlias("course.affiliatedTo", "affiliatedTo");
        academicYearCourseCrt.add(Restrictions.eq("academicYear.id", academicYearId));
        academicYearCourseCrt.add(Restrictions.eq("affiliatedTo.id", affiliationAuthorityId));
        List<AcademicYearCourse> academicYearCourses = academicYearCourseCrt.list();
        Collection<Long> courseIds = null;
        if (!(academicYearCourses == null || academicYearCourses.isEmpty())) {
            courseIds = new HashSet();
            for (AcademicYearCourse academicYearCourse : academicYearCourses) {
                courseIds.add(academicYearCourse.getCourse().getId());
            }
        }
        Criteria courseCrt = getSession().createCriteria(Course.class);
        courseCrt.add(Restrictions.eq("affiliatedTo.id", affiliationAuthorityId));
        if (courseIds != null) {
            courseCrt.add(Restrictions.not(Restrictions.in("id", courseIds)));
        }
        return courseCrt.list();
    }

    public List<Course> getNotAddedCourses(Long academicYearId) {
        AcademicYear academicYear = (AcademicYear) getHibernateTemplate().get(AcademicYear.class, academicYearId);
        Criteria academicYearCourseCrt = getSession().createCriteria(AcademicYearCourse.class);
        academicYearCourseCrt.createAlias("course", "course");
        academicYearCourseCrt.createAlias("course.affiliatedTo", "affiliatedTo");
        academicYearCourseCrt.add(Restrictions.eq("academicYear.id", academicYearId));
        List<AcademicYearCourse> academicYearCourses = academicYearCourseCrt.list();
        Collection<Long> courseIds = null;
        if (!(academicYearCourses == null || academicYearCourses.isEmpty())) {
            courseIds = new HashSet();
            for (AcademicYearCourse academicYearCourse : academicYearCourses) {
                courseIds.add(academicYearCourse.getCourse().getId());
            }
        }
        Criteria courseCrt = getSession().createCriteria(Course.class);
        courseCrt.add(Restrictions.eq("institute.id", academicYear.getInstitute().getId()));
        if (courseIds != null) {
            courseCrt.add(Restrictions.not(Restrictions.in("id", courseIds)));
        }
        return courseCrt.list();
    }

    public void addCoursesInAcademicYear(List<AcademicYearCourse> academicYearCourses) {
        if (academicYearCourses != null) {
            for (AcademicYearCourse academicYearCourse : academicYearCourses) {
                getHibernateTemplate().saveOrUpdate(academicYearCourse);
            }
        }
    }

    public CourseYearSetting getCourseYearSetting(Long courseYearSettingId) {
        return (CourseYearSetting) getHibernateTemplate().get(CourseYearSetting.class, courseYearSettingId);
    }

    public CourseYearSetting loadCourseYearSetting(Long courseYearSettingId) {
        return (CourseYearSetting) getHibernateTemplate().load(CourseYearSetting.class, courseYearSettingId);
    }

    public AcademicYearClass loadAcademicYearClass(Long academicYearClassId) {
        return (AcademicYearClass) getHibernateTemplate().load(AcademicYearClass.class, academicYearClassId);
    }

    public List<CourseYearSetting> getActiveCourseYearSettings(Long academicYearid) {
        Criteria crt = getSession().createCriteria(CourseYearSetting.class);
        crt.add(Restrictions.eq("active", Boolean.TRUE));
        crt.add(Restrictions.eq("academicYear.id", academicYearid));
        return crt.list();
    }

    public List<CourseYearSetting> getInvidualCourseSettings(Long academicYearId, Long affiliationAuthorityId) {
        Criteria crt = getSession().createCriteria(CourseYearSetting.class);
        crt.createAlias("courseYear", "courseYear");
        crt.createAlias("courseYear.course", "course");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        if (affiliationAuthorityId != null) {
            crt.createAlias("course.affiliatedTo", "affiliatedTo");
            crt.add(Restrictions.eq("affiliatedTo.id", affiliationAuthorityId));
        }
        return crt.list();
    }
}
