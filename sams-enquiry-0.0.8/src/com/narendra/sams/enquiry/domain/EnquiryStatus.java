package com.narendra.sams.enquiry.domain;

import com.narendra.sams.core.domain.Institute;

public class EnquiryStatus {
    public static String CANCEL = "Cancel";
    public static String COLD = "Cold";
    public static String CONVERTED_INTO_ADMISSION = "Converted Into Admission";
    public static String HOT = "Hot";
    public static String IN_PROGRESS = "In-Progress";
    public static String NEW = "New";
    public static String WARM = "Warm";
    private Boolean active;
    private Long id;
    private Institute institute;
    private String name;

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

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
