package com.narendra.sams.web.restws.academics;

import com.narendra.sams.academics.domain.AssessmentName;
import com.narendra.sams.academics.domain.AssessmentTerm;
import com.narendra.sams.academics.domain.ScoreCard;
import com.narendra.sams.academics.domain.StudentAttendance;
import com.narendra.sams.academics.exam.domain.EvaluationScheme;
import com.narendra.sams.academics.exam.domain.EvaluationTerm;
import com.narendra.sams.academics.exam.domain.StudentRollNo;
import com.narendra.sams.academics.service.EvaluationTermService;
import com.narendra.sams.academics.service.ExamPatternClassService;
import com.narendra.sams.academics.service.ScoreCardService;
import com.narendra.sams.academics.service.StudentAttendanceService;
import com.narendra.sams.academics.service.StudentRollNoService;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.service.AdmissionListService;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.academics.mapper.vo.SGKLScoreCardVOMapper;
import com.narendra.sams.web.restws.academics.mapper.vo.SPISFirstToFifthScorecardVOMapper;
import com.narendra.sams.web.restws.academics.mapper.vo.SPISScorecardVOMapper;
import com.narendra.sams.web.restws.academics.mapper.vo.SPISSixToEigthScorecardVOMapper;
import com.narendra.sams.web.restws.academics.utils.MarksheetUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/academics/student/scorecard/pdf"})
public class StudentPDFScorecardRestController {
    @Autowired
    private AdmissionListService admissionListService;
    @Autowired
    private EvaluationTermService evaluationTermService;
    @Autowired
    private ExamPatternClassService examPatternClassService;
    @Autowired
    private ScoreCardService scoreCardService;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private StudentAttendanceService studentAttendanceService;
    @Autowired
    private StudentRollNoService studentRollNoService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{studentId}"})
    public void pdfScoreCard(@PathVariable Long studentId, Long evaluationTermId, HttpServletResponse response) {
        JRException e;
        FileInputStream fileInputStream;
        Exception e2;
        Student student = this.studentService.getStudentById(studentId);
        ScoreCard scoreCard = this.scoreCardService.getStudentScholasticScoreCardOfOneTerm(studentId, student.getAcademicYearClass().getId(), evaluationTermId);
        Map<String, Object> parameters = new HashMap();
        setLabelParameters(parameters);
        setValueParameters(parameters, student, scoreCard);
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource((ArrayList) new MarksheetUtil().getMarksheetForTerm1Only(scoreCard.getScholasticAssessment().getAssessmentSubjects()));
        try {
            FileInputStream jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("cce_scholastic_individual_term.jasper").getFile()));
            try {
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, beanColDataSource);
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=Term_1_Score_Card" + student.getStudentId() + ".pdf");
                OutputStream outStream = response.getOutputStream();
                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));
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

    private Map<String, Object> prepareSGKLScorecardParameters(Student student, Long classId) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("imagePath", this.servletContext.getRealPath("/resources/logo/school_logo.jpeg"));
        Institute institute = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute();
        parameters.put("studentImage", "");
        if (student.getImageName() != null) {
            parameters.put("studentImage", this.servletContext.getRealPath("/resources/studentpics/" + student.getImageName()));
        }
        ScoreCard scoreCard = this.scoreCardService.getStudentScoreCard(student.getId(), classId);
        StudentRollNo studentRollNo = this.studentRollNoService.getStudentRollNo(student.getId(), classId);
        String rollNo = null;
        if (!(studentRollNo == null || studentRollNo.getRollNo() == null)) {
            rollNo = studentRollNo.getRollNo();
        }
        parameters.put("term1WorkingDays", "NA");
        parameters.put("term2WorkingDays", "NA");
        parameters.put("term1Attendance", "NA");
        parameters.put("term2Attendance", "NA");
        if (!(scoreCard == null || scoreCard.getScholasticAssessment() == null || scoreCard.getScholasticAssessment().getAssessmentTerms() == null)) {
            EvaluationTerm term1 = this.evaluationTermService.getEvaluationTerm(((AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(0)).getId());
            if (!(term1 == null || term1.getWorkingDays() == null)) {
                parameters.put("term1WorkingDays", term1.getWorkingDays().toString());
            }
            StudentAttendance studentAttendance1 = this.studentAttendanceService.getStudentAttendance(student.getId(), classId, term1.getId());
            if (!(studentAttendance1 == null || studentAttendance1.getAttendance() == null)) {
                parameters.put("term1Attendance", studentAttendance1.getAttendance().toString());
            }
            EvaluationTerm term2 = this.evaluationTermService.getEvaluationTerm(((AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(1)).getId());
            if (!(term2 == null || term2.getWorkingDays() == null)) {
                parameters.put("term2WorkingDays", term2.getWorkingDays().toString());
            }
            StudentAttendance studentAttendance2 = this.studentAttendanceService.getStudentAttendance(student.getId(), classId, term2.getId());
            if (!(studentAttendance2 == null || studentAttendance2.getAttendance() == null)) {
                parameters.put("term2Attendance", studentAttendance2.getAttendance().toString());
            }
        }
        parameters.putAll(SGKLScoreCardVOMapper.prepareScorecard(scoreCard, institute, student, classId, rollNo));
        return parameters;
    }

    private Map<String, Object> prepareSPISScorecardParameters(Student student, Long classId) {
        StudentAttendance studentAttendance;
        StudentRollNo studentRollNo = this.studentRollNoService.getStudentRollNo(student.getId(), classId);
        String rollNo = null;
        if (!(studentRollNo == null || studentRollNo.getRollNo() == null)) {
            rollNo = studentRollNo.getRollNo();
        }
        ScoreCard scoreCard = this.scoreCardService.getStudentScoreCard(student.getId(), classId);
        EvaluationTerm term1 = this.evaluationTermService.getEvaluationTerm(((AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(0)).getId());
        String term1WorkingDays = null;
        String term2WorkingDays = null;
        String term1StudentAttendance = null;
        String term2StudentAttendance = null;
        if (!(term1 == null || term1.getWorkingDays() == null)) {
            term1WorkingDays = term1.getWorkingDays().toString();
            studentAttendance = this.studentAttendanceService.getStudentAttendance(student.getId(), classId, term1.getId());
            if (!(studentAttendance == null || studentAttendance.getAttendance() == null)) {
                term1StudentAttendance = studentAttendance.getAttendance().toString();
            }
        }
        EvaluationTerm term2 = this.evaluationTermService.getEvaluationTerm(((AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(1)).getId());
        if (!(term2 == null || term2.getWorkingDays() == null)) {
            term2WorkingDays = term2.getWorkingDays().toString();
            studentAttendance = this.studentAttendanceService.getStudentAttendance(student.getId(), classId, term2.getId());
            if (!(studentAttendance == null || studentAttendance.getAttendance() == null)) {
                term2StudentAttendance = studentAttendance.getAttendance().toString();
            }
        }
        return SPISScorecardVOMapper.prepareScoreCard(scoreCard, student, classId, rollNo, term1WorkingDays, term2WorkingDays, term1StudentAttendance, term2StudentAttendance);
    }

    private Map<String, Object> prepareSPISFirstToFifthScorecardParameters(String scorecardFor, Student student, Long classId) {
        StudentAttendance studentAttendance;
        StudentRollNo studentRollNo = this.studentRollNoService.getStudentRollNo(student.getId(), classId);
        String rollNo = null;
        if (!(studentRollNo == null || studentRollNo.getRollNo() == null)) {
            rollNo = studentRollNo.getRollNo();
        }
        ScoreCard scoreCard = this.scoreCardService.getStudentScoreCard(student.getId(), classId);
        String term1WorkingDays = null;
        String term2WorkingDays = null;
        String term3WorkingDays = null;
        String term1StudentAttendance = null;
        String term2StudentAttendance = null;
        String term3StudentAttendance = null;
        EvaluationTerm term1 = this.evaluationTermService.getEvaluationTerm(((AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(0)).getId());
        if (!(term1 == null || term1.getWorkingDays() == null)) {
            term1WorkingDays = term1.getWorkingDays().toString();
            studentAttendance = this.studentAttendanceService.getStudentAttendance(student.getId(), classId, term1.getId());
            if (!(studentAttendance == null || studentAttendance.getAttendance() == null)) {
                term1StudentAttendance = studentAttendance.getAttendance().toString();
            }
        }
        EvaluationTerm term2 = this.evaluationTermService.getEvaluationTerm(((AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(1)).getId());
        if (!(term2 == null || term2.getWorkingDays() == null)) {
            term2WorkingDays = term2.getWorkingDays().toString();
            studentAttendance = this.studentAttendanceService.getStudentAttendance(student.getId(), classId, term2.getId());
            if (!(studentAttendance == null || studentAttendance.getAttendance() == null)) {
                term2StudentAttendance = studentAttendance.getAttendance().toString();
            }
        }
        EvaluationTerm term3 = this.evaluationTermService.getEvaluationTerm(((AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(2)).getId());
        if (!(term3 == null || term3.getWorkingDays() == null)) {
            term3WorkingDays = term3.getWorkingDays().toString();
            studentAttendance = this.studentAttendanceService.getStudentAttendance(student.getId(), classId, term3.getId());
            if (!(studentAttendance == null || studentAttendance.getAttendance() == null)) {
                term3StudentAttendance = studentAttendance.getAttendance().toString();
            }
        }
        return SPISFirstToFifthScorecardVOMapper.prepareScoreCard(scorecardFor, scoreCard, student, classId, rollNo, term1WorkingDays, term2WorkingDays, term3WorkingDays, term1StudentAttendance, term2StudentAttendance, term3StudentAttendance);
    }

    private Map<String, Object> prepareSPISSixToEigthScorecardParameters(Student student, Long classId) {
        StudentAttendance studentAttendance;
        StudentRollNo studentRollNo = this.studentRollNoService.getStudentRollNo(student.getId(), student.getAcademicYearClass().getId());
        String rollNo = null;
        if (!(studentRollNo == null || studentRollNo.getRollNo() == null)) {
            rollNo = studentRollNo.getRollNo();
        }
        ScoreCard scoreCard = this.scoreCardService.getStudentScoreCard(student.getId(), classId);
        String term1WorkingDays = null;
        String term2WorkingDays = null;
        String term3WorkingDays = null;
        String term1StudentAttendance = null;
        String term2StudentAttendance = null;
        String term3StudentAttendance = null;
        EvaluationTerm term1 = this.evaluationTermService.getEvaluationTerm(((AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(0)).getId());
        if (!(term1 == null || term1.getWorkingDays() == null)) {
            term1WorkingDays = term1.getWorkingDays().toString();
            studentAttendance = this.studentAttendanceService.getStudentAttendance(student.getId(), classId, term1.getId());
            if (!(studentAttendance == null || studentAttendance.getAttendance() == null)) {
                term1StudentAttendance = studentAttendance.getAttendance().toString();
            }
        }
        EvaluationTerm term2 = this.evaluationTermService.getEvaluationTerm(((AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(1)).getId());
        if (!(term2 == null || term2.getWorkingDays() == null)) {
            term2WorkingDays = term2.getWorkingDays().toString();
            studentAttendance = this.studentAttendanceService.getStudentAttendance(student.getId(), classId, term2.getId());
            if (!(studentAttendance == null || studentAttendance.getAttendance() == null)) {
                term2StudentAttendance = studentAttendance.getAttendance().toString();
            }
        }
        EvaluationTerm term3 = this.evaluationTermService.getEvaluationTerm(((AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(2)).getId());
        if (!(term3 == null || term3.getWorkingDays() == null)) {
            term3WorkingDays = term3.getWorkingDays().toString();
            studentAttendance = this.studentAttendanceService.getStudentAttendance(student.getId(), classId, term3.getId());
            if (!(studentAttendance == null || studentAttendance.getAttendance() == null)) {
                term3StudentAttendance = studentAttendance.getAttendance().toString();
            }
        }
        return SPISSixToEigthScorecardVOMapper.prepareScoreCard(scoreCard, student, classId, rollNo, term1WorkingDays, term2WorkingDays, term3WorkingDays, term1StudentAttendance, term2StudentAttendance, term3StudentAttendance);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/full/{studentId}"})
    public void fullPDFScoreCard(@PathVariable Long studentId, HttpServletResponse response) {
        Student student = this.studentService.getStudentById(studentId);
        Map<String, Object> parameters = null;
        String deployedOn = System.getenv("ENV");
        FileInputStream jasperStream = null;
        if ("SGRKL".equals(deployedOn)) {
            parameters = prepareSGKLScorecardParameters(student, student.getAcademicYearClass().getId());
            try {
                jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("cbse/sgkl/ScoreCard.jasper").getFile()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if ("SPIS".equals(deployedOn)) {
            EvaluationScheme evaluationScheme = this.examPatternClassService.getExamPatternOfClass(student.getAcademicYearClass().getId());
            if ("NUR-UKG".equals(evaluationScheme.getScorecardTemplate())) {
                try {
                    jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("cbse/spis/Nur-UKG/Nur-LKG-Scoreacrd.jasper").getFile()));
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }
                parameters = prepareSPISScorecardParameters(student, student.getAcademicYearClass().getId());
            }
            if ("I-II".equals(evaluationScheme.getScorecardTemplate())) {
                try {
                    jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("cbse/spis/I-II/I-II-Scoreacrd.jasper").getFile()));
                } catch (FileNotFoundException e22) {
                    e22.printStackTrace();
                }
                parameters = prepareSPISFirstToFifthScorecardParameters(evaluationScheme.getScorecardTemplate(), student, student.getAcademicYearClass().getId());
            }
            if ("III-V".equals(evaluationScheme.getScorecardTemplate())) {
                try {
                    jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("cbse/spis/III-V/III-V-Scoreacrd.jasper").getFile()));
                } catch (FileNotFoundException e222) {
                    e222.printStackTrace();
                }
                parameters = prepareSPISFirstToFifthScorecardParameters(evaluationScheme.getScorecardTemplate(), student, student.getAcademicYearClass().getId());
            }
            if ("VI-VIII".equals(evaluationScheme.getScorecardTemplate())) {
                try {
                    jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("cbse/spis/VI-VIII/VI-VIII-Scoreacrd.jasper").getFile()));
                } catch (FileNotFoundException e2222) {
                    e2222.printStackTrace();
                }
                parameters = prepareSPISSixToEigthScorecardParameters(student, student.getAcademicYearClass().getId());
            }
            parameters.put("imagePath", this.servletContext.getRealPath("/resources/logo/spis-school-logo.jpg"));
        }
        writeJasperReport(jasperStream, parameters, response);
    }

    private void writeJasperReport(FileInputStream fis, Map<String, Object> parameters, HttpServletResponse response) {
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(fis, parameters, new JREmptyDataSource());
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline; filename=Scorecard.pdf");
            OutputStream outStream = response.getOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));
            exporter.exportReport();
            outStream.flush();
            outStream.close();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void sgklScorecardMethod(Long studentId, HttpServletResponse response) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("imagePath", this.servletContext.getRealPath("/resources/logo/school_logo.jpeg"));
        Institute institute = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute();
        Student student = this.studentService.getStudentById(studentId);
        parameters.put("studentImage", "");
        if (student.getImageName() != null) {
            parameters.put("studentImage", this.servletContext.getRealPath("/resources/studentpics/" + student.getImageName()));
        }
        ScoreCard scoreCard = this.scoreCardService.getStudentScoreCard(studentId, student.getAcademicYearClass().getId());
        StudentRollNo studentRollNo = this.studentRollNoService.getStudentRollNo(studentId, student.getAcademicYearClass().getId());
        String rollNo = null;
        if (!(studentRollNo == null || studentRollNo.getRollNo() == null)) {
            rollNo = studentRollNo.getRollNo();
        }
        SGKLScoreCardVOMapper.prepareScorecard(scoreCard, institute, student, student.getAcademicYearClass().getId(), rollNo);
        if (!(scoreCard == null || scoreCard.getScholasticAssessment() == null || scoreCard.getScholasticAssessment().getAssessmentTerms() == null)) {
            StudentAttendance studentAttendance;
            EvaluationTerm term1 = this.evaluationTermService.getEvaluationTerm(((AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(0)).getId());
            if (!(term1 == null || term1.getWorkingDays() == null)) {
                parameters.put("term1WorkingDays", term1.getWorkingDays().toString());
                studentAttendance = this.studentAttendanceService.getStudentAttendance(student.getId(), student.getAcademicYearClass().getId(), term1.getId());
                if (!(studentAttendance == null || studentAttendance.getAttendance() == null)) {
                    parameters.put("term1Attendance", studentAttendance.getAttendance().toString());
                }
            }
            EvaluationTerm term2 = this.evaluationTermService.getEvaluationTerm(((AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(1)).getId());
            if (!(term2 == null || term2.getWorkingDays() == null)) {
                parameters.put("term2WorkingDays", term2.getWorkingDays().toString());
                studentAttendance = this.studentAttendanceService.getStudentAttendance(student.getId(), student.getAcademicYearClass().getId(), term2.getId());
                if (!(studentAttendance == null || studentAttendance.getAttendance() == null)) {
                    parameters.put("term2Attendance", studentAttendance.getAttendance().toString());
                }
            }
        }
        FileInputStream jasperStream = null;
        try {
            jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("cbse/sgkl/ScoreCard.jasper").getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writeJasperReport(jasperStream, parameters, response);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{classId}/{evaluationTermId}"})
    public void classScorecards(@PathVariable Long classId, @PathVariable Long evaluationTermId, Long sectionId, HttpServletResponse response) {
        String deployedOn = System.getenv("ENV");
        List<ClassHistory> classHistories = this.admissionListService.getAdmissionsByClass(classId, StudentStatus.CONFIRMED, null);
        List<JasperPrint> jasperPrintList = new ArrayList();
        for (ClassHistory classHistory : classHistories) {
            FileInputStream jasperStream = null;
            JasperPrint jasperPrint = null;
            Student student = classHistory.getStudent();
            if (evaluationTermId.longValue() == -1) {
                Map<String, Object> parameters = null;
                if ("SGRKL".equals(deployedOn)) {
                    parameters = prepareSGKLScorecardParameters(student, classId);
                    try {
                        jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("cbse/sgkl/ScoreCard.jasper").getFile()));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if ("SPIS".equals(deployedOn)) {
                    EvaluationScheme evaluationScheme = this.examPatternClassService.getExamPatternOfClass(classId);
                    if ("NUR-UKG".equals(evaluationScheme.getScorecardTemplate())) {
                        try {
                            jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("cbse/spis/Nur-UKG/Nur-LKG-Scoreacrd.jasper").getFile()));
                        } catch (FileNotFoundException e2) {
                            e2.printStackTrace();
                        }
                        parameters = prepareSPISScorecardParameters(student, classId);
                    }
                    if ("I-II".equals(evaluationScheme.getScorecardTemplate())) {
                        try {
                            jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("cbse/spis/I-II/I-II-Scoreacrd.jasper").getFile()));
                        } catch (FileNotFoundException e22) {
                            e22.printStackTrace();
                        }
                        parameters = prepareSPISFirstToFifthScorecardParameters(evaluationScheme.getScorecardTemplate(), student, classId);
                    }
                    if ("III-V".equals(evaluationScheme.getScorecardTemplate())) {
                        try {
                            jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("cbse/spis/III-V/III-V-Scoreacrd.jasper").getFile()));
                        } catch (FileNotFoundException e222) {
                            e222.printStackTrace();
                        }
                        parameters = prepareSPISFirstToFifthScorecardParameters(evaluationScheme.getScorecardTemplate(), student, classId);
                    }
                    if ("VI-VIII".equals(evaluationScheme.getScorecardTemplate())) {
                        try {
                            jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("cbse/spis/VI-VIII/VI-VIII-Scoreacrd.jasper").getFile()));
                        } catch (FileNotFoundException e2222) {
                            e2222.printStackTrace();
                        }
                        parameters = prepareSPISSixToEigthScorecardParameters(student, student.getAcademicYearClass().getId());
                    }
                    parameters.put("imagePath", this.servletContext.getRealPath("/resources/logo/spis-school-logo.jpg"));
                }
                try {
                    jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, new JREmptyDataSource());
                } catch (JRException e3) {
                    e3.printStackTrace();
                }
                jasperPrintList.add(jasperPrint);
            }
        }
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline; filename=class-scorecard.pdf");
            OutputStream outStream = response.getOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            configuration.setCreatingBatchModeBookmarks(Boolean.valueOf(true));
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            outStream.flush();
            outStream.close();
        } catch (JRException e32) {
            e32.printStackTrace();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    private JasperPrint prepareJasperPrint(Student student) {
        ScoreCard scoreCard = this.scoreCardService.getStudentScoreCard(student.getId(), student.getAcademicYearClass().getId());
        Map<String, Object> parameters = new HashMap();
        parameters.put("imagePath", this.servletContext.getRealPath("/resources/logo/school_logo.jpeg"));
        parameters.put("studentImage", "");
        if (student.getImageName() != null) {
            parameters.put("studentImage", this.servletContext.getRealPath("/resources/studentpics/" + student.getImageName()));
        }
        String rollNo = null;
        StudentRollNo studentRollNo = this.studentRollNoService.getStudentRollNo(student.getId(), student.getAcademicYearClass().getId());
        if (!(studentRollNo == null || studentRollNo.getRollNo() == null)) {
            rollNo = studentRollNo.getRollNo();
        }
        SGKLScoreCardVOMapper.prepareScorecard(scoreCard, UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute(), student, student.getAcademicYearClass().getId(), rollNo);
        if (!(scoreCard == null || scoreCard.getScholasticAssessment() == null || scoreCard.getScholasticAssessment().getAssessmentTerms() == null)) {
            StudentAttendance studentAttendance;
            EvaluationTerm term1 = this.evaluationTermService.getEvaluationTerm(((AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(0)).getId());
            if (!(term1 == null || term1.getWorkingDays() == null)) {
                parameters.put("term1WorkingDays", term1.getWorkingDays().toString());
                studentAttendance = this.studentAttendanceService.getStudentAttendance(student.getId(), student.getAcademicYearClass().getId(), term1.getId());
                if (!(studentAttendance == null || studentAttendance.getAttendance() == null)) {
                    parameters.put("term1Attendance", studentAttendance.getAttendance().toString());
                }
            }
            EvaluationTerm term2 = this.evaluationTermService.getEvaluationTerm(((AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(1)).getId());
            if (!(term2 == null || term2.getWorkingDays() == null)) {
                parameters.put("term2WorkingDays", term2.getWorkingDays().toString());
                studentAttendance = this.studentAttendanceService.getStudentAttendance(student.getId(), student.getAcademicYearClass().getId(), term2.getId());
                if (!(studentAttendance == null || studentAttendance.getAttendance() == null)) {
                    parameters.put("term2Attendance", studentAttendance.getAttendance().toString());
                }
            }
        }
        FileInputStream jasperStream = null;
        try {
            jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("cbse/sgkl/ScoreCard.jasper").getFile()));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, new JREmptyDataSource());
        } catch (JRException e) {
            e.printStackTrace();
        }
        return jasperPrint;
    }

    private void setLabelParameters(Map<String, Object> parameters) {
        parameters.put("imagePath", this.servletContext.getRealPath("/resources/logo/school_logo.jpeg"));
        parameters.put("Session", "Session:");
        parameters.put("AdmissionNo", "Scholor ID");
        parameters.put("DOB", "Date of Birth");
        parameters.put("NameOfStudent", "Student Name");
        parameters.put("Class", "Class");
        parameters.put("Gender", "Gender");
        parameters.put("sectionText", "Section");
        parameters.put("AffiliationNo", "CBSE Affiliation No.");
        parameters.put("totWorkDaysText", "Total Working Days");
        parameters.put("attOfStdText", "Attendance of Student");
        parameters.put("subText", "Subject");
        parameters.put("ClassTeacher", "Class Teacher");
        parameters.put("Principal", "Principal");
        parameters.put("Parent", "Parents");
    }

    private void setValueParameters(Map<String, Object> parameters, Student student, ScoreCard scoreCard) {
        Institute institute = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute();
        AssessmentTerm assessmentTerm = (AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(0);
        parameters.put("AffiliationNoValue", institute.getAffiliationNo());
        parameters.put("NameOfSchoolValue", institute.getName());
        parameters.put("CompleteAddressValue", institute.getAddress());
        parameters.put("ReportBook", "Report Card - " + assessmentTerm.getName());
        parameters.put("SessionValue", "Session " + student.getAcademicYear().getName());
        parameters.put("AdmissionNoValue", student.getStudentId());
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
        parameters.put("DOBValue", "");
        if (student.getDob() != null) {
            parameters.put("DOBValue", DateUtil.formatDate(student.getDob(), "dd-MMM-yyyy"));
        }
        parameters.put("ClassValue", student.getAcademicYearClass().getDisplayName());
        parameters.put("Section", "");
        if (student.getClassSection() != null) {
            parameters.put("Section", student.getClassSection().getSectionName());
        }
        parameters.put("totWorkDaysValue", "");
        parameters.put("attOfStdVale", "");
        parameters.put("term1Text", assessmentTerm.getName());
        parameters.put("FA1Text", ((AssessmentName) assessmentTerm.getAssessmentNames().get(0)).getName());
        parameters.put("FA2Text", ((AssessmentName) assessmentTerm.getAssessmentNames().get(1)).getName());
        parameters.put("SA1Text", ((AssessmentName) assessmentTerm.getAssessmentNames().get(2)).getName());
        parameters.put("FA1FA2SA1Text", ((AssessmentName) assessmentTerm.getAssessmentNames().get(3)).getName());
        if (scoreCard != null && scoreCard.getScholasticAssessment() != null && scoreCard.getScholasticAssessment().getAssessmentTerms() != null) {
            EvaluationTerm evaluationTerm = this.evaluationTermService.getEvaluationTerm(((AssessmentTerm) scoreCard.getScholasticAssessment().getAssessmentTerms().get(0)).getId());
            if (evaluationTerm != null && evaluationTerm.getWorkingDays() != null) {
                parameters.put("totWorkDaysValue", evaluationTerm.getWorkingDays().toString());
                StudentAttendance studentAttendance = this.studentAttendanceService.getStudentAttendance(student.getId(), student.getAcademicYearClass().getId(), evaluationTerm.getId());
                if (studentAttendance != null && studentAttendance.getAttendance() != null) {
                    parameters.put("attOfStdVale", studentAttendance.getAttendance().toString());
                }
            }
        }
    }
}
