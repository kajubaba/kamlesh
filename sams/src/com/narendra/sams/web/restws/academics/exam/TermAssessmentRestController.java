package com.narendra.sams.web.restws.academics.exam;

import com.narendra.sams.academics.exam.domain.EvaluationTerm;
import com.narendra.sams.academics.exam.domain.TermAssessment;
import com.narendra.sams.academics.service.TermAssessmentService;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.academics.exam.form.TermAssessmentForm;
import com.narendra.sams.web.restws.academics.exam.vo.TermAssessmentVO;
import com.narendra.sams.web.restws.academics.mapper.vo.TermAssessmentVOMapper;
import com.narendra.sams.web.restws.admission.vo.AjaxSuccessResponse;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/academics/exam/term/assessment"})
public class TermAssessmentRestController {
    @Autowired
    private TermAssessmentService termAssessmentService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<TermAssessmentVO> getTermsAssessments(Long termId) {
        return TermAssessmentVOMapper.prepareTermAssessmentVOs(this.termAssessmentService.getTermAssessments(termId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxSuccessResponse saveTermAssessment(@RequestBody TermAssessmentForm termAssessmentForm) {
        TermAssessment termAssessment = new TermAssessment();
        termAssessment.setId(termAssessmentForm.getId());
        termAssessment.setName(termAssessmentForm.getName());
        termAssessment.setDisplayName(termAssessmentForm.getDisplayName());
        termAssessment.setDisplayOrder(termAssessmentForm.getDisplayOrder());
        termAssessment.setWeightageInAcademicSession(termAssessmentForm.getWeightage());
        termAssessment.setMaxMarks(termAssessmentForm.getMaxMarks());
        if (termAssessment.getId() == null) {
            EvaluationTerm evaluationTerm = new EvaluationTerm();
            evaluationTerm.setId(termAssessmentForm.getTermId());
            termAssessment.setEvaluationTerm(evaluationTerm);
            this.termAssessmentService.addTermAssessment(termAssessment, LoggedinUserAssistant.getLoggedInUserId());
        } else {
            this.termAssessmentService.updateTermAssessment(termAssessment, LoggedinUserAssistant.getLoggedInUserId());
        }
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/delete/{termAssessmentId}"})
    public AjaxSuccessResponse deleteTermAssessment(@PathVariable Long termAssessmentId) {
        this.termAssessmentService.deleteTermAssessment(termAssessmentId);
        AjaxSuccessResponse ajaxSuccessResponse = new AjaxSuccessResponse();
        ajaxSuccessResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxSuccessResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/print"})
    public void printMarksheet(HttpServletResponse response) {
        JRException e;
        FileInputStream fileInputStream;
        Exception e2;
        JRBeanCollectionDataSource marksheetDS = new JRBeanCollectionDataSource((ArrayList) new MarksheetUtil().getMarksheet());
        Map parameters = new HashMap();
        try {
            FileInputStream jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("marksheet_cce1.jrxml").getFile()));
            try {
                JasperPrint jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(jasperStream), parameters, marksheetDS);
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=marksheet_cce1.pdf");
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
