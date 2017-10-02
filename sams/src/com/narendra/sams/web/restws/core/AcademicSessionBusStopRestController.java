package com.narendra.sams.web.restws.core;

import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.service.BusAdoptedAdmissionListService;
import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.service.AcademicYearBusFeeService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.form.AcademicSesionBusStopFeeForm;
import com.narendra.sams.web.restws.form.AcademicSessionBusStopForm;
import com.narendra.sams.web.restws.mapper.form.BusStopFormMapper;
import com.narendra.sams.web.restws.mapper.vo.AcademicSessionBusStopVOMapper;
import com.narendra.sams.web.restws.mapper.vo.BusStopVOMapper;
import com.narendra.sams.web.restws.vo.AcademicSessionBusStopVO;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.restws.vo.BusStopVO;
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
@RequestMapping({"/ws/academic-session/bus-stop"})
public class AcademicSessionBusStopRestController {
    @Autowired
    private AcademicYearBusFeeService academicYearBusFeeService;
    @Autowired
    private BusAdoptedAdmissionListService busAdoptedAdmissionListService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list/{academicSessionId}"})
    public List<AcademicSessionBusStopVO> getBusStops(@PathVariable Long academicSessionId) {
        return AcademicSessionBusStopVOMapper.prepareAcademicSessionBusStopVOs(this.academicYearBusFeeService.getAssigedBusFee(academicSessionId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list/unassigned/{academicSessionId}"})
    public List<BusStopVO> getUnAssignedBusStops(@PathVariable Long academicSessionId) {
        return BusStopVOMapper.prepareBusStopVOs(this.academicYearBusFeeService.getUnAssignedBusStops(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId(), academicSessionId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save-bus-fee"})
    public AjaxResponse saveAcademicSessionBusStopFee(@RequestBody AcademicSesionBusStopFeeForm academicSesionBusStopFeeForm) {
        this.academicYearBusFeeService.updateFeeChanges(BusStopFormMapper.prepareBusStopsFeeDomain(academicSesionBusStopFeeForm.getAcademicSessionBusFees()));
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/assign"})
    public AjaxResponse assignBusStops(@RequestBody AcademicSessionBusStopForm academicSessionBusStopForm) {
        this.academicYearBusFeeService.addBusStops(academicSessionBusStopForm.getAcademicSessionId(), academicSessionBusStopForm.getBusStopIds());
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/delete/{busFeeId}"})
    public AjaxResponse unAssignBusStop(@PathVariable Long busFeeId) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        BusFee busFee = this.academicYearBusFeeService.getBusFee(busFeeId);
        if (busFeeId != null) {
            List<ClassHistory> classHistories = this.busAdoptedAdmissionListService.getBusFacilityAdoptedAdmissionsByBusStop(busFee.getAcademicYear().getId(), null, busFee.getBusStop().getId(), null);
            if (!(classHistories == null || classHistories.isEmpty())) {
                ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
                return ajaxResponse;
            }
        }
        this.academicYearBusFeeService.deleteBusStop(busFeeId);
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }
}
