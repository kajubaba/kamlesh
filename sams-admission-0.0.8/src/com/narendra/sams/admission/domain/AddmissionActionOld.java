package com.narendra.sams.admission.domain;

public class AddmissionActionOld {
    public static final Long ADDMISSION_RENEWAL = Long.valueOf(6);
    public static final Long ASSIGN_BUS_STOP = Long.valueOf(7);
    public static final Long CHANGE_BUS_STOP = Long.valueOf(3);
    public static final Long CHANGE_CLASS = Long.valueOf(23);
    public static final Long CHANGE_STATUS = Long.valueOf(1);
    public static final Long FEE_DEPOSIT = Long.valueOf(4);
    public static final Long PROMOTE_CLASS = Long.valueOf(5);
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
}
