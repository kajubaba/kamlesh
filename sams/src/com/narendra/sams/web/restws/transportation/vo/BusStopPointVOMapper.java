package com.narendra.sams.web.restws.transportation.vo;

import com.narendra.sams.transportation.domain.BusStopPoint;
import java.util.ArrayList;
import java.util.List;

public class BusStopPointVOMapper {
    public static List<BusStopPointVO> prepareBusStopPointVOs(List<BusStopPoint> busStopPoints) {
        List<BusStopPointVO> busStopPointVOs = new ArrayList();
        if (busStopPoints != null) {
            for (BusStopPoint busStopPoint : busStopPoints) {
                BusStopPointVO buStopPointVO = new BusStopPointVO();
                buStopPointVO.setId(busStopPoint.getId());
                buStopPointVO.setName(busStopPoint.getLocationName());
                buStopPointVO.setLandmark(busStopPoint.getLandmark());
                busStopPointVOs.add(buStopPointVO);
            }
        }
        return busStopPointVOs;
    }
}
