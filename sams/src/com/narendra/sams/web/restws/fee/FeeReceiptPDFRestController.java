package com.narendra.sams.web.restws.fee;

import com.narendra.sams.admission.domain.FeeTransaction;
import com.narendra.sams.admission.domain.FeeTransactionDetail;
import com.narendra.sams.core.domain.FeeSettings;
import com.narendra.sams.core.util.AmountInWords;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.fee.service.PaidFeeService;
import com.narendra.sams.web.auth.ApplicationCacheManager;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.fee.vo.FeeReceiptDataBean;
import com.narendra.sams.web.utils.LogoNameUtil;
import com.narendra.sams.web.utils.StudentInformationUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
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
@RequestMapping({"/ws/feereceipt"})
public class FeeReceiptPDFRestController {
    @Autowired
    private PaidFeeService paidFeeService;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/print/{transactionId}"})
    public void printReceipt(@PathVariable Long transactionId, HttpServletResponse response) {
        FeeTransaction dbFeeTransaction = this.paidFeeService.getFeeTransaction(transactionId);
        Map<String, Object> parameters = new HashMap();
        String imagePath = LogoNameUtil.getImagePath();
        if (imagePath != null) {
            parameters.put("imagePath", this.servletContext.getRealPath(imagePath));
        } else {
            parameters.put("imagePath", null);
        }
        parameters.put("Receipt_Header", dbFeeTransaction.getFeeRecieptHeader().getHeader());
        parameters.put("Receipt_Sub_Header", dbFeeTransaction.getFeeRecieptHeader().getSubHeader());
        if (dbFeeTransaction.getPaymentDate() != null) {
            parameters.put("Payment_Date", DateUtil.formatDate(dbFeeTransaction.getPaymentDate(), "dd-MMM-yyyy"));
        } else {
            parameters.put("Payment_Date", "");
        }
        String studentName = StudentInformationUtil.getFullName(dbFeeTransaction.getStudent().getFirstName(), dbFeeTransaction.getStudent().getMiddleName(), dbFeeTransaction.getStudent().getLastName());
        if (!(dbFeeTransaction.getStudent().getFatherName() == null || dbFeeTransaction.getStudent().getFatherName().isEmpty())) {
            studentName = "male".equals(dbFeeTransaction.getStudent().getGender()) ? new StringBuilder(String.valueOf(studentName)).append(" S/O ").append(dbFeeTransaction.getStudent().getFatherName()).toString() : new StringBuilder(String.valueOf(studentName)).append(" D/O ").append(dbFeeTransaction.getStudent().getFatherName()).toString();
        }
        parameters.put("Receipt_No", dbFeeTransaction.getRecieptNo().toString());
        parameters.put("Student_ID", dbFeeTransaction.getStudent().getStudentId());
        parameters.put("Student_Name", studentName);
        parameters.put("Academic_Year", dbFeeTransaction.getAcademicYear().getName());
        parameters.put("Student_Class", StudentInformationUtil.getClassName(dbFeeTransaction.getStudentClass()));
        ArrayList<FeeReceiptDataBean> dataBeanList = new ArrayList();
        Long paidFeeSum = Long.valueOf(0);
        for (FeeTransactionDetail feeTransactionDetail : dbFeeTransaction.getFeeTransactionDetails()) {
            if (!(feeTransactionDetail == null || feeTransactionDetail.getAmount().longValue() == 0)) {
                FeeReceiptDataBean feeReceiptDataBean = new FeeReceiptDataBean();
                feeReceiptDataBean.setFeeHeadName(feeTransactionDetail.getFeeHead().getName());
                feeReceiptDataBean.setPaidAmount(feeTransactionDetail.getAmount().toString());
                dataBeanList.add(feeReceiptDataBean);
                paidFeeSum = Long.valueOf(paidFeeSum.longValue() + feeTransactionDetail.getAmount().longValue());
            }
        }
        parameters.put("Amount_in_Words", "Rs. " + AmountInWords.convertToWords(paidFeeSum.longValue()) + " only");
        parameters.put("Paid_Fee_Sum", paidFeeSum.toString());
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
        try {
            FileInputStream jasperStream;
            if (FeeSettings.RECEIPT_TYPE_DOUBLE.equals(ApplicationCacheManager.getInstituteSetting(this.webApplicationContext, UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()).getFeeSettings().getReceiptType())) {
                jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("Fee-Receipt_With_Copy.jasper").getFile()));
            } else {
                jasperStream = new FileInputStream(new File(getClass().getClassLoader().getResource("Fee-Receipt.jasper").getFile()));
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, beanColDataSource);
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline; filename=Fee_Receipt.pdf");
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

    private void print() {
    }
}
