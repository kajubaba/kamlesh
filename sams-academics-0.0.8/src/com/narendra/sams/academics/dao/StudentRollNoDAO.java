package com.narendra.sams.academics.dao;

import com.narendra.sams.academics.exam.domain.StudentRollNo;
import java.util.List;

public interface StudentRollNoDAO {
    StudentRollNo getStudentRollNo(Long l, Long l2);

    List<StudentRollNo> getStudentRollNos(Long l, Long l2);

    void saveStudentRollNos(List<StudentRollNo> list, Long l);
}
