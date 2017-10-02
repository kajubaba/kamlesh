package com.narendra.sams.admission.service;

import com.narendra.sams.admission.domain.AcademicYearClassAdmissionCount;
import com.narendra.sams.admission.domain.Student;
import java.util.List;

public interface AdmissionRenewalService {
    Long getAdmissionCountPendingForRenewal(Long l);

    List<Student> getAdmissionsPendingForRenewal(Long l);

    List<Student> getClassAdmissionsPendingForRenewal(Long l);

    List<AcademicYearClassAdmissionCount> getClasswiseAdmissionsPendingForRenewal(Long l);

    Boolean getRenewalEligibility(Long l, Long l2);
}
