package com.narendra.sams.web.restws.transportation;

import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.transportation.domain.BusStopPoint;
import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.domain.StudentPickupDropPoint;
import com.narendra.sams.transportation.service.BusOnRouteService;
import com.narendra.sams.transportation.service.RouteStopService;
import com.narendra.sams.transportation.service.StudentPickupDropPointService;
import com.narendra.sams.web.restws.transportation.mapper.vo.StudentTransportationDetailVOMapper;
import com.narendra.sams.web.restws.transportation.vo.BusStopPointVOMapper;
import com.narendra.sams.web.restws.transportation.vo.StudentTransportationDetailsVO;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/transportation/student"})
public class StudentTransportationDetailRestController {
    @Autowired
    private BusOnRouteService busOnRouteService;
    @Autowired
    private RouteStopService routeStopService;
    @Autowired
    private StudentPickupDropPointService studentPickupDropPointService;
    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{studentId}"})
    public StudentTransportationDetailsVO getStudentDetails(@PathVariable Long studentId) {
        Student student = this.studentService.getStudentById(studentId);
        StudentTransportationDetailsVO studentTransportationDetailsVO = StudentTransportationDetailVOMapper.prepareStudentVO(student);
        BusStopPoint pickupPoint = null;
        BusStopPoint dropPoint = null;
        StudentPickupDropPoint studentPickupDropPoint = this.studentPickupDropPointService.getStudentPickupDropPoint(student.getId(), student.getAcademicYear().getId());
        if (studentPickupDropPoint != null) {
            pickupPoint = studentPickupDropPoint.getPickupPoint();
            dropPoint = studentPickupDropPoint.getDropPoint();
        }
        List<Route> routes = this.busOnRouteService.getStudentRoutes(student.getId(), student.getAcademicYear().getId());
        if (routes == null || routes.isEmpty()) {
            studentTransportationDetailsVO.setArrivalInfo(StudentTransportationDetailVOMapper.prepareStudentRouteDetailsVs(pickupPoint, null));
            studentTransportationDetailsVO.setDepartureInfo(StudentTransportationDetailVOMapper.prepareStudentRouteDetailsVs(dropPoint, null));
        } else {
            for (Route route : routes) {
                if (Route.ROUTE_TYPE_ARRIVAL.equals(route.getType())) {
                    studentTransportationDetailsVO.setArrivalInfo(StudentTransportationDetailVOMapper.prepareStudentRouteDetailsVs(pickupPoint, route));
                } else if (Route.ROUTE_TYPE_DEPARTUTE.equals(route.getType())) {
                    studentTransportationDetailsVO.setDepartureInfo(StudentTransportationDetailVOMapper.prepareStudentRouteDetailsVs(dropPoint, route));
                }
            }
        }
        List<BusStopPoint> pickupPoints = new ArrayList();
        List<BusStopPoint> dropPoints = new ArrayList();
        if (student.getBusStop() != null) {
            List<BusStopPoint> pickupDropPoints = this.routeStopService.getBusStopPickUpDropPoints(student.getAcademicYear().getId(), student.getBusStop().getId());
            if (pickupDropPoints != null) {
                for (BusStopPoint busStopPoint : pickupDropPoints) {
                    if (BusStopPoint.TYPE_PICKUP.equals(busStopPoint.getType())) {
                        pickupPoints.add(busStopPoint);
                    } else {
                        dropPoints.add(busStopPoint);
                    }
                }
            }
        }
        studentTransportationDetailsVO.setPickupPoints(BusStopPointVOMapper.prepareBusStopPointVOs(pickupPoints));
        studentTransportationDetailsVO.setDropPoints(BusStopPointVOMapper.prepareBusStopPointVOs(dropPoints));
        return studentTransportationDetailsVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/update/pickuppoint/{studentId}"})
    public AjaxResponse updatePickupPoint(@PathVariable Long studentId, Long pointId) {
        this.studentPickupDropPointService.updateStudentPickupPoint(studentId, this.studentService.getStudentById(studentId).getAcademicYear().getId(), pointId, LoggedinUserAssistant.getLoggedInUserId());
        return AjaxResponse.successResponse();
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/update/droppoint/{studentId}"})
    public AjaxResponse updateDropPoint(@PathVariable Long studentId, Long pointId) {
        this.studentPickupDropPointService.updateStudentDropPoint(studentId, this.studentService.getStudentById(studentId).getAcademicYear().getId(), pointId, LoggedinUserAssistant.getLoggedInUserId());
        return AjaxResponse.successResponse();
    }
}
