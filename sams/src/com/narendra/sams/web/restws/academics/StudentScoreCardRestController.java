package com.narendra.sams.web.restws.academics;

import com.narendra.sams.academics.domain.AssessmentName;
import com.narendra.sams.academics.domain.AssessmentTerm;
import com.narendra.sams.academics.domain.ScoreCard;
import com.narendra.sams.academics.domain.StudentAttendance;
import com.narendra.sams.academics.exam.domain.EvaluationTerm;
import com.narendra.sams.academics.service.EvaluationTermService;
import com.narendra.sams.academics.service.ScoreCardService;
import com.narendra.sams.academics.service.StudentAttendanceService;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.academics.utils.MarksheetUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/academics/student/scorecard"})
public class StudentScoreCardRestController {
    @Autowired
    private EvaluationTermService evaluationTermService;
    @Autowired
    private ScoreCardService scoreCardService;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private StudentAttendanceService studentAttendanceService;
    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{studentId}"})
    public ScoreCard getFullScholasticScorecard(@PathVariable Long studentId) {
        return this.scoreCardService.getStudentScholasticScoreCard(studentId, this.studentService.getStudentById(studentId).getAcademicYearClass().getId());
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/scholastic/single/{studentId}"})
    public ScoreCard getScholasticScorecardOfOneEvaluation(@PathVariable Long studentId, Long evaluationTermId) {
        return this.scoreCardService.getStudentScholasticScoreCardOfOneTerm(studentId, this.studentService.getStudentById(studentId).getAcademicYearClass().getId(), evaluationTermId);
    }

    public void pdfScoreCard(@PathVariable Long studentId, Long evaluationTermId, HttpServletResponse response) {
        JRException e;
        FileInputStream fileInputStream;
        Exception e2;
        Student student = this.studentService.getStudentById(studentId);
        ScoreCard scoreCard = this.scoreCardService.getStudentScholasticScoreCardOfOneTerm(studentId, student.getAcademicYearClass().getId(), evaluationTermId);
        AssessmentTerm assessmentTerm = (AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(0);
        Map<String, Object> parameters = new HashMap();
        parameters.put("imagePath", this.servletContext.getRealPath("/resources/logo/school_logo.jpeg"));
        parameters.put("NameOfSchoolValue", "SHREE GURUKUL SCHOOL");
        parameters.put("CompleteAddressValue", "Chichalay Road, Behind Govt. College, Kasrawad (M.P.)");
        parameters.put("ReportBook", "Report Card - " + assessmentTerm.getName());
        parameters.put("Session", "Session:");
        parameters.put("SessionValue", "Session " + student.getAcademicYear().getName());
        parameters.put("AdmissionNo", "Scholor ID");
        parameters.put("AdmissionNoValue", student.getStudentId());
        parameters.put("DOB", "Date of Birth");
        parameters.put("DOBValue", "");
        if (student.getDob() != null) {
            parameters.put("DOBValue", DateUtil.formatDate(student.getDob(), "dd-MMM-yyyy"));
        }
        parameters.put("NameOfStudent", "Student Name");
        parameters.put("NameOfStudentValue", "");
        if ("male".equals(student.getGender())) {
            parameters.put("NameOfStudentValue", student.getFullName() + " S/O " + student.getFatherName());
            parameters.put("GenderValue", "Male");
        } else if ("female".equals(student.getGender())) {
            parameters.put("NameOfStudentValue", student.getFullName() + " D/O " + student.getFatherName());
            parameters.put("GenderValue", "Female");
        } else {
            parameters.put("NameOfStudentValue", student.getFullName());
            parameters.put("GenderValue", "");
        }
        parameters.put("Class", "Class");
        parameters.put("ClassValue", student.getAcademicYearClass().getDisplayName());
        parameters.put("Gender", "Gender");
        parameters.put("sectionText", "Section");
        parameters.put("Section", "");
        if (student.getClassSection() != null) {
            parameters.put("Section", student.getClassSection().getSectionName());
        }
        parameters.put("AffiliationNo", "CBSE Affiliation No.");
        parameters.put("AffiliationNoValue", "1030855");
        parameters.put("totWorkDaysText", "Total Working Days");
        parameters.put("totWorkDaysValue", "");
        parameters.put("attOfStdText", "Attendance of Student");
        parameters.put("attOfStdVale", "");
        parameters.put("subText", "Subject");
        parameters.put("term1Text", assessmentTerm.getName());
        parameters.put("FA1Text", ((AssessmentName) assessmentTerm.getAssessmentNames().get(0)).getName());
        parameters.put("FA2Text", ((AssessmentName) assessmentTerm.getAssessmentNames().get(1)).getName());
        parameters.put("SA1Text", ((AssessmentName) assessmentTerm.getAssessmentNames().get(2)).getName());
        parameters.put("FA1FA2SA1Text", ((AssessmentName) assessmentTerm.getAssessmentNames().get(3)).getName());
        if (!(scoreCard == null || scoreCard.getScholasticAssessment() == null || scoreCard.getScholasticAssessment().getAssessmentTerms() == null)) {
            EvaluationTerm evaluationTerm = this.evaluationTermService.getEvaluationTerm(((AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(0)).getId());
            if (!(evaluationTerm == null || evaluationTerm.getWorkingDays() == null)) {
                parameters.put("totWorkDaysValue", evaluationTerm.getWorkingDays().toString());
                StudentAttendance studentAttendance = this.studentAttendanceService.getStudentAttendance(student.getId(), student.getAcademicYearClass().getId(), evaluationTerm.getId());
                if (!(studentAttendance == null || studentAttendance.getAttendance() == null)) {
                    parameters.put("attOfStdVale", studentAttendance.getAttendance().toString());
                }
            }
        }
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource((ArrayList) new MarksheetUtil().getMarksheetForTerm1Only(scoreCard.getScholasticAssessment().getAssessmentSubjects()));
        parameters.put("ClassTeacher", "Class Teacher");
        parameters.put("Principal", "Principal");
        parameters.put("Parent", "Parents");
        try {
            FileInputStream jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("marksheet_term1.jrxml").getFile()));
            try {
                JasperPrint jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(jasperStream), parameters, beanColDataSource);
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=Term_1_Score_Card" + student.getStudentId() + ".pdf");
                JRPdfExporter exporter = new JRPdfExporter();
                OutputStream outStream = response.getOutputStream();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
                exporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, "this.print({bUI: true,bSilent: true,bShrinkToFit: true});");
                exporter.exportReport();
                outStream.flush();
                outStream.close();
            } catch (JRException e3) {
                e = e3;
                fileInputStream = jasperStream;
            } catch (Exception e4) {
                e2 = e4;
                fileInputStream = jasperStream;
                e2.printStackTrace();
            }
        } catch (Exception e6) {
            e2 = e6;
            e2.printStackTrace();
        }
    }
}
