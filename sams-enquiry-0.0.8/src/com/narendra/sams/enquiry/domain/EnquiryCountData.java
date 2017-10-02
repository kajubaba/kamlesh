package com.narendra.sams.enquiry.domain;

public class EnquiryCountData {
    private Short className;
    private String courseName;
    private Long courseType;
    private String displayName;
    private Long enquiryCount;
    private Long id;
    
    public EnquiryCountData(){
    	
    }

    public EnquiryCountData(Long id, String displayName, Long enquiryCount) {
        this.id = id;
        this.displayName = displayName;
        this.enquiryCount = enquiryCount;
    }

    public EnquiryCountData(Long id, String courseName, Short className, Long courseType, Long enquiryCount) {
        this.id = id;
        this.courseName = courseName;
        this.className = className;
        this.courseType = courseType;
        this.enquiryCount = enquiryCount;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getEnquiryCount() {
        return this.enquiryCount;
    }

    public void setEnquiryCount(Long enquiryCount) {
        this.enquiryCount = enquiryCount;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Short getClassName() {
        return this.className;
    }

    public void setClassName(Short className) {
        this.className = className;
    }

    public Long getCourseType() {
        return this.courseType;
    }

    public void setCourseType(Long courseType) {
        this.courseType = courseType;
    }
}
