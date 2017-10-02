package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.AcademicYearBusFeeDAO;
import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusFeeDetail;
import com.narendra.sams.core.domain.BusFeeInstallment;
import com.narendra.sams.core.domain.BusFeeInstallmentDetail;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AcademicYearBusFeeDAOImpl extends HibernateDaoSupport implements AcademicYearBusFeeDAO {
    public UserView loadUser(Long userId) {
        return (UserView) getHibernateTemplate().load(UserView.class, userId);
    }

    public List<BusFee> getAssigedBusFee(Long academicYearId) {
        Criteria crt = getSession().createCriteria(BusFee.class);
        crt.createAlias("busStop", "busStop");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.addOrder(Order.asc("busStop.distance"));
        crt.addOrder(Order.asc("busStop.name"));
        return crt.list();
    }

    public void updateFeeChanges(List<BusFee> bussFeeList) {
        for (BusFee busFee : bussFeeList) {
            BusFee loadedBusFee = loadBusFeeById(busFee.getId());
            if (busFee.getRs() == null) {
                loadedBusFee.setRs(Long.valueOf(0));
            } else {
                loadedBusFee.setRs(busFee.getRs());
            }
        }
    }

    public BusFee getBusFee(Long id) {
        return (BusFee) getHibernateTemplate().get(BusFee.class, id);
    }

    public BusFee loadBusFeeById(Long id) {
        return (BusFee) getHibernateTemplate().load(BusFee.class, id);
    }

    public void addBusFee(List<BusFee> busFeeList) {
        getHibernateTemplate().saveOrUpdateAll(busFeeList);
    }

    public void addBusFeeInstallment(BusFeeInstallment busFeeInstallment, Long userId) {
        UserView user = loadUser(userId);
        Date currentDatetime = DateUtil.getSystemDateTime();
        busFeeInstallment.setCreatedBy(user);
        busFeeInstallment.setModifiedBy(user);
        busFeeInstallment.setCreatedDate(currentDatetime);
        busFeeInstallment.setModifiedDateTime(currentDatetime);
        getHibernateTemplate().save(busFeeInstallment);
    }

    public BusFeeInstallment getBusFeeInstallment(Long academicYearId) {
        Criteria crt = getSession().createCriteria(BusFeeInstallment.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        return (BusFeeInstallment) crt.uniqueResult();
    }

    public void updateBusFeeInstallmentDetail(BusFeeInstallment busFeeInstallment, Long userId) {
        UserView user = loadUser(userId);
        Date currentDatetime = DateUtil.getSystemDateTime();
        BusFeeInstallment persistBusFeeInstallment = (BusFeeInstallment) getHibernateTemplate().get(BusFeeInstallment.class, busFeeInstallment.getId());
        persistBusFeeInstallment.setInstallmentCount(busFeeInstallment.getInstallmentCount());
        persistBusFeeInstallment.setSetupType(busFeeInstallment.getSetupType());
        persistBusFeeInstallment.setModifiedBy(user);
        persistBusFeeInstallment.setModifiedDateTime(currentDatetime);
        Boolean found;
        if (busFeeInstallment.getBusFeeInstallmentDetails().size() >= persistBusFeeInstallment.getBusFeeInstallmentDetails().size()) {
            for (BusFeeInstallmentDetail busFeeInstallmentDetail : busFeeInstallment.getBusFeeInstallmentDetails()) {
                found = Boolean.FALSE;
                if (busFeeInstallmentDetail.getId() == null) {
                    persistBusFeeInstallment.getBusFeeInstallmentDetails().add(busFeeInstallmentDetail);
                } else {
                    for (BusFeeInstallmentDetail persistBusFeeInstallmentDetail : persistBusFeeInstallment.getBusFeeInstallmentDetails()) {
                        if (busFeeInstallmentDetail.getId().equals(persistBusFeeInstallmentDetail.getId())) {
                            persistBusFeeInstallmentDetail.setFeePercent(busFeeInstallmentDetail.getFeePercent());
                            found = Boolean.TRUE;
                            break;
                        }
                    }
                    if (!found.booleanValue()) {
                        persistBusFeeInstallment.getBusFeeInstallmentDetails().add(busFeeInstallmentDetail);
                    }
                }
            }
        } else {
            Set<BusFeeInstallmentDetail> installmentsToBeDeleted = new HashSet();
            for (BusFeeInstallmentDetail persistBusFeeInstallmentDetail2 : persistBusFeeInstallment.getBusFeeInstallmentDetails()) {
                found = Boolean.FALSE;
                for (BusFeeInstallmentDetail busFeeInstallmentDetail2 : busFeeInstallment.getBusFeeInstallmentDetails()) {
                    if (busFeeInstallmentDetail2.getId().equals(persistBusFeeInstallmentDetail2.getId())) {
                        persistBusFeeInstallmentDetail2.setFeePercent(busFeeInstallmentDetail2.getFeePercent());
                        found = Boolean.TRUE;
                        break;
                    }
                }
                if (!found.booleanValue()) {
                    installmentsToBeDeleted.add(persistBusFeeInstallmentDetail2);
                }
            }
            if (!installmentsToBeDeleted.isEmpty()) {
                persistBusFeeInstallment.getBusFeeInstallmentDetails().removeAll(installmentsToBeDeleted);
            }
        }
        getHibernateTemplate().save(persistBusFeeInstallment);
    }

    public BusFee getBusFee(Long academicYearId, Long busStopId) {
        Criteria crt = getSession().createCriteria(BusFee.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("busStop.id", busStopId));
        return (BusFee) crt.uniqueResult();
    }

    public BusFeeInstallmentDetail getBusFeeInstallmentDetails(Long academicYearId, Long installment) {
        Criteria crt = getSession().createCriteria(BusFeeInstallmentDetail.class);
        crt.createAlias("busFeeInstallment", "busFeeInstallment");
        crt.createAlias("busFeeInstallment.academicYear", "academicYear");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.add(Restrictions.eq("installment.id", installment));
        return (BusFeeInstallmentDetail) crt.uniqueResult();
    }

    public void deleteBusStop(Long busFeeId) {
        getHibernateTemplate().delete(getHibernateTemplate().load(BusFee.class, busFeeId));
    }

    public void saveBusFeeInstallments(List<BusFee> busFees) {
        for (BusFee busFee : busFees) {
            Criteria crt = getSession().createCriteria(BusFeeDetail.class);
            crt.add(Restrictions.eq("busFee.id", busFee.getId()));
            List<BusFeeDetail> persisitBusFeeDetails = crt.list();
            if (persisitBusFeeDetails != null) {
                if (persisitBusFeeDetails.size() > busFee.getBusFeeDetails().size()) {
                    ListIterator<BusFeeDetail> itr = persisitBusFeeDetails.listIterator();
                    while (itr.hasNext()) {
                        BusFeeDetail item = (BusFeeDetail) itr.next();
                        Boolean found = Boolean.FALSE;
                        for (BusFeeDetail busFeeDetail : busFee.getBusFeeDetails()) {
                            if (busFeeDetail.getId().equals(item.getId())) {
                                found = Boolean.TRUE;
                                break;
                            }
                        }
                        if (!found.booleanValue()) {
                            itr.remove();
                            getHibernateTemplate().delete(item);
                        }
                    }
                }
                for (BusFeeDetail transientFeeDetail : busFee.getBusFeeDetails()) {
                    if (transientFeeDetail.getId() != null) {
                        for (BusFeeDetail persistFeeDetail : persisitBusFeeDetails) {
                            if (persistFeeDetail.getId().equals(transientFeeDetail.getId())) {
                                persistFeeDetail.setFee(transientFeeDetail.getFee());
                                getHibernateTemplate().update(persistFeeDetail);
                                break;
                            }
                        }
                    }
                    getHibernateTemplate().save(transientFeeDetail);
                }
            } else {
                getHibernateTemplate().saveOrUpdateAll(busFee.getBusFeeDetails());
            }
        }
    }

    public void saveOrUpdateBusFeeInstallment(BusFeeInstallment busFeeInstallment, Long userId) {
        UserView user = (UserView) getHibernateTemplate().load(UserView.class, userId);
        if (busFeeInstallment.getId() == null) {
            busFeeInstallment.setCreatedBy(user);
            busFeeInstallment.setModifiedBy(user);
            busFeeInstallment.setCreatedDate(DateUtil.getSystemDateTime());
            busFeeInstallment.setModifiedDateTime(DateUtil.getSystemDateTime());
        } else {
            busFeeInstallment.setModifiedBy(user);
            busFeeInstallment.setModifiedDateTime(DateUtil.getSystemDateTime());
        }
        getHibernateTemplate().saveOrUpdate(busFeeInstallment);
    }

    public void deleteBusFeeInstallmentDetail(Long busFeeInstallmentId) {
        Criteria crt = getSession().createCriteria(BusFeeInstallmentDetail.class);
        crt.add(Restrictions.eq("busFeeInstallment.id", busFeeInstallmentId));
        List<BusFeeInstallmentDetail> busFeeInstallmentDetails = crt.list();
        if (busFeeInstallmentDetails != null) {
            getHibernateTemplate().deleteAll(busFeeInstallmentDetails);
        }
    }

    public void deleteBusFeeDetails(Long academicSessionId) {
        Criteria crt = getSession().createCriteria(BusFee.class);
        crt.add(Restrictions.eq("academicYear.id", academicSessionId));
        List<BusFee> busFees = crt.list();
        if (busFees != null) {
            for (BusFee busFee : busFees) {
                Criteria crt2 = getSession().createCriteria(BusFeeDetail.class);
                crt2.add(Restrictions.eq("busFee.id", busFee.getId()));
                List<BusFeeDetail> busFeeDetails = crt2.list();
                if (busFeeDetails != null) {
                    getHibernateTemplate().deleteAll(busFeeDetails);
                }
            }
        }
    }
}
