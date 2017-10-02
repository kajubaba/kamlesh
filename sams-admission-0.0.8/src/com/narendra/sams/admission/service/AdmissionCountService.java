package com.narendra.sams.admission.service;

import com.narendra.sams.admission.domain.AcademicYearClassAdmissionCount;
import java.util.List;

public interface AdmissionCountService {
    long getAdmissionCount(Long l, Long l2, Short sh);

    long getAdmissionCountUnderScheme(Long l);

    List<AcademicYearClassAdmissionCount> getClasswiseAdmissionCount(Long l, Long l2, Short sh);
}
