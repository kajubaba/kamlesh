package com.narendra.sams.admission.service.impl;

import com.narendra.sams.admission.dao.FeeAdjustedStudentDAO;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.service.FeeAdjustedStudentService;
import java.util.List;

public class FeeAdjustedStudentServiceImpl implements FeeAdjustedStudentService {
    private FeeAdjustedStudentDAO feeAdjustedStudentDAO;

    public FeeAdjustedStudentDAO getFeeAdjustedStudentDAO() {
        return this.feeAdjustedStudentDAO;
    }

    public void setFeeAdjustedStudentDAO(FeeAdjustedStudentDAO feeAdjustedStudentDAO) {
        this.feeAdjustedStudentDAO = feeAdjustedStudentDAO;
    }

    public Long getFeeAdjustedStudentCount(Long academicYearId) {
        return this.feeAdjustedStudentDAO.getFeeAdjustedStudentCount(academicYearId);
    }

    public List<Student> getStudentsWhoseFeeIsCustomized(Long academicYearId, Long studentStatusId) {
        return this.feeAdjustedStudentDAO.getStudentsWhoseFeeIsCustomized(academicYearId, studentStatusId);
    }
}
