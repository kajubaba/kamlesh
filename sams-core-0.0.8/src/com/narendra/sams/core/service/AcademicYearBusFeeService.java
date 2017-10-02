package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusFeeInstallment;
import com.narendra.sams.core.domain.BusFeeInstallmentDetail;
import com.narendra.sams.core.domain.BusStop;
import java.util.List;

public interface AcademicYearBusFeeService {
    void addBusFee(List<BusFee> list);

    void addBusStops(Long l, List<Long> list);

    void copyBusFeeInstallments(Long l, Long l2, Long l3);

    void copyBusStopAndFee(Long l, Long l2);

    Boolean deleteBusStop(Long l);

    List<BusFee> getAssigedBusFee(Long l);

    BusFee getBusFee(Long l);

    BusFee getBusFee(Long l, Long l2);

    BusFeeInstallment getBusFeeInstallment(Long l);

    BusFeeInstallmentDetail getBusFeeInstallmentDetails(Long l, Long l2);

    List<BusStop> getUnAssignedBusStops(Long l, Long l2);

    void saveBusFeeInstallment(BusFeeInstallment busFeeInstallment, Long l);

    void saveBusFeeInstallments(List<BusFee> list, Long l, Long l2, Long l3);

    void updateFeeChanges(List<BusFee> list);
}
