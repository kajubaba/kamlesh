package com.narendra.sams.web.restws.core.form;

public class DocumentForm {
    private Long academicSessionId;
    private Short admissionTypeId;
    private Long id;
    private Boolean mandatory;
    private String name;

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

    public Boolean getMandatory() {
        return this.mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Short getAdmissionTypeId() {
        return this.admissionTypeId;
    }

    public void setAdmissionTypeId(Short admissionTypeId) {
        this.admissionTypeId = admissionTypeId;
    }

    public Long getAcademicSessionId() {
        return this.academicSessionId;
    }

    public void setAcademicSessionId(Long academicSessionId) {
        this.academicSessionId = academicSessionId;
    }
}
