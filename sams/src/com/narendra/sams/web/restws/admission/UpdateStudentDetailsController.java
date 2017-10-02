package com.narendra.sams.web.restws.admission;

import com.narendra.sams.admission.domain.Address;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.core.address.domain.Country;
import com.narendra.sams.core.address.domain.State;
import com.narendra.sams.core.address.service.AddressService;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AdmissionScheme;
import com.narendra.sams.core.domain.AffiliationAuthority;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.StudentCategory;
import com.narendra.sams.core.service.AcademicYearAdmissionSchemeService;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.core.service.AffiliationAuthorityService;
import com.narendra.sams.core.service.BusStopService;
import com.narendra.sams.core.service.StudentCategoryService;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.form.AdmissionEditForm;
import com.narendra.sams.web.restws.admission.vo.AcademicYearClassVO;
import com.narendra.sams.web.restws.admission.vo.AdmissionCategoryVO;
import com.narendra.sams.web.restws.admission.vo.AffiliationAuthorityVO;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.restws.admission.vo.BusStopVO;
import com.narendra.sams.web.restws.admission.vo.StateVO;
import com.narendra.sams.web.restws.mapper.vo.AdmissionSchemeVOMapper;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import com.narendra.sams.web.utils.StudentInformationUtil;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/updatestudent"})
public class UpdateStudentDetailsController {
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
    private StudentService studentService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/viewdetails/{studentId}"})
    public AdmissionEditForm getStudentForEdit(@PathVariable Long studentId) {
        Student student = this.studentService.getStudentById(studentId);
        AdmissionEditForm admission = null;
        if (student != null) {
            admission = new AdmissionEditForm();
            admission.setId(student.getId());
            admission.setStudentId(student.getStudentId());
            if (student.getBusStop() != null) {
                admission.setBusStopId(student.getBusStop().getId());
            }
            if (student.getStudentCategory() != null) {
                admission.setStudentCategoryId(student.getStudentCategory().getId());
            }
            Address currentAddress = student.getLocalAddress();
            admission.setLine1(currentAddress.getLine1());
            admission.setLine2(currentAddress.getLine2());
            admission.setCity(currentAddress.getCity());
            if (currentAddress.getState() != null) {
                admission.setStateId(currentAddress.getState().getId());
            }
            admission.setZipCode(currentAddress.getZipCode());
            admission.setFatherName(student.getFatherName());
            admission.setMotherName(student.getMotherName());
            admission.setFormNo(student.getAdmissionFormNo());
            admission.setStudentName(StudentInformationUtil.getFullName(student.getFirstName(), student.getMiddleName(), student.getLastName()));
            admission.setStudentContactNo(student.getPhone1());
            admission.setAffiliationAuthorityId(student.getAcademicYearClass().getCourseYear().getCourse().getAffiliatedTo().getId());
            admission.setAcademicYearClassId(student.getAcademicYearClass().getId());
            admission.setGender(student.getGender());
            admission.setEmailId(student.getEmailId());
            if (student.getDob() != null) {
                admission.setDobStr(DateUtil.formatDate(student.getDob(), "dd-MMM-yyyy"));
            } else {
                admission.setDobStr("");
            }
            admission.setEnrollmentNo(student.getEnrollmentNo());
            if (student.getAdmissionScheme() != null) {
                admission.setAdmissionSchemeId(student.getAdmissionScheme().getId());
            }
            admission.setSamagraId(student.getSamagraId());
            admission.setFatherContact1(student.getFatherContact1());
            Long instituteId = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId();
            List<AffiliationAuthority> affiliationAuthorities = this.affiliationAuthorityService.getAllActive(instituteId);
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
            admission.setAffiliationAuthorities(affiliationAuthorityVOs);
            List<AcademicYearClass> academicYearClasses = this.academicYearService.getActiveAcademicYearClassess(admission.getAffiliationAuthorityId(), UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId());
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
            admission.setAcademicYearClasses(academicYearClassVOs);
            List<StudentCategory> studentCategories = this.studentCategoryService.getActiveStudentCategories(instituteId);
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
            admission.setAdmissionCategories(admissionCategoryVOs);
            List<BusStop> busStops = this.busStopService.getAllBusStops(instituteId);
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
            admission.setBusStops(busStopVOs);
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
            admission.setStates(stateVOs);
        }
        admission.setAdmissionSchemes(AdmissionSchemeVOMapper.prepareAdmissionSchemeVO(this.academicYearAdmissionSchemeService.getAssignedAdmissionSchemes(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId())));
        return admission;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update"})
    public AjaxSuccessResponse updateStdeuntDetails(@RequestBody AdmissionEditForm admission) {
        Student student = new Student();
        student.setId(admission.getId());
        Address currentAddress = new Address();
        currentAddress.setLine1(admission.getLine1());
        currentAddress.setLine2(admission.getLine2());
        currentAddress.setCity(admission.getCity());
        currentAddress.setZipCode(admission.getZipCode());
        currentAddress.setStudent(student);
        if (admission.getStateId() != null) {
            State state = new State();
            state.setId(admission.getStateId());
            currentAddress.setState(state);
        }
        Country country = new Country();
        country.setId(Long.valueOf(1));
        currentAddress.setCountry(country);
        student.setAddress(currentAddress);
        AcademicYearClass academicYearClass = new AcademicYearClass();
        academicYearClass.setId(admission.getAcademicYearClassId());
        student.setAcademicYearClass(academicYearClass);
        student.setAdmissionFormNo(admission.getFormNo());
        if (admission.getBusStopId() == null) {
            student.setBusStop(null);
        } else {
            BusStop busStop = new BusStop();
            busStop.setId(admission.getBusStopId());
            student.setBusStop(busStop);
        }
        if (!"".equals(admission.getDobStr())) {
            try {
                student.setDob(DateUtil.parseDate(admission.getDobStr(), "dd-MMM-yyyy"));
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        student.setEmailId(admission.getEmailId());
        student.setEnrollmentNo(admission.getEnrollmentNo());
        String[] names = admission.getStudentName().trim().split(" ");
        if (names != null) {
            if (names.length == 1) {
                student.setFirstName(names[0].trim());
            } else if (names.length == 2) {
                student.setFirstName(names[0].trim());
                student.setLastName(names[1].trim());
            } else if (names.length == 3) {
                student.setFirstName(names[0].trim());
                student.setMiddleName(names[1].trim());
                student.setLastName(names[2].trim());
            }
        }
        student.setGender(admission.getGender());
        student.setFatherName(admission.getFatherName());
        student.setMotherName(admission.getMotherName());
        student.setPhone1(admission.getStudentContactNo());
        student.setSamagraId(admission.getSamagraId());
        student.setFatherContact1(admission.getFatherContact1());
        if (admission.getAdmissionSchemeId() != null) {
            AdmissionScheme admissionScheme = new AdmissionScheme();
            admissionScheme.setId(admission.getAdmissionSchemeId());
            student.setAdmissionScheme(admissionScheme);
        }
        if (admission.getStudentCategoryId() != null) {
            StudentCategory studentCategory = new StudentCategory();
            studentCategory.setId(admission.getStudentCategoryId());
            student.setStudentCategory(studentCategory);
        }
        this.studentService.updatePersonalInformation(student, LoggedinUserAssistant.getLoggedInUserId());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus("Ok");
        return ajaxSuccessResponse;
    }
}
