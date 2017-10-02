package com.narendra.sams.acad.dao.impl;

import com.narendra.sams.acad.dao.StudentSectionDAO;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.ClassSection;
import com.narendra.sams.core.domain.StudentStatus;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StudentSectionDAOImpl extends HibernateDaoSupport implements StudentSectionDAO {
    public List<ClassHistory> getStudents(Long academicYearClassId, Long sectionId) {
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.add(Restrictions.eq("academicYearClass.id", academicYearClassId));
        if (sectionId != null) {
            crt.add(Restrictions.eq("classSection.id", sectionId));
        }
        crt.add(Restrictions.in("studentStatus.id", StudentStatus.ELIGIBLE_FOR_BIRTHDAY_WISH));
        return crt.list();
    }

    public List<ClassHistory> getStudents(Long academicYearClassId, Long sectionId, Long studentStatus) {
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.createAlias("student", "student");
        crt.add(Restrictions.eq("academicYearClass.id", academicYearClassId));
        if (sectionId != null) {
            crt.add(Restrictions.eq("classSection.id", sectionId));
        }
        crt.add(Restrictions.eq("studentStatus.id", studentStatus));
        crt.addOrder(Order.asc("student.firstName")).addOrder(Order.asc("student.lastName"));
        return crt.list();
    }

    public void updateStudentSection(Collection<Long> studentIds, Long studentClassId, Long newSectionId) {
        Criteria crt = getSession().createCriteria(ClassHistory.class);
        crt.add(Restrictions.eq("academicYearClass.id", studentClassId));
        crt.add(Restrictions.in("student.id", studentIds));
        List<ClassHistory> classHistories = crt.list();
        if (classHistories != null && !classHistories.isEmpty()) {
            ClassSection newClassSection = (ClassSection) getHibernateTemplate().load(ClassSection.class, newSectionId);
            for (ClassHistory classHistory : classHistories) {
                classHistory.setClassSection(newClassSection);
                if (classHistory.getStudent().getAcademicYearClass().getId().equals(studentClassId)) {
                    classHistory.getStudent().setClassSection(newClassSection);
                }
            }
            getHibernateTemplate().saveOrUpdateAll(classHistories);
        }
    }
}
