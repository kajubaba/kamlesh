package com.narendra.sams.admission.dao;

import com.narendra.sams.admission.domain.AcademicYearClassAdmissionCount;
import java.util.List;

public interface AdmissionCountDAO {
    Long getAdmissionCount(Long l, Long l2, Short sh);

    long getAdmissionCountUnderScheme(Long l);

    List<AcademicYearClassAdmissionCount> getClasswiseAdmissionCount(Long l, Long l2, Short sh);
}
