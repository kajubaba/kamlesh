package com.narendra.sams.fee.service.impl;

import com.narendra.sams.admission.dao.FeeRecieptDAO;
import com.narendra.sams.admission.domain.FeeRecieptHeader;
import com.narendra.sams.fee.service.FeeRecieptService;
import java.util.List;

public class FeeRecieptServiceImpl implements FeeRecieptService {
    private FeeRecieptDAO feeRecieptDAO;

    public FeeRecieptDAO getFeeRecieptDAO() {
        return this.feeRecieptDAO;
    }

    public void setFeeRecieptDAO(FeeRecieptDAO feeRecieptDAO) {
        this.feeRecieptDAO = feeRecieptDAO;
    }

    public List<FeeRecieptHeader> getActiveFeeRecieptHeaders(Long institute) {
        return this.feeRecieptDAO.getActiveFeeRecieptHeaders(institute);
    }

    public FeeRecieptHeader getFeeRecieptHeader(Long id) {
        return this.feeRecieptDAO.getFeeRecieptHeader(id);
    }

    public FeeRecieptHeader getDefaultHeader(Long instituteId) {
        return this.feeRecieptDAO.getDefaultHeader(instituteId);
    }
}
