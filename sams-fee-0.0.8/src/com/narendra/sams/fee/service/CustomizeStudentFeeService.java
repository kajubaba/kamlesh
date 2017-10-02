package com.narendra.sams.fee.service;

import com.narendra.sams.admission.domain.CustomizeFee;
import com.narendra.sams.admission.domain.CustomizeInstallment;
import com.narendra.sams.admission.domain.CustomizeInstallmentDetail;
import com.narendra.sams.admission.domain.FeeCustomizeComments;
import com.narendra.sams.admission.domain.FeeDiscount;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface CustomizeStudentFeeService {
    void deleteFeeAdjustment(Long l, Long l2);

    List<Date> getAdjustedInstallmentDueDates(Long l);

    FeeCustomizeComments getComments(Long l, Long l2);

    List<FeeCustomizeComments> getComments(Long l, Collection<Long> collection);

    CustomizeFee getCustomizeFee(Long l, Long l2, Long l3);

    CustomizeInstallment getCustomizeInstallment(Long l);

    List<CustomizeInstallment> getCustomizeInstallments(Long l, Long l2, Long l3, Short sh);

    List<FeeDiscount> getFeeDiscounts(Long l, Long l2);

    Boolean isCourseYearFeeAdjusted(Long l, Short sh);

    void save(List<FeeDiscount> list, Boolean bool, List<CustomizeInstallmentDetail> list2, List<CustomizeInstallment> list3, List<CustomizeInstallment> list4, List<CustomizeInstallment> list5, FeeCustomizeComments feeCustomizeComments, Long l);

    void saveCustomizeFee(CustomizeFee customizeFee, Long l);

    void saveCustomizeInstallments(List<CustomizeInstallment> list, Long l);

    void saveOrUpdateComments(FeeCustomizeComments feeCustomizeComments);

    void saveOrUpdateStudentDiscount(List<FeeDiscount> list, Long l);

    void updateCustomizeFee(List<CustomizeInstallmentDetail> list, Long l);

    void updateCustomizeInstallmentDueDate(List<CustomizeInstallment> list);
}
