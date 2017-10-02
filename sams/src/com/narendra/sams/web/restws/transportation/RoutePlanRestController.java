package com.narendra.sams.web.restws.transportation;

import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.domain.RouteBusStop;
import com.narendra.sams.transportation.domain.RouteStudent;
import com.narendra.sams.transportation.service.RoutePlanService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.restws.transportation.helper.RoutePlanDomainToVOConverter;
import com.narendra.sams.web.restws.transportation.vo.AcademicYearBusStopVO;
import com.narendra.sams.web.restws.transportation.vo.RouteBusStopInfoVO;
import com.narendra.sams.web.restws.transportation.vo.RouteBusStopVO;
import com.narendra.sams.web.restws.transportation.vo.RouteStudentVO;
import com.narendra.sams.web.utils.AJAXResponseStatus;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import com.narendra.sams.web.utils.StudentInformationUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/transportation/routeplan"})
public class RoutePlanRestController {
    @Autowired
    private RoutePlanService routePlanService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{routeId}/busstops"})
    public List<RouteBusStopVO> routeBusStops(@PathVariable Long routeId) {
        List<RouteBusStop> routeBusStops = this.routePlanService.getRouteBusStops(routeId);
        List<RouteBusStopVO> routeBusStopVOs = new ArrayList();
        if (routeBusStops != null) {
            for (RouteBusStop routeBusStop : routeBusStops) {
                RouteBusStopVO routeBusStopVO = new RouteBusStopVO();
                routeBusStopVO.setId(routeBusStop.getId());
                routeBusStopVO.setBusStopName(routeBusStop.getBusStop().getBusStop().getName());
                routeBusStopVO.setBusStopId(routeBusStop.getBusStop().getBusStop().getId());
                List<RouteStudent> routeStudents = this.routePlanService.getStudentsOfRoute(routeBusStop.getId());
                if (routeStudents != null) {
                    routeBusStopVO.setAssignedStudentCount(new Long((long) routeStudents.size()));
                }
                List<Student> students = this.routePlanService.getStudentsNotAssginedToBusStopInAnyRoute(routeBusStop.getId(), routeBusStop.getRoute().getType());
                if (students != null) {
                    routeBusStopVO.setUnAssignedStudenCount(new Long((long) students.size()));
                }
                routeBusStopVOs.add(routeBusStopVO);
            }
        }
        return routeBusStopVOs;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{routeId}/notassignedbusstops"})
    public List<AcademicYearBusStopVO> notAssignedBusStops(@PathVariable Long routeId) {
        List<AcademicYearBusStopVO> academicYearBusStopVOs = new ArrayList();
        List<BusFee> busStops = this.routePlanService.getBusStopsToBeAssignedToRoute(routeId);
        if (busStops != null) {
            for (BusFee busFee : busStops) {
                AcademicYearBusStopVO academicYearBusStopVO = new AcademicYearBusStopVO();
                academicYearBusStopVO.setId(busFee.getId());
                academicYearBusStopVO.setBusStopName(busFee.getBusStop().getName());
                academicYearBusStopVO.setBusStopFee(busFee.getRs());
                academicYearBusStopVOs.add(academicYearBusStopVO);
            }
        }
        return academicYearBusStopVOs;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/{routeId}/import"})
    public AjaxSuccessResponse importBusStopsInRoute(@PathVariable Long routeId, @RequestBody List<Integer> academicYearBusStopIds) {
        this.routePlanService.addBusStopsInRoute(routeId, academicYearBusStopIds, LoggedinUserAssistant.getLoggedInUserId());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AJAXResponseStatus.SUCCESS);
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/busstop/{routeBusStopId}/students"})
    public List<RouteStudentVO> getRouteBusStopStduents(@PathVariable Long routeBusStopId) {
        Route route = this.routePlanService.getRouteOfBusStop(routeBusStopId);
        return RoutePlanDomainToVOConverter.convertRouteStudentForDisplay(this.routePlanService.getRouteStudentsWithPickDropPints(routeBusStopId, route.getAcademicYear().getId()), route.getAcademicYear().getId());
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/students/notassignedttbusstop/{routeBusStopId}"})
    public List<RouteStudentVO> getNotAssignedStudentsWithBusStop(@PathVariable Long routeBusStopId) {
        List<Student> students = this.routePlanService.getStudentsNotAssginedToBusStopInAnyRoute(routeBusStopId, this.routePlanService.getRouteBusStop(routeBusStopId).getRoute().getType());
        List<RouteStudentVO> routeStudentVOs = new ArrayList();
        if (students != null) {
            Long academicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
            for (Student student : students) {
                RouteStudentVO routeStudentVO = new RouteStudentVO();
                routeStudentVO.setStudentDBId(student.getId());
                routeStudentVO.setStudentId(student.getStudentId());
                routeStudentVO.setStudentName(student.getFullName());
                routeStudentVO.setStudentGender(student.getGender());
                routeStudentVO.setStudentClass(StudentInformationUtil.getClassName(student.getActiveClassHistory(academicYearId).getAcademicYearClass()));
                routeStudentVO.setStudentStatus(student.getStudentStatus().getName());
                routeStudentVOs.add(routeStudentVO);
            }
        }
        return routeStudentVOs;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/assinstudentstoroute/{routeBusStopId}"})
    public RouteBusStopInfoVO assignStudentsToRoute(@PathVariable Long routeBusStopId, @RequestBody List<Integer> studentIds) {
        this.routePlanService.addStudentsToRoute(routeBusStopId, studentIds, LoggedinUserAssistant.getLoggedInUserId());
        List<RouteStudent> routeStudents = this.routePlanService.getStudentsOfRoute(routeBusStopId);
        Route route = this.routePlanService.getRouteOfBusStop(routeBusStopId);
        RouteBusStopInfoVO routeBusStopInfoVO = new RouteBusStopInfoVO();
        routeBusStopInfoVO.setBusStopStudents(RoutePlanDomainToVOConverter.convertRouteStudentForDisplay(routeStudents, route.getAcademicYear().getId()));
        RouteBusStopVO routeBusStopVO = new RouteBusStopVO();
        if (routeStudents != null) {
            routeBusStopVO.setAssignedStudentCount(new Long((long) routeStudents.size()));
        }
        List<Student> students = this.routePlanService.getStudentsNotAssginedToBusStopInAnyRoute(routeBusStopId, this.routePlanService.getRouteBusStop(routeBusStopId).getRoute().getType());
        if (students != null) {
            routeBusStopVO.setUnAssignedStudenCount(new Long((long) students.size()));
        }
        routeBusStopInfoVO.setBusStopInfo(routeBusStopVO);
        return routeBusStopInfoVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/removestudent/{routeStudentId}"})
    public RouteBusStopInfoVO removeRouteStudent(@PathVariable Long routeStudentId, Long routeBusStopId) {
        this.routePlanService.deleteRouteStudent(routeStudentId);
        List<RouteStudent> routeStudents = this.routePlanService.getStudentsOfRoute(routeBusStopId);
        Route route = this.routePlanService.getRouteOfBusStop(routeBusStopId);
        RouteBusStopInfoVO routeBusStopInfoVO = new RouteBusStopInfoVO();
        routeBusStopInfoVO.setBusStopStudents(RoutePlanDomainToVOConverter.convertRouteStudentForDisplay(routeStudents, route.getAcademicYear().getId()));
        RouteBusStopVO routeBusStopVO = new RouteBusStopVO();
        if (routeStudents != null) {
            routeBusStopVO.setAssignedStudentCount(new Long((long) routeStudents.size()));
        }
        List<Student> students = this.routePlanService.getStudentsNotAssginedToBusStopInAnyRoute(routeBusStopId, this.routePlanService.getRouteBusStop(routeBusStopId).getRoute().getType());
        if (students != null) {
            routeBusStopVO.setUnAssignedStudenCount(new Long((long) students.size()));
        }
        routeBusStopInfoVO.setBusStopInfo(routeBusStopVO);
        return routeBusStopInfoVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/removebusstop/{routeBusStopId}"})
    public List<RouteBusStopVO> removeRouteBusStop(@PathVariable Long routeBusStopId) {
        Route route = this.routePlanService.getRouteOfBusStop(routeBusStopId);
        this.routePlanService.deleteRouteBusStop(routeBusStopId);
        List<RouteBusStop> routeBusStops = this.routePlanService.getRouteBusStops(route.getId());
        List<RouteBusStopVO> routeBusStopVOs = new ArrayList();
        if (routeBusStops != null) {
            for (RouteBusStop routeBusStop : routeBusStops) {
                RouteBusStopVO routeBusStopVO = new RouteBusStopVO();
                routeBusStopVO.setId(routeBusStop.getId());
                routeBusStopVO.setBusStopName(routeBusStop.getBusStop().getBusStop().getName());
                List<RouteStudent> routeStudents = this.routePlanService.getStudentsOfRoute(routeBusStop.getId());
                if (routeStudents != null) {
                    routeBusStopVO.setAssignedStudentCount(new Long((long) routeStudents.size()));
                }
                List<Student> students = this.routePlanService.getStudentsNotAssginedToBusStopInAnyRoute(routeBusStop.getId(), routeBusStop.getRoute().getType());
                if (students != null) {
                    routeBusStopVO.setUnAssignedStudenCount(new Long((long) students.size()));
                }
                routeBusStopVOs.add(routeBusStopVO);
            }
        }
        return routeBusStopVOs;
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/exportstudents/{routeId}"})
    public void exportStudents(@PathVariable Long routeId, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Content-disposition", "attachment;filename=students-list.xls");
        List<RouteBusStop> routeBusStops = this.routePlanService.getRouteBusStops(routeId);
        Workbook wb = new HSSFWorkbook();
        if (routeBusStops != null) {
            Sheet sheet = wb.createSheet("Students");
            Row row0 = sheet.createRow(0);
            row0.createCell(0).setCellValue("Bus Stop");
            row0.createCell(1).setCellValue("Student ID");
            row0.createCell(2).setCellValue("Student Name");
            row0.createCell(3).setCellValue("Father's Name");
            row0.createCell(4).setCellValue("Gender");
            row0.createCell(5).setCellValue("Student Class");
            row0.createCell(6).setCellValue("Student Contact#");
            row0.createCell(7).setCellValue("Father's Contact#");
            row0.createCell(8).setCellValue("Mother's Contact#");
            row0.createCell(9).setCellValue("Pickup Point");
            row0.createCell(10).setCellValue("Drop Point");
            int rowCount = 1;
            for (RouteBusStop routeBusStop : routeBusStops) {
                for (RouteStudent routeStudent : routeBusStop.getBusStopStudents()) {
                    int rowCount2 = rowCount + 1;
                    Row row = sheet.createRow(rowCount);
                    row.createCell(0).setCellValue(routeBusStop.getBusStop().getBusStop().getName());
                    row.createCell(1).setCellValue(routeStudent.getStudent().getStudentId());
                    row.createCell(2).setCellValue(routeStudent.getStudent().getFullName());
                    row.createCell(3).setCellValue(routeStudent.getStudent().getFatherName());
                    row.createCell(4).setCellValue(routeStudent.getStudent().getGender());
                    row.createCell(5).setCellValue(StudentInformationUtil.getClassName(routeStudent.getStudent().getActiveClassHistory(routeBusStop.getRoute().getAcademicYear().getId()).getAcademicYearClass()));
                    StringBuffer stringBuffer = new StringBuffer("");
                    stringBuffer = new StringBuffer("");
                    stringBuffer = new StringBuffer("");
                    if (!(routeStudent.getStudent().getPhone1() == null || routeStudent.getStudent().getPhone1().isEmpty())) {
                        stringBuffer.append(routeStudent.getStudent().getPhone1());
                    }
                    if (!(routeStudent.getStudent().getPhone2() == null || routeStudent.getStudent().getPhone2().isEmpty())) {
                        stringBuffer.append(", ").append(routeStudent.getStudent().getPhone2());
                    }
                    if (!(routeStudent.getStudent().getFatherContact1() == null || routeStudent.getStudent().getFatherContact1().isEmpty())) {
                        stringBuffer.append(routeStudent.getStudent().getFatherContact1());
                    }
                    if (!(routeStudent.getStudent().getFatherContact2() == null || routeStudent.getStudent().getFatherContact2().isEmpty())) {
                        stringBuffer.append(", ").append(routeStudent.getStudent().getFatherContact2());
                    }
                    if (!(routeStudent.getStudent().getMotherContact1() == null || routeStudent.getStudent().getMotherContact1().isEmpty())) {
                        stringBuffer.append(routeStudent.getStudent().getMotherContact1());
                    }
                    if (!(routeStudent.getStudent().getMotherContact2() == null || routeStudent.getStudent().getMotherContact2().isEmpty())) {
                        stringBuffer.append(", ").append(routeStudent.getStudent().getMotherContact2());
                    }
                    Cell cell_6 = row.createCell(6);
                    Cell cell_7 = row.createCell(7);
                    Cell cell_8 = row.createCell(8);
                    cell_6.setCellValue(stringBuffer.toString());
                    cell_7.setCellValue(stringBuffer.toString());
                    cell_8.setCellValue(stringBuffer.toString());
                    Cell cell_9 = row.createCell(9);
                    Cell cell_10 = row.createCell(10);
                    cell_9.setCellValue(routeStudent.getPickupPoint());
                    cell_10.setCellValue(routeStudent.getDropPoint());
                    rowCount = rowCount2;
                }
            }
        }
        wb.write(response.getOutputStream());
    }
}
