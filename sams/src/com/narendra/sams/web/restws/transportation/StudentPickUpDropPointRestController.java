package com.narendra.sams.web.restws.transportation;

import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.service.BusAdoptedAdmissionListService;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.transportation.domain.StudentPickupDropPoint;
import com.narendra.sams.transportation.service.StudentPickupDropPointService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.restws.transportation.form.UpdateStudentPickupDropPoint;
import com.narendra.sams.web.restws.transportation.vo.StudentPickupDropPointVO;
import com.narendra.sams.web.utils.AJAXResponseStatus;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import com.narendra.sams.web.utils.StudentInformationUtil;
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
@RequestMapping({"/ws/transportation/studentpickupdroppoint"})
public class StudentPickUpDropPointRestController {
    @Autowired
    private BusAdoptedAdmissionListService busAdoptedAdmissionListService;
    @Autowired
    private StudentPickupDropPointService studentPickupDropPointService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/getBusStopWise/{busStopId}"})
    public List<StudentPickupDropPointVO> getStudentsWithPickupDropPoints(@PathVariable Long busStopId) {
        AcademicYear admissionAcademicYear = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission();
        List<StudentPickupDropPointVO> studentPickupDropPointVOs = prepareStudentinformation(this.busAdoptedAdmissionListService.getBusFacilityAdoptedAdmissionsByBusStop(admissionAcademicYear.getId(), StudentStatus.CONFIRMED, busStopId, null));
        getStudentPickupDropPoints(studentPickupDropPointVOs, admissionAcademicYear.getId());
        return studentPickupDropPointVOs;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/updatepickuppoint"})
    public AjaxSuccessResponse updateStudentPickupPoint(@RequestBody UpdateStudentPickupDropPoint updateStudentPickupDropPoint) {
        this.studentPickupDropPointService.updateStudentPickupPoint(updateStudentPickupDropPoint.getStudentId(), UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), updateStudentPickupDropPoint.getPointId(), LoggedinUserAssistant.getLoggedInUserId());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AJAXResponseStatus.SUCCESS);
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/updatedroppoint"})
    public AjaxSuccessResponse updateStudentDropPoint(@RequestBody UpdateStudentPickupDropPoint updateStudentPickupDropPoint) {
        this.studentPickupDropPointService.updateStudentDropPoint(updateStudentPickupDropPoint.getStudentId(), UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), updateStudentPickupDropPoint.getPointId(), LoggedinUserAssistant.getLoggedInUserId());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AJAXResponseStatus.SUCCESS);
        return ajaxSuccessResponse;
    }

    private void getStudentPickupDropPoints(List<StudentPickupDropPointVO> studentPickupDropPointVOs, Long academicYearId) {
        if (studentPickupDropPointVOs != null && !studentPickupDropPointVOs.isEmpty()) {
            for (StudentPickupDropPointVO studentPickupDropPointVO : studentPickupDropPointVOs) {
                StudentPickupDropPoint studentPickupDropPoint = this.studentPickupDropPointService.getStudentPickupDropPoint(studentPickupDropPointVO.getStudentId(), academicYearId);
                if (studentPickupDropPoint != null) {
                    if (studentPickupDropPoint.getDropPoint() != null) {
                        studentPickupDropPointVO.setDropPointId(studentPickupDropPoint.getDropPoint().getId());
                        studentPickupDropPointVO.setDropPointName(studentPickupDropPoint.getDropPoint().getLocationName());
                        studentPickupDropPointVO.setDropPointLandmark(studentPickupDropPoint.getDropPoint().getLandmark());
                    }
                    if (studentPickupDropPoint.getPickupPoint() != null) {
                        studentPickupDropPointVO.setPickupPointId(studentPickupDropPoint.getPickupPoint().getId());
                        studentPickupDropPointVO.setPickupPointName(studentPickupDropPoint.getPickupPoint().getLocationName());
                        studentPickupDropPointVO.setPickupPointLandmark(studentPickupDropPoint.getPickupPoint().getLandmark());
                    }
                }
            }
        }
    }

    private List<StudentPickupDropPointVO> prepareStudentinformation(List<ClassHistory> students) {
        List<StudentPickupDropPointVO> studentPickupDropPointVOs = new ArrayList();
        if (students != null) {
            for (ClassHistory classHistory : students) {
                StudentPickupDropPointVO studentPickupDropPointVO = new StudentPickupDropPointVO();
                studentPickupDropPointVO.setStudentId(classHistory.getStudent().getId());
                studentPickupDropPointVO.setStudentUniqueId(classHistory.getStudent().getStudentId());
                studentPickupDropPointVO.setStudentUniqueId(classHistory.getStudent().getStudentId());
                studentPickupDropPointVO.setStudentName(StudentInformationUtil.getFullName(classHistory.getStudent().getFirstName(), classHistory.getStudent().getMiddleName(), classHistory.getStudent().getLastName()));
                studentPickupDropPointVO.setCurrentClassName(StudentInformationUtil.getClassName(classHistory.getAcademicYearClass()));
                studentPickupDropPointVOs.add(studentPickupDropPointVO);
            }
        }
        return studentPickupDropPointVOs;
    }
}
