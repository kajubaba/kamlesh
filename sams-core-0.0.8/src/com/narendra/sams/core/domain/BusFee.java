package com.narendra.sams.core.domain;

import java.util.Set;

public class BusFee {
    private AcademicYear academicYear;
    private Set<BusFeeDetail> busFeeDetails;
    private BusStop busStop;
    private Long id;
    private Long rs;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public BusStop getBusStop() {
        return this.busStop;
    }

    public void setBusStop(BusStop busStop) {
        this.busStop = busStop;
    }

    public Long getRs() {
        return this.rs;
    }

    public void setRs(Long rs) {
        this.rs = rs;
    }

    public Set<BusFeeDetail> getBusFeeDetails() {
        return this.busFeeDetails;
    }

    public void setBusFeeDetails(Set<BusFeeDetail> busFeeDetails) {
        this.busFeeDetails = busFeeDetails;
    }
}
