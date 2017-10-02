package com.narendra.sams.admission.domain;

public class BusStopWiseFee {
    private Long busFee;
    private Long busStopId;
    private Long courseYearId;
    private Long studentCount;

    public Long getBusStopId() {
        return this.busStopId;
    }

    public void setBusStopId(Long busStopId) {
        this.busStopId = busStopId;
    }

    public Long getStudentCount() {
        return this.studentCount;
    }

    public void setStudentCount(Long studentCount) {
        this.studentCount = studentCount;
    }

    public Long getBusFee() {
        return this.busFee;
    }

    public void setBusFee(Long busFee) {
        this.busFee = busFee;
    }

    public Long getCourseYearId() {
        return this.courseYearId;
    }

    public void setCourseYearId(Long courseYearId) {
        this.courseYearId = courseYearId;
    }
}
