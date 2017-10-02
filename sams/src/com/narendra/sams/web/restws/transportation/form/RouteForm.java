package com.narendra.sams.web.restws.transportation.form;

public class RouteForm {
    private Boolean forceCopy;
    private String from;
    private Long id;
    private String name;
    private Integer plannedStudents;
    private String to;
    private String type;

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

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPlannedStudents() {
        return this.plannedStudents;
    }

    public void setPlannedStudents(Integer plannedStudents) {
        this.plannedStudents = plannedStudents;
    }

    public Boolean getForceCopy() {
        return this.forceCopy;
    }

    public void setForceCopy(Boolean forceCopy) {
        this.forceCopy = forceCopy;
    }
}
