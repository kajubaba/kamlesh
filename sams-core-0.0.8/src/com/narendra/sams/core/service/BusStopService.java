package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.BusStopTranslation;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import java.util.Collection;
import java.util.List;

public interface BusStopService {
    List<BusStop> getActiveBusStops(Long l);

    List<BusStop> getAllBusStops(Long l);

    List<BusStop> getAllBusStopsOfAcademicYear(Long l);

    BusStop getBusStop(Long l);

    Long saveBusStop(BusStop busStop, Long l) throws DuplicateNameFoundException;

    void updateBusStoptranslations(Collection<BusStopTranslation> collection);
}
