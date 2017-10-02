package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.domain.InstituteSetting;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import java.util.List;

public interface AcademicYearService {
    void copyAcademicYear(Long l, Long l2, Long l3);

    AcademicYear getAcademicYearById(long j);

    AcademicYear getAcademicYearByName(Long l, String str);

    AcademicYear getAcademicYearByOrder(Short sh, Long l);

    List<AcademicYearClass> getActiveAcademicYearClassess(Long l, Long l2);

    AcademicYear getActiveAcademicYearForAdmission(Long l);

    AcademicYear getActiveAcademicYearForEnquiry(Long l);

    List<AcademicYear> getActiveAcademicYears(Long l);

    List<AcademicYearClass> getActiveClassess(Long l, Long l2);

    List<Course> getActiveCourses(Long l, Long l2);

    List<AcademicYear> getAllAcademicYears(Long l);

    List<AcademicYear> getFeeAcademicYears(Long l);

    List<InstituteSetting> getInstituteSettings();

    List<AcademicYearClass> getPromotionClasses(Long l, Long l2);

    Boolean isAcademicYearNameExists(Long l, String str);

    void publishAcademicYear(Long l);

    Long saveAcademicYear(AcademicYear academicYear, Long l) throws DuplicateNameFoundException;
}
