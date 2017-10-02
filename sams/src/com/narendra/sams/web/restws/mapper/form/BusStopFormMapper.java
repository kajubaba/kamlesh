package com.narendra.sams.web.restws.mapper.form;

import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.web.restws.form.BusStopForm;
import com.narendra.sams.web.restws.vo.AcademicSessionBusStopVO;
import java.util.ArrayList;
import java.util.List;

public class BusStopFormMapper {
    public static BusStop prepareBusStopDomain(BusStopForm busStopForm) {
        if (busStopForm == null) {
            return null;
        }
        BusStop busStop = new BusStop();
        busStop.setId(busStopForm.getId());
        busStop.setName(busStopForm.getName());
        busStop.setNameInOtherLang(busStopForm.getNameInOtherLang());
        busStop.setDistance(busStopForm.getDistance());
        busStop.setActive(busStopForm.getActive());
        return busStop;
    }

    public static List<BusFee> prepareBusStopsFeeDomain(List<AcademicSessionBusStopVO> academicSessionBusStopVOs) {
        if (academicSessionBusStopVOs == null) {
            return null;
        }
        List<BusFee> busFees = new ArrayList();
        for (AcademicSessionBusStopVO academicSessionBusStopVO : academicSessionBusStopVOs) {
            BusFee busFee = new BusFee();
            busFee.setId(academicSessionBusStopVO.getId());
            busFee.setRs(academicSessionBusStopVO.getBusFee());
            busFees.add(busFee);
        }
        return busFees;
    }
}
