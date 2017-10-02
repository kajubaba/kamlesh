package com.narendra.sams.transportation.service.impl;

import com.narendra.sams.transportation.dao.VehicleDAO;
import com.narendra.sams.transportation.domain.Vehicle;
import com.narendra.sams.transportation.service.VehicleService;
import java.util.List;

public class VehicleServiceImpl implements VehicleService {
    private VehicleDAO vehicleDAO;

    public VehicleDAO getVehicleDAO() {
        return this.vehicleDAO;
    }

    public void setVehicleDAO(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    public List<Vehicle> getAllVehicles(Long instituteId) {
        return this.vehicleDAO.getAllVehicles(instituteId);
    }

    public Long addVehicle(Vehicle vehicle, Long userId) {
        return this.vehicleDAO.addVehicle(vehicle, userId);
    }

    public Vehicle getVehicle(Long vehicleId) {
        return this.vehicleDAO.getVehicle(vehicleId);
    }

    public void updateVehicle(Vehicle vehicle, Long userId) {
        this.vehicleDAO.updateVehicle(vehicle, userId);
    }
}
