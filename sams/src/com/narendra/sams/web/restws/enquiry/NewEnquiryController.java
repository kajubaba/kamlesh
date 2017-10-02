package com.narendra.sams.web.restws.enquiry;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.enquiry.service.EnquiryService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.vo.AcademicYearClassVO;
import com.narendra.sams.web.restws.enquiry.form.EnquiryForm;
import com.narendra.sams.web.restws.enquiry.form.mapper.EnquiryFormMapper;
import com.narendra.sams.web.restws.enquiry.mapper.vo.EnquiryVOMapper;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryDetailVO;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryFormUpdateVO;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryFormVO;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/enquiry"})
public class NewEnquiryController {
    @Autowired
    AcademicYearService academicYearService;
    @Autowired
    private EnquiryService enquiryService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/add"})
    public AjaxResponse newEnquiry(@RequestBody EnquiryForm enquiryForm) {
        Enquiry enquiry = EnquiryFormMapper.prepareEnquiryDomain(enquiryForm);
        enquiry.setInstitute(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute());
        AcademicYear academicYear = new AcademicYear();
        academicYear.setId(UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId());
        enquiry.setAcademicYear(academicYear);
        Long id = this.enquiryService.addNewEnquiry(enquiry, LoggedinUserAssistant.getLoggedInUserId());
        AjaxResponse ajaxResponse = AjaxResponse.successResponse();
        ajaxResponse.setGeneratedId(id);
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update"})
    public AjaxResponse updateEnquiry(@RequestBody EnquiryForm enquiryForm) {
        this.enquiryService.updateEnquiry(EnquiryFormMapper.prepareEnquiryDomain(enquiryForm), LoggedinUserAssistant.getLoggedInUserId());
        AjaxResponse ajaxResponse = AjaxResponse.successResponse();
        ajaxResponse.setGeneratedId(enquiryForm.getId());
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{enquiryId}"})
    public EnquiryFormUpdateVO getEnquiry(@PathVariable Long enquiryId) {
        Enquiry enquiry = this.enquiryService.getEnquiry(enquiryId);
        EnquiryFormVO enquiryFormVO = EnquiryVOMapper.prepareEnquiryFormVO(enquiry);
        List<AcademicYearClassVO> academicYearClassVOs = null;
        if (!(enquiry == null || enquiry.getAcademicYearClass() == null)) {
            academicYearClassVOs = activeClasses(enquiry.getAcademicYearClass().getCourseYear().getCourse().getAffiliatedTo().getId(), enquiry.getAcademicYear().getId());
        }
        EnquiryFormUpdateVO enquiryFormUpdateVO = new EnquiryFormUpdateVO();
        enquiryFormUpdateVO.setClasses(academicYearClassVOs);
        enquiryFormUpdateVO.setEnquiry(enquiryFormVO);
        return enquiryFormUpdateVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/by-contact"})
    public EnquiryFormUpdateVO getEnquiryByContactNo(String contactNo) {
        Enquiry enquiry = this.enquiryService.getEnquiryByContactNo(UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId(), contactNo);
        EnquiryFormVO enquiryFormVO = EnquiryVOMapper.prepareEnquiryFormVO(enquiry);
        List<AcademicYearClassVO> academicYearClassVOs = null;
        if (!(enquiry == null || enquiry.getAcademicYearClass() == null)) {
            academicYearClassVOs = activeClasses(enquiry.getAcademicYearClass().getCourseYear().getCourse().getAffiliatedTo().getId(), enquiry.getAcademicYear().getId());
        }
        EnquiryFormUpdateVO enquiryFormUpdateVO = new EnquiryFormUpdateVO();
        enquiryFormUpdateVO.setClasses(academicYearClassVOs);
        enquiryFormUpdateVO.setEnquiry(enquiryFormVO);
        return enquiryFormUpdateVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/detail/{enquiryId}"})
    public EnquiryDetailVO getEnquiryDetail(@PathVariable Long enquiryId) {
        return EnquiryVOMapper.prepareEnquiryDetailVO(this.enquiryService.getEnquiry(enquiryId));
    }

    private List<AcademicYearClassVO> activeClasses(@PathVariable Long affiliationAuthorityId, Long academicYearId) {
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
}
