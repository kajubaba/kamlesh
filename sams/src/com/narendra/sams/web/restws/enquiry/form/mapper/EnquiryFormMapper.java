package com.narendra.sams.web.restws.enquiry.form.mapper;

import com.narendra.sams.core.address.domain.State;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.enquiry.domain.EnquiryAddress;
import com.narendra.sams.enquiry.domain.StudentPrevClass;
import com.narendra.sams.web.restws.enquiry.form.EnquiryForm;
import java.text.ParseException;

public class EnquiryFormMapper {
    public static Enquiry prepareEnquiryDomain(EnquiryForm enquiryForm) {
        Enquiry enquiry = new Enquiry();
        enquiry.setId(enquiryForm.getId());
        EnquiryAddress enquiryAddress = new EnquiryAddress();
        enquiryAddress.setLine1(enquiryForm.getLine1());
        enquiryAddress.setLine2(enquiryForm.getLine2());
        enquiryAddress.setCity(enquiryForm.getCity());
        enquiryAddress.setZipCode(enquiryForm.getZipCode());
        if (enquiryForm.getStateId() != null) {
            State state = new State();
            state.setId(enquiryForm.getStateId());
            enquiry.setState(state);
        }
        enquiry.setAddress(enquiryAddress);
        AcademicYearClass academicYearClass = new AcademicYearClass();
        academicYearClass.setId(enquiryForm.getAcademicYearClassId());
        enquiry.setAcademicYearClass(academicYearClass);
        String[] names = enquiryForm.getStudentName().trim().split(" ");
        if (names != null) {
            if (names.length == 1) {
                enquiry.setStudentFirstName(names[0].trim());
            } else if (names.length == 2) {
                enquiry.setStudentFirstName(names[0].trim());
                enquiry.setStudentLastName(names[1].trim());
            } else if (names.length == 3) {
                enquiry.setStudentFirstName(names[0].trim());
                enquiry.setStudentMiddleName(names[1].trim());
                enquiry.setStudentLastName(names[2].trim());
            }
        }
        enquiry.setStudentGender(enquiryForm.getGender());
        enquiry.setStudentContactNo(enquiryForm.getStudentContactNo());
        if (!"".equals(enquiryForm.getDobStr())) {
            try {
                enquiry.setStudentDob(DateUtil.parseDate(enquiryForm.getDobStr(), "dd-MMM-yyyy"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        enquiry.setStudentFatherName(enquiryForm.getFatherName());
        enquiry.setFatherContactNo(enquiryForm.getFatherContactNo());
        enquiry.setFatherOccupation(enquiryForm.getFatherOccupation());
        enquiry.setMotherName(enquiryForm.getMotherName());
        enquiry.setMotherContactNo(enquiryForm.getMotherContactNo());
        enquiry.setMotherOccupation(enquiryForm.getMotherOccupation());
        StudentPrevClass studentPrevClass = new StudentPrevClass();
        studentPrevClass.setBoard(enquiryForm.getPrevClassBoard());
        studentPrevClass.setCity(enquiryForm.getPrevClassCity());
        studentPrevClass.setClassName(enquiryForm.getPrevClass());
        studentPrevClass.setInstituteName(enquiryForm.getPrevInstituteName());
        studentPrevClass.setPercentage(enquiryForm.getPreClassPercentage());
        if (enquiryForm.getPrevClassStatus() == null) {
            studentPrevClass.setStudentStatus(StudentPrevClass.STATUS_BLANK);
        } else {
            studentPrevClass.setStudentStatus(enquiryForm.getPrevClassStatus());
        }
        enquiry.setStudentPrevClass(studentPrevClass);
        return enquiry;
    }
}
