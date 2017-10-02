package com.narendra.sams.admission.service.impl;

import com.narendra.sams.admission.dao.AdmissionRenewalDAO;
import com.narendra.sams.admission.domain.AcademicYearClassAdmissionCount;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.service.AdmissionRenewalService;
import java.util.List;

public class AdmissionRenewalServiceImpl implements AdmissionRenewalService {
    private AdmissionRenewalDAO admissionRenewalDAO;

    public AdmissionRenewalDAO getAdmissionRenewalDAO() {
        return this.admissionRenewalDAO;
    }

    public void setAdmissionRenewalDAO(AdmissionRenewalDAO admissionRenewalDAO) {
        this.admissionRenewalDAO = admissionRenewalDAO;
    }

    public Long getAdmissionCountPendingForRenewal(Long instituteId) {
        return this.admissionRenewalDAO.getAdmissionCountPendingForRenewal(instituteId);
    }

    public List<Student> getAdmissionsPendingForRenewal(Long instituteId) {
        return this.admissionRenewalDAO.getAdmissionsPendingForRenewal(instituteId);
    }

    public List<AcademicYearClassAdmissionCount> getClasswiseAdmissionsPendingForRenewal(Long instituteId) {
        return this.admissionRenewalDAO.getClasswiseAdmissionsPendingForRenewal(instituteId);
    }

    public List<Student> getClassAdmissionsPendingForRenewal(Long academicYearClassId) {
        return this.admissionRenewalDAO.getClassAdmissionsPendingForRenewal(academicYearClassId);
    }

    public Boolean getRenewalEligibility(Long instituteId, Long studentId) {
        return this.admissionRenewalDAO.getRenewalEligibility(instituteId, studentId);
    }
}
