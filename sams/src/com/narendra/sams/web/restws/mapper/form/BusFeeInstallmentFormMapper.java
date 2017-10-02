package com.narendra.sams.web.restws.mapper.form;

import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusFeeDetail;
import com.narendra.sams.core.domain.Installment;
import com.narendra.sams.web.restws.vo.BusFeeDetailVO;
import com.narendra.sams.web.restws.vo.BusFeeVO;
import com.narendra.sams.web.restws.vo.BusStopFeeVO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BusFeeInstallmentFormMapper {
    public static List<BusFee> prepareBusFeeInstallments(BusFeeVO busFeeVO) {
        List<BusFee> list = null;
        if (!(busFeeVO == null || busFeeVO.getBusStopFees() == null || busFeeVO.getBusStopFees().isEmpty())) {
            list = new ArrayList();
            for (BusStopFeeVO busStopFeeVO : busFeeVO.getBusStopFees()) {
                BusFee busFee = new BusFee();
                busFee.setId(busStopFeeVO.getId());
                busFee.setBusFeeDetails(new HashSet());
                for (BusFeeDetailVO busFeeDetailVO : busStopFeeVO.getBusFeeDetails()) {
                    BusFeeDetail busFeeDetail = new BusFeeDetail();
                    busFeeDetail.setId(busFeeDetailVO.getId());
                    if (busFeeDetailVO.getFee() == null) {
                        busFeeDetail.setFee(Long.valueOf(0));
                    } else {
                        busFeeDetail.setFee(busFeeDetailVO.getFee());
                    }
                    Installment installment = new Installment();
                    installment.setId(busFeeDetailVO.getInstallmentId());
                    busFeeDetail.setInstallment(installment);
                    busFeeDetail.setBusFee(busFee);
                    busFee.getBusFeeDetails().add(busFeeDetail);
                }
                list.add(busFee);
            }
        }
        return list;
    }
}
