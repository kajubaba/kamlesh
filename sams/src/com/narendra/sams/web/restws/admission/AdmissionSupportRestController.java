package com.narendra.sams.web.restws.admission;

import com.narendra.sams.core.address.domain.State;
import com.narendra.sams.core.address.service.AddressService;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AffiliationAuthority;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.StudentCategory;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.service.AcademicYearAdmissionSchemeService;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.core.service.AffiliationAuthorityService;
import com.narendra.sams.core.service.BusStopService;
import com.narendra.sams.core.service.StudentCategoryService;
import com.narendra.sams.core.service.StudentStatusService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.vo.AcademicYearClassVO;
import com.narendra.sams.web.restws.admission.vo.AdmissionCategoryVO;
import com.narendra.sams.web.restws.admission.vo.AffiliationAuthorityVO;
import com.narendra.sams.web.restws.admission.vo.BusStopVO;
import com.narendra.sams.web.restws.admission.vo.StateVO;
import com.narendra.sams.web.restws.admission.vo.StudentStatusVO;
import com.narendra.sams.web.restws.mapper.vo.AdmissionSchemeVOMapper;
import com.narendra.sams.web.restws.vo.AdmissionSchemeVO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/admission/support"})
public class AdmissionSupportRestController {
    @Autowired
    private AcademicYearAdmissionSchemeService academicYearAdmissionSchemeService;
    @Autowired
    private AcademicYearService academicYearService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AffiliationAuthorityService affiliationAuthorityService;
    @Autowired
    private BusStopService busStopService;
    @Autowired
    private StudentCategoryService studentCategoryService;
    @Autowired
    private StudentStatusService studentStatusService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/affiliationAuthorities"})
    public List<AffiliationAuthorityVO> activeAffiliationAuthories() {
        List<AffiliationAuthority> affiliationAuthorities = this.affiliationAuthorityService.getAllActive(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId());
        List<AffiliationAuthorityVO> affiliationAuthorityVOs = null;
        if (affiliationAuthorities != null) {
            affiliationAuthorityVOs = new ArrayList();
            for (AffiliationAuthority affiliationAuthority : affiliationAuthorities) {
                AffiliationAuthorityVO affiliationAuthorityVO = new AffiliationAuthorityVO();
                affiliationAuthorityVO.setId(affiliationAuthority.getId());
                affiliationAuthorityVO.setName(affiliationAuthority.getName());
                affiliationAuthorityVO.setDisplayName(affiliationAuthority.getDisplayName());
                affiliationAuthorityVOs.add(affiliationAuthorityVO);
            }
        }
        return affiliationAuthorityVOs;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classes/{affiliationAuthorityId}"})
    public List<AcademicYearClassVO> activeClasses(@PathVariable Long affiliationAuthorityId, Long academicYearId) {
        List<AcademicYearClass> academicYearClasses;
        if (academicYearId == null) {
            academicYearClasses = this.academicYearService.getActiveAcademicYearClassess(affiliationAuthorityId, UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId());
        } else {
            academicYearClasses = this.academicYearService.getActiveAcademicYearClassess(affiliationAuthorityId, academicYearId);
        }
        List<AcademicYearClassVO> academicYearClassVOs = null;
        if (academicYearClasses != null) {
            academicYearClassVOs = new ArrayList();
            for (AcademicYearClass academicYearClass : academicYearClasses) {
                AcademicYearClassVO academicYearClassVO = new AcademicYearClassVO();
                academicYearClassVO.setClassId(academicYearClass.getId());
                academicYearClassVO.setClassName(academicYearClass.getDisplayName());
                academicYearClassVO.setAffiliatedTo(academicYearClass.getCourseYear().getCourse().getAffiliatedTo().getDisplayName());
                academicYearClassVOs.add(academicYearClassVO);
            }
        }
        return academicYearClassVOs;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/enqclasses/{affiliationAuthorityId}"})
    public List<AcademicYearClassVO> enqActiveClasses(@PathVariable Long affiliationAuthorityId, Long academicYearId) {
        List<AcademicYearClass> academicYearClasses;
        if (academicYearId == null) {
            academicYearClasses = this.academicYearService.getActiveAcademicYearClassess(affiliationAuthorityId, UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfEnquiry().getId());
        } else {
            academicYearClasses = this.academicYearService.getActiveAcademicYearClassess(affiliationAuthorityId, academicYearId);
        }
        List<AcademicYearClassVO> academicYearClassVOs = null;
        if (academicYearClasses != null) {
            academicYearClassVOs = new ArrayList();
            for (AcademicYearClass academicYearClass : academicYearClasses) {
                AcademicYearClassVO academicYearClassVO = new AcademicYearClassVO();
                academicYearClassVO.setClassId(academicYearClass.getId());
                academicYearClassVO.setClassName(academicYearClass.getDisplayName());
                academicYearClassVO.setAffiliatedTo(academicYearClass.getCourseYear().getCourse().getAffiliatedTo().getDisplayName());
                academicYearClassVOs.add(academicYearClassVO);
            }
        }
        return academicYearClassVOs;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/categories"})
    public List<AdmissionCategoryVO> activeCategories() {
        List<StudentCategory> studentCategories = this.studentCategoryService.getActiveStudentCategories(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId());
        List<AdmissionCategoryVO> admissionCategoryVOs = null;
        if (studentCategories != null) {
            admissionCategoryVOs = new ArrayList();
            for (StudentCategory studentCategory : studentCategories) {
                AdmissionCategoryVO admissionCategoryVO = new AdmissionCategoryVO();
                admissionCategoryVO.setId(studentCategory.getId());
                admissionCategoryVO.setName(studentCategory.getName());
                admissionCategoryVOs.add(admissionCategoryVO);
            }
        }
        return admissionCategoryVOs;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/busStops"})
    public List<BusStopVO> activeBusStops() {
        List<BusStop> busStops = this.busStopService.getAllBusStopsOfAcademicYear(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId());
        List<BusStopVO> busStopVOs = null;
        if (busStops != null) {
            busStopVOs = new ArrayList();
            for (BusStop busStop : busStops) {
                BusStopVO busStopVO = new BusStopVO();
                busStopVO.setId(busStop.getId());
                busStopVO.setName(busStop.getName());
                busStopVOs.add(busStopVO);
            }
        }
        return busStopVOs;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/states"})
    public List<StateVO> activeStates() {
        List<State> states = this.addressService.getAllStates();
        List<StateVO> stateVOs = null;
        if (states != null) {
            stateVOs = new ArrayList();
            for (State state : states) {
                StateVO stateVO = new StateVO();
                stateVO.setId(state.getId());
                stateVO.setName(state.getName());
                stateVOs.add(stateVO);
            }
        }
        return stateVOs;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/admissionschemes"})
    public List<AdmissionSchemeVO> activeAdmissionSchemes() {
        return AdmissionSchemeVOMapper.prepareAdmissionSchemeVO(this.academicYearAdmissionSchemeService.getAssignedAdmissionSchemes(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/admissionstatuslist"})
    public List<StudentStatusVO> allAdmissionStatus() {
        List<StudentStatusVO> studentStatusVOList = new ArrayList();
        List<StudentStatus> studentStatusList = this.studentStatusService.getStudentStatusCanAssignToStudent();
        if (studentStatusList != null) {
            for (StudentStatus studentStatus : studentStatusList) {
                StudentStatusVO studentStatusVO = new StudentStatusVO();
                studentStatusVO.setStatusId(studentStatus.getId());
                studentStatusVO.setStatusName(studentStatus.getName());
                studentStatusVOList.add(studentStatusVO);
            }
        }
        return studentStatusVOList;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/activestudentstatuslist"})
    public List<StudentStatusVO> activeStudentStatusList() {
        List<StudentStatusVO> studentStatusVOList = new ArrayList();
        List<StudentStatus> studentStatusList = this.studentStatusService.getActiveStatusList();
        if (studentStatusList != null) {
            for (StudentStatus studentStatus : studentStatusList) {
                StudentStatusVO studentStatusVO = new StudentStatusVO();
                studentStatusVO.setStatusId(studentStatus.getId());
                studentStatusVO.setStatusName(studentStatus.getName());
                studentStatusVOList.add(studentStatusVO);
            }
        }
        return studentStatusVOList;
    }
}
