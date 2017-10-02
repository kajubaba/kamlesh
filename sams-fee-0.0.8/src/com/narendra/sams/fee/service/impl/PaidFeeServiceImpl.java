package com.narendra.sams.fee.service.impl;

import com.narendra.sams.admission.dao.PaidFeeDAO;
import com.narendra.sams.admission.domain.DateWisePaidFee;
import com.narendra.sams.admission.domain.FeeHeadPaid;
import com.narendra.sams.admission.domain.FeeTransaction;
import com.narendra.sams.admission.domain.PaidFee;
import com.narendra.sams.admission.domain.StudentPaidFee;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.fee.service.PaidFeeService;
import java.util.Date;
import java.util.List;

public class PaidFeeServiceImpl implements PaidFeeService {
    private PaidFeeDAO paidFeeDAO;

    public PaidFeeDAO getPaidFeeDAO() {
        return this.paidFeeDAO;
    }

    public void setPaidFeeDAO(PaidFeeDAO paidFeeDAO) {
        this.paidFeeDAO = paidFeeDAO;
    }

    public List<PaidFee> getClassWisePaidFee(Long instituteId, Long academicYearId) {
        return this.paidFeeDAO.getClassWisePaidFee(instituteId, academicYearId);
    }

    public List<PaidFee> getClassWisePaidFee(Long instituteId, Date fromDate, Date toDate, Long academicYearId) {
        if (fromDate != null) {
            fromDate = DateUtil.makeStartDate(fromDate);
        }
        if (toDate != null) {
            toDate = DateUtil.makeEndDate(toDate);
        }
        return this.paidFeeDAO.getClassWisePaidFee(instituteId, fromDate, toDate, academicYearId);
    }

    public List<FeeTransaction> getFeeTransactions(Long instituteId, Date fromDate, Date toDate, Long academicYearId) {
        if (fromDate != null) {
            fromDate = DateUtil.makeStartDate(fromDate);
        }
        if (toDate != null) {
            toDate = DateUtil.makeEndDate(toDate);
        }
        return this.paidFeeDAO.getFeeTransactions(instituteId, fromDate, toDate, academicYearId);
    }

    public List<FeeHeadPaid> getHeadwisePaidFee(Long instituteId, Date fromDate, Date toDate, Long academicYearId) {
        if (fromDate != null) {
            fromDate = DateUtil.makeStartDate(fromDate);
        }
        if (toDate != null) {
            toDate = DateUtil.makeEndDate(toDate);
        }
        return this.paidFeeDAO.getHeadwisePaidFee(instituteId, fromDate, toDate, academicYearId);
    }

    public List<StudentPaidFee> getHeadwisePaidFee(Long instituteId, Long feeHeadId, Date fromDate, Date toDate, Long academicYearId) {
        if (fromDate != null) {
            fromDate = DateUtil.makeStartDate(fromDate);
        }
        if (toDate != null) {
            toDate = DateUtil.makeEndDate(toDate);
        }
        return this.paidFeeDAO.getHeadwisePaidFee(instituteId, feeHeadId, fromDate, toDate, academicYearId);
    }

    public List<FeeTransaction> getRecentFeeTransactions(Long instituteId) {
        return this.paidFeeDAO.getRecentFeeTransactions(instituteId);
    }

    public List<FeeTransaction> getAllFeeTransactions(Long instituteId, Long academicYearId, Date fromDate, Date toDate) {
        if (fromDate != null) {
            fromDate = DateUtil.makeStartDate(fromDate);
        }
        if (toDate != null) {
            toDate = DateUtil.makeEndDate(toDate);
        }
        return this.paidFeeDAO.getAllFeeTransactions(instituteId, academicYearId, fromDate, toDate);
    }

    public Long getTotalPaidFee(Long academicYearId) {
        return this.paidFeeDAO.getTotalPaidFee(academicYearId);
    }

    public Long getTodayPaidFee(Long instituteId) {
        Date todaysDate = DateUtil.getSystemDate();
        return this.paidFeeDAO.getPaidFee(instituteId, DateUtil.makeStartDate(todaysDate), DateUtil.makeEndDate(todaysDate));
    }

    public FeeTransaction getFeeTransaction(Long feeTransactionId) {
        return this.paidFeeDAO.getFeeTransaction(feeTransactionId);
    }

    public List<DateWisePaidFee> getDateWisePaidFee(Long instituteId, Long feeHeadId, Long academicYearId, Date fromDate, Date toDate) {
        if (fromDate != null) {
            fromDate = DateUtil.makeStartDate(fromDate);
        }
        if (toDate != null) {
            toDate = DateUtil.makeEndDate(toDate);
        }
        return this.paidFeeDAO.getDateWisePaidFee(instituteId, feeHeadId, academicYearId, fromDate, toDate);
    }

    public void deleteFeeTransaction(Long feeTransactionId) {
        this.paidFeeDAO.deleteFeeTransaction(feeTransactionId);
    }

    public Boolean isFeePaidInCourseYearSetting(Long courseYearSetting, Short admissionTypeId) {
        List<FeeTransaction> feeTransactions = this.paidFeeDAO.getFeeTransactionInCourseYearSetting(courseYearSetting, admissionTypeId);
        if (feeTransactions == null || feeTransactions.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
