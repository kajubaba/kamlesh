package com.narendra.sams.admission.dao.impl;

import com.narendra.sams.admission.dao.StudentActivityDAO;
import com.narendra.sams.admission.domain.AddmissionActionOld;
import com.narendra.sams.admission.domain.ChangeRequest;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentActivity;
import com.narendra.sams.core.domain.AdmissionScheme;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StudentActivityDAOImpl extends HibernateDaoSupport implements StudentActivityDAO {
    public UserView loadUser(Long userId) {
        return (UserView) getHibernateTemplate().load(UserView.class, userId);
    }

    public AddmissionActionOld loadAddmissionAction(Long addmissionActionId) {
        return (AddmissionActionOld) getHibernateTemplate().load(AddmissionActionOld.class, addmissionActionId);
    }

    public void addStudentActivity(StudentActivity studentActivity, Long userId) {
        studentActivity.setCreatedByUser(loadUser(userId));
        getHibernateTemplate().save(studentActivity);
    }

    public void addChangeRequest(ChangeRequest busStopChnageRequest) {
        getHibernateTemplate().save(busStopChnageRequest);
    }

    public void updateStudentAdmissionScheme(Long studentId, Long newAdmissionSchemId) {
        Student student = (Student) getHibernateTemplate().load(Student.class, studentId);
        AdmissionScheme newAdmissionScheme = null;
        if (newAdmissionSchemId != null) {
            newAdmissionScheme = (AdmissionScheme) getHibernateTemplate().load(AdmissionScheme.class, newAdmissionSchemId);
        }
        student.setAdmissionScheme(newAdmissionScheme);
        if (student.getClassHistories() != null) {
            for (ClassHistory classHistory : student.getClassHistories()) {
                if (classHistory.getAcademicYear().getId().equals(student.getAcademicYearClass().getAcademicYear().getId()) && Boolean.TRUE.equals(classHistory.getActiveClass())) {
                    classHistory.setAdmissionScheme(newAdmissionScheme);
                    return;
                }
            }
        }
    }

    public void updateStudentStatus(Long studentId, Long newStudentStatusId) {
        Student persisitStudent = (Student) getHibernateTemplate().load(Student.class, studentId);
        if (persisitStudent.getAdmissionConfirmationDate() == null) {
            persisitStudent.setAdmissionConfirmationDate(DateUtil.getSystemDateTime());
        }
        StudentStatus newStudentStatus = (StudentStatus) getHibernateTemplate().load(StudentStatus.class, newStudentStatusId);
        persisitStudent.setStudentStatus(newStudentStatus);
        if (persisitStudent.getClassHistories() != null) {
            for (ClassHistory classHistory : persisitStudent.getClassHistories()) {
                if (classHistory.getAcademicYear().getId().equals(persisitStudent.getAcademicYearClass().getAcademicYear().getId()) && Boolean.TRUE.equals(classHistory.getActiveClass())) {
                    classHistory.setStudentStatus(newStudentStatus);
                    if (classHistory.getAdmissionConfirmDateTime() == null) {
                        classHistory.setAdmissionConfirmDateTime(DateUtil.getSystemDateTime());
                        return;
                    }
                    return;
                }
            }
        }
    }
}
