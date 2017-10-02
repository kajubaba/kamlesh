package com.narendra.sams.core.address.domain;

import java.io.Serializable;

public class Country implements Serializable {
    private static final long serialVersionUID = 1007128090754821306L;
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
