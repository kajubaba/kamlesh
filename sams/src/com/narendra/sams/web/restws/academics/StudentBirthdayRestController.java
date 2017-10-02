package com.narendra.sams.web.restws.academics;

import com.narendra.sams.acad.service.StudentBirthdayService;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.academics.vo.BirthdayStudent;
import com.narendra.sams.web.utils.StudentInformationUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/acad/studentbirthdays"})
public class StudentBirthdayRestController {
    @Autowired
    private StudentBirthdayService studentBirthdayService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/todays"})
    public List<BirthdayStudent> getTodaysBirthdays() {
        return convertIntoVO(this.studentBirthdayService.getTodaysBirthdays(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    private List<BirthdayStudent> convertIntoVO(List<Student> students) {
        List<BirthdayStudent> birthdayStudents = new ArrayList();
        if (!(students == null || students.isEmpty())) {
            for (Student student : students) {
                BirthdayStudent birthdayStudent = new BirthdayStudent();
                birthdayStudent.setId(student.getId());
                birthdayStudent.setStudentId(student.getStudentId());
                birthdayStudent.setStudentName(student.getFullName());
                birthdayStudent.setCurrentClass(StudentInformationUtil.getClassName(student.getAcademicYearClass()));
                birthdayStudent.setFatherName(student.getFatherName());
                birthdayStudent.setGender(student.getGender());
                birthdayStudent.setBornOn(DateUtil.formatDate(student.getDob(), "dd-MMM-yyyy"));
                birthdayStudent.setStatus(student.getStudentStatus().getName());
                birthdayStudent.setAdmissionType(student.getAdmissionType().getName());
                if (student.getImageName() == null || student.getImageName().isEmpty()) {
                    birthdayStudent.setImageURL("sams/assets/img/student_icon.png");
                } else {
                    birthdayStudent.setImageURL("resources/studentpics/" + student.getImageName());
                }
                if (!(student.getPhone1() == null || student.getPhone1().isEmpty())) {
                    birthdayStudent.setStudentContactNo1(student.getPhone1());
                }
                if (!(student.getPhone2() == null || student.getPhone2().isEmpty())) {
                    birthdayStudent.setStudentContactNo2(student.getPhone2());
                }
                if (!(student.getFatherContact1() == null || student.getFatherContact1().isEmpty())) {
                    birthdayStudent.setFatherContactNo1(student.getFatherContact1());
                }
                if (!(student.getFatherContact2() == null || student.getFatherContact2().isEmpty())) {
                    birthdayStudent.setFatherContactNo2(student.getFatherContact2());
                }
                if (!(student.getMotherContact1() == null || student.getMotherContact1().isEmpty())) {
                    birthdayStudent.setMotherContactNo1(student.getMotherContact1());
                }
                if (!(student.getMotherContact2() == null || student.getMotherContact2().isEmpty())) {
                    birthdayStudent.setMotherContactNo2(student.getMotherContact2());
                }
                if (student.getLocalAddress() != null) {
                    birthdayStudent.setCity(student.getLocalAddress().getCity());
                }
                birthdayStudents.add(birthdayStudent);
            }
        }
        return birthdayStudents;
    }
}
