package com.narendra.sams.web.restws.transportation;

import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.service.BusAdoptedAdmissionListService;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.domain.StudentPickupDropPoint;
import com.narendra.sams.transportation.service.BusOnRouteService;
import com.narendra.sams.transportation.service.StudentPickupDropPointService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.vo.StudentVO;
import com.narendra.sams.web.restws.transportation.mapper.vo.TransportationStudentListVOMaker;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/transportation/busadopted"})
public class BusAdoptedStudentRestController {
    @Autowired
    private BusAdoptedAdmissionListService busAdoptedAdmissionListService;
    @Autowired
    private BusOnRouteService busOnRouteService;
    @Autowired
    private StudentPickupDropPointService studentPickupDropPointService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/studentView/{studentStatus}"})
    public List<StudentVO> listStudentsStatuswise(@PathVariable Long studentStatus, Long ayid) {
        Long acadenicYearId;
        if (ayid == null) {
            acadenicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        } else {
            acadenicYearId = ayid;
        }
        List<ClassHistory> classHistories = this.busAdoptedAdmissionListService.getBusFacilityAdoptedAdmissions(acadenicYearId, studentStatus, null);
        Map<Long, StudentPickupDropPoint> pickupDropPointMap = new HashMap();
        Map<Long, List<Route>> studentVehicleRouteMap = new HashMap();
        if (!(classHistories == null || classHistories.isEmpty())) {
            for (ClassHistory classHistory : classHistories) {
                StudentPickupDropPoint studentPickupDropPoint = this.studentPickupDropPointService.getStudentPickupDropPoint(classHistory.getStudent().getId(), acadenicYearId);
                if (studentPickupDropPoint != null) {
                    pickupDropPointMap.put(classHistory.getStudent().getId(), studentPickupDropPoint);
                }
                List<Route> routes = this.busOnRouteService.getStudentRoutes(classHistory.getStudent().getId(), acadenicYearId);
                if (!(routes == null || routes.isEmpty())) {
                    studentVehicleRouteMap.put(classHistory.getStudent().getId(), routes);
                }
            }
        }
        return TransportationStudentListVOMaker.prepareStudentListToDisplay(classHistories, pickupDropPointMap, studentVehicleRouteMap);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/studentView/new"})
    public List<StudentVO> getNewAdmissions(Long ayid) {
        Long acadenicYearId;
        if (ayid == null) {
            acadenicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        } else {
            acadenicYearId = ayid;
        }
        List<ClassHistory> classHistories = this.busAdoptedAdmissionListService.getBusFacilityAdoptedAdmissions(acadenicYearId, StudentStatus.CONFIRMED, AdmissionType.NEW_ADMISSION_ID);
        Map<Long, StudentPickupDropPoint> pickupDropPointMap = new HashMap();
        Map<Long, List<Route>> studentVehicleRouteMap = new HashMap();
        if (!(classHistories == null || classHistories.isEmpty())) {
            for (ClassHistory classHistory : classHistories) {
                StudentPickupDropPoint studentPickupDropPoint = this.studentPickupDropPointService.getStudentPickupDropPoint(classHistory.getStudent().getId(), acadenicYearId);
                if (studentPickupDropPoint != null) {
                    pickupDropPointMap.put(classHistory.getStudent().getId(), studentPickupDropPoint);
                }
                List<Route> vehicleRoutes = this.busOnRouteService.getStudentRoutes(classHistory.getStudent().getId(), acadenicYearId);
                if (!(vehicleRoutes == null || vehicleRoutes.isEmpty())) {
                    studentVehicleRouteMap.put(classHistory.getStudent().getId(), vehicleRoutes);
                }
            }
        }
        return TransportationStudentListVOMaker.prepareStudentListToDisplay(classHistories, pickupDropPointMap, studentVehicleRouteMap);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classwise/{studentStatus}/{academicYearClassId}"})
    public List<StudentVO> listBusAdoptedStudentsByClass(@PathVariable Long studentStatus, @PathVariable Long academicYearClassId) {
        List<ClassHistory> classHistories = this.busAdoptedAdmissionListService.getBusFacilityAdoptedAdmissionsByClass(academicYearClassId, studentStatus, null);
        Long academicSessionId = null;
        Map<Long, StudentPickupDropPoint> pickupDropPointMap = new HashMap();
        Map<Long, List<Route>> studentVehicleRouteMap = new HashMap();
        if (!(classHistories == null || classHistories.isEmpty())) {
            for (ClassHistory classHistory : classHistories) {
                if (academicSessionId == null) {
                    academicSessionId = classHistory.getAcademicYear().getId();
                }
                StudentPickupDropPoint studentPickupDropPoint = this.studentPickupDropPointService.getStudentPickupDropPoint(classHistory.getStudent().getId(), academicSessionId);
                if (studentPickupDropPoint != null) {
                    pickupDropPointMap.put(classHistory.getStudent().getId(), studentPickupDropPoint);
                }
                List<Route> vehicleRoutes = this.busOnRouteService.getStudentRoutes(classHistory.getStudent().getId(), academicSessionId);
                if (!(vehicleRoutes == null || vehicleRoutes.isEmpty())) {
                    studentVehicleRouteMap.put(classHistory.getStudent().getId(), vehicleRoutes);
                }
            }
        }
        return TransportationStudentListVOMaker.prepareStudentListToDisplay(classHistories, pickupDropPointMap, studentVehicleRouteMap);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classwise/new/{academicYearClassId}"})
    public List<StudentVO> listBusAdoptedNewStudentsByClass(@PathVariable Long academicYearClassId) {
        List<ClassHistory> classHistories = this.busAdoptedAdmissionListService.getBusFacilityAdoptedAdmissionsByClass(academicYearClassId, StudentStatus.CONFIRMED, AdmissionType.NEW_ADMISSION_ID);
        Long academicSessionId = null;
        Map<Long, StudentPickupDropPoint> pickupDropPointMap = new HashMap();
        Map<Long, List<Route>> studentVehicleRouteMap = new HashMap();
        if (!(classHistories == null || classHistories.isEmpty())) {
            for (ClassHistory classHistory : classHistories) {
                if (academicSessionId == null) {
                    academicSessionId = classHistory.getAcademicYear().getId();
                }
                StudentPickupDropPoint studentPickupDropPoint = this.studentPickupDropPointService.getStudentPickupDropPoint(classHistory.getStudent().getId(), academicSessionId);
                if (studentPickupDropPoint != null) {
                    pickupDropPointMap.put(classHistory.getStudent().getId(), studentPickupDropPoint);
                }
                List<Route> vehicleRoutes = this.busOnRouteService.getStudentRoutes(classHistory.getStudent().getId(), academicSessionId);
                if (!(vehicleRoutes == null || vehicleRoutes.isEmpty())) {
                    studentVehicleRouteMap.put(classHistory.getStudent().getId(), vehicleRoutes);
                }
            }
        }
        return TransportationStudentListVOMaker.prepareStudentListToDisplay(classHistories, pickupDropPointMap, studentVehicleRouteMap);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/busstopwise/{studentStatus}/{busStopId}"})
    public List<StudentVO> listBusAdoptedStudentsByBusStop(@PathVariable Long studentStatus, @PathVariable Long busStopId, Long ayid) {
        Long acadenicYearId;
        if (ayid == null) {
            acadenicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        } else {
            acadenicYearId = ayid;
        }
        List<ClassHistory> classHistories = this.busAdoptedAdmissionListService.getBusFacilityAdoptedAdmissionsByBusStop(acadenicYearId, studentStatus, busStopId, null);
        Map<Long, StudentPickupDropPoint> pickupDropPointMap = new HashMap();
        Map<Long, List<Route>> studentVehicleRouteMap = new HashMap();
        if (!(classHistories == null || classHistories.isEmpty())) {
            for (ClassHistory classHistory : classHistories) {
                StudentPickupDropPoint studentPickupDropPoint = this.studentPickupDropPointService.getStudentPickupDropPoint(classHistory.getStudent().getId(), acadenicYearId);
                if (studentPickupDropPoint != null) {
                    pickupDropPointMap.put(classHistory.getStudent().getId(), studentPickupDropPoint);
                }
                List<Route> vehicleRoutes = this.busOnRouteService.getStudentRoutes(classHistory.getStudent().getId(), acadenicYearId);
                if (!(vehicleRoutes == null || vehicleRoutes.isEmpty())) {
                    studentVehicleRouteMap.put(classHistory.getStudent().getId(), vehicleRoutes);
                }
            }
        }
        return TransportationStudentListVOMaker.prepareStudentListToDisplay(classHistories, pickupDropPointMap, studentVehicleRouteMap);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/busstopwise/new/{busStopId}"})
    public List<StudentVO> listBusAdoptedNewStudentsByBusStop(@PathVariable Long busStopId, Long ayid) {
        Long acadenicYearId;
        if (ayid == null) {
            acadenicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        } else {
            acadenicYearId = ayid;
        }
        List<ClassHistory> classHistories = this.busAdoptedAdmissionListService.getBusFacilityAdoptedAdmissionsByBusStop(acadenicYearId, StudentStatus.CONFIRMED, busStopId, AdmissionType.NEW_ADMISSION_ID);
        Map<Long, StudentPickupDropPoint> pickupDropPointMap = new HashMap();
        Map<Long, List<Route>> studentVehicleRouteMap = new HashMap();
        if (!(classHistories == null || classHistories.isEmpty())) {
            for (ClassHistory classHistory : classHistories) {
                StudentPickupDropPoint studentPickupDropPoint = this.studentPickupDropPointService.getStudentPickupDropPoint(classHistory.getStudent().getId(), acadenicYearId);
                if (studentPickupDropPoint != null) {
                    pickupDropPointMap.put(classHistory.getStudent().getId(), studentPickupDropPoint);
                }
                List<Route> vehicleRoutes = this.busOnRouteService.getStudentRoutes(classHistory.getStudent().getId(), acadenicYearId);
                if (!(vehicleRoutes == null || vehicleRoutes.isEmpty())) {
                    studentVehicleRouteMap.put(classHistory.getStudent().getId(), vehicleRoutes);
                }
            }
        }
        return TransportationStudentListVOMaker.prepareStudentListToDisplay(classHistories, pickupDropPointMap, studentVehicleRouteMap);
    }
}
