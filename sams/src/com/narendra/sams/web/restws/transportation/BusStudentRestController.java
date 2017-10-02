package com.narendra.sams.web.restws.transportation;

import com.narendra.sams.transportation.domain.AcademicYearVehicle;
import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.domain.RouteStudent;
import com.narendra.sams.transportation.service.AcademicYearVehicleService;
import com.narendra.sams.transportation.service.BusOnRouteService;
import com.narendra.sams.transportation.service.RouteService;
import com.narendra.sams.web.restws.transportation.mapper.vo.RouteMapper;
import com.narendra.sams.web.restws.transportation.mapper.vo.RouteStudentVOMapper;
import com.narendra.sams.web.restws.transportation.vo.VehicleRouteStudentVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/transportation/busstudent"})
public class BusStudentRestController {
    @Autowired
    private AcademicYearVehicleService academicYearVehicleService;
    @Autowired
    private BusOnRouteService busOnRouteService;
    @Autowired
    private RouteService routeService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/arrival/list/{academicSessionBusId}"})
    public VehicleRouteStudentVO getArrivalStudents(@PathVariable Long academicSessionBusId) {
        VehicleRouteStudentVO vehicleRouteStudentVO = new VehicleRouteStudentVO();
        AcademicYearVehicle academicYearVehicle = this.academicYearVehicleService.getAcademicYearVehicle(academicSessionBusId);
        List<Route> busRoutes = this.busOnRouteService.getBusRoutes(academicSessionBusId, Route.ROUTE_TYPE_ARRIVAL);
        vehicleRouteStudentVO.setRoutes(RouteMapper.mapToVOs(busRoutes));
        if (!(busRoutes == null || busRoutes.isEmpty())) {
            List<RouteStudent> routeStudents = this.busOnRouteService.getStudents(((Route) busRoutes.get(0)).getId());
            vehicleRouteStudentVO.setRouteId(((Route) busRoutes.get(0)).getId());
            vehicleRouteStudentVO.setRouteStudents(RouteStudentVOMapper.prepareRouteStudentVO(routeStudents, academicYearVehicle.getAcademicYear().getId()));
        }
        return vehicleRouteStudentVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/route/{routeId}"})
    public VehicleRouteStudentVO getRouteStudents(@PathVariable Long routeId) {
        Route route = this.routeService.getRoute(routeId);
        VehicleRouteStudentVO vehicleRouteStudentVO = new VehicleRouteStudentVO();
        AcademicYearVehicle academicYearVehicle = route.getAcademicYearVehicle();
        if (route.getAcademicYearVehicle() != null) {
            vehicleRouteStudentVO.setRoutes(RouteMapper.mapToVOs(this.busOnRouteService.getBusRoutes(route.getAcademicYearVehicle().getId(), route.getType())));
        }
        if (route != null) {
            List<RouteStudent> routeStudents = this.busOnRouteService.getStudents(route.getId());
            vehicleRouteStudentVO.setRouteId(route.getId());
            vehicleRouteStudentVO.setRouteStudents(RouteStudentVOMapper.prepareRouteStudentVO(routeStudents, academicYearVehicle.getAcademicYear().getId()));
        }
        return vehicleRouteStudentVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/departure/list/{academicSessionBusId}"})
    public VehicleRouteStudentVO getDepartureStudents(@PathVariable Long academicSessionBusId) {
        VehicleRouteStudentVO vehicleRouteStudentVO = new VehicleRouteStudentVO();
        AcademicYearVehicle academicYearVehicle = this.academicYearVehicleService.getAcademicYearVehicle(academicSessionBusId);
        List<Route> busRoutes = this.busOnRouteService.getBusRoutes(academicSessionBusId, Route.ROUTE_TYPE_DEPARTUTE);
        vehicleRouteStudentVO.setRoutes(RouteMapper.mapToVOs(busRoutes));
        if (!(busRoutes == null || busRoutes.isEmpty())) {
            List<RouteStudent> routeStudents = this.busOnRouteService.getStudents(((Route) busRoutes.get(0)).getId());
            vehicleRouteStudentVO.setRouteId(((Route) busRoutes.get(0)).getId());
            vehicleRouteStudentVO.setRouteStudents(RouteStudentVOMapper.prepareRouteStudentVO(routeStudents, academicYearVehicle.getAcademicYear().getId()));
        }
        return vehicleRouteStudentVO;
    }
}
