package com.narendra.sams.web.restws.academics;

import com.narendra.sams.academics.exam.domain.StudentRollNo;
import com.narendra.sams.academics.service.StudentRollNoService;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.web.restws.academics.exam.vo.StudentRollNoVO;
import com.narendra.sams.web.restws.academics.form.ClassRollNoForm;
import com.narendra.sams.web.restws.academics.form.RollNoForm;
import com.narendra.sams.web.restws.academics.mapper.vo.StudentRollNoVOMapper;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/academics/rollno"})
public class RollNoRestController {
    @Autowired
    private StudentRollNoService studentRollNoService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/students"})
    public List<StudentRollNoVO> getStduentRollNos(Long classId, Long sectionId) {
        return StudentRollNoVOMapper.prepareStudentRollNoVOs(this.studentRollNoService.getStudentRollNos(classId, sectionId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxSuccessResponse saveAttendance(@RequestBody ClassRollNoForm classRollNoForm) {
        AcademicYearClass academicYearClass = new AcademicYearClass();
        academicYearClass.setId(classRollNoForm.getClassId());
        List<StudentRollNo> studentRollNos = new ArrayList();
        for (RollNoForm rollNoForm : classRollNoForm.getRollNoForms()) {
            StudentRollNo studentRollNo = new StudentRollNo();
            studentRollNo.setId(rollNoForm.getId());
            Student student = new Student();
            student.setId(rollNoForm.getStudentId());
            studentRollNo.setStudent(student);
            studentRollNo.setStudentClass(academicYearClass);
            studentRollNo.setRollNo(rollNoForm.getRollNo());
            studentRollNos.add(studentRollNo);
        }
        this.studentRollNoService.saveStudentRollNos(classRollNoForm.getClassId(), studentRollNos, LoggedinUserAssistant.getLoggedInUserId());
        return new AjaxSuccessResponse();
    }
}
