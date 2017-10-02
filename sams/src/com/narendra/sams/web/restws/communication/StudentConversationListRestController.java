package com.narendra.sams.web.restws.communication;

import com.narendra.sams.admission.service.StudentConversationService;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.communication.form.ConversationsSearchForm;
import com.narendra.sams.web.restws.communication.mapper.StudentConversationVOMapper;
import com.narendra.sams.web.restws.communication.vo.StudentConversationVO;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/conversations"})
public class StudentConversationListRestController {
    @Autowired
    private StudentConversationService studentConversationService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/init-search-param"})
    public ConversationsSearchForm initConversationsSearchForm() {
        ConversationsSearchForm conversationsSearchForm = new ConversationsSearchForm();
        Date todaysDate = DateUtil.getSystemDate();
        conversationsSearchForm.setFrm(DateUtil.formatDate(todaysDate, "dd-MMM-yyyy"));
        conversationsSearchForm.setTo(DateUtil.formatDate(todaysDate, "dd-MMM-yyyy"));
        return conversationsSearchForm;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<StudentConversationVO> getConversation(ConversationsSearchForm conversationsSearchForm) {
        Date from = null;
        Date to = null;
        if (!"".equals(conversationsSearchForm.getFrm())) {
            try {
                from = DateUtil.makeStartDate(DateUtil.parseDate(conversationsSearchForm.getFrm(), "dd-MMM-yyyy"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!"".equals(conversationsSearchForm.getTo())) {
            try {
                to = DateUtil.makeEndDate(DateUtil.parseDate(conversationsSearchForm.getTo(), "dd-MMM-yyyy"));
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
        }
        return StudentConversationVOMapper.prepareStudentConversationsVO(this.studentConversationService.getStudentConversations(from, to, conversationsSearchForm.getUsr(), conversationsSearchForm.getMode(), UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list/todays"})
    public List<StudentConversationVO> getTodaysConversations() {
        Date from = DateUtil.makeStartDate(DateUtil.getSystemDate());
        Date to = DateUtil.makeEndDate(DateUtil.getSystemDate());
        return StudentConversationVOMapper.prepareStudentConversationsVO(this.studentConversationService.getStudentConversations(from, to, null, null, UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }
}
