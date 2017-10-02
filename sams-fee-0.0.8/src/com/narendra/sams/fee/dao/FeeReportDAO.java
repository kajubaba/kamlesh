package com.narendra.sams.fee.dao;

import com.narendra.sams.admission.domain.BusStopWiseFee;
import com.narendra.sams.admission.domain.CourseDiscount;
import com.narendra.sams.admission.domain.CourseYearwiseAdmissionCount;
import com.narendra.sams.admission.domain.CourseYearwiseBusFee;
import com.narendra.sams.admission.domain.CourseYearwiseDepositFee;
import com.narendra.sams.admission.domain.CourseYearwiseDiscount;
import com.narendra.sams.admission.domain.CourseYearwiseFee;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentFee;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface FeeReportDAO {
    List<CourseYearwiseAdmissionCount> getAdmissionCount(Long l, List<Long> list, Long l2, Date date);

    long getBusFeePercentage(Long l, Long l2, Short sh, Date date);

    List<BusStopWiseFee> getBusStopWiseAllStudents(Long l, Long l2, Short sh);

    List<BusStopWiseFee> getBusStopWiseCustomizedStudents(Long l, Long l2, Short sh);

    List<CourseYearwiseBusFee> getCourseYearwiseBusFee(Long l, List<Long> list, Boolean bool, Date date);

    List<CourseYearwiseBusFee> getCourseYearwiseBusFeeDiscount(Long l, List<Long> list);

    List<CourseYearwiseDepositFee> getCourseYearwiseDepositFee(Long l, List<Long> list, Long l2, Boolean bool, Boolean bool2);

    List<CourseYearwiseDepositFee> getCourseYearwiseDepositFee(Long l, List<Long> list, Date date, Boolean bool, Boolean bool2, Boolean bool3, Long l2);

    List<CourseYearwiseDiscount> getCourseYearwiseDiscount(Long l, List<Long> list, Long l2);

    List<CourseYearwiseFee> getCourseYearwiseFee(Long l);

    List<CourseYearwiseFee> getCourseYearwiseFee(Long l, Collection<Long> collection, Date date);

    List<CourseYearwiseAdmissionCount> getCustomizeAdmissionCount(Long l, List<Long> list, Date date, Long l2);

    List<Student> getCustomizedStudent(Long l, Long l2, Long l3);

    List<CourseDiscount> getCustomizedStudentFees(Long l, Long l2, Long l3);

    List<StudentFee> getCustomizedStudentFees(Long l, Long l2, Date date, Long l3);

    List<StudentFee> getCustomizedStudentFees(Long l, Date date, Long l2);

    List<CourseDiscount> getDiscountGiven(Long l, Long l2);

    List<Student> getFeeAdjustedStudents(Long l, Long l2);

    List<StudentFee> getPaidFeeByStudents(Collection<String> collection, Long l, Long l2, Date date, Boolean bool, Boolean bool2, Boolean bool3);
}
