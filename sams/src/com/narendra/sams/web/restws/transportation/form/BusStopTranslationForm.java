package com.narendra.sams.web.restws.transportation.form;

import com.narendra.sams.core.domain.BusStopTranslation;
import java.util.List;

public class BusStopTranslationForm {
    private List<BusStopTranslation> busStopTranslations;

    public List<BusStopTranslation> getBusStopTranslations() {
        return this.busStopTranslations;
    }

    public void setBusStopTranslations(List<BusStopTranslation> busStopTranslations) {
        this.busStopTranslations = busStopTranslations;
    }
}
