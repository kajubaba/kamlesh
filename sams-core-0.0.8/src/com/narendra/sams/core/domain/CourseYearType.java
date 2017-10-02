package com.narendra.sams.core.domain;

public class CourseYearType {
    public static Long SEMESTER_DB_ID = Long.valueOf(2);
    public static Long TYPE_SEMESTER = Long.valueOf(2);
    public static Long TYPE_YEAR = Long.valueOf(1);
    public static Long YEAR_DB_ID = Long.valueOf(1);
    private Long id;
    private String name;
    private Long value;

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

    public Long getValue() {
        return this.value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
