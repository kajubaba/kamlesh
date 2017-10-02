package com.narendra.sams.transportation.domain;

import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.UserView;
import java.util.Date;

public class RouteStudent {
    private UserView createdBy;
    private Date createdDateTime;
    private String dropPoint;
    private Long dropPointId;
    private Long id;
    private UserView lastUpdatedBy;
    private Date lastUpdatedDateTime;
    private String pickupPoint;
    private Long pickupPointId;
    private RouteBusStop routeBusStop;
    private Student student;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RouteBusStop getRouteBusStop() {
        return this.routeBusStop;
    }

    public void setRouteBusStop(RouteBusStop routeBusStop) {
        this.routeBusStop = routeBusStop;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public UserView getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(UserView createdBy) {
        this.createdBy = createdBy;
    }

    public UserView getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public void setLastUpdatedBy(UserView lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getCreatedDateTime() {
        return this.createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Date getLastUpdatedDateTime() {
        return this.lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(Date lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    public String getPickupPoint() {
        return this.pickupPoint;
    }

    public void setPickupPoint(String pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public String getDropPoint() {
        return this.dropPoint;
    }

    public void setDropPoint(String dropPoint) {
        this.dropPoint = dropPoint;
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
}
