package com.narendra.sams.transportation.dao;

import com.narendra.sams.transportation.domain.AcademicYearVehicle;
import com.narendra.sams.transportation.domain.Vehicle;
import java.util.List;

public interface AcademicYearVehicleDAO {
    void addVehiclesInAcademicYear(List<Integer> list, Long l);

    AcademicYearVehicle getAcademicYearVehicle(Long l);

    List<AcademicYearVehicle> getAcademicYearVehicles(Long l);

    Long getActiveBusesCountInAcademicYear(Long l);

    List<Vehicle> getActiveVehiclesInAcademicYear(Long l);

    Long getTotalSeatCapacityOfVehicles(Long l);
}
