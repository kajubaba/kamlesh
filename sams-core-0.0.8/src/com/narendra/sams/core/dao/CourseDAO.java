package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.domain.CourseYear;
import java.util.Collection;
import java.util.List;

public interface CourseDAO {
    Long addCourse(Course course, Long l);

    List<Course> getAllCourses(Long l);

    Course getCourse(Long l);

    List<Course> getCourses(Collection<Long> collection);

    Boolean isCourseNameExist(Long l, String str);

    Course loadCourseByNameAndAffiliationAthority(Long l, String str);

    CourseYear loadCourseYear(Long l);

    void updateCourse(Course course, Long l);
}
