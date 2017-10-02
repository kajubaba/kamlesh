package com.narendra.sams.transportation.service.impl;

import com.narendra.sams.transportation.dao.AcademicYearVehicleDAO;
import com.narendra.sams.transportation.dao.VehicleDAO;
import com.narendra.sams.transportation.domain.AcademicYearVehicle;
import com.narendra.sams.transportation.domain.Vehicle;
import com.narendra.sams.transportation.service.AcademicYearVehicleService;
import java.util.ArrayList;
import java.util.List;

public class AcademicYearVehicleServiceImpl implements AcademicYearVehicleService {
    private AcademicYearVehicleDAO academicYearVehicleDAO;
    private VehicleDAO vehicleDAO;

    public AcademicYearVehicleDAO getAcademicYearVehicleDAO() {
        return this.academicYearVehicleDAO;
    }

    public void setAcademicYearVehicleDAO(AcademicYearVehicleDAO academicYearVehicleDAO) {
        this.academicYearVehicleDAO = academicYearVehicleDAO;
    }

    public VehicleDAO getVehicleDAO() {
        return this.vehicleDAO;
    }

    public void setVehicleDAO(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    public List<Vehicle> getActiveVehiclesInAcademicYear(Long academicYearId) {
        return this.academicYearVehicleDAO.getActiveVehiclesInAcademicYear(academicYearId);
    }

    public Long getActiveBusesCountInAcademicYear(Long academicYearId) {
        return this.academicYearVehicleDAO.getActiveBusesCountInAcademicYear(academicYearId);
    }

    public List<Vehicle> getVehicleToBeAssgnedInAcademicYear(Long academicYearId, Long instituteId) {
        List<Vehicle> masterVehicles = this.vehicleDAO.getAllVehicles(instituteId);
        if (masterVehicles == null) {
            return null;
        }
        List<Vehicle> academicYearVehicles = this.academicYearVehicleDAO.getActiveVehiclesInAcademicYear(academicYearId);
        if (academicYearVehicles == null) {
            return masterVehicles;
        }
        List<Vehicle> toBeAssigned = new ArrayList();
        for (Vehicle masterVehicle : masterVehicles) {
            Boolean alreadAdded = Boolean.FALSE;
            for (Vehicle academicYearVehicle : academicYearVehicles) {
                if (masterVehicle.getId().equals(academicYearVehicle.getId())) {
                    alreadAdded = Boolean.TRUE;
                    break;
                }
            }
            if (!alreadAdded.booleanValue()) {
                toBeAssigned.add(masterVehicle);
            }
        }
        return toBeAssigned;
    }

    public void addVehiclesInAcademicYear(List<Integer> vehicleIds, Long academicYearId) {
        this.academicYearVehicleDAO.addVehiclesInAcademicYear(vehicleIds, academicYearId);
    }

    public AcademicYearVehicle getAcademicYearVehicle(Long academicSessionBusId) {
        return this.academicYearVehicleDAO.getAcademicYearVehicle(academicSessionBusId);
    }

    public List<AcademicYearVehicle> getAcademicYearVehicles(Long academicYearId) {
        return this.academicYearVehicleDAO.getAcademicYearVehicles(academicYearId);
    }

    public Long getTotalSeatCapacityOfVehicles(Long academicSessionId) {
        return this.academicYearVehicleDAO.getTotalSeatCapacityOfVehicles(academicSessionId);
    }
}
