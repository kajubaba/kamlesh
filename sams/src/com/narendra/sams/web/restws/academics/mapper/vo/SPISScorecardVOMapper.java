package com.narendra.sams.web.restws.academics.mapper.vo;

import com.narendra.sams.academics.domain.AssessmentActivity;
import com.narendra.sams.academics.domain.AssessmentCriteria;
import com.narendra.sams.academics.domain.AssessmentSkill;
import com.narendra.sams.academics.domain.AssessmentSubject;
import com.narendra.sams.academics.domain.CoScholasticAssessment;
import com.narendra.sams.academics.domain.ConversionRule;
import com.narendra.sams.academics.domain.ScoreCard;
import com.narendra.sams.admission.domain.ClassSection;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.academics.vo.NurToUKGCoExtraBean;
import com.narendra.sams.web.restws.academics.vo.NurToUKGCoScholasticBean;
import com.narendra.sams.web.restws.academics.vo.ScholasticExamCycleBean;
import com.narendra.sams.web.restws.academics.vo.SixToEigthGradeBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class SPISScorecardVOMapper {
    public static Map<String, Object> prepareScoreCard(ScoreCard scoreCard, Student student, Long classId, String rollNo, String term1WorkingDays, String term2WorkingDays, String term1StudentAttendance, String term2StudentAttendance) {
        Map<String, Object> parameters = new HashMap();
        parameters.putAll(setScholasticLabels());
        parameters.putAll(setCoScholasticLabels());
        parameters.putAll(setSignatureLabels());
        parameters.put("result", student.getAcademicYearClass().getNextClassName());
        parameters.putAll(setStudentProfileLabels());
        parameters.putAll(setStudentProfileValues(student, classId, rollNo));
        parameters.putAll(setHealthStatusAndAttendanceLabels());
        JRBeanCollectionDataSource scholasticDS = prepareNurToUKGScholasticBean(scoreCard.getScholasticAssessment().getAssessmentSubjects());
        JRBeanCollectionDataSource coscholasticDS1 = prepareNurToUKGCoScholasticBeanPart1((AssessmentActivity) scoreCard.getCoScholasticAssessment().getAssessmentActivities().get(0));
        JRBeanCollectionDataSource coscholasticDS2 = prepareNurToUKGCoScholasticBeanPart1((AssessmentActivity) scoreCard.getCoScholasticAssessment().getAssessmentActivities().get(1));
        JRBeanCollectionDataSource heightAndWeightDS = setHeightAndWeight(scoreCard.getCoScholasticAssessment());
        JRBeanCollectionDataSource attendanceDS = prepareNurToUKGAttendanceBean(term1WorkingDays, term2WorkingDays, term1StudentAttendance, term2StudentAttendance);
        JRBeanCollectionDataSource scholasticGradeDS = prepareNurToUKGScholasticGradeBean(scoreCard.getScholasticAssessment().getConversionRules());
        JRBeanCollectionDataSource coScholasticGradeDS = prepareNurToUKGCoScholasticGradeBean(scoreCard.getCoScholasticAssessment().getConversionRules());
        parameters.put("academicPerformance", "PART I - ACADEMIC PERFORMANCE : SCHOLASTIC AREAS");
        parameters.put("activity1Name", ((AssessmentActivity) scoreCard.getCoScholasticAssessment().getAssessmentActivities().get(0)).getName());
        parameters.put("activity2Name", ((AssessmentActivity) scoreCard.getCoScholasticAssessment().getAssessmentActivities().get(1)).getName());
        parameters.put("DSScholastic", scholasticDS);
        parameters.put("DS1CoScholastic", coscholasticDS1);
        parameters.put("DS2CoScholastic", coscholasticDS2);
        parameters.put("DSSignature", new JREmptyDataSource());
        parameters.put("DSHeightAndWeight", heightAndWeightDS);
        parameters.put("DSAttendance", attendanceDS);
        parameters.put("DSStudentProfile", new JREmptyDataSource());
        parameters.put("DSScholasticGrade", scholasticGradeDS);
        parameters.put("DSCoScholasticGrade", coScholasticGradeDS);
        parameters.put("DSHeader", new JREmptyDataSource());
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
        JRBeanCollectionDataSource scholasticDS = prepareNurToUKGScholasticBean();
        JRBeanCollectionDataSource coscholasticDS1 = prepareNurToUKGCoScholasticBeanPart1();
        JRBeanCollectionDataSource coscholasticDS2 = prepareNurToUKGCoScholasticBeanPart2();
        JRBeanCollectionDataSource heightAndWeightDS = prepareNurToUKGHeightAndweightBean();
        JRBeanCollectionDataSource attendanceDS = prepareNurToUKGAttendanceBean();
        JRBeanCollectionDataSource scholasticGradeDS = prepareNurToUKGScholasticGradeBean();
        JRBeanCollectionDataSource coScholasticGradeDS = prepareNurToUKGCoScholasticGradeBean();
        parameters.put("academicPerformance", "PART I - ACADEMIC PERFORMANCE : SCHOLASTIC AREAS");
        parameters.put("activity1Name", "PART II - CO-CURRICULAR ACTIVITIES");
        parameters.put("activity2Name", "PART III - PERSONALITY DEVELOPMENT");
        parameters.put("DSScholastic", scholasticDS);
        parameters.put("DS1CoScholastic", coscholasticDS1);
        parameters.put("DS2CoScholastic", coscholasticDS2);
        parameters.put("DSScholasticGrade", scholasticGradeDS);
        parameters.put("DSCoScholasticGrade", coScholasticGradeDS);
        parameters.put("DSSignature", new JREmptyDataSource());
        parameters.put("DSHeightAndWeight", heightAndWeightDS);
        parameters.put("DSAttendance", attendanceDS);
        parameters.put("DSStudentProfile", new JREmptyDataSource());
        parameters.put("DSHeader", new JREmptyDataSource());
        try {
            JasperExportManager.exportReportToPdfFile(JasperFillManager.fillReportToFile("C:\\SPIS-Scorecard\\Nur-UKG\\Nur-LKG-Scoreacrd.jasper", parameters, new JREmptyDataSource()), "C:\\SPIS-Scorecard\\Nur-UKG\\ScoreCard.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> setScholasticLabels() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("lblSubject", "Subject");
        parameters.put("lblCycle1", "Cycle 1");
        parameters.put("lblCycle2", "Cycle 2");
        parameters.put("lblCycle3", "Cycle 3");
        parameters.put("lblCycle4", "Cycle 4");
        parameters.put("lblCycle5", "Cycle 5");
        parameters.put("lblCycle6", "Cycle 6");
        parameters.put("lblCycle7", "Cycle 7");
        parameters.put("lblCycle8", "Cycle 8");
        parameters.put("lblCycle9", "Cycle 9");
        parameters.put("lblCycle10", "Cycle 10");
        return parameters;
    }

    public static Map<String, Object> setCoScholasticLabels() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("lblTerm1", "Term I");
        parameters.put("lblTerm2", "Term II");
        return parameters;
    }

    public static Map<String, Object> setSignatureLabels() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("lblSign1", "(Parents)");
        parameters.put("lblSign2", "(Class teacher)");
        parameters.put("lblSign3", "(Principal)");
        parameters.put("lblNewSessionBeginOn", "New Session Begins On :");
        parameters.put("newSessionBeginOn", "22-Mar-2017");
        parameters.put("lblResult", "Congratulations !!! Promoted to Class : ");
        parameters.put("result", "");
        return parameters;
    }

    public static Map<String, Object> setStudentProfileLabels() {
        Map<String, Object> parameters = new HashMap();
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
        parameters.put("valSession", academicYearClass.getAcademicYear().getName());
        parameters.put("valClass", academicYearClass.getDisplayName());
        parameters.put("valSection", academicYearClass.getDisplayName());
        ClassSection classSection = student.getActiveClassByClassId(classId).getClassSection();
        if (!(classSection == null || classSection == null || classSection.getSectionName().trim().isEmpty())) {
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

    public static JRBeanCollectionDataSource prepareNurToUKGScholasticBean() {
        List<ScholasticExamCycleBean> beans = new ArrayList();
        for (int i = 0; i < 5; i++) {
            beans.add(new ScholasticExamCycleBean("Subject-" + i, "A", "B", "C", "D", "E", "A", "B", "C", "D", "E"));
        }
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareNurToUKGScholasticBean(List<AssessmentSubject> assessmentSubjects) {
        if (assessmentSubjects == null) {
            return null;
        }
        List<ScholasticExamCycleBean> beans = new ArrayList();
        for (AssessmentSubject assessmentSubject : assessmentSubjects) {
            beans.add(new ScholasticExamCycleBean(assessmentSubject.getName(), (String) assessmentSubject.getGrades().get(0), (String) assessmentSubject.getGrades().get(1), (String) assessmentSubject.getGrades().get(2), (String) assessmentSubject.getGrades().get(3), (String) assessmentSubject.getGrades().get(4), (String) assessmentSubject.getGrades().get(5), (String) assessmentSubject.getGrades().get(6), (String) assessmentSubject.getGrades().get(7), (String) assessmentSubject.getGrades().get(8), (String) assessmentSubject.getGrades().get(9)));
        }
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareNurToUKGCoScholasticBeanPart1() {
        List<NurToUKGCoScholasticBean> beans = new ArrayList();
        NurToUKGCoScholasticBean bean1 = new NurToUKGCoScholasticBean("Games", "Enthusiasm", "A", "B");
        NurToUKGCoScholasticBean bean2 = new NurToUKGCoScholasticBean("Games", "Discipline", "A", "B");
        NurToUKGCoScholasticBean bean3 = new NurToUKGCoScholasticBean("Games", "Team Spirit", "A", "B");
        NurToUKGCoScholasticBean bean4 = new NurToUKGCoScholasticBean("Games", "Talent", "A", "B");
        NurToUKGCoScholasticBean bean5 = new NurToUKGCoScholasticBean("Art/Craft", "Interest", "A", "B");
        NurToUKGCoScholasticBean bean6 = new NurToUKGCoScholasticBean("Art/Craft", "Creativity", "A", "B");
        NurToUKGCoScholasticBean nurToUKGCoScholasticBean = new NurToUKGCoScholasticBean("Art/Craft", "Skill", "A", "B");
        nurToUKGCoScholasticBean = new NurToUKGCoScholasticBean("Art/Craft", "Neatness", "A", "B");
        nurToUKGCoScholasticBean = new NurToUKGCoScholasticBean("Music", "Interest", "A", "B");
        NurToUKGCoScholasticBean bean10 = new NurToUKGCoScholasticBean("Music", "Rhythm", "A", "B");
        NurToUKGCoScholasticBean bean11 = new NurToUKGCoScholasticBean("Music", "Melody", "A", "B");
        NurToUKGCoScholasticBean bean12 = new NurToUKGCoScholasticBean("Dance", "Interest", "A", "B");
        NurToUKGCoScholasticBean bean13 = new NurToUKGCoScholasticBean("Dance", "Rhythm", "A", "B");
        NurToUKGCoScholasticBean bean14 = new NurToUKGCoScholasticBean("Dance", "Melody", "A", "B");
        beans.add(bean1);
        beans.add(bean2);
        beans.add(bean3);
        beans.add(bean4);
        beans.add(bean5);
        beans.add(bean6);
        beans.add(nurToUKGCoScholasticBean);
        beans.add(nurToUKGCoScholasticBean);
        beans.add(nurToUKGCoScholasticBean);
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

    public static JRBeanCollectionDataSource prepareNurToUKGHeightAndweightBean() {
        List<NurToUKGCoExtraBean> beans = new ArrayList();
        NurToUKGCoExtraBean bean1 = new NurToUKGCoExtraBean("Height (Cm.)", "12", "34");
        NurToUKGCoExtraBean bean2 = new NurToUKGCoExtraBean("Weight (Kg.)", "39", "23");
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

    public static JRBeanCollectionDataSource prepareNurToUKGAttendanceBean(String term1WorkingDays, String term2WorkingDays, String term1StudentAttendance, String term2StudentAttendance) {
        List<NurToUKGCoExtraBean> beans = new ArrayList();
        NurToUKGCoExtraBean bean1 = new NurToUKGCoExtraBean("Student's Attendance", term1StudentAttendance, term2StudentAttendance);
        NurToUKGCoExtraBean bean2 = new NurToUKGCoExtraBean("Total Working Days", term1WorkingDays, term2WorkingDays);
        beans.add(bean1);
        beans.add(bean2);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareNurToUKGCoScholasticGradeBean() {
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

    public static JRBeanCollectionDataSource prepareNurToUKGScholasticGradeBean() {
        List<SixToEigthGradeBean> beans = new ArrayList();
        SixToEigthGradeBean bean1 = new SixToEigthGradeBean("1", "A*", "Outstanding");
        SixToEigthGradeBean bean2 = new SixToEigthGradeBean("2", "A", "Excellent");
        SixToEigthGradeBean bean3 = new SixToEigthGradeBean("3", "B", "Very Good");
        SixToEigthGradeBean bean4 = new SixToEigthGradeBean("4", "C", "Good");
        SixToEigthGradeBean bean5 = new SixToEigthGradeBean("5", "D", "Average");
        beans.add(bean1);
        beans.add(bean2);
        beans.add(bean3);
        beans.add(bean4);
        beans.add(bean5);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareNurToUKGScholasticGradeBean(List<ConversionRule> conversionRules) {
        List<SixToEigthGradeBean> beans = new ArrayList();
        for (ConversionRule conversionRule : conversionRules) {
            beans.add(new SixToEigthGradeBean(new StringBuilder(String.valueOf((int) conversionRule.getFrom())).append("-").append((int) conversionRule.getTo()).toString(), conversionRule.getGrade(), conversionRule.getGradeMeaning()));
        }
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareNurToUKGCoScholasticGradeBean(List<ConversionRule> conversionRules) {
        List<SixToEigthGradeBean> beans = new ArrayList();
        for (ConversionRule conversionRule : conversionRules) {
            beans.add(new SixToEigthGradeBean("", conversionRule.getGrade(), conversionRule.getGradeMeaning()));
        }
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
