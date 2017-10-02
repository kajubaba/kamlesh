package com.narendra.sams.web.restws.common.vo;

public class BankVO {
    private Boolean active;
    private String bankName;
    private Long id;
    
    public BankVO(){
    	
    }

    public BankVO(Long id, String bankName, Boolean active) {
        this.id = id;
        this.bankName = bankName;
        this.active = active;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
