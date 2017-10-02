package com.narendra.sams.academics.service;

import com.narendra.sams.academics.domain.Attendance;
import com.narendra.sams.academics.domain.StudentAttendance;
import java.util.List;

public interface StudentAttendanceService {
    StudentAttendance getStudentAttendance(Long l, Long l2, Long l3);

    List<Attendance> getTermAttendance(Long l, Long l2, Long l3);

    void saveTermAttendance(Long l, Long l2, List<Attendance> list, Long l3);
}
