package com.narendra.sams.web.restws.admission;

import com.narendra.sams.admission.domain.Address;
import com.narendra.sams.admission.domain.GaurdianAddress;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentGaurdian;
import com.narendra.sams.admission.domain.StudentParentsInformation;
import com.narendra.sams.admission.domain.StudentPersonalInformation;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.core.address.domain.Country;
import com.narendra.sams.core.address.domain.State;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.restws.admission.vo.GaurdianInformationVO;
import com.narendra.sams.web.restws.admission.vo.ParentsInformationVO;
import com.narendra.sams.web.restws.admission.vo.StudentPersonalInformationVO;
import com.narendra.sams.web.utils.AJAXResponseStatus;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/studentinfo"})
public class StudentGaurdianRestController {
    @Autowired
    private StudentService studentService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update/gaurdianinfo"})
    public AjaxSuccessResponse addGaurdian(@RequestBody GaurdianInformationVO gaurdian) {
        StudentGaurdian studentGaurdian = prepareGaurdianToAdd(gaurdian);
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        if (studentGaurdian != null) {
            if (studentGaurdian.getId() != null) {
                this.studentService.updateGaurdian(studentGaurdian, LoggedinUserAssistant.getLoggedInUserId());
                ajaxSuccessResponse.setGeneratedId(gaurdian.getId());
            } else {
                ajaxSuccessResponse.setGeneratedId(this.studentService.addGaurdian(studentGaurdian, LoggedinUserAssistant.getLoggedInUserId()));
            }
            ajaxSuccessResponse.setStatus(AJAXResponseStatus.SUCCESS);
        } else {
            ajaxSuccessResponse.setStatus(AJAXResponseStatus.FAIL);
        }
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update/personalinfo"})
    private AjaxSuccessResponse updateStudentPersonalInformation(@RequestBody StudentPersonalInformationVO studentPersonalInformationVO) {
        this.studentService.updateStudentPersonalInformation(prepareStudentPersonalinformationToUpdate(studentPersonalInformationVO), LoggedinUserAssistant.getLoggedInUserId());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AJAXResponseStatus.SUCCESS);
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update/parentsinfo"})
    private AjaxSuccessResponse updateParentsInformation(@RequestBody ParentsInformationVO parentsInformationVO) {
        this.studentService.updateStudentParentsInformation(prepareStudentParentsinformationToUpdate(parentsInformationVO), LoggedinUserAssistant.getLoggedInUserId());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AJAXResponseStatus.SUCCESS);
        return ajaxSuccessResponse;
    }

    private StudentGaurdian prepareGaurdianToAdd(GaurdianInformationVO gaurdian) {
        if (gaurdian == null) {
            return null;
        }
        StudentGaurdian studentGaurdian = new StudentGaurdian();
        studentGaurdian.setId(gaurdian.getId());
        studentGaurdian.setName(gaurdian.getName());
        studentGaurdian.setDob(null);
        studentGaurdian.setAnnualIncome(gaurdian.getAnnaulIncome());
        studentGaurdian.setOccupation(gaurdian.getOccupation());
        studentGaurdian.setContactNo1(gaurdian.getContactNo1());
        studentGaurdian.setContactNo2(gaurdian.getContactNo2());
        studentGaurdian.setGender("");
        studentGaurdian.setRelationWithStudent(gaurdian.getRelationWithStudent());
        Student student = new Student();
        student.setId(gaurdian.getStudentId());
        studentGaurdian.setStudent(student);
        GaurdianAddress gaurdianAddress = new GaurdianAddress();
        gaurdianAddress.setLine1(gaurdian.getAddress().getFullAddress());
        gaurdianAddress.setLine2(gaurdian.getAddress().getVillegeTownArea());
        gaurdianAddress.setCity(gaurdian.getAddress().getCity());
        gaurdianAddress.setTeh("");
        gaurdianAddress.setDistrict("");
        gaurdianAddress.setZipCode(gaurdian.getAddress().getZipCode());
        gaurdianAddress.setAddressType("Current");
        if (gaurdian.getAddress().getStateId() != null) {
            State state = new State();
            state.setId(gaurdian.getAddress().getStateId());
            gaurdianAddress.setState(state);
        }
        Country country = new Country();
        country.setId(Long.valueOf(1));
        gaurdianAddress.setCountry(country);
        gaurdianAddress.setStudentGaurdian(studentGaurdian);
        Set<GaurdianAddress> addresses = new HashSet();
        addresses.add(gaurdianAddress);
        studentGaurdian.setAddresses(addresses);
        return studentGaurdian;
    }

    private StudentPersonalInformation prepareStudentPersonalinformationToUpdate(StudentPersonalInformationVO newInformation) {
        if (newInformation == null) {
            return null;
        }
        StudentPersonalInformation studentPersonalInformation = new StudentPersonalInformation();
        studentPersonalInformation.setStudentId(newInformation.getStudentId());
        studentPersonalInformation.setStudentAssignedId(newInformation.getStudentAssignedId());
        String[] names = newInformation.getName().split(" ");
        if (names != null) {
            if (names.length == 1) {
                studentPersonalInformation.setFirstName(names[0]);
            } else if (names.length == 2) {
                studentPersonalInformation.setFirstName(names[0]);
                studentPersonalInformation.setLastName(names[1]);
            } else if (names.length == 3) {
                studentPersonalInformation.setFirstName(names[0]);
                studentPersonalInformation.setMiddleName(names[1]);
                studentPersonalInformation.setLastName(names[2]);
            }
        }
        studentPersonalInformation.setGender(newInformation.getGender());
        studentPersonalInformation.setSubCaste(newInformation.getSubCaste());
        studentPersonalInformation.setCategoryId(newInformation.getCategoryId());
        studentPersonalInformation.setMobileNo1(newInformation.getMobileNo1());
        studentPersonalInformation.setMobileNo2(newInformation.getMobileNo2());
        if (!(newInformation.getDob() == null || newInformation.getDob().isEmpty())) {
            try {
                studentPersonalInformation.setDob(DateUtil.parseDate(newInformation.getDob(), "dd-MMM-yyyy"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        studentPersonalInformation.setBirthPlace(newInformation.getBirthPlace());
        studentPersonalInformation.setEmail(newInformation.getEmail());
        studentPersonalInformation.setSamagraId(newInformation.getSamagraId());
        studentPersonalInformation.setFamilyId(newInformation.getFamilyId());
        studentPersonalInformation.setAadharNo(newInformation.getAadharNo());
        studentPersonalInformation.setReligion(newInformation.getReligion());
        studentPersonalInformation.setBloodGroup(newInformation.getBloodGroup());
        studentPersonalInformation.setNationality(newInformation.getNationality());
        studentPersonalInformation.setLanguagesKnown(newInformation.getLanguagesKnown());
        studentPersonalInformation.setEnrollmentNo(newInformation.getEnrollmentNo());
        Address address = new Address();
        address.setId(newInformation.getAddress().getAddressId());
        address.setLine1(newInformation.getAddress().getFullAddress());
        address.setLine2(newInformation.getAddress().getVillegeTownArea());
        address.setCity(newInformation.getAddress().getCity());
        address.setTeh(newInformation.getAddress().getTeh());
        address.setDistrict(newInformation.getAddress().getDist());
        if (newInformation.getAddress().getStateId() != null) {
            State state = new State();
            state.setId(newInformation.getAddress().getStateId());
            address.setState(state);
        } else {
            address.setState(new State());
        }
        address.setZipCode(newInformation.getAddress().getZipCode());
        studentPersonalInformation.setAddress(address);
        return studentPersonalInformation;
    }

    private StudentParentsInformation prepareStudentParentsinformationToUpdate(ParentsInformationVO newInformation) {
        if (newInformation == null) {
            return null;
        }
        StudentParentsInformation studentParentsInformation = new StudentParentsInformation();
        studentParentsInformation.setStudentId(newInformation.getStudentId());
        studentParentsInformation.setFatherName(newInformation.getFatherName());
        studentParentsInformation.setFatherContactNo1(newInformation.getFatherContactNo1());
        studentParentsInformation.setFatherContactNo2(newInformation.getFatherContactNo2());
        studentParentsInformation.setFatherOccupation(newInformation.getFatherOccupation());
        studentParentsInformation.setFatherAnnulaIncome(newInformation.getFatherAnnulaIncome());
        studentParentsInformation.setMotherName(newInformation.getMotherName());
        studentParentsInformation.setMotherContactNo1(newInformation.getMotherContactNo1());
        studentParentsInformation.setMotherContactNo2(newInformation.getMotherContactNo2());
        studentParentsInformation.setMotherOccupation(newInformation.getMotherOccupation());
        studentParentsInformation.setMotherAnnulaIncome(newInformation.getMotherAnnulaIncome());
        Address address = new Address();
        address.setId(newInformation.getAddress().getAddressId());
        address.setLine1(newInformation.getAddress().getFullAddress());
        address.setLine2(newInformation.getAddress().getVillegeTownArea());
        address.setCity(newInformation.getAddress().getCity());
        address.setTeh(newInformation.getAddress().getTeh());
        address.setDistrict(newInformation.getAddress().getDist());
        if (newInformation.getAddress().getStateId() != null) {
            State state = new State();
            state.setId(newInformation.getAddress().getStateId());
            address.setState(state);
        } else {
            address.setState(new State());
        }
        address.setZipCode(newInformation.getAddress().getZipCode());
        studentParentsInformation.setAddress(address);
        return studentParentsInformation;
    }
}
