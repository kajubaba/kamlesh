package com.narendra.sams.web.restws.core.vo;

public class DocumentVO {
    private String forAdmissionTYpe;
    private Long id;
    private String lastModifiedBy;
    private String lastModifiedOn;
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

    public String getForAdmissionTYpe() {
        return this.forAdmissionTYpe;
    }

    public void setForAdmissionTYpe(String forAdmissionTYpe) {
        this.forAdmissionTYpe = forAdmissionTYpe;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModifiedOn() {
        return this.lastModifiedOn;
    }

    public void setLastModifiedOn(String lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }
}
