package com.narendra.sams.core.domain;

import java.util.Set;

public class Company {
    public static Long DEFAULT_COMPANY = Long.valueOf(1);
    private Long id;
    private Set<Institute> institutes;
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

    public Set<Institute> getInstitutes() {
        return this.institutes;
    }

    public void setInstitutes(Set<Institute> institutes) {
        this.institutes = institutes;
    }
}
