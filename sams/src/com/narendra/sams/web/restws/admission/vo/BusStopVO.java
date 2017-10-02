package com.narendra.sams.web.restws.admission.vo;

public class BusStopVO {
    private Long academicSessionStopId;
    private Long id;
    private String name;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAcademicSessionStopId() {
        return this.academicSessionStopId;
    }

    public void setAcademicSessionStopId(Long academicSessionStopId) {
        this.academicSessionStopId = academicSessionStopId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
