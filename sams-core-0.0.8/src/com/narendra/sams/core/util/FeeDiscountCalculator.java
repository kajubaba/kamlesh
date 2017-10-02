package com.narendra.sams.core.util;

import com.narendra.sams.core.domain.AcademicSessionAdmisionSchemeDetail;
import com.narendra.sams.core.domain.AcademicYearAdmissionScheme;
import com.narendra.sams.core.domain.AcademicYearFeeInstallment;
import com.narendra.sams.core.domain.AcademicYearFeeInstallmentDetail;
import com.narendra.sams.core.domain.FeeHead;

public class FeeDiscountCalculator {
    public static Long calculateAdmissionSchemeDiscount(AcademicYearAdmissionScheme academicYearAdmissionScheme, AcademicYearFeeInstallment academicYearFeeInstallment) {
        if (academicYearAdmissionScheme == null) {
            return Long.valueOf(0);
        }
        if (academicYearAdmissionScheme.getSchemeDetails() == null) {
            return Long.valueOf(0);
        }
        if (academicYearFeeInstallment == null) {
            return Long.valueOf(0);
        }
        if (academicYearFeeInstallment.getAcademicYearFeeInstallmentDetails() == null) {
            return Long.valueOf(0);
        }
        Long discountTotal = Long.valueOf(0);
        for (AcademicSessionAdmisionSchemeDetail academicSessionAdmisionSchemeDetail : academicYearAdmissionScheme.getSchemeDetails()) {
            for (AcademicYearFeeInstallmentDetail academicYearFeeInstallmentDetail : academicYearFeeInstallment.getAcademicYearFeeInstallmentDetails()) {
                if (academicYearFeeInstallmentDetail.getFeeHead().getId().equals(academicSessionAdmisionSchemeDetail.getFeeHead().getId())) {
                    if (!(academicYearFeeInstallmentDetail.getAmount() == null || academicYearFeeInstallmentDetail.getAmount().longValue() == 0)) {
                        discountTotal = Long.valueOf(discountTotal.longValue() + ((academicYearFeeInstallmentDetail.getAmount().longValue() * academicSessionAdmisionSchemeDetail.getDiscount().longValue()) / 100));
                    }
                }
            }
        }
        return discountTotal;
    }

    public static Long calculateAdmissionSchemeDiscountOnBusFee(AcademicYearAdmissionScheme academicYearAdmissionScheme, Long busFee) {
        if (academicYearAdmissionScheme == null) {
            return Long.valueOf(0);
        }
        if (academicYearAdmissionScheme.getSchemeDetails() == null) {
            return Long.valueOf(0);
        }
        if (busFee == null) {
            return Long.valueOf(0);
        }
        Long discountTotal = Long.valueOf(0);
        for (AcademicSessionAdmisionSchemeDetail academicSessionAdmisionSchemeDetail : academicYearAdmissionScheme.getSchemeDetails()) {
            if (FeeHead.FEE_HEAD_BUS_FEE.equals(academicSessionAdmisionSchemeDetail.getFeeHead().getName())) {
                return Long.valueOf((busFee.longValue() * academicSessionAdmisionSchemeDetail.getDiscount().longValue()) / 100);
            }
        }
        return discountTotal;
    }
}
