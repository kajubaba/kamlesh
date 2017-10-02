package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.AcademicSessionAdmisionSchemeDetail;
import com.narendra.sams.core.domain.AcademicYearAdmissionScheme;
import com.narendra.sams.core.domain.AdmissionScheme;
import java.util.List;

public interface AcademicYearAdmissionSchemeService {
    void addAdmissionSchemes(List<Long> list, Long l, Long l2);

    void copyAdmissionSchemes(Long l, Long l2, Long l3);

    void deleteAdmissionScheme(Long l);

    AcademicYearAdmissionScheme getAcademicYearAdmissionScheme(Long l);

    AcademicYearAdmissionScheme getAcademicYearAdmissionScheme(Long l, Long l2);

    List<AcademicYearAdmissionScheme> getAdmissionSchemes(Long l);

    List<AdmissionScheme> getAssignedAdmissionSchemes(Long l);

    List<AdmissionScheme> getUnAssignedAdmissionSchemes(Long l, Long l2);

    void saveSchemeDetails(List<AcademicSessionAdmisionSchemeDetail> list, Long l);
}
