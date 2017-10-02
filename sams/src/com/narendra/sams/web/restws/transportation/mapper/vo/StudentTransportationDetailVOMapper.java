package com.narendra.sams.web.restws.transportation.mapper.vo;

import com.narendra.sams.admission.domain.Address;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.transportation.domain.BusStopPoint;
import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.web.restws.transportation.vo.StudentRouteDetailsVO;
import com.narendra.sams.web.restws.transportation.vo.StudentTransportationDetailsVO;
import com.narendra.sams.web.utils.StudentInformationUtil;

public class StudentTransportationDetailVOMapper {
    public static StudentTransportationDetailsVO prepareStudentVO(Student student) {
        StudentTransportationDetailsVO studentVO = new StudentTransportationDetailsVO();
        studentVO.setId(student.getId());
        studentVO.setStudentId(student.getStudentId());
        studentVO.setGender(student.getGender());
        studentVO.setFatherName(student.getFatherName());
        studentVO.setMotherName(student.getMotherName());
        studentVO.setAcademicYear(student.getAcademicYear().getName());
        studentVO.setName(StudentInformationUtil.getFullName(student.getFirstName(), student.getMiddleName(), student.getLastName()));
        studentVO.setCurrentClass(StudentInformationUtil.getClassName(student.getAcademicYearClass()));
        studentVO.setStudentContactNo(StudentInformationUtil.prepareContactNo(student.getPhone1(), student.getPhone2()));
        studentVO.setFatherContactNo(StudentInformationUtil.prepareContactNo(student.getFatherContact1(), student.getFatherContact2()));
        studentVO.setMotherContactNo(StudentInformationUtil.prepareContactNo(student.getMotherContact1(), student.getMotherContact2()));
        studentVO.setAddress(StudentInformationUtil.prepareStduentLocalAddress(student.getAddresses()));
        if (student.getAddresses() != null) {
            for (Address add : student.getAddresses()) {
                if (Address.ADDRESS_TYPE_LOCAL.equals(add.getAddressOf())) {
                    studentVO.setCity(add.getCity());
                    break;
                }
            }
        }
        if (student.getClassSection() != null) {
            studentVO.setClassSection(student.getClassSection().getSectionName());
        }
        if (student.getStudentStatus() != null) {
            studentVO.setStudentStatus(student.getStudentStatus().getName());
        }
        if (student.getBusStop() != null) {
            studentVO.setBusStop(student.getBusStop().getName());
        }
        studentVO.setPicURL("resources/studentpics/" + student.getImageName());
        return studentVO;
    }

    public static StudentRouteDetailsVO prepareStudentRouteDetailsVs(BusStopPoint busStopPoint, Route route) {
        StudentRouteDetailsVO studentRouteDetailsVO = new StudentRouteDetailsVO();
        if (route != null) {
            studentRouteDetailsVO.setAcademicYearRouteId(route.getId());
            studentRouteDetailsVO.setRouteName(route.getName());
            if (route.getAcademicYearVehicle() != null) {
                studentRouteDetailsVO.setBus(route.getAcademicYearVehicle().getVehicle().getVehicleName());
                studentRouteDetailsVO.setBusNo(route.getAcademicYearVehicle().getVehicle().getVehicleId());
                studentRouteDetailsVO.setAcademicYearVehicleId(route.getAcademicYearVehicle().getId());
                if (route.getAcademicYearVehicle().getDriver() != null) {
                    studentRouteDetailsVO.setDriverName(route.getAcademicYearVehicle().getDriver().getName());
                    studentRouteDetailsVO.setDriverContactNo(route.getAcademicYearVehicle().getDriver().getPrimaryContact());
                }
                route.getAcademicYearVehicle().getConductor();
            }
        }
        if (busStopPoint != null) {
            studentRouteDetailsVO.setPoint(busStopPoint.getLocationName());
            studentRouteDetailsVO.setPointId(busStopPoint.getId());
        }
        return studentRouteDetailsVO;
    }
}
