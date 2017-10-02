package com.narendra.sams.web.restws.academics;

import com.narendra.sams.academics.domain.Attendance;
import com.narendra.sams.academics.exam.domain.EvaluationTerm;
import com.narendra.sams.academics.service.EvaluationTermService;
import com.narendra.sams.academics.service.StudentAttendanceService;
import com.narendra.sams.web.restws.academics.form.AttendanceForm;
import com.narendra.sams.web.restws.academics.vo.AttendanceVO;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.util.Collections;
import java.util.List;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/academics/attendance"})
public class AttendanceController {
    @Autowired
    private EvaluationTermService evaluationTermService;
    @Autowired
    private StudentAttendanceService studentAttendanceService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/get/students"})
    public AttendanceVO getStduentAttendance(Long classId, Long sectionId, Long termId) {
        AttendanceVO attendanceVO = new AttendanceVO();
        EvaluationTerm evaluationTerm = this.evaluationTermService.getEvaluationTerm(termId);
        List<Attendance> attendances = this.studentAttendanceService.getTermAttendance(termId, classId, sectionId);
        if (!(attendances == null || attendances.isEmpty())) {
            Collections.sort(attendances, new BeanComparator("studentName", new NullComparator()));
        }
        attendanceVO.setAttendances(attendances);
        if (evaluationTerm != null) {
            attendanceVO.setTermId(evaluationTerm.getId());
            attendanceVO.setWorkingDays(evaluationTerm.getWorkingDays());
        }
        return attendanceVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxSuccessResponse saveAttendance(@RequestBody AttendanceForm attendanceForm) {
        this.studentAttendanceService.saveTermAttendance(attendanceForm.getTermId(), attendanceForm.getClassId(), attendanceForm.getAttendances(), LoggedinUserAssistant.getLoggedInUserId());
        return new AjaxSuccessResponse();
    }
}
