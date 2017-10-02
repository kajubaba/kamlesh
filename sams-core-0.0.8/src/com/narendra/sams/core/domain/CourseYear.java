package com.narendra.sams.core.domain;

import java.io.Serializable;

public class CourseYear implements Serializable {
    private static final long serialVersionUID = -6946388201980249228L;
    private Course course;
    private Long id;
    private Short name;
    private Short order;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getName() {
        return this.name;
    }

    public void setName(Short name) {
        this.name = name;
    }

    public Short getOrder() {
        return this.order;
    }

    public void setOrder(Short order) {
        this.order = order;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CourseYear [id=").append(this.id).append(", name=").append(this.name).append("]");
        return builder.toString();
    }
}
