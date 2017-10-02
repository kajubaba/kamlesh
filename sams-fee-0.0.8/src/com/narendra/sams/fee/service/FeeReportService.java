package com.narendra.sams.fee.service;

import com.narendra.sams.admission.domain.CourseDiscount;
import com.narendra.sams.admission.domain.CourseYearFeeSummary;
import com.narendra.sams.admission.domain.StudentFee;
import java.util.Date;
import java.util.List;

public interface FeeReportService {
    List<CourseYearFeeSummary> getAnnualFeeReport(Long l, Long l2);

    List<CourseDiscount> getDiscountedStudents(Long l, Long l2);

    List<CourseYearFeeSummary> getDueFeeReport(Long l, Date date, Long l2);

    List<StudentFee> getDueStudents(Long l, Long l2, Date date, Long l3);

    List<StudentFee> getDueStudents(Long l, Date date, Long l2);

    List<Date> getUniqueInstallmentDueDates(Long l);
}
