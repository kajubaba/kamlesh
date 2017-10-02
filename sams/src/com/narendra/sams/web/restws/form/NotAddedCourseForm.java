package com.narendra.sams.web.restws.form;

import java.util.Collection;

public class NotAddedCourseForm {
    private Long academicSessionId;
    private Collection<Long> courseIds;

    public Long getAcademicSessionId() {
        return this.academicSessionId;
    }

    public void setAcademicSessionId(Long academicSessionId) {
        this.academicSessionId = academicSessionId;
    }

    public Collection<Long> getCourseIds() {
        return this.courseIds;
    }

    public void setCourseIds(Collection<Long> courseIds) {
        this.courseIds = courseIds;
    }
}
