package com.narendra.sams.transportation.domain;

import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.UserView;
import java.util.Date;

public class StudentPickupDropPoint {
    private AcademicYear academicYear;
    private BusFee academicYearBusStop;
    private BusStopPoint dropPoint;
    private Long id;
    private UserView lastModifiedBy;
    private Date lastModifiedDateTime;
    private BusStopPoint pickupPoint;
    private Student student;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public BusFee getAcademicYearBusStop() {
        return this.academicYearBusStop;
    }

    public void setAcademicYearBusStop(BusFee academicYearBusStop) {
        this.academicYearBusStop = academicYearBusStop;
    }

    public BusStopPoint getPickupPoint() {
        return this.pickupPoint;
    }

    public void setPickupPoint(BusStopPoint pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public BusStopPoint getDropPoint() {
        return this.dropPoint;
    }

    public void setDropPoint(BusStopPoint dropPoint) {
        this.dropPoint = dropPoint;
    }

    public UserView getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy(UserView lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDateTime() {
        return this.lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(Date lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }
}
