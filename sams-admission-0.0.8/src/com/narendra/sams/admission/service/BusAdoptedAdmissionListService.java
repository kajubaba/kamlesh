package com.narendra.sams.admission.service;

import com.narendra.sams.admission.domain.AcademicYearClassAdmissionCount;
import com.narendra.sams.admission.domain.BusStopAdmissionCount;
import com.narendra.sams.admission.domain.ClassHistory;
import java.util.List;

public interface BusAdoptedAdmissionListService {
    Long getBusFacilityAdoptedAdmissionCount(Long l, Long l2, Short sh);

    List<ClassHistory> getBusFacilityAdoptedAdmissions(Long l, Long l2, Short sh);

    List<ClassHistory> getBusFacilityAdoptedAdmissionsByBusStop(Long l, Long l2, Long l3, Short sh);

    List<ClassHistory> getBusFacilityAdoptedAdmissionsByClass(Long l, Long l2, Short sh);

    List<BusStopAdmissionCount> getBusStopWiseBusFacilityAdoptedAdmissionCount(Long l, Long l2, Short sh);

    List<AcademicYearClassAdmissionCount> getClasswiseBusFacilityAdoptedAdmissionCount(Long l, Long l2, Short sh);
}
