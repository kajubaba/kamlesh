package com.narendra.sams.transportation.service;

import com.narendra.sams.transportation.domain.StudentPickupDropPoint;

public interface StudentPickupDropPointService {
    void addStudentPickupDropPoint(StudentPickupDropPoint studentPickupDropPoint, Long l);

    StudentPickupDropPoint getStudentPickupDropPoint(Long l, Long l2);

    void updateStudentDropPoint(Long l, Long l2, Long l3, Long l4);

    void updateStudentPickupPoint(Long l, Long l2, Long l3, Long l4);
}
