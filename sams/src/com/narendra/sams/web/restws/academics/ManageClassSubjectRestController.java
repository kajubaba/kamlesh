package com.narendra.sams.web.restws.academics;

import com.narendra.sams.academics.domain.ClassSubject;
import com.narendra.sams.academics.domain.ClassSubjectCount;
import com.narendra.sams.academics.service.ClassSubjectService;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.academics.form.ExamClassSubjectForm;
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
@RequestMapping({"/ws/academics/managesubjects"})
public class ManageClassSubjectRestController {
    @Autowired
    private ClassSubjectService classSubjectService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxSuccessResponse saveClassSubject(@RequestBody ExamClassSubjectForm subjectForm) {
        ClassSubject examClassSubject = new ClassSubject();
        if (subjectForm.getId() == null) {
            AcademicYearClass academicYearClass = new AcademicYearClass();
            academicYearClass.setId(subjectForm.getAcademicYearClassId());
            examClassSubject.setAcademicYearClass(academicYearClass);
        } else {
            examClassSubject.setId(subjectForm.getId());
        }
        examClassSubject.setIsOptional(Boolean.FALSE);
        examClassSubject.setSubjectName(subjectForm.getSubjectName());
        examClassSubject.setSubjectCode(subjectForm.getSubjectCode());
        examClassSubject.setDisplaySequenceNo(subjectForm.getDisplaySequenceNo());
        examClassSubject.setMaxMarks(subjectForm.getMaxMarks());
        this.classSubjectService.saveSubject(examClassSubject, LoggedinUserAssistant.getLoggedInUserId());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classwise/subjectcount"})
    public List<ClassSubjectCount> classWiseSubjectCount(Long academicYearId) {
        List<ClassSubjectCount> classSubjectCounts = this.classSubjectService.getClasswiseSubjectCount(academicYearId);
        if (classSubjectCounts == null) {
            return new ArrayList();
        }
        return classSubjectCounts;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classwise/SubjectDetails"})
    public List<ExamClassSubjectForm> getClassWiseSubjectDetails(Long academicYearClassId) {
        List<ClassSubject> examClassSubjects = this.classSubjectService.getExamClassSubjects(academicYearClassId);
        List<ExamClassSubjectForm> examClassSubjectForms = new ArrayList();
        if (examClassSubjects != null && examClassSubjects.size() > 0) {
            for (ClassSubject examClassSubject : examClassSubjects) {
                ExamClassSubjectForm examClassSubjectForm = new ExamClassSubjectForm();
                examClassSubjectForm.setId(examClassSubject.getId());
                examClassSubjectForm.setSubjectName(examClassSubject.getSubjectName());
                examClassSubjectForm.setSubjectCode(examClassSubject.getSubjectCode());
                examClassSubjectForm.setMaxMarks(examClassSubject.getMaxMarks());
                examClassSubjectForm.setDisplaySequenceNo(examClassSubject.getDisplaySequenceNo());
                examClassSubjectForms.add(examClassSubjectForm);
            }
        }
        return examClassSubjectForms;
    }
}
