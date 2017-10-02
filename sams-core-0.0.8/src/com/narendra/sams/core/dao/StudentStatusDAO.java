package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.StudentStatus;
import java.util.List;

public interface StudentStatusDAO {
    Long addStatus(StudentStatus studentStatus, Long l);

    void deleteStatus(Long l);

    List<StudentStatus> getActiveStatusList();

    List<StudentStatus> getAllStatusList(Long l);

    StudentStatus getStaudentStatus(Long l);

    StudentStatus getStudentStatusByName(String str, Long l);

    List<StudentStatus> getStudentStatusCanAssignToStudent();

    void updateStatus(StudentStatus studentStatus, Long l);
}
