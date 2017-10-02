package com.narendra.sams.admission.dao;

import com.narendra.sams.admission.domain.Student;

public interface AdmissionDAO {
    Long admitStudentTemporarily(Student student);

    void renewStudent(Student student, Long l);

    void renewStudent(Long l, String str, Long l2, Long l3, Long l4, Long l5);
}
