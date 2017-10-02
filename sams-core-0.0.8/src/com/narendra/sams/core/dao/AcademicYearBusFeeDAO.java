package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusFeeInstallment;
import com.narendra.sams.core.domain.BusFeeInstallmentDetail;
import java.util.List;

public interface AcademicYearBusFeeDAO {
    void addBusFee(List<BusFee> list);

    void addBusFeeInstallment(BusFeeInstallment busFeeInstallment, Long l);

    void deleteBusFeeDetails(Long l);

    void deleteBusFeeInstallmentDetail(Long l);

    void deleteBusStop(Long l);

    List<BusFee> getAssigedBusFee(Long l);

    BusFee getBusFee(Long l);

    BusFee getBusFee(Long l, Long l2);

    BusFeeInstallment getBusFeeInstallment(Long l);

    BusFeeInstallmentDetail getBusFeeInstallmentDetails(Long l, Long l2);

    void saveBusFeeInstallments(List<BusFee> list);

    void saveOrUpdateBusFeeInstallment(BusFeeInstallment busFeeInstallment, Long l);

    void updateBusFeeInstallmentDetail(BusFeeInstallment busFeeInstallment, Long l);

    void updateFeeChanges(List<BusFee> list);
}
