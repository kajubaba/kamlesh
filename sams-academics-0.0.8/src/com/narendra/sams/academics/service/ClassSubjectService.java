package com.narendra.sams.academics.service;

import com.narendra.sams.academics.domain.ClassSubject;
import com.narendra.sams.academics.domain.ClassSubjectCount;
import java.util.List;

public interface ClassSubjectService {
    List<ClassSubjectCount> getClasswiseSubjectCount(Long l);

    List<ClassSubject> getExamClassSubjects(Long l);

    void saveSubject(ClassSubject classSubject, Long l);
}
