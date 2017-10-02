package com.narendra.sams.academics.dao.impl;

import com.narendra.sams.academics.dao.ClassSubjectDAO;
import com.narendra.sams.academics.domain.ClassSubject;
import com.narendra.sams.academics.domain.ClassSubjectCount;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ClassSubjectDaoImpl extends HibernateDaoSupport implements ClassSubjectDAO {
    public void addSubject(ClassSubject classSubject, Long userId) {
        getHibernateTemplate().save(classSubject);
    }

    public void updateSubject(ClassSubject classSubject, Long userId) {
        ClassSubject persistClassSubject = (ClassSubject) getHibernateTemplate().load(ClassSubject.class, classSubject.getId());
        persistClassSubject.setDisplaySequenceNo(classSubject.getDisplaySequenceNo());
        persistClassSubject.setMaxMarks(classSubject.getMaxMarks());
        persistClassSubject.setSubjectCode(classSubject.getSubjectCode());
        persistClassSubject.setSubjectName(classSubject.getSubjectName());
        getHibernateTemplate().update(persistClassSubject);
    }

    public List<ClassSubjectCount> getClasswiseSubjectCount(Long academicYearId) {
        List<ClassSubjectCount> classSubjectCounts = null;
        Criteria crt = getSession().createCriteria(ClassSubject.class);
        crt.createAlias("academicYearClass", "academicYearClass").createAlias("academicYearClass.academicYear", "academicYear");
        crt.setProjection(Projections.projectionList().add(Projections.groupProperty("academicYearClass.id")).add(Projections.property("academicYearClass.displayName")).add(Projections.countDistinct("id"), "subjectCount")).addOrder(Order.asc("subjectCount"));
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            classSubjectCounts = new ArrayList();
            while (it.hasNext()) {
                Object[] col = (Object[]) it.next();
                classSubjectCounts.add(new ClassSubjectCount((Long) col[0], (String) col[1], (Long) col[2], academicYearId));
            }
        }
        return classSubjectCounts;
    }

    public List<ClassSubject> getExamClassSubjects(Long academicYearClassId) {
        Criteria crt = getSession().createCriteria(ClassSubject.class);
        crt.add(Restrictions.eq("academicYearClass.id", academicYearClassId));
        crt.addOrder(Order.asc("displaySequenceNo"));
        crt.add(Restrictions.eq("isOptional", Boolean.FALSE));
        return crt.list();
    }
}
