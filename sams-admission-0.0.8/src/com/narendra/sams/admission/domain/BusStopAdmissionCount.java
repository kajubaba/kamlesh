package com.narendra.sams.admission.domain;

public class BusStopAdmissionCount {
    private Long admissions;
    private String busStop;
    private Long busStopId;
    private String studentStatusId;
    
    public BusStopAdmissionCount(){
    	
    }

    public BusStopAdmissionCount(Long busStopId, String busStop, Long admissions) {
        this.busStopId = busStopId;
        this.busStop = busStop;
        this.admissions = admissions;
    }

    public Long getBusStopId() {
        return this.busStopId;
    }

    public void setBusStopId(Long busStopId) {
        this.busStopId = busStopId;
    }

    public String getBusStop() {
        return this.busStop;
    }

    public void setBusStop(String busStop) {
        this.busStop = busStop;
    }

    public Long getAdmissions() {
        return this.admissions;
    }

    public void setAdmissions(Long admissions) {
        this.admissions = admissions;
    }

    public String getStudentStatusId() {
        return this.studentStatusId;
    }

    public void setStudentStatusId(String studentStatusId) {
        this.studentStatusId = studentStatusId;
    }
}
