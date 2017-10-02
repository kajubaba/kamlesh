package com.narendra.sams.academics.dao.impl;

import com.narendra.sams.acad.domain.ClassSectionCount;
import com.narendra.sams.academics.dao.ClassSectionDAO;
import com.narendra.sams.admission.domain.ClassSection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ClassSectionDAOImpl extends HibernateDaoSupport implements ClassSectionDAO {
    public Long addClassSection(ClassSection classSection, Long userId) {
        return (Long) getHibernateTemplate().save(classSection);
    }

    public void updateClassSection(ClassSection classSection, Long userId) {
        ClassSection persistClassSection = (ClassSection) getHibernateTemplate().load(ClassSection.class, classSection.getId());
        persistClassSection.setSectionName(classSection.getSectionName());
        persistClassSection.setSectionCode(classSection.getSectionCode());
        getHibernateTemplate().update(persistClassSection);
    }

    public List<ClassSectionCount> getClasswiseSectionCount(Long academicYearId) {
        List<ClassSectionCount> classSectionCounts = null;
        Criteria crt = getSession().createCriteria(ClassSection.class);
        crt.createAlias("academicYearClass", "academicYearClass").createAlias("academicYearClass.academicYear", "academicYear");
        crt.setProjection(Projections.projectionList().add(Projections.groupProperty("academicYearClass.id")).add(Projections.property("academicYearClass.displayName")).add(Projections.countDistinct("id"), "sectionCount")).addOrder(Order.asc("sectionCount"));
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            classSectionCounts = new ArrayList();
            while (it.hasNext()) {
                Object[] col = (Object[]) it.next();
                classSectionCounts.add(new ClassSectionCount((Long) col[0], (String) col[1], (Long) col[2], academicYearId));
            }
        }
        return classSectionCounts;
    }

    public List<ClassSection> getClassSections(Long academicYearClassId) {
        Criteria crt = getSession().createCriteria(ClassSection.class);
        crt.add(Restrictions.eq("academicYearClass.id", academicYearClassId));
        return crt.list();
    }
}
