package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.BankDAO;
import com.narendra.sams.core.domain.Bank;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BankDAOImpl extends HibernateDaoSupport implements BankDAO {
    public List<Bank> getActiveBanks(Long instituteId) {
        Criteria crt = getSession().createCriteria(Bank.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.add(Restrictions.eq("active", Boolean.TRUE));
        crt.addOrder(Order.asc("bankName"));
        return crt.list();
    }
}
