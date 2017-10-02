package com.narendra.sams.web.restws.transportation.form;

public class UpdateStudentPickupDropPoint {
    private Long academicYearBusStopId;
    private Long pointId;
    private Long studentId;

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getAcademicYearBusStopId() {
        return this.academicYearBusStopId;
    }

    public void setAcademicYearBusStopId(Long academicYearBusStopId) {
        this.academicYearBusStopId = academicYearBusStopId;
    }

    public Long getPointId() {
        return this.pointId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }
}
