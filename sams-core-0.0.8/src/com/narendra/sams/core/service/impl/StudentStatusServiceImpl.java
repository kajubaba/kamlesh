package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.StudentStatusDAO;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.StudentStatusService;
import java.util.List;

public class StudentStatusServiceImpl implements StudentStatusService {
    private StudentStatusDAO studentStatusDAO;

    public StudentStatusDAO getStudentStatusDAO() {
        return this.studentStatusDAO;
    }

    public void setStudentStatusDAO(StudentStatusDAO studentStatusDAO) {
        this.studentStatusDAO = studentStatusDAO;
    }

    public List<StudentStatus> getAllStatusList(Long instituteId) {
        if (instituteId == null) {
            return null;
        }
        return this.studentStatusDAO.getAllStatusList(instituteId);
    }

    public List<StudentStatus> getActiveStatusList() {
        return this.studentStatusDAO.getActiveStatusList();
    }

    public List<StudentStatus> getStudentStatusCanAssignToStudent() {
        return this.studentStatusDAO.getStudentStatusCanAssignToStudent();
    }

    public StudentStatus getStaudentStatus(Long statusId) {
        if (statusId == null) {
            return null;
        }
        return this.studentStatusDAO.getStaudentStatus(statusId);
    }

    public Long addStatus(StudentStatus studentStatus, Long userId) throws DuplicateNameFoundException {
        if (studentStatus == null) {
            return null;
        }
        StudentStatus persistStudentStatus = this.studentStatusDAO.getStudentStatusByName(studentStatus.getName(), null);
        if (studentStatus.getId() != null || persistStudentStatus == null) {
            return this.studentStatusDAO.addStatus(studentStatus, userId);
        }
        throw new DuplicateNameFoundException("Student status [" + studentStatus.getName() + "] already exists");
    }

    public void updateStatus(StudentStatus studentStatus, Long userId) throws DuplicateNameFoundException {
        if (studentStatus != null) {
            StudentStatus persistStudentStatus = this.studentStatusDAO.getStudentStatusByName(studentStatus.getName(), null);
            if (persistStudentStatus == null || persistStudentStatus.getId().equals(studentStatus.getId())) {
                this.studentStatusDAO.updateStatus(studentStatus, userId);
                return;
            }
            throw new DuplicateNameFoundException("Student status [" + persistStudentStatus.getName() + "] already exists");
        }
    }

    public void deleteStatus(Long statusId) {
        if (statusId != null) {
            this.studentStatusDAO.deleteStatus(statusId);
        }
    }
}
