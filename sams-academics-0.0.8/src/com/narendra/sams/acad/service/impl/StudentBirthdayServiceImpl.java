package com.narendra.sams.acad.service.impl;

import com.narendra.sams.acad.dao.StudentBirthdayDAO;
import com.narendra.sams.acad.service.StudentBirthdayService;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.util.DateUtil;
import java.util.List;

public class StudentBirthdayServiceImpl implements StudentBirthdayService {
    private StudentBirthdayDAO studentBirthdayDAO;

    public StudentBirthdayDAO getStudentBirthdayDAO() {
        return this.studentBirthdayDAO;
    }

    public void setStudentBirthdayDAO(StudentBirthdayDAO studentBirthdayDAO) {
        this.studentBirthdayDAO = studentBirthdayDAO;
    }

    public List<Student> getTodaysBirthdays(Long instituteId) {
        return this.studentBirthdayDAO.getBirthdayStudents(instituteId, StudentStatus.ELIGIBLE_FOR_BIRTHDAY_WISH, DateUtil.getSystemDate());
    }

    public List<Student> getUpcomingBirthdays(Long instituteId) {
        return null;
    }
}
