package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.FeeHeadDAO;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.FeeHeadService;
import java.util.List;

public class FeeHeadServiceImpl implements FeeHeadService {
    private FeeHeadDAO feeHeadDAO;

    public FeeHeadDAO getFeeHeadDAO() {
        return this.feeHeadDAO;
    }

    public void setFeeHeadDAO(FeeHeadDAO feeHeadDAO) {
        this.feeHeadDAO = feeHeadDAO;
    }

    public Long saveFeeHead(FeeHead feeHead, Long userId) throws DuplicateNameFoundException {
        if (feeHead == null) {
            return null;
        }
        Long feeHeadId = feeHead.getId();
        if (feeHead.getId() != null) {
            FeeHead loadedFeeHead = this.feeHeadDAO.loadByName(feeHead.getInstitute().getId(), feeHead.getName());
            if (loadedFeeHead == null || loadedFeeHead.getId().equals(feeHead.getId())) {
                this.feeHeadDAO.updateFeeHead(feeHead, userId);
                return feeHeadId;
            }
            throw new DuplicateNameFoundException("Fee Head [" + feeHead.getName() + "] already exist");
        } else if (this.feeHeadDAO.isFeeHeadNameExist(feeHead.getInstitute().getId(), feeHead.getName()).booleanValue()) {
            throw new DuplicateNameFoundException("Fee Head [" + feeHead.getName() + "] already exist");
        } else {
            feeHead.setSystemFeeHead(Boolean.FALSE);
            return this.feeHeadDAO.addFeeHead(feeHead, userId);
        }
    }

    public List<FeeHead> getAllActiveFeeHeads(Long instituteId) {
        return this.feeHeadDAO.getAllActiveFeeHeads(instituteId);
    }

    public FeeHead getFeeHead(Long feeHeadId) {
        return this.feeHeadDAO.getFeeHead(feeHeadId);
    }

    public FeeHead getBusFeeHead(Long instituteId) {
        return this.feeHeadDAO.getBusFeeHead(instituteId);
    }

    public FeeHead getLateFeeHead(Long instituteId) {
        return this.feeHeadDAO.getLateFeeHead(instituteId);
    }

    public List<FeeHead> getAllFeeHeads(Long instituteId) {
        return this.feeHeadDAO.getAllFeeHeads(instituteId);
    }
}
