package com.narendra.sams.admission.dao;

import com.narendra.sams.admission.domain.Student;
import java.util.List;

public interface FeeAdjustedStudentDAO {
    Long getFeeAdjustedStudentCount(Long l);

    List<Student> getStudentsWhoseFeeIsCustomized(Long l, Long l2);
}
