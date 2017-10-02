package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.AcademicYearAdmissionSchemeDAO;
import com.narendra.sams.core.domain.AcademicSessionAdmisionSchemeDetail;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearAdmissionScheme;
import com.narendra.sams.core.domain.AdmissionScheme;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.service.AcademicYearAdmissionSchemeService;
import com.narendra.sams.core.service.AdmissionSchemeService;
import com.narendra.sams.core.util.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AcademicYearAdmissionSchemeServiceImpl implements AcademicYearAdmissionSchemeService {
    private AcademicYearAdmissionSchemeDAO academicYearAdmissionSchemeDAO;
    private AdmissionSchemeService admissionSchemeService;

    public AcademicYearAdmissionSchemeDAO getAcademicYearAdmissionSchemeDAO() {
        return this.academicYearAdmissionSchemeDAO;
    }

    public void setAcademicYearAdmissionSchemeDAO(AcademicYearAdmissionSchemeDAO academicYearAdmissionSchemeDAO) {
        this.academicYearAdmissionSchemeDAO = academicYearAdmissionSchemeDAO;
    }

    public AdmissionSchemeService getAdmissionSchemeService() {
        return this.admissionSchemeService;
    }

    public void setAdmissionSchemeService(AdmissionSchemeService admissionSchemeService) {
        this.admissionSchemeService = admissionSchemeService;
    }

    public AcademicYearAdmissionScheme getAcademicYearAdmissionScheme(Long id) {
        return this.academicYearAdmissionSchemeDAO.getAcademicYearAdmissionScheme(id);
    }

    public void addAdmissionSchemes(List<Long> admissionSchemeIds, Long academicSessionId, Long userId) {
        if (academicSessionId != null && academicSessionId != null && userId != null && !admissionSchemeIds.isEmpty()) {
            AcademicYear academicYear = new AcademicYear();
            academicYear.setId(academicSessionId);
            UserView user = new UserView();
            user.setId(userId);
            Date dateTime = DateUtil.getSystemDateTime();
            List<AcademicYearAdmissionScheme> academicYearAdmissionSchemes = new ArrayList();
            for (Long id : admissionSchemeIds) {
                AcademicYearAdmissionScheme academicYearAdmissionScheme = new AcademicYearAdmissionScheme();
                AdmissionScheme admissionScheme = new AdmissionScheme();
                admissionScheme.setId(id);
                academicYearAdmissionScheme.setAdmissionScheme(admissionScheme);
                academicYearAdmissionScheme.setAcademicYear(academicYear);
                academicYearAdmissionScheme.setCreatedBy(user);
                academicYearAdmissionScheme.setModifiedBy(user);
                academicYearAdmissionScheme.setCreatedDateTime(dateTime);
                academicYearAdmissionScheme.setModifiedDateTime(dateTime);
                academicYearAdmissionSchemes.add(academicYearAdmissionScheme);
            }
            this.academicYearAdmissionSchemeDAO.addAdmissionSchemes(academicYearAdmissionSchemes);
        }
    }

    public void deleteAdmissionScheme(Long admissionSchemeId) {
        this.academicYearAdmissionSchemeDAO.deleteAdmissionScheme(admissionSchemeId);
    }

    public List<AcademicYearAdmissionScheme> getAdmissionSchemes(Long academicSessionId) {
        return this.academicYearAdmissionSchemeDAO.getAdmissionSchemes(academicSessionId);
    }

    public List<AdmissionScheme> getUnAssignedAdmissionSchemes(Long instituteId, Long academicSessionId) {
        List<AcademicYearAdmissionScheme> academicYearAdmissionSchemes = getAdmissionSchemes(academicSessionId);
        if (academicYearAdmissionSchemes == null || academicYearAdmissionSchemes.isEmpty()) {
            return this.admissionSchemeService.getActiveAdmissionSchemes(instituteId);
        }
        List<AdmissionScheme> admissionSchemes = this.admissionSchemeService.getActiveAdmissionSchemes(instituteId);
        if (admissionSchemes == null || admissionSchemes.isEmpty()) {
            return null;
        }
        List<AdmissionScheme> unAssignedAdmissionSchemes = new ArrayList();
        for (AdmissionScheme admissionScheme : admissionSchemes) {
            Boolean found = Boolean.FALSE;
            for (AcademicYearAdmissionScheme academicYearAdmissionScheme : academicYearAdmissionSchemes) {
                if (admissionScheme.getId().equals(academicYearAdmissionScheme.getAdmissionScheme().getId())) {
                    found = Boolean.TRUE;
                    break;
                }
            }
            if (!found.booleanValue()) {
                unAssignedAdmissionSchemes.add(admissionScheme);
            }
        }
        return unAssignedAdmissionSchemes;
    }

    public List<AdmissionScheme> getAssignedAdmissionSchemes(Long academicSessionId) {
        List<AcademicYearAdmissionScheme> academicYearAdmissionSchemes = this.academicYearAdmissionSchemeDAO.getAdmissionSchemes(academicSessionId);
        if (academicYearAdmissionSchemes == null || academicYearAdmissionSchemes.isEmpty()) {
            return null;
        }
        List<AdmissionScheme> admissionSchemes = new ArrayList();
        for (AcademicYearAdmissionScheme academicYearAdmissionScheme : academicYearAdmissionSchemes) {
            admissionSchemes.add(academicYearAdmissionScheme.getAdmissionScheme());
        }
        return admissionSchemes;
    }

    public void copyAdmissionSchemes(Long fromAcademicYear, Long toAcademicYear, Long userId) {
        List<AdmissionScheme> admissionSchems = getAssignedAdmissionSchemes(fromAcademicYear);
        if (admissionSchems != null) {
            List<Long> schemeIds = new ArrayList();
            for (AdmissionScheme admissionScheme : admissionSchems) {
                schemeIds.add(admissionScheme.getId());
            }
            addAdmissionSchemes(schemeIds, toAcademicYear, userId);
        }
    }

    public AcademicYearAdmissionScheme getAcademicYearAdmissionScheme(Long academicSessionId, Long admissionSchemeId) {
        return this.academicYearAdmissionSchemeDAO.getAcademicYearAdmissionScheme(academicSessionId, admissionSchemeId);
    }

    public void saveSchemeDetails(List<AcademicSessionAdmisionSchemeDetail> details, Long academicSessionSchemeId) {
        if (academicSessionSchemeId != null && details != null) {
            this.academicYearAdmissionSchemeDAO.saveSchemeDetails(details, academicSessionSchemeId);
        }
    }
}
