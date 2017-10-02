package com.narendra.sams.web.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PDPGenerator {
    public static void main(String[] args) throws Exception {
        JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Fee-Receipt.jrxml");
        Map<String, Object> parameters = new HashMap();
        parameters.put("Receipt_Header", "Sardar Patel International School");
        parameters.put("Receipt_Sub_Header", "Choli Road Mandleshwar");
        parameters.put("Payment_Date", "20-Mar-2016");
        parameters.put("Receipt_No", "23456");
        parameters.put("Student_ID", "SPITM20100001");
        parameters.put("Student_Name", "Narendra Patidar");
        parameters.put("Academic_Year", "2015-2016");
        parameters.put("Student_Class", "BE. Comp. Sc. 1 Yr.");
        parameters.put("Paid_Fee_Sum", "23456");
        DataBean bean1 = new DataBean();
        DataBean bean2 = new DataBean();
        DataBean bean3 = new DataBean();
        bean1.setFeeHeadName("Bus Fee");
        bean1.setPaidAmount("1234");
        bean2.setFeeHeadName("Tution Fee");
        bean2.setPaidAmount("1234");
        bean3.setFeeHeadName("Late Fee");
        bean3.setPaidAmount("1234");
        ArrayList<DataBean> dataBeanList = new ArrayList(3);
        dataBeanList.add(bean1);
        dataBeanList.add(bean2);
        dataBeanList.add(bean3);
        JasperExportManager.exportReportToPdfFile(JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(dataBeanList)), "C:\\Fee-Receipt.pdf");
    }
}
