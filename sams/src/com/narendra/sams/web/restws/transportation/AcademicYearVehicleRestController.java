package com.narendra.sams.web.restws.transportation;

import com.narendra.sams.transportation.domain.AcademicYearVehicle;
import com.narendra.sams.transportation.domain.Vehicle;
import com.narendra.sams.transportation.service.AcademicYearVehicleService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.restws.transportation.mapper.vo.VehicleVOMapper;
import com.narendra.sams.web.restws.transportation.vo.CountVO;
import com.narendra.sams.web.restws.transportation.vo.VehicleVO;
import com.narendra.sams.web.utils.AJAXResponseStatus;
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
@RequestMapping({"/ws/transportation/academicyearvehicle"})
public class AcademicYearVehicleRestController {
    @Autowired
    private AcademicYearVehicleService academicYearVehicleService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/allvehicles"})
    public List<VehicleVO> allVehicles() {
        return prepareAcademicYearVehiclesListToDisplay(this.academicYearVehicleService.getAcademicYearVehicles(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/toBeAssigned"})
    public List<VehicleVO> toBeAssigned() {
        return prepareVehicleListToDisplay(this.academicYearVehicleService.getVehicleToBeAssgnedInAcademicYear(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{academicSessionVehicleId}"})
    public VehicleVO getAcademicSessionrVehicle(@PathVariable Long academicSessionVehicleId) {
        return VehicleVOMapper.prepareVehicleVO(this.academicYearVehicleService.getAcademicYearVehicle(academicSessionVehicleId).getVehicle());
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/import"})
    public AjaxSuccessResponse importInAcademicYear(@RequestBody List<Integer> ids) {
        this.academicYearVehicleService.addVehiclesInAcademicYear(ids, UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AJAXResponseStatus.SUCCESS);
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/count"})
    public CountVO academicYearVehicleCount() {
        Long count = this.academicYearVehicleService.getActiveBusesCountInAcademicYear(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId());
        CountVO countVO = new CountVO();
        countVO.setCountOf("academicYearActiveBusCount");
        countVO.setCount(count);
        return countVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/seat-capacity"})
    public CountVO academemicSessionVehicleSeactCapacity() {
        Long count = this.academicYearVehicleService.getTotalSeatCapacityOfVehicles(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId());
        CountVO countVO = new CountVO();
        countVO.setCountOf("seatCapactyCount");
        countVO.setCount(count);
        return countVO;
    }

    private List<VehicleVO> prepareVehicleListToDisplay(List<Vehicle> vehicles) {
        List<VehicleVO> vehicleVOs = new ArrayList();
        if (vehicles != null) {
            for (Vehicle vehicle : vehicles) {
                VehicleVO vehicleVO = new VehicleVO();
                vehicleVO.setId(vehicle.getId());
                vehicleVO.setVehicleId(vehicle.getVehicleId());
                vehicleVO.setVehicleType(vehicle.getVehicleType());
                vehicleVO.setVehicleName(vehicle.getVehicleName());
                vehicleVO.setManufacturingYear(vehicle.getManufacturingYear());
                vehicleVO.setManufacturer(vehicle.getManufacturer());
                vehicleVO.setVehicleColor(vehicle.getVehicleColor());
                vehicleVO.setVehicleSeatCapacity(vehicle.getVehicleSeatCapacity());
                vehicleVOs.add(vehicleVO);
            }
        }
        return vehicleVOs;
    }

    private List<VehicleVO> prepareAcademicYearVehiclesListToDisplay(List<AcademicYearVehicle> vehicles) {
        List<VehicleVO> vehicleVOs = new ArrayList();
        if (vehicles != null) {
            for (AcademicYearVehicle vehicle : vehicles) {
                VehicleVO vehicleVO = new VehicleVO();
                vehicleVO.setId(vehicle.getId());
                vehicleVO.setVehicleId(vehicle.getVehicle().getVehicleId());
                vehicleVO.setVehicleType(vehicle.getVehicle().getVehicleType());
                vehicleVO.setVehicleName(vehicle.getVehicle().getVehicleName());
                vehicleVO.setManufacturingYear(vehicle.getVehicle().getManufacturingYear());
                vehicleVO.setManufacturer(vehicle.getVehicle().getManufacturer());
                vehicleVO.setVehicleColor(vehicle.getVehicle().getVehicleColor());
                vehicleVO.setVehicleSeatCapacity(vehicle.getVehicle().getVehicleSeatCapacity());
                vehicleVOs.add(vehicleVO);
            }
        }
        return vehicleVOs;
    }
}
