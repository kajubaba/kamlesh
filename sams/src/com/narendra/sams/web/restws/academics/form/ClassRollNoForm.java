package com.narendra.sams.web.restws.academics.form;

import java.util.List;

public class ClassRollNoForm {
    private Long classId;
    private List<RollNoForm> rollNoForms;

    public Long getClassId() {
        return this.classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public List<RollNoForm> getRollNoForms() {
        return this.rollNoForms;
    }

    public void setRollNoForms(List<RollNoForm> rollNoForms) {
        this.rollNoForms = rollNoForms;
    }
}
