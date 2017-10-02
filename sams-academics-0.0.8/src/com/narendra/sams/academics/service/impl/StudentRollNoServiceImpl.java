package com.narendra.sams.academics.service.impl;

import com.narendra.sams.acad.dao.StudentSectionDAO;
import com.narendra.sams.academics.dao.StudentRollNoDAO;
import com.narendra.sams.academics.exam.domain.StudentRollNo;
import com.narendra.sams.academics.service.StudentRollNoService;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.core.domain.StudentStatus;
import java.util.ArrayList;
import java.util.List;

public class StudentRollNoServiceImpl implements StudentRollNoService {
    private StudentRollNoDAO studentRollNoDAO;
    private StudentSectionDAO studentSectionDAO;

    public StudentRollNoDAO getStudentRollNoDAO() {
        return this.studentRollNoDAO;
    }

    public void setStudentRollNoDAO(StudentRollNoDAO studentRollNoDAO) {
        this.studentRollNoDAO = studentRollNoDAO;
    }

    public StudentSectionDAO getStudentSectionDAO() {
        return this.studentSectionDAO;
    }

    public void setStudentSectionDAO(StudentSectionDAO studentSectionDAO) {
        this.studentSectionDAO = studentSectionDAO;
    }

    public List<StudentRollNo> getStudentRollNos(Long classId, Long sectionId) {
        List<StudentRollNo> list = new ArrayList();
        if (classId == null) {
            return null;
        }
        List<StudentRollNo> studentRollNos = this.studentRollNoDAO.getStudentRollNos(classId, sectionId);
        List<ClassHistory> classHistories = this.studentSectionDAO.getStudents(classId, sectionId, StudentStatus.CONFIRMED);
        StudentRollNo studentRollNo;
        if (studentRollNos != null && (studentRollNos == null || !studentRollNos.isEmpty())) {
            StudentRollNo item;
            for (StudentRollNo studentRollNo2 : studentRollNos) {
                item = new StudentRollNo();
                item.setId(studentRollNo2.getId());
                item.setStudent(studentRollNo2.getStudent());
                item.setStudentClass(studentRollNo2.getStudentClass());
                item.setRollNo(studentRollNo2.getRollNo());
                list.add(item);
            }
            if (classHistories == null || classHistories.isEmpty()) {
                return list;
            }
            for (ClassHistory classHistory : classHistories) {
                Boolean found = Boolean.FALSE;
                for (StudentRollNo studentRollNo22 : list) {
                    if (studentRollNo22.getStudent().getId().equals(classHistory.getStudent().getId())) {
                        found = Boolean.TRUE;
                        break;
                    }
                }
                if (!found.booleanValue()) {
                    item = new StudentRollNo();
                    item.setStudent(classHistory.getStudent());
                    item.setStudentClass(classHistory.getAcademicYearClass());
                    list.add(item);
                }
            }
            return list;
        } else if (classHistories == null || classHistories.isEmpty()) {
            return list;
        } else {
            for (ClassHistory classHistory2 : classHistories) {
            	StudentRollNo studentRollNo22 = new StudentRollNo();
                studentRollNo22.setStudent(classHistory2.getStudent());
                studentRollNo22.setStudentClass(classHistory2.getAcademicYearClass());
                list.add(studentRollNo22);
            }
            return list;
        }
    }

    public void saveStudentRollNos(Long classId, List<StudentRollNo> studentRollNos, Long userId) {
        if (classId != null && studentRollNos != null) {
            this.studentRollNoDAO.saveStudentRollNos(studentRollNos, userId);
        }
    }

    public StudentRollNo getStudentRollNo(Long studentId, Long classId) {
        return this.studentRollNoDAO.getStudentRollNo(studentId, classId);
    }
}
