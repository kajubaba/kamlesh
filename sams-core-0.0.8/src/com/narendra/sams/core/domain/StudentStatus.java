package com.narendra.sams.core.domain;

public class StudentStatus {
    public static final Long CANCELLED = Long.valueOf(1);
    public static final Long CONFIRMED = Long.valueOf(5);
    public static final Long DEGREE_AWARDED = Long.valueOf(3);
    public static final Long TEMPORARY = Long.valueOf(4);
    public static final Long TEMPORARY_RENEWAL = Long.valueOf(6);
    public static final Long TERMINATED = Long.valueOf(2);
    public static final Long[] ELIGIBLE_FOR_BIRTHDAY_WISH = new Long[]{TEMPORARY, CONFIRMED, TEMPORARY_RENEWAL};
    
    private Boolean active;
    private Boolean canAssignToStudent;
    private Long id;
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

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getCanAssignToStudent() {
        return this.canAssignToStudent;
    }

    public void setCanAssignToStudent(Boolean canAssignToStudent) {
        this.canAssignToStudent = canAssignToStudent;
    }
}
