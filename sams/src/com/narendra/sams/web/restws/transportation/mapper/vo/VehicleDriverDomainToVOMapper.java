package com.narendra.sams.web.restws.transportation.mapper.vo;

import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.transportation.domain.VehicleDriver;
import com.narendra.sams.web.restws.transportation.vo.VehicleDriverVO;
import java.util.ArrayList;
import java.util.List;

public class VehicleDriverDomainToVOMapper {
    public static VehicleDriverVO mapToVO(VehicleDriver vehicleDriver) {
        if (vehicleDriver == null) {
            return null;
        }
        VehicleDriverVO vehicleDriverVO = new VehicleDriverVO();
        vehicleDriverVO.setId(vehicleDriver.getId());
        vehicleDriverVO.setName(vehicleDriver.getName());
        vehicleDriverVO.setPrimaryContact(vehicleDriver.getPrimaryContact());
        vehicleDriverVO.setSecondaryContact(vehicleDriver.getSecondaryContact());
        vehicleDriverVO.setAddress(vehicleDriver.getAddress());
        vehicleDriverVO.setRole(vehicleDriver.getRole());
        vehicleDriverVO.setLicenseNo(vehicleDriver.getLicenseNo());
        vehicleDriverVO.setCreatedBy(vehicleDriver.getCreatedBy().getFullName());
        vehicleDriverVO.setLastUpdatedBy(vehicleDriver.getLastUpdatedBy().getFullName());
        vehicleDriverVO.setCreatedDateTime(DateUtil.formatDate(vehicleDriver.getCreatedDateTime(), "dd-MMM-yyyy hh:mm a"));
        vehicleDriverVO.setLastUpdatedDateTime(DateUtil.formatDate(vehicleDriver.getLastUpdatedDateTime(), "dd-MMM-yyyy hh:mm a"));
        return vehicleDriverVO;
    }

    public static List<VehicleDriverVO> mapToVOs(List<VehicleDriver> vehicleDrivers) {
        if (vehicleDrivers == null) {
            return null;
        }
        List<VehicleDriverVO> vehicleDriverVOs = new ArrayList();
        for (VehicleDriver vehicleDriver : vehicleDrivers) {
            vehicleDriverVOs.add(mapToVO(vehicleDriver));
        }
        return vehicleDriverVOs;
    }
}
