package com.narendra.sams.transportation.service.impl;

import com.narendra.sams.core.exception.CanNotDeleteException;
import com.narendra.sams.transportation.dao.VehicleDriverDAO;
import com.narendra.sams.transportation.domain.VehicleDriver;
import com.narendra.sams.transportation.service.VehicleDriverService;
import java.util.List;

public class VehicleDriverServiceImpl implements VehicleDriverService {
    private VehicleDriverDAO vehicleDriverDAO;

    public VehicleDriverDAO getVehicleDriverDAO() {
        return this.vehicleDriverDAO;
    }

    public void setVehicleDriverDAO(VehicleDriverDAO vehicleDriverDAO) {
        this.vehicleDriverDAO = vehicleDriverDAO;
    }

    public VehicleDriver getVehicleDriver(Long driverId, Long instituteId) {
        if (driverId == null || instituteId == null) {
            return null;
        }
        return this.vehicleDriverDAO.getVehicleDriver(driverId, instituteId);
    }

    public void deleteVehicleDriver(Long driverId, Long instituteId) throws CanNotDeleteException {
        if (driverId != null && instituteId != null) {
            this.vehicleDriverDAO.deleteVehicleDriver(driverId, instituteId);
        }
    }

    public List<VehicleDriver> getVehicleDrivers(Long instituteId) {
        if (instituteId == null) {
            return null;
        }
        return this.vehicleDriverDAO.getVehicleDrivers(instituteId);
    }

    public Long saveVehicleDriver(VehicleDriver vehicleDriver, Long userId) {
        if (vehicleDriver.getId() == null) {
            return this.vehicleDriverDAO.addVehicleDriver(vehicleDriver, userId);
        }
        this.vehicleDriverDAO.updateVehicleDriver(vehicleDriver, userId);
        return vehicleDriver.getId();
    }
}
