package com.narendra.sams.core.domain;

public class Institute {
    private String address;
    private String affiliationNo;
    private Company company;
    private Boolean defaultInstitute;
    private Long id;
    private String line2;
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

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Boolean isDefaultInstitute() {
        if (this.defaultInstitute == null) {
            return Boolean.valueOf(false);
        }
        return this.defaultInstitute;
    }

    public void setDefaultInstitute(Boolean defaultInstitute) {
        this.defaultInstitute = defaultInstitute;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAffiliationNo() {
        return this.affiliationNo;
    }

    public void setAffiliationNo(String affiliationNo) {
        this.affiliationNo = affiliationNo;
    }

    public Boolean getDefaultInstitute() {
        return this.defaultInstitute;
    }

    public String getLine2() {
        return this.line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }
}
