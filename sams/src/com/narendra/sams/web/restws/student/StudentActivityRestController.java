package com.narendra.sams.web.restws.student;

import com.narendra.sams.core.exception.OperationCanNotSucceedException;
import com.narendra.sams.core.exception.OperationCanSucceedException;
import com.narendra.sams.fee.service.StudentActivityService;
import com.narendra.sams.web.restws.admission.form.ChangeRequestForm;
import com.narendra.sams.web.utils.AJAXResponseStatus;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/student/activity"})
public class StudentActivityRestController {
    @Autowired
    private StudentActivityService studentActivityService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/changebusstop"})
    public ChangeRequestResponse changeBusStop(@RequestBody ChangeRequestForm changeRequestForm) {
        ChangeRequestResponse busStopChangeResponse = new ChangeRequestResponse();
        try {
            this.studentActivityService.updateStudentBusStop(changeRequestForm.getStudentId(), changeRequestForm.getNewId(), LoggedinUserAssistant.getLoggedInUserId(), changeRequestForm.getComments(), changeRequestForm.getIsForced());
        } catch (OperationCanNotSucceedException e) {
            e.printStackTrace();
            busStopChangeResponse.setStatus("FAILED");
            busStopChangeResponse.setMessage(e.getMessage());
            busStopChangeResponse.setShowAlert(Boolean.FALSE);
        } catch (OperationCanSucceedException e2) {
            e2.printStackTrace();
            busStopChangeResponse.setStatus("FAILED");
            busStopChangeResponse.setMessage(e2.getMessage());
            busStopChangeResponse.setShowAlert(Boolean.TRUE);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        busStopChangeResponse.setStatus(AJAXResponseStatus.SUCCESS);
        busStopChangeResponse.setMessage("Bus Stop changed successfully");
        return busStopChangeResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/changeclass"})
    public ChangeRequestResponse changeClass(@RequestBody ChangeRequestForm changeRequestForm) {
        ChangeRequestResponse changeRequestResponse = new ChangeRequestResponse();
        try {
            this.studentActivityService.updateStudentClass(changeRequestForm.getStudentId(), changeRequestForm.getNewId(), LoggedinUserAssistant.getLoggedInUserId(), changeRequestForm.getComments(), changeRequestForm.getIsForced());
        } catch (OperationCanNotSucceedException e) {
            e.printStackTrace();
            changeRequestResponse.setStatus("FAILED");
            changeRequestResponse.setMessage(e.getMessage());
            changeRequestResponse.setShowAlert(Boolean.FALSE);
        } catch (OperationCanSucceedException e2) {
            e2.printStackTrace();
            changeRequestResponse.setStatus("FAILED");
            changeRequestResponse.setMessage(e2.getMessage());
            changeRequestResponse.setShowAlert(Boolean.TRUE);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        changeRequestResponse.setStatus(AJAXResponseStatus.SUCCESS);
        changeRequestResponse.setMessage("Academic class changed successfully");
        return changeRequestResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/changeadmissionscheme"})
    public ChangeRequestResponse changeAdmissionScheme(@RequestBody ChangeRequestForm changeRequestForm) {
        ChangeRequestResponse busStopChangeResponse = new ChangeRequestResponse();
        this.studentActivityService.updateStudentAdmissionScheme(changeRequestForm.getStudentId(), changeRequestForm.getNewId(), LoggedinUserAssistant.getLoggedInUserId(), changeRequestForm.getComments());
        busStopChangeResponse.setStatus(AJAXResponseStatus.SUCCESS);
        busStopChangeResponse.setMessage("Admission Scheme changed successfully");
        return busStopChangeResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/changestudentstatus"})
    public ChangeRequestResponse changeStudentStatus(@RequestBody ChangeRequestForm changeRequestForm) {
        ChangeRequestResponse studentStatusChangeResponse = new ChangeRequestResponse();
        try {
            this.studentActivityService.updateStudentStatus(changeRequestForm.getStudentId(), changeRequestForm.getNewId(), LoggedinUserAssistant.getLoggedInUserId(), changeRequestForm.getComments());
        } catch (OperationCanNotSucceedException e) {
            e.printStackTrace();
        }
        studentStatusChangeResponse.setStatus(AJAXResponseStatus.SUCCESS);
        studentStatusChangeResponse.setMessage("Student Status changed successfully");
        return studentStatusChangeResponse;
    }
}
