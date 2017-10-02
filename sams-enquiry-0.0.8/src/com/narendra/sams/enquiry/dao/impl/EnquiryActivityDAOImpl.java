package com.narendra.sams.enquiry.dao.impl;

import com.narendra.sams.core.address.dao.UserViewDAO;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.dao.EnquiryActivityDAO;
import com.narendra.sams.enquiry.dao.EnquiryDAO;
import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.enquiry.domain.EnquiryActivity;
import com.narendra.sams.enquiry.domain.EnquiryStatus;
import com.narendra.sams.enquiry.literal.EnquiryActivityType;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class EnquiryActivityDAOImpl extends HibernateDaoSupport implements EnquiryActivityDAO {
    private EnquiryDAO enquiryDAO;
    private UserViewDAO userViewDAO;

    public UserViewDAO getUserViewDAO() {
        return this.userViewDAO;
    }

    public void setUserViewDAO(UserViewDAO userViewDAO) {
        this.userViewDAO = userViewDAO;
    }

    public EnquiryDAO getEnquiryDAO() {
        return this.enquiryDAO;
    }

    public void setEnquiryDAO(EnquiryDAO enquiryDAO) {
        this.enquiryDAO = enquiryDAO;
    }

    public void updateEnquiryOwner(Long[] enquiryIds, Long newOwnerId) {
        Query query = getSession().createQuery("update Enquiry set owner.id=:newOwnerId where id in (:enqIds)");
        query.setParameter("newOwnerId", newOwnerId);
        query.setParameterList("enqIds", enquiryIds);
        query.executeUpdate();
    }

    public void addChangeOwnerLog(Long[] enquiryIds, Long newOwnerId, String comments, Long userId) {
        UserView newOwner = this.userViewDAO.loadUser(newOwnerId);
        UserView createdBy = this.userViewDAO.loadUser(userId);
        for (Long enquiryId : enquiryIds) {
            Enquiry enquiry = this.enquiryDAO.getEnquiry(enquiryId);
            EnquiryActivity enquiryActivity = new EnquiryActivity();
            enquiryActivity.setActivityType(EnquiryActivityType.change_owner.toString());
            enquiryActivity.setEnquiry(enquiry);
            enquiryActivity.setOwnerFrom(enquiry.getOwner());
            enquiryActivity.setOwnerTo(newOwner);
            enquiryActivity.setComments(comments);
            enquiryActivity.setCreatedDateTime(DateUtil.getSystemDateTime());
            enquiryActivity.setCreatedBy(createdBy);
            getHibernateTemplate().save(enquiryActivity);
        }
    }

    public void updateEnquiryAssignee(Long[] enquiryIds, Long newAssigneeId) {
        Query query = getSession().createQuery("update Enquiry set assignee.id=:newAssigneeId where id in (:enqIds)");
        query.setParameter("newAssigneeId", newAssigneeId);
        query.setParameterList("enqIds", enquiryIds);
        query.executeUpdate();
    }

    public void addChangeAssigneeLog(Long[] enquiryIds, Long newAssigneeId, String comments, Long userId) {
        UserView newAssignee = this.userViewDAO.loadUser(newAssigneeId);
        UserView createdBy = this.userViewDAO.loadUser(userId);
        for (Long enquiryId : enquiryIds) {
            Enquiry enquiry = this.enquiryDAO.getEnquiry(enquiryId);
            EnquiryActivity enquiryActivity = new EnquiryActivity();
            enquiryActivity.setActivityType(EnquiryActivityType.change_assignee.toString());
            enquiryActivity.setEnquiry(enquiry);
            enquiryActivity.setAssigneeFrom(enquiry.getAssignee());
            enquiryActivity.setAssigneeTo(newAssignee);
            enquiryActivity.setComments(comments);
            enquiryActivity.setCreatedDateTime(DateUtil.getSystemDateTime());
            enquiryActivity.setCreatedBy(createdBy);
            getHibernateTemplate().save(enquiryActivity);
        }
    }

    public void addChangeStatusLog(Long[] enquiryIds, Long newStatusId, String comments, Long userId) {
        EnquiryStatus newEnquiryStatus = (EnquiryStatus) getHibernateTemplate().load(EnquiryStatus.class, newStatusId);
        UserView createdBy = this.userViewDAO.loadUser(userId);
        for (Long enquiryId : enquiryIds) {
            Enquiry enquiry = this.enquiryDAO.getEnquiry(enquiryId);
            EnquiryActivity enquiryActivity = new EnquiryActivity();
            enquiryActivity.setActivityType(EnquiryActivityType.change_status.toString());
            enquiryActivity.setEnquiry(enquiry);
            enquiryActivity.setStatusFrom(enquiry.getEnquiryStatus());
            enquiryActivity.setStatusTo(newEnquiryStatus);
            enquiryActivity.setComments(comments);
            enquiryActivity.setCreatedDateTime(DateUtil.getSystemDateTime());
            enquiryActivity.setCreatedBy(createdBy);
            getHibernateTemplate().save(enquiryActivity);
        }
    }

    public void addChangeStatusLog(Long enquiryId, Long newStatusId, String comments, Long userId) {
        EnquiryStatus newEnquiryStatus = (EnquiryStatus) getHibernateTemplate().load(EnquiryStatus.class, newStatusId);
        UserView createdBy = this.userViewDAO.loadUser(userId);
        Enquiry enquiry = this.enquiryDAO.getEnquiry(enquiryId);
        EnquiryActivity enquiryActivity = new EnquiryActivity();
        enquiryActivity.setActivityType(EnquiryActivityType.change_status.toString());
        enquiryActivity.setEnquiry(enquiry);
        enquiryActivity.setStatusFrom(enquiry.getEnquiryStatus());
        enquiryActivity.setStatusTo(newEnquiryStatus);
        enquiryActivity.setComments(comments);
        enquiryActivity.setCreatedDateTime(DateUtil.getSystemDateTime());
        enquiryActivity.setCreatedBy(createdBy);
        getHibernateTemplate().save(enquiryActivity);
    }

    public void updateEnquiryStatus(Long[] enquiryIds, Long newStatusId) {
        Query query = getSession().createQuery("update Enquiry set enquiryStatus.id=:newStatusId where id in (:enqIds)");
        query.setParameter("newStatusId", newStatusId);
        query.setParameterList("enqIds", enquiryIds);
        query.executeUpdate();
    }

    public void addFollowupLog(Long[] enquiryIds, String followupActivity, String comments, Long userId) {
        UserView createdBy = this.userViewDAO.loadUser(userId);
        for (Long enquiryId : enquiryIds) {
            Enquiry enquiry = this.enquiryDAO.getEnquiry(enquiryId);
            EnquiryActivity enquiryActivity = new EnquiryActivity();
            enquiryActivity.setActivityType(EnquiryActivityType.followup.toString());
            enquiryActivity.setEnquiry(enquiry);
            enquiryActivity.setFollowupActivity(followupActivity);
            enquiryActivity.setComments(comments);
            enquiryActivity.setCreatedDateTime(DateUtil.getSystemDateTime());
            enquiryActivity.setCreatedBy(createdBy);
            getHibernateTemplate().save(enquiryActivity);
        }
    }

    public List<EnquiryActivity> getEnquiryActivities(Long enquiryId) {
        return getSession().createCriteria(EnquiryActivity.class).createAlias("enquiry", "enquiry").add(Restrictions.eq("enquiry.id", enquiryId)).addOrder(Order.desc("createdDateTime")).list();
    }

    public void updateEnquiryStatus(Long enquiryId, Long newStatusId) {
        Enquiry enquiry = (Enquiry) getHibernateTemplate().load(Enquiry.class, enquiryId);
        enquiry.setEnquiryStatus((EnquiryStatus) getHibernateTemplate().load(EnquiryStatus.class, newStatusId));
        getHibernateTemplate().update(enquiry);
    }

    public void updateFormDetails(Long enquiryId, String formNo, Long formFee, Date formIssueDate, Long receiptNo, Long userId) {
        Enquiry enquiry = (Enquiry) getHibernateTemplate().load(Enquiry.class, enquiryId);
        enquiry.setFormNo(formNo);
        enquiry.setFormFee(formFee);
        enquiry.setReceiptNo(receiptNo);
        enquiry.setFormIssueDate(formIssueDate);
        enquiry.setFormIssuedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        getHibernateTemplate().update(enquiry);
    }
}
