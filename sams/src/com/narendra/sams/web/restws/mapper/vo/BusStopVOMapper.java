package com.narendra.sams.web.restws.mapper.vo;

import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.vo.BusStopVO;
import java.util.ArrayList;
import java.util.List;

public class BusStopVOMapper {
    public static List<BusStopVO> prepareBusStopVOs(List<BusStop> busStops) {
        List<BusStopVO> busStopVOs = new ArrayList();
        if (!(busStops == null || busStops.isEmpty())) {
            for (BusStop busStop : busStops) {
                busStopVOs.add(prepareBusStopVO(busStop));
            }
        }
        return busStopVOs;
    }

    public static BusStopVO prepareBusStopVO(BusStop busStop) {
        if (busStop == null) {
            return null;
        }
        BusStopVO busStopVO = new BusStopVO();
        busStopVO.setId(busStop.getId());
        busStopVO.setName(busStop.getName());
        busStopVO.setNameInOtherLang(busStop.getNameInOtherLang());
        busStopVO.setDistance(busStop.getDistance());
        busStopVO.setActive(busStop.getActive());
        if (busStop.getCreatedBy() != null) {
            busStopVO.setCreatedBy(busStop.getCreatedBy().getFullName());
        }
        if (busStop.getCreatedDate() != null) {
            busStopVO.setCreatedOn(DateUtil.formatDate(busStop.getCreatedDate(), "dd-MMM-yyyy hh:mm a"));
        }
        if (busStop.getModifiedBy() != null) {
            busStopVO.setModifiedBy(busStop.getModifiedBy().getFullName());
        }
        if (busStop.getModifiedDate() == null) {
            return busStopVO;
        }
        busStopVO.setModifiedOn(DateUtil.formatDate(busStop.getModifiedDate(), "dd-MMM-yyyy hh:mm a"));
        return busStopVO;
    }
}
