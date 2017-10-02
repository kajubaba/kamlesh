package com.narendra.sams.web.course.vo;

import com.narendra.sams.core.domain.CourseYearSetting;
import java.util.List;

public class CourseVO {
    private List<CourseYearSetting> courseYearSettings;
    private String displayName;
    private Long id;
    private String name;

    public CourseVO(Long id, String name, String displayName) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
    }
    
    public CourseVO(){
    	
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<CourseYearSetting> getCourseYearSettings() {
        return this.courseYearSettings;
    }

    public void setCourseYearSettings(List<CourseYearSetting> courseYearSettings) {
        this.courseYearSettings = courseYearSettings;
    }
}
