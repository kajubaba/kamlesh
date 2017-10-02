package com.narendra.sams.academics.domain;

import java.util.Set;

public class SubjectActivity {
    private ClassSubject classSubject;
    private Long id;
    private String name;
    private Set<ActivityRubric> rubrics;

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

    public ClassSubject getClassSubject() {
        return this.classSubject;
    }

    public void setClassSubject(ClassSubject classSubject) {
        this.classSubject = classSubject;
    }

    public Set<ActivityRubric> getRubrics() {
        return this.rubrics;
    }

    public void setRubrics(Set<ActivityRubric> rubrics) {
        this.rubrics = rubrics;
    }
}
