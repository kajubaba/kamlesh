package com.narendra.sams.web.restws.enquiry;

import com.narendra.sams.enquiry.service.FollowupCommWithService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.enquiry.mapper.vo.FollowupCommWithVOMapper;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryFollowupMasterVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/enquiry-followup/comm-with"})
public class FollowupCommWithController {
    @Autowired
    private FollowupCommWithService followupCommWithService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<EnquiryFollowupMasterVO> getCommunicationWithList() {
        return FollowupCommWithVOMapper.prepareEnquiryFollowupVOs(this.followupCommWithService.getCommunicationWith(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }
}
