package com.narendra.sams.web.restws.transportation;

import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.service.BusOnRouteService;
import com.narendra.sams.transportation.service.RouteService;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.transportation.vo.VehicleOnRouteVO;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/transportation/vehicleroute"})
public class VehicleRouteRestController {
    @Autowired
    private BusOnRouteService busOnRouteService;
    @Autowired
    private RouteService routeService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/vehicleonroute/{routeId}"})
    public VehicleOnRouteVO getVehicleOnRoute(@PathVariable Long routeId) {
        Route route = this.routeService.getRoute(routeId);
        VehicleOnRouteVO vehicleOnRouteVO = new VehicleOnRouteVO();
        if (route.getAcademicYearVehicle() != null) {
            vehicleOnRouteVO.setVehicleId(route.getAcademicYearVehicle().getId());
            vehicleOnRouteVO.setVehicleSeatCapacity(route.getAcademicYearVehicle().getVehicle().getVehicleSeatCapacity());
            vehicleOnRouteVO.setVehilceName(route.getAcademicYearVehicle().getVehicle().getVehicleName());
            vehicleOnRouteVO.setVehilceNo(route.getAcademicYearVehicle().getVehicle().getVehicleId());
        }
        return vehicleOnRouteVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/update"})
    public AjaxResponse updateVehicleOnRoute(Long routeId, Long academicSessionVehicleId) {
        this.busOnRouteService.updateVehicleOnRoute(routeId, academicSessionVehicleId);
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }
}
