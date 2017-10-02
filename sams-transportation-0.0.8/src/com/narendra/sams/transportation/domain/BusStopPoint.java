package com.narendra.sams.transportation.domain;

import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.UserView;
import java.util.Date;

public class BusStopPoint {
    public static String TYPE_DROP = "Drop";
    public static String TYPE_PICKUP = "Pickup";
    private BusFee busFee;
    private UserView createdBy;
    private Date createdDateTime;
    private Long id;
    private String landmark;
    private UserView lastUpdatedBy;
    private Date lastUpdatedDateTime;
    private String locationName;
    private String type;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BusFee getBusFee() {
        return this.busFee;
    }

    public void setBusFee(BusFee busFee) {
        this.busFee = busFee;
    }

    public String getLandmark() {
        return this.landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
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
}
