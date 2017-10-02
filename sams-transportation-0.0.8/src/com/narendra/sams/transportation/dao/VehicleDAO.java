package com.narendra.sams.transportation.dao;

import com.narendra.sams.transportation.domain.Vehicle;
import java.util.List;

public interface VehicleDAO {
    Long addVehicle(Vehicle vehicle, Long l);

    List<Vehicle> getAllVehicles(Long l);

    Vehicle getVehicle(Long l);

    void updateVehicle(Vehicle vehicle, Long l);
}
