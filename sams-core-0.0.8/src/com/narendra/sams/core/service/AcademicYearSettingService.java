package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AcademicYearCourse;
import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.domain.CourseYearSetting;
import java.util.Collection;
import java.util.List;

public interface AcademicYearSettingService {
    void addCoursesInAcademicYearWithDefaultSettings(Long l, Collection<Long> collection);

    List<AcademicYearCourse> getAcademicYearCourses(Long l);

    List<AcademicYearCourse> getAcademicYearCourses(Long l, Long l2);

    List<CourseYearSetting> getActiveCourseYearSettings(Long l);

    List<Course> getActiveCourses(Long l, Long l2);

    List<CourseYearSetting> getAllCourseYearSettings(Long l, Long l2);

    CourseYearSetting getCourseYearSetting(Long l);

    List<CourseYearSetting> getInvidualCourseSettings(Long l, Long l2);

    List<Course> getNotAddedCourses(Long l);

    List<Course> getNotAddedCourses(Long l, Long l2);

    AcademicYearClass loadAcademicYearClass(Long l);

    void removeCourseFromAcademicYear(Long l);

    void saveCourseYearSetting(Long l, Boolean bool, Long l2, Long l3);
}
