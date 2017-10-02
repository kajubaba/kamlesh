package com.narendra.sams.web.restws.core;

import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.StudentStatusService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.core.form.StudentStatusForm;
import com.narendra.sams.web.restws.core.form.mapper.StudentStatusFormToDomainMapper;
import com.narendra.sams.web.restws.core.vo.StudentStatusVO;
import com.narendra.sams.web.restws.core.vo.mapper.StudentStatusDomainToVOMapper;
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
@RequestMapping({"/ws/studentstatus"})
public class StudentStatusRestController {
    @Autowired
    private StudentStatusService studentStatusService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET})
    public List<StudentStatusVO> getStudentStatusList() {
        return StudentStatusDomainToVOMapper.mapToVOs(this.studentStatusService.getAllStatusList(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{studentStatusId}"})
    public StudentStatusVO getStudentStatus(@PathVariable Long studentStatusId) {
        return StudentStatusDomainToVOMapper.mapToVO(this.studentStatusService.getStaudentStatus(studentStatusId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST})
    public AjaxResponse addStatus(@RequestBody StudentStatusForm studentStatusForm) {
        StudentStatus studentStatus = StudentStatusFormToDomainMapper.mapToDomain(studentStatusForm);
        AjaxResponse ajaxResponse = AjaxResponse.successResponse();
        try {
            ajaxResponse.setGeneratedId(this.studentStatusService.addStatus(studentStatus, LoggedinUserAssistant.getLoggedInUserId()));
        } catch (DuplicateNameFoundException e) {
            ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.PUT})
    public AjaxResponse updateStatus(@RequestBody StudentStatusForm studentStatusForm) {
        StudentStatus studentStatus = StudentStatusFormToDomainMapper.mapToDomain(studentStatusForm);
        AjaxResponse ajaxResponse = AjaxResponse.successResponse();
        try {
            this.studentStatusService.updateStatus(studentStatus, LoggedinUserAssistant.getLoggedInUserId());
            ajaxResponse.setGeneratedId(studentStatusForm.getId());
        } catch (DuplicateNameFoundException e) {
            ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
            e.printStackTrace();
        }
        return ajaxResponse;
    }
}
