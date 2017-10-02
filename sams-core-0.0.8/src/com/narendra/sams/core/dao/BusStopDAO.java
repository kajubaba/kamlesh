package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.BusStopTranslation;
import java.util.Collection;
import java.util.List;

public interface BusStopDAO {
    Long addBusStop(BusStop busStop, Long l);

    List<BusStop> getActiveBusStops(Long l);

    List<BusStop> getAllBusStops(Long l);

    List<BusStop> getAllBusStopsOfAcademicYear(Long l);

    BusStop getBusStop(Long l);

    Boolean isBusStopExist(Long l, String str);

    BusStop loadBusStopByName(Long l, String str);

    void updateBusStop(BusStop busStop, Long l);

    void updateBusStoptranslations(Collection<BusStopTranslation> collection);
}
