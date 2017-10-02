package com.narendra.sams.admission.dao;

import com.narendra.sams.admission.domain.AddmissionActionOld;
import com.narendra.sams.admission.domain.ChangeRequest;
import com.narendra.sams.admission.domain.StudentActivity;

public interface StudentActivityDAO {
    void addChangeRequest(ChangeRequest changeRequest);

    void addStudentActivity(StudentActivity studentActivity, Long l);

    AddmissionActionOld loadAddmissionAction(Long l);

    void updateStudentAdmissionScheme(Long l, Long l2);

    void updateStudentStatus(Long l, Long l2);
}
