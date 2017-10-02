package com.narendra.sams.transportation.domain;

import com.narendra.sams.core.domain.AcademicYear;

public class AcademicYearVehicle {
    private AcademicYear academicYear;
    private VehicleDriver conductor;
    private VehicleDriver driver;
    private Long id;
    private Vehicle vehicle;

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

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public VehicleDriver getDriver() {
        return this.driver;
    }

    public void setDriver(VehicleDriver driver) {
        this.driver = driver;
    }

    public VehicleDriver getConductor() {
        return this.conductor;
    }

    public void setConductor(VehicleDriver conductor) {
        this.conductor = conductor;
    }
}
