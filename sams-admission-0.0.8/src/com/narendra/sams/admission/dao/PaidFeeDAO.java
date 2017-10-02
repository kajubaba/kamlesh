package com.narendra.sams.admission.dao;

import com.narendra.sams.admission.domain.DateWisePaidFee;
import com.narendra.sams.admission.domain.FeeHeadPaid;
import com.narendra.sams.admission.domain.FeeTransaction;
import com.narendra.sams.admission.domain.PaidFee;
import com.narendra.sams.admission.domain.StudentPaidFee;
import java.util.Date;
import java.util.List;

public interface PaidFeeDAO {
    void deleteFeeTransaction(Long l);

    List<FeeTransaction> getAllFeeTransactions(Long l, Long l2, Date date, Date date2);

    List<PaidFee> getClassWisePaidFee(Long l, Long l2);

    List<PaidFee> getClassWisePaidFee(Long l, Date date, Date date2, Long l2);

    List<DateWisePaidFee> getDateWisePaidFee(Long l, Long l2, Long l3, Date date, Date date2);

    FeeTransaction getFeeTransaction(Long l);

    List<FeeTransaction> getFeeTransactionInCourseYearSetting(Long l, Short sh);

    List<FeeTransaction> getFeeTransactions(Long l, Date date, Date date2, Long l2);

    List<StudentPaidFee> getHeadwisePaidFee(Long l, Long l2, Date date, Date date2, Long l3);

    List<FeeHeadPaid> getHeadwisePaidFee(Long l, Date date, Date date2, Long l2);

    Long getPaidFee(Long l, Date date, Date date2);

    List<FeeTransaction> getRecentFeeTransactions(Long l);

    Long getTotalPaidFee(Long l);
}
