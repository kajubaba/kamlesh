package com.narendra.sams.transportation.dao;

import com.narendra.sams.core.exception.CanNotDeleteException;
import com.narendra.sams.transportation.domain.VehicleDriver;
import java.util.List;

public interface VehicleDriverDAO {
    Long addVehicleDriver(VehicleDriver vehicleDriver, Long l);

    void deleteVehicleDriver(Long l, Long l2) throws CanNotDeleteException;

    VehicleDriver getVehicleDriver(Long l, Long l2);

    List<VehicleDriver> getVehicleDrivers(Long l);

    void updateVehicleDriver(VehicleDriver vehicleDriver, Long l);
}
