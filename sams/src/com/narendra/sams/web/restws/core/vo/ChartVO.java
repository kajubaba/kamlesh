package com.narendra.sams.web.restws.core.vo;

public class ChartVO {
    private String className;
    private String color;
    private Long students;

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getStudents() {
        return this.students;
    }

    public void setStudents(Long students) {
        this.students = students;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
