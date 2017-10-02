package com.narendra.sams.admission.dao;

import com.narendra.sams.admission.domain.ClassHistory;
import java.util.List;

public interface BusNotAdoptedAdmissionDAO {
    Long getBusFacilityNotAdoptedAdmissionCount(Long l, Long l2, Short sh);

    List<ClassHistory> getBusFacilityNotAdoptedAdmissions(Long l, Long l2, Short sh);
}
