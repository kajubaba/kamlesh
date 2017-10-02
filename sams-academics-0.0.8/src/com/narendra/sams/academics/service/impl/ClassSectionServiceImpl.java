package com.narendra.sams.academics.service.impl;

import com.narendra.sams.acad.domain.ClassSectionCount;
import com.narendra.sams.academics.dao.ClassSectionDAO;
import com.narendra.sams.academics.service.ClassSectionService;
import com.narendra.sams.admission.domain.ClassSection;
import java.util.List;

public class ClassSectionServiceImpl implements ClassSectionService {
    private ClassSectionDAO classSectionDAO;

    public ClassSectionDAO getClassSectionDAO() {
        return this.classSectionDAO;
    }

    public void setClassSectionDAO(ClassSectionDAO classSectionDAO) {
        this.classSectionDAO = classSectionDAO;
    }

    public void saveClassSection(ClassSection classSection, Long userId) {
        if (classSection != null) {
            if (classSection.getId() != null) {
                this.classSectionDAO.updateClassSection(classSection, userId);
            } else {
                this.classSectionDAO.addClassSection(classSection, userId);
            }
        }
    }

    public List<ClassSectionCount> getClasswiseSectionCount(Long academicYearId) {
        return this.classSectionDAO.getClasswiseSectionCount(academicYearId);
    }

    public List<ClassSection> getClassSections(Long academicYearClassId) {
        return this.classSectionDAO.getClassSections(academicYearClassId);
    }
}
