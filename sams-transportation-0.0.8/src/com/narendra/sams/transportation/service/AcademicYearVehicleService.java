package com.narendra.sams.transportation.service;

import com.narendra.sams.transportation.domain.AcademicYearVehicle;
import com.narendra.sams.transportation.domain.Vehicle;
import java.util.List;

public interface AcademicYearVehicleService {
    void addVehiclesInAcademicYear(List<Integer> list, Long l);

    AcademicYearVehicle getAcademicYearVehicle(Long l);

    List<AcademicYearVehicle> getAcademicYearVehicles(Long l);

    Long getActiveBusesCountInAcademicYear(Long l);

    List<Vehicle> getActiveVehiclesInAcademicYear(Long l);

    Long getTotalSeatCapacityOfVehicles(Long l);

    List<Vehicle> getVehicleToBeAssgnedInAcademicYear(Long l, Long l2);
}
