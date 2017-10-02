package com.narendra.sams.fee.dao;

import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.CustomizeInstallment;
import com.narendra.sams.admission.domain.DaysOverdue;
import com.narendra.sams.admission.domain.FeeTransaction;
import com.narendra.sams.admission.domain.FeeTransactionDetail;
import com.narendra.sams.admission.domain.Student;
import java.util.Date;
import java.util.List;

public interface StudentFeeDAO {
    void customizeLateFee(List<DaysOverdue> list, Long l);

    long getAcademicYearBusFeeDeposited(Long l, Long l2, Long l3, Short sh);

    List<ClassHistory> getAllClassHistories(Long l);

    List<FeeTransactionDetail> getAllTransactionDetails(Long l, Long l2, Boolean bool);

    List<CustomizeInstallment> getCustomizeInstallments(Long l, Long l2);

    long getCustomizedBusFeeDeposited(Long l, Long l2, Long l3, Short sh);

    List<DaysOverdue> getDayOverdue(Long l, Long l2);

    DaysOverdue getDaysOverdue(Long l, Long l2, Long l3);

    FeeTransaction getFeeTransaction(Long l);

    List<FeeTransaction> getFeeTransactions(Long l);

    List<FeeTransaction> getFeeTransactions(Long l, Long l2, Long l3);

    List<FeeTransaction> getFeeTransactionsOnAcademicYearFee(Long l, Long l2, Long l3);

    Date getFirstTransactionDatetime(Long l, Long l2);

    List<DaysOverdue> getLateFeeDetails(Long l, Long l2);

    List<FeeTransactionDetail> getPaidFee(Long l, Long l2);

    List<FeeTransactionDetail> getPaidFeeDetails(Long l, Long l2, Long l3, Short[] shArr);

    List<FeeTransactionDetail> getPaidFeeDetailsByAcdemicYearFeeInstallmentId(Long l, Long l2);

    List<FeeTransaction> getStudentFeeTransactions(Long l, Long l2);

    List<Student> getStudentsWhoseFeeIsCustomized(Long l, String str);

    List<FeeTransactionDetail> getTransactionDetailsOfCustomizedInstallment(Long l, Long l2);

    void removeBusStopFromAdjustedFee(Long l, Long l2);

    void saveOrUpdateDaysOverdue(List<DaysOverdue> list);

    void updateCustomizeInstallmenetCommentsTable();

    void updateCustomizeInstallmenetTable();

    void updateDaysOverdueTable();

    void updateFeeDiscountTable();

    void updateFeeTransactionTable();

    void updateLateFeeCalculationFlag(Long l, Boolean bool, Long l2);
}
