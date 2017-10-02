package com.narendra.sams.academics.service.impl;

import com.narendra.sams.acad.dao.StudentSectionDAO;
import com.narendra.sams.academics.dao.ScholasticScoreDAO;
import com.narendra.sams.academics.exam.domain.ScholasticScore;
import com.narendra.sams.academics.exam.domain.StudentScore;
import com.narendra.sams.academics.service.AssessmentTypeService;
import com.narendra.sams.academics.service.ClassSubjectService;
import com.narendra.sams.academics.service.ScholasticScoreService;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.service.AdmissionListService;
import com.narendra.sams.core.domain.StudentStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ScholasticScoreServiceImpl implements ScholasticScoreService {
    private AdmissionListService admissionListService;
    private AssessmentTypeService assessmentTypeService;
    private ClassSubjectService classSubjectService;
    private ScholasticScoreDAO scholasticScoreDAO;
    private StudentSectionDAO studentSectionDAO;

    public ClassSubjectService getClassSubjectService() {
        return this.classSubjectService;
    }

    public void setClassSubjectService(ClassSubjectService classSubjectService) {
        this.classSubjectService = classSubjectService;
    }

    public ScholasticScoreDAO getScholasticScoreDAO() {
        return this.scholasticScoreDAO;
    }

    public void setScholasticScoreDAO(ScholasticScoreDAO scholasticScoreDAO) {
        this.scholasticScoreDAO = scholasticScoreDAO;
    }

    public AdmissionListService getAdmissionListService() {
        return this.admissionListService;
    }

    public void setAdmissionListService(AdmissionListService admissionListService) {
        this.admissionListService = admissionListService;
    }

    public AssessmentTypeService getAssessmentTypeService() {
        return this.assessmentTypeService;
    }

    public void setAssessmentTypeService(AssessmentTypeService assessmentTypeService) {
        this.assessmentTypeService = assessmentTypeService;
    }

    public StudentSectionDAO getStudentSectionDAO() {
        return this.studentSectionDAO;
    }

    public void setStudentSectionDAO(StudentSectionDAO studentSectionDAO) {
        this.studentSectionDAO = studentSectionDAO;
    }

    public List<StudentScore> getScholasticScore(Long classId, Long sectionId, Long subjectId, Long termAssessmentId) {
        List<StudentScore> studentScores = new ArrayList();
        if (classId == null || subjectId == null || termAssessmentId == null) {
            return null;
        }
        List<ScholasticScore> scholasticScores = this.scholasticScoreDAO.getScholasticScore(classId, sectionId, subjectId, termAssessmentId);
        List<ClassHistory> students = this.studentSectionDAO.getStudents(classId, sectionId, StudentStatus.CONFIRMED);
        StudentScore studentScore;
        if (scholasticScores == null || !scholasticScores.isEmpty()) {
            for (ScholasticScore score : scholasticScores) {
                studentScore = new StudentScore();
                studentScore.setScoreId(score.getId());
                studentScore.setStudentDBId(score.getStudent().getId());
                studentScore.setStudentId(score.getStudent().getStudentId());
                studentScore.setStudentName(score.getStudent().getFullName());
                studentScore.setStudentGender(score.getStudent().getGender());
                studentScore.setFatherName(score.getStudent().getFatherName());
                if (score.getStudent().getClassSection() != null) {
                    studentScore.setStudentSection(score.getStudent().getClassSection().getSectionName());
                }
                ClassHistory activeClass = getActiveClass(classId, score.getStudent().getClassHistories());
                if (activeClass.getClassSection() != null) {
                    studentScore.setStudentSection(activeClass.getClassSection().getSectionName());
                }
                studentScore.setMarksObtained(score.getMarksObtained());
                studentScores.add(studentScore);
            }
            if (students == null || students.isEmpty()) {
                return studentScores;
            }
            for (ClassHistory student : students) {
                Boolean found = Boolean.FALSE;
                for (StudentScore studentScore2 : studentScores) {
                    if (studentScore2.getStudentDBId().equals(student.getStudent().getId())) {
                        found = Boolean.TRUE;
                        break;
                    }
                }
                if (!found.booleanValue()) {
                	StudentScore studentScore2 = new StudentScore();
                    studentScore2.setStudentDBId(student.getStudent().getId());
                    studentScore2.setStudentId(student.getStudent().getStudentId());
                    studentScore2.setStudentName(student.getStudent().getFullName());
                    studentScore2.setStudentGender(student.getStudent().getGender());
                    studentScore2.setFatherName(student.getStudent().getFatherName());
                    if (student.getClassSection() != null) {
                        studentScore2.setStudentSection(student.getClassSection().getSectionName());
                    }
                    studentScores.add(studentScore2);
                }
            }
            return studentScores;
        }
        for (ClassHistory student2 : students) {
        	StudentScore studentScore2 = new StudentScore();
            studentScore2.setStudentDBId(student2.getStudent().getId());
            studentScore2.setStudentId(student2.getStudent().getStudentId());
            studentScore2.setStudentName(student2.getStudent().getFullName());
            studentScore2.setStudentGender(student2.getStudent().getGender());
            studentScore2.setFatherName(student2.getStudent().getFatherName());
            if (student2.getClassSection() != null) {
                studentScore2.setStudentSection(student2.getClassSection().getSectionName());
            }
            studentScores.add(studentScore2);
        }
        return studentScores;
    }

    public void saveScholasticScore(Long classId, Long subjectId, Long termAssessmentId, List<StudentScore> studentScores) {
        if (classId != null && subjectId != null && termAssessmentId != null && studentScores != null) {
            for (StudentScore studentScore : studentScores) {
                if (studentScore.getScoreId() == null) {
                    this.scholasticScoreDAO.addStudentScore(studentScore.getStudentDBId(), classId, subjectId, termAssessmentId, studentScore.getMarksObtained());
                } else {
                    this.scholasticScoreDAO.updateStudentScore(studentScore.getScoreId(), studentScore.getMarksObtained());
                }
            }
        }
    }

    private ClassHistory getActiveClass(Long classId, Set<ClassHistory> classHistories) {
        if (!(classId == null || classHistories == null || classHistories.isEmpty())) {
            for (ClassHistory ch : classHistories) {
                if (classId.equals(ch.getAcademicYearClass().getId()) && ch.getActiveClass().booleanValue()) {
                    return ch;
                }
            }
        }
        return null;
    }

    public ScholasticScore getStudnetScholasticScore(Long studentId, Long studentClassId, Long assessmentId, Long subjectId) {
        return this.scholasticScoreDAO.getStudnetScholasticScore(studentId, studentClassId, assessmentId, subjectId);
    }
}
