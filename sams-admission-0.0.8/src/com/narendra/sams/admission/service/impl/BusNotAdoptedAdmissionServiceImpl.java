package com.narendra.sams.admission.service.impl;

import com.narendra.sams.admission.dao.BusNotAdoptedAdmissionDAO;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.service.BusNotAdoptedAdmissionService;
import java.util.List;

public class BusNotAdoptedAdmissionServiceImpl implements BusNotAdoptedAdmissionService {
    private BusNotAdoptedAdmissionDAO busNotAdoptedAdmissionDAO;

    public BusNotAdoptedAdmissionDAO getBusNotAdoptedAdmissionDAO() {
        return this.busNotAdoptedAdmissionDAO;
    }

    public void setBusNotAdoptedAdmissionDAO(BusNotAdoptedAdmissionDAO busNotAdoptedAdmissionDAO) {
        this.busNotAdoptedAdmissionDAO = busNotAdoptedAdmissionDAO;
    }

    public Long getBusFacilityNotAdoptedAdmissionCount(Long academicYearId, Long studentStatus, Short admissionType) {
        return this.busNotAdoptedAdmissionDAO.getBusFacilityNotAdoptedAdmissionCount(academicYearId, studentStatus, admissionType);
    }

    public List<ClassHistory> getBusFacilityNotAdoptedAdmissions(Long academicYearId, Long studentStatusId, Short admissionTypeId) {
        if (academicYearId == null) {
            return null;
        }
        return this.busNotAdoptedAdmissionDAO.getBusFacilityNotAdoptedAdmissions(academicYearId, studentStatusId, admissionTypeId);
    }
}
