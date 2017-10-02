package com.narendra.sams.web.restws.academics;

import com.narendra.sams.acad.service.StudentSectionService;
import com.narendra.sams.web.restws.academics.form.ChangeStudentSectionForm;
import com.narendra.sams.web.restws.admission.StudentListVOMaker;
import com.narendra.sams.web.restws.admission.vo.StudentVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/acad/studentsection"})
public class StudentSectionRestController {
    @Autowired
    private StudentSectionService studentSectionService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/studentlist"})
    public List<StudentVO> getStudents(Long academicYearClassId, Long classSectionId) {
        return StudentListVOMaker.prepareStudentListToDisplay(this.studentSectionService.getStudents(academicYearClassId, classSectionId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/change"})
    public List<StudentVO> changeStudntSection(@RequestBody ChangeStudentSectionForm changeStudentSectionForm) {
        this.studentSectionService.updateStudentSection(changeStudentSectionForm.getStudentIds(), changeStudentSectionForm.getAcademicYearClassId(), changeStudentSectionForm.getNewSectionId());
        return StudentListVOMaker.prepareStudentListToDisplay(this.studentSectionService.getStudents(changeStudentSectionForm.getAcademicYearClassId(), changeStudentSectionForm.getSelectedSectionId()));
    }
}
