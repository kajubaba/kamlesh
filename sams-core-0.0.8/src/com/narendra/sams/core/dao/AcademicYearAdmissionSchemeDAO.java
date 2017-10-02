package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.AcademicSessionAdmisionSchemeDetail;
import com.narendra.sams.core.domain.AcademicYearAdmissionScheme;
import java.util.List;

public interface AcademicYearAdmissionSchemeDAO {
    void addAdmissionSchemes(List<AcademicYearAdmissionScheme> list);

    void deleteAdmissionScheme(Long l);

    AcademicYearAdmissionScheme getAcademicYearAdmissionScheme(Long l);

    AcademicYearAdmissionScheme getAcademicYearAdmissionScheme(Long l, Long l2);

    List<AcademicYearAdmissionScheme> getAdmissionSchemes(Long l);

    void saveSchemeDetails(List<AcademicSessionAdmisionSchemeDetail> list, Long l);
}
