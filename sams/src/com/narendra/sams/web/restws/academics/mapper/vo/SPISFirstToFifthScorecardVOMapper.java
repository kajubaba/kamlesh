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
import com.narendra.sams.web.restws.academics.vo.FirstToFifthScholasticBean;
import com.narendra.sams.web.restws.academics.vo.FirstToSecondCoScholasticBean;
import com.narendra.sams.web.restws.academics.vo.NurToUKGCoExtraBean;
import com.narendra.sams.web.restws.academics.vo.SixToEigthGradeBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class SPISFirstToFifthScorecardVOMapper {
    public static Map<String, Object> prepareScoreCard(String scorecardFor, ScoreCard scoreCard, Student student, Long classId, String rollNo, String term1WorkingDays, String term2WorkingDays, String term3WorkingDays, String term1StudentAttendance, String term2StudentAttendance, String term3StudentAttendance) {
        Map<String, Object> parameters = new HashMap();
        parameters.putAll(setScholasticLabels());
        parameters.putAll(setCoScholasticLabels());
        parameters.putAll(setHealthStatusAndAttendanceLabels());
        parameters.putAll(setStudentProfileLabels());
        parameters.putAll(setStudentProfileValues(student, classId, rollNo));
        JRBeanCollectionDataSource scholasticDS = prepareFirstToSecondScholasticBean(scoreCard.getScholasticAssessment().getAssessmentSubjects());
        JRBeanCollectionDataSource coscholasticDS1 = null;
        JRBeanCollectionDataSource coscholasticDS2 = null;
        if ("I-II".equals(scorecardFor)) {
            coscholasticDS1 = prepareCoScholasticDataSource(scoreCard.getCoScholasticAssessment(), 1, 3);
            coscholasticDS2 = prepareCoScholasticDataSource(scoreCard.getCoScholasticAssessment(), 3, 6);
        } else if ("III-V".equals(scorecardFor)) {
            coscholasticDS1 = prepareCoScholasticDataSource(scoreCard.getCoScholasticAssessment(), 1, 4);
            coscholasticDS2 = prepareCoScholasticDataSource(scoreCard.getCoScholasticAssessment(), 4, 7);
        }
        JRBeanCollectionDataSource heightAndWeightDS = prepareHeightAndweightDataSource(scoreCard.getCoScholasticAssessment());
        JRBeanCollectionDataSource attendanceDS = prepareAttendanceDataSource(term1WorkingDays, term2WorkingDays, term3WorkingDays, term1StudentAttendance, term2StudentAttendance, term3StudentAttendance);
        JRBeanCollectionDataSource scholasticGradeDS = prepareScholasticGradeDataSource(scoreCard.getScholasticAssessment().getConversionRules());
        JRBeanCollectionDataSource coScholasticGradeDS = prepareCoScholasticGradeDataSource(scoreCard.getCoScholasticAssessment().getConversionRules());
        parameters.put("DSScholastic", scholasticDS);
        parameters.put("DS1CoScholastic", coscholasticDS1);
        parameters.put("DS2CoScholastic", coscholasticDS2);
        parameters.put("DS3CoScholastic", prepareLanguageDataSource(scoreCard.getCoScholasticAssessment(), 0));
        parameters.put("DS4CoScholastic", prepareLanguageDataSource(scoreCard.getCoScholasticAssessment(), 1));
        parameters.put("DSHeightAndWeight", heightAndWeightDS);
        parameters.put("DSAttendance", attendanceDS);
        parameters.put("DSStudentProfile", new JREmptyDataSource());
        parameters.put("DSHeader", new JREmptyDataSource());
        parameters.put("DSScholasticGrade", scholasticGradeDS);
        parameters.put("DSCoScholasticGrade", coScholasticGradeDS);
        parameters.put("academicPerformance", "PART I - ACADEMIC PERFORMANCE : SCHOLASTIC AREAS");
        parameters.put("lblNewSessionBeginOn", "New Session Begins On :");
        parameters.put("newSessionBeginOn", "22-Mar-2017");
        parameters.put("promotionClass", student.getAcademicYearClass().getNextClassName());
        parameters.put("eval1", "Eval 1");
        parameters.put("eval2", "Eval 2");
        parameters.put("eval3", "Eval 3");
        return parameters;
    }

    public static void main(String[] args) {
        Map<String, Object> parameters = new HashMap();
        parameters.putAll(setScholasticLabels());
        parameters.putAll(setCoScholasticLabels());
        parameters.putAll(setStudentProfileLabels());
        parameters.putAll(setStudentProfileValues());
        parameters.putAll(setHealthStatusAndAttendanceLabels());
        JRBeanCollectionDataSource scholasticDS = prepareScholasticDataSource();
        JRBeanCollectionDataSource coscholasticDS1 = prepareThirdToFifthCoScholasticDataSource1();
        JRBeanCollectionDataSource coscholasticDS2 = prepareThirdToFifthCoScholasticDataSource2();
        JRBeanCollectionDataSource heightAndWeightDS = prepareHeightAndweightDataSource();
        JRBeanCollectionDataSource attendanceDS = prepareAttendanceDataSource();
        JRBeanCollectionDataSource scholasticGradeDS = prepareScholasticGradeDataSource();
        JRBeanCollectionDataSource coScholasticGradeDS = prepareCoScholasticGradeDataSource();
        parameters.put("DSScholastic", scholasticDS);
        parameters.put("DS1CoScholastic", coscholasticDS1);
        parameters.put("DS2CoScholastic", coscholasticDS2);
        parameters.put("DSHeightAndWeight", heightAndWeightDS);
        parameters.put("DSAttendance", attendanceDS);
        parameters.put("DSStudentProfile", new JREmptyDataSource());
        parameters.put("DSHeader", new JREmptyDataSource());
        parameters.put("DSScholasticGrade", scholasticGradeDS);
        parameters.put("DSCoScholasticGrade", coScholasticGradeDS);
        parameters.put("academicPerformance", "PART I : ACADEMIC PERFORMANCE");
        parameters.put("lblNewSessionBeginOn", "New Session Begins On :");
        parameters.put("newSessionBeginOn", "22-Mar-2017");
        parameters.put("promotionClass", "XY");
        parameters.put("eval1", "Eval 1");
        parameters.put("eval2", "Eval 2");
        parameters.put("eval3", "Eval 3");
        try {
            JasperExportManager.exportReportToPdfFile(JasperFillManager.fillReportToFile("C:\\SPIS-Scorecard\\III-V\\III-V-Scoreacrd.jasper", parameters, new JREmptyDataSource()), "C:\\SPIS-Scorecard\\III-V\\ScoreCard.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> setScholasticLabels() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("lblSubject", "Subject");
        return parameters;
    }

    public static Map<String, Object> setCoScholasticLabels() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("lblTerm1", "Evaluation 1");
        parameters.put("lblTerm2", "Evaluation 2");
        parameters.put("lblTerm3", "Evaluation 3");
        return parameters;
    }

    public static Map<String, Object> setStudentProfileLabels() {
        Map<String, Object> parameters = new HashMap();
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
        parameters.put("valSession", academicYearClass.getAcademicYear().getName());
        parameters.put("valClass", academicYearClass.getDisplayName());
        parameters.put("valSection", academicYearClass.getDisplayName());
        ClassSection classSection = student.getActiveClassByClassId(classId).getClassSection();
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

    public static JRBeanCollectionDataSource prepareScholasticDataSource() {
        List<FirstToFifthScholasticBean> beans = new ArrayList();
        for (int i = 0; i < 5; i++) {
            beans.add(new FirstToFifthScholasticBean("Subject-" + i, "A", "B", "C"));
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

    public static JRBeanCollectionDataSource prepareCoScholasticDataSource(CoScholasticAssessment coScholasticAssessment, int from, int to) {
        List<FirstToSecondCoScholasticBean> beans = new ArrayList();
        for (AssessmentActivity assessmentActivity : coScholasticAssessment.getAssessmentActivities().subList(from, to)) {
            for (AssessmentSkill assessmentSkill : assessmentActivity.getAssessmentSkills()) {
                for (AssessmentCriteria assessmentCriteria : assessmentSkill.getAssessmentCriterias()) {
                    beans.add(new FirstToSecondCoScholasticBean(assessmentActivity.getName(), assessmentSkill.getName(), assessmentCriteria.getName(), (String) assessmentCriteria.getGradeBasedScores().get(0), (String) assessmentCriteria.getGradeBasedScores().get(1), (String) assessmentCriteria.getGradeBasedScores().get(2)));
                }
            }
        }
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareLanguageDataSource(CoScholasticAssessment coScholasticAssessment, int index) {
        List<FirstToSecondCoScholasticBean> beans = new ArrayList();
        AssessmentActivity activity = (AssessmentActivity) ((AssessmentActivity) coScholasticAssessment.getAssessmentActivities().get(0)).getSubActivities().get(index);
        for (AssessmentSkill assessmentSkill : activity.getAssessmentSkills()) {
            for (AssessmentCriteria assessmentCriteria : assessmentSkill.getAssessmentCriterias()) {
                beans.add(new FirstToSecondCoScholasticBean(activity.getName(), assessmentSkill.getName(), assessmentCriteria.getName(), (String) assessmentCriteria.getGradeBasedScores().get(0), (String) assessmentCriteria.getGradeBasedScores().get(1), (String) assessmentCriteria.getGradeBasedScores().get(2)));
            }
        }
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareCoScholasticDataSource1() {
        List<FirstToSecondCoScholasticBean> beans = new ArrayList();
        FirstToSecondCoScholasticBean bean1 = new FirstToSecondCoScholasticBean("B. Mathematics", "Aspects", "Concept", "A", "B", "C");
        FirstToSecondCoScholasticBean bean2 = new FirstToSecondCoScholasticBean("B. Mathematics", "Aspects", "Activity", "A", "B", "C");
        FirstToSecondCoScholasticBean bean3 = new FirstToSecondCoScholasticBean("B. Mathematics", "Aspects", "Tables", "A", "B", "C");
        FirstToSecondCoScholasticBean bean4 = new FirstToSecondCoScholasticBean("B. Mathematics", "Aspects", "Mental Ability", "A", "B", "C");
        FirstToSecondCoScholasticBean bean5 = new FirstToSecondCoScholasticBean("B. Mathematics", "Aspects", "Written Work", "A", "B", "C");
        FirstToSecondCoScholasticBean bean7 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Games", "Enthusiasm", "A", "B", "C");
        FirstToSecondCoScholasticBean bean8 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Games", "Discipline", "A", "B", "C");
        FirstToSecondCoScholasticBean bean9 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Games", "Team Spirit", "A", "B", "C");
        FirstToSecondCoScholasticBean bean10 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Games", "Talent", "A", "B", "C");
        FirstToSecondCoScholasticBean bean11 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Art/Craft", "Interest", "A", "B", "C");
        FirstToSecondCoScholasticBean bean12 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Art/Craft", "Creativity", "A", "B", "C");
        FirstToSecondCoScholasticBean bean13 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Art/Craft", "Skill ", "A", "B", "C");
        FirstToSecondCoScholasticBean bean14 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Art/Craft", "Neatness", "A", "B", "C");
        FirstToSecondCoScholasticBean bean15 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Music", "Interest ", "A", "B", "C");
        FirstToSecondCoScholasticBean bean16 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Music", "Rhythm", "A", "B", "C");
        FirstToSecondCoScholasticBean bean17 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Music", "Melody", "A", "B", "C");
        FirstToSecondCoScholasticBean bean18 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Dance", "Interest", "A", "B", "C");
        FirstToSecondCoScholasticBean bean19 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Dance", "Rhythm", "A", "B", "C");
        FirstToSecondCoScholasticBean bean20 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Dance", "Melody", "A", "B", "C");
        beans.add(bean1);
        beans.add(bean2);
        beans.add(bean3);
        beans.add(bean4);
        beans.add(bean5);
        beans.add(bean7);
        beans.add(bean8);
        beans.add(bean9);
        beans.add(bean10);
        beans.add(bean11);
        beans.add(bean12);
        beans.add(bean13);
        beans.add(bean14);
        beans.add(bean15);
        beans.add(bean16);
        beans.add(bean17);
        beans.add(bean18);
        beans.add(bean19);
        beans.add(bean20);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareThirdToFifthCoScholasticDataSource1() {
        List<FirstToSecondCoScholasticBean> beans = new ArrayList();
        FirstToSecondCoScholasticBean bean1 = new FirstToSecondCoScholasticBean("B. Mathematics", "Aspects", "Concept", "A", "B", "C");
        FirstToSecondCoScholasticBean bean2 = new FirstToSecondCoScholasticBean("B. Mathematics", "Aspects", "Activity", "A", "B", "C");
        FirstToSecondCoScholasticBean bean3 = new FirstToSecondCoScholasticBean("B. Mathematics", "Aspects", "Tables", "A", "B", "C");
        FirstToSecondCoScholasticBean bean4 = new FirstToSecondCoScholasticBean("B. Mathematics", "Aspects", "Mental Ability", "A", "B", "C");
        FirstToSecondCoScholasticBean bean5 = new FirstToSecondCoScholasticBean("B. Mathematics", "Aspects", "Written Work", "A", "B", "C");
        FirstToSecondCoScholasticBean bean7 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Games", "Enthusiasm", "A", "B", "C");
        FirstToSecondCoScholasticBean bean8 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Games", "Discipline", "A", "B", "C");
        FirstToSecondCoScholasticBean bean9 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Games", "Team Spirit", "A", "B", "C");
        FirstToSecondCoScholasticBean bean10 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Games", "Talent", "A", "B", "C");
        FirstToSecondCoScholasticBean bean11 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Art/Craft", "Interest", "A", "B", "C");
        FirstToSecondCoScholasticBean bean12 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Art/Craft", "Creativity", "A", "B", "C");
        FirstToSecondCoScholasticBean bean13 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Art/Craft", "Skill ", "A", "B", "C");
        FirstToSecondCoScholasticBean bean14 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Art/Craft", "Neatness", "A", "B", "C");
        FirstToSecondCoScholasticBean bean15 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Music", "Interest ", "A", "B", "C");
        FirstToSecondCoScholasticBean bean16 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Music", "Rhythm", "A", "B", "C");
        FirstToSecondCoScholasticBean bean17 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Music", "Melody", "A", "B", "C");
        FirstToSecondCoScholasticBean bean18 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Dance", "Interest", "A", "B", "C");
        FirstToSecondCoScholasticBean bean19 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Dance", "Rhythm", "A", "B", "C");
        FirstToSecondCoScholasticBean bean20 = new FirstToSecondCoScholasticBean("D. Co-Curricular Activities", "Dance", "Melody", "A", "B", "C");
        FirstToSecondCoScholasticBean bean21 = new FirstToSecondCoScholasticBean("Computer", "Aspect", "Aptitute", "A", "B", "C");
        FirstToSecondCoScholasticBean bean22 = new FirstToSecondCoScholasticBean("Computer", "Aspect", "Skill", "A", "B", "C");
        beans.add(bean1);
        beans.add(bean2);
        beans.add(bean3);
        beans.add(bean4);
        beans.add(bean5);
        beans.add(bean7);
        beans.add(bean8);
        beans.add(bean9);
        beans.add(bean10);
        beans.add(bean11);
        beans.add(bean12);
        beans.add(bean13);
        beans.add(bean14);
        beans.add(bean15);
        beans.add(bean16);
        beans.add(bean17);
        beans.add(bean18);
        beans.add(bean19);
        beans.add(bean20);
        beans.add(bean21);
        beans.add(bean22);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareThirdToFifthCoScholasticDataSource2() {
        List<FirstToSecondCoScholasticBean> beans = new ArrayList();
        FirstToSecondCoScholasticBean bean1 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Courteousness", "A", "B", "C");
        FirstToSecondCoScholasticBean bean2 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Confidence", "A", "B", "C");
        FirstToSecondCoScholasticBean bean3 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Care of Belongings ", "A", "B", "C");
        FirstToSecondCoScholasticBean bean4 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Neatness", "A", "B", "C");
        FirstToSecondCoScholasticBean bean5 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Regularity and", "A", "B", "C");
        FirstToSecondCoScholasticBean bean6 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Initiative ", "A", "B", "C");
        FirstToSecondCoScholasticBean bean7 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Self-Control", "A", "B", "C");
        FirstToSecondCoScholasticBean bean8 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Respect for other's", "A", "B", "C");
        FirstToSecondCoScholasticBean bean9 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Sharing and Caring", "A", "B", "C");
        FirstToSecondCoScholasticBean bean10 = new FirstToSecondCoScholasticBean("Personality", "Attitude and Values", "Towards Teacher", "A", "B", "C");
        FirstToSecondCoScholasticBean bean11 = new FirstToSecondCoScholasticBean("Personality", "Attitude and Values", "Towards student ", "A", "B", "C");
        FirstToSecondCoScholasticBean bean12 = new FirstToSecondCoScholasticBean("Personality", "Attitude and Values", "Towards School", "A", "B", "C");
        FirstToSecondCoScholasticBean bean13 = new FirstToSecondCoScholasticBean("Personality", "Attitude and Values", "Value Education ", "A", "B", "C");
        FirstToSecondCoScholasticBean bean14 = new FirstToSecondCoScholasticBean("Science", "Aspect", "Concept", "A", "B", "C");
        FirstToSecondCoScholasticBean bean15 = new FirstToSecondCoScholasticBean("Science", "Aspect", "Activity/Project", "A", "B", "C");
        FirstToSecondCoScholasticBean bean16 = new FirstToSecondCoScholasticBean("Science", "Aspect", "Scientific Skill", "A", "B", "C");
        FirstToSecondCoScholasticBean bean17 = new FirstToSecondCoScholasticBean("Science", "Aspect", "Group Discussion", "A", "B", "C");
        FirstToSecondCoScholasticBean bean18 = new FirstToSecondCoScholasticBean("C. Environmental Science", "Aspects", "Environmental", "A", "B", "C");
        FirstToSecondCoScholasticBean bean19 = new FirstToSecondCoScholasticBean("C. Environmental Science", "Aspects", "Activity / Project", "A", "B", "C");
        FirstToSecondCoScholasticBean bean20 = new FirstToSecondCoScholasticBean("C. Environmental Science", "Aspects", "Group Discussion", "A", "B", "C");
        FirstToSecondCoScholasticBean bean21 = new FirstToSecondCoScholasticBean("C. Environmental Science", "Aspects", "Written Work", "A", "B", "C");
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
        beans.add(bean15);
        beans.add(bean16);
        beans.add(bean17);
        beans.add(bean18);
        beans.add(bean19);
        beans.add(bean20);
        beans.add(bean21);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareCoScholasticDataSource2() {
        List<FirstToSecondCoScholasticBean> beans = new ArrayList();
        FirstToSecondCoScholasticBean bean1 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Courteousness", "A", "B", "C");
        FirstToSecondCoScholasticBean bean2 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Confidence", "A", "B", "C");
        FirstToSecondCoScholasticBean bean3 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Care of Belongings ", "A", "B", "C");
        FirstToSecondCoScholasticBean bean4 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Neatness", "A", "B", "C");
        FirstToSecondCoScholasticBean bean5 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Regularity and", "A", "B", "C");
        FirstToSecondCoScholasticBean bean6 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Initiative ", "A", "B", "C");
        FirstToSecondCoScholasticBean bean7 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Self-Control", "A", "B", "C");
        FirstToSecondCoScholasticBean bean8 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Respect for other's", "A", "B", "C");
        FirstToSecondCoScholasticBean bean9 = new FirstToSecondCoScholasticBean("Personality", "Personal and Social", "Sharing and Caring", "A", "B", "C");
        FirstToSecondCoScholasticBean bean10 = new FirstToSecondCoScholasticBean("Personality", "Attitude and Values", "Towards Teacher", "A", "B", "C");
        FirstToSecondCoScholasticBean bean11 = new FirstToSecondCoScholasticBean("Personality", "Attitude and Values", "Towards student ", "A", "B", "C");
        FirstToSecondCoScholasticBean bean12 = new FirstToSecondCoScholasticBean("Personality", "Attitude and Values", "Towards School", "A", "B", "C");
        FirstToSecondCoScholasticBean bean13 = new FirstToSecondCoScholasticBean("Personality", "Attitude and Values", "Value Education ", "A", "B", "C");
        FirstToSecondCoScholasticBean bean14 = new FirstToSecondCoScholasticBean("Computer", "Aspect", "Aptitute", "A", "B", "C");
        FirstToSecondCoScholasticBean bean15 = new FirstToSecondCoScholasticBean("Computer", "Aspect", "Skill", "A", "B", "C");
        FirstToSecondCoScholasticBean bean16 = new FirstToSecondCoScholasticBean("C. Environmental Science", "Aspects", "Environmental", "A", "B", "C");
        FirstToSecondCoScholasticBean bean17 = new FirstToSecondCoScholasticBean("C. Environmental Science", "Aspects", "Activity / Project", "A", "B", "C");
        FirstToSecondCoScholasticBean bean18 = new FirstToSecondCoScholasticBean("C. Environmental Science", "Aspects", "Group Discussion", "A", "B", "C");
        FirstToSecondCoScholasticBean bean19 = new FirstToSecondCoScholasticBean("C. Environmental Science", "Aspects", "Written Work", "A", "B", "C");
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
        beans.add(bean15);
        beans.add(bean16);
        beans.add(bean17);
        beans.add(bean18);
        beans.add(bean19);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareHeightAndweightDataSource() {
        List<NurToUKGCoExtraBean> beans = new ArrayList();
        NurToUKGCoExtraBean bean1 = new NurToUKGCoExtraBean("Height (Cms.)", "12", "23", "40");
        NurToUKGCoExtraBean bean2 = new NurToUKGCoExtraBean("Weight (Kg.)", "34", "23", "50");
        beans.add(bean1);
        beans.add(bean2);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareAttendanceDataSource() {
        List<NurToUKGCoExtraBean> beans = new ArrayList();
        NurToUKGCoExtraBean bean1 = new NurToUKGCoExtraBean("Student's Attendance", "120", "145", "345");
        NurToUKGCoExtraBean bean2 = new NurToUKGCoExtraBean("Total Working Days", "130", "150", "qwqw");
        beans.add(bean1);
        beans.add(bean2);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareAttendanceDataSource(String term1WorkingDays, String term2WorkingDays, String term3WorkingDays, String term1StudentAttendance, String term2StudentAttendance, String term3StudentAttendance) {
        List<NurToUKGCoExtraBean> beans = new ArrayList();
        NurToUKGCoExtraBean bean1 = new NurToUKGCoExtraBean("Student's Attendance", term1StudentAttendance, term2StudentAttendance);
        bean1.setValue3(term3StudentAttendance);
        NurToUKGCoExtraBean bean2 = new NurToUKGCoExtraBean("Total Working Days", term1WorkingDays, term2WorkingDays);
        bean2.setValue3(term3WorkingDays);
        beans.add(bean1);
        beans.add(bean2);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareHeightAndweightDataSource(CoScholasticAssessment coScholasticAssessment) {
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
                            bean1.setValue1((String) assessmentCriteria.getGradeBasedScores().get(0));
                            bean1.setValue2((String) assessmentCriteria.getGradeBasedScores().get(1));
                            bean1.setValue3((String) assessmentCriteria.getGradeBasedScores().get(2));
                        } else if ("Weight(Kg.)".equalsIgnoreCase(assessmentCriteria.getName())) {
                            bean2.setValue1((String) assessmentCriteria.getGradeBasedScores().get(0));
                            bean2.setValue2((String) assessmentCriteria.getGradeBasedScores().get(1));
                            bean2.setValue3((String) assessmentCriteria.getGradeBasedScores().get(2));
                        }
                    }
                }
            }
        }
        beans.add(bean1);
        beans.add(bean2);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareScholasticGradeDataSource(List<ConversionRule> conversionRules) {
        List<SixToEigthGradeBean> beans = new ArrayList();
        for (ConversionRule conversionRule : conversionRules) {
            beans.add(new SixToEigthGradeBean(new StringBuilder(String.valueOf((int) conversionRule.getFrom())).append("-").append((int) conversionRule.getTo()).toString(), conversionRule.getGrade(), conversionRule.getGradeMeaning()));
        }
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareCoScholasticGradeDataSource(List<ConversionRule> conversionRules) {
        List<SixToEigthGradeBean> beans = new ArrayList();
        for (ConversionRule conversionRule : conversionRules) {
            beans.add(new SixToEigthGradeBean("", conversionRule.getGrade(), conversionRule.getGradeMeaning()));
        }
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareCoScholasticGradeDataSource() {
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

    public static JRBeanCollectionDataSource prepareScholasticGradeDataSource() {
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
}
