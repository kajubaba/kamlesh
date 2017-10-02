package com.narendra.sams.web.restws.admission;

import com.narendra.sams.admission.domain.Address;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.service.AdmissionService;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.core.address.domain.State;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.StudentCategory;
import com.narendra.sams.core.service.AcademicYearBusFeeService;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.enquiry.domain.EnquiryAddress;
import com.narendra.sams.enquiry.domain.EnquiryStatus;
import com.narendra.sams.enquiry.service.EnquiryActivityService;
import com.narendra.sams.enquiry.service.EnquiryService;
import com.narendra.sams.enquiry.service.EnquiryStatusService;
import com.narendra.sams.transportation.domain.BusStopPoint;
import com.narendra.sams.transportation.domain.StudentPickupDropPoint;
import com.narendra.sams.transportation.service.StudentPickupDropPointService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.form.Admission;
import com.narendra.sams.web.restws.admission.form.BulkAdmissionRenewForm;
import com.narendra.sams.web.restws.admission.form.RenewAdmissionForm;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.restws.admission.vo.EnquiryToAdmissionVO;
import com.narendra.sams.web.restws.admission.vo.NewAdmissionResponse;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/admission/manage"})
public class ManageAdmissionController {
    @Autowired
    private AcademicYearBusFeeService academicYearBusFeeService;
    @Autowired
    private AdmissionService admissionService;
    @Autowired
    private EnquiryActivityService enquiryActivityService;
    @Autowired
    private EnquiryService enquiryService;
    @Autowired
    private EnquiryStatusService enquiryStatusService;
    @Autowired
    private StudentPickupDropPointService studentPickupDropPointService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/newregistration"})
    public NewAdmissionResponse newRegistration(@RequestBody Admission admission) {
        Student student = new Student();
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
        student.setPhone1(admission.getStudentContactNo());
        student.setEmailId(admission.getEmailId());
        if (!"".equals(admission.getDobStr())) {
            try {
                student.setDob(DateUtil.parseDate(admission.getDobStr(), "dd-MMM-yyyy"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (admission.getStudentCategoryId() != null) {
            StudentCategory studentCategory = new StudentCategory();
            studentCategory.setId(admission.getStudentCategoryId());
            student.setStudentCategory(studentCategory);
        }
        student.setFatherName(admission.getFatherName());
        student.setFatherContact1(admission.getFatherContact1());
        student.setFatherOccupation(admission.getFatherOccupation());
        student.setMotherName(admission.getMotherName());
        student.setMotherContact1(admission.getMotherContact1());
        student.setMotherOccupation(admission.getMotherOccupation());
        String tempIdPrefix = UserSessionManager.getUserSession(this.webApplicationContext).getInstituteSetting().getAdmissionSettings().getRegisteredStudentIdPrefix();
        student.setInstitute(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute());
        student.setAcademicYear(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission());
        admission.setStudentId(null);
        Enquiry enquiry = this.enquiryService.getEnquiryByFormNo(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), student.getAdmissionFormNo());
        student.setEnquiry(enquiry);
        Long studentId = this.admissionService.admitStudentTemporarily(student, tempIdPrefix, LoggedinUserAssistant.getLoggedInUserId());
        Student loadedStudent = this.studentService.getStudentById(studentId);
        addStudentPickupDropPoint(loadedStudent, admission.getPickupPointId(), admission.getDropPointId());
        NewAdmissionResponse newAdmissionResponse = new NewAdmissionResponse();
        newAdmissionResponse.setId(studentId);
        newAdmissionResponse.setStudentId(loadedStudent.getStudentId());
        this.enquiryActivityService.updateEnquiryStatus(enquiry.getId(), this.enquiryStatusService.getStatusByName(EnquiryStatus.CONVERTED_INTO_ADMISSION, UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()).getId(), "Auto Conversion", LoggedinUserAssistant.getLoggedInUserId());
        return newAdmissionResponse;
    }

    private void addStudentPickupDropPoint(Student student, Long pickupPointId, Long dropPointId) {
        if (student.getBusStop() != null) {
            StudentPickupDropPoint studentPickupDropPoint = new StudentPickupDropPoint();
            studentPickupDropPoint.setAcademicYear(student.getAcademicYear());
            studentPickupDropPoint.setStudent(student);
            if (pickupPointId != null) {
                BusStopPoint pickupPoint = new BusStopPoint();
                pickupPoint.setId(pickupPointId);
                studentPickupDropPoint.setPickupPoint(pickupPoint);
            }
            if (dropPointId != null) {
                BusStopPoint dropPoint = new BusStopPoint();
                dropPoint.setId(dropPointId);
                studentPickupDropPoint.setDropPoint(dropPoint);
            }
            studentPickupDropPoint.setAcademicYearBusStop(this.academicYearBusFeeService.getBusFee(student.getAcademicYear().getId(), student.getBusStop().getId()));
            this.studentPickupDropPointService.addStudentPickupDropPoint(studentPickupDropPoint, LoggedinUserAssistant.getLoggedInUserId());
        }
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/renew"})
    public AjaxSuccessResponse renewAdmission(@RequestBody RenewAdmissionForm renewAdmissionForm) {
        this.admissionService.renewStudent(renewAdmissionForm.getStudentId(), renewAdmissionForm.getFormNo(), renewAdmissionForm.getAcademicYearClassId(), renewAdmissionForm.getAdmissionSchemeId(), renewAdmissionForm.getBusStopId(), LoggedinUserAssistant.getLoggedInUserId());
        return new AjaxSuccessResponse();
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/bulkrenew"})
    public AjaxSuccessResponse bulkRenewAdmission(@RequestBody BulkAdmissionRenewForm bulkAdmissionRenewForm) {
        this.admissionService.renewStudents(bulkAdmissionRenewForm.getStudentIds(), bulkAdmissionRenewForm.getAcademicYearClassId(), bulkAdmissionRenewForm.getCopyBusStop(), bulkAdmissionRenewForm.getCopyAdmissionScheme(), LoggedinUserAssistant.getLoggedInUserId());
        return new AjaxSuccessResponse();
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/byformno"})
    public EnquiryToAdmissionVO getEnquiryByFormNo(String formNo) {
        EnquiryToAdmissionVO enquiryToAdmissionVO = new EnquiryToAdmissionVO();
        Admission admission = new Admission();
        Enquiry enquiry = this.enquiryService.getEnquiryByFormNo(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), formNo);
        if (enquiry == null) {
            enquiryToAdmissionVO.setIsEnquiryExists(Boolean.FALSE);
            enquiryToAdmissionVO.setAdmission(admission);
        } else {
            enquiryToAdmissionVO.setIsEnquiryExists(Boolean.TRUE);
            if (this.studentService.getStudent(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), formNo) == null) {
                enquiryToAdmissionVO.setIsAlreadyAdmissionTaken(Boolean.FALSE);
                if (enquiry != null) {
                    EnquiryAddress enquiryAddress = enquiry.getAddress();
                    if (enquiryAddress != null) {
                        admission.setLine1(enquiryAddress.getLine1());
                        admission.setLine2(enquiryAddress.getLine2());
                        admission.setCity(enquiryAddress.getCity());
                        admission.setZipCode(enquiryAddress.getZipCode());
                    }
                    if (enquiry.getState() != null) {
                        admission.setStateId(enquiry.getState().getId());
                    }
                    admission.setFatherName(enquiry.getStudentFatherName());
                    admission.setFatherContact1(enquiry.getFatherContactNo());
                    admission.setFatherOccupation(enquiry.getFatherOccupation());
                    admission.setMotherName(enquiry.getMotherName());
                    admission.setMotherContact1(enquiry.getMotherContactNo());
                    admission.setMotherOccupation(enquiry.getMotherOccupation());
                    admission.setFormNo(enquiry.getFormNo());
                    admission.setStudentName(enquiry.getStudentFullName());
                    admission.setStudentContactNo(enquiry.getStudentContactNo());
                    if (enquiry.getAcademicYearClass() != null) {
                        admission.setAcademicYearClassId(enquiry.getAcademicYearClass().getId());
                        admission.setAffiliationAuthorityId(enquiry.getAcademicYearClass().getCourseYear().getCourse().getAffiliatedTo().getId());
                    }
                    if (enquiry.getStudentGender() != null) {
                        if ("Male".equals(enquiry.getStudentGender())) {
                            admission.setGender("male");
                        }
                        if ("Female".equals(enquiry.getStudentGender())) {
                            admission.setGender("female");
                        }
                    }
                    if (enquiry.getStudentDob() != null) {
                        admission.setDobStr(DateUtil.formatDate(enquiry.getStudentDob(), "dd-MMM-yyyy"));
                    } else {
                        admission.setDobStr("");
                    }
                }
                enquiryToAdmissionVO.setAdmission(admission);
            } else {
                enquiryToAdmissionVO.setIsAlreadyAdmissionTaken(Boolean.TRUE);
                enquiryToAdmissionVO.setAdmission(admission);
            }
        }
        return enquiryToAdmissionVO;
    }
}
