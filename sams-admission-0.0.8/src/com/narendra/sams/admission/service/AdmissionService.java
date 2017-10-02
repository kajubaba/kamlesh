package com.narendra.sams.admission.service;

import com.narendra.sams.admission.domain.Student;
import java.util.Collection;

public interface AdmissionService {
    Long addStudent(Student student, Long l);

    Long admitStudentTemporarily(Student student, String str, Long l);

    void renewStudent(Student student, Long l);

    void renewStudent(Long l, String str, Long l2, Long l3, Long l4, Long l5);

    void renewStudents(Collection<Long> collection, Long l, Boolean bool, Boolean bool2, Long l2);
}
