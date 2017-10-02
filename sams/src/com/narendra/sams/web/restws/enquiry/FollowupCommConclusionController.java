package com.narendra.sams.web.restws.enquiry;

import com.narendra.sams.enquiry.service.FollowupCommConclusionService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.enquiry.mapper.vo.FollowupCommConclusionVOMapper;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryFollowupMasterVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/enquiry-followup/comm-conclusion"})
public class FollowupCommConclusionController {
    @Autowired
    private FollowupCommConclusionService followupCommConclusionService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<EnquiryFollowupMasterVO> getConclusions() {
        return FollowupCommConclusionVOMapper.prepareEnquiryFollowupVOs(this.followupCommConclusionService.getFollowupConclusions(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }
}
