package com.narendra.sams.communication.dao.impl;

import com.narendra.sams.communication.dao.SmsProviderDAO;
import com.narendra.sams.communication.domain.InstituteSMSProvider;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class SmsProviderDAOImpl extends HibernateDaoSupport implements SmsProviderDAO {
    public void addSMSProvider(InstituteSMSProvider instituteSMSProvider, Long userId) {
        instituteSMSProvider.setIsEnabled(Boolean.TRUE);
        getHibernateTemplate().save(instituteSMSProvider);
    }

    public void updateSMSProvider(InstituteSMSProvider instituteSMSProvider, Long userId) {
        InstituteSMSProvider persistProvider = (InstituteSMSProvider) getHibernateTemplate().load(InstituteSMSProvider.class, instituteSMSProvider.getId());
        persistProvider.setIsEnabled(Boolean.TRUE);
        persistProvider.setAuthKey(instituteSMSProvider.getAuthKey());
        persistProvider.setSenderId(instituteSMSProvider.getSenderId());
        persistProvider.setSmsProviderName(instituteSMSProvider.getSmsProviderName());
        persistProvider.setUrl(instituteSMSProvider.getUrl());
        getHibernateTemplate().update(persistProvider);
    }

    public InstituteSMSProvider getSMSProvider(Long instituteId) {
        Criteria crt = getSession().createCriteria(InstituteSMSProvider.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.add(Restrictions.eq("isEnabled", Boolean.TRUE));
        return (InstituteSMSProvider) crt.uniqueResult();
    }

    public InstituteSMSProvider getSMSProvider() {
        Criteria crt = getSession().createCriteria(InstituteSMSProvider.class);
        crt.add(Restrictions.eq("isEnabled", Boolean.TRUE));
        return (InstituteSMSProvider) crt.uniqueResult();
    }

    public List<InstituteSMSProvider> getSMSProviders(Long instituteId) {
        Criteria crt = getSession().createCriteria(InstituteSMSProvider.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.addOrder(Order.asc("smsProviderName"));
        return crt.list();
    }
}
