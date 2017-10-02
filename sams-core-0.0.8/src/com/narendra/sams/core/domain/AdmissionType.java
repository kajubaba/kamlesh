package com.narendra.sams.core.domain;

import java.io.Serializable;

public class AdmissionType implements Serializable {
    public static final Short NEW_ADMISSION_ID = Short.valueOf(Integer.valueOf(1).shortValue());
    public static final Short REGULAR_ADMISSION_ID = Short.valueOf(Integer.valueOf(2).shortValue());
    private static final long serialVersionUID = 6833940744591784428L;
    private Short id;
    private String name;

    public Short getId() {
        return this.id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
