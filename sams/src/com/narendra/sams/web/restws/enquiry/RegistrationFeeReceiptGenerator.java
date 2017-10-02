package com.narendra.sams.web.restws.enquiry;

import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.util.AmountInWords;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.enquiry.service.EnquiryService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.enquiry.vo.FormFeeBean;
import com.narendra.sams.web.utils.LogoNameUtil;
import com.narendra.sams.web.utils.StudentInformationUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/enquiry/form-receipt"})
public class RegistrationFeeReceiptGenerator {
    @Autowired
    private EnquiryService enquiryService;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{enquiryId}"})
    public void print(@PathVariable Long enquiryId, HttpServletResponse response) {
        JRException e;
        FileInputStream fileInputStream;
        Exception e2;
        Enquiry enquiry = this.enquiryService.getEnquiry(enquiryId);
        Institute institute = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute();
        Map<String, Object> parameters = new HashMap();
        String imagePath = LogoNameUtil.getImagePath();
        if (imagePath != null) {
            parameters.put("imagePath", this.servletContext.getRealPath(imagePath));
        } else {
            parameters.put("imagePath", null);
        }
        parameters.putAll(setLabels());
        parameters.putAll(setValues(enquiry));
        parameters.putAll(setEnquiryDetails(enquiry));
        parameters.putAll(setInstituteDetails(institute));
        parameters.put("DSFeeHeads1", prepareDataSource(enquiry.getFormFee()));
        parameters.put("DSFeeHeads2", prepareDataSource(enquiry.getFormFee()));
        try {
            FileInputStream jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("Form-Receipt_A4_Portrait_2_Copy.jasper").getFile()));
            try {
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, new JREmptyDataSource());
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=Form-Fee-Receipt" + enquiryId + ".pdf");
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
        }  catch (Exception e6) {
            e2 = e6;
            e2.printStackTrace();
        }
    }

    private Map<String, String> setValues(Enquiry enquiry) {
        Map<String, String> parameters = new HashMap();
        if (enquiry.getFormFee() != null) {
            parameters.put("valPaidSum", enquiry.getFormFee().toString());
            parameters.put("valAmountInWords", "Rs. " + AmountInWords.convertToWords(enquiry.getFormFee().longValue()) + " only");
        }
        if (enquiry.getFormIssueDate() != null) {
            parameters.put("valPaymentDate", DateUtil.formatDate(enquiry.getFormIssueDate(), "dd-MMM-yyyy"));
        }
        if (enquiry.getReceiptNo() != null) {
            parameters.put("valReceiptNo", enquiry.getReceiptNo().toString());
        }
        return parameters;
    }

    private Map<String, String> setEnquiryDetails(Enquiry enquiry) {
        Map<String, String> parameters = new HashMap();
        parameters.put("valAcademicSession", enquiry.getAcademicYear().getName());
        parameters.put("valClass", "-");
        if (enquiry.getAcademicYearClass() != null) {
            parameters.put("valClass", StudentInformationUtil.getClassName(enquiry.getAcademicYearClass()));
        }
        StringBuffer studentName = new StringBuffer("");
        studentName.append(enquiry.getStudentFullName());
        if (!(enquiry.getStudentFatherName() == null || enquiry.getStudentFatherName().trim().isEmpty())) {
            if ("Male".equals(enquiry.getStudentGender())) {
                studentName.append(" S/O ");
            } else {
                studentName.append(" D/O ");
            }
            studentName.append(enquiry.getStudentFatherName());
        }
        parameters.put("valName", studentName.toString());
        return parameters;
    }

    public static Map<String, String> setInstituteDetails(Institute institute) {
        Map<String, String> parameters = new HashMap();
        parameters.put("valInstituteName", institute.getName());
        parameters.put("valLine1", institute.getAddress());
        parameters.put("valLine2", institute.getLine2());
        if (institute.getAffiliationNo() != null) {
            parameters.put("lblAffiliationNo", "C.B.S.E. Affiliation No : ");
            parameters.put("valAffiliationNo", institute.getAffiliationNo());
        }
        return parameters;
    }

    public static JRBeanCollectionDataSource prepareDataSource(Long formFee) {
        List<FormFeeBean> beans = new ArrayList();
        FormFeeBean bean1 = new FormFeeBean();
        bean1.setFeeHeadName("Registration Fee");
        if (formFee != null) {
            bean1.setPaidFee(formFee.toString());
        }
        beans.add(bean1);
        return new JRBeanCollectionDataSource(beans);
    }

    public static void main(String[] args) {
        Map<String, Object> parameters = new HashMap();
        parameters.putAll(setLabels());
        parameters.putAll(setValues());
        parameters.putAll(setEnquiryDetails());
        parameters.putAll(setInstituteName());
        parameters.put("DSFeeHeads1", prepareDataSource());
        parameters.put("DSFeeHeads2", prepareDataSource());
        try {
            JasperExportManager.exportReportToPdfFile(JasperFillManager.fillReportToFile("D:\\Narendra Private\\My-Software\\Jaspers\\Form-Receipt\\Form-Receipt_A4_Portrait_2_Copy.jasper", parameters, new JREmptyDataSource()), "D:\\Narendra Private\\My-Software\\Jaspers\\Form-Receipt\\Form-Receipt_A4_Portrait_2_Copy.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> setLabels() {
        Map<String, String> parameters = new HashMap();
        parameters.put("lblReceiptName", "Fee Receipt");
        parameters.put("lblFeeHead", "Perticulars");
        parameters.put("lblPaidAmount", "Amount (Rs.)");
        parameters.put("lblTotal", "Total");
        parameters.put("lblAmountInWords", "Amount in Words : ");
        parameters.put("lblPaymentDate", "Date : ");
        parameters.put("lblAuthorisedSignatory", "(Authorised Signatory)");
        parameters.put("lblAffiliationNo", "C.B.S.E. Affiliation No : ");
        parameters.put("lblAcademicSession", "Academic Session");
        parameters.put("lblClass", "Class");
        parameters.put("lblName", "Student's Name");
        parameters.put("lblReceiptNo", "Receipt No");
        return parameters;
    }

    public static Map<String, String> setValues() {
        Map<String, String> parameters = new HashMap();
        parameters.put("valPaidSum", "300");
        parameters.put("valAmountInWords", "Rs. Three Hundred Only");
        parameters.put("valPaymentDate", "Apr-22-2017");
        parameters.put("valReceiptNo", "1234");
        return parameters;
    }

    public static Map<String, String> setEnquiryDetails() {
        Map<String, String> parameters = new HashMap();
        parameters.put("valClass", "Nursery");
        parameters.put("valName", "Madhav Patidar S/O Mr. Narang Patidar");
        parameters.put("valAcademicSession", "2016-2017");
        return parameters;
    }

    public static Map<String, String> setInstituteName() {
        Map<String, String> parameters = new HashMap();
        parameters.put("valInstituteName", "KRISHNA ACADEMY");
        parameters.put("valLine1", "Teh. Maheshwar, Distt. Khargone M.P. 451225");
        parameters.put("valLine2", "Phone: 07283-223020, 8020709899");
        parameters.put("valAffiliationNo", "1030714");
        return parameters;
    }

    public static JRBeanCollectionDataSource prepareDataSource() {
        List<FormFeeBean> beans = new ArrayList();
        FormFeeBean bean1 = new FormFeeBean();
        bean1.setFeeHeadName("Registration Fee");
        bean1.setPaidFee("300");
        beans.add(bean1);
        return new JRBeanCollectionDataSource(beans);
    }
}
