package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.AcademicYearCourse;
import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.AcademicYearFeeDetail;
import com.narendra.sams.core.domain.AcademicYearFeeInstallment;
import java.util.Date;
import java.util.List;

public interface AcademicYearFeeDAO {
    void deleteAcademicYearFeeInstallment(AcademicYearFeeInstallment academicYearFeeInstallment);

    List<Date> getAcademicInstallmentDueDates(Long l);

    AcademicYearFee getAcademicYearFee(Long l, Long l2, Short sh);

    AcademicYearFee getAcademicYearFee(Long l, Short sh);

    List<AcademicYearFee> getAcademicYearFee(Long l, Long l2);

    AcademicYearFee getAcademicYearFeeById(Long l);

    List<AcademicYearFee> getAcademicYearFeeForAllAdmissionType(Long l);

    AcademicYearFeeInstallment getAcademicYearFeeInstallment(Long l);

    List<AcademicYearFeeInstallment> getAcademicYearFeeInstallments(Long l);

    List<AcademicYearFeeInstallment> getDueFeeInstallments(Long l, Long l2, Short sh, Date date);

    List<AcademicYearFeeInstallment> getFeeInstallments(Long l, Long l2, Short sh, Short[] shArr);

    void saveAcademicYearCourses(List<AcademicYearCourse> list);

    void saveAcademicYearFeeInstallments(List<AcademicYearFeeInstallment> list);

    void saveCourseYearFee(List<AcademicYearFee> list);

    void updateCourseYearFeeDetail(List<AcademicYearFeeDetail> list);

    void updateInstallmentDueDateAndLateFeeRule(List<AcademicYearFeeInstallment> list);
}
