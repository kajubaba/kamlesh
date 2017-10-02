package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.AcademicYearBusFeeDAO;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusFeeInstallment;
import com.narendra.sams.core.domain.BusFeeInstallmentDetail;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.service.AcademicYearBusFeeService;
import com.narendra.sams.core.service.BusStopService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AcademicYearBusFeeServiceImpl implements AcademicYearBusFeeService {
    private AcademicYearBusFeeDAO academicYearBusFeeDAO;
    private BusStopService busStopService;

    public AcademicYearBusFeeDAO getAcademicYearBusFeeDAO() {
        return this.academicYearBusFeeDAO;
    }

    public void setAcademicYearBusFeeDAO(AcademicYearBusFeeDAO academicYearBusFeeDAO) {
        this.academicYearBusFeeDAO = academicYearBusFeeDAO;
    }

    public BusStopService getBusStopService() {
        return this.busStopService;
    }

    public void setBusStopService(BusStopService busStopService) {
        this.busStopService = busStopService;
    }

    public List<BusFee> getAssigedBusFee(Long academicYearId) {
        return this.academicYearBusFeeDAO.getAssigedBusFee(academicYearId);
    }

    public void copyBusStopAndFee(Long fromAcademicYear, Long toAcademicYear) {
        List<BusFee> fromBusFees = getAssigedBusFee(fromAcademicYear);
        if (fromBusFees != null) {
            AcademicYear academicYear = new AcademicYear();
            academicYear.setId(toAcademicYear);
            List<BusFee> newAcademicYearBusFees = new ArrayList();
            for (BusFee busFee : fromBusFees) {
                BusFee newBusFee = new BusFee();
                newBusFee.setBusStop(busFee.getBusStop());
                newBusFee.setRs(busFee.getRs());
                newBusFee.setAcademicYear(academicYear);
                newAcademicYearBusFees.add(newBusFee);
            }
            addBusFee(newAcademicYearBusFees);
        }
    }

    public void copyBusFeeInstallments(Long fromAcademicYear, Long toAcademicYear, Long userId) {
        BusFeeInstallment fromBusFeeInstallments = getBusFeeInstallment(fromAcademicYear);
        if (fromBusFeeInstallments != null) {
            AcademicYear academicYear = new AcademicYear();
            academicYear.setId(toAcademicYear);
            new UserView().setId(userId);
            BusFeeInstallment newBusFeeInstallments = new BusFeeInstallment();
            newBusFeeInstallments.setInstallmentCount(fromBusFeeInstallments.getInstallmentCount());
            newBusFeeInstallments.setAcademicYear(academicYear);
            Set<BusFeeInstallmentDetail> busFeeInstallmentDetails = new HashSet();
            if (fromBusFeeInstallments.getBusFeeInstallmentDetails() != null) {
                for (BusFeeInstallmentDetail fromBusFeeInstallmentDetail : fromBusFeeInstallments.getBusFeeInstallmentDetails()) {
                    BusFeeInstallmentDetail toBusFeeInstallmentDetail = new BusFeeInstallmentDetail();
                    toBusFeeInstallmentDetail.setBusFeeInstallment(newBusFeeInstallments);
                    toBusFeeInstallmentDetail.setFeePercent(fromBusFeeInstallmentDetail.getFeePercent());
                    toBusFeeInstallmentDetail.setInstallment(fromBusFeeInstallmentDetail.getInstallment());
                    busFeeInstallmentDetails.add(toBusFeeInstallmentDetail);
                }
            }
            newBusFeeInstallments.setBusFeeInstallmentDetails(busFeeInstallmentDetails);
            this.academicYearBusFeeDAO.addBusFeeInstallment(newBusFeeInstallments, userId);
        }
    }

    public void updateFeeChanges(List<BusFee> bussFeeList) {
        this.academicYearBusFeeDAO.updateFeeChanges(bussFeeList);
    }

    public List<BusStop> getUnAssignedBusStops(Long instituteId, Long academicYearId) {
        List<BusFee> busFeeList = getAssigedBusFee(academicYearId);
        if (busFeeList == null || busFeeList.isEmpty()) {
            return this.busStopService.getActiveBusStops(instituteId);
        }
        List<BusStop> activeBusStops = this.busStopService.getActiveBusStops(instituteId);
        if (activeBusStops == null || activeBusStops.isEmpty()) {
            return null;
        }
        List<BusStop> unAssignedBusStops = new ArrayList();
        for (BusStop activeBusStop : activeBusStops) {
            Boolean found = Boolean.FALSE;
            for (BusFee busFee : busFeeList) {
                if (activeBusStop.getId().equals(busFee.getBusStop().getId())) {
                    found = Boolean.TRUE;
                    break;
                }
            }
            if (!found.booleanValue()) {
                unAssignedBusStops.add(activeBusStop);
            }
        }
        return unAssignedBusStops;
    }

    public void addBusFee(List<BusFee> busFeeList) {
        this.academicYearBusFeeDAO.addBusFee(busFeeList);
    }

    public void saveBusFeeInstallment(BusFeeInstallment busFeeInstallment, Long userId) {
        if (busFeeInstallment != null) {
            if (busFeeInstallment.getId() == null) {
                this.academicYearBusFeeDAO.addBusFeeInstallment(busFeeInstallment, userId);
            } else {
                this.academicYearBusFeeDAO.updateBusFeeInstallmentDetail(busFeeInstallment, userId);
            }
            this.academicYearBusFeeDAO.deleteBusFeeDetails(busFeeInstallment.getAcademicYear().getId());
        }
    }

    public BusFeeInstallment getBusFeeInstallment(Long academicYearId) {
        return this.academicYearBusFeeDAO.getBusFeeInstallment(academicYearId);
    }

    public BusFee getBusFee(Long academicYearId, Long busStopId) {
        if (busStopId == null) {
            return null;
        }
        return this.academicYearBusFeeDAO.getBusFee(academicYearId, busStopId);
    }

    public BusFeeInstallmentDetail getBusFeeInstallmentDetails(Long academicYearId, Long installment) {
        return this.academicYearBusFeeDAO.getBusFeeInstallmentDetails(academicYearId, installment);
    }

    public void addBusStops(Long academicSession, List<Long> busStops) {
        if (academicSession != null && busStops != null) {
            AcademicYear academicYear = new AcademicYear();
            academicYear.setId(academicSession);
            List<BusFee> busFeeList = new ArrayList();
            for (Long busStopId : busStops) {
                BusFee busFee = new BusFee();
                busFee.setAcademicYear(academicYear);
                BusStop busStop = new BusStop();
                busStop.setId(busStopId);
                busFee.setBusStop(busStop);
                busFee.setRs(Long.valueOf(0));
                busFeeList.add(busFee);
            }
            this.academicYearBusFeeDAO.addBusFee(busFeeList);
        }
    }

    public Boolean deleteBusStop(Long busFeeId) {
        this.academicYearBusFeeDAO.deleteBusStop(busFeeId);
        return Boolean.TRUE;
    }

    public BusFee getBusFee(Long id) {
        return this.academicYearBusFeeDAO.getBusFee(id);
    }

    public void saveBusFeeInstallments(List<BusFee> busFees, Long installments, Long academicSessionId, Long userId) {
        if (busFees != null) {
            BusFeeInstallment busFeeInstallment = this.academicYearBusFeeDAO.getBusFeeInstallment(academicSessionId);
            if (busFeeInstallment == null) {
                busFeeInstallment = new BusFeeInstallment();
                AcademicYear academicYear = new AcademicYear();
                academicYear.setId(academicSessionId);
                busFeeInstallment.setAcademicYear(academicYear);
            } else {
                this.academicYearBusFeeDAO.deleteBusFeeInstallmentDetail(busFeeInstallment.getId());
            }
            busFeeInstallment.setInstallmentCount(installments);
            busFeeInstallment.setSetupType(BusFeeInstallment.SETUP_TYPE_MANUAL);
            this.academicYearBusFeeDAO.saveBusFeeInstallments(busFees);
            this.academicYearBusFeeDAO.saveOrUpdateBusFeeInstallment(busFeeInstallment, userId);
        }
    }
}
