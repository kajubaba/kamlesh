package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.CourseYearType;
import java.util.List;

public interface CourseYearTypeService {
    List<CourseYearType> getAllCourseYearTypes();

    CourseYearType loadCourseYearType(Long l);
}
