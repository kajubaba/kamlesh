package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.domain.CourseYear;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import java.util.Collection;
import java.util.List;

public interface CourseService {
    List<Course> getAllCourses(Long l);

    Course getCourse(Long l);

    List<Course> getCourses(Collection<Long> collection);

    CourseYear loadCourseYear(Long l);

    Long saveCourse(Course course, Long l) throws DuplicateNameFoundException;
}
