package com.narendra.sams.web.restws.common;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.common.vo.AcademicYearVO;
import com.narendra.sams.web.restws.common.vo.AcademicYearWrapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/academicsession"})
public class AcademicSessionController {
    @Autowired
    private AcademicYearService academicYearService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/currentadmissionyear"})
    public AcademicYearVO activeAdmissionAcademicYear() {
        AcademicYear academicYear = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission();
        AcademicYearVO academicYearVO = new AcademicYearVO();
        academicYearVO.setAcademicYearId(academicYear.getId());
        academicYearVO.setAcademicYearName(academicYear.getName());
        return academicYearVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/currentenquiryyear"})
    public AcademicYearVO activeEnquiryAcademicYear() {
        AcademicYear academicYear = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfEnquiry();
        AcademicYearVO academicYearVO = new AcademicYearVO();
        academicYearVO.setAcademicYearId(academicYear.getId());
        academicYearVO.setAcademicYearName(academicYear.getName());
        return academicYearVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/all"})
    public AcademicYearWrapper getAcademicYears(Long academicYear) {
        AcademicYearWrapper academicYearWrapper = new AcademicYearWrapper();
        if (academicYear == null) {
            academicYear = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        }
        academicYearWrapper.setSelectedAcademicYear(academicYear);
        academicYearWrapper.setAcademicYears(getAcademicYears());
        return academicYearWrapper;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/allfee"})
    public AcademicYearWrapper getAcademicYearsForFeeModule(Long academicYear) {
        AcademicYearWrapper academicYearWrapper = new AcademicYearWrapper();
        academicYearWrapper.setAcademicYears(getAcademicYears());
        AcademicYearVO academicYearVO = new AcademicYearVO();
        academicYearVO.setAcademicYearId(Long.valueOf(-1));
        academicYearVO.setAcademicYearName("All");
        academicYearWrapper.getAcademicYears().add(academicYearVO);
        academicYearWrapper.setSelectedAcademicYear(academicYear);
        return academicYearWrapper;
    }

    private List<AcademicYearVO> getAcademicYears() {
        List<AcademicYearVO> academicYearVOs = new ArrayList();
        List<AcademicYear> academicYears = this.academicYearService.getAllAcademicYears(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId());
        if (!(academicYears == null || academicYears.isEmpty())) {
            for (AcademicYear academicYear : academicYears) {
                AcademicYearVO academicYearVO = new AcademicYearVO();
                academicYearVO.setAcademicYearId(academicYear.getId());
                academicYearVO.setAcademicYearName(academicYear.getName());
                academicYearVOs.add(academicYearVO);
            }
        }
        return academicYearVOs;
    }
}
