package com.narendra.sams.web.restws.enquiry;

import com.narendra.sams.enquiry.service.FollowupCommTypeService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.enquiry.mapper.vo.FollowupCommTypeVOMapper;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryFollowupMasterVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/enquiry-followup/comm-type"})
public class FollowupCommTypeController {
    @Autowired
    private FollowupCommTypeService followupCommTypeService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<EnquiryFollowupMasterVO> getCommTypes() {
        return FollowupCommTypeVOMapper.prepareEnquiryFollowupVOs(this.followupCommTypeService.getFollowupTypes(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }
}
