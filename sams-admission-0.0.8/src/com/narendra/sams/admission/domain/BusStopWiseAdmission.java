package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.BusStop;

public class BusStopWiseAdmission {
    private Long admissionCount;
    private BusStop busStop;

    public BusStop getBusStop() {
        return this.busStop;
    }

    public void setBusStop(BusStop busStop) {
        this.busStop = busStop;
    }

    public Long getAdmissionCount() {
        return this.admissionCount;
    }

    public void setAdmissionCount(Long admissionCount) {
        this.admissionCount = admissionCount;
    }
}
