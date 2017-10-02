package com.narendra.sams.web.restws.enquiry.form.mapper;

import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.enquiry.domain.EnquiryFolloup;
import com.narendra.sams.enquiry.domain.FollowupCommConclusion;
import com.narendra.sams.enquiry.domain.FollowupCommType;
import com.narendra.sams.enquiry.domain.FollowupCommWith;
import com.narendra.sams.enquiry.domain.FollowupNextAction;
import com.narendra.sams.enquiry.domain.FollowupSuggestion;
import com.narendra.sams.web.restws.enquiry.form.EnquiryFollowupForm;
import java.text.ParseException;

public class EnquiryFollowupFormMapper {
    public static EnquiryFolloup prepareEnquiryFollowupDomain(EnquiryFollowupForm enquiryFollowupForm) {
        EnquiryFolloup enquiryFolloup = new EnquiryFolloup();
        enquiryFolloup.setId(enquiryFollowupForm.getId());
        enquiryFolloup.setCommunicationSummary(enquiryFollowupForm.getCommSummary());
        try {
            if (enquiryFollowupForm.getNextFollowupDate() != null) {
                enquiryFolloup.setNextFollowupDate(DateUtil.parseDate(enquiryFollowupForm.getNextFollowupDate(), "dd-MMM-yyyy"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        enquiryFolloup.setNextFollowupHr(enquiryFollowupForm.getNextFollowupHr());
        enquiryFolloup.setNextFollowupMin(enquiryFollowupForm.getNextFollowupMin());
        enquiryFolloup.setAmOrPM(enquiryFollowupForm.getAmOrPM());
        if (enquiryFollowupForm.getCommTypeId() != null) {
            FollowupCommType followupCommType = new FollowupCommType();
            followupCommType.setId(enquiryFollowupForm.getCommTypeId());
            enquiryFolloup.setFollowupCommType(followupCommType);
        }
        if (enquiryFollowupForm.getCommWithId() != null) {
            FollowupCommWith followupCommWith = new FollowupCommWith();
            followupCommWith.setId(enquiryFollowupForm.getCommWithId());
            enquiryFolloup.setFollowupCommWith(followupCommWith);
        }
        if (enquiryFollowupForm.getCommConclusionId() != null) {
            FollowupCommConclusion followupCommConclusion = new FollowupCommConclusion();
            followupCommConclusion.setId(enquiryFollowupForm.getCommConclusionId());
            enquiryFolloup.setFollowupCommConclusion(followupCommConclusion);
        }
        if (enquiryFollowupForm.getNextActionId() != null) {
            FollowupNextAction followupNextAction = new FollowupNextAction();
            followupNextAction.setId(enquiryFollowupForm.getNextActionId());
            enquiryFolloup.setFollowupNextAction(followupNextAction);
        }
        if (enquiryFollowupForm.getSuggestionId() != null) {
            FollowupSuggestion followupSuggestion = new FollowupSuggestion();
            followupSuggestion.setId(enquiryFollowupForm.getSuggestionId());
            enquiryFolloup.setFollowupSuggestion(followupSuggestion);
        }
        if (enquiryFollowupForm.getEnquiryId() != null) {
            Enquiry enquiry = new Enquiry();
            enquiry.setId(enquiryFollowupForm.getEnquiryId());
            enquiryFolloup.setEnquiry(enquiry);
        }
        return enquiryFolloup;
    }
}
