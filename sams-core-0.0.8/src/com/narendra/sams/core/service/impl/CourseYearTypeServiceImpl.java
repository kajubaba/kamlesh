package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.CourseYearTypeDAO;
import com.narendra.sams.core.domain.CourseYearType;
import com.narendra.sams.core.service.CourseYearTypeService;
import java.util.List;

public class CourseYearTypeServiceImpl implements CourseYearTypeService {
    private CourseYearTypeDAO courseYearTypeDAO;

    public CourseYearTypeDAO getCourseYearTypeDAO() {
        return this.courseYearTypeDAO;
    }

    public void setCourseYearTypeDAO(CourseYearTypeDAO courseYearTypeDAO) {
        this.courseYearTypeDAO = courseYearTypeDAO;
    }

    public List<CourseYearType> getAllCourseYearTypes() {
        return this.courseYearTypeDAO.getAllCourseYearTypes();
    }

    public CourseYearType loadCourseYearType(Long id) {
        return this.courseYearTypeDAO.loadCourseYearType(id);
    }
}
