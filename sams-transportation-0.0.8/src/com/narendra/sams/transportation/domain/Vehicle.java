package com.narendra.sams.transportation.domain;

import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.domain.UserView;
import java.util.Date;
import java.util.Set;

public class Vehicle {
    private Set<AcademicYearVehicle> academicYearVehicles;
    private String chassisNo;
    private UserView createdBy;
    private Date createdDateTime;
    private String engineNo;
    private Date fitnessDueDate;
    private Long id;
    private Institute institute;
    private Date insuranceDueDate;
    private UserView lastUpdatedBy;
    private Date lastUpdatedDateTime;
    private String manufacturer;
    private String manufacturingYear;
    private Date permitDueDate;
    private Date pucDueDate;
    private Date registrationDate;
    private Date roadTaxDueDate;
    private Date transferDate;
    private String vehicleColor;
    private String vehicleId;
    private String vehicleName;
    private Integer vehicleSeatCapacity;
    private String vehicleType;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleId() {
        return this.vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return this.vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleName() {
        return this.vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getManufacturingYear() {
        return this.manufacturingYear;
    }

    public void setManufacturingYear(String manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVehicleColor() {
        return this.vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public Integer getVehicleSeatCapacity() {
        return this.vehicleSeatCapacity;
    }

    public void setVehicleSeatCapacity(Integer vehicleSeatCapacity) {
        this.vehicleSeatCapacity = vehicleSeatCapacity;
    }

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public Set<AcademicYearVehicle> getAcademicYearVehicles() {
        return this.academicYearVehicles;
    }

    public void setAcademicYearVehicles(Set<AcademicYearVehicle> academicYearVehicles) {
        this.academicYearVehicles = academicYearVehicles;
    }

    public UserView getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(UserView createdBy) {
        this.createdBy = createdBy;
    }

    public UserView getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public void setLastUpdatedBy(UserView lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getCreatedDateTime() {
        return this.createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Date getLastUpdatedDateTime() {
        return this.lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(Date lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    public String getChassisNo() {
        return this.chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getEngineNo() {
        return this.engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getTransferDate() {
        return this.transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public Date getPucDueDate() {
        return this.pucDueDate;
    }

    public void setPucDueDate(Date pucDueDate) {
        this.pucDueDate = pucDueDate;
    }

    public Date getFitnessDueDate() {
        return this.fitnessDueDate;
    }

    public void setFitnessDueDate(Date fitnessDueDate) {
        this.fitnessDueDate = fitnessDueDate;
    }

    public Date getInsuranceDueDate() {
        return this.insuranceDueDate;
    }

    public void setInsuranceDueDate(Date insuranceDueDate) {
        this.insuranceDueDate = insuranceDueDate;
    }

    public Date getRoadTaxDueDate() {
        return this.roadTaxDueDate;
    }

    public void setRoadTaxDueDate(Date roadTaxDueDate) {
        this.roadTaxDueDate = roadTaxDueDate;
    }

    public Date getPermitDueDate() {
        return this.permitDueDate;
    }

    public void setPermitDueDate(Date permitDueDate) {
        this.permitDueDate = permitDueDate;
    }
}
