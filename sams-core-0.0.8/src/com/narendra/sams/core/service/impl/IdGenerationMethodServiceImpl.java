package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.IdGenerationMethodDAO;
import com.narendra.sams.core.domain.IDGenerationMethod;
import com.narendra.sams.core.service.IdGenerationMethodService;

public class IdGenerationMethodServiceImpl implements IdGenerationMethodService {
    private IdGenerationMethodDAO idGenerationMethodDAO;

    public IdGenerationMethodDAO getIdGenerationMethodDAO() {
        return this.idGenerationMethodDAO;
    }

    public void setIdGenerationMethodDAO(IdGenerationMethodDAO idGenerationMethodDAO) {
        this.idGenerationMethodDAO = idGenerationMethodDAO;
    }

    public IDGenerationMethod getAcademicSessionClassIDGenerationMethod(Long academicSessionClassId) {
        if (academicSessionClassId == null) {
            return null;
        }
        return this.idGenerationMethodDAO.getAcademicSessionClassIDGenerationMethod(academicSessionClassId);
    }

    public void updateNextNo(Long methodId, Long nextNo) {
        if (methodId == null || nextNo == null) {
            this.idGenerationMethodDAO.updateNextNo(methodId, nextNo);
        }
    }
}
