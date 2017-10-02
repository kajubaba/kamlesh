package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.AdmissionScheme;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import java.util.List;

public interface AdmissionSchemeService {
    List<AdmissionScheme> getActiveAdmissionSchemes(Long l);

    AdmissionScheme getAdmissionScheme(Long l);

    List<AdmissionScheme> getAdmissionSchemes(Long l);

    List<AdmissionScheme> getAdmissionSchemesOfAcademicSession(Long l);

    Long saveAdmissionScheme(AdmissionScheme admissionScheme, Long l) throws DuplicateNameFoundException;
}
