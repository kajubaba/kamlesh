package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.CourseYearType;
import java.util.List;

public interface CourseYearTypeDAO {
    List<CourseYearType> getAllCourseYearTypes();

    CourseYearType loadCourseYearType(Long l);
}
