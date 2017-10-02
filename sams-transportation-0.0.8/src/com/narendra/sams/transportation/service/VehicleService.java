package com.narendra.sams.transportation.service;

import com.narendra.sams.transportation.domain.Vehicle;
import java.util.List;

public interface VehicleService {
    Long addVehicle(Vehicle vehicle, Long l);

    List<Vehicle> getAllVehicles(Long l);

    Vehicle getVehicle(Long l);

    void updateVehicle(Vehicle vehicle, Long l);
}
