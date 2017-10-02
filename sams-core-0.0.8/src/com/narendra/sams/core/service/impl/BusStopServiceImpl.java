package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.BusStopDAO;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.BusStopTranslation;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.BusStopService;
import java.util.Collection;
import java.util.List;

public class BusStopServiceImpl implements BusStopService {
    private BusStopDAO busStopDAO;

    public BusStopDAO getBusStopDAO() {
        return this.busStopDAO;
    }

    public void setBusStopDAO(BusStopDAO busStopDAO) {
        this.busStopDAO = busStopDAO;
    }

    public Long saveBusStop(BusStop busStop, Long userId) throws DuplicateNameFoundException {
        if (busStop == null) {
            return null;
        }
        Long busStopId = busStop.getId();
        if (busStop.getId() != null) {
            BusStop loadedBusStop = this.busStopDAO.loadBusStopByName(busStop.getInstitute().getId(), busStop.getName().trim());
            if (loadedBusStop == null || loadedBusStop.getId().equals(busStop.getId())) {
                this.busStopDAO.updateBusStop(busStop, userId);
                return busStopId;
            }
            throw new DuplicateNameFoundException("Bus stop ['" + busStop.getName() + "'] already exists");
        } else if (!this.busStopDAO.isBusStopExist(busStop.getInstitute().getId(), busStop.getName()).booleanValue()) {
            return this.busStopDAO.addBusStop(busStop, userId);
        } else {
            throw new DuplicateNameFoundException("Bus stop ['" + busStop.getName() + "'] already exist");
        }
    }

    public List<BusStop> getAllBusStops(Long instituteId) {
        return this.busStopDAO.getAllBusStops(instituteId);
    }

    public List<BusStop> getActiveBusStops(Long instituteId) {
        return this.busStopDAO.getActiveBusStops(instituteId);
    }

    public BusStop getBusStop(Long busStopId) {
        if (busStopId == null) {
            return null;
        }
        return this.busStopDAO.getBusStop(busStopId);
    }

    public List<BusStop> getAllBusStopsOfAcademicYear(Long academicYearId) {
        return this.busStopDAO.getAllBusStopsOfAcademicYear(academicYearId);
    }

    public void updateBusStoptranslations(Collection<BusStopTranslation> busStopTranslations) {
        if (busStopTranslations != null) {
            this.busStopDAO.updateBusStoptranslations(busStopTranslations);
        }
    }
}
