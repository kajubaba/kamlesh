package com.narendra.sams.web.restws.mapper.vo;

import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusFeeDetail;
import com.narendra.sams.core.domain.BusFeeInstallment;
import com.narendra.sams.core.domain.BusFeeInstallmentDetail;
import com.narendra.sams.core.domain.Installment;
import com.narendra.sams.web.restws.vo.BusFeeDetailVO;
import com.narendra.sams.web.restws.vo.BusFeeInstallmentPercentageVO;
import com.narendra.sams.web.restws.vo.BusFeeVO;
import com.narendra.sams.web.restws.vo.BusStopFeeVO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;

public class BusFeeInstallmentVOMapper {
    public static BusFeeVO prepareBusFeeInstallmentVO(BusFeeInstallment busFeeInstallment) {
        BusFeeVO busFeeVO = new BusFeeVO();
        if (!(busFeeInstallment == null || busFeeInstallment.getBusFeeInstallmentDetails() == null || busFeeInstallment.getBusFeeInstallmentDetails().isEmpty())) {
            busFeeVO.setBusFeeInstallmentInPercentage(new ArrayList());
            for (BusFeeInstallmentDetail busFeeInstallmentDetail : busFeeInstallment.getBusFeeInstallmentDetails()) {
                busFeeVO.getBusFeeInstallmentInPercentage().add(prepareBusFeeInstallmentDetailVO(busFeeInstallmentDetail));
            }
            Collections.sort(busFeeVO.getBusFeeInstallmentInPercentage(), new BeanComparator("displayOrder", new NullComparator()));
            busFeeVO.setInstallments(busFeeInstallment.getInstallmentCount());
        }
        return busFeeVO;
    }

    public static List<BusFeeInstallmentPercentageVO> prepareBusFeeInstallmentVO(BusFeeInstallment busFeeInstallment, List<Installment> installments) {
        Installment installment;
        List<BusFeeInstallmentPercentageVO> busFeeInstallmentPercentageVOs = new ArrayList();
        if (busFeeInstallment == null) {
            for (Installment installment2 : installments) {
                BusFeeInstallmentPercentageVO busFeeInstallmentDetailVO = new BusFeeInstallmentPercentageVO();
                busFeeInstallmentDetailVO.setInstallmentId(installment2.getId());
                busFeeInstallmentDetailVO.setInstallmentName(installment2.getName());
                busFeeInstallmentDetailVO.setDisplayOrder(installment2.getDisplayOrder());
                busFeeInstallmentPercentageVOs.add(busFeeInstallmentDetailVO);
            }
        } else {
            for (BusFeeInstallmentDetail busFeeInstallmentDetail : busFeeInstallment.getBusFeeInstallmentDetails()) {
                busFeeInstallmentPercentageVOs.add(prepareBusFeeInstallmentDetailVO(busFeeInstallmentDetail));
            }
        }
        BeanComparator beanComparator = new BeanComparator("displayOrder", new NullComparator());
        Collections.sort(busFeeInstallmentPercentageVOs, beanComparator);
        if (installments.size() > busFeeInstallmentPercentageVOs.size()) {
            List<BusFeeInstallmentPercentageVO> additionalInstallments = new ArrayList();
            for (int i = busFeeInstallmentPercentageVOs.size(); i < installments.size(); i++) {
            	Installment  installment2 = (Installment) installments.get(i);
                BusFeeInstallmentPercentageVO busFeeInstallmentDetailVO = new BusFeeInstallmentPercentageVO();
                busFeeInstallmentDetailVO.setInstallmentId(installment2.getId());
                busFeeInstallmentDetailVO.setInstallmentName(installment2.getName());
                busFeeInstallmentDetailVO.setDisplayOrder(installment2.getDisplayOrder());
                additionalInstallments.add(busFeeInstallmentDetailVO);
            }
            busFeeInstallmentPercentageVOs.addAll(additionalInstallments);
        } else {
            busFeeInstallmentPercentageVOs = busFeeInstallmentPercentageVOs.subList(0, installments.size());
        }
        Collections.sort(busFeeInstallmentPercentageVOs, beanComparator);
        return busFeeInstallmentPercentageVOs;
    }

    private static BusFeeInstallmentPercentageVO prepareBusFeeInstallmentDetailVO(BusFeeInstallmentDetail busFeeInstallmentDetail) {
        if (busFeeInstallmentDetail == null) {
            return null;
        }
        BusFeeInstallmentPercentageVO busFeeInstallmentDetailVO = new BusFeeInstallmentPercentageVO();
        busFeeInstallmentDetailVO.setId(busFeeInstallmentDetail.getId());
        busFeeInstallmentDetailVO.setInstallmentId(busFeeInstallmentDetail.getInstallment().getId());
        busFeeInstallmentDetailVO.setInstallmentName(busFeeInstallmentDetail.getInstallment().getName());
        busFeeInstallmentDetailVO.setFeePercent(busFeeInstallmentDetail.getFeePercent());
        busFeeInstallmentDetailVO.setDisplayOrder(busFeeInstallmentDetail.getInstallment().getDisplayOrder());
        return busFeeInstallmentDetailVO;
    }

    public static BusFeeVO prepareAcademicSessionBusStopFeeVOs(List<BusFee> busFees, Long installments) {
        BusFeeVO busFeeVO = new BusFeeVO();
        List<BusStopFeeVO> busStopFeeVOs = new ArrayList();
        if (!(busFees == null || busFees.isEmpty())) {
            for (BusFee busFee : busFees) {
                BusStopFeeVO busStopFeeVO = new BusStopFeeVO();
                busStopFeeVO.setId(busFee.getId());
                busStopFeeVO.setBusStopId(busFee.getBusStop().getId());
                busStopFeeVO.setBusStopName(busFee.getBusStop().getName());
                busStopFeeVO.setBusFee(busFee.getRs());
                busStopFeeVO.setDistance(busFee.getBusStop().getDistance());
                busStopFeeVO.setBusFeeDetails(new ArrayList());
                Long installmentTotal = Long.valueOf(0);
                int i;
                BusFeeDetailVO emptyInstallment;
                if (busFee.getBusFeeDetails() == null || busFee.getBusFeeDetails().isEmpty()) {
                    for (i = 1; ((long) i) <= installments.longValue(); i++) {
                        emptyInstallment = new BusFeeDetailVO();
                        emptyInstallment.setInstallmentId(Long.valueOf((long) i));
                        busStopFeeVO.getBusFeeDetails().add(emptyInstallment);
                    }
                    busStopFeeVO.setInstallmentTotal(Long.valueOf(0));
                } else {
                    int actualInstallments = busFee.getBusFeeDetails().size();
                    List<BusFeeDetail> busFeeDetailSortedList = new ArrayList(busFee.getBusFeeDetails());
                    Collections.sort(busFeeDetailSortedList, new BeanComparator("installment.id", new NullComparator()));
                    if (actualInstallments == installments.intValue()) {
                        for (BusFeeDetail busFeeDetail : busFeeDetailSortedList) {
                            if (busFeeDetail.getFee() != null) {
                                installmentTotal = Long.valueOf(installmentTotal.longValue() + busFeeDetail.getFee().longValue());
                            }
                            busStopFeeVO.getBusFeeDetails().add(prepareBusFeeDetailVO(busFeeDetail));
                        }
                    } else if (installments.intValue() > actualInstallments) {
                        for (BusFeeDetail busFeeDetail2 : busFeeDetailSortedList) {
                            installmentTotal = Long.valueOf(installmentTotal.longValue() + busFeeDetail2.getFee().longValue());
                            busStopFeeVO.getBusFeeDetails().add(prepareBusFeeDetailVO(busFeeDetail2));
                        }
                        for (i = actualInstallments + 1; ((long) i) <= installments.longValue(); i++) {
                            emptyInstallment = new BusFeeDetailVO();
                            emptyInstallment.setInstallmentId(Long.valueOf((long) i));
                            busStopFeeVO.getBusFeeDetails().add(emptyInstallment);
                        }
                    } else if (installments.intValue() < actualInstallments) {
                        Long index = Long.valueOf(1);
                        for (BusFeeDetail busFeeDetail22 : busFeeDetailSortedList) {
                            if (index.longValue() > installments.longValue()) {
                                break;
                            }
                            installmentTotal = Long.valueOf(installmentTotal.longValue() + busFeeDetail22.getFee().longValue());
                            busStopFeeVO.getBusFeeDetails().add(prepareBusFeeDetailVO(busFeeDetail22));
                            index = Long.valueOf(index.longValue() + 1);
                        }
                    }
                    busStopFeeVO.setInstallmentTotal(installmentTotal);
                }
                busStopFeeVOs.add(busStopFeeVO);
            }
        }
        busFeeVO.setInstallments(installments);
        busFeeVO.setBusStopFees(busStopFeeVOs);
        return busFeeVO;
    }

    public static BusFeeDetailVO prepareBusFeeDetailVO(BusFeeDetail busFeeDetail) {
        BusFeeDetailVO busFeeDetailVO = new BusFeeDetailVO();
        busFeeDetailVO.setId(busFeeDetail.getId());
        busFeeDetailVO.setFee(busFeeDetail.getFee());
        busFeeDetailVO.setInstallmentId(busFeeDetail.getInstallment().getId());
        return busFeeDetailVO;
    }
}
