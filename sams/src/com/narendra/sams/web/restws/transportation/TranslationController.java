package com.narendra.sams.web.restws.transportation;

import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentTranslation;
import com.narendra.sams.admission.service.AdmissionListService;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.BusStopTranslation;
import com.narendra.sams.core.service.BusStopService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.restws.transportation.form.BusStopTranslationForm;
import com.narendra.sams.web.restws.transportation.form.TranslationForm;
import java.util.ArrayList;
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
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/translations"})
public class TranslationController {
    @Autowired
    private AdmissionListService admissionListService;
    @Autowired
    private BusStopService busStopService;
    @Autowired
    private StudentService studentService;
    @Autowired
    WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"students"})
    public List<StudentTranslation> getStudents(Long classId, Long stduentStatusId) {
        return prepareTranslationVO(this.admissionListService.getStudentsForTranslations(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), classId, stduentStatusId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.PUT}, value = {"students"})
    public AjaxSuccessResponse updateTranslations(@RequestBody TranslationForm translationForm) {
        this.studentService.updateStudentTranslations(translationForm.getStudentTranslations());
        return new AjaxSuccessResponse();
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/busstops"})
    public List<BusStopTranslation> getBusStops() {
        List<BusStopTranslation> busStopTranslations = new ArrayList();
        List<BusStop> busStops = this.busStopService.getAllBusStops(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId());
        if (busStops != null) {
            for (BusStop busStop : busStops) {
                BusStopTranslation busStopTranslation = new BusStopTranslation();
                busStopTranslation.setBusStopId(busStop.getId());
                busStopTranslation.setBusStopName(busStop.getName());
                busStopTranslation.setBusStopNameInOtherLanguage(busStop.getNameInOtherLang());
                busStopTranslations.add(busStopTranslation);
            }
            Collections.sort(busStopTranslations, new BeanComparator("busStopName", new NullComparator()));
        }
        return busStopTranslations;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.PUT}, value = {"/busstops"})
    public AjaxSuccessResponse updateBusStopTranslations(@RequestBody BusStopTranslationForm busStopTranslationForm) {
        this.busStopService.updateBusStoptranslations(busStopTranslationForm.getBusStopTranslations());
        return new AjaxSuccessResponse();
    }

    private List<StudentTranslation> prepareTranslationVO(List<Student> students) {
        List<StudentTranslation> studentTranslations = new ArrayList();
        if (students != null) {
            for (Student student : students) {
                StudentTranslation studentTranslation = new StudentTranslation();
                studentTranslation.setId(student.getId());
                studentTranslation.setStudentId(student.getStudentId());
                studentTranslation.setStudentName(student.getFullName());
                studentTranslation.setFatherName(student.getFatherName());
                studentTranslation.setTranslatedName(student.getTranslatedStudentName());
                studentTranslation.setTranslatedFatherName(student.getTranslatedFatherName());
                studentTranslations.add(studentTranslation);
            }
            Collections.sort(studentTranslations, new BeanComparator("studentName", new NullComparator()));
        }
        return studentTranslations;
    }
}
