package com.narendra.sams.web.restws.transportation.vo;

import java.util.List;

public class StudentTransportationDetailsVO {
    private String academicYear;
    private String address;
    private StudentRouteDetailsVO arrivalInfo;
    private String busStop;
    private String city;
    private String classSection;
    private String currentClass;
    private StudentRouteDetailsVO departureInfo;
    private List<BusStopPointVO> dropPoints;
    private String fatherContactNo;
    private String fatherName;
    private String gender;
    private Long id;
    private String motherContactNo;
    private String motherName;
    private String name;
    private String picURL;
    private List<BusStopPointVO> pickupPoints;
    private String studentContactNo;
    private String studentId;
    private String studentStatus;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return this.motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCurrentClass() {
        return this.currentClass;
    }

    public void setCurrentClass(String currentClass) {
        this.currentClass = currentClass;
    }

    public String getClassSection() {
        return this.classSection;
    }

    public void setClassSection(String classSection) {
        this.classSection = classSection;
    }

    public String getBusStop() {
        return this.busStop;
    }

    public void setBusStop(String busStop) {
        this.busStop = busStop;
    }

    public String getStudentContactNo() {
        return this.studentContactNo;
    }

    public void setStudentContactNo(String studentContactNo) {
        this.studentContactNo = studentContactNo;
    }

    public String getFatherContactNo() {
        return this.fatherContactNo;
    }

    public void setFatherContactNo(String fatherContactNo) {
        this.fatherContactNo = fatherContactNo;
    }

    public String getMotherContactNo() {
        return this.motherContactNo;
    }

    public void setMotherContactNo(String motherContactNo) {
        this.motherContactNo = motherContactNo;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStudentStatus() {
        return this.studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPicURL() {
        return this.picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    public StudentRouteDetailsVO getArrivalInfo() {
        return this.arrivalInfo;
    }

    public void setArrivalInfo(StudentRouteDetailsVO arrivalInfo) {
        this.arrivalInfo = arrivalInfo;
    }

    public StudentRouteDetailsVO getDepartureInfo() {
        return this.departureInfo;
    }

    public void setDepartureInfo(StudentRouteDetailsVO departureInfo) {
        this.departureInfo = departureInfo;
    }

    public List<BusStopPointVO> getPickupPoints() {
        return this.pickupPoints;
    }

    public void setPickupPoints(List<BusStopPointVO> pickupPoints) {
        this.pickupPoints = pickupPoints;
    }

    public List<BusStopPointVO> getDropPoints() {
        return this.dropPoints;
    }

    public void setDropPoints(List<BusStopPointVO> dropPoints) {
        this.dropPoints = dropPoints;
    }

    public String getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
}
