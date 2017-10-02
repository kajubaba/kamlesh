package com.narendra.sams.admission.service;

import com.narendra.sams.admission.domain.ClassHistory;
import java.util.List;

public interface BusNotAdoptedAdmissionService {
    Long getBusFacilityNotAdoptedAdmissionCount(Long l, Long l2, Short sh);

    List<ClassHistory> getBusFacilityNotAdoptedAdmissions(Long l, Long l2, Short sh);
}
