package com.narendra.sams.web.restws.transportation.helper;

import com.narendra.sams.transportation.domain.VehicleDriver;
import com.narendra.sams.web.restws.transportation.form.VehicleDriverForm;

public class VehicleDriverFormToDomainMapper {
    public static VehicleDriver mapToDomain(VehicleDriverForm vehicleDriverForm) {
        if (vehicleDriverForm == null) {
            return null;
        }
        VehicleDriver vehicleDriver = new VehicleDriver();
        vehicleDriver.setId(vehicleDriverForm.getId());
        vehicleDriver.setName(vehicleDriverForm.getName());
        vehicleDriver.setAddress(vehicleDriverForm.getAddress());
        vehicleDriver.setRole(vehicleDriverForm.getRole());
        vehicleDriver.setPrimaryContact(vehicleDriverForm.getPrimaryContact());
        vehicleDriver.setSecondaryContact(vehicleDriverForm.getSecondaryContact());
        vehicleDriver.setLicenseNo(vehicleDriverForm.getLicenseNo());
        return vehicleDriver;
    }
}
