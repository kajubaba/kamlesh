package com.narendra.sams.academics.dao;

import com.narendra.sams.academics.domain.StudentAttendance;
import java.util.List;

public interface StudentAttendanceDAO {
    StudentAttendance getStudentAttendance(Long l, Long l2, Long l3);

    List<StudentAttendance> getTermAttendance(Long l, Long l2, Long l3);

    void saveStudentAttendance(StudentAttendance studentAttendance, Long l);
}
