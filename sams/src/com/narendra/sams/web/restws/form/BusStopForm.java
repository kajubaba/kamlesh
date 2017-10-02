package com.narendra.sams.web.restws.form;

public class BusStopForm {
    private Boolean active;
    private Float distance;
    private Long id;
    private String name;
    private String nameInOtherLang;

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

    public Float getDistance() {
        return this.distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getNameInOtherLang() {
        return this.nameInOtherLang;
    }

    public void setNameInOtherLang(String nameInOtherLang) {
        this.nameInOtherLang = nameInOtherLang;
    }
}
