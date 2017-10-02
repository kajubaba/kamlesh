package com.narendra.sams.web.restws.transportation;

import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.transportation.domain.VehicleDriver;
import com.narendra.sams.transportation.service.VehicleDriverService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.transportation.form.VehicleDriverForm;
import com.narendra.sams.web.restws.transportation.helper.VehicleDriverFormToDomainMapper;
import com.narendra.sams.web.restws.transportation.mapper.vo.VehicleDriverDomainToVOMapper;
import com.narendra.sams.web.restws.transportation.vo.VehicleDriverVO;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
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
@RequestMapping({"/ws/vehicle/drivers"})
public class VehicleDriverRestController {
    @Autowired
    private VehicleDriverService vehicleDriverService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET})
    public List<VehicleDriverVO> getDrivers() {
        return VehicleDriverDomainToVOMapper.mapToVOs(this.vehicleDriverService.getVehicleDrivers(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{driverId}"})
    public VehicleDriverVO getDriver(@PathVariable Long driverId) {
        return VehicleDriverDomainToVOMapper.mapToVO(this.vehicleDriverService.getVehicleDriver(driverId, UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST})
    public AjaxResponse addDriver(@RequestBody VehicleDriverForm vehicleDriverForm) {
        Long instituteId = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId();
        VehicleDriver vehicleDriver = VehicleDriverFormToDomainMapper.mapToDomain(vehicleDriverForm);
        Institute institute = new Institute();
        institute.setId(instituteId);
        vehicleDriver.setInstitute(institute);
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setGeneratedId(this.vehicleDriverService.saveVehicleDriver(vehicleDriver, LoggedinUserAssistant.getLoggedInUserId()));
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.PUT})
    public AjaxResponse updateSDriver(@RequestBody VehicleDriverForm vehicleDriverForm) {
        Long instituteId = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId();
        VehicleDriver vehicleDriver = VehicleDriverFormToDomainMapper.mapToDomain(vehicleDriverForm);
        Institute institute = new Institute();
        institute.setId(instituteId);
        vehicleDriver.setInstitute(institute);
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setGeneratedId(this.vehicleDriverService.saveVehicleDriver(vehicleDriver, LoggedinUserAssistant.getLoggedInUserId()));
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }
}
