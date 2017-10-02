package com.narendra.sams.admission.service.impl;

import com.narendra.sams.admission.dao.AdmissionCountDAO;
import com.narendra.sams.admission.domain.AcademicYearClassAdmissionCount;
import com.narendra.sams.admission.service.AdmissionCountService;
import java.util.List;

public class AdmissionCountServiceImpl implements AdmissionCountService {
    private AdmissionCountDAO admissionCountDAO;

    public AdmissionCountDAO getAdmissionCountDAO() {
        return this.admissionCountDAO;
    }

    public void setAdmissionCountDAO(AdmissionCountDAO admissionCountDAO) {
        this.admissionCountDAO = admissionCountDAO;
    }

    public long getAdmissionCountUnderScheme(Long academicYearId) {
        return this.admissionCountDAO.getAdmissionCountUnderScheme(academicYearId);
    }

    public long getAdmissionCount(Long academicYearId, Long studentStatus, Short admissionType) {
        return this.admissionCountDAO.getAdmissionCount(academicYearId, studentStatus, admissionType).longValue();
    }

    public List<AcademicYearClassAdmissionCount> getClasswiseAdmissionCount(Long academicYearId, Long studentStatus, Short admissionType) {
        if (academicYearId == null || studentStatus == null) {
            return null;
        }
        return this.admissionCountDAO.getClasswiseAdmissionCount(academicYearId, studentStatus, admissionType);
    }
}
