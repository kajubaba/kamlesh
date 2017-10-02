package com.narendra.sams.web.admin.controller;

import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.BusStopService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/admin/busstop"})
public class BusStopController {
    @Autowired
    private BusStopService busStopService;
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.GET}, value = {"/defaultList"})
    public String busStopView(Model model) {
        this.logger.info("Preparing bus stop default listing view");
        model.addAttribute("busStops", this.busStopService.getAllBusStops(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId()));
        this.logger.info("Returning bus stop default listing view");
        return "admin_bus_stop_list";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/new"})
    public String getNewFeeHeadView(Model model) {
        this.logger.info("Returning new bus stop form");
        return "admin_bus_stop_form";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/get/{busStopId}"})
    public String getRoleBusStopView(@PathVariable Long busStopId, Model model) {
        this.logger.info("Returning bus stop form to update role id :" + busStopId);
        model.addAttribute("busStop", this.busStopService.getBusStop(busStopId));
        model.addAttribute("action", "update");
        return "admin_bus_stop_form";
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/add"})
    public String addBusStop(BusStop busStop) {
        busStop.setInstitute(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute());
        Boolean duplicateExist = Boolean.FALSE;
        Long busStopId = null;
        try {
            busStopId = this.busStopService.saveBusStop(busStop, LoggedinUserAssistant.getLoggedInUserId());
        } catch (DuplicateNameFoundException e) {
            duplicateExist = Boolean.TRUE;
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        if (duplicateExist.booleanValue()) {
            jsonObject.put("status", AjaxStatus.DUPLICATE);
        } else {
            jsonObject.put("status", AjaxStatus.OK);
            jsonObject.put("id", busStopId);
        }
        return jsonObject.toString();
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update"})
    public String updateBusStop(BusStop busStop) {
        Boolean duplicateExist = Boolean.FALSE;
        JSONObject jsonObject = new JSONObject();
        if (duplicateExist.booleanValue()) {
            jsonObject.put("status", AjaxStatus.DUPLICATE);
        } else {
            jsonObject.put("status", AjaxStatus.OK);
        }
        return jsonObject.toString();
    }
}
