package com.narendra.sams.web.restws.admission;

import com.narendra.sams.admission.service.BusNotAdoptedAdmissionService;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.vo.StudentVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/admissions/busnotadopted"})
public class BusFacilityNotAdoptedAdmissionRestController {
    @Autowired
    private BusNotAdoptedAdmissionService busNotAdoptedAdmissionService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/studentView/{studentStatus}"})
    public List<StudentVO> listStudentsStatuswise(@PathVariable Long studentStatus, Long academicSessionId) {
        Long acadenicYearId;
        if (academicSessionId == null) {
            acadenicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        } else {
            acadenicYearId = academicSessionId;
        }
        return StudentListVOMaker.prepareStudentListToDisplay(this.busNotAdoptedAdmissionService.getBusFacilityNotAdoptedAdmissions(acadenicYearId, studentStatus, null));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/studentView/new"})
    public List<StudentVO> getNewAdmissions(Long academicSessionId) {
        Long acadenicYearId;
        if (academicSessionId == null) {
            acadenicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        } else {
            acadenicYearId = academicSessionId;
        }
        return StudentListVOMaker.prepareStudentListToDisplay(this.busNotAdoptedAdmissionService.getBusFacilityNotAdoptedAdmissions(acadenicYearId, StudentStatus.CONFIRMED, AdmissionType.NEW_ADMISSION_ID));
    }
}
