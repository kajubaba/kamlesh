package com.narendra.sams.transportation.service;

import com.narendra.sams.core.exception.CanNotDeleteException;
import com.narendra.sams.transportation.domain.VehicleDriver;
import java.util.List;

public interface VehicleDriverService {
    void deleteVehicleDriver(Long l, Long l2) throws CanNotDeleteException;

    VehicleDriver getVehicleDriver(Long l, Long l2);

    List<VehicleDriver> getVehicleDrivers(Long l);

    Long saveVehicleDriver(VehicleDriver vehicleDriver, Long l);
}
