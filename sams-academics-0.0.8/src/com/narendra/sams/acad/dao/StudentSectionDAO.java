package com.narendra.sams.acad.dao;

import com.narendra.sams.admission.domain.ClassHistory;
import java.util.Collection;
import java.util.List;

public interface StudentSectionDAO {
    List<ClassHistory> getStudents(Long l, Long l2);

    List<ClassHistory> getStudents(Long l, Long l2, Long l3);

    void updateStudentSection(Collection<Long> collection, Long l, Long l2);
}
