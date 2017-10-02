package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.AdmissionSchemeDAO;
import com.narendra.sams.core.domain.AdmissionScheme;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.AdmissionSchemeService;
import java.util.List;

public class AdmissionSchemeServiceImpl implements AdmissionSchemeService {
    private AdmissionSchemeDAO admissionSchemeDAO;

    public AdmissionSchemeDAO getAdmissionSchemeDAO() {
        return this.admissionSchemeDAO;
    }

    public void setAdmissionSchemeDAO(AdmissionSchemeDAO admissionSchemeDAO) {
        this.admissionSchemeDAO = admissionSchemeDAO;
    }

    public List<AdmissionScheme> getAdmissionSchemesOfAcademicSession(Long academicYearId) {
        return this.admissionSchemeDAO.getAdmissionSchemesOfAcademicSession(academicYearId);
    }

    public List<AdmissionScheme> getAdmissionSchemes(Long instituteId) {
        return this.admissionSchemeDAO.getAdmissionSchemes(instituteId);
    }

    public List<AdmissionScheme> getActiveAdmissionSchemes(Long instituteId) {
        return this.admissionSchemeDAO.getActiveAdmissionSchemes(instituteId);
    }

    public AdmissionScheme getAdmissionScheme(Long admissionSchemeId) {
        return this.admissionSchemeDAO.getAdmissionScheme(admissionSchemeId);
    }

    public Long saveAdmissionScheme(AdmissionScheme admissionScheme, Long userId) throws DuplicateNameFoundException {
        if (admissionScheme == null || userId == null) {
            return null;
        }
        AdmissionScheme persistAdmissionScheme = this.admissionSchemeDAO.getAdmissionSchemeByName(admissionScheme.getName(), admissionScheme.getInstitute().getId());
        if (admissionScheme.getId() == null) {
            if (persistAdmissionScheme == null) {
                return this.admissionSchemeDAO.addAdmissionScheme(admissionScheme, userId);
            }
            throw new DuplicateNameFoundException("Admission Scheme [" + admissionScheme.getName() + "] already exists");
        } else if (persistAdmissionScheme == null || persistAdmissionScheme.getId().equals(admissionScheme.getId())) {
            this.admissionSchemeDAO.updateAdmissionScheme(admissionScheme, userId);
            return admissionScheme.getId();
        } else {
            throw new DuplicateNameFoundException("Admission Scheme [" + admissionScheme.getName() + "] already exists");
        }
    }
}
