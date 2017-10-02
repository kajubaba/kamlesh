package com.narendra.sams.web.restws.transportation.vo;

public class VehicleOnRouteVO {
    private Long vehicleId;
    private Integer vehicleSeatCapacity;
    private String vehilceName;
    private String vehilceNo;

    public Long getVehicleId() {
        return this.vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehilceNo() {
        return this.vehilceNo;
    }

    public void setVehilceNo(String vehilceNo) {
        this.vehilceNo = vehilceNo;
    }

    public String getVehilceName() {
        return this.vehilceName;
    }

    public void setVehilceName(String vehilceName) {
        this.vehilceName = vehilceName;
    }

    public Integer getVehicleSeatCapacity() {
        return this.vehicleSeatCapacity;
    }

    public void setVehicleSeatCapacity(Integer vehicleSeatCapacity) {
        this.vehicleSeatCapacity = vehicleSeatCapacity;
    }
}
