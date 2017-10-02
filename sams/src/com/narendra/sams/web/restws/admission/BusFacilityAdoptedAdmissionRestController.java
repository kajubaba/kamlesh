package com.narendra.sams.web.restws.admission;

import com.narendra.sams.admission.domain.AcademicYearClassAdmissionCount;
import com.narendra.sams.admission.domain.BusStopAdmissionCount;
import com.narendra.sams.admission.service.BusAdoptedAdmissionListService;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.vo.StudentVO;
import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/admissions/busadopted"})
public class BusFacilityAdoptedAdmissionRestController {
    @Autowired
    private BusAdoptedAdmissionListService busAdoptedAdmissionListService;
    @Autowired
    private ServletContext servletContext;
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
        return StudentListVOMaker.prepareStudentListToDisplay(this.busAdoptedAdmissionListService.getBusFacilityAdoptedAdmissions(acadenicYearId, studentStatus, null));
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
        return StudentListVOMaker.prepareStudentListToDisplay(this.busAdoptedAdmissionListService.getBusFacilityAdoptedAdmissions(acadenicYearId, StudentStatus.CONFIRMED, AdmissionType.NEW_ADMISSION_ID));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/busstopview/{studentStatus}"})
    public List<BusStopAdmissionCount> busStopWiseAdmissionCount(@PathVariable Long studentStatus, Long ayid) {
        Long acadenicYearId;
        if (ayid == null) {
            acadenicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        } else {
            acadenicYearId = ayid;
        }
        return this.busAdoptedAdmissionListService.getBusStopWiseBusFacilityAdoptedAdmissionCount(acadenicYearId, studentStatus, null);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/busstopview/new"})
    public List<BusStopAdmissionCount> busStopWiseNewAdmissionCount(Long ayid) {
        Long acadenicYearId;
        if (ayid == null) {
            acadenicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        } else {
            acadenicYearId = ayid;
        }
        return this.busAdoptedAdmissionListService.getBusStopWiseBusFacilityAdoptedAdmissionCount(acadenicYearId, StudentStatus.CONFIRMED, AdmissionType.NEW_ADMISSION_ID);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classview/{studentStatus}"})
    public List<AcademicYearClassAdmissionCount> classWiseAdmissionCount(@PathVariable Long studentStatus, Long ayid) {
        Long acadenicYearId;
        if (ayid == null) {
            acadenicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        } else {
            acadenicYearId = ayid;
        }
        return this.busAdoptedAdmissionListService.getClasswiseBusFacilityAdoptedAdmissionCount(acadenicYearId, studentStatus, null);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classwisexml/{studentStatus}"})
    public String getClassWiseAdmissionCountXml(@PathVariable Long studentStatus) {
        AcademicYear admissionAcademicYear = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission();
        if (studentStatus == null) {
            studentStatus = StudentStatus.CONFIRMED;
        }
        return prepareXML(this.busAdoptedAdmissionListService.getClasswiseBusFacilityAdoptedAdmissionCount(admissionAcademicYear.getId(), studentStatus, null), studentStatus.toString());
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classview/new"})
    public List<AcademicYearClassAdmissionCount> classWiseWiseNewAdmissionCount(Long ayid) {
        Long acadenicYearId;
        if (ayid == null) {
            acadenicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        } else {
            acadenicYearId = ayid;
        }
        return this.busAdoptedAdmissionListService.getClasswiseBusFacilityAdoptedAdmissionCount(acadenicYearId, StudentStatus.CONFIRMED, AdmissionType.NEW_ADMISSION_ID);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classwise/{studentStatus}/{academicYearClassId}"})
    public List<StudentVO> listBusAdoptedStudentsByClass(@PathVariable Long studentStatus, @PathVariable Long academicYearClassId) {
        return StudentListVOMaker.prepareStudentListToDisplay(this.busAdoptedAdmissionListService.getBusFacilityAdoptedAdmissionsByClass(academicYearClassId, studentStatus, null));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classwise/new/{academicYearClassId}"})
    public List<StudentVO> listBusAdoptedNewStudentsByClass(@PathVariable Long academicYearClassId) {
        return StudentListVOMaker.prepareStudentListToDisplay(this.busAdoptedAdmissionListService.getBusFacilityAdoptedAdmissionsByClass(academicYearClassId, StudentStatus.CONFIRMED, AdmissionType.NEW_ADMISSION_ID));
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
        return StudentListVOMaker.prepareStudentListToDisplay(this.busAdoptedAdmissionListService.getBusFacilityAdoptedAdmissionsByBusStop(acadenicYearId, studentStatus, busStopId, null));
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
        return StudentListVOMaker.prepareStudentListToDisplay(this.busAdoptedAdmissionListService.getBusFacilityAdoptedAdmissionsByBusStop(acadenicYearId, StudentStatus.CONFIRMED, busStopId, AdmissionType.NEW_ADMISSION_ID));
    }

    private String prepareXML(List<AcademicYearClassAdmissionCount> academicYearClassAdmissionCounts, String studentStatus) {
        StringBuffer dataXml = new StringBuffer();
        dataXml.append("<graph caption='' xAxisName='Class' yAxisName='Admissions' showNames='0' decimalPrecision='0' formatNumberScale='10' rotateNames='1'>");
        if (academicYearClassAdmissionCounts != null) {
            List<String> colors = (List) this.servletContext.getAttribute("colors");
            int colorCount = 0;
            for (AcademicYearClassAdmissionCount academicYearClassAdmissionCount : academicYearClassAdmissionCounts) {
                dataXml.append("<set ").append("name='").append(academicYearClassAdmissionCount.getName()).append("' ").append("value='").append(academicYearClassAdmissionCount.getAdmissionCount()).append("' ").append("link='#/transportation/students/classwise/" + academicYearClassAdmissionCount.getAcademicYearClassId() + "'");
                if (colorCount == colors.size()) {
                    colorCount = 0;
                }
                int colorCount2 = colorCount + 1;
                dataXml.append("color='").append((String) colors.get(colorCount)).append("' ").append(" />");
                colorCount = colorCount2;
            }
        }
        dataXml.append("</graph>");
        return dataXml.toString();
    }
}
