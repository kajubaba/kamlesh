package com.narendra.sams.admission.dao.impl;

import com.narendra.sams.admission.dao.StudentConversationDAO;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentConversation;
import com.narendra.sams.admission.domain.StudentConversationType;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StudentConversationDAOImpl extends HibernateDaoSupport implements StudentConversationDAO {
    public void addConversation(StudentConversation studentConversation, Long userId, Long instituteId) {
        Date date = DateUtil.getSystemDateTime();
        UserView userView = (UserView) getHibernateTemplate().load(UserView.class, userId);
        studentConversation.setCreatedBy(userView);
        studentConversation.setLastModifiedBy(userView);
        studentConversation.setCreatedDateTime(date);
        studentConversation.setLastModifiedDateTime(date);
        studentConversation.setConversationDate(date);
        Student persistStudent = (Student) getHibernateTemplate().load(Student.class, studentConversation.getStudent().getId());
        studentConversation.setStudent(persistStudent);
        studentConversation.setStudentClass(persistStudent.getAcademicYearClass());
        studentConversation.setInstitute((Institute) getHibernateTemplate().load(Institute.class, instituteId));
        getHibernateTemplate().save(studentConversation);
    }

    public void updateConversation(StudentConversation studentConversation, Long userId) {
        StudentConversation persistConversation = (StudentConversation) getHibernateTemplate().load(StudentConversation.class, studentConversation.getId());
        persistConversation.setConversation(studentConversation.getConversation());
        persistConversation.setConversationAgenda(studentConversation.getConversationAgenda());
        persistConversation.setConversationUser(studentConversation.getConversationUser());
        persistConversation.setConversationWith(studentConversation.getConversationWith());
        if (studentConversation.getConversationType() != null) {
            persistConversation.setConversationType((StudentConversationType) getHibernateTemplate().load(StudentConversationType.class, studentConversation.getConversationType().getId()));
        } else {
            persistConversation.setConversationType(null);
        }
        persistConversation.setLastModifiedDateTime(DateUtil.getSystemDateTime());
        persistConversation.setLastModifiedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        getHibernateTemplate().update(persistConversation);
    }

    public StudentConversation getConversation(Long id) {
        return (StudentConversation) getHibernateTemplate().get(StudentConversation.class, id);
    }

    public List<StudentConversation> getStudentConversations(Long studentId) {
        Criteria crt = getSession().createCriteria(StudentConversation.class);
        crt.add(Restrictions.eq("student.id", studentId));
        crt.addOrder(Order.desc("createdDateTime"));
        return crt.list();
    }

    public List<StudentConversation> getStudentConversations(Date from, Date to, Long by, Long mode, Long instituteId) {
        Criteria crt = getSession().createCriteria(StudentConversation.class);
        if (from != null) {
            crt.add(Restrictions.ge("conversationDate", from));
        }
        if (to != null) {
            crt.add(Restrictions.le("conversationDate", to));
        }
        if (by != null) {
            crt.add(Restrictions.eq("createdBy.id", by));
        }
        if (mode != null) {
            crt.add(Restrictions.eq("conversationType.id", mode));
        }
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.addOrder(Order.desc("conversationDate"));
        return crt.list();
    }

    public List<StudentConversationType> getActiveConversationTypes(Long instituteId) {
        Criteria crt = getSession().createCriteria(StudentConversationType.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.add(Restrictions.eq("active", Boolean.TRUE));
        crt.addOrder(Order.asc("name"));
        return crt.list();
    }
}
