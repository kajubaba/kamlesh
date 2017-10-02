package com.narendra.sams.academics.service.impl;

import com.narendra.sams.academics.dao.ClassSubjectDAO;
import com.narendra.sams.academics.domain.ClassSubject;
import com.narendra.sams.academics.domain.ClassSubjectCount;
import com.narendra.sams.academics.service.ClassSubjectService;
import java.util.List;

public class ClassSubjectServiceImpl implements ClassSubjectService {
    private ClassSubjectDAO classSubjectDao;

    public ClassSubjectDAO getClassSubjectDao() {
        return this.classSubjectDao;
    }

    public void setExamClassSubjectDao(ClassSubjectDAO classSubjectDao) {
        this.classSubjectDao = classSubjectDao;
    }

    public void saveSubject(ClassSubject classSubject, Long userId) {
        if (classSubject != null) {
            if (classSubject.getId() == null) {
                this.classSubjectDao.addSubject(classSubject, userId);
            } else {
                this.classSubjectDao.updateSubject(classSubject, userId);
            }
        }
    }

    public List<ClassSubjectCount> getClasswiseSubjectCount(Long academicYearId) {
        return this.classSubjectDao.getClasswiseSubjectCount(academicYearId);
    }

    public List<ClassSubject> getExamClassSubjects(Long academicYearClassId) {
        return this.classSubjectDao.getExamClassSubjects(academicYearClassId);
    }
}
