package com.narendra.sams.web.restws.transportation;

import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.transportation.domain.BusStopPoint;
import com.narendra.sams.transportation.domain.RouteInfo;
import com.narendra.sams.transportation.service.RouteStopService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.restws.transportation.form.BusStopPointForm;
import com.narendra.sams.web.restws.transportation.vo.BusStopPickupDropPointsVO;
import com.narendra.sams.web.restws.transportation.vo.BusStopPointVO;
import com.narendra.sams.web.restws.transportation.vo.CountVO;
import com.narendra.sams.web.restws.transportation.vo.RouteInfoVO;
import com.narendra.sams.web.utils.AJAXResponseStatus;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
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
@RequestMapping({"/ws/transportation/busstop"})
public class RouteBusStopRestController {
    @Autowired
    private RouteStopService routeStopService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/allstops"})
    public List<RouteInfoVO> allBusStops() {
        return preparBusStopsToDisplay(this.routeStopService.getActiveBusStopsInAcademicYear(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/pickupdroppoints/{academicYearBusStopId}"})
    public BusStopPickupDropPointsVO pickupDropPoints(@PathVariable Long academicYearBusStopId) {
        BusStopPickupDropPointsVO busStopPickupDropPointsVO = new BusStopPickupDropPointsVO();
        busStopPickupDropPointsVO.setBusStopId(academicYearBusStopId);
        busStopPickupDropPointsVO.setBusStopName(this.routeStopService.getBusStop(academicYearBusStopId).getName());
        List<BusStopPoint> busStopPoints = this.routeStopService.getBusStopPickUpDropPoints(academicYearBusStopId);
        if (busStopPoints != null) {
            for (BusStopPoint busStopPoint : busStopPoints) {
                BusStopPointVO buStopPointVO = new BusStopPointVO();
                buStopPointVO.setId(busStopPoint.getId());
                buStopPointVO.setName(busStopPoint.getLocationName());
                buStopPointVO.setLandmark(busStopPoint.getLandmark());
                if ("Pickup".equals(busStopPoint.getType())) {
                    busStopPickupDropPointsVO.getPickupPoints().add(buStopPointVO);
                } else if ("Drop".equals(busStopPoint.getType())) {
                    busStopPickupDropPointsVO.getDropPoints().add(buStopPointVO);
                }
            }
        }
        return busStopPickupDropPointsVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/pickupdroppointsbybusstopid/{busStopId}"})
    public BusStopPickupDropPointsVO pickupDropPointsByBusStopId(@PathVariable Long busStopId) {
        Long academicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        BusFee academicYearBusStop = this.routeStopService.getAcademicYearBusStop(academicYearId, busStopId);
        BusStopPickupDropPointsVO busStopPickupDropPointsVO = new BusStopPickupDropPointsVO();
        busStopPickupDropPointsVO.setBusStopId(academicYearBusStop.getId());
        busStopPickupDropPointsVO.setBusStopName(academicYearBusStop.getBusStop().getName());
        List<BusStopPoint> busStopPoints = this.routeStopService.getBusStopPickUpDropPoints(academicYearId, busStopId);
        if (busStopPoints != null) {
            for (BusStopPoint busStopPoint : busStopPoints) {
                BusStopPointVO buStopPointVO = new BusStopPointVO();
                buStopPointVO.setId(busStopPoint.getId());
                buStopPointVO.setName(busStopPoint.getLocationName());
                buStopPointVO.setLandmark(busStopPoint.getLandmark());
                if ("Pickup".equals(busStopPoint.getType())) {
                    busStopPickupDropPointsVO.getPickupPoints().add(buStopPointVO);
                } else if ("Drop".equals(busStopPoint.getType())) {
                    busStopPickupDropPointsVO.getDropPoints().add(buStopPointVO);
                }
            }
        }
        return busStopPickupDropPointsVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/pickupdroppoints/add"})
    public AjaxSuccessResponse addPickUpOrDropPoint(@RequestBody BusStopPointForm busStopPointForm) {
        BusStopPoint busStopPoint = new BusStopPoint();
        busStopPoint.setLocationName(busStopPointForm.getName());
        busStopPoint.setLandmark(busStopPointForm.getLadmark());
        busStopPoint.setType(busStopPointForm.getType());
        BusFee busFee = new BusFee();
        busFee.setId(busStopPointForm.getBusStopId());
        busStopPoint.setBusFee(busFee);
        this.routeStopService.addBusStopPickUpDropPoint(busStopPoint, busStopPointForm.getCreateReversePoint(), LoggedinUserAssistant.getLoggedInUserId());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AJAXResponseStatus.SUCCESS);
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/count"})
    public CountVO routeBusStopCount() {
        Long count = this.routeStopService.getBusStopCount(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId());
        CountVO countVO = new CountVO();
        countVO.setCountOf("busstop");
        countVO.setCount(count);
        return countVO;
    }

    private List<RouteInfoVO> preparBusStopsToDisplay(List<RouteInfo> busStops) {
        List<RouteInfoVO> routeBusStopVOs = new ArrayList();
        if (busStops != null) {
            for (RouteInfo busStop : busStops) {
                RouteInfoVO routeBusStopVO = new RouteInfoVO();
                routeBusStopVO.setId(busStop.getId());
                routeBusStopVO.setBusStopId(busStop.getBusStopId());
                routeBusStopVO.setName(busStop.getBusStopName());
                routeBusStopVO.setDistance(busStop.getDistance());
                routeBusStopVO.setPickupPointCount(busStop.getPickupPointCount());
                routeBusStopVO.setDropPointCount(busStop.getDropPointCount());
                routeBusStopVOs.add(routeBusStopVO);
            }
        }
        return routeBusStopVOs;
    }
}
