package com.narendra.sams.admission.service.impl;

import com.narendra.sams.admission.dao.BusAdoptedAdmissionListDAO;
import com.narendra.sams.admission.domain.AcademicYearClassAdmissionCount;
import com.narendra.sams.admission.domain.BusStopAdmissionCount;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.service.BusAdoptedAdmissionListService;
import java.util.List;

public class BusAdoptedAdmissionListServiceImpl implements BusAdoptedAdmissionListService {
    private BusAdoptedAdmissionListDAO busAdoptedAdmissionListDAO;

    public BusAdoptedAdmissionListDAO getBusAdoptedAdmissionListDAO() {
        return this.busAdoptedAdmissionListDAO;
    }

    public void setBusAdoptedAdmissionListDAO(BusAdoptedAdmissionListDAO busAdoptedAdmissionListDAO) {
        this.busAdoptedAdmissionListDAO = busAdoptedAdmissionListDAO;
    }

    public Long getBusFacilityAdoptedAdmissionCount(Long academicYearId, Long studentStatus, Short admissionType) {
        if (academicYearId == null) {
            return null;
        }
        return this.busAdoptedAdmissionListDAO.getBusFacilityAdoptedAdmissionCount(academicYearId, studentStatus, admissionType);
    }

    public List<ClassHistory> getBusFacilityAdoptedAdmissions(Long academicYearId, Long studentStatusId, Short admissionTypeId) {
        if (academicYearId == null) {
            return null;
        }
        return this.busAdoptedAdmissionListDAO.getBusFacilityAdoptedAdmissions(academicYearId, studentStatusId, admissionTypeId);
    }

    public List<BusStopAdmissionCount> getBusStopWiseBusFacilityAdoptedAdmissionCount(Long academicYearId, Long studentStatusId, Short admissionTypeId) {
        if (academicYearId == null) {
            return null;
        }
        return this.busAdoptedAdmissionListDAO.getBusStopWiseBusFacilityAdoptedAdmissionCount(academicYearId, studentStatusId, admissionTypeId);
    }

    public List<AcademicYearClassAdmissionCount> getClasswiseBusFacilityAdoptedAdmissionCount(Long academicYearId, Long studentStatus, Short admissionTypeId) {
        if (academicYearId == null) {
            return null;
        }
        return this.busAdoptedAdmissionListDAO.getClasswiseBusFacilityAdoptedAdmissionCount(academicYearId, studentStatus, admissionTypeId);
    }

    public List<ClassHistory> getBusFacilityAdoptedAdmissionsByBusStop(Long academicYearId, Long studentStatusId, Long busStopId, Short admissionTypeId) {
        if (academicYearId == null || busStopId == null) {
            return null;
        }
        return this.busAdoptedAdmissionListDAO.getBusFacilityAdoptedAdmissionsByBusStop(academicYearId, studentStatusId, busStopId, admissionTypeId);
    }

    public List<ClassHistory> getBusFacilityAdoptedAdmissionsByClass(Long academicYearClassId, Long studentStatusId, Short admissionTypeId) {
        if (academicYearClassId == null) {
            return null;
        }
        return this.busAdoptedAdmissionListDAO.getBusFacilityAdoptedAdmissionsByClass(academicYearClassId, studentStatusId, admissionTypeId);
    }
}
