package com.narendra.sams.web.restws.admission;

import com.narendra.sams.admission.domain.Address;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.GaurdianAddress;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentGaurdian;
import com.narendra.sams.admission.domain.StudentQuick;
import com.narendra.sams.admission.service.AdmissionListService;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.vo.AddressVO;
import com.narendra.sams.web.restws.admission.vo.GaurdianInformationVO;
import com.narendra.sams.web.restws.admission.vo.ParentsInformationVO;
import com.narendra.sams.web.restws.admission.vo.StudentBriefInfomationVO;
import com.narendra.sams.web.restws.admission.vo.StudentDetailVO;
import com.narendra.sams.web.restws.admission.vo.StudentPersonalInformationVO;
import com.narendra.sams.web.restws.vo.QuickSearchResultVO;
import com.narendra.sams.web.utils.StudentInformationUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/student"})
public class StudentDetailController {
    @Autowired
    private AdmissionListService admissionListService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/briefinfo/{studentId}"})
    public StudentBriefInfomationVO studentBriefInformation(@PathVariable Long studentId) {
        Student student = this.studentService.getStudentById(studentId);
        StudentBriefInfomationVO studentBriefInfomation = new StudentBriefInfomationVO();
        if (student != null) {
            if (student.getAdmissionScheme() != null) {
                studentBriefInfomation.setAdmissionScheme(student.getAdmissionScheme().getName());
            } else {
                studentBriefInfomation.setAdmissionScheme(" --- ");
            }
            if (student.getBusStop() != null) {
                studentBriefInfomation.setBusStop(student.getBusStop().getName());
            } else {
                studentBriefInfomation.setBusStop(" --- ");
            }
            studentBriefInfomation.setCurrentAcademicYear(student.getAcademicYear().getName());
            studentBriefInfomation.setCurrentClass(StudentInformationUtil.getClassName(student.getAcademicYearClass()));
            studentBriefInfomation.setFatherName(student.getFatherName());
            studentBriefInfomation.setGender(student.getGender());
            studentBriefInfomation.setId(student.getId());
            studentBriefInfomation.setStudentId(student.getStudentId());
            studentBriefInfomation.setStudentName(StudentInformationUtil.getFullName(student.getFirstName(), student.getMiddleName(), student.getLastName()));
            if (!(student.getImageName() == null || student.getImageName().isEmpty())) {
                studentBriefInfomation.setPhoto("resources/studentpics/" + student.getImageName());
            }
            if (student.getAdmissionType() != null) {
                studentBriefInfomation.setStatus(new StringBuilder(String.valueOf(student.getAdmissionType().getName())).append("/").toString());
            } else {
                studentBriefInfomation.setStatus("-----/");
            }
            if (student.getStudentStatus() != null) {
                studentBriefInfomation.setStatus(studentBriefInfomation.getStatus() + student.getStudentStatus().getName());
            } else {
                studentBriefInfomation.setStatus(studentBriefInfomation.getStatus() + "-----");
            }
        }
        return studentBriefInfomation;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/feebriefinfo/{studentId}"})
    public StudentBriefInfomationVO studentFeeBriefInformation(@PathVariable Long studentId, Long classId) {
        Student student = this.studentService.getStudentById(studentId);
        StudentBriefInfomationVO studentBriefInfomation = new StudentBriefInfomationVO();
        if (student != null) {
            if (classId != null) {
                ClassHistory classHistory = student.getActiveClassHistoryByHistoryId(classId);
                if (student.getAdmissionScheme() != null) {
                    studentBriefInfomation.setAdmissionScheme(classHistory.getAdmissionScheme().getName());
                } else {
                    studentBriefInfomation.setAdmissionScheme(" --- ");
                }
                if (classHistory.getBusStop() != null) {
                    studentBriefInfomation.setBusStop(classHistory.getBusStop().getName());
                } else {
                    studentBriefInfomation.setBusStop(" --- ");
                }
                studentBriefInfomation.setCurrentAcademicYear(classHistory.getAcademicYear().getName());
                studentBriefInfomation.setCurrentClass(StudentInformationUtil.getClassName(classHistory.getAcademicYearClass()));
                if (student.getAdmissionType() != null) {
                    studentBriefInfomation.setStatus(new StringBuilder(String.valueOf(classHistory.getAdmissionType().getName())).append("/").toString());
                } else {
                    studentBriefInfomation.setStatus("-----/");
                }
                if (student.getStudentStatus() != null) {
                    studentBriefInfomation.setStatus(studentBriefInfomation.getStatus() + classHistory.getStudentStatus().getName());
                } else {
                    studentBriefInfomation.setStatus(studentBriefInfomation.getStatus() + "-----");
                }
            } else {
                if (student.getAdmissionScheme() != null) {
                    studentBriefInfomation.setAdmissionScheme(student.getAdmissionScheme().getName());
                } else {
                    studentBriefInfomation.setAdmissionScheme(" --- ");
                }
                if (student.getBusStop() != null) {
                    studentBriefInfomation.setBusStop(student.getBusStop().getName());
                } else {
                    studentBriefInfomation.setBusStop(" --- ");
                }
                studentBriefInfomation.setCurrentAcademicYear(student.getAcademicYear().getName());
                studentBriefInfomation.setCurrentClass(StudentInformationUtil.getClassName(student.getAcademicYearClass()));
                if (student.getAdmissionType() != null) {
                    studentBriefInfomation.setStatus(new StringBuilder(String.valueOf(student.getAdmissionType().getName())).append("/").toString());
                } else {
                    studentBriefInfomation.setStatus("-----/");
                }
                if (student.getStudentStatus() != null) {
                    studentBriefInfomation.setStatus(studentBriefInfomation.getStatus() + student.getStudentStatus().getName());
                } else {
                    studentBriefInfomation.setStatus(studentBriefInfomation.getStatus() + "-----");
                }
            }
            studentBriefInfomation.setFatherName(student.getFatherName());
            studentBriefInfomation.setGender(student.getGender());
            studentBriefInfomation.setId(student.getId());
            studentBriefInfomation.setStudentId(student.getStudentId());
            studentBriefInfomation.setStudentName(StudentInformationUtil.getFullName(student.getFirstName(), student.getMiddleName(), student.getLastName()));
            if (!(student.getImageName() == null || student.getImageName().isEmpty())) {
                studentBriefInfomation.setPhoto("resources/studentpics/" + student.getImageName());
            }
        }
        return studentBriefInfomation;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/details/{studentId}"})
    public StudentDetailVO studentDetails(@PathVariable Long studentId) {
        Student student = this.studentService.getStudentById(studentId);
        StudentDetailVO studentDetailVO = null;
        if (student != null) {
            studentDetailVO = new StudentDetailVO();
            if (student.getAdmissionScheme() != null) {
                studentDetailVO.setAdmissionScheme(student.getAdmissionScheme().getName());
            } else {
                studentDetailVO.setAdmissionScheme(" --- ");
            }
            if (student.getBusStop() != null) {
                studentDetailVO.setBusStop(student.getBusStop().getName());
            } else {
                studentDetailVO.setBusStop(" --- ");
            }
            studentDetailVO.setCurrentAcademicYear(student.getAcademicYear().getName());
            studentDetailVO.setCurrentClass(StudentInformationUtil.getClassName(student.getAcademicYearClass()));
            studentDetailVO.setFatherName(student.getFatherName());
            studentDetailVO.setGender(student.getGender());
            studentDetailVO.setId(student.getId());
            studentDetailVO.setStudentId(student.getStudentId());
            studentDetailVO.setStudentName(StudentInformationUtil.getFullName(student.getFirstName(), student.getMiddleName(), student.getLastName()));
            if (!(student.getImageName() == null || student.getImageName().isEmpty())) {
                studentDetailVO.setPhoto("resources/studentpics/" + student.getImageName());
            }
            if (student.getAdmissionType() != null) {
                studentDetailVO.setStatus(new StringBuilder(String.valueOf(student.getAdmissionType().getName())).append("/").toString());
            } else {
                studentDetailVO.setStatus("-----/");
            }
            if (student.getStudentStatus() != null) {
                studentDetailVO.setStatus(studentDetailVO.getStatus() + student.getStudentStatus().getName());
                studentDetailVO.setAdmissionStatus(student.getStudentStatus().getName());
            } else {
                studentDetailVO.setStatus(studentDetailVO.getStatus() + "-----");
            }
            studentDetailVO.setStudentPersonalInformation(prepareStudentPersonalInfomration(student));
            studentDetailVO.setParentsDetails(prepareParentsInfomration(student));
            studentDetailVO.setGaurdianDetails(prepareGaurdianInfomration(student));
        }
        return studentDetailVO;
    }

    private StudentPersonalInformationVO prepareStudentPersonalInfomration(Student student) {
        StudentPersonalInformationVO studentPersonalInformation = new StudentPersonalInformationVO();
        if (student != null) {
            studentPersonalInformation.setStudentId(student.getId());
            studentPersonalInformation.setStudentAssignedId(student.getStudentId());
            studentPersonalInformation.setName(StudentInformationUtil.getFullName(student.getFirstName(), student.getMiddleName(), student.getLastName()));
            studentPersonalInformation.setGender(student.getGender());
            studentPersonalInformation.setSubCaste(student.getSubCaste());
            studentPersonalInformation.setAadharNo(student.getAadharNo());
            studentPersonalInformation.setEmail(student.getEmailId());
            studentPersonalInformation.setFamilyId(student.getFamilyId());
            studentPersonalInformation.setLanguagesKnown(student.getLanguagesKnown());
            studentPersonalInformation.setMobileNo1(student.getPhone1());
            studentPersonalInformation.setMobileNo2(student.getPhone2());
            studentPersonalInformation.setNationality(student.getNationality());
            studentPersonalInformation.setReligion(student.getReligion());
            studentPersonalInformation.setSamagraId(student.getSamagraId());
            studentPersonalInformation.setAge("");
            studentPersonalInformation.setBirthPlace(student.getBirthPlace());
            studentPersonalInformation.setBloodGroup(student.getBloodGroup());
            studentPersonalInformation.setEnrollmentNo(student.getEnrollmentNo());
            if (student.getStudentCategory() != null) {
                studentPersonalInformation.setCategoryId(student.getStudentCategory().getId());
                studentPersonalInformation.setCategory(student.getStudentCategory().getName());
            }
            if (student.getDob() != null) {
                studentPersonalInformation.setDob(DateUtil.formatDate(student.getDob(), "dd-MMM-yyyy"));
            }
            Address studentAddress = null;
            if (student.getAddresses() != null) {
                for (Address address : student.getAddresses()) {
                    if ("local".equals(address.getAddressOf())) {
                        studentAddress = address;
                        break;
                    }
                }
            }
            studentPersonalInformation.setAddress(prepareAddress(studentAddress));
        }
        return studentPersonalInformation;
    }

    private ParentsInformationVO prepareParentsInfomration(Student student) {
        ParentsInformationVO parentDetails = new ParentsInformationVO();
        parentDetails.setStudentId(student.getId());
        if (student != null) {
            parentDetails.setFatherName(student.getFatherName());
            parentDetails.setFatherContactNo1(student.getFatherContact1());
            parentDetails.setFatherContactNo2(student.getFatherContact2());
            parentDetails.setFatherOccupation(student.getFatherOccupation());
            parentDetails.setFatherAnnulaIncome(student.getFatherAnnualIncome());
            parentDetails.setMotherName(student.getMotherName());
            parentDetails.setMotherContactNo1(student.getMotherContact1());
            parentDetails.setMotherContactNo2(student.getMotherContact2());
            parentDetails.setMotherOccupation(student.getMotherOccupation());
            parentDetails.setMotherAnnulaIncome(student.getMotherAnnualIncome());
            Address parentsAddress = null;
            if (student.getAddresses() != null) {
                for (Address address : student.getAddresses()) {
                    if ("parent".equals(address.getAddressOf())) {
                        parentsAddress = address;
                        break;
                    }
                }
            }
            parentDetails.setAddress(prepareAddress(parentsAddress));
        }
        return parentDetails;
    }

    private GaurdianInformationVO prepareGaurdianInfomration(Student student) {
        GaurdianInformationVO gaurdianDetails = null;
        if (student != null) {
            Iterator it;
            gaurdianDetails = new GaurdianInformationVO();
            gaurdianDetails.setStudentId(student.getId());
            StudentGaurdian gaurdian = null;
            if (student.getGaurdians() != null) {
                it = student.getGaurdians().iterator();
                if (it.hasNext()) {
                    gaurdian = (StudentGaurdian) it.next();
                }
            }
            if (gaurdian == null) {
                gaurdianDetails.setAddress(new AddressVO());
                return gaurdianDetails;
            }
            gaurdianDetails.setId(gaurdian.getId());
            gaurdianDetails.setName(gaurdian.getName());
            gaurdianDetails.setContactNo1(gaurdian.getContactNo1());
            gaurdianDetails.setContactNo2(gaurdian.getContactNo2());
            gaurdianDetails.setRelationWithStudent(gaurdian.getRelationWithStudent());
            gaurdianDetails.setAnnaulIncome(gaurdian.getAnnualIncome());
            gaurdianDetails.setOccupation(gaurdian.getOccupation());
            GaurdianAddress address = null;
            if (gaurdian.getAddresses() != null) {
                it = gaurdian.getAddresses().iterator();
                if (it.hasNext()) {
                    address = (GaurdianAddress) it.next();
                }
            }
            gaurdianDetails.setAddress(prepareGaurdianAddress(address));
        }
        return gaurdianDetails;
    }

    private AddressVO prepareAddress(Address address) {
        AddressVO addressVO = null;
        if (address != null) {
            addressVO = new AddressVO();
            addressVO.setAddressId(address.getId());
            addressVO.setFullAddress(address.getLine1());
            addressVO.setVillegeTownArea(address.getLine2());
            addressVO.setCity(address.getCity());
            addressVO.setZipCode(address.getZipCode());
            addressVO.setTeh(address.getTeh());
            addressVO.setDist(address.getDistrict());
            if (address.getState() != null) {
                addressVO.setStateId(address.getState().getId());
                addressVO.setState(address.getState().getDisplayName());
            }
            if (address.getCountry() != null) {
                addressVO.setCountryId(address.getCountry().getId());
                addressVO.setCountry(address.getCountry().getName());
            }
        }
        return addressVO;
    }

    private AddressVO prepareGaurdianAddress(GaurdianAddress address) {
        AddressVO addressVO = new AddressVO();
        if (address != null) {
            addressVO.setAddressId(address.getId());
            addressVO.setFullAddress(address.getLine1());
            addressVO.setVillegeTownArea(address.getLine2());
            addressVO.setCity(address.getCity());
            addressVO.setZipCode(address.getZipCode());
            addressVO.setTeh(address.getTeh());
            addressVO.setDist(address.getDistrict());
            if (address.getState() != null) {
                addressVO.setStateId(address.getState().getId());
                addressVO.setState(address.getState().getDisplayName());
            } else {
                addressVO.setState("");
            }
            if (address.getCountry() != null) {
                addressVO.setCountryId(address.getCountry().getId());
                addressVO.setCountry(address.getCountry().getName());
            }
        }
        return addressVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/quicksearch"})
    public List<QuickSearchResultVO> findStudentsByChars(String searchStr) {
        List<StudentQuick> students = this.admissionListService.searchStudents(searchStr, UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId());
        List<QuickSearchResultVO> quickSearchResultVOs = null;
        if (students != null) {
            quickSearchResultVOs = new ArrayList();
            for (StudentQuick student : students) {
                QuickSearchResultVO quickSearchResultVO = new QuickSearchResultVO();
                quickSearchResultVO.setId(student.getId());
                StringBuffer sb = new StringBuffer("");
                sb.append(student.getFirstName());
                if (student.getLastName() != null) {
                    sb.append(" ").append(student.getLastName());
                }
                if (!(student.getFirstName() == null || student.getFirstName().isEmpty())) {
                    if (student.getGender() == null || student.getGender().isEmpty()) {
                        sb.append(" ").append(" S/O ").append(student.getFatherName());
                    } else if ("male".equals(student.getGender())) {
                        sb.append(" ").append(" S/O ").append(student.getFatherName());
                    } else {
                        sb.append(" ").append(" D/O ").append(student.getFatherName());
                    }
                }
                quickSearchResultVO.setName(sb.toString() + " [" + student.getStudentId() + "]");
                quickSearchResultVOs.add(quickSearchResultVO);
            }
        }
        return quickSearchResultVOs;
    }
}
