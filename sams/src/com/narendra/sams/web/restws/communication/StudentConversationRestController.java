package com.narendra.sams.web.restws.communication;

import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentConversation;
import com.narendra.sams.admission.domain.StudentConversationType;
import com.narendra.sams.admission.service.StudentConversationService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.restws.admission.vo.ConversationTypeVO;
import com.narendra.sams.web.restws.communication.form.StudentConversationForm;
import com.narendra.sams.web.restws.communication.mapper.ConversationTypeVOMapper;
import com.narendra.sams.web.restws.communication.mapper.StudentConversationVOMapper;
import com.narendra.sams.web.restws.communication.vo.StudentConversationVO;
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
@RequestMapping({"/ws/student/conversation"})
public class StudentConversationRestController {
    @Autowired
    private StudentConversationService studentConversationService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{conversationId}"})
    public StudentConversationVO getConversation(@PathVariable Long conversationId) {
        return StudentConversationVOMapper.prepareConversationVO(this.studentConversationService.getConversation(conversationId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list/{studentId}"})
    public List<StudentConversationVO> getStudentConversations(@PathVariable Long studentId) {
        return StudentConversationVOMapper.prepareStudentConversationsVO(this.studentConversationService.getStudentConversations(studentId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/types"})
    public List<ConversationTypeVO> getConversationTypes() {
        return ConversationTypeVOMapper.prepareConversationTypeVOs(this.studentConversationService.getActiveConversationTypes(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxSuccessResponse saveStudentConversation(@RequestBody StudentConversationForm studentConversationForm) {
        StudentConversation studentConversation = new StudentConversation();
        studentConversation.setId(studentConversationForm.getId());
        studentConversation.setConversation(studentConversationForm.getConversation());
        studentConversation.setConversationAgenda(studentConversationForm.getConversationAgenda());
        studentConversation.setConversationUser(studentConversationForm.getConversationUser());
        studentConversation.setConversationWith(studentConversationForm.getConversationWith());
        if (studentConversationForm.getConversationType() != null) {
            StudentConversationType studentConversationType = new StudentConversationType();
            studentConversationType.setId(studentConversationForm.getConversationType());
            studentConversation.setConversationType(studentConversationType);
        }
        if (studentConversationForm.getStudentId() != null) {
            Student student = new Student();
            student.setId(studentConversationForm.getStudentId());
            studentConversation.setStudent(student);
        }
        this.studentConversationService.saveConversation(studentConversation, LoggedinUserAssistant.getLoggedInUserId(), studentConversationForm.getIsSelf(), UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId());
        return new AjaxSuccessResponse();
    }
}
