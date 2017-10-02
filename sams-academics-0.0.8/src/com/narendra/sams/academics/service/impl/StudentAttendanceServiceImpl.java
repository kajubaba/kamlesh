package com.narendra.sams.academics.service.impl;

import com.narendra.sams.acad.dao.StudentSectionDAO;
import com.narendra.sams.academics.dao.StudentAttendanceDAO;
import com.narendra.sams.academics.domain.Attendance;
import com.narendra.sams.academics.domain.StudentAttendance;
import com.narendra.sams.academics.exam.domain.EvaluationTerm;
import com.narendra.sams.academics.service.StudentAttendanceService;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.StudentStatus;
import java.util.ArrayList;
import java.util.List;

public class StudentAttendanceServiceImpl implements StudentAttendanceService {
    private StudentAttendanceDAO studentAttendanceDAO;
    private StudentSectionDAO studentSectionDAO;

    public StudentAttendanceDAO getStudentAttendanceDAO() {
        return this.studentAttendanceDAO;
    }

    public void setStudentAttendanceDAO(StudentAttendanceDAO studentAttendanceDAO) {
        this.studentAttendanceDAO = studentAttendanceDAO;
    }

    public StudentSectionDAO getStudentSectionDAO() {
        return this.studentSectionDAO;
    }

    public void setStudentSectionDAO(StudentSectionDAO studentSectionDAO) {
        this.studentSectionDAO = studentSectionDAO;
    }

    public List<Attendance> getTermAttendance(Long termId, Long classId, Long sectionId) {
        List<Attendance> attendances = new ArrayList();
        if (termId == null || classId == null) {
            return null;
        }
        List<StudentAttendance> studentAttendances = this.studentAttendanceDAO.getTermAttendance(termId, classId, sectionId);
        List<ClassHistory> students = this.studentSectionDAO.getStudents(classId, sectionId, StudentStatus.CONFIRMED);
        Attendance attendance;
        if (studentAttendances != null && (studentAttendances == null || !studentAttendances.isEmpty())) {
            for (StudentAttendance studentAttendance : studentAttendances) {
                attendance = new Attendance();
                attendance.setAttendanceId(studentAttendance.getId());
                attendance.setStudentDBId(studentAttendance.getStudent().getId());
                attendance.setStudentId(studentAttendance.getStudent().getStudentId());
                attendance.setStudentName(studentAttendance.getStudent().getFullName());
                attendance.setStudentGender(studentAttendance.getStudent().getGender());
                attendance.setFatherName(studentAttendance.getStudent().getFatherName());
                ClassHistory activeClass = studentAttendance.getStudent().getActiveClassByClassId(classId);
                if (activeClass.getClassSection() != null) {
                    attendance.setStudentSection(activeClass.getClassSection().getSectionName());
                }
                attendance.setAttendance(studentAttendance.getAttendance());
                attendances.add(attendance);
            }
            if (students == null || students.isEmpty()) {
                return attendances;
            }
            for (ClassHistory student : students) {
                Boolean found = Boolean.FALSE;
                for (Attendance attendance2 : attendances) {
                    if (attendance2.getStudentDBId().equals(student.getStudent().getId())) {
                        found = Boolean.TRUE;
                        break;
                    }
                }
                if (!found.booleanValue()) {
                	Attendance attendance2 = new Attendance();
                    attendance2.setStudentDBId(student.getStudent().getId());
                    attendance2.setStudentId(student.getStudent().getStudentId());
                    attendance2.setStudentName(student.getStudent().getFullName());
                    attendance2.setStudentGender(student.getStudent().getGender());
                    attendance2.setFatherName(student.getStudent().getFatherName());
                    if (student.getClassSection() != null) {
                        attendance2.setStudentSection(student.getClassSection().getSectionName());
                    }
                    attendances.add(attendance2);
                }
            }
            return attendances;
        } else if (students == null || students.isEmpty()) {
            return attendances;
        } else {
            for (ClassHistory student2 : students) {
            	Attendance attendance2 = new Attendance();
                attendance2.setStudentDBId(student2.getStudent().getId());
                attendance2.setStudentId(student2.getStudent().getStudentId());
                attendance2.setStudentName(student2.getStudent().getFullName());
                attendance2.setStudentGender(student2.getStudent().getGender());
                attendance2.setFatherName(student2.getStudent().getFatherName());
                if (student2.getClassSection() != null) {
                    attendance2.setStudentSection(student2.getClassSection().getSectionName());
                }
                attendances.add(attendance2);
            }
            return attendances;
        }
    }

    public void saveTermAttendance(Long termId, Long classId, List<Attendance> attendances, Long userId) {
        if (termId != null && classId != null && attendances != null) {
            for (Attendance attendance : attendances) {
                StudentAttendance studentAttendance = new StudentAttendance();
                studentAttendance.setId(attendance.getAttendanceId());
                studentAttendance.setAttendance(attendance.getAttendance());
                if (studentAttendance.getId() == null) {
                    AcademicYearClass academicYearClass = new AcademicYearClass();
                    academicYearClass.setId(classId);
                    studentAttendance.setAcademicYearClass(academicYearClass);
                    EvaluationTerm term = new EvaluationTerm();
                    term.setId(termId);
                    studentAttendance.setEvaluationTerm(term);
                    Student student = new Student();
                    student.setId(attendance.getStudentDBId());
                    studentAttendance.setStudent(student);
                }
                this.studentAttendanceDAO.saveStudentAttendance(studentAttendance, userId);
            }
        }
    }

    public StudentAttendance getStudentAttendance(Long studentId, Long classId, Long termId) {
        return this.studentAttendanceDAO.getStudentAttendance(studentId, classId, termId);
    }
}
