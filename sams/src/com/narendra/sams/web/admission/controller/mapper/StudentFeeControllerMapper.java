package com.narendra.sams.web.admission.controller.mapper;

import com.narendra.sams.admission.domain.CustomizeInstallment;
import com.narendra.sams.admission.domain.CustomizeInstallmentDetail;
import com.narendra.sams.admission.domain.DaysOverdue;
import com.narendra.sams.admission.domain.FeeTransactionDetail;
import com.narendra.sams.admission.utils.LateFeeCalculator;
import com.narendra.sams.core.domain.AcademicSessionAdmisionSchemeDetail;
import com.narendra.sams.core.domain.AcademicYearAdmissionScheme;
import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.AcademicYearFeeInstallment;
import com.narendra.sams.core.domain.AcademicYearFeeInstallmentDetail;
import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusFeeDetail;
import com.narendra.sams.core.domain.BusFeeInstallment;
import com.narendra.sams.core.domain.BusFeeInstallmentDetail;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.core.util.FeeDiscountCalculator;
import com.narendra.sams.web.admission.vo.FeePaymentHeadVO;
import com.narendra.sams.web.admission.vo.StudentInstallmentVO;
import com.narendra.sams.web.restws.fee.vo.FeeInstallmentVO;
import com.narendra.sams.web.restws.fee.vo.InstallmentHeadFee;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;

public class StudentFeeControllerMapper {
    public List<StudentInstallmentVO> prepareInstallments(AcademicYearFee academicYearFee, List<CustomizeInstallment> customizeInstallments, BusFee busFee, BusFeeInstallment busFeeInstallment, List<FeeTransactionDetail> feeTransactionDetails, List<DaysOverdue> daysOverdues) {
        StudentInstallmentVO studentInstallmentVO;
        Map<Long, StudentInstallmentVO> installmentsMap = new HashMap();
        Boolean feeCustomized = Boolean.FALSE;
        if (!(customizeInstallments == null || customizeInstallments.isEmpty())) {
            feeCustomized = Boolean.TRUE;
        }
        if (!feeCustomized.booleanValue()) {
            if (!(academicYearFee == null || academicYearFee.getAcademicYearFeeInstallments() == null || academicYearFee.getAcademicYearFeeInstallments().isEmpty())) {
                for (AcademicYearFeeInstallment academicYearFeeInstallment : academicYearFee.getAcademicYearFeeInstallments()) {
                    studentInstallmentVO = new StudentInstallmentVO();
                    studentInstallmentVO.setInstallmentId(academicYearFeeInstallment.getInstallment().getId());
                    studentInstallmentVO.setAcademicYearFeeInstallmentId(academicYearFeeInstallment.getId());
                    studentInstallmentVO.setInstallmentName(academicYearFeeInstallment.getInstallment().getName());
                    studentInstallmentVO.setDueDate(academicYearFeeInstallment.getDueDate());
                    studentInstallmentVO.setTotalFee(academicYearFeeInstallment.getTotal());
                    studentInstallmentVO.setLateFeeRule(academicYearFeeInstallment.getLateFeeRule());
                    installmentsMap.put(academicYearFeeInstallment.getInstallment().getId(), studentInstallmentVO);
                }
            }
            if (!(installmentsMap.isEmpty() || busFee == null || busFeeInstallment == null || busFeeInstallment.getBusFeeInstallmentDetails() == null || busFeeInstallment.getBusFeeInstallmentDetails().isEmpty())) {
                for (BusFeeInstallmentDetail busFeeInstallmentDetail : busFeeInstallment.getBusFeeInstallmentDetails()) {
                    studentInstallmentVO = (StudentInstallmentVO) installmentsMap.get(busFeeInstallmentDetail.getInstallment().getId());
                    if (busFee.getRs() == null || busFeeInstallmentDetail.getFeePercent() == null) {
                        studentInstallmentVO.setBusFee(0);
                    } else {
                        studentInstallmentVO.setBusFee((busFee.getRs().longValue() * busFeeInstallmentDetail.getFeePercent().longValue()) / 100);
                    }
                }
            }
        } else if (!(customizeInstallments == null || customizeInstallments.isEmpty())) {
            for (CustomizeInstallment customizeInstallment : customizeInstallments) {
                studentInstallmentVO = new StudentInstallmentVO();
                studentInstallmentVO.setDueDate(customizeInstallment.getDueDate());
                studentInstallmentVO.setInstallmentId(customizeInstallment.getInstallment().getId());
                studentInstallmentVO.setInstallmentName(customizeInstallment.getInstallment().getName());
                studentInstallmentVO.setTotalFee(customizeInstallment.getTotalFee());
                studentInstallmentVO.setBusFee(customizeInstallment.getTotalBusFee());
                studentInstallmentVO.setCustFeeInstallmentId(customizeInstallment.getId());
                studentInstallmentVO.setLateFeeRule(customizeInstallment.getLateFeeRule());
                installmentsMap.put(customizeInstallment.getInstallment().getId(), studentInstallmentVO);
            }
        }
        if (installmentsMap.isEmpty()) {
            return null;
        }
        if (!(feeTransactionDetails == null || feeTransactionDetails.isEmpty())) {
            for (FeeTransactionDetail feeTransactionDetail : feeTransactionDetails) {
                studentInstallmentVO = (StudentInstallmentVO) installmentsMap.get(feeTransactionDetail.getInstallment().getId());
                studentInstallmentVO.setDepositeFee(studentInstallmentVO.getDepositeFee() + feeTransactionDetail.getAmount().longValue());
            }
        }
        if (!(daysOverdues == null || daysOverdues.isEmpty())) {
            for (DaysOverdue daysOverdue : daysOverdues) {
                studentInstallmentVO = (StudentInstallmentVO) installmentsMap.get(daysOverdue.getInstallment().getId());
                if (studentInstallmentVO != null) {
                    studentInstallmentVO.setDaysOverdue((long) daysOverdue.getDaysOverdue());
                    if (daysOverdue.getDiscount() != null) {
                        studentInstallmentVO.setLateFeeDiscount((long) daysOverdue.getDiscount().intValue());
                    } else {
                        studentInstallmentVO.setLateFeeDiscount(0);
                    }
                    if (daysOverdue.getCalculate() == null) {
                        studentInstallmentVO.setCalculate(Boolean.FALSE);
                    } else {
                        studentInstallmentVO.setCalculate(daysOverdue.getCalculate());
                    }
                }
            }
        }
        List<StudentInstallmentVO> installmentsList = new ArrayList(installmentsMap.values());
        Collections.sort(installmentsList, new BeanComparator("installmentId", new NullComparator()));
        return installmentsList;
    }

    public List<FeeInstallmentVO> prepareInstallmentsNew(AcademicYearFee academicYearFee, List<CustomizeInstallment> customizeInstallments, BusFee busFee, BusFeeInstallment busFeeInstallment, List<FeeTransactionDetail> feeTransactionDetails, List<DaysOverdue> daysOverdues, AcademicYearAdmissionScheme academicYearAdmissionScheme) {
        FeeInstallmentVO installmentVO;
        Map<Long, FeeInstallmentVO> installmentsMap = new HashMap();
        Boolean feeCustomized = Boolean.FALSE;
        if (!(customizeInstallments == null || customizeInstallments.isEmpty())) {
            feeCustomized = Boolean.TRUE;
        }
        if (!feeCustomized.booleanValue()) {
            if (!(academicYearFee == null || academicYearFee.getAcademicYearFeeInstallments() == null || academicYearFee.getAcademicYearFeeInstallments().isEmpty())) {
                for (AcademicYearFeeInstallment academicYearFeeInstallment : academicYearFee.getAcademicYearFeeInstallments()) {
                    installmentVO = new FeeInstallmentVO();
                    installmentVO.setInstallmentId(academicYearFeeInstallment.getInstallment().getId());
                    installmentVO.setAcademicYearFeeInstallmentId(academicYearFeeInstallment.getId());
                    installmentVO.setInstallment(academicYearFeeInstallment.getInstallment().getName());
                    if (academicYearFeeInstallment.getDueDate() != null) {
                        installmentVO.setInstallmentDueDate(DateUtil.formatDate(academicYearFeeInstallment.getDueDate(), "dd-MMM-yyyy"));
                    }
                    installmentVO.setAcademicFee(Long.valueOf(academicYearFeeInstallment.getTotal()));
                    installmentVO.setLateFeeRule(academicYearFeeInstallment.getLateFeeRule());
                    installmentVO.setCalculateLateFee(Boolean.TRUE);
                    installmentVO.setAdmissionSchemeDiscount(FeeDiscountCalculator.calculateAdmissionSchemeDiscount(academicYearAdmissionScheme, academicYearFeeInstallment));
                    installmentsMap.put(academicYearFeeInstallment.getInstallment().getId(), installmentVO);
                }
            }
            if (!(installmentsMap.isEmpty() || busFee == null || busFeeInstallment == null)) {
                if (BusFeeInstallment.SETUP_TYPE_PERCENTAGE.equals(busFeeInstallment.getSetupType())) {
                    if (busFeeInstallment.getBusFeeInstallmentDetails() != null) {
                        for (BusFeeInstallmentDetail busFeeInstallmentDetail : busFeeInstallment.getBusFeeInstallmentDetails()) {
                            installmentVO = (FeeInstallmentVO) installmentsMap.get(busFeeInstallmentDetail.getInstallment().getId());
                            if (installmentVO != null) {
                                if (busFee.getRs() == null || busFeeInstallmentDetail.getFeePercent() == null) {
                                    installmentVO.setBusFee(Long.valueOf(0));
                                } else {
                                    installmentVO.setBusFee(Long.valueOf((busFee.getRs().longValue() * busFeeInstallmentDetail.getFeePercent().longValue()) / 100));
                                }
                            }
                            installmentVO.setAdmissionSchemeDiscountOnBusFee(FeeDiscountCalculator.calculateAdmissionSchemeDiscountOnBusFee(academicYearAdmissionScheme, installmentVO.getBusFee()));
                        }
                    }
                } else if (BusFeeInstallment.SETUP_TYPE_MANUAL.equals(busFeeInstallment.getSetupType()) && busFee.getBusFeeDetails() != null) {
                    for (BusFeeDetail busFeeDetail : busFee.getBusFeeDetails()) {
                        installmentVO = (FeeInstallmentVO) installmentsMap.get(busFeeDetail.getInstallment().getId());
                        if (installmentVO != null) {
                            installmentVO.setBusFee(busFeeDetail.getFee());
                        }
                        installmentVO.setAdmissionSchemeDiscountOnBusFee(FeeDiscountCalculator.calculateAdmissionSchemeDiscountOnBusFee(academicYearAdmissionScheme, installmentVO.getBusFee()));
                    }
                }
            }
        } else if (!(customizeInstallments == null || customizeInstallments.isEmpty())) {
            for (CustomizeInstallment customizeInstallment : customizeInstallments) {
                installmentVO = new FeeInstallmentVO();
                if (customizeInstallment.getDueDate() != null) {
                    installmentVO.setInstallmentDueDate(DateUtil.formatDate(customizeInstallment.getDueDate(), "dd-MMM-yyyy"));
                }
                installmentVO.setInstallmentId(customizeInstallment.getInstallment().getId());
                installmentVO.setInstallment(customizeInstallment.getInstallment().getName());
                installmentVO.setAcademicFee(Long.valueOf(customizeInstallment.getTotalFee()));
                installmentVO.setBusFee(Long.valueOf(customizeInstallment.getTotalBusFee()));
                installmentVO.setCustFeeInstallmentId(customizeInstallment.getId());
                installmentVO.setLateFeeRule(customizeInstallment.getLateFeeRule());
                installmentVO.setCalculateLateFee(Boolean.TRUE);
                installmentsMap.put(customizeInstallment.getInstallment().getId(), installmentVO);
            }
        }
        if (installmentsMap.isEmpty()) {
            return null;
        }
        if (!(feeTransactionDetails == null || feeTransactionDetails.isEmpty())) {
            for (FeeTransactionDetail feeTransactionDetail : feeTransactionDetails) {
                installmentVO = (FeeInstallmentVO) installmentsMap.get(feeTransactionDetail.getInstallment().getId());
                installmentVO.setPaidFee(Long.valueOf(installmentVO.getPaidFee().longValue() + feeTransactionDetail.getAmount().longValue()));
            }
        }
        if (!(daysOverdues == null || daysOverdues.isEmpty())) {
            for (DaysOverdue daysOverdue : daysOverdues) {
                installmentVO = (FeeInstallmentVO) installmentsMap.get(daysOverdue.getInstallment().getId());
                if (installmentVO != null) {
                    installmentVO.setDaysOverDue(Long.valueOf((long) daysOverdue.getDaysOverdue()));
                    if (daysOverdue.getDiscount() != null) {
                        installmentVO.setLateFeeDiscount(Long.valueOf(daysOverdue.getDiscount().longValue()));
                    }
                    if (daysOverdue.getCalculate() == null) {
                        installmentVO.setCalculateLateFee(Boolean.FALSE);
                    } else {
                        installmentVO.setCalculateLateFee(daysOverdue.getCalculate());
                    }
                }
            }
        }
        return new ArrayList(installmentsMap.values());
    }

    private Long calculateAdmissionSchemeDiscount(AcademicYearAdmissionScheme academicYearAdmissionScheme, InstallmentHeadFee installmentHeadFee) {
        Long discount = Long.valueOf(0);
        if (academicYearAdmissionScheme == null) {
            return Long.valueOf(0);
        }
        if (academicYearAdmissionScheme.getSchemeDetails() == null) {
            return Long.valueOf(0);
        }
        if (installmentHeadFee == null) {
            return Long.valueOf(0);
        }
        for (AcademicSessionAdmisionSchemeDetail academicSessionAdmisionSchemeDetail : academicYearAdmissionScheme.getSchemeDetails()) {
            if (installmentHeadFee.getFeeHeadId().equals(academicSessionAdmisionSchemeDetail.getFeeHead().getId())) {
                discount = Long.valueOf((installmentHeadFee.getAmount().longValue() * academicSessionAdmisionSchemeDetail.getDiscount().longValue()) / 100);
                break;
            }
        }
        return discount;
    }

    public List<FeePaymentHeadVO> prepareInstallment(CustomizeInstallment customizeInstallment, AcademicYearFeeInstallment academicYearFeeInstallment, BusFee busFee, BusFeeInstallmentDetail busFeeInstallmentDetail, FeeHead busFeeHead, List<FeeTransactionDetail> feeTransactionDetails, DaysOverdue daysOverdue, FeeHead lateFeeHead) {
        FeePaymentHeadVO feePaymentHeadVO;
        Map<Long, FeePaymentHeadVO> feeHeadMap = new HashMap();
        Boolean feeCustomized = Boolean.FALSE;
        if (customizeInstallment != null && customizeInstallment.getCustomizeInstallmentDetails() != null && !customizeInstallment.getCustomizeInstallmentDetails().isEmpty()) {
            feeCustomized = Boolean.TRUE;
            for (CustomizeInstallmentDetail customizeInstallmentDetail : customizeInstallment.getCustomizeInstallmentDetails()) {
                feePaymentHeadVO = new FeePaymentHeadVO();
                feePaymentHeadVO = new FeePaymentHeadVO();
                feePaymentHeadVO.setFeeHeadId(customizeInstallmentDetail.getFeeHead().getId());
                feePaymentHeadVO.setFeeHeadName(customizeInstallmentDetail.getFeeHead().getName());
                if (customizeInstallmentDetail.getAmount() != null) {
                    feePaymentHeadVO.setAmount(feePaymentHeadVO.getAmount() + customizeInstallmentDetail.getAmount().longValue());
                }
                feeHeadMap.put(feePaymentHeadVO.getFeeHeadId(), feePaymentHeadVO);
            }
        } else if (!(academicYearFeeInstallment == null || academicYearFeeInstallment.getAcademicYearFeeInstallmentDetails() == null || academicYearFeeInstallment.getAcademicYearFeeInstallmentDetails().isEmpty())) {
            for (AcademicYearFeeInstallmentDetail academicYearFeeInstallmentDetail : academicYearFeeInstallment.getAcademicYearFeeInstallmentDetails()) {
                feePaymentHeadVO = new FeePaymentHeadVO();
                feePaymentHeadVO.setFeeHeadId(academicYearFeeInstallmentDetail.getFeeHead().getId());
                feePaymentHeadVO.setFeeHeadName(academicYearFeeInstallmentDetail.getFeeHead().getName());
                if (academicYearFeeInstallmentDetail.getAmount() != null) {
                    feePaymentHeadVO.setAmount(feePaymentHeadVO.getAmount() + academicYearFeeInstallmentDetail.getAmount().longValue());
                }
                feeHeadMap.put(feePaymentHeadVO.getFeeHeadId(), feePaymentHeadVO);
            }
            if (!(busFee == null || busFeeInstallmentDetail == null)) {
                long totalBusFee;
                if (busFee.getRs() == null || busFeeInstallmentDetail.getFeePercent() == null) {
                    totalBusFee = 0 + 0;
                } else {
                    totalBusFee = 0 + ((busFee.getRs().longValue() * busFeeInstallmentDetail.getFeePercent().longValue()) / 100);
                }
                FeePaymentHeadVO busFeeHeadVO = new FeePaymentHeadVO();
                busFeeHeadVO.setFeeHeadId(busFeeHead.getId());
                busFeeHeadVO.setFeeHeadName(busFeeHead.getName());
                busFeeHeadVO.setAmount(totalBusFee);
                feeHeadMap.put(busFeeHeadVO.getFeeHeadId(), busFeeHeadVO);
            }
        }
        if (feeHeadMap.isEmpty()) {
            return null;
        }
        FeePaymentHeadVO lateFeeHeadVO = new FeePaymentHeadVO();
        lateFeeHeadVO.setFeeHeadId(lateFeeHead.getId());
        lateFeeHeadVO.setFeeHeadName(lateFeeHead.getName());
        lateFeeHeadVO.setAmount(0);
        feeHeadMap.put(lateFeeHeadVO.getFeeHeadId(), lateFeeHeadVO);
        if (daysOverdue != null) {
            if (feeCustomized.booleanValue()) {
                lateFeeHeadVO.setAmount(LateFeeCalculator.calculateLateFee((long) daysOverdue.getDaysOverdue(), customizeInstallment.getLateFeeRule()) - ((long) daysOverdue.getDiscount().intValue()));
            } else {
                lateFeeHeadVO.setAmount(LateFeeCalculator.calculateLateFee((long) daysOverdue.getDaysOverdue(), academicYearFeeInstallment.getLateFeeRule()) - ((long) daysOverdue.getDiscount().intValue()));
            }
        }
        if (!(feeTransactionDetails == null || feeTransactionDetails.isEmpty())) {
            for (FeeTransactionDetail feeTransactionDetail : feeTransactionDetails) {
                feePaymentHeadVO = (FeePaymentHeadVO) feeHeadMap.get(feeTransactionDetail.getFeeHead().getId());
                if (!(feePaymentHeadVO == null || feeTransactionDetail.getAmount() == null)) {
                    feePaymentHeadVO.setDeplositFee(feePaymentHeadVO.getDeplositFee() + feeTransactionDetail.getAmount().longValue());
                }
            }
        }
        return new ArrayList(feeHeadMap.values());
    }

    public List<InstallmentHeadFee> prepareInstallmentNew(CustomizeInstallment customizeInstallment, AcademicYearFeeInstallment academicYearFeeInstallment, BusFee busFee, String busFeeSetupType, BusFeeInstallmentDetail busFeeInstallmentDetail, FeeHead busFeeHead, List<FeeTransactionDetail> feeTransactionDetails, DaysOverdue daysOverdue, FeeHead lateFeeHead, AcademicYearAdmissionScheme academicYearAdmissionScheme) {
        Map<Long, InstallmentHeadFee> feeHeadMap = new HashMap();
        Boolean feeCustomized = Boolean.FALSE;
        InstallmentHeadFee installmentHeadFee;
        if (customizeInstallment != null && customizeInstallment.getCustomizeInstallmentDetails() != null && !customizeInstallment.getCustomizeInstallmentDetails().isEmpty()) {
            feeCustomized = Boolean.TRUE;
            for (CustomizeInstallmentDetail customizeInstallmentDetail : customizeInstallment.getCustomizeInstallmentDetails()) {
                installmentHeadFee = new InstallmentHeadFee();
                installmentHeadFee = new InstallmentHeadFee();
                installmentHeadFee.setFeeHeadId(customizeInstallmentDetail.getFeeHead().getId());
                installmentHeadFee.setFeeHeadName(customizeInstallmentDetail.getFeeHead().getName());
                if (customizeInstallmentDetail.getAmount() != null) {
                    installmentHeadFee.setAmount(Long.valueOf(installmentHeadFee.getAmount().longValue() + customizeInstallmentDetail.getAmount().longValue()));
                }
                feeHeadMap.put(installmentHeadFee.getFeeHeadId(), installmentHeadFee);
            }
        } else if (!(academicYearFeeInstallment == null || academicYearFeeInstallment.getAcademicYearFeeInstallmentDetails() == null || academicYearFeeInstallment.getAcademicYearFeeInstallmentDetails().isEmpty())) {
            for (AcademicYearFeeInstallmentDetail academicYearFeeInstallmentDetail : academicYearFeeInstallment.getAcademicYearFeeInstallmentDetails()) {
                installmentHeadFee = new InstallmentHeadFee();
                installmentHeadFee.setFeeHeadId(academicYearFeeInstallmentDetail.getFeeHead().getId());
                installmentHeadFee.setFeeHeadName(academicYearFeeInstallmentDetail.getFeeHead().getName());
                if (academicYearFeeInstallmentDetail.getAmount() != null) {
                    installmentHeadFee.setAmount(Long.valueOf(installmentHeadFee.getAmount().longValue() + academicYearFeeInstallmentDetail.getAmount().longValue()));
                }
                installmentHeadFee.setAmount(Long.valueOf(installmentHeadFee.getAmount().longValue() - calculateAdmissionSchemeDiscount(academicYearAdmissionScheme, installmentHeadFee).longValue()));
                feeHeadMap.put(installmentHeadFee.getFeeHeadId(), installmentHeadFee);
            }
            if (busFee != null) {
                long totalBusFee = 0;
                if (BusFeeInstallment.SETUP_TYPE_PERCENTAGE.equals(busFeeSetupType) && busFeeInstallmentDetail != null) {
                    totalBusFee = (busFee.getRs() == null || busFeeInstallmentDetail.getFeePercent() == null) ? 0 + 0 : 0 + ((busFee.getRs().longValue() * busFeeInstallmentDetail.getFeePercent().longValue()) / 100);
                } else if (BusFeeInstallment.SETUP_TYPE_MANUAL.equals(busFeeSetupType) && busFee.getBusFeeDetails() != null) {
                    BusFeeDetail busFeeInstallment = null;
                    for (BusFeeDetail busFeeDetail : busFee.getBusFeeDetails()) {
                        if (busFeeDetail.getInstallment().getId().equals(academicYearFeeInstallment.getInstallment().getId())) {
                            busFeeInstallment = busFeeDetail;
                            break;
                        }
                    }
                    if (busFeeInstallment != null) {
                        totalBusFee = busFeeInstallment.getFee().longValue();
                    }
                }
                InstallmentHeadFee busFeeHeadVO = new InstallmentHeadFee();
                busFeeHeadVO.setFeeHeadId(busFeeHead.getId());
                busFeeHeadVO.setFeeHeadName(busFeeHead.getName());
                busFeeHeadVO.setAmount(Long.valueOf(totalBusFee));
                busFeeHeadVO.setAmount(Long.valueOf(busFeeHeadVO.getAmount().longValue() - calculateAdmissionSchemeDiscount(academicYearAdmissionScheme, busFeeHeadVO).longValue()));
                feeHeadMap.put(busFeeHeadVO.getFeeHeadId(), busFeeHeadVO);
            }
        }
        if (feeHeadMap.isEmpty()) {
            return null;
        }
        InstallmentHeadFee lateFeeHeadVO = new InstallmentHeadFee();
        lateFeeHeadVO.setFeeHeadId(lateFeeHead.getId());
        lateFeeHeadVO.setFeeHeadName(lateFeeHead.getName());
        lateFeeHeadVO.setAmount(Long.valueOf(0));
        feeHeadMap.put(lateFeeHeadVO.getFeeHeadId(), lateFeeHeadVO);
        if (daysOverdue != null) {
            if (feeCustomized.booleanValue()) {
                lateFeeHeadVO.setAmount(Long.valueOf(LateFeeCalculator.calculateLateFee((long) daysOverdue.getDaysOverdue(), customizeInstallment.getLateFeeRule()) - ((long) daysOverdue.getDiscount().intValue())));
            } else {
                lateFeeHeadVO.setAmount(Long.valueOf(LateFeeCalculator.calculateLateFee((long) daysOverdue.getDaysOverdue(), academicYearFeeInstallment.getLateFeeRule()) - ((long) daysOverdue.getDiscount().intValue())));
            }
        }
        if (!(feeTransactionDetails == null || feeTransactionDetails.isEmpty())) {
            for (FeeTransactionDetail feeTransactionDetail : feeTransactionDetails) {
                InstallmentHeadFee feePaymentHeadVO = (InstallmentHeadFee) feeHeadMap.get(feeTransactionDetail.getFeeHead().getId());
                if (!(feePaymentHeadVO == null || feeTransactionDetail.getAmount() == null)) {
                    feePaymentHeadVO.setPaid(Long.valueOf(feePaymentHeadVO.getPaid().longValue() + feeTransactionDetail.getAmount().longValue()));
                }
            }
        }
        return new ArrayList(feeHeadMap.values());
    }
}
