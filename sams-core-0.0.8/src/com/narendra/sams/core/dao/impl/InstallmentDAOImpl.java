package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.InstallmentDAO;
import com.narendra.sams.core.domain.Installment;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class InstallmentDAOImpl extends HibernateDaoSupport implements InstallmentDAO {
    public List<Installment> getAllActiveInstallments() {
        Criteria crt = getSession().createCriteria(Installment.class);
        crt.addOrder(Order.asc("displayOrder"));
        return crt.list();
    }

    public List<Installment> getInstallments(Long installmentCount) {
        Criteria crt = getSession().createCriteria(Installment.class);
        crt.add(Restrictions.le("displayOrder", installmentCount));
        crt.addOrder(Order.asc("displayOrder"));
        return crt.list();
    }
}
