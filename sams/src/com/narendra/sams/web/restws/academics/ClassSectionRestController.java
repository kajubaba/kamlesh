package com.narendra.sams.web.restws.academics;

import com.narendra.sams.acad.domain.ClassSectionCount;
import com.narendra.sams.academics.service.ClassSectionService;
import com.narendra.sams.admission.domain.ClassSection;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.acad.form.ClassSectionForm;
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
@RequestMapping({"/ws/academics/class-section"})
public class ClassSectionRestController {
    @Autowired
    private ClassSectionService classSectionService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxSuccessResponse saveClassSection(@RequestBody ClassSectionForm classSectionForm) {
        ClassSection classSection = new ClassSection();
        classSection.setId(classSectionForm.getId());
        if (classSection.getId() == null) {
            AcademicYearClass academicYearClass = new AcademicYearClass();
            academicYearClass.setId(classSectionForm.getAcademicYearClassId());
            classSection.setAcademicYearClass(academicYearClass);
        }
        classSection.setSectionName(classSectionForm.getSectionName());
        classSection.setSectionCode(classSectionForm.getSectionCode());
        this.classSectionService.saveClassSection(classSection, LoggedinUserAssistant.getLoggedInUserId());
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<ClassSectionForm> getClassSections(Long academicYearClassId) {
        List<ClassSection> classSections = this.classSectionService.getClassSections(academicYearClassId);
        List<ClassSectionForm> classSectionVOs = new ArrayList();
        if (!(classSections == null || classSections.isEmpty())) {
            for (ClassSection classSection : classSections) {
                ClassSectionForm classSectionForm = new ClassSectionForm();
                classSectionForm.setId(classSection.getId());
                classSectionForm.setSectionName(classSection.getSectionName());
                classSectionForm.setSectionCode(classSection.getSectionCode());
                classSectionVOs.add(classSectionForm);
            }
        }
        return classSectionVOs;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classwise/sectioncount"})
    public List<ClassSectionCount> classWiseSectionCount(Long academicYearId) {
        List<ClassSectionCount> classSubjectCounts = this.classSectionService.getClasswiseSectionCount(academicYearId);
        if (classSubjectCounts == null) {
            return new ArrayList();
        }
        return classSubjectCounts;
    }
}
