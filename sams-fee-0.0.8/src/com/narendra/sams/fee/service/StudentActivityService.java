package com.narendra.sams.fee.service;

import com.narendra.sams.core.exception.OperationCanNotSucceedException;

public interface StudentActivityService {
    void addStudentActivity(Long l, Long l2, String str, String str2, Long l3);

    void updateStudentAdmissionScheme(Long l, Long l2, Long l3, String str);

    void updateStudentBusStop(Long l, Long l2, Long l3, String str, Boolean bool) throws Exception;

    void updateStudentClass(Long l, Long l2, Long l3, String str, Boolean bool) throws Exception;

    void updateStudentStatus(Long l, Long l2, Long l3, String str) throws OperationCanNotSucceedException;
}
