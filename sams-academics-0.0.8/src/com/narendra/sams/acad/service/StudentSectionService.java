package com.narendra.sams.acad.service;

import com.narendra.sams.admission.domain.ClassHistory;
import java.util.Collection;
import java.util.List;

public interface StudentSectionService {
    List<ClassHistory> getStudents(Long l, Long l2);

    void updateStudentSection(Collection<Long> collection, Long l, Long l2);
}
