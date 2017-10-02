package com.narendra.sams.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.apache.commons.beanutils.BeanComparator;

public class AcademicYearCourse implements Serializable {
    private static final long serialVersionUID = 7948636605915567405L;
    private AcademicYear academicYear;
    private Boolean active;
    private Course course;
    private Set<CourseYearSetting> courseYearSettings;
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<CourseYearSetting> getCourseYearSettings() {
        return this.courseYearSettings;
    }

    public void setCourseYearSettings(Set<CourseYearSetting> courseYearSettings) {
        this.courseYearSettings = courseYearSettings;
    }

    public List<CourseYearSetting> getSortedCourseYearSettings() {
        if (this.courseYearSettings == null) {
            return null;
        }
        List<CourseYearSetting> list = new ArrayList(this.courseYearSettings);
        Collections.sort(list, new BeanComparator("courseYear.order"));
        return list;
    }
}
