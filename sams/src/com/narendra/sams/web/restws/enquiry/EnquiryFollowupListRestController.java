package com.narendra.sams.web.restws.enquiry;

import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.service.EnquiryFollowupListService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.enquiry.mapper.vo.EnquiryFollowupListVOMaker;
import com.narendra.sams.web.restws.enquiry.vo.CountVO;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryFollowupListVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/followups"})
public class EnquiryFollowupListRestController {
    @Autowired
    private EnquiryFollowupListService enquiryFollowupListService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/todays"})
    public List<EnquiryFollowupListVO> getTodaysFollowups() {
        return EnquiryFollowupListVOMaker.prepareEnquiryFollowupListVOs(this.enquiryFollowupListService.getUpcomingEnquiryFollowups(UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId(), DateUtil.getSystemDate(), DateUtil.getSystemDate()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/tomorrows"})
    public List<EnquiryFollowupListVO> getTomorrowsFollowups() {
        return EnquiryFollowupListVOMaker.prepareEnquiryFollowupListVOs(this.enquiryFollowupListService.getUpcomingEnquiryFollowups(UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId(), DateUtil.getTomorrowDate(DateUtil.getSystemDate()), DateUtil.getTomorrowDate(DateUtil.getSystemDate())));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/todays-count"})
    public CountVO getTodaysFollowupsCount() {
        Long count = this.enquiryFollowupListService.getUpcomingEnquiryFollowupsCount(UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId(), DateUtil.getSystemDate(), DateUtil.getSystemDate());
        CountVO countVO = new CountVO();
        countVO.setCount(count.longValue());
        return countVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/tomorrows-count"})
    public CountVO getTomorrosFollowupsCount() {
        Long count = this.enquiryFollowupListService.getUpcomingEnquiryFollowupsCount(UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId(), DateUtil.getTomorrowDate(DateUtil.getSystemDate()), DateUtil.getTomorrowDate(DateUtil.getSystemDate()));
        CountVO countVO = new CountVO();
        countVO.setCount(count.longValue());
        return countVO;
    }
}
