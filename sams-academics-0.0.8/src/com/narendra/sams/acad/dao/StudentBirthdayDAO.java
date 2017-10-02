package com.narendra.sams.acad.dao;

import com.narendra.sams.admission.domain.Student;
import java.util.Date;
import java.util.List;

public interface StudentBirthdayDAO {
    List<Student> getBirthdayStudents(Long l, Long[] lArr, Date date);
}
