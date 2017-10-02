package com.narendra.sams.web.restws.transportation;

import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.service.RouteService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.restws.transportation.form.RouteForm;
import com.narendra.sams.web.restws.transportation.mapper.vo.RouteMapper;
import com.narendra.sams.web.restws.transportation.vo.CountVO;
import com.narendra.sams.web.restws.transportation.vo.RouteVO;
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
@RequestMapping({"/ws/transportation/route"})
public class RouteRestController {
    @Autowired
    private RouteService routeService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/add"})
    public AjaxResponse addRoute(@RequestBody RouteVO routeVO) {
        Route route = new Route();
        route.setName(routeVO.getName());
        route.setFrom(routeVO.getFrom());
        route.setTo(routeVO.getTo());
        route.setType(routeVO.getType());
        route.setPlannedStudents(routeVO.getPlannedStudents());
        route.setAcademicYear(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission());
        Long routeId = this.routeService.addRoute(route, LoggedinUserAssistant.getLoggedInUserId());
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setGeneratedId(routeId);
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/copy"})
    public AjaxSuccessResponse flipRoute(@RequestBody RouteForm routeForm) {
        Route route = new Route();
        route.setId(routeForm.getId());
        route.setName(routeForm.getName());
        route.setFrom(routeForm.getFrom());
        route.setTo(routeForm.getTo());
        route.setType(routeForm.getType());
        route.setPlannedStudents(routeForm.getPlannedStudents());
        Long routeId = this.routeService.createRouteFromRoute(route, routeForm.getForceCopy(), LoggedinUserAssistant.getLoggedInUserId());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setGeneratedId(routeId);
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{routeId}"})
    public RouteVO getRoute(@PathVariable Long routeId) {
        return RouteMapper.mapToVO(this.routeService.getRoute(routeId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update"})
    public AjaxResponse updateRoute(@RequestBody RouteVO routeVO) {
        Route route = new Route();
        route.setId(routeVO.getId());
        route.setName(routeVO.getName());
        route.setFrom(routeVO.getFrom());
        route.setTo(routeVO.getTo());
        route.setType(routeVO.getType());
        route.setPlannedStudents(routeVO.getPlannedStudents());
        this.routeService.updateRoute(route, LoggedinUserAssistant.getLoggedInUserId());
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setGeneratedId(routeVO.getId());
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/allroutes"})
    public List<RouteVO> allRoutes() {
        return RouteMapper.mapToVOs(this.routeService.getAllRoutes(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/count"})
    public CountVO routeCount() {
        Long count = this.routeService.getRouteCount(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId());
        CountVO countVO = new CountVO();
        countVO.setCountOf("route");
        countVO.setCount(count);
        return countVO;
    }
}
