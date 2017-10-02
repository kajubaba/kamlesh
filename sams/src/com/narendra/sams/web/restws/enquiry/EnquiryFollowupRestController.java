package com.narendra.sams.web.restws.enquiry;

import com.narendra.sams.enquiry.domain.EnquiryFolloup;
import com.narendra.sams.enquiry.service.EnquiryFollowupService;
import com.narendra.sams.web.restws.enquiry.form.EnquiryFollowupForm;
import com.narendra.sams.web.restws.enquiry.form.mapper.EnquiryFollowupFormMapper;
import com.narendra.sams.web.restws.enquiry.mapper.vo.EnquiryFollowupVOMapper;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryFollowupDetailVO;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryFollowupVO;
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
@RequestMapping({"/ws/enquiry-followup"})
public class EnquiryFollowupRestController {
    @Autowired
    private EnquiryFollowupService enquiryFollowupService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxResponse saveEnquiryFollowup(@RequestBody EnquiryFollowupForm enquiryFollowupForm) {
        Long id;
        EnquiryFolloup enquiryFolloup = EnquiryFollowupFormMapper.prepareEnquiryFollowupDomain(enquiryFollowupForm);
        if (enquiryFolloup.getId() == null) {
            id = this.enquiryFollowupService.addFollowup(enquiryFolloup, LoggedinUserAssistant.getLoggedInUserId());
        } else {
            this.enquiryFollowupService.updateFollowup(enquiryFolloup, LoggedinUserAssistant.getLoggedInUserId());
            id = enquiryFolloup.getId();
        }
        AjaxResponse ajaxResponse = AjaxResponse.successResponse();
        ajaxResponse.setGeneratedId(id);
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list/{enquiryId}"})
    public List<EnquiryFollowupDetailVO> saveEnquiryFollowup(@PathVariable Long enquiryId) {
        return EnquiryFollowupVOMapper.prepareEnquiryFollowupDetailVOs(this.enquiryFollowupService.getEnquiryFollowups(enquiryId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/get/{followupId}"})
    public EnquiryFollowupVO getEnquiryFollowup(@PathVariable Long followupId) {
        return EnquiryFollowupVOMapper.prepareEnquiryFollowupVO(this.enquiryFollowupService.getFollowup(followupId));
    }
}
