package com.narendra.sams.web.restws.transportation.vo;

public class StudentRouteDetailsVO {
    private Long academicYearRouteId;
    private Long academicYearVehicleId;
    private String bus;
    private String busNo;
    private String driverContactNo;
    private String driverName;
    private String point;
    private Long pointId;
    private String routeName;
    private String stopTime;

    public String getBus() {
        return this.bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getBusNo() {
        return this.busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public String getRouteName() {
        return this.routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverContactNo() {
        return this.driverContactNo;
    }

    public void setDriverContactNo(String driverContactNo) {
        this.driverContactNo = driverContactNo;
    }

    public String getStopTime() {
        return this.stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getPoint() {
        return this.point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public Long getPointId() {
        return this.pointId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    public Long getAcademicYearVehicleId() {
        return this.academicYearVehicleId;
    }

    public void setAcademicYearVehicleId(Long academicYearVehicleId) {
        this.academicYearVehicleId = academicYearVehicleId;
    }

    public Long getAcademicYearRouteId() {
        return this.academicYearRouteId;
    }

    public void setAcademicYearRouteId(Long academicYearRouteId) {
        this.academicYearRouteId = academicYearRouteId;
    }
}
