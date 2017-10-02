package com.narendra.sams.web.restws.transportation.vo;

public class StudentPickupDropPointVO {
    private String currentClassName;
    private Long dropPointId;
    private String dropPointLandmark;
    private String dropPointName;
    private Long pickupPointId;
    private String pickupPointLandmark;
    private String pickupPointName;
    private Long studentId;
    private String studentName;
    private String studentUniqueId;

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentUniqueId() {
        return this.studentUniqueId;
    }

    public void setStudentUniqueId(String studentUniqueId) {
        this.studentUniqueId = studentUniqueId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCurrentClassName() {
        return this.currentClassName;
    }

    public void setCurrentClassName(String currentClassName) {
        this.currentClassName = currentClassName;
    }

    public Long getPickupPointId() {
        return this.pickupPointId;
    }

    public void setPickupPointId(Long pickupPointId) {
        this.pickupPointId = pickupPointId;
    }

    public Long getDropPointId() {
        return this.dropPointId;
    }

    public void setDropPointId(Long dropPointId) {
        this.dropPointId = dropPointId;
    }

    public String getPickupPointName() {
        return this.pickupPointName;
    }

    public void setPickupPointName(String pickupPointName) {
        this.pickupPointName = pickupPointName;
    }

    public String getPickupPointLandmark() {
        return this.pickupPointLandmark;
    }

    public void setPickupPointLandmark(String pickupPointLandmark) {
        this.pickupPointLandmark = pickupPointLandmark;
    }

    public String getDropPointName() {
        return this.dropPointName;
    }

    public void setDropPointName(String dropPointName) {
        this.dropPointName = dropPointName;
    }

    public String getDropPointLandmark() {
        return this.dropPointLandmark;
    }

    public void setDropPointLandmark(String dropPointLandmark) {
        this.dropPointLandmark = dropPointLandmark;
    }
}
