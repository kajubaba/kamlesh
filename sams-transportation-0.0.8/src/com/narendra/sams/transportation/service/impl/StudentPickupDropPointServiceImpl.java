package com.narendra.sams.transportation.service.impl;

import com.narendra.sams.transportation.dao.StudentPickupDropPointDAO;
import com.narendra.sams.transportation.domain.StudentPickupDropPoint;
import com.narendra.sams.transportation.service.StudentPickupDropPointService;

public class StudentPickupDropPointServiceImpl implements StudentPickupDropPointService {
    private StudentPickupDropPointDAO studentPickupDropPointDAO;

    public StudentPickupDropPointDAO getStudentPickupDropPointDAO() {
        return this.studentPickupDropPointDAO;
    }

    public void setStudentPickupDropPointDAO(StudentPickupDropPointDAO studentPickupDropPointDAO) {
        this.studentPickupDropPointDAO = studentPickupDropPointDAO;
    }

    public StudentPickupDropPoint getStudentPickupDropPoint(Long studentId, Long academicYearId) {
        return this.studentPickupDropPointDAO.getStudentPickupDropPoint(studentId, academicYearId);
    }

    public void updateStudentDropPoint(Long studentId, Long academicYearId, Long dropPointId, Long userId) {
        this.studentPickupDropPointDAO.updateStudentDropPoint(studentId, academicYearId, dropPointId, userId);
    }

    public void updateStudentPickupPoint(Long studentId, Long academicYearId, Long pickupPointId, Long userId) {
        this.studentPickupDropPointDAO.updateStudentPickupPoint(studentId, academicYearId, pickupPointId, userId);
    }

    public void addStudentPickupDropPoint(StudentPickupDropPoint studentPickupDropPoint, Long userId) {
        this.studentPickupDropPointDAO.addStudentPickupDropPoint(studentPickupDropPoint, userId);
    }
}
