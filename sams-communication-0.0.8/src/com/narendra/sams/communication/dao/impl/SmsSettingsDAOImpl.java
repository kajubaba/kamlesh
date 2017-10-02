package com.narendra.sams.communication.dao.impl;

import com.narendra.sams.communication.dao.SmsSettingsDAO;
import com.narendra.sams.communication.domain.SMSSetting;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class SmsSettingsDAOImpl extends HibernateDaoSupport implements SmsSettingsDAO {
    public void addSMSSettings(SMSSetting smsSetting, Long userId) {
        getHibernateTemplate().save(smsSetting);
    }

    public SMSSetting getSMSSettings(Long instituteId, String settingsType) {
        Criteria crt = getSession().createCriteria(SMSSetting.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.add(Restrictions.eq("notificationType", settingsType));
        return (SMSSetting) crt.uniqueResult();
    }

    public SMSSetting getSMSSettings(String settingsType) {
        Criteria crt = getSession().createCriteria(SMSSetting.class);
        crt.add(Restrictions.eq("notificationType", settingsType));
        return (SMSSetting) crt.uniqueResult();
    }

    public void updateSMSSettings(SMSSetting smsSetting, Long userId) {
        SMSSetting persistSetting = (SMSSetting) getHibernateTemplate().load(SMSSetting.class, smsSetting.getId());
        persistSetting.setIsEnabled(smsSetting.getIsEnabled());
        persistSetting.setSendToFather(smsSetting.getSendToFather());
        persistSetting.setSendToMother(smsSetting.getSendToMother());
        persistSetting.setSendToStudent(smsSetting.getSendToStudent());
        getHibernateTemplate().update(persistSetting);
    }
}
