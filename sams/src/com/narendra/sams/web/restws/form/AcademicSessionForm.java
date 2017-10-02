package com.narendra.sams.web.restws.form;

public class AcademicSessionForm {
    private String from;
    private Long id;
    private Long importSettingsFrom;
    private String name;
    private Short orderNo;
    private String to;

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

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Short getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(Short orderNo) {
        this.orderNo = orderNo;
    }

    public Long getImportSettingsFrom() {
        return this.importSettingsFrom;
    }

    public void setImportSettingsFrom(Long importSettingsFrom) {
        this.importSettingsFrom = importSettingsFrom;
    }
}
