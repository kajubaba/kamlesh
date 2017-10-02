package com.narendra.sams.web.restws.vo;

public class AcademicSessionClassVO {
    private Boolean active;
    private String displayName;
    private Long id;
    private Short intake;
    private String nextClassName;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getNextClassName() {
        return this.nextClassName;
    }

    public void setNextClassName(String nextClassName) {
        this.nextClassName = nextClassName;
    }

    public Short getIntake() {
        return this.intake;
    }

    public void setIntake(Short intake) {
        this.intake = intake;
    }
}
