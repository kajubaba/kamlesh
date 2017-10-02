package com.narendra.sams.transportation.domain;

public class VehicleRoute {
    private AcademicYearVehicle academicYearVehicle;
    private Long id;
    private Route route;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcademicYearVehicle getAcademicYearVehicle() {
        return this.academicYearVehicle;
    }

    public void setAcademicYearVehicle(AcademicYearVehicle academicYearVehicle) {
        this.academicYearVehicle = academicYearVehicle;
    }

    public Route getRoute() {
        return this.route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
