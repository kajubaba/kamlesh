package com.narendra.sams.enquiry.dao.impl;

import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.dao.EnquiryFollowupDAO;
import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.enquiry.domain.EnquiryFolloup;
import com.narendra.sams.enquiry.domain.FollowupCommConclusion;
import com.narendra.sams.enquiry.domain.FollowupCommType;
import com.narendra.sams.enquiry.domain.FollowupCommWith;
import com.narendra.sams.enquiry.domain.FollowupNextAction;
import com.narendra.sams.enquiry.domain.FollowupSuggestion;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class EnquiryFollowupDAOImpl extends HibernateDaoSupport implements EnquiryFollowupDAO {
    private UserView loadUser(Long userId) {
        return (UserView) getHibernateTemplate().load(UserView.class, userId);
    }

    public Long addFollowup(EnquiryFolloup enquiryFolloup, Long userId) {
        UserView user = loadUser(userId);
        enquiryFolloup.setCreatedBy(user);
        enquiryFolloup.setModifiedBy(user);
        enquiryFolloup.setCreatedOn(DateUtil.getSystemDateTime());
        enquiryFolloup.setModifiedOn(DateUtil.getSystemDateTime());
        if (enquiryFolloup.getFollowupCommType() != null) {
            enquiryFolloup.setFollowupCommType((FollowupCommType) getHibernateTemplate().load(FollowupCommType.class, enquiryFolloup.getFollowupCommType().getId()));
        }
        if (enquiryFolloup.getFollowupCommWith() != null) {
            enquiryFolloup.setFollowupCommWith((FollowupCommWith) getHibernateTemplate().load(FollowupCommWith.class, enquiryFolloup.getFollowupCommWith().getId()));
        }
        if (enquiryFolloup.getFollowupCommConclusion() != null) {
            enquiryFolloup.setFollowupCommConclusion((FollowupCommConclusion) getHibernateTemplate().load(FollowupCommConclusion.class, enquiryFolloup.getFollowupCommConclusion().getId()));
        }
        if (enquiryFolloup.getFollowupNextAction() != null) {
            enquiryFolloup.setFollowupNextAction((FollowupNextAction) getHibernateTemplate().load(FollowupNextAction.class, enquiryFolloup.getFollowupNextAction().getId()));
        }
        if (enquiryFolloup.getFollowupSuggestion() != null) {
            enquiryFolloup.setFollowupSuggestion((FollowupSuggestion) getHibernateTemplate().load(FollowupSuggestion.class, enquiryFolloup.getFollowupSuggestion().getId()));
        }
        if (enquiryFolloup.getEnquiry() != null) {
            enquiryFolloup.setEnquiry((Enquiry) getHibernateTemplate().load(Enquiry.class, enquiryFolloup.getEnquiry().getId()));
        }
        return (Long) getHibernateTemplate().save(enquiryFolloup);
    }

    public EnquiryFolloup getFollowup(Long id) {
        return (EnquiryFolloup) getHibernateTemplate().get(EnquiryFolloup.class, id);
    }

    public List<EnquiryFolloup> getEnquiryFollowups(Long enquiryId) {
        Criteria crt = getSession().createCriteria(EnquiryFolloup.class);
        crt.add(Restrictions.eq("enquiry.id", enquiryId));
        crt.addOrder(Order.asc("createdOn"));
        return crt.list();
    }

    public void updateFollowup(EnquiryFolloup enquiryFolloup, Long userId) {
        EnquiryFolloup persitEnquiryFollowup = (EnquiryFolloup) getHibernateTemplate().load(EnquiryFolloup.class, enquiryFolloup.getId());
        persitEnquiryFollowup.setModifiedBy(loadUser(userId));
        persitEnquiryFollowup.setModifiedOn(DateUtil.getSystemDateTime());
        if (enquiryFolloup.getFollowupCommType() != null) {
            persitEnquiryFollowup.setFollowupCommType((FollowupCommType) getHibernateTemplate().load(FollowupCommType.class, enquiryFolloup.getFollowupCommType().getId()));
        } else {
            persitEnquiryFollowup.setFollowupCommType(null);
        }
        if (enquiryFolloup.getFollowupCommWith() != null) {
            persitEnquiryFollowup.setFollowupCommWith((FollowupCommWith) getHibernateTemplate().load(FollowupCommWith.class, enquiryFolloup.getFollowupCommWith().getId()));
        } else {
            persitEnquiryFollowup.setFollowupCommWith(null);
        }
        if (enquiryFolloup.getFollowupCommConclusion() != null) {
            persitEnquiryFollowup.setFollowupCommConclusion((FollowupCommConclusion) getHibernateTemplate().load(FollowupCommConclusion.class, enquiryFolloup.getFollowupCommConclusion().getId()));
        } else {
            enquiryFolloup.setFollowupCommConclusion(null);
        }
        if (enquiryFolloup.getFollowupNextAction() != null) {
            persitEnquiryFollowup.setFollowupNextAction((FollowupNextAction) getHibernateTemplate().load(FollowupNextAction.class, enquiryFolloup.getFollowupNextAction().getId()));
        } else {
            persitEnquiryFollowup.setFollowupNextAction(null);
        }
        if (enquiryFolloup.getFollowupSuggestion() != null) {
            persitEnquiryFollowup.setFollowupSuggestion((FollowupSuggestion) getHibernateTemplate().load(FollowupSuggestion.class, enquiryFolloup.getFollowupSuggestion().getId()));
        } else {
            persitEnquiryFollowup.setFollowupSuggestion(null);
        }
        persitEnquiryFollowup.setNextFollowupDate(enquiryFolloup.getNextFollowupDate());
        persitEnquiryFollowup.setNextFollowupHr(enquiryFolloup.getNextFollowupHr());
        persitEnquiryFollowup.setNextFollowupMin(enquiryFolloup.getNextFollowupMin());
        persitEnquiryFollowup.setAmOrPM(enquiryFolloup.getAmOrPM());
        getHibernateTemplate().update(persitEnquiryFollowup);
    }
}
