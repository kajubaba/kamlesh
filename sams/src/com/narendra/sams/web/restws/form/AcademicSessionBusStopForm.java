package com.narendra.sams.web.restws.form;

import java.util.List;

public class AcademicSessionBusStopForm {
    private Long academicSessionId;
    private List<Long> busStopIds;

    public Long getAcademicSessionId() {
        return this.academicSessionId;
    }

    public void setAcademicSessionId(Long academicSessionId) {
        this.academicSessionId = academicSessionId;
    }

    public List<Long> getBusStopIds() {
        return this.busStopIds;
    }

    public void setBusStopIds(List<Long> busStopIds) {
        this.busStopIds = busStopIds;
    }
}
