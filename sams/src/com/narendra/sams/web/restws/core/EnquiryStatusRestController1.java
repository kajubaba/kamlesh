package com.narendra.sams.web.restws.core;

import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.enquiry.domain.EnquiryStatus;
import com.narendra.sams.enquiry.service.EnquiryStatusService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.core.form.EnquiryStatusForm;
import com.narendra.sams.web.restws.core.form.mapper.EnquiryStatusFormToDomainMapper;
import com.narendra.sams.web.restws.core.vo.EnquiryStatusVO;
import com.narendra.sams.web.restws.core.vo.mapper.EnquiryStatusDomainToVOMapper;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
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
@RequestMapping({"/ws/enquiry/status"})
public class EnquiryStatusRestController1 {
    @Autowired
    private EnquiryStatusService enquiryStatusService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET})
    public List<EnquiryStatusVO> getEnquiryStatusList() {
        return EnquiryStatusDomainToVOMapper.mapToVOs(this.enquiryStatusService.getAllStatusList(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<EnquiryStatusVO> getActiveStatusList() {
        return EnquiryStatusDomainToVOMapper.mapToVOs(this.enquiryStatusService.getAllActiveEnquiryStatus(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{enquiryStatusId}"})
    public EnquiryStatusVO getStudentStatus(@PathVariable Long enquiryStatusId) {
        return EnquiryStatusDomainToVOMapper.mapToVO(this.enquiryStatusService.getEnquiryStatus(enquiryStatusId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST})
    public AjaxResponse addStatus(@RequestBody EnquiryStatusForm enquiryStatusForm) {
        EnquiryStatus enquiryStatus = EnquiryStatusFormToDomainMapper.mapToDomain(enquiryStatusForm, UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId());
        AjaxResponse ajaxResponse = AjaxResponse.successResponse();
        try {
            ajaxResponse.setGeneratedId(this.enquiryStatusService.addStatus(enquiryStatus, LoggedinUserAssistant.getLoggedInUserId()));
        } catch (DuplicateNameFoundException e) {
            ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.PUT})
    public AjaxResponse updateStatus(@RequestBody EnquiryStatusForm enquiryStatusForm) {
        EnquiryStatus enquiryStatus = EnquiryStatusFormToDomainMapper.mapToDomain(enquiryStatusForm);
        AjaxResponse ajaxResponse = AjaxResponse.successResponse();
        try {
            this.enquiryStatusService.updateStatus(enquiryStatus, LoggedinUserAssistant.getLoggedInUserId());
            ajaxResponse.setGeneratedId(enquiryStatusForm.getId());
        } catch (DuplicateNameFoundException e) {
            ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
            e.printStackTrace();
        }
        return ajaxResponse;
    }
}
