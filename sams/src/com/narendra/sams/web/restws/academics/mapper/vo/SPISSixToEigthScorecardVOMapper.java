package com.narendra.sams.web.restws.academics.mapper.vo;

import com.narendra.sams.academics.domain.AssessmentActivity;
import com.narendra.sams.academics.domain.AssessmentCriteria;
import com.narendra.sams.academics.domain.AssessmentSkill;
import com.narendra.sams.academics.domain.AssessmentSubject;
import com.narendra.sams.academics.domain.CoScholasticAssessment;
import com.narendra.sams.academics.domain.ScoreCard;
import com.narendra.sams.admission.domain.ClassSection;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.academics.vo.FirstToFifthScholasticBean;
import com.narendra.sams.web.restws.academics.vo.NurToUKGCoExtraBean;
import com.narendra.sams.web.restws.academics.vo.NurToUKGCoScholasticBean;
import com.narendra.sams.web.restws.academics.vo.SignatureBean;
import com.narendra.sams.web.restws.academics.vo.SixToEigthCoScholasticBean;
import com.narendra.sams.web.restws.academics.vo.SixToEigthGradeBean;
import com.narendra.sams.web.restws.academics.vo.SixToEigthScholasticBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class SPISSixToEigthScorecardVOMapper {
    public static Map<String, Object> prepareScoreCard(ScoreCard scoreCard, Student student, Long classId, String rollNo, String term1WorkingDays, String term2WorkingDays, String term3WorkingDays, String term1StudentAttendance, String term2StudentAttendance, String term3StudentAttendance) {
        Map<String, Object> parameters = new HashMap();
        parameters.putAll(setScholasticLabels());
        parameters.putAll(setCoScholasticLabels());
        parameters.putAll(setSignatureLabels());
        parameters.putAll(setStudentProfileLabels());
        parameters.putAll(setStudentProfileValues(student, classId, rollNo));
        parameters.putAll(setHealthStatusAndAttendanceLabels());
        JRBeanCollectionDataSource scholasticDS = prepareSixToEighthScholasticBean(scoreCard.getScholasticAssessment().getAssessmentSubjects());
        JRBeanCollectionDataSource coscholasticDS1 = prepareSixToEighthCoScholasticBean1(scoreCard.getCoScholasticAssessment());
        JRBeanCollectionDataSource coscholasticDS2 = prepareSixToEighthCoScholasticBean2(scoreCard.getCoScholasticAssessment());
        JRBeanCollectionDataSource scholasticGradeDS = prepareSixToEighthScholasticGradeBean();
        JRBeanCollectionDataSource coscholasticGradeDS = prepareSixToEighthCoScholasticGradeBean();
        JRBeanCollectionDataSource heightAndWeightDS = setHeightAndWeight(scoreCard.getCoScholasticAssessment());
        Float cgpa = calculateCGPA(scoreCard.getScholasticAssessment().getAssessmentSubjects());
        parameters.put("cgpa", "");
        if (cgpa != null) {
            parameters.put("cgpa", String.format("%.02f", new Object[]{cgpa}));
        }
        JRBeanCollectionDataSource attendanceDS = prepareNurToUKGAttendanceBean(term1WorkingDays, term2WorkingDays, term3WorkingDays, term1StudentAttendance, term2StudentAttendance, term3StudentAttendance);
        parameters.put("DSHeader", new JREmptyDataSource());
        parameters.put("DSScholastic", scholasticDS);
        parameters.put("DS1CoScholastic", coscholasticDS1);
        parameters.put("DS2CoScholastic", null);
        parameters.put("DSScholasticGrade", scholasticGradeDS);
        parameters.put("DSCoScholasticGrade", coscholasticGradeDS);
        parameters.put("DSHeightAndWeight", heightAndWeightDS);
        parameters.put("DSAttendance", attendanceDS);
        parameters.put("DSStudentProfile", new JREmptyDataSource());
        parameters.put("promotionClass", student.getAcademicYearClass().getNextClassName());
        return parameters;
    }

    public static void main(String[] args) {
        Map<String, Object> parameters = new HashMap();
        parameters.putAll(setScholasticLabels());
        parameters.putAll(setCoScholasticLabels());
        parameters.putAll(setSignatureLabels());
        parameters.putAll(setStudentProfileLabels());
        parameters.putAll(setStudentProfileValues());
        parameters.putAll(setHealthStatusAndAttendanceLabels());
        JRBeanCollectionDataSource scholasticDS = prepareSixToEighthScholasticBean();
        JRBeanCollectionDataSource coscholasticDS1 = prepareSixToEighthCoScholasticBean1();
        JRBeanCollectionDataSource coscholasticDS2 = prepareSixToEighthCoScholasticBean1();
        JRBeanCollectionDataSource scholasticGradeDS = prepareSixToEighthScholasticGradeBean();
        JRBeanCollectionDataSource coscholasticGradeDS = prepareSixToEighthCoScholasticGradeBean();
        JRBeanCollectionDataSource heightAndWeightDS = prepareNurToUKGHeightAndweightBean();
        JRBeanCollectionDataSource attendanceDS = prepareNurToUKGAttendanceBean();
        parameters.put("DSScholasticGrade", scholasticGradeDS);
        parameters.put("DSCoScholasticGrade", coscholasticGradeDS);
        parameters.put("DSScholastic", scholasticDS);
        parameters.put("DS1CoScholastic", coscholasticDS1);
        parameters.put("DS2CoScholastic", coscholasticDS2);
        parameters.put("DSHeader", new JREmptyDataSource());
        parameters.put("DSHeightAndWeight", heightAndWeightDS);
        parameters.put("DSAttendance", attendanceDS);
        parameters.put("DSStudentProfile", new JREmptyDataSource());
        parameters.put("promotionClass", "");
        try {
            JasperExportManager.exportReportToPdfFile(JasperFillManager.fillReportToFile("C:\\SPIS-Scorecard\\VI-VIII\\VI-VIII-Scoreacrd.jasper", parameters, new JREmptyDataSource()), "C:\\SPIS-Scorecard\\VI-VIII\\ScoreCard.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> setScholasticLabels() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("part1", "PART I - ACADEMIC PERFORMANCE : SCHOLASTIC AREAS");
        parameters.put("lblOverallTerm", "Overall (Term I + Term II)");
        parameters.put("lblTerm1", "Term I");
        parameters.put("lblTerm2", "Term II");
        parameters.put("lblSubject", "Subject");
        parameters.put("lblFA1", "FA1");
        parameters.put("lblFA2", "FA2");
        parameters.put("lblFA3", "FA3");
        parameters.put("lblFA4", "FA4");
        parameters.put("lblSA1", "SA1");
        parameters.put("lblSA2", "SA2");
        parameters.put("lblTerm1Total", "Total");
        parameters.put("lblTerm2Total", "Total");
        parameters.put("lblOverallFA", "FA");
        parameters.put("lblOverallSA", "SA");
        parameters.put("lblOverallGrade", "Overall Grade");
        parameters.put("lblGP", "GP");
        return parameters;
    }

    public static Map<String, Object> setCoScholasticLabels() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("lblGrade", "Grade");
        parameters.put("lblOverallDescriptor", "Overall Descriptive Indicator");
        return parameters;
    }

    public static Map<String, Object> setSignatureLabels() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("lblSign1", "(Parents)");
        parameters.put("lblSign2", "(Class Teacher)");
        parameters.put("lblSign3", "(Principal)");
        parameters.put("lblNewSessionBeginOn", "New Session Begins On :");
        parameters.put("newSessionBeginOn", "22-Mar-2017");
        parameters.put("lblResult", "RESULT");
        parameters.put("result", "Eligible for Promotion");
        parameters.put("DSSignature", new JREmptyDataSource());
        return parameters;
    }

    public static Map<String, Object> setStudentProfileLabels() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("lblHeader", "Report Card");
        parameters.put("lbSession", "Session");
        parameters.put("lblClass", "Class");
        parameters.put("lblSection", "Class");
        parameters.put("lblAdmissionNo", "Admission No.");
        parameters.put("lblRollNo", "Roll No.");
        parameters.put("lblStudentName", "Name of Student");
        parameters.put("lblDOB", "Date of Birth");
        parameters.put("lblFatherName", "Father's Name");
        parameters.put("lblMotherName", "Mother's Name");
        parameters.put("lblContactNo", "Contact No.");
        parameters.put("lblAddress", "Address");
        return parameters;
    }

    public static Map<String, Object> setStudentProfileValues() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("valSession", "2016-2017");
        parameters.put("valClass", "Nursery");
        parameters.put("valSection", "B");
        parameters.put("valAdmissionNo", "123.");
        parameters.put("valRollNo", "12345");
        parameters.put("valStudentName", "Manish Patidar");
        parameters.put("valDOB", "Jan-01-2015");
        parameters.put("valFatherName", "Suraj Patidar");
        parameters.put("valMotherName", "Kiran Patidar");
        parameters.put("valContactNo", "1234567890");
        parameters.put("valAddress", "Indore");
        return parameters;
    }

    public static Map<String, Object> setStudentProfileValues(Student student, Long classId, String rollNo) {
        Map<String, Object> parameters = new HashMap();
        AcademicYearClass academicYearClass = student.getActiveClassByClassId(classId).getAcademicYearClass();
        ClassSection classSection = student.getActiveClassByClassId(classId).getClassSection();
        parameters.put("valSession", academicYearClass.getAcademicYear().getName());
        parameters.put("valClass", academicYearClass.getDisplayName());
        parameters.put("valSection", academicYearClass.getDisplayName());
        if (!(classSection == null || classSection.getSectionName() == null || classSection.getSectionName().trim().isEmpty())) {
            parameters.put("valSection", academicYearClass.getDisplayName() + " (" + classSection.getSectionName().trim() + ")");
        }
        parameters.put("valAdmissionNo", student.getStudentId());
        parameters.put("valRollNo", "");
        if (!(rollNo == null || rollNo.trim().isEmpty())) {
            parameters.put("valRollNo", rollNo.trim());
        }
        parameters.put("valStudentName", student.getFullName());
        parameters.put("valDOB", "");
        if (student.getDob() != null) {
            parameters.put("valDOB", DateUtil.formatDate(student.getDob(), "dd-MMM-yyyy"));
        }
        parameters.put("valFatherName", student.getFatherName());
        parameters.put("valMotherName", student.getMotherName());
        parameters.put("valContactNo", student.getPhone1());
        parameters.put("valAddress", "");
        if (!(student.getLocalAddress() == null || student.getLocalAddress().getLine1() == null || student.getLocalAddress().getLine1().trim().isEmpty())) {
            parameters.put("valAddress", student.getLocalAddress().getLine1());
        }
        return parameters;
    }

    public static Map<String, Object> setHealthStatusAndAttendanceLabels() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("healthStatus", "Health Status : ");
        parameters.put("attendance", "Attendance : ");
        return parameters;
    }

    public static JRBeanCollectionDataSource prepareSixToEighthScholasticBean() {
        List<SixToEigthScholasticBean> beans = new ArrayList();
        for (int i = 1; i < 9; i++) {
            SixToEigthScholasticBean scholasticBean = new SixToEigthScholasticBean();
            scholasticBean.setSubjectName("Subject " + i);
            scholasticBean.setFa1("A");
            scholasticBean.setFa2("A");
            scholasticBean.setFa3("A");
            scholasticBean.setFa4("A");
            scholasticBean.setSa1("A");
            scholasticBean.setSa2("A");
            scholasticBean.setTerm1Total("A");
            scholasticBean.setTerm2Total("A");
            scholasticBean.setOverallFA("A");
            scholasticBean.setOverallSA("A");
            scholasticBean.setOverall("A");
            scholasticBean.setGp("8");
            beans.add(scholasticBean);
        }
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareSixToEighthScholasticBean(List<AssessmentSubject> assessmentSubjects) {
        List<SixToEigthScholasticBean> beans = new ArrayList();
        for (AssessmentSubject assessmentSubject : assessmentSubjects) {
            SixToEigthScholasticBean scholasticBean = new SixToEigthScholasticBean();
            scholasticBean.setSubjectName(assessmentSubject.getName());
            scholasticBean.setFa1((String) assessmentSubject.getGrades().get(0));
            scholasticBean.setFa2((String) assessmentSubject.getGrades().get(1));
            scholasticBean.setSa1((String) assessmentSubject.getGrades().get(2));
            scholasticBean.setTerm1Total((String) assessmentSubject.getGrades().get(3));
            scholasticBean.setFa3((String) assessmentSubject.getGrades().get(4));
            scholasticBean.setFa4((String) assessmentSubject.getGrades().get(5));
            scholasticBean.setSa2((String) assessmentSubject.getGrades().get(6));
            scholasticBean.setTerm2Total((String) assessmentSubject.getGrades().get(7));
            scholasticBean.setOverallFA((String) assessmentSubject.getGrades().get(8));
            scholasticBean.setOverallSA((String) assessmentSubject.getGrades().get(9));
            scholasticBean.setOverall((String) assessmentSubject.getGrades().get(10));
            if (assessmentSubject.getGradePoint() == null) {
                scholasticBean.setGp("");
            } else {
                scholasticBean.setGp(assessmentSubject.getGradePoint().toString());
            }
            beans.add(scholasticBean);
        }
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareFirstToSecondScholasticBean(List<AssessmentSubject> assessmentSubjects) {
        List<FirstToFifthScholasticBean> beans = new ArrayList();
        for (AssessmentSubject assessmentSubject : assessmentSubjects) {
            beans.add(new FirstToFifthScholasticBean(assessmentSubject.getName(), (String) assessmentSubject.getGrades().get(0), (String) assessmentSubject.getGrades().get(1), (String) assessmentSubject.getGrades().get(2)));
        }
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareSixToEigthCoScholasticBeanPart(CoScholasticAssessment coScholasticAssessment) {
        return null;
    }

    public static JRBeanCollectionDataSource prepareSixToEighthCoScholasticBean1(CoScholasticAssessment coScholasticAssessment) {
        List<SixToEigthCoScholasticBean> beans = new ArrayList();
        for (AssessmentActivity assessmentActivity : coScholasticAssessment.getAssessmentActivities()) {
            for (AssessmentSkill assessmentSkill : assessmentActivity.getAssessmentSkills()) {
                if (!(assessmentSkill.getIsAdditional() == null || assessmentSkill.getIsAdditional().booleanValue())) {
                    for (AssessmentCriteria assessmentCriteria : assessmentSkill.getAssessmentCriterias()) {
                        String indcator1 = "";
                        String indcator2 = "";
                        if (assessmentCriteria.getGradeToIndicatorMap() != null) {
                            indcator1 = (String) assessmentCriteria.getGradeToIndicatorMap().get(assessmentCriteria.getGradeBasedScores().get(0));
                            indcator2 = (String) assessmentCriteria.getGradeToIndicatorMap().get(assessmentCriteria.getGradeBasedScores().get(1));
                        }
                        beans.add(new SixToEigthCoScholasticBean(assessmentActivity.getName(), assessmentCriteria.getName(), (String) assessmentCriteria.getGradeBasedScores().get(0), indcator1, (String) assessmentCriteria.getGradeBasedScores().get(1), indcator2));
                    }
                }
            }
        }
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareSixToEighthCoScholasticBean2(CoScholasticAssessment coScholasticAssessment) {
        List<SixToEigthCoScholasticBean> beans = new ArrayList();
        for (AssessmentActivity assessmentActivity : coScholasticAssessment.getAssessmentActivities()) {
            for (AssessmentSkill assessmentSkill : assessmentActivity.getAssessmentSkills()) {
                if (assessmentSkill.getIsAdditional() != null) {
                    for (AssessmentCriteria assessmentCriteria : assessmentSkill.getAssessmentCriterias()) {
                        String indcator = "";
                        if (assessmentCriteria.getGradeToIndicatorMap() != null) {
                            String str = (String) assessmentCriteria.getGradeToIndicatorMap().get(assessmentCriteria.getGradeBasedScores().get(0));
                        }
                    }
                }
            }
        }
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareSixToEighthCoScholasticBean1() {
        List<SixToEigthCoScholasticBean> beans = new ArrayList();
        SixToEigthCoScholasticBean bean1 = new SixToEigthCoScholasticBean("Extra", "Art Education", "A", "", "", "");
        SixToEigthCoScholasticBean bean2 = new SixToEigthCoScholasticBean("Extra", "Work Experience", "A", "", "", "");
        SixToEigthCoScholasticBean bean3 = new SixToEigthCoScholasticBean("Extra", "Physical & Health Education", "A", "", "", "");
        SixToEigthCoScholasticBean bean4 = new SixToEigthCoScholasticBean("Life Skills", "Thinking Skills", "A", "", "", "");
        SixToEigthCoScholasticBean bean5 = new SixToEigthCoScholasticBean("Life Skills", "Social Skills", "A", "", "", "");
        SixToEigthCoScholasticBean bean6 = new SixToEigthCoScholasticBean("Life Skills", "Emotional Skills", "A", "", "", "");
        SixToEigthCoScholasticBean bean7 = new SixToEigthCoScholasticBean("Attitude & Values", "Toward School-Mates", "A", "", "", "");
        SixToEigthCoScholasticBean bean8 = new SixToEigthCoScholasticBean("Attitude & Values", "Toward School Programmes", "A", "", "", "");
        SixToEigthCoScholasticBean bean9 = new SixToEigthCoScholasticBean("Attitude & Values", "Toward Environment", "A", "", "", "");
        SixToEigthCoScholasticBean bean10 = new SixToEigthCoScholasticBean("Attitude & Values", "Toward Value Systems", "A", "", "", "");
        SixToEigthCoScholasticBean bean11 = new SixToEigthCoScholasticBean("Co-Scholastic Activities", "Literary & Creative Skills", "A", "", "", "");
        SixToEigthCoScholasticBean bean12 = new SixToEigthCoScholasticBean("Co-Scholastic Activities", "Scientific Skills", "A", "", "", "");
        SixToEigthCoScholasticBean bean13 = new SixToEigthCoScholasticBean("Health & Physical Education", "Yoga", "A", "", "", "");
        SixToEigthCoScholasticBean bean14 = new SixToEigthCoScholasticBean("Health & Physical Education", "Kho-Kho", "A", "", "", "");
        beans.add(bean1);
        beans.add(bean2);
        beans.add(bean3);
        beans.add(bean4);
        beans.add(bean5);
        beans.add(bean6);
        beans.add(bean7);
        beans.add(bean8);
        beans.add(bean9);
        beans.add(bean10);
        beans.add(bean11);
        beans.add(bean12);
        beans.add(bean13);
        beans.add(bean14);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareNurToUKGCoScholasticBeanPart1(AssessmentActivity assessmentActivity) {
        List<NurToUKGCoScholasticBean> beans = new ArrayList();
        for (AssessmentSkill assessmentSkill : assessmentActivity.getAssessmentSkills()) {
            for (AssessmentCriteria assessmentCriteria : assessmentSkill.getAssessmentCriterias()) {
                beans.add(new NurToUKGCoScholasticBean(assessmentSkill.getName(), assessmentCriteria.getName(), (String) assessmentCriteria.getGradeBasedScores().get(0), (String) assessmentCriteria.getGradeBasedScores().get(1)));
            }
        }
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareNurToUKGCoScholasticBeanPart2() {
        List<NurToUKGCoScholasticBean> beans = new ArrayList();
        NurToUKGCoScholasticBean bean1 = new NurToUKGCoScholasticBean("Personal and Social Traits", "Courteousness", "A", "B");
        NurToUKGCoScholasticBean bean2 = new NurToUKGCoScholasticBean("Personal and Social Traits", "Confidence", "A", "B");
        NurToUKGCoScholasticBean bean3 = new NurToUKGCoScholasticBean("Personal and Social Traits", "Care of Belongings", "A", "B");
        NurToUKGCoScholasticBean bean4 = new NurToUKGCoScholasticBean("Personal and Social Traits", "Neatness", "A", "B");
        NurToUKGCoScholasticBean bean5 = new NurToUKGCoScholasticBean("Personal and Social Traits", "Regularity and Punctuality", "A", "B");
        NurToUKGCoScholasticBean bean6 = new NurToUKGCoScholasticBean("Personal and Social Traits", "Initiative", "A", "B");
        NurToUKGCoScholasticBean bean7 = new NurToUKGCoScholasticBean("Personal and Social Traits", "Self-Control", "A", "B");
        NurToUKGCoScholasticBean nurToUKGCoScholasticBean = new NurToUKGCoScholasticBean("Personal and Social Traits", "Respect for other's property", "A", "B");
        nurToUKGCoScholasticBean = new NurToUKGCoScholasticBean("Personal and Social Traits", "Sharing and Caring", "A", "B");
        NurToUKGCoScholasticBean bean10 = new NurToUKGCoScholasticBean("Attitude and Values", "Towards Teacher", "A", "B");
        NurToUKGCoScholasticBean bean11 = new NurToUKGCoScholasticBean("Attitude and Values", "Towards student", "A", "B");
        NurToUKGCoScholasticBean bean12 = new NurToUKGCoScholasticBean("Attitude and Values", "Towards School Programme", "A", "B");
        NurToUKGCoScholasticBean bean13 = new NurToUKGCoScholasticBean("Attitude and Values", "Value Education", "A", "B");
        beans.add(bean1);
        beans.add(bean2);
        beans.add(bean3);
        beans.add(bean4);
        beans.add(bean5);
        beans.add(bean6);
        beans.add(bean7);
        beans.add(nurToUKGCoScholasticBean);
        beans.add(nurToUKGCoScholasticBean);
        beans.add(bean10);
        beans.add(bean11);
        beans.add(bean12);
        beans.add(bean13);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareNurToUKGSignatureBean() {
        List<SignatureBean> beans = new ArrayList();
        SignatureBean bean1 = new SignatureBean("Term 1");
        SignatureBean bean2 = new SignatureBean("Term 2");
        beans.add(bean1);
        beans.add(bean2);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareNurToUKGHeightAndweightBean() {
        List<NurToUKGCoExtraBean> beans = new ArrayList();
        NurToUKGCoExtraBean bean1 = new NurToUKGCoExtraBean("Height (Cms.)", "", "");
        NurToUKGCoExtraBean bean2 = new NurToUKGCoExtraBean("Weight (Kg.)", "", "");
        beans.add(bean1);
        beans.add(bean2);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareNurToUKGAttendanceBean() {
        List<NurToUKGCoExtraBean> beans = new ArrayList();
        NurToUKGCoExtraBean bean1 = new NurToUKGCoExtraBean("Student's Attendance", "120", "145");
        NurToUKGCoExtraBean bean2 = new NurToUKGCoExtraBean("Total Working Days", "130", "150");
        beans.add(bean1);
        beans.add(bean2);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareNurToUKGAttendanceBean(String term1WorkingDays, String term2WorkingDays, String term3WorkingDays, String term1StudentAttendance, String term2StudentAttendance, String term3StudentAttendance) {
        List<NurToUKGCoExtraBean> beans = new ArrayList();
        NurToUKGCoExtraBean bean1 = new NurToUKGCoExtraBean("Student's Attendance", term1StudentAttendance, term2StudentAttendance);
        bean1.setValue3(term3StudentAttendance);
        NurToUKGCoExtraBean bean2 = new NurToUKGCoExtraBean("Total Working Days", term1WorkingDays, term2WorkingDays);
        bean2.setValue3(term3WorkingDays);
        beans.add(bean1);
        beans.add(bean2);
        return new JRBeanCollectionDataSource(beans);
    }

    public static Float calculateCGPA(List<AssessmentSubject> assessmentSubjects) {
        if (assessmentSubjects == null || assessmentSubjects.isEmpty()) {
            return null;
        }
        Float total = Float.valueOf(0.0f);
        for (AssessmentSubject assessmentSubject : assessmentSubjects) {
            if (assessmentSubject.getGradePoint() != null) {
                total = Float.valueOf(total.floatValue() + assessmentSubject.getGradePoint().floatValue());
            }
        }
        return Float.valueOf(total.floatValue() / ((float) assessmentSubjects.size()));
    }

    public static JRBeanCollectionDataSource prepareSixToEighthScholasticGradeBean() {
        List<SixToEigthGradeBean> beans = new ArrayList();
        SixToEigthGradeBean bean1 = new SixToEigthGradeBean("91-100", "A1", "10.0");
        SixToEigthGradeBean bean2 = new SixToEigthGradeBean("81-90", "A2", "9.0");
        SixToEigthGradeBean bean3 = new SixToEigthGradeBean("71-80", "B1", "8.0");
        SixToEigthGradeBean bean4 = new SixToEigthGradeBean("61-70", "B2", "7.0");
        SixToEigthGradeBean bean5 = new SixToEigthGradeBean("51-60", "C1", "6.0");
        SixToEigthGradeBean bean6 = new SixToEigthGradeBean("41-50", "C2", "5.0");
        SixToEigthGradeBean bean7 = new SixToEigthGradeBean("33-40", "D", "4.0");
        SixToEigthGradeBean bean8 = new SixToEigthGradeBean("21-32", "E1", "3.0");
        SixToEigthGradeBean bean9 = new SixToEigthGradeBean("00-20", "E2", "2.0");
        beans.add(bean1);
        beans.add(bean2);
        beans.add(bean3);
        beans.add(bean4);
        beans.add(bean5);
        beans.add(bean6);
        beans.add(bean7);
        beans.add(bean8);
        beans.add(bean9);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareSixToEighthCoScholasticGradeBean() {
        List<SixToEigthGradeBean> beans = new ArrayList();
        SixToEigthGradeBean bean1 = new SixToEigthGradeBean("", "A*", "Outstanding");
        SixToEigthGradeBean bean2 = new SixToEigthGradeBean("", "A", "Excellent");
        SixToEigthGradeBean bean3 = new SixToEigthGradeBean("", "B", "Very Good");
        SixToEigthGradeBean bean4 = new SixToEigthGradeBean("", "C", "Good");
        SixToEigthGradeBean bean5 = new SixToEigthGradeBean("", "D", "Average");
        beans.add(bean1);
        beans.add(bean2);
        beans.add(bean3);
        beans.add(bean4);
        beans.add(bean5);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource setHeightAndWeight(CoScholasticAssessment coScholasticAssessment) {
        List<NurToUKGCoExtraBean> beans = new ArrayList();
        NurToUKGCoExtraBean bean1 = new NurToUKGCoExtraBean();
        NurToUKGCoExtraBean bean2 = new NurToUKGCoExtraBean();
        bean1.setLabel("Height(Cm.)");
        bean2.setLabel("Weight(Kg.)");
        for (AssessmentActivity assessmentActivity : coScholasticAssessment.getAssessmentActivities()) {
            for (AssessmentSkill assessmentSkill : assessmentActivity.getAssessmentSkills()) {
                if (assessmentSkill.getIsAdditional() != null && assessmentSkill.getIsAdditional().booleanValue()) {
                    for (AssessmentCriteria assessmentCriteria : assessmentSkill.getAssessmentCriterias()) {
                        if ("Height(Cm.)".equalsIgnoreCase(assessmentCriteria.getName())) {
                            if (!(assessmentCriteria.getGradeBasedScores().get(0) == null || ((String) assessmentCriteria.getGradeBasedScores().get(0)).trim().isEmpty())) {
                                bean1.setValue1((String) assessmentCriteria.getGradeBasedScores().get(0));
                                bean1.setValue2((String) assessmentCriteria.getGradeBasedScores().get(1));
                            }
                        } else if (!(!"Weight(Kg.)".equalsIgnoreCase(assessmentCriteria.getName()) || assessmentCriteria.getGradeBasedScores().get(0) == null || ((String) assessmentCriteria.getGradeBasedScores().get(0)).trim().isEmpty())) {
                            bean2.setValue1((String) assessmentCriteria.getGradeBasedScores().get(0));
                            bean2.setValue2((String) assessmentCriteria.getGradeBasedScores().get(1));
                        }
                    }
                }
            }
        }
        beans.add(bean1);
        beans.add(bean2);
        return new JRBeanCollectionDataSource(beans);
    }
}
