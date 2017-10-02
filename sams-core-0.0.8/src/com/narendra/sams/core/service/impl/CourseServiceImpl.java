package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.CourseDAO;
import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.domain.CourseYear;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.CourseService;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private CourseDAO courseDAO;

    public CourseDAO getCourseDAO() {
        return this.courseDAO;
    }

    public void setCourseDAO(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public List<Course> getAllCourses(Long instituteId) {
        return this.courseDAO.getAllCourses(instituteId);
    }

    public Course getCourse(Long courseId) {
        return this.courseDAO.getCourse(courseId);
    }

    public Long saveCourse(Course course, Long userId) throws DuplicateNameFoundException {
        if (course == null) {
            return null;
        }
        Long courseId = course.getId();
        if (course.getId() != null) {
            Course persistCourse = this.courseDAO.loadCourseByNameAndAffiliationAthority(course.getAffiliatedTo().getId(), course.getName());
            if (persistCourse == null || persistCourse.getId().equals(course.getId())) {
                this.courseDAO.updateCourse(course, userId);
                return courseId;
            }
            throw new DuplicateNameFoundException("Course name ['" + course.getName() + "'] already exist");
        } else if (this.courseDAO.isCourseNameExist(course.getAffiliatedTo().getId(), course.getName()).booleanValue()) {
            throw new DuplicateNameFoundException("Course name ['" + course.getName() + "'] already exist");
        } else {
            course.setCourseYears(new LinkedHashSet());
            for (short yearCounter = (short) 1; yearCounter <= course.getDuration().shortValue(); yearCounter = (short) (yearCounter + 1)) {
                CourseYear courseYear = new CourseYear();
                courseYear.setName(Short.valueOf(yearCounter));
                courseYear.setOrder(Short.valueOf(yearCounter));
                courseYear.setCourse(course);
                course.getCourseYears().add(courseYear);
            }
            return this.courseDAO.addCourse(course, userId);
        }
    }

    public List<Course> getCourses(Collection<Long> ids) {
        return this.courseDAO.getCourses(ids);
    }

    public CourseYear loadCourseYear(Long id) {
        return this.courseDAO.loadCourseYear(id);
    }
}
