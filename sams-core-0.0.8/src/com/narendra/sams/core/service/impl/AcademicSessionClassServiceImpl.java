package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.AcademicSessionClassDAO;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.service.AcademicSessionClassService;

public class AcademicSessionClassServiceImpl implements AcademicSessionClassService {
    private AcademicSessionClassDAO academicSessionClassDAO;

    public AcademicSessionClassDAO getAcademicSessionClassDAO() {
        return this.academicSessionClassDAO;
    }

    public void setAcademicSessionClassDAO(AcademicSessionClassDAO academicSessionClassDAO) {
        this.academicSessionClassDAO = academicSessionClassDAO;
    }

    public AcademicYearClass getClass(Long id) {
        if (id == null) {
            return null;
        }
        return this.academicSessionClassDAO.getClass(id);
    }
}
