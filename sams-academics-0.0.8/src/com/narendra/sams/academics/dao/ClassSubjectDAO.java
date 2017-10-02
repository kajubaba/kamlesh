package com.narendra.sams.academics.dao;

import com.narendra.sams.academics.domain.ClassSubject;
import com.narendra.sams.academics.domain.ClassSubjectCount;
import java.util.List;

public interface ClassSubjectDAO {
    void addSubject(ClassSubject classSubject, Long l);

    List<ClassSubjectCount> getClasswiseSubjectCount(Long l);

    List<ClassSubject> getExamClassSubjects(Long l);

    void updateSubject(ClassSubject classSubject, Long l);
}
