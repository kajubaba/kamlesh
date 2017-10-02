package com.narendra.sams.enquiry.service.impl;

import com.narendra.sams.core.domain.InstituteSetting;
import com.narendra.sams.core.service.InstituteSettingService;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.dao.EnquiryActivityDAO;
import com.narendra.sams.enquiry.dao.EnquiryDAO;
import com.narendra.sams.enquiry.dao.EnquiryStatusDAO;
import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.enquiry.domain.EnquiryActivity;
import com.narendra.sams.enquiry.domain.EnquiryStatus;
import com.narendra.sams.enquiry.service.EnquiryActivityService;
import java.util.List;

public class EnquiryActivityServiceImpl implements EnquiryActivityService {
    private EnquiryActivityDAO enquiryActivityDAO;
    private EnquiryActivityService enquiryActivityService;
    private EnquiryDAO enquiryDAO;
    private EnquiryStatusDAO enquiryStatusDAO;
    private InstituteSettingService instituteSettingService;

    public EnquiryActivityService getEnquiryActivityService() {
        return this.enquiryActivityService;
    }

    public void setEnquiryActivityService(EnquiryActivityService enquiryActivityService) {
        this.enquiryActivityService = enquiryActivityService;
    }

    public EnquiryStatusDAO getEnquiryStatusDAO() {
        return this.enquiryStatusDAO;
    }

    public void setEnquiryStatusDAO(EnquiryStatusDAO enquiryStatusDAO) {
        this.enquiryStatusDAO = enquiryStatusDAO;
    }

    public EnquiryActivityDAO getEnquiryActivityDAO() {
        return this.enquiryActivityDAO;
    }

    public void setEnquiryActivityDAO(EnquiryActivityDAO enquiryActivityDAO) {
        this.enquiryActivityDAO = enquiryActivityDAO;
    }

    public InstituteSettingService getInstituteSettingService() {
        return this.instituteSettingService;
    }

    public void setInstituteSettingService(InstituteSettingService instituteSettingService) {
        this.instituteSettingService = instituteSettingService;
    }

    public EnquiryDAO getEnquiryDAO() {
        return this.enquiryDAO;
    }

    public void setEnquiryDAO(EnquiryDAO enquiryDAO) {
        this.enquiryDAO = enquiryDAO;
    }

    public void updateEnquiryOwner(Long[] enquiryIds, Long newOwnerId, String comments, Long userId) {
        this.enquiryActivityDAO.addChangeOwnerLog(enquiryIds, newOwnerId, comments, userId);
        this.enquiryActivityDAO.updateEnquiryOwner(enquiryIds, newOwnerId);
    }

    public void updateEnquiryAssignee(Long[] enquiryIds, Long newAssigneeId, String comments, Long userId) {
        this.enquiryActivityDAO.addChangeAssigneeLog(enquiryIds, newAssigneeId, comments, userId);
        this.enquiryActivityDAO.updateEnquiryAssignee(enquiryIds, newAssigneeId);
    }

    public void updateEnquiryStatus(Long[] enquiryIds, Long newStatusId, String comments, Long userId) {
        this.enquiryActivityDAO.addChangeStatusLog(enquiryIds, newStatusId, comments, userId);
        this.enquiryActivityDAO.updateEnquiryStatus(enquiryIds, newStatusId);
    }

    public void followEnquiry(Long[] enquiryIds, String followupActivity, String comments, Long userId) {
        this.enquiryActivityDAO.addFollowupLog(enquiryIds, followupActivity, comments, userId);
    }

    public List<EnquiryActivity> getEnquiryActivities(Long enquiryId) {
        return this.enquiryActivityDAO.getEnquiryActivities(enquiryId);
    }

    public void updateEnquiryStatus(Long enquiryId, Long newStatusId, String comments, Long userId) {
        this.enquiryActivityDAO.addChangeStatusLog(enquiryId, newStatusId, comments, userId);
        this.enquiryActivityDAO.updateEnquiryStatus(enquiryId, newStatusId);
    }

    public synchronized void updateFormDetails(Long enquiryId, String formNo, Long userId) {
        if (!(enquiryId == null || formNo == null)) {
            Long receiptNo;
            Enquiry enquiry = this.enquiryDAO.getEnquiry(enquiryId);
            this.enquiryActivityService.updateEnquiryStatus(enquiry.getId(), this.enquiryStatusDAO.getStatusByName(EnquiryStatus.HOT, enquiry.getInstitute().getId()).getId(), "Auto Conversion", userId);
            InstituteSetting instituteSetting = this.instituteSettingService.getInstituteSetting(enquiry.getInstitute().getId());
            if (instituteSetting.getFeeSettings().getIsFeeReceiptNoInCont() == null || !Boolean.TRUE.equals(instituteSetting.getFeeSettings().getIsFeeReceiptNoInCont())) {
                receiptNo = instituteSetting.getEnquirySettings().getNextFormReceiptNo();
                instituteSetting.getEnquirySettings().setNextFormReceiptNo(Long.valueOf(receiptNo.longValue() + 1));
            } else {
                receiptNo = Long.valueOf(instituteSetting.getFeeSettings().getLastFeeReceiptNo().longValue() + 1);
                instituteSetting.getFeeSettings().setLastFeeReceiptNo(receiptNo);
            }
            this.enquiryActivityDAO.updateFormDetails(enquiryId, formNo, instituteSetting.getEnquirySettings().getFormFee(), DateUtil.getSystemDateTime(), receiptNo, userId);
        }
    }
}
