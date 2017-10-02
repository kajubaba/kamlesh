package com.narendra.sams.web.restws.admission;

import com.narendra.sams.admission.domain.AcademicYearClassAdmissionCount;
import com.narendra.sams.admission.domain.Address;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.StudentBankDetails;
import com.narendra.sams.admission.service.AdmissionCountService;
import com.narendra.sams.admission.service.AdmissionListService;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.vo.StudentVO;
import com.narendra.sams.web.utils.StudentInformationUtil;
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
@RequestMapping({"/ws/admissions"})
public class AdmissionListRestController {
    @Autowired
    private AdmissionCountService admissionCountService;
    @Autowired
    private AdmissionListService admissionListService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/underScheme/{studentStatusId}"})
    public List<StudentVO> getUnderSchemeAdmissions(@PathVariable Long studentStatusId, Long ayid) {
        Long acadenicYearId;
        if (ayid == null) {
            acadenicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        } else {
            acadenicYearId = ayid;
        }
        return poplateUnderSchemeStudentVO(this.admissionListService.getUnderSchemeAdmissions(acadenicYearId, studentStatusId, null));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/underScheme/new"})
    public List<StudentVO> getUnderSchemeNewAdmissions(Long ayid) {
        Long acadenicYearId;
        if (ayid == null) {
            acadenicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        } else {
            acadenicYearId = ayid;
        }
        return poplateUnderSchemeStudentVO(this.admissionListService.getUnderSchemeAdmissions(acadenicYearId, StudentStatus.CONFIRMED, AdmissionType.NEW_ADMISSION_ID));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/studentView/{studentStatusId}"})
    public List<StudentVO> getConfirmAdmissions(@PathVariable Long studentStatusId, Long ayid) {
        Long acadenicYearId;
        if (ayid == null) {
            acadenicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        } else {
            acadenicYearId = ayid;
        }
        return StudentListVOMaker.prepareStudentListToDisplay(this.admissionListService.getAdmissions(acadenicYearId, studentStatusId, null));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/studentView/new"})
    public List<StudentVO> getnewAdmissions(Long ayid) {
        Long acadenicYearId;
        if (ayid == null) {
            acadenicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        } else {
            acadenicYearId = ayid;
        }
        return StudentListVOMaker.prepareStudentListToDisplay(this.admissionListService.getAdmissions(acadenicYearId, StudentStatus.CONFIRMED, AdmissionType.NEW_ADMISSION_ID));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classview/{studentStatusId}"})
    public List<AcademicYearClassAdmissionCount> getClasswiseAdmissionCount(@PathVariable Long studentStatusId, Long ayid) {
        Long acadenicYearId;
        if (ayid == null) {
            acadenicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        } else {
            acadenicYearId = ayid;
        }
        return this.admissionCountService.getClasswiseAdmissionCount(acadenicYearId, studentStatusId, null);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classview/new"})
    public List<AcademicYearClassAdmissionCount> getClasswiseNewAdmissionCount(Long ayid) {
        Long acadenicYearId;
        if (ayid == null) {
            acadenicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        } else {
            acadenicYearId = ayid;
        }
        return this.admissionCountService.getClasswiseAdmissionCount(acadenicYearId, StudentStatus.CONFIRMED, AdmissionType.NEW_ADMISSION_ID);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classwise/{classId}/{studentStatusId}"})
    public List<StudentVO> getStudentsOfClass(@PathVariable Long classId, @PathVariable Long studentStatusId) {
        return StudentListVOMaker.prepareStudentListToDisplay(this.admissionListService.getAdmissionsByClass(classId, studentStatusId, null));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classwise/{classId}/new"})
    public List<StudentVO> getNewStudentsOfClass(@PathVariable Long classId) {
        return StudentListVOMaker.prepareStudentListToDisplay(this.admissionListService.getAdmissionsByClass(classId, StudentStatus.CONFIRMED, AdmissionType.NEW_ADMISSION_ID));
    }

    private List<StudentVO> poplateUnderSchemeStudentVO(List<ClassHistory> classHistories) {
        List<StudentVO> students = null;
        if (classHistories != null) {
            students = new ArrayList();
            for (ClassHistory classHistory : classHistories) {
                StudentVO studentVO = new StudentVO();
                studentVO.setId(classHistory.getStudent().getId());
                studentVO.setStudentId(classHistory.getStudent().getStudentId());
                studentVO.setName(StudentInformationUtil.getFullName(classHistory.getStudent().getFirstName(), classHistory.getStudent().getMiddleName(), classHistory.getStudent().getLastName()));
                studentVO.setGender(classHistory.getStudent().getGender());
                studentVO.setGuardianName(classHistory.getStudent().getFatherName());
                studentVO.setCurrentClass(classHistory.getAcademicYearClass().getDisplayName());
                studentVO.setCurrentClassId(classHistory.getAcademicYearClass().getId());
                studentVO.setMotherName(classHistory.getStudent().getMotherName());
                studentVO.setCaste(classHistory.getStudent().getSubCaste());
                studentVO.setRelegion(classHistory.getStudent().getReligion());
                studentVO.setBirthPlace(classHistory.getStudent().getBirthPlace());
                studentVO.setFamilyId(classHistory.getStudent().getFamilyId());
                studentVO.setSamagraId(classHistory.getStudent().getSamagraId());
                studentVO.setAadharNo(classHistory.getStudent().getAadharNo());
                studentVO.setBloodGroup(classHistory.getStudent().getBloodGroup());
                studentVO.setSubCaste(classHistory.getStudent().getSubCaste());
                if (classHistory.getStudent().getStudentCategory() != null) {
                    studentVO.setCategory(classHistory.getStudent().getStudentCategory().getName());
                }
                studentVO.setStudentContactNo(StudentInformationUtil.prepareContactNo(classHistory.getStudent().getPhone1(), classHistory.getStudent().getPhone2()));
                studentVO.setFatherContactNo(StudentInformationUtil.prepareContactNo(classHistory.getStudent().getFatherContact1(), classHistory.getStudent().getFatherContact2()));
                studentVO.setMotherContactNo(StudentInformationUtil.prepareContactNo(classHistory.getStudent().getMotherContact1(), classHistory.getStudent().getMotherContact2()));
                StringBuffer address = new StringBuffer("");
                if (classHistory.getStudent().getAddresses() != null) {
                    for (Address add : classHistory.getStudent().getAddresses()) {
                        if (Address.ADDRESS_TYPE_LOCAL.equals(add.getAddressOf())) {
                            if (!(add.getLine1() == null || add.getLine1().isEmpty())) {
                                address.append(add.getLine1()).append(" , ");
                            }
                            if (!(add.getLine2() == null || add.getLine2().isEmpty())) {
                                address.append(add.getLine2()).append(" , ");
                            }
                            address.append(add.getCity());
                            studentVO.setCity(add.getCity());
                        }
                    }
                }
                studentVO.setFullAddress(address.toString());
                if (classHistory.getLastRegistrationDate() != null) {
                    studentVO.setAdmissionRegistrationDate(DateUtil.formatDate(classHistory.getLastRegistrationDate(), "dd-MMM-yyyy"));
                }
                if (classHistory.getAdmissionConfirmDateTime() != null) {
                    studentVO.setAdmissionConfirmationDate(DateUtil.formatDate(classHistory.getAdmissionConfirmDateTime(), "dd-MMM-yyyy"));
                }
                studentVO.setAdmissionFormNo(classHistory.getAdmissionFormNo());
                if (classHistory.getStudent().getDob() != null) {
                    studentVO.setDob(DateUtil.formatDate(classHistory.getStudent().getDob(), "dd-MMM-yyyy"));
                }
                if (classHistory.getClassSection() != null) {
                    studentVO.setClassSection(classHistory.getClassSection().getSectionName());
                }
                if (classHistory.getStudentStatus() != null) {
                    studentVO.setStudentStatus(classHistory.getStudentStatus().getName());
                }
                if (classHistory.getBusStop() != null) {
                    studentVO.setBusStopId(classHistory.getBusStop().getId());
                    studentVO.setBusStop(classHistory.getBusStop().getName());
                }
                if (classHistory.getAdmissionScheme() != null) {
                    studentVO.setAdmissionScheme(classHistory.getAdmissionScheme().getName());
                }
                if (!(classHistory.getStudent().getEnrollmentNo() == null || classHistory.getStudent().getEnrollmentNo().isEmpty())) {
                    studentVO.setEnrollmentNo(classHistory.getStudent().getEnrollmentNo());
                }
                if (!(classHistory.getStudent().getStudentBankDetails() == null || classHistory.getStudent().getStudentBankDetails().isEmpty())) {
                    for (StudentBankDetails studentBankDetails : classHistory.getStudent().getStudentBankDetails()) {
                        studentVO.setBankAcNo(studentBankDetails.getBankAcNo());
                        studentVO.setBankBranch(studentBankDetails.getBranchName());
                        if (studentBankDetails.getBank() != null) {
                            studentVO.setBankName(studentBankDetails.getBank().getBankName());
                        }
                        studentVO.setBankIfsc(studentBankDetails.getIfsc());
                    }
                }
                students.add(studentVO);
            }
        }
        return students;
    }
}
