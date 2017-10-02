package com.narendra.sams.academics.dao.impl;

import com.narendra.sams.academics.dao.StudentRollNoDAO;
import com.narendra.sams.academics.exam.domain.StudentRollNo;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StudentRollNoDAOImpl extends HibernateDaoSupport implements StudentRollNoDAO {
    public StudentRollNo getStudentRollNo(Long studentId, Long classId) {
        Criteria crt = getSession().createCriteria(StudentRollNo.class);
        crt.add(Restrictions.eq("student.id", studentId));
        crt.add(Restrictions.eq("studentClass.id", classId));
        return (StudentRollNo) crt.uniqueResult();
    }

    public List<StudentRollNo> getStudentRollNos(Long classId, Long sectionId) {
        Criteria crt = getSession().createCriteria(StudentRollNo.class);
        crt.createAlias("student", "student");
        crt.add(Restrictions.eq("studentClass.id", classId));
        if (sectionId != null) {
            crt.createAlias("student.classHistories", "classHistory");
            crt.add(Restrictions.eq("classHistory.classSection.id", sectionId));
        }
        crt.addOrder(Order.asc("student.firstName")).addOrder(Order.asc("student.lastName"));
        return crt.list();
    }

    public void saveStudentRollNos(List<StudentRollNo> studentRollNos, Long userId) {
        UserView user = (UserView) getHibernateTemplate().load(UserView.class, userId);
        for (StudentRollNo studentRollNo : studentRollNos) {
            if (studentRollNo.getId() == null) {
                studentRollNo.setCreatedBy(user);
                studentRollNo.setModifiedBy(user);
                studentRollNo.setCreatedOn(DateUtil.getSystemDateTime());
                studentRollNo.setModifiedOn(DateUtil.getSystemDateTime());
                getHibernateTemplate().save(studentRollNo);
            } else {
                StudentRollNo persistRollNo = (StudentRollNo) getHibernateTemplate().load(StudentRollNo.class, studentRollNo.getId());
                persistRollNo.setRollNo(studentRollNo.getRollNo());
                persistRollNo.setModifiedBy(user);
                persistRollNo.setModifiedOn(DateUtil.getSystemDateTime());
                getHibernateTemplate().update(persistRollNo);
            }
        }
    }
}
