package com.narendra.sams.fee.service;

import com.narendra.sams.admission.domain.CustomizeInstallment;
import com.narendra.sams.admission.domain.DaysOverdue;
import com.narendra.sams.admission.domain.FeeTransaction;
import com.narendra.sams.admission.domain.FeeTransactionDetail;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentDueFee;
import com.narendra.sams.admission.domain.StudentDueFeeHeadWise;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface StudentFeeService {
    void customizeLateFee(List<DaysOverdue> list, Long l);

    List<FeeTransactionDetail> getAllTransactionDetails(Long l, Long l2, Boolean bool);

    List<CustomizeInstallment> getCustomizeInstallments(Long l, Long l2);

    List<DaysOverdue> getDayOverdue(Long l, Long l2);

    DaysOverdue getDaysOverdue(Long l, Long l2, Long l3);

    List<StudentDueFee> getDueStudentsForNotice(Collection<Long> collection, Long l, Date date);

    List<StudentDueFeeHeadWise> getDueStudentsHeadWiseForNotice(Collection<Long> collection, Long l, Date date);

    FeeTransaction getFeeTransaction(Long l);

    List<FeeTransaction> getFeeTransactions(Long l);

    List<FeeTransaction> getFeeTransactions(Long l, Long l2, Long l3);

    List<FeeTransaction> getFeeTransactionsOnAcademicYearFee(Long l, Long l2, Long l3);

    List<DaysOverdue> getLateFeeDetails(Long l, Long l2);

    List<FeeTransactionDetail> getPaidFeeDetails(Long l, Long l2, Long l3, Short[] shArr);

    List<FeeTransactionDetail> getPaidFeeDetailsByAcdemicYearFeeInstallmentId(Long l, Long l2);

    StudentDueFee getStudentDueFee(Long l, Long l2, Date date);

    List<FeeTransaction> getStudentFeeTransactions(Long l, Long l2);

    List<Student> getStudentsWhoseFeeIsCustomized(Long l, String str);

    List<FeeTransactionDetail> getTransactionDetailsOfCustomizedInstallment(Long l, Long l2);

    Boolean isCurrentClassFeeCustomized(Long l);

    void removeBusStopFromAdjustedFee(Long l, Long l2);

    void saveAdmissiondate(Long l);

    void saveOrUpdateDaysOverdue(List<DaysOverdue> list);

    void updateCustomizeInstallmenetCommentsTable();

    void updateCustomizeInstallmenetTable();

    void updateDaysOverdueTable();

    void updateFeeDiscountTable();

    void updateFeeTransactionTable();

    void updateLateFeeCalculationFlag(Long l, Boolean bool, Long l2);
}
