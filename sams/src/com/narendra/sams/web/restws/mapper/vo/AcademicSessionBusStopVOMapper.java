package com.narendra.sams.web.restws.mapper.vo;

import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.web.restws.vo.AcademicSessionBusStopVO;
import java.util.ArrayList;
import java.util.List;

public class AcademicSessionBusStopVOMapper {
    public static List<AcademicSessionBusStopVO> prepareAcademicSessionBusStopVOs(List<BusFee> busFees) {
        List<AcademicSessionBusStopVO> academicSessionBusStopVOs = new ArrayList();
        if (!(busFees == null || busFees.isEmpty())) {
            for (BusFee busFee : busFees) {
                academicSessionBusStopVOs.add(prepareAcademicSessionBusStopVO(busFee));
            }
        }
        return academicSessionBusStopVOs;
    }

    public static AcademicSessionBusStopVO prepareAcademicSessionBusStopVO(BusFee busFee) {
        if (busFee == null) {
            return null;
        }
        AcademicSessionBusStopVO academicSessionBusStopVO = new AcademicSessionBusStopVO();
        academicSessionBusStopVO.setId(busFee.getId());
        if (busFee.getBusStop() == null) {
            return academicSessionBusStopVO;
        }
        academicSessionBusStopVO.setBusStopId(busFee.getBusStop().getId());
        academicSessionBusStopVO.setBusStopName(busFee.getBusStop().getName());
        academicSessionBusStopVO.setBusFee(busFee.getRs());
        academicSessionBusStopVO.setDistance(busFee.getBusStop().getDistance());
        return academicSessionBusStopVO;
    }
}
