package com.narendra.sams.enquiry.service.impl;

import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.enquiry.dao.EnquiryStatusDAO;
import com.narendra.sams.enquiry.domain.EnquiryStatus;
import com.narendra.sams.enquiry.service.EnquiryStatusService;
import java.util.List;

public class EnquiryStatusServiceImpl implements EnquiryStatusService {
    private EnquiryStatusDAO enquiryStatusDAO;

    public EnquiryStatusDAO getEnquiryStatusDAO() {
        return this.enquiryStatusDAO;
    }

    public void setEnquiryStatusDAO(EnquiryStatusDAO enquiryStatusDAO) {
        this.enquiryStatusDAO = enquiryStatusDAO;
    }

    public List<EnquiryStatus> getAllActiveEnquiryStatus(Long instituteId) {
        return this.enquiryStatusDAO.getAllActiveEnquiryStatus(instituteId);
    }

    public EnquiryStatus getStatusByName(String name, Long instituteId) {
        return this.enquiryStatusDAO.getStatusByName(name, instituteId);
    }

    public List<EnquiryStatus> getAllStatusList(Long instituteId) {
        if (instituteId == null) {
            return null;
        }
        return this.enquiryStatusDAO.getAllStatusList(instituteId);
    }

    public EnquiryStatus getEnquiryStatus(Long statusId) {
        if (statusId == null) {
            return null;
        }
        return this.enquiryStatusDAO.getEnquiryStatus(statusId);
    }

    public Long addStatus(EnquiryStatus enquiryStatus, Long userId) throws DuplicateNameFoundException {
        if (enquiryStatus == null) {
            return null;
        }
        if (this.enquiryStatusDAO.getStatusByName(enquiryStatus.getName(), null) == null) {
            return this.enquiryStatusDAO.addStatus(enquiryStatus, userId);
        }
        throw new DuplicateNameFoundException("Enquiry status [" + enquiryStatus.getName() + "] already exists");
    }

    public void updateStatus(EnquiryStatus enquiryStatus, Long userId) throws DuplicateNameFoundException {
        if (enquiryStatus != null) {
            EnquiryStatus persistStatus = this.enquiryStatusDAO.getStatusByName(enquiryStatus.getName(), null);
            if (persistStatus == null || persistStatus.getId().equals(enquiryStatus.getId())) {
                this.enquiryStatusDAO.updateStatus(enquiryStatus, userId);
                return;
            }
            throw new DuplicateNameFoundException("Enquiry status [" + persistStatus.getName() + "] already exists");
        }
    }

    public void deleteStatus(Long statusId) {
        if (statusId != null) {
            this.enquiryStatusDAO.deleteStatus(statusId);
        }
    }
}
