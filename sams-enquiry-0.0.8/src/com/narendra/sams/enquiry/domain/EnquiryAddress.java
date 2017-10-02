package com.narendra.sams.enquiry.domain;

import com.narendra.sams.core.address.domain.Country;
import com.narendra.sams.core.address.domain.State;
import java.io.Serializable;

public class EnquiryAddress implements Serializable {
    private static final long serialVersionUID = 287161393725172993L;
    private String city;
    private Country country;
    private String district;
    private Long id;
    private String line1;
    private String line2;
    private State state;
    private String teh;
    private String zipCode;

    public void copyProperties(EnquiryAddress address) {
        this.line1 = address.getLine1();
        this.line2 = address.getLine2();
        this.city = address.getCity();
        this.teh = address.getTeh();
        this.district = address.getDistrict();
        this.zipCode = address.getZipCode();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLine1() {
        return this.line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return this.line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTeh() {
        return this.teh;
    }

    public void setTeh(String teh) {
        this.teh = teh;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
