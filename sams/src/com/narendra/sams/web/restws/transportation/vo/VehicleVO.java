package com.narendra.sams.web.restws.transportation.vo;

public class VehicleVO {
    private String chassisNo;
    private String engineNo;
    private String fitnessDueDate;
    private Long id;
    private String insuranceDueDate;
    private String manufacturer;
    private String manufacturingYear;
    private String permitDueDate;
    private String pucDueDate;
    private String registrationDate;
    private String roadTaxDueDate;
    private String transferDate;
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

    public String getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getTransferDate() {
        return this.transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public String getPucDueDate() {
        return this.pucDueDate;
    }

    public void setPucDueDate(String pucDueDate) {
        this.pucDueDate = pucDueDate;
    }

    public String getFitnessDueDate() {
        return this.fitnessDueDate;
    }

    public void setFitnessDueDate(String fitnessDueDate) {
        this.fitnessDueDate = fitnessDueDate;
    }

    public String getInsuranceDueDate() {
        return this.insuranceDueDate;
    }

    public void setInsuranceDueDate(String insuranceDueDate) {
        this.insuranceDueDate = insuranceDueDate;
    }

    public String getRoadTaxDueDate() {
        return this.roadTaxDueDate;
    }

    public void setRoadTaxDueDate(String roadTaxDueDate) {
        this.roadTaxDueDate = roadTaxDueDate;
    }

    public String getPermitDueDate() {
        return this.permitDueDate;
    }

    public void setPermitDueDate(String permitDueDate) {
        this.permitDueDate = permitDueDate;
    }
}
