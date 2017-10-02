package com.narendra.sams.web.restws.academics.vo;

public class NurToUKGCoExtraBean {
    private String label;
    private String value1;
    private String value2;
    private String value3;
    
public NurToUKGCoExtraBean(){
	
}
    public NurToUKGCoExtraBean(String label, String value1, String value2, String value3) {
        this.label = label;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public NurToUKGCoExtraBean(String label, String value1, String value2) {
        this.label = label;
        this.value1 = value1;
        this.value2 = value2;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue1() {
        return this.value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return this.value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return this.value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }
}
