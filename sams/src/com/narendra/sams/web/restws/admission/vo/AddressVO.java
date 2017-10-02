package com.narendra.sams.web.restws.admission.vo;

public class AddressVO {
    private Long addressId;
    private String addressOf;
    private String city;
    private String country;
    private Long countryId;
    private String dist;
    private String fullAddress;
    private String state;
    private Long stateId;
    private Long studentId;
    private String teh;
    private String villegeTownArea;
    private String zipCode;

    public Long getAddressId() {
        return this.addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getFullAddress() {
        return this.fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getVillegeTownArea() {
        return this.villegeTownArea;
    }

    public void setVillegeTownArea(String villegeTownArea) {
        this.villegeTownArea = villegeTownArea;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddressOf() {
        return this.addressOf;
    }

    public void setAddressOf(String addressOf) {
        this.addressOf = addressOf;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStateId() {
        return this.stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getCountryId() {
        return this.countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getTeh() {
        return this.teh;
    }

    public void setTeh(String teh) {
        this.teh = teh;
    }

    public String getDist() {
        return this.dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }
}
