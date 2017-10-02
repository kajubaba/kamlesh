package com.narendra.sams.web.restws.transportation.mapper.vo;

import com.narendra.sams.transportation.domain.Vehicle;
import com.narendra.sams.web.restws.transportation.vo.VehicleVO;

public class VehicleVOMapper {
    public static VehicleVO prepareVehicleVO(Vehicle vehicle) {
        VehicleVO vehicleVO = new VehicleVO();
        if (vehicle == null) {
            return null;
        }
        vehicleVO.setId(vehicle.getId());
        vehicleVO.setManufacturer(vehicle.getManufacturer());
        vehicleVO.setManufacturingYear(vehicle.getManufacturingYear());
        vehicleVO.setVehicleColor(vehicle.getVehicleColor());
        vehicleVO.setVehicleName(vehicle.getVehicleName());
        vehicleVO.setVehicleSeatCapacity(vehicle.getVehicleSeatCapacity());
        vehicleVO.setVehicleType(vehicle.getVehicleType());
        vehicleVO.setVehicleId(vehicle.getVehicleId());
        return vehicleVO;
    }
}
