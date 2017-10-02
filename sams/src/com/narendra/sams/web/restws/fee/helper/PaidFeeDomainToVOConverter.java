package com.narendra.sams.web.restws.fee.helper;

import com.narendra.sams.admission.domain.FeeHeadPaid;
import com.narendra.sams.admission.domain.FeeTransaction;
import com.narendra.sams.admission.domain.FeeTransactionDetail;
import com.narendra.sams.admission.domain.PaidFee;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.fee.vo.ClasswisePaidFeeVO;
import com.narendra.sams.web.restws.fee.vo.FeeTransactionDetailVO;
import com.narendra.sams.web.restws.fee.vo.FeeTransactionVO;
import com.narendra.sams.web.restws.fee.vo.HeadwisePaidFeeVO;
import com.narendra.sams.web.utils.FeeTransactionClassNameUtils;
import java.util.ArrayList;
import java.util.List;

public class PaidFeeDomainToVOConverter {
    public static List<HeadwisePaidFeeVO> prepareHeadwisePaidFee(List<FeeHeadPaid> paidFees) {
        List<HeadwisePaidFeeVO> headwisePaidFeeVOs = new ArrayList();
        if (paidFees != null) {
            for (FeeHeadPaid feeHeadPaid : paidFees) {
                HeadwisePaidFeeVO headwisePaidFeeVO = new HeadwisePaidFeeVO();
                headwisePaidFeeVO.setAcademicYearId(feeHeadPaid.getAcademicYearId());
                headwisePaidFeeVO.setAcademicYearName(feeHeadPaid.getAcademicYearName());
                headwisePaidFeeVO.setFeeHeadId(feeHeadPaid.getFeeHeadId());
                headwisePaidFeeVO.setFeeHeadName(feeHeadPaid.getFeeHeadName());
                headwisePaidFeeVO.setPaidFee(feeHeadPaid.getPaidFee());
                headwisePaidFeeVOs.add(headwisePaidFeeVO);
            }
        }
        return headwisePaidFeeVOs;
    }

    public static List<ClasswisePaidFeeVO> prepareClasswisePaidFee(List<PaidFee> paidFees) {
        List<ClasswisePaidFeeVO> classwisePaidFeeVOs = new ArrayList();
        if (paidFees != null) {
            for (PaidFee paidFee : paidFees) {
                ClasswisePaidFeeVO classwisePaidFeeVO = new ClasswisePaidFeeVO();
                classwisePaidFeeVO.setClassName(paidFee.getClassName());
                classwisePaidFeeVO.setPaidAmount(paidFee.getPaidAmount());
                classwisePaidFeeVOs.add(classwisePaidFeeVO);
            }
        }
        return classwisePaidFeeVOs;
    }

    public static FeeTransactionVO prepareTransactionDetail(FeeTransaction feeTransaction) {
        FeeTransactionVO feeTransactionVO = new FeeTransactionVO();
        if (feeTransaction != null) {
            feeTransactionVO.setStudentDBId(feeTransaction.getStudent().getId());
            feeTransactionVO.setStudentId(feeTransaction.getStudent().getStudentId());
            feeTransactionVO.setStudentName(feeTransaction.getStudent().getFullName());
            feeTransactionVO.setTransactionId(feeTransaction.getId());
            feeTransactionVO.setTransactionNo(feeTransaction.getTransactionId());
            feeTransactionVO.setReceiptNo(feeTransaction.getRecieptNo());
            feeTransactionVO.setPaidFee(Long.valueOf(feeTransaction.getFeeSum()));
            if (feeTransaction.getPaymentDate() != null) {
                feeTransactionVO.setPaymentDate(DateUtil.formatDate(feeTransaction.getPaymentDate(), "dd-MMM-yyyy"));
            }
            feeTransactionVO.setDepositedBy(feeTransaction.getDepositedBy());
            if (feeTransaction.getTransactionTime() != null) {
                feeTransactionVO.setTransactionDateTime(DateUtil.formatDate(feeTransaction.getTransactionTime(), "dd-MMM-yyyy hh:mm a"));
            }
            feeTransactionVO.setCollectedBy(new StringBuilder(String.valueOf(feeTransaction.getUser().getFirstName())).append(" ").append(feeTransaction.getUser().getLastName()).toString());
            feeTransactionVO.setPaymentMode(feeTransaction.getPaymentMode());
            if (feeTransaction.getAcademicYearFeeInstallment() != null) {
                feeTransactionVO.setFeeInstallment(feeTransaction.getAcademicYearFeeInstallment().getInstallment().getName());
            } else if (feeTransaction.getCustomizeInstallment() != null) {
                feeTransactionVO.setFeeInstallment(feeTransaction.getCustomizeInstallment().getInstallment().getName());
            }
            feeTransactionVO.setPaymentComments(feeTransaction.getComments());
            feeTransactionVO.setPaymentAcademicYear(feeTransaction.getAcademicYear().getName());
            feeTransactionVO.setPaymentClass(FeeTransactionClassNameUtils.getClassName(feeTransaction));
            List<FeeTransactionDetailVO> feeTransactionDetailVOs = new ArrayList();
            for (FeeTransactionDetail feeTransactionDetail : feeTransaction.getFeeTransactionDetails()) {
                FeeTransactionDetailVO feeTransactionDetailVO = new FeeTransactionDetailVO();
                feeTransactionDetailVO.setFeeHeadName(feeTransactionDetail.getFeeHead().getName());
                feeTransactionDetailVO.setPaidAmount(feeTransactionDetail.getAmount());
                feeTransactionDetailVOs.add(feeTransactionDetailVO);
            }
            feeTransactionVO.setPaidFees(feeTransactionDetailVOs);
        }
        return feeTransactionVO;
    }

    public static List<FeeTransactionVO> prepareFeeTransactions(List<FeeTransaction> feeTransactions) {
        List<FeeTransactionVO> feeTransactionVOs = new ArrayList();
        if (feeTransactions == null) {
            return feeTransactionVOs;
        }
        feeTransactionVOs = new ArrayList();
        for (FeeTransaction feeTransaction : feeTransactions) {
            FeeTransactionVO feeTransactionVO = new FeeTransactionVO();
            feeTransactionVO.setStudentDBId(feeTransaction.getStudent().getId());
            feeTransactionVO.setStudentId(feeTransaction.getStudent().getStudentId());
            feeTransactionVO.setStudentName(feeTransaction.getStudent().getFullName());
            feeTransactionVO.setFatherName(feeTransaction.getStudent().getFatherName());
            feeTransactionVO.setPaymentClass(FeeTransactionClassNameUtils.getClassName(feeTransaction));
            feeTransactionVO.setTransactionId(feeTransaction.getId());
            feeTransactionVO.setTransactionNo(feeTransaction.getTransactionId());
            feeTransactionVO.setReceiptNo(feeTransaction.getRecieptNo());
            feeTransactionVO.setPaidFee(Long.valueOf(feeTransaction.getFeeSum()));
            if (feeTransaction.getPaymentDate() != null) {
                feeTransactionVO.setPaymentDate(DateUtil.formatDate(feeTransaction.getPaymentDate(), "dd-MMM-yyyy"));
            } else {
                feeTransactionVO.setPaymentDate("");
            }
            if (feeTransaction.getTransactionTime() != null) {
                feeTransactionVO.setTransactionDateTime(DateUtil.formatDate(feeTransaction.getPaymentDate(), "dd-MMM-yyyy hh:mm a"));
            } else {
                feeTransactionVO.setTransactionDateTime("");
            }
            feeTransactionVO.setPaymentComments(feeTransaction.getComments());
            feeTransactionVO.setDepositedBy(feeTransaction.getDepositedBy());
            if (feeTransaction.getUser() != null) {
                feeTransactionVO.setCollectedBy(feeTransaction.getUser().getFullName());
            }
            feeTransactionVO.setPaymentMode(feeTransaction.getPaymentMode());
            feeTransactionVO.setPaymentAcademicYear(feeTransaction.getAcademicYear().getName());
            if (feeTransaction.getAcademicYearFeeInstallment() != null) {
                feeTransactionVO.setFeeInstallment(feeTransaction.getAcademicYearFeeInstallment().getInstallment().getName());
            } else if (feeTransaction.getCustomizeInstallment() != null) {
                try {
                    feeTransactionVO.setFeeInstallment(feeTransaction.getCustomizeInstallment().getInstallment().getName());
                } catch (Exception e) {
                    feeTransactionVO.setFeeInstallment("");
                    e.printStackTrace();
                }
            }
            feeTransactionVOs.add(feeTransactionVO);
        }
        return feeTransactionVOs;
    }
}
