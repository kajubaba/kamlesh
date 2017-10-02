package com.narendra.sams.enquiry.dao;

import com.narendra.sams.enquiry.domain.EnquiryActivity;
import java.util.Date;
import java.util.List;

public interface EnquiryActivityDAO {
    void addChangeAssigneeLog(Long[] lArr, Long l, String str, Long l2);

    void addChangeOwnerLog(Long[] lArr, Long l, String str, Long l2);

    void addChangeStatusLog(Long l, Long l2, String str, Long l3);

    void addChangeStatusLog(Long[] lArr, Long l, String str, Long l2);

    void addFollowupLog(Long[] lArr, String str, String str2, Long l);

    List<EnquiryActivity> getEnquiryActivities(Long l);

    void updateEnquiryAssignee(Long[] lArr, Long l);

    void updateEnquiryOwner(Long[] lArr, Long l);

    void updateEnquiryStatus(Long l, Long l2);

    void updateEnquiryStatus(Long[] lArr, Long l);

    void updateFormDetails(Long l, String str, Long l2, Date date, Long l3, Long l4);
}
