package com.narendra.sams.web.restws.enquiry.mapper.vo;

import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryDetailVO;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryFormVO;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryVO;
import com.narendra.sams.web.utils.StudentInformationUtil;
import java.util.ArrayList;
import java.util.List;

public class EnquiryVOMapper {
    public static List<EnquiryVO> prepareEnquiryVOs(List<Enquiry> enquiries, Long formFee) {
        List<EnquiryVO> enquiryVOs = new ArrayList();
        if (enquiries != null) {
            for (Enquiry enquiry : enquiries) {
                enquiryVOs.add(prepareEnquiryVO(enquiry, formFee));
            }
        }
        return enquiryVOs;
    }

    public static EnquiryVO prepareEnquiryVO(Enquiry enquiry, Long formFee) {
        EnquiryVO enquiryVO = new EnquiryVO();
        if (enquiry != null) {
            enquiryVO.setId(enquiry.getId());
            enquiryVO.setStudentName(enquiry.getStudentFullName());
            enquiryVO.setStudentContactNo(enquiry.getStudentContactNo());
            enquiryVO.setGender(enquiry.getStudentGender());
            if (enquiry.getStudentDob() != null) {
                enquiryVO.setDobStr(DateUtil.formatDate(enquiry.getStudentDob(), "dd-MMM-yyyy"));
            }
            if (enquiry.getFormIssueDate() != null) {
                enquiryVO.setFormIssuedOn(DateUtil.formatDate(enquiry.getFormIssueDate(), "dd-MMM-yyyy"));
            }
            enquiryVO.setFormReceiptNo(enquiry.getReceiptNo());
            enquiryVO.setFatherName(enquiry.getStudentFatherName());
            enquiryVO.setFatherOccupation(enquiry.getFatherOccupation());
            enquiryVO.setFatherContactNo(enquiry.getFatherContactNo());
            enquiryVO.setMotherName(enquiry.getMotherName());
            enquiryVO.setMotherOccupation(enquiry.getMotherOccupation());
            enquiryVO.setMotherContactNo(enquiry.getMotherContactNo());
            if (enquiry.getAddress() != null) {
                enquiryVO.setLine1(enquiry.getAddress().getLine1());
                enquiryVO.setLine2(enquiry.getAddress().getLine2());
                enquiryVO.setCity(enquiry.getAddress().getCity());
                enquiryVO.setZipCode(enquiry.getAddress().getZipCode());
            }
            enquiryVO.setFormNo(enquiry.getFormNo());
            if (enquiry.getFormNo() != null) {
                enquiryVO.setFormFee(enquiry.getFormFee());
            }
            if (enquiry.getState() != null) {
                enquiryVO.setStateId(enquiry.getState().getId());
            }
            if (enquiry.getCountry() != null) {
                enquiryVO.setCountryId(enquiry.getCountry().getId());
            }
            if (enquiry.getEnquiryStatus() != null) {
                enquiryVO.setStatus(enquiry.getEnquiryStatus().getName());
            }
            enquiryVO.setEnquiryDate(DateUtil.formatDate(enquiry.getCreatedDate(), "dd-MMM-yyyy hh:mm a"));
            if (enquiry.getAcademicYearClass() != null) {
                enquiryVO.setAcademicYearClassId(enquiry.getAcademicYearClass().getId());
                enquiryVO.setAffiliationAuthorityId(enquiry.getAcademicYearClass().getCourseYear().getCourse().getAffiliatedTo().getId());
                enquiryVO.setClassName(StudentInformationUtil.getClassName(enquiry.getAcademicYearClass()));
            }
            if (enquiry.getStudentPrevClass() != null) {
                enquiryVO.setPreClassPercentage(enquiry.getStudentPrevClass().getPercentage());
                enquiryVO.setPrevClass(enquiry.getStudentPrevClass().getClassName());
                enquiryVO.setPrevClassBoard(enquiry.getStudentPrevClass().getBoard());
                enquiryVO.setPrevClassCity(enquiry.getStudentPrevClass().getCity());
                enquiryVO.setPrevInstituteName(enquiry.getStudentPrevClass().getInstituteName());
                enquiryVO.setPrevClassStatus(enquiry.getStudentPrevClass().getStudentStatus());
            }
        }
        return enquiryVO;
    }

    public static EnquiryFormVO prepareEnquiryFormVO(Enquiry enquiry) {
        EnquiryFormVO enquiryVO = new EnquiryFormVO();
        if (enquiry != null) {
            enquiryVO.setId(enquiry.getId());
            enquiryVO.setStudentName(enquiry.getStudentFullName());
            enquiryVO.setStudentContactNo(enquiry.getStudentContactNo());
            enquiryVO.setGender(enquiry.getStudentGender());
            if (enquiry.getStudentDob() != null) {
                enquiryVO.setDobStr(DateUtil.formatDate(enquiry.getStudentDob(), "dd-MMM-yyyy"));
            }
            enquiryVO.setFatherName(enquiry.getStudentFatherName());
            enquiryVO.setFatherOccupation(enquiry.getFatherOccupation());
            enquiryVO.setFatherContactNo(enquiry.getFatherContactNo());
            enquiryVO.setMotherName(enquiry.getMotherName());
            enquiryVO.setMotherOccupation(enquiry.getMotherOccupation());
            enquiryVO.setMotherContactNo(enquiry.getMotherContactNo());
            if (enquiry.getAddress() != null) {
                enquiryVO.setLine1(enquiry.getAddress().getLine1());
                enquiryVO.setLine2(enquiry.getAddress().getLine2());
                enquiryVO.setCity(enquiry.getAddress().getCity());
                enquiryVO.setZipCode(enquiry.getAddress().getZipCode());
            }
            if (enquiry.getState() != null) {
                enquiryVO.setStateId(enquiry.getState().getId());
            }
            if (enquiry.getAcademicYearClass() != null) {
                enquiryVO.setAcademicYearClassId(enquiry.getAcademicYearClass().getId());
                enquiryVO.setAffiliationAuthorityId(enquiry.getAcademicYearClass().getCourseYear().getCourse().getAffiliatedTo().getId());
            }
            if (enquiry.getStudentPrevClass() != null) {
                enquiryVO.setPreClassPercentage(enquiry.getStudentPrevClass().getPercentage());
                enquiryVO.setPrevClass(enquiry.getStudentPrevClass().getClassName());
                enquiryVO.setPrevClassBoard(enquiry.getStudentPrevClass().getBoard());
                enquiryVO.setPrevClassCity(enquiry.getStudentPrevClass().getCity());
                enquiryVO.setPrevInstituteName(enquiry.getStudentPrevClass().getInstituteName());
                enquiryVO.setPrevClassStatus(enquiry.getStudentPrevClass().getStudentStatus());
            }
        }
        return enquiryVO;
    }

    public static EnquiryDetailVO prepareEnquiryDetailVO(Enquiry enquiry) {
        EnquiryDetailVO enquiryVO = new EnquiryDetailVO();
        if (enquiry != null) {
            enquiryVO.setId(enquiry.getId());
            enquiryVO.setStudentName(enquiry.getStudentFullName());
            enquiryVO.setStudentContactNo(enquiry.getStudentContactNo());
            enquiryVO.setGender(enquiry.getStudentGender());
            if (enquiry.getStudentDob() != null) {
                enquiryVO.setDobStr(DateUtil.formatDate(enquiry.getStudentDob(), "dd-MMM-yyyy"));
            }
            enquiryVO.setFatherName(enquiry.getStudentFatherName());
            enquiryVO.setFatherOccupation(enquiry.getFatherOccupation());
            enquiryVO.setFatherContactNo(enquiry.getFatherContactNo());
            enquiryVO.setMotherName(enquiry.getMotherName());
            enquiryVO.setMotherOccupation(enquiry.getMotherOccupation());
            enquiryVO.setMotherContactNo(enquiry.getMotherContactNo());
            if (enquiry.getAddress() != null) {
                enquiryVO.setLine1(enquiry.getAddress().getLine1());
                enquiryVO.setLine2(enquiry.getAddress().getLine2());
                enquiryVO.setCity(enquiry.getAddress().getCity());
                enquiryVO.setZipCode(enquiry.getAddress().getZipCode());
            }
            if (enquiry.getState() != null) {
                enquiryVO.setStateId(enquiry.getState().getId());
            }
            if (enquiry.getAcademicYearClass() != null) {
                enquiryVO.setAcademicYearClassId(enquiry.getAcademicYearClass().getId());
                enquiryVO.setAffiliationAuthorityId(enquiry.getAcademicYearClass().getCourseYear().getCourse().getAffiliatedTo().getId());
                enquiryVO.setEnquiryForClass(StudentInformationUtil.getClassName(enquiry.getAcademicYearClass()));
            }
            if (enquiry.getStudentPrevClass() != null) {
                enquiryVO.setPreClassPercentage(enquiry.getStudentPrevClass().getPercentage());
                enquiryVO.setPrevClass(enquiry.getStudentPrevClass().getClassName());
                enquiryVO.setPrevClassBoard(enquiry.getStudentPrevClass().getBoard());
                enquiryVO.setPrevClassCity(enquiry.getStudentPrevClass().getCity());
                enquiryVO.setPrevInstituteName(enquiry.getStudentPrevClass().getInstituteName());
                enquiryVO.setPrevClassStatus(enquiry.getStudentPrevClass().getStudentStatus());
            }
            if (enquiry.getEnquiryStatus() != null) {
                enquiryVO.setStatusId(enquiry.getEnquiryStatus().getId());
                enquiryVO.setStatusName(enquiry.getEnquiryStatus().getName());
            }
            if (enquiry.getOwner() != null) {
                enquiryVO.setOwnerId(enquiry.getOwner().getId());
                enquiryVO.setOwnerName(enquiry.getOwner().getFullName());
            }
            if (enquiry.getAssignee() != null) {
                enquiryVO.setAssignedToId(enquiry.getAssignee().getId());
                enquiryVO.setAssignedTo(enquiry.getAssignee().getFullName());
            }
            enquiryVO.setFormNo(enquiry.getFormNo());
            enquiryVO.setEnquiryAcademicSession(enquiry.getAcademicYear().getName());
        }
        return enquiryVO;
    }
}
