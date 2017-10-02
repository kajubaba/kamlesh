package com.narendra.sams.transportation.dao.impl;

import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.transportation.dao.StudentPickupDropPointDAO;
import com.narendra.sams.transportation.domain.BusStopPoint;
import com.narendra.sams.transportation.domain.StudentPickupDropPoint;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StudentPickupDropPointDAOImpl extends HibernateDaoSupport implements StudentPickupDropPointDAO {
    public StudentPickupDropPoint getStudentPickupDropPoint(Long studentId, Long academicYearId) {
        Criteria crt = getSession().createCriteria(StudentPickupDropPoint.class);
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        return (StudentPickupDropPoint) crt.uniqueResult();
    }

    public void updateStudentDropPoint(Long studentId, Long academicYearId, Long dropPointId, Long userId) {
        StudentPickupDropPoint studentPickupDropPoint = getStudentPickupDropPoint(studentId, academicYearId);
        if (studentPickupDropPoint == null) {
            studentPickupDropPoint = new StudentPickupDropPoint();
        }
        studentPickupDropPoint.setAcademicYear((AcademicYear) getHibernateTemplate().load(AcademicYear.class, academicYearId));
        if (dropPointId == null) {
            studentPickupDropPoint.setDropPoint(null);
        } else {
            BusStopPoint busStopPoint = (BusStopPoint) getHibernateTemplate().load(BusStopPoint.class, dropPointId);
            studentPickupDropPoint.setDropPoint(busStopPoint);
            studentPickupDropPoint.setAcademicYearBusStop(busStopPoint.getBusFee());
        }
        studentPickupDropPoint.setStudent((Student) getHibernateTemplate().load(Student.class, studentId));
        studentPickupDropPoint.setLastModifiedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        studentPickupDropPoint.setLastModifiedDateTime(DateUtil.getSystemDateTime());
        getHibernateTemplate().saveOrUpdate(studentPickupDropPoint);
    }

    public void updateStudentPickupPoint(Long studentId, Long academicYearId, Long pickupPointId, Long userId) {
        StudentPickupDropPoint studentPickupDropPoint = getStudentPickupDropPoint(studentId, academicYearId);
        if (studentPickupDropPoint == null) {
            studentPickupDropPoint = new StudentPickupDropPoint();
        }
        studentPickupDropPoint.setAcademicYear((AcademicYear) getHibernateTemplate().load(AcademicYear.class, academicYearId));
        if (pickupPointId == null) {
            studentPickupDropPoint.setPickupPoint(null);
        } else {
            BusStopPoint busStopPoint = (BusStopPoint) getHibernateTemplate().load(BusStopPoint.class, pickupPointId);
            studentPickupDropPoint.setPickupPoint(busStopPoint);
            studentPickupDropPoint.setAcademicYearBusStop(busStopPoint.getBusFee());
        }
        studentPickupDropPoint.setStudent((Student) getHibernateTemplate().load(Student.class, studentId));
        studentPickupDropPoint.setLastModifiedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        studentPickupDropPoint.setLastModifiedDateTime(DateUtil.getSystemDateTime());
        getHibernateTemplate().saveOrUpdate(studentPickupDropPoint);
    }

    public void addStudentPickupDropPoint(StudentPickupDropPoint studentPickupDropPoint, Long userId) {
        studentPickupDropPoint.setLastModifiedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        studentPickupDropPoint.setLastModifiedDateTime(DateUtil.getSystemDateTime());
        getHibernateTemplate().save(studentPickupDropPoint);
    }
}
