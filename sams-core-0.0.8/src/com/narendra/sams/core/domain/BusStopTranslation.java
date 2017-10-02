package com.narendra.sams.core.domain;

public class BusStopTranslation {
    private Long busStopId;
    private String busStopName;
    private String busStopNameInOtherLanguage;

    public Long getBusStopId() {
        return this.busStopId;
    }

    public void setBusStopId(Long busStopId) {
        this.busStopId = busStopId;
    }

    public String getBusStopName() {
        return this.busStopName;
    }

    public void setBusStopName(String busStopName) {
        this.busStopName = busStopName;
    }

    public String getBusStopNameInOtherLanguage() {
        return this.busStopNameInOtherLanguage;
    }

    public void setBusStopNameInOtherLanguage(String busStopNameInOtherLanguage) {
        this.busStopNameInOtherLanguage = busStopNameInOtherLanguage;
    }
}
