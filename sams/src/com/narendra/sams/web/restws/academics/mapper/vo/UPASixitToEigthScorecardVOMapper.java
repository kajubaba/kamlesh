package com.narendra.sams.web.restws.academics.mapper.vo;

import com.narendra.sams.web.restws.academics.vo.UPAFooterBean;
import com.narendra.sams.web.restws.academics.vo.UPASixthToEigthCoScholasticBean;
import com.narendra.sams.web.restws.academics.vo.UPASixthToEigthScholasticBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class UPASixitToEigthScorecardVOMapper {
    public static void main(String[] args) {
        Map<String, Object> parameters = new HashMap();
        parameters.putAll(setHeaderParameters("SARDAR PATEL INTERNATIONAL SCHOOL", "Choli Road Mandleshwar", "2017-2018"));
        parameters.putAll(setStduentSectionParameters());
        parameters.putAll(setClass9ScholasticParameters());
        parameters.putAll(setClass6To8ScholasticParameters());
        parameters.put("DSHeader", new JREmptyDataSource());
        parameters.put("DSStudentSection", new JREmptyDataSource());
        parameters.put("DSScholastic", prepapreScholasticDS());
        parameters.put("DSCoScholastic", prepapreCoScholasticDS());
        parameters.put("DSFooter", prepapreFooterDS());
        prepareFullScoreCard(parameters);
    }

    public static void prepareTerm1ScoreCard(Map<String, Object> parameters) {
        try {
            JasperExportManager.exportReportToPdfFile(JasperFillManager.fillReportToFile("D:\\Narendra Private\\My-Software\\Jaspers\\UPA\\Scorecrd_9_A4.jasper", parameters, new JREmptyDataSource()), "D:\\Narendra Private\\My-Software\\Jaspers\\UPA\\9_scorecard.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void prepareFullScoreCard(Map<String, Object> parameters) {
        try {
            JasperExportManager.exportReportToPdfFile(JasperFillManager.fillReportToFile("D:\\Narendra Private\\My-Software\\Jaspers\\UPA\\Scorecrd_6_8_A4.jasper", parameters, new JREmptyDataSource()), "D:\\Narendra Private\\My-Software\\Jaspers\\UPA\\6_8_scorecard.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> setHeaderParameters(String schoolName, String addressLine1, String academicSession) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("schoolName", schoolName);
        parameters.put("schoolAddressLine1", addressLine1);
        parameters.put("academicSession", academicSession);
        parameters.put("cbseLogoPath", null);
        parameters.put("schoolLogoPath", null);
        return parameters;
    }

    public static Map<String, Object> setStduentSectionParameters() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("rollNo", "1234");
        parameters.put("studentName", "Aarava Sharma");
        parameters.put("fatherName", "Gaurav Sharma");
        parameters.put("motherName", "Priya Sharma");
        parameters.put("dateOfBirth", "01-01-2014");
        parameters.put("calssSection", "VI - A");
        return parameters;
    }

    public static Map<String, Object> setClass9ScholasticParameters() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("subjectName", "Subject Name");
        parameters.put("assessment1", "Periodic Test");
        parameters.put("assessment2", "Note Book");
        parameters.put("assessment3", "Subject Enrichment");
        parameters.put("assessment4", "Annual Examination");
        parameters.put("marksObtained", "Marks Obtained");
        parameters.put("grade", "Grade");
        parameters.put("termName", "Academic Year (100 marks)");
        parameters.put("assessment1Marks", "10");
        parameters.put("assessment2Marks", "5");
        parameters.put("assessment3Marks", "5");
        parameters.put("assessment4Marks", "80");
        parameters.put("marksObtainedMarks", "100");
        return parameters;
    }

    public static Map<String, Object> setClass6To8ScholasticParameters() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("term1", "Term-1(100 marks)");
        parameters.put("term2", "Term-2(100 marks)");
        parameters.put("grade", "Grade");
        parameters.put("subjectName", "Subject Name");
        parameters.put("term1Assessment1", "Per Test");
        parameters.put("term1Assessment2", "Note Book");
        parameters.put("term1Assessment3", "Subject Enrichment");
        parameters.put("term1Assessment4", "Half Yearly Exam");
        parameters.put("term1MarksObtained", "Marks Obtained");
        parameters.put("term1Assessment1Marks", "10");
        parameters.put("term1Assessment2Marks", "5");
        parameters.put("term1Assessment3Marks", "5");
        parameters.put("term1Assessment4Marks", "80");
        parameters.put("term1MarksObtainedMarks", "100");
        parameters.put("term2Assessment1", "Per Test");
        parameters.put("term2Assessment2", "Note Book");
        parameters.put("term2Assessment3", "Subject Enrichment");
        parameters.put("term2Assessment4", "Yearly Exam");
        parameters.put("term2MarksObtained", "Marks Obtained");
        parameters.put("term2Assessment1Marks", "10");
        parameters.put("term2Assessment2Marks", "5");
        parameters.put("term2Assessment3Marks", "5");
        parameters.put("term2Assessment4Marks", "80");
        parameters.put("term2MarksObtainedMarks", "100");
        return parameters;
    }

    public static JRBeanCollectionDataSource prepapreScholasticDS() {
        List<UPASixthToEigthScholasticBean> beans = new ArrayList();
        for (int i = 0; i < 5; i++) {
            beans.add(new UPASixthToEigthScholasticBean("Subject-" + i, "8", "4", "3", "70", "85", "A", "8", "4", "3", "70", "85", "A"));
        }
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepapreCoScholasticDS() {
        List<UPASixthToEigthCoScholasticBean> beans = new ArrayList();
        UPASixthToEigthCoScholasticBean bean1 = new UPASixthToEigthCoScholasticBean("Co-Scholastic Areas:[on a 3-point (A-C) grading scale", "Work Education (or Pre-vocational Education)", "A", "B");
        UPASixthToEigthCoScholasticBean bean2 = new UPASixthToEigthCoScholasticBean("Co-Scholastic Areas:[on a 3-point (A-C) grading scale", "Art Education", "A", "B");
        UPASixthToEigthCoScholasticBean bean3 = new UPASixthToEigthCoScholasticBean("Co-Scholastic Areas:[on a 3-point (A-C) grading scale", "Health & Physical Education", "A", "B");
        UPASixthToEigthCoScholasticBean bean4 = new UPASixthToEigthCoScholasticBean("Discipline::[on a 3-point (A-C) grading scale", "Discipline", "A", "B");
        beans.add(bean1);
        beans.add(bean2);
        beans.add(bean3);
        beans.add(bean4);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepapreFooterDS() {
        List<UPAFooterBean> beans = new ArrayList();
        UPAFooterBean bean1 = new UPAFooterBean("91-100", "A 1");
        UPAFooterBean bean2 = new UPAFooterBean("81-90", "A 2");
        UPAFooterBean bean3 = new UPAFooterBean("71-80", "B 1");
        UPAFooterBean bean4 = new UPAFooterBean("61-70", "B 2");
        UPAFooterBean bean5 = new UPAFooterBean("51-60", "C 1");
        UPAFooterBean bean6 = new UPAFooterBean("41-50", "C 2");
        UPAFooterBean bean7 = new UPAFooterBean("33-40", "D");
        UPAFooterBean bean8 = new UPAFooterBean("32 & Below", "E (Needs improvement)");
        beans.add(bean1);
        beans.add(bean2);
        beans.add(bean3);
        beans.add(bean4);
        beans.add(bean5);
        beans.add(bean6);
        beans.add(bean7);
        beans.add(bean8);
        return new JRBeanCollectionDataSource(beans);
    }
}
