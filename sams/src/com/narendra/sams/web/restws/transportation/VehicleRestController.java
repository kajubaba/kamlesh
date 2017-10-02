package com.narendra.sams.web.restws.transportation;

import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.transportation.domain.Vehicle;
import com.narendra.sams.transportation.service.AcademicYearVehicleService;
import com.narendra.sams.transportation.service.VehicleService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.transportation.vo.VehicleVO;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/transportation/vehicle"})
public class VehicleRestController {
    @Autowired
    private AcademicYearVehicleService academicYearVehicleService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/allvehicles"})
    public List<VehicleVO> allVehicles() {
        return prepareVehicleListToDisplay(this.vehicleService.getAllVehicles(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/add"})
    public AjaxResponse addVehicle(@RequestBody VehicleVO vehicleVO) {
        Vehicle vehicle = mapToDomain(vehicleVO);
        Institute institute = new Institute();
        institute.setId(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId());
        vehicle.setInstitute(institute);
        Long vehicleId = this.vehicleService.addVehicle(vehicle, LoggedinUserAssistant.getLoggedInUserId());
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setGeneratedId(vehicleId);
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update"})
    public AjaxResponse updateVehicle(@RequestBody VehicleVO vehicleVO) {
        this.vehicleService.updateVehicle(mapToDomain(vehicleVO), LoggedinUserAssistant.getLoggedInUserId());
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setGeneratedId(vehicleVO.getId());
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{vehicleId}"})
    public VehicleVO getVehicle(@PathVariable Long vehicleId) {
        return mapToVO(this.vehicleService.getVehicle(vehicleId));
    }

    private Vehicle mapToDomain(VehicleVO vehicleVO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleVO.getId());
        vehicle.setManufacturer(vehicleVO.getManufacturer());
        vehicle.setManufacturingYear(vehicleVO.getManufacturingYear());
        vehicle.setVehicleColor(vehicleVO.getVehicleColor());
        vehicle.setVehicleName(vehicleVO.getVehicleName());
        vehicle.setVehicleSeatCapacity(vehicleVO.getVehicleSeatCapacity());
        vehicle.setVehicleType(vehicleVO.getVehicleType());
        vehicle.setVehicleId(vehicleVO.getVehicleId());
        vehicle.setChassisNo(vehicleVO.getChassisNo());
        vehicle.setEngineNo(vehicleVO.getEngineNo());
        if (!(vehicleVO.getPucDueDate() == null || vehicleVO.getPucDueDate().isEmpty())) {
            try {
                vehicle.setPucDueDate(DateUtil.parseDate(vehicleVO.getPucDueDate(), "dd-MMM-yyyy"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!(vehicleVO.getRegistrationDate() == null || vehicleVO.getRegistrationDate().isEmpty())) {
            try {
                vehicle.setRegistrationDate(DateUtil.parseDate(vehicleVO.getRegistrationDate(), "dd-MMM-yyyy"));
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
        }
        if (!(vehicleVO.getTransferDate() == null || vehicleVO.getTransferDate().isEmpty())) {
            try {
                vehicle.setTransferDate(DateUtil.parseDate(vehicleVO.getTransferDate(), "dd-MMM-yyyy"));
            } catch (ParseException e22) {
                e22.printStackTrace();
            }
        }
        if (!(vehicleVO.getRoadTaxDueDate() == null || vehicleVO.getRoadTaxDueDate().isEmpty())) {
            try {
                vehicle.setRoadTaxDueDate(DateUtil.parseDate(vehicleVO.getRoadTaxDueDate(), "dd-MMM-yyyy"));
            } catch (ParseException e222) {
                e222.printStackTrace();
            }
        }
        if (!(vehicleVO.getPermitDueDate() == null || vehicleVO.getPermitDueDate().isEmpty())) {
            try {
                vehicle.setPermitDueDate(DateUtil.parseDate(vehicleVO.getPermitDueDate(), "dd-MMM-yyyy"));
            } catch (ParseException e2222) {
                e2222.printStackTrace();
            }
        }
        if (!(vehicleVO.getInsuranceDueDate() == null || vehicleVO.getInsuranceDueDate().isEmpty())) {
            try {
                vehicle.setInsuranceDueDate(DateUtil.parseDate(vehicleVO.getInsuranceDueDate(), "dd-MMM-yyyy"));
            } catch (ParseException e22222) {
                e22222.printStackTrace();
            }
        }
        if (!(vehicleVO.getFitnessDueDate() == null || vehicleVO.getFitnessDueDate().isEmpty())) {
            try {
                vehicle.setFitnessDueDate(DateUtil.parseDate(vehicleVO.getFitnessDueDate(), "dd-MMM-yyyy"));
            } catch (ParseException e222222) {
                e222222.printStackTrace();
            }
        }
        return vehicle;
    }

    private List<VehicleVO> prepareVehicleListToDisplay(List<Vehicle> vehicles) {
        List<VehicleVO> vehicleVOs = new ArrayList();
        if (vehicles != null) {
            for (Vehicle vehicle : vehicles) {
                vehicleVOs.add(mapToVO(vehicle));
            }
        }
        return vehicleVOs;
    }

    private VehicleVO mapToVO(Vehicle vehicle) {
        VehicleVO vehicleVO = new VehicleVO();
        vehicleVO.setId(vehicle.getId());
        vehicleVO.setVehicleId(vehicle.getVehicleId());
        vehicleVO.setVehicleType(vehicle.getVehicleType());
        vehicleVO.setVehicleName(vehicle.getVehicleName());
        vehicleVO.setManufacturingYear(vehicle.getManufacturingYear());
        vehicleVO.setManufacturer(vehicle.getManufacturer());
        vehicleVO.setVehicleColor(vehicle.getVehicleColor());
        vehicleVO.setVehicleSeatCapacity(vehicle.getVehicleSeatCapacity());
        vehicleVO.setChassisNo(vehicle.getChassisNo());
        vehicleVO.setEngineNo(vehicle.getEngineNo());
        if (vehicle.getPucDueDate() != null) {
            vehicleVO.setPucDueDate(DateUtil.formatDate(vehicle.getPucDueDate(), "dd-MMM-yyyy"));
        }
        if (vehicle.getFitnessDueDate() != null) {
            vehicleVO.setFitnessDueDate(DateUtil.formatDate(vehicle.getFitnessDueDate(), "dd-MMM-yyyy"));
        }
        if (vehicle.getRoadTaxDueDate() != null) {
            vehicleVO.setRoadTaxDueDate(DateUtil.formatDate(vehicle.getRoadTaxDueDate(), "dd-MMM-yyyy"));
        }
        if (vehicle.getPermitDueDate() != null) {
            vehicleVO.setPermitDueDate(DateUtil.formatDate(vehicle.getPermitDueDate(), "dd-MMM-yyyy"));
        }
        if (vehicle.getInsuranceDueDate() != null) {
            vehicleVO.setInsuranceDueDate(DateUtil.formatDate(vehicle.getInsuranceDueDate(), "dd-MMM-yyyy"));
        }
        if (vehicle.getRegistrationDate() != null) {
            vehicleVO.setRegistrationDate(DateUtil.formatDate(vehicle.getRegistrationDate(), "dd-MMM-yyyy"));
        }
        if (vehicle.getTransferDate() != null) {
            vehicleVO.setTransferDate(DateUtil.formatDate(vehicle.getTransferDate(), "dd-MMM-yyyy"));
        }
        return vehicleVO;
    }
}
