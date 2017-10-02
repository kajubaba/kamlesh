package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.AdmissionScheme;
import java.util.List;

public interface AdmissionSchemeDAO {
    Long addAdmissionScheme(AdmissionScheme admissionScheme, Long l);

    List<AdmissionScheme> getActiveAdmissionSchemes(Long l);

    AdmissionScheme getAdmissionScheme(Long l);

    AdmissionScheme getAdmissionSchemeByName(String str, Long l);

    List<AdmissionScheme> getAdmissionSchemes(Long l);

    List<AdmissionScheme> getAdmissionSchemesOfAcademicSession(Long l);

    void updateAdmissionScheme(AdmissionScheme admissionScheme, Long l);
}
