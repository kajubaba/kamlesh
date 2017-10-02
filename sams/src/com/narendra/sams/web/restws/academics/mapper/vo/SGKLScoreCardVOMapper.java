package com.narendra.sams.web.restws.academics.mapper.vo;

import com.narendra.sams.academics.domain.AssessmentActivity;
import com.narendra.sams.academics.domain.AssessmentCriteria;
import com.narendra.sams.academics.domain.AssessmentSkill;
import com.narendra.sams.academics.domain.AssessmentSubject;
import com.narendra.sams.academics.domain.CoScholasticAssessment;
import com.narendra.sams.academics.domain.ScoreCard;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.academics.vo.GradePointBean;
import com.narendra.sams.web.restws.academics.vo.ScoreCardCoScholasticBean;
import com.narendra.sams.web.restws.academics.vo.ScoreCardScholasticBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class SGKLScoreCardVOMapper {
    public static Map<String, Object> prepareScorecard(ScoreCard scoreCard, Institute institute, Student student, Long classId, String rollNo) {
        Map<String, Object> parameters = new HashMap();
        AcademicYearClass academicYearClass = student.getActiveClassByClassId(classId).getAcademicYearClass();
        parameters.putAll(setInstituteHeaderConstantLabels());
        parameters.putAll(setInstituteHeaderValues(institute, academicYearClass));
        parameters.putAll(setStudentProfileConstantLabels());
        parameters.putAll(setStudentProfileValues(student, classId, rollNo));
        parameters.putAll(setAttendanceAndHealthStatusConstantLabels());
        parameters.putAll(setAttendanceAndHealthStatusValues(student));
        parameters.putAll(setHeightAndWeight(scoreCard.getCoScholasticAssessment()));
        parameters.putAll(setScholasticLabels());
        parameters.putAll(setGradeMeaningLabels());
        parameters.putAll(setCoScholasticLabels());
        parameters.putAll(setFooterLabels());
        parameters.putAll(setFooterValues());
        parameters.put("DSScholastic", prepareScholasticSectionDataSource(scoreCard.getScholasticAssessment().getAssessmentSubjects()));
        Float cgpa = calculateCGPA(scoreCard.getScholasticAssessment().getAssessmentSubjects());
        parameters.put("cgpa", "");
        if (cgpa != null) {
            parameters.put("cgpa", String.format("%.02f", new Object[]{cgpa}));
        }
        parameters.put("DSCoScholastic", populateScoreCardCoScholasticBean(scoreCard.getCoScholasticAssessment()));
        parameters.put("DSScholasticMean", populateGradeMeaningBean());
        return parameters;
    }

    public static JRBeanCollectionDataSource populateScoreCardCoScholasticBean(CoScholasticAssessment coScholasticAssessment) {
        List<ScoreCardCoScholasticBean> beans = new ArrayList();
        for (AssessmentActivity assessmentActivity : coScholasticAssessment.getAssessmentActivities()) {
            for (AssessmentSkill assessmentSkill : assessmentActivity.getAssessmentSkills()) {
                if (!(assessmentSkill.getIsAdditional() == null || assessmentSkill.getIsAdditional().booleanValue())) {
                    for (AssessmentCriteria assessmentCriteria : assessmentSkill.getAssessmentCriterias()) {
                        String indcator = "";
                        if (assessmentCriteria.getGradeToIndicatorMap() != null) {
                            indcator = (String) assessmentCriteria.getGradeToIndicatorMap().get(assessmentCriteria.getGradeBasedScores().get(0));
                        }
                        beans.add(new ScoreCardCoScholasticBean(assessmentSkill.getName(), assessmentCriteria.getName(), (String) assessmentCriteria.getGradeBasedScores().get(0), indcator));
                    }
                }
            }
        }
        return new JRBeanCollectionDataSource(beans);
    }

    private static Map<String, Object> setInstituteHeaderConstantLabels() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("line3", "Certificate of Continuous and Comprehensive Evaluation");
        parameters.put("performanceProfile", "Performance Profile");
        parameters.put("lblClass", "Class");
        parameters.put("lblSession", "Session");
        return parameters;
    }

    private static Map<String, Object> setInstituteHeaderValues(Institute institute, AcademicYearClass academicYearClass) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("instituteName", institute.getName());
        parameters.put("affiliatedTo", "Affiliated to CBSE. No - " + institute.getAffiliationNo());
        parameters.put("line1", institute.getAddress());
        parameters.put("line2", "Mobile : 9009284284 E-mail : shreegurukul12@gmail.com ");
        parameters.put("className", academicYearClass.getDisplayName());
        parameters.put("lblSessionName", academicYearClass.getAcademicYear().getName());
        parameters.put("DSHeader", new JREmptyDataSource());
        return parameters;
    }

    private static Map<String, Object> setStudentProfileConstantLabels() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("lblStudentProfile", "STUDENT PROFILE");
        parameters.put("lblClassAndSection", "Class & Section");
        parameters.put("lblAdmissionNo", "Admission No.");
        parameters.put("lblRollNo", "Roll No.");
        parameters.put("lblStudentName", "Name of Student");
        parameters.put("lblDOB", "Date of Birth");
        parameters.put("lblFatherName", "Father's Name");
        parameters.put("lblMotherName", "Mother's Name");
        parameters.put("lblContactNo", "Contact No.");
        parameters.put("lblAddress", "Residential Address");
        return parameters;
    }

    private static Map<String, Object> setAttendanceAndHealthStatusConstantLabels() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("lblAttendance", "ATTENDANCE");
        parameters.put("lblTerm1", "Term 1");
        parameters.put("lblTerm2", "Term 2");
        parameters.put("lblWorkingDays", "Total Working Days");
        parameters.put("lblTotalAttendance", "Total Attendance of Student");
        parameters.put("lblHealthStatus", "HEALTH STATUS");
        parameters.put("lblHeight", "Height (cm.)");
        parameters.put("lblWeight", "Weight (Kg.)");
        parameters.put("lblBloodGroup", "Blood Group");
        parameters.put("lblVision", "Vision");
        parameters.put("lblDentalHygiene", "Dental Hygiene");
        return parameters;
    }

    private static Map<String, Object> setAttendanceAndHealthStatusValues(Student student) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("dentalHygiene", "NA");
        parameters.put("visionL", "NA");
        parameters.put("visionR", "NA");
        parameters.put("bloodGroup", "NA");
        if (!(student.getBloodGroup() == null || student.getBloodGroup().trim().isEmpty())) {
            parameters.put("bloodGroup", student.getBloodGroup());
        }
        parameters.put("DSAttendanceAndHealthStatus", new JREmptyDataSource());
        return parameters;
    }

    private static Map<String, Object> setAttendanceAndHealthStatusValues() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("term1Attendance", "NA");
        parameters.put("term2Attendance", "NA");
        parameters.put("term1WorkingDays", "NA");
        parameters.put("term2WorkingDays", "NA");
        parameters.put("height", "NA");
        parameters.put("weight", "NA");
        parameters.put("bloodGroup", "NA");
        parameters.put("dentalHygiene", "NA");
        parameters.put("visionL", "NA");
        parameters.put("visionR", "NA");
        parameters.put("DSAttendanceAndHealthStatus", new JREmptyDataSource());
        return parameters;
    }

    private static Map<String, Object> setStudentProfileValues(Student student, Long classId, String rollNo) {
        Map<String, Object> parameters = new HashMap();
        AcademicYearClass academicYearClass = student.getActiveClassByClassId(classId).getAcademicYearClass();
        String section = "-";
        if (student.getActiveClassByClassId(classId).getClassSection() != null) {
            section = student.getClassSection().getSectionName();
        }
        parameters.put("admissionNo", student.getStudentId());
        parameters.put("classAndSection", academicYearClass.getDisplayName() + " (" + section + ")");
        parameters.put("rollNo", "-");
        if (rollNo != null) {
            parameters.put("rollNo", rollNo);
        }
        parameters.put("studentName", student.getFullName());
        parameters.put("dob", "");
        if (student.getDob() != null) {
            parameters.put("dob", DateUtil.formatDate(student.getDob(), "dd-MMM-yyyy"));
        }
        parameters.put("fatherName", student.getFatherName());
        parameters.put("motherName", student.getMotherName());
        parameters.put("address", "-");
        if (!(student.getLocalAddress().getLine2() == null || student.getLocalAddress().getLine2().trim().isEmpty())) {
            parameters.put("address", student.getLocalAddress().getLine2().trim());
        }
        parameters.put("contactNo", student.getFatherContact1());
        parameters.put("DSStudentProfile", new JREmptyDataSource());
        return parameters;
    }

    public static JRBeanCollectionDataSource prepareCoScholasticSectionDataSource() {
        List<ScoreCardCoScholasticBean> beans = new ArrayList();
        ScoreCardCoScholasticBean bean1 = new ScoreCardCoScholasticBean("2(A) : LIFE SKILLS", "Thinking Skills", "A", "");
        ScoreCardCoScholasticBean bean2 = new ScoreCardCoScholasticBean("2(A) : LIFE SKILLS", "Social SKills", "B", "");
        ScoreCardCoScholasticBean bean3 = new ScoreCardCoScholasticBean("2(A) : LIFE SKILLS", "Emotional SKills", "A", "");
        ScoreCardCoScholasticBean scoreCardCoScholasticBean = new ScoreCardCoScholasticBean("2(A) : LIFE SKILLS", "Creative Thinking", "B", "");
        scoreCardCoScholasticBean = new ScoreCardCoScholasticBean("2(B) : ", "Work Education", "A", "");
        scoreCardCoScholasticBean = new ScoreCardCoScholasticBean("2(C) : ", "Visual & Performing Arts", "A", "");
        ScoreCardCoScholasticBean bean11 = new ScoreCardCoScholasticBean("2(D) : ATTITUTE & VALUES", "Attitude Towards Teachers", "A", "");
        ScoreCardCoScholasticBean bean12 = new ScoreCardCoScholasticBean("2(D) : ATTITUTE & VALUES", "Attitude Towards Value System", "B", "");
        ScoreCardCoScholasticBean bean13 = new ScoreCardCoScholasticBean("2(D) : ATTITUTE & VALUES", "Attitude Towards School-mates", "B", "");
        ScoreCardCoScholasticBean bean14 = new ScoreCardCoScholasticBean("2(D) : ATTITUTE & VALUES", "School Programme and Environment", "B", "");
        ScoreCardCoScholasticBean bean15 = new ScoreCardCoScholasticBean("3(A) : CO-SCHOLASTIC ACTIVITIES", "Literary and Creative Skills", "A", "");
        ScoreCardCoScholasticBean bean16 = new ScoreCardCoScholasticBean("3(A) : CO-SCHOLASTIC ACTIVITIES", "Information & Technology (ICT)", "B", "");
        ScoreCardCoScholasticBean bean17 = new ScoreCardCoScholasticBean("3(B) : HEALTH AND PHYSICAL ACTIVITIES", "Organization & Leadership Skill", "A", "");
        ScoreCardCoScholasticBean bean18 = new ScoreCardCoScholasticBean("3(B) : HEALTH AND PHYSICAL ACTIVITIES", "Scientific Skills", "B", "");
        beans.add(bean1);
        beans.add(bean2);
        beans.add(bean3);
        beans.add(scoreCardCoScholasticBean);
        beans.add(scoreCardCoScholasticBean);
        beans.add(scoreCardCoScholasticBean);
        beans.add(bean11);
        beans.add(bean12);
        beans.add(bean13);
        beans.add(bean14);
        beans.add(bean15);
        beans.add(bean16);
        beans.add(bean17);
        beans.add(bean18);
        return new JRBeanCollectionDataSource(beans);
    }

    public static JRBeanCollectionDataSource prepareScholasticSectionDataSource(List<AssessmentSubject> assessmentSubjects) {
        List<ScoreCardScholasticBean> beans = new ArrayList();
        for (AssessmentSubject assessmentSubject : assessmentSubjects) {
            ScoreCardScholasticBean scholasticBean = new ScoreCardScholasticBean();
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

    public static void main(String[] args) {
        System.out.println(String.format("%.02f", new Object[]{Float.valueOf(123.332f)}));
    }

    public static Map<String, Object> setScholasticLabels() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("part1", "Part–1 : Academic Performance: Scholastic Areas");
        parameters.put("lblOverallTerm", "Overall (Term1 + Term2)");
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
        parameters.put("part2", "Part 2 : Co-Scholastic Areas");
        parameters.put("lblAreaOfAssessment", "Area of Assessment");
        parameters.put("lblGrade", "Grade");
        parameters.put("lblDescriptiveIndicator", "Overall Descriptive Indicator");
        return parameters;
    }

    public static Map<String, Object> setFooterLabels() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("lblResult", "RESULT : ");
        parameters.put("lblSessionBeginOn", "New Session Begins On : ");
        parameters.put("lblNote", "Parents who want to continue their children in the school next year must pay the fees on any working days before 10 April for the new session failing which continuation of the child in the school is not assured");
        parameters.put("lblPrincipal", "Principal");
        parameters.put("lblClassTeacher", "Class Teacher");
        parameters.put("lblStudent", "Student");
        return parameters;
    }

    public static Map<String, Object> setFooterValues() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("result", "Eligibile for Promotion");
        parameters.put("sessionBeginOn", "23/03/2017");
        parameters.put("DSFooter", new JREmptyDataSource());
        return parameters;
    }

    public static JRBeanCollectionDataSource populateScholasticBean() {
        List<ScoreCardScholasticBean> beans = new ArrayList();
        for (int i = 1; i < 6; i++) {
            ScoreCardScholasticBean scholasticBean = new ScoreCardScholasticBean();
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

    private static Map<String, Object> setInstituteHeaderValues() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("instituteName", "Shree Gurukul");
        parameters.put("line1", "asas");
        parameters.put("line2", "Mobile : 900092 84284 E-mail : shreegurukul12@gmail.com ");
        parameters.put("affiliatedTo", "Affiliated to CBSE. No - 101020");
        parameters.put("className", "I");
        parameters.put("lblSessionName", "2016-2017");
        parameters.put("DSHeader", new JREmptyDataSource());
        parameters.put("line1", "Chichlay Road, Behind Govt. College, Kasrawad, Dist. Khargone (M.P.)");
        return parameters;
    }

    public static Map<String, Object> setStudentProfileValues() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("admissionNo", "201");
        parameters.put("classAndSection", "I (A)");
        parameters.put("rollNo", "101");
        parameters.put("studentName", "Narendra Patidar");
        parameters.put("dob", "Mar-26-1981");
        parameters.put("fatherName", "R. Patidar");
        parameters.put("motherName", "S. Patidar");
        parameters.put("address", "Indore");
        parameters.put("contactNo", "9179190678");
        parameters.put("DSStudentProfile", new JREmptyDataSource());
        return parameters;
    }

    public static Map<String, Object> setGradeMeaningLabels() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("labelGrade", "Grade");
        parameters.put("lblMarksRange", "Marks Range");
        parameters.put("lblGradePoint", "Grade Point");
        return parameters;
    }

    public static Map<String, Object> setHeightAndWeight(CoScholasticAssessment coScholasticAssessment) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("height", "NA");
        parameters.put("weight", "NA");
        for (AssessmentActivity assessmentActivity : coScholasticAssessment.getAssessmentActivities()) {
            for (AssessmentSkill assessmentSkill : assessmentActivity.getAssessmentSkills()) {
                if (assessmentSkill.getIsAdditional() != null && assessmentSkill.getIsAdditional().booleanValue()) {
                    for (AssessmentCriteria assessmentCriteria : assessmentSkill.getAssessmentCriterias()) {
                        if ("Height(Cm.)".equalsIgnoreCase(assessmentCriteria.getName())) {
                            if (!(assessmentCriteria.getGradeBasedScores().get(0) == null || ((String) assessmentCriteria.getGradeBasedScores().get(0)).trim().isEmpty())) {
                                parameters.put("height", assessmentCriteria.getGradeBasedScores().get(0));
                            }
                        } else if (!(!"Weight(Kg.)".equalsIgnoreCase(assessmentCriteria.getName()) || assessmentCriteria.getGradeBasedScores().get(0) == null || ((String) assessmentCriteria.getGradeBasedScores().get(0)).trim().isEmpty())) {
                            parameters.put("weight", assessmentCriteria.getGradeBasedScores().get(0));
                        }
                    }
                }
            }
        }
        return parameters;
    }

    public static JRBeanCollectionDataSource populateGradeMeaningBean() {
        List<GradePointBean> beans = new ArrayList();
        GradePointBean bean1 = new GradePointBean("A1", "A2", "91-100", "81-90", "10.0", "9.0");
        GradePointBean bean2 = new GradePointBean("B1", "B2", "71-80", "61-70", "8.0", "7.0");
        GradePointBean bean3 = new GradePointBean("C1", "C2", "51-60", "41-50", "6.0", "5.0");
        GradePointBean bean4 = new GradePointBean("D", "E1", "33-40", "21-32", "4.0", "3.0");
        GradePointBean bean5 = new GradePointBean("E2", "", "0-20", "", "2.0", "");
        beans.add(bean1);
        beans.add(bean2);
        beans.add(bean3);
        beans.add(bean4);
        beans.add(bean5);
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
}
