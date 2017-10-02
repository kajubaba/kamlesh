package com.narendra.sams.academics.service.impl;

import com.narendra.sams.academics.dao.ExamPatternClassDAO;
import com.narendra.sams.academics.dao.ExamPatternDAO;
import com.narendra.sams.academics.exam.domain.EvaluationScheme;
import com.narendra.sams.academics.exam.domain.EvaluationSchemeClass;
import com.narendra.sams.academics.service.ExamPatternClassService;
import com.narendra.sams.core.dao.AcademicYearDAO;
import com.narendra.sams.core.domain.AcademicYearClass;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExamPatternClassServiceImpl implements ExamPatternClassService {
    private AcademicYearDAO academicYearDAO;
    private ExamPatternClassDAO examPatternClassDAO;
    private ExamPatternDAO examPatternDAO;

    public ExamPatternClassDAO getExamPatternClassDAO() {
        return this.examPatternClassDAO;
    }

    public void setExamPatternClassDAO(ExamPatternClassDAO examPatternClassDAO) {
        this.examPatternClassDAO = examPatternClassDAO;
    }

    public AcademicYearDAO getAcademicYearDAO() {
        return this.academicYearDAO;
    }

    public void setAcademicYearDAO(AcademicYearDAO academicYearDAO) {
        this.academicYearDAO = academicYearDAO;
    }

    public ExamPatternDAO getExamPatternDAO() {
        return this.examPatternDAO;
    }

    public void setExamPatternDAO(ExamPatternDAO examPatternDAO) {
        this.examPatternDAO = examPatternDAO;
    }

    public void addClassesInExamPattern(Collection<Integer> classes, Long examPatternId) {
        if (classes != null && examPatternId != null) {
            this.examPatternClassDAO.addClassesInExamPattern(classes, examPatternId);
        }
    }

    public List<EvaluationSchemeClass> getExamPatternClasses(Long examPatternId) {
        return this.examPatternClassDAO.getExamPatternClasses(examPatternId);
    }

    public List<AcademicYearClass> getNotAssociatedClasses(Long academicYearId) {
        if (academicYearId == null) {
            return null;
        }
        List<AcademicYearClass> allClasses = this.academicYearDAO.getAcademicYearClasses(academicYearId);
        List<EvaluationSchemeClass> examPatternClasses = this.examPatternClassDAO.getAllClassesAddedInExamPattern(academicYearId);
        if (examPatternClasses == null) {
            return allClasses;
        }
        List<AcademicYearClass> notAddedClasses = new ArrayList();
        for (AcademicYearClass academicYearClass : allClasses) {
            Boolean isAdded = Boolean.FALSE;
            for (EvaluationSchemeClass evaluationSchemeClass : examPatternClasses) {
                if (academicYearClass.getId().equals(evaluationSchemeClass.getAcademicYearClass().getId())) {
                    isAdded = Boolean.TRUE;
                }
            }
            if (!isAdded.booleanValue()) {
                notAddedClasses.add(academicYearClass);
            }
        }
        return notAddedClasses;
    }

    public void removeClassFromExamPattern(Long examPatternClassId) {
        this.examPatternClassDAO.removeClassFromExamPattern(examPatternClassId);
    }

    public EvaluationScheme getExamPatternOfClass(Long academicYearClassId) {
        return this.examPatternClassDAO.getExamPatternClass(academicYearClassId).getEvaluationScheme();
    }
}
