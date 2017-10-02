package com.narendra.sams.admission.service;

import com.narendra.sams.admission.domain.Student;
import java.util.List;

public interface FeeAdjustedStudentService {
    Long getFeeAdjustedStudentCount(Long l);

    List<Student> getStudentsWhoseFeeIsCustomized(Long l, Long l2);
}
