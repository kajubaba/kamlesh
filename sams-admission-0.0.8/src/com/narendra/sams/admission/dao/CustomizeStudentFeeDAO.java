package com.narendra.sams.admission.dao;

import com.narendra.sams.admission.domain.CustomizeFee;
import com.narendra.sams.admission.domain.CustomizeInstallment;
import com.narendra.sams.admission.domain.CustomizeInstallmentDetail;
import com.narendra.sams.admission.domain.FeeCustomizeComments;
import com.narendra.sams.admission.domain.FeeDiscount;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface CustomizeStudentFeeDAO {
    void deleteFeeAdjustment(Long l, Long l2);

    void deleteFeeAdjustmentComments(Long l, Long l2);

    void deleteFeeAdjustmentDiscount(Long l, Long l2);

    List<Date> getAdjustedInstallmentDueDates(Long l);

    List<CustomizeInstallment> getAdjustedInstallmentOfCourseYearSetting(Long l, Short sh);

    FeeCustomizeComments getComments(Long l, Long l2);

    List<FeeCustomizeComments> getComments(Long l, Collection<Long> collection);

    CustomizeFee getCustomizeFee(Long l, Long l2, Long l3);

    CustomizeInstallment getCustomizeInstallment(Long l);

    List<CustomizeInstallment> getCustomizeInstallments(Long l, Long l2, Long l3, Short sh);

    List<FeeDiscount> getFeeDiscounts(Long l, Long l2);

    void saveCustomizeFee(CustomizeFee customizeFee);

    void saveCustomizeInstallments(List<CustomizeInstallment> list);

    void saveOrUpdateComments(FeeCustomizeComments feeCustomizeComments);

    void saveOrUpdateStudentDiscount(List<FeeDiscount> list);

    void updateCustomizeFee(List<CustomizeInstallmentDetail> list, Long l);

    void updateCustomizeInstallmentDueDate(List<CustomizeInstallment> list);
}
