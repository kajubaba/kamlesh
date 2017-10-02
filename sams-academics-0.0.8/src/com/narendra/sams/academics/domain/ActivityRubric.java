package com.narendra.sams.academics.domain;

public class ActivityRubric {
    private Long id;
    private String name;
    private SubjectActivity subjectActivity;

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

    public SubjectActivity getSubjectActivity() {
        return this.subjectActivity;
    }

    public void setSubjectActivity(SubjectActivity subjectActivity) {
        this.subjectActivity = subjectActivity;
    }
}
