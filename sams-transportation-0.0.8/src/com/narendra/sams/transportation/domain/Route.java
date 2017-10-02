package com.narendra.sams.transportation.domain;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.UserView;
import java.util.Date;
import java.util.Set;

public class Route {
    public static String ROUTE_TYPE_ARRIVAL = "Arrival";
    public static String ROUTE_TYPE_DEPARTUTE = "Departure";
    private AcademicYear academicYear;
    private AcademicYearVehicle academicYearVehicle;
    private Set<RouteBusStop> busStops;
    private UserView createdBy;
    private Date createdDateTime;
    private String from;
    private Long id;
    private UserView lastUpdatedBy;
    private Date lastUpdatedDateTime;
    private String name;
    private Integer plannedStudents;
    private String to;
    private String type;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPlannedStudents() {
        return this.plannedStudents;
    }

    public void setPlannedStudents(Integer plannedStudents) {
        this.plannedStudents = plannedStudents;
    }

    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
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

    public Set<RouteBusStop> getBusStops() {
        return this.busStops;
    }

    public void setBusStops(Set<RouteBusStop> busStops) {
        this.busStops = busStops;
    }

    public AcademicYearVehicle getAcademicYearVehicle() {
        return this.academicYearVehicle;
    }

    public void setAcademicYearVehicle(AcademicYearVehicle academicYearVehicle) {
        this.academicYearVehicle = academicYearVehicle;
    }

    public Long getrouteStudentsCount() {
        Long count = Long.valueOf(0);
        if (this.busStops != null) {
            for (RouteBusStop routeBusStop : this.busStops) {
                if (routeBusStop.getBusStopStudents() != null) {
                    count = Long.valueOf(count.longValue() + ((long) routeBusStop.getBusStopStudents().size()));
                }
            }
        }
        return count;
    }
}
