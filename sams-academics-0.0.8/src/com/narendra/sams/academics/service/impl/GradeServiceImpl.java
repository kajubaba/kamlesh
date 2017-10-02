package com.narendra.sams.academics.service.impl;

import com.narendra.sams.academics.dao.GradeDAO;
import com.narendra.sams.academics.domain.GradeScale;
import com.narendra.sams.academics.domain.GradeScalePoint;
import com.narendra.sams.academics.service.GradeService;
import java.util.List;

public class GradeServiceImpl implements GradeService {
    private GradeDAO gradeDAO;

    public GradeDAO getGradeDAO() {
        return this.gradeDAO;
    }

    public void setGradeDAO(GradeDAO gradeDAO) {
        this.gradeDAO = gradeDAO;
    }

    public List<GradeScale> getGradeScales(Long instituteId) {
        return this.gradeDAO.getGradeScales(instituteId);
    }

    public List<GradeScalePoint> getGradeScalePoints(Long gradeScaleId) {
        return this.gradeDAO.getGradeScalePoints(gradeScaleId);
    }
}
