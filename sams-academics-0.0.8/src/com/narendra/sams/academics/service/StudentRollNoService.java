package com.narendra.sams.academics.service;

import com.narendra.sams.academics.exam.domain.StudentRollNo;
import java.util.List;

public interface StudentRollNoService {
    StudentRollNo getStudentRollNo(Long l, Long l2);

    List<StudentRollNo> getStudentRollNos(Long l, Long l2);

    void saveStudentRollNos(Long l, List<StudentRollNo> list, Long l2);
}
