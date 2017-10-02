package com.narendra.sams.admission.dao;

import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentQuick;
import java.util.Collection;
import java.util.List;

public interface AdmissionListDAO {
    List<ClassHistory> getAdmissions(Long l, Long l2, Short sh);

    List<ClassHistory> getAdmissionsByClass(Long l, Long l2, Short sh);

    List<ClassHistory> getAdmissionsByClass(Collection<Long> collection);

    List<Student> getStudents(Long l, Long l2);

    List<Student> getStudents(Long l, Long l2, Long l3);

    List<Student> getStudentsForTranslations(Long l, Long l2, Long l3);

    List<ClassHistory> getUnderSchemeAdmissions(Long l, Long l2);

    List<ClassHistory> getUnderSchemeAdmissions(Long l, Long l2, Short sh);

    List<StudentQuick> searchStudents(String str, Long l);
}
