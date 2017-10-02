package com.narendra.sams.acad.service;

import com.narendra.sams.admission.domain.Student;
import java.util.List;

public interface StudentBirthdayService {
    List<Student> getTodaysBirthdays(Long l);

    List<Student> getUpcomingBirthdays(Long l);
}
