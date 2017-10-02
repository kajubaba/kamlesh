package com.narendra.sams.web.restws.student;

import com.narendra.sams.admission.domain.StudentTranslation;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.restws.student.form.StudentBankDetailsForm;
import com.narendra.sams.web.restws.student.vo.StudentBankDetailsVO;
import com.narendra.sams.web.utils.AJAXResponseStatus;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/student/personalinfo"})
public class StudentPersonalInformationRestController {
    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{studentId}/bankdetails"})
    public StudentBankDetailsVO getStudentBankDetails(@PathVariable Long studentId) {
        return StudentPersonalInfoRestControllerHelper.prepareBankDetailsVO(this.studentService.getStudentBankDetails(studentId), studentId);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update/bankdetails"})
    public AjaxSuccessResponse updateStudentBankDetails(@RequestBody StudentBankDetailsForm studentBankDetailsForm) {
        Long bankDetailId = this.studentService.updateStudentBankDetails(StudentPersonalInfoRestControllerHelper.prepareBankDetailsDomain(studentBankDetailsForm), LoggedinUserAssistant.getLoggedInUserId());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AJAXResponseStatus.SUCCESS);
        ajaxSuccessResponse.setGeneratedId(bankDetailId);
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update/translation"})
    public AjaxSuccessResponse updateStudentTranslations(@RequestBody StudentTranslation studentTranslation) {
        this.studentService.updateStudentTranslation(studentTranslation);
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AJAXResponseStatus.SUCCESS);
        return ajaxSuccessResponse;
    }
}
