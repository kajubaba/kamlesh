package com.narendra.sams.enquiry.dao;

import com.narendra.sams.enquiry.domain.AdvanceEnquirySearchParam;
import com.narendra.sams.enquiry.domain.ClasswiseEnquiryCount;
import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.enquiry.domain.EnquiryBriefInfo;
import com.narendra.sams.enquiry.domain.EnquiryCountData;
import com.narendra.sams.enquiry.domain.StatusWiseEnquiryCount;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface EnquiryDAO {
    Long addEnquiry(Enquiry enquiry, Long l);

    Long addNewEnquiry(Enquiry enquiry, Long l);

    List<Enquiry> advanceSearch(Long l, Long l2, AdvanceEnquirySearchParam advanceEnquirySearchParam, Long l3);

    List<Enquiry> getAllEnquiries(Long l, Long l2, Long l3);

    List<Enquiry> getAllEnquiries(Long l, Long l2, Long l3, String str, String str2);

    List<Enquiry> getAllEnquiries(Long l, String str, String str2, Long l2, Long l3);

    List<EnquiryCountData> getCityWiseEnquiryCount(Long l, Long l2, Long l3);

    List<EnquiryCountData> getClassWiseEnquiryCount(Long l, Long l2, Long l3);

    List<ClasswiseEnquiryCount> getClasswiseEnquiryCount(Long l);

    Long getCountByStatusName(Long l, String str);

    List<Enquiry> getEnquiries(Long l, String str);

    List<Enquiry> getEnquiries(Collection<Long> collection);

    List<Enquiry> getEnquiriesByClass(Long l);

    List<Enquiry> getEnquiriesByContactNo(Long l, String str, String str2);

    List<Enquiry> getEnquiriesByDate(Long l, Date date, Date date2);

    List<Enquiry> getEnquiriesByFormIssueDate(Long l, Date date, Date date2);

    List<Enquiry> getEnquiriesByStatus(Long l, Long l2);

    List<Enquiry> getEnquiruesWithFormNo(Long l, String str);

    Enquiry getEnquiry(Long l);

    List<EnquiryBriefInfo> getEnquiryBrifInfo(Long l, String str);

    Enquiry getEnquiryByContactNo(Long l, String str);

    Enquiry getEnquiryByFormNo(Long l, String str);

    List<String> getEnquiryCities(Long l);

    List<Enquiry> getRecentEnquiries(Long l);

    List<StatusWiseEnquiryCount> getStatusWiseEnquiryCount(Long l);

    List<EnquiryCountData> getStatusWiseEnquiryCount(Long l, Long l2, Long l3);

    Boolean isEnquiryExist(Long l, Long l2, String str, String str2);

    void updateEnquiry(Enquiry enquiry, Long l);
}
