package com.narendra.sams.web.restws.core;

import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.BusStopService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.form.BusStopForm;
import com.narendra.sams.web.restws.mapper.form.BusStopFormMapper;
import com.narendra.sams.web.restws.mapper.vo.BusStopVOMapper;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.restws.vo.BusStopVO;
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
@RequestMapping({"/ws/bus-stop"})
public class ManageBusStopRestController {
    @Autowired
    private BusStopService busStopService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxResponse saveBusStop(@RequestBody BusStopForm busStopForm) {
        BusStop busStop = BusStopFormMapper.prepareBusStopDomain(busStopForm);
        busStop.setInstitute(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute());
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            ajaxResponse.setGeneratedId(this.busStopService.saveBusStop(busStop, LoggedinUserAssistant.getLoggedInUserId()));
            ajaxResponse.setStatus(AjaxStatus.OK.toString());
        } catch (DuplicateNameFoundException e) {
            ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/delete/{busStopId}"})
    public void deleteBusStop() {
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{busStopId}"})
    public BusStopVO getBusStop(@PathVariable Long busStopId) {
        return BusStopVOMapper.prepareBusStopVO(this.busStopService.getBusStop(busStopId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<BusStopVO> listBusStops() {
        return BusStopVOMapper.prepareBusStopVOs(this.busStopService.getAllBusStops(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId()));
    }
}
