package com.narendra.sams.enquiry.service.impl;

import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.dao.EnquiryDAO;
import com.narendra.sams.enquiry.dao.EnquiryStatusDAO;
import com.narendra.sams.enquiry.domain.AdvanceEnquirySearchParam;
import com.narendra.sams.enquiry.domain.ClasswiseEnquiryCount;
import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.enquiry.domain.EnquiryBriefInfo;
import com.narendra.sams.enquiry.domain.EnquiryCountData;
import com.narendra.sams.enquiry.domain.EnquiryStatus;
import com.narendra.sams.enquiry.domain.StatusWiseEnquiryCount;
import com.narendra.sams.enquiry.service.EnquiryService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class EnquiryServiceImpl implements EnquiryService {
    private EnquiryDAO enquiryDAO;
    private EnquiryStatusDAO enquiryStatusDAO;

    public EnquiryDAO getEnquiryDAO() {
        return this.enquiryDAO;
    }

    public void setEnquiryDAO(EnquiryDAO enquiryDAO) {
        this.enquiryDAO = enquiryDAO;
    }

    public EnquiryStatusDAO getEnquiryStatusDAO() {
        return this.enquiryStatusDAO;
    }

    public void setEnquiryStatusDAO(EnquiryStatusDAO enquiryStatusDAO) {
        this.enquiryStatusDAO = enquiryStatusDAO;
    }

    public List<Enquiry> getEnquiriesByStatus(Long academicYearId, Long statusId) {
        return this.enquiryDAO.getEnquiriesByStatus(academicYearId, statusId);
    }

    public List<StatusWiseEnquiryCount> getStatusWiseEnquiryCount(Long academicYearId, Long instituteId) {
        List<StatusWiseEnquiryCount> counts = this.enquiryDAO.getStatusWiseEnquiryCount(academicYearId);
        if (counts == null) {
            counts = new ArrayList();
        }
        List<EnquiryStatus> statusList = this.enquiryStatusDAO.getAllActiveEnquiryStatus(instituteId);
        List<StatusWiseEnquiryCount> newStatusList = new ArrayList();
        if (statusList != null) {
            for (EnquiryStatus enquiryStatus : statusList) {
                Boolean found = Boolean.FALSE;
                for (StatusWiseEnquiryCount count : counts) {
                    if (count.getStatusId().equals(enquiryStatus.getId())) {
                        found = Boolean.TRUE;
                        break;
                    }
                }
                if (!found.booleanValue()) {
                    StatusWiseEnquiryCount statusWiseEnquiryCount = new StatusWiseEnquiryCount();
                    statusWiseEnquiryCount.setAcademicSessionId(academicYearId);
                    statusWiseEnquiryCount.setEnquiryCount(Long.valueOf(0));
                    statusWiseEnquiryCount.setStatusId(enquiryStatus.getId());
                    statusWiseEnquiryCount.setStatusName(enquiryStatus.getName());
                    newStatusList.add(statusWiseEnquiryCount);
                }
            }
        }
        counts.addAll(newStatusList);
        return counts;
    }

    public List<Enquiry> getEnquiriesByClass(Long classId) {
        return this.enquiryDAO.getEnquiriesByClass(classId);
    }

    public List<ClasswiseEnquiryCount> getClasswiseEnquiryCount(Long academicYearId) {
        return this.enquiryDAO.getClasswiseEnquiryCount(academicYearId);
    }

    public long getCountByStatusName(Long academicYearId, String statusName) {
        return this.enquiryDAO.getCountByStatusName(academicYearId, statusName).longValue();
    }

    public List<Enquiry> getRecentEnquiries(Long academicSessionId) {
        return this.enquiryDAO.getRecentEnquiries(academicSessionId);
    }

    public List<Enquiry> getEnquiries(Long academicSessionId, String statusId) {
        return this.enquiryDAO.getEnquiries(academicSessionId, statusId);
    }

    public Long addEnquiry(Enquiry enquiry, Long userid) {
        enquiry.setEnquiryStatus(this.enquiryStatusDAO.getStatusByName(EnquiryStatus.NEW, enquiry.getInstitute().getId()));
        return this.enquiryDAO.addEnquiry(enquiry, userid);
    }

    public Long addNewEnquiry(Enquiry enquiry, Long userid) {
        enquiry.setEnquiryStatus(this.enquiryStatusDAO.getStatusByName(EnquiryStatus.NEW, enquiry.getInstitute().getId()));
        return this.enquiryDAO.addEnquiry(enquiry, userid);
    }

    public List<Enquiry> getAllEnquiries(Long instituteId, Long academicYearId, Long responsibleUserId) {
        return this.enquiryDAO.getAllEnquiries(instituteId, academicYearId, responsibleUserId);
    }

    public List<Enquiry> getAllEnquiries(Long instituteId, Long academicYearId, Long responsibleUserId, String searchStr, String searchWise) {
        return this.enquiryDAO.getAllEnquiries(instituteId, academicYearId, responsibleUserId, searchStr, searchWise);
    }

    public Enquiry getEnquiry(Long enquiryId) {
        return this.enquiryDAO.getEnquiry(enquiryId);
    }

    public void updateNewEnquiry(Enquiry enquiry, Long userid) {
        this.enquiryDAO.updateEnquiry(enquiry, userid);
    }

    public void updateEnquiry(Enquiry enquiry, Long userid) {
        this.enquiryDAO.updateEnquiry(enquiry, userid);
    }

    public List<EnquiryCountData> getClassWiseEnquiryCount(Long instituteId, Long academicYearId, Long userId) {
        return this.enquiryDAO.getClassWiseEnquiryCount(instituteId, academicYearId, userId);
    }

    public List<EnquiryCountData> getCityWiseEnquiryCount(Long instituteId, Long academicYearId, Long userId) {
        return this.enquiryDAO.getCityWiseEnquiryCount(instituteId, academicYearId, userId);
    }

    public List<Enquiry> getAllEnquiries(Long instituteId, String searchStr, String propertyName, Long responsibleUserId, Long academicYearId) {
        return this.enquiryDAO.getAllEnquiries(instituteId, searchStr, propertyName, responsibleUserId, academicYearId);
    }

    public Boolean isEnquiryExist(Long instituteId, Long academicYearId, String studentFirstName, String phone1) {
        return this.enquiryDAO.isEnquiryExist(instituteId, academicYearId, studentFirstName, phone1);
    }

    public List<Enquiry> advanceSearch(Long instituteId, Long academicYearId, AdvanceEnquirySearchParam advanceEnquirySearchParam, Long userId) {
        return this.enquiryDAO.advanceSearch(instituteId, academicYearId, advanceEnquirySearchParam, userId);
    }

    public Boolean isEnquiriesExists(Collection<Long> classes) {
        if (classes == null) {
            return Boolean.FALSE;
        }
        List<Enquiry> enquiries = this.enquiryDAO.getEnquiries(classes);
        if (enquiries == null || enquiries.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public List<Enquiry> getEnquiruesWithFormNo(Long academicSessionId, String formNo) {
        return this.enquiryDAO.getEnquiruesWithFormNo(academicSessionId, formNo);
    }

    public Enquiry getEnquiryByFormNo(Long academicSessionId, String formNo) {
        return this.enquiryDAO.getEnquiryByFormNo(academicSessionId, formNo);
    }

    public Enquiry getEnquiryByContactNo(Long academicSessionId, String contactNo) {
        return this.enquiryDAO.getEnquiryByContactNo(academicSessionId, contactNo);
    }

    public List<Enquiry> getEnquiriesByContactNo(Long academicSessionId, String contactNo, String contactOf) {
        if (academicSessionId == null || contactNo == null || contactOf == null) {
            return null;
        }
        return this.enquiryDAO.getEnquiriesByContactNo(academicSessionId, contactNo, contactOf);
    }

    public List<String> getEnquiryCities(Long instituteId) {
        return this.enquiryDAO.getEnquiryCities(instituteId);
    }

    public List<EnquiryBriefInfo> getEnquiryBrifInfo(Long academicSession, String searchString) {
        if (academicSession == null || searchString == null) {
            return null;
        }
        return this.enquiryDAO.getEnquiryBrifInfo(academicSession, searchString);
    }

    public List<Enquiry> getEnquiriesByDate(Long academicSession, Date from, Date to) {
        Date fromDate = null;
        Date toDate = null;
        if (from != null) {
            fromDate = DateUtil.makeStartDate(from);
        }
        if (to != null) {
            toDate = DateUtil.makeEndDate(to);
        }
        return this.enquiryDAO.getEnquiriesByDate(academicSession, fromDate, toDate);
    }

    public List<Enquiry> getEnquiriesByFormIssueDate(Long academicSession, Date from, Date to) {
        Date fromDate = null;
        Date toDate = null;
        if (from != null) {
            fromDate = DateUtil.makeStartDate(from);
        }
        if (to != null) {
            toDate = DateUtil.makeEndDate(to);
        }
        return this.enquiryDAO.getEnquiriesByFormIssueDate(academicSession, fromDate, toDate);
    }
}
