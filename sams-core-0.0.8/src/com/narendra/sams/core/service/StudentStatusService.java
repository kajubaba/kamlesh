package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import java.util.List;

public interface StudentStatusService {
    Long addStatus(StudentStatus studentStatus, Long l) throws DuplicateNameFoundException;

    void deleteStatus(Long l);

    List<StudentStatus> getActiveStatusList();

    List<StudentStatus> getAllStatusList(Long l);

    StudentStatus getStaudentStatus(Long l);

    List<StudentStatus> getStudentStatusCanAssignToStudent();

    void updateStatus(StudentStatus studentStatus, Long l) throws DuplicateNameFoundException;
}
