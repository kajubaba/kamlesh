package com.narendra.sams.web.restws.admission;

import com.narendra.sams.admission.domain.AcademicYearClassAdmissionCount;
import com.narendra.sams.admission.service.AdmissionCountService;
import com.narendra.sams.admission.service.AdmissionListService;
import com.narendra.sams.admission.service.BusAdoptedAdmissionListService;
import com.narendra.sams.admission.service.BusNotAdoptedAdmissionService;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.vo.AdmissionCountVO;
import com.narendra.sams.web.restws.core.vo.ChartVO;
import java.util.ArrayList;
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
@RequestMapping({"/ws/admission/dashboard"})
public class AdmissionDashboardRestController {
    @Autowired
    private AdmissionCountService admissionCountService;
    @Autowired
    private AdmissionListService admissionListService;
    @Autowired
    private BusAdoptedAdmissionListService busAdoptedAdmissionListService;
    @Autowired
    private BusNotAdoptedAdmissionService busNotAdoptedAdmissionService;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping({"/schemeAdmissions"})
    public AdmissionCountVO getUnderSchemeAdmissions() {
        Long count = Long.valueOf(this.admissionCountService.getAdmissionCountUnderScheme(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId()));
        AdmissionCountVO admissionCount = new AdmissionCountVO();
        admissionCount.setAdmissions(count);
        return admissionCount;
    }

    @ResponseBody
    @RequestMapping({"/totalAdmissions"})
    public AdmissionCountVO getTotalAdmissions() {
        Long count = Long.valueOf(this.admissionCountService.getAdmissionCount(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), StudentStatus.CONFIRMED, null));
        AdmissionCountVO admissionCount = new AdmissionCountVO();
        admissionCount.setAdmissions(count);
        return admissionCount;
    }

    @ResponseBody
    @RequestMapping({"/newAdmissions"})
    public AdmissionCountVO getnewAdmissions() {
        Long count = Long.valueOf(this.admissionCountService.getAdmissionCount(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), StudentStatus.CONFIRMED, AdmissionType.NEW_ADMISSION_ID));
        AdmissionCountVO admissionCount = new AdmissionCountVO();
        admissionCount.setAdmissions(count);
        return admissionCount;
    }

    @RequestMapping({"/queueAdmissions"})
    public void getQueueAdmissions() {
    }

    @ResponseBody
    @RequestMapping({"/newRegistrations"})
    public AdmissionCountVO getNewRegistrations() {
        Long count = Long.valueOf(this.admissionCountService.getAdmissionCount(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), StudentStatus.TEMPORARY, null));
        AdmissionCountVO admissionCount = new AdmissionCountVO();
        admissionCount.setAdmissions(count);
        return admissionCount;
    }

    @ResponseBody
    @RequestMapping({"/renewRegistrations"})
    public AdmissionCountVO getRenewRegistrations() {
        Long count = Long.valueOf(this.admissionCountService.getAdmissionCount(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), StudentStatus.TEMPORARY_RENEWAL, null));
        AdmissionCountVO admissionCount = new AdmissionCountVO();
        admissionCount.setAdmissions(count);
        return admissionCount;
    }

    @ResponseBody
    @RequestMapping({"/cancelAdmissions"})
    public AdmissionCountVO getCancelAdmission() {
        Long count = Long.valueOf(this.admissionCountService.getAdmissionCount(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), StudentStatus.CANCELLED, null));
        AdmissionCountVO admissionCount = new AdmissionCountVO();
        admissionCount.setAdmissions(count);
        return admissionCount;
    }

    @ResponseBody
    @RequestMapping({"/degreeawarded"})
    public AdmissionCountVO getDegreeAwardedAdmission() {
        Long count = Long.valueOf(this.admissionCountService.getAdmissionCount(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), StudentStatus.DEGREE_AWARDED, null));
        AdmissionCountVO admissionCount = new AdmissionCountVO();
        admissionCount.setAdmissions(count);
        return admissionCount;
    }

    @ResponseBody
    @RequestMapping({"/busAdopted"})
    public AdmissionCountVO getBusFacilityAdoptedAdmission() {
        Long count = this.busAdoptedAdmissionListService.getBusFacilityAdoptedAdmissionCount(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), StudentStatus.CONFIRMED, null);
        AdmissionCountVO admissionCount = new AdmissionCountVO();
        admissionCount.setAdmissions(count);
        return admissionCount;
    }

    @ResponseBody
    @RequestMapping({"/busNotAdopted"})
    public AdmissionCountVO getBusFacilityNotAdoptedAdmission() {
        Long count = this.busNotAdoptedAdmissionService.getBusFacilityNotAdoptedAdmissionCount(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), StudentStatus.CONFIRMED, null);
        AdmissionCountVO admissionCount = new AdmissionCountVO();
        admissionCount.setAdmissions(count);
        return admissionCount;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classwisexml/{studentStatus}"})
    public List<ChartVO> getClassWiseXml(@PathVariable Long studentStatus) {
        AcademicYear admissionAcademicYear = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission();
        if (studentStatus == null) {
            studentStatus = StudentStatus.CONFIRMED;
        }
        return prepareXML(this.admissionCountService.getClasswiseAdmissionCount(admissionAcademicYear.getId(), studentStatus, null), studentStatus.toString());
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/academics/classwisexml/{studentStatus}"})
    public String getClassWiseXmlForAcademics(@PathVariable Long studentStatus) {
        AcademicYear admissionAcademicYear = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission();
        if (studentStatus == null) {
            studentStatus = StudentStatus.CONFIRMED;
        }
        return prepareXMLForAcademics(this.admissionCountService.getClasswiseAdmissionCount(admissionAcademicYear.getId(), studentStatus, null), studentStatus.toString());
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classwisexml/new"})
    public List<ChartVO> getClassWiseXmlForNewAdmissions() {
        return prepareXML(this.admissionCountService.getClasswiseAdmissionCount(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), StudentStatus.CONFIRMED, AdmissionType.NEW_ADMISSION_ID), "new");
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/academics/classwisexml/new"})
    public String getClassWiseXmlForNewAdmissionsForAcademics() {
        return prepareXMLForAcademics(this.admissionCountService.getClasswiseAdmissionCount(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), StudentStatus.CONFIRMED, AdmissionType.NEW_ADMISSION_ID), "new");
    }

    private List<ChartVO> prepareXML(List<AcademicYearClassAdmissionCount> academicYearClassAdmissionCounts, String studentStatus) {
        List<ChartVO> chartVOs = new ArrayList();
        if (academicYearClassAdmissionCounts != null) {
            List<String> colors = (List) this.servletContext.getAttribute("colors");
            int colorCount = 0;
            for (AcademicYearClassAdmissionCount academicYearClassAdmissionCount : academicYearClassAdmissionCounts) {
                ChartVO chartVO = new ChartVO();
                chartVO.setClassName(academicYearClassAdmissionCount.getName());
                chartVO.setStudents(academicYearClassAdmissionCount.getAdmissionCount());
                if (colorCount == 10) {
                    colorCount = 0;
                }
                int colorCount2 = colorCount + 1;
                chartVO.setColor((String) colors.get(colorCount));
                chartVOs.add(chartVO);
                colorCount = colorCount2;
            }
        }
        return chartVOs;
    }

    private String prepareXMLForAcademics(List<AcademicYearClassAdmissionCount> academicYearClassAdmissionCounts, String studentStatus) {
        StringBuffer dataXml = new StringBuffer();
        dataXml.append("<graph caption='' xAxisName='Class' yAxisName='Admissions' showNames='0' decimalPrecision='0' formatNumberScale='10' rotateNames='1'>");
        if (academicYearClassAdmissionCounts != null) {
            List<String> colors = (List) this.servletContext.getAttribute("colors");
            int colorCount = 0;
            for (AcademicYearClassAdmissionCount academicYearClassAdmissionCount : academicYearClassAdmissionCounts) {
                dataXml.append("<set ").append("name='").append(academicYearClassAdmissionCount.getName()).append("' ").append("value='").append(academicYearClassAdmissionCount.getAdmissionCount()).append("' ").append("link='#/academics/classwise/" + studentStatus + "/" + academicYearClassAdmissionCount.getAcademicYearClassId() + "/null'");
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
