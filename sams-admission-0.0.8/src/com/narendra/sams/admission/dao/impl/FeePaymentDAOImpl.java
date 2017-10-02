package com.narendra.sams.admission.dao.impl;

import com.narendra.sams.admission.dao.FeePaymentDAO;
import com.narendra.sams.admission.domain.FeeTransaction;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FeePaymentDAOImpl extends HibernateDaoSupport implements FeePaymentDAO {
    public UserView loadUser(Long userId) {
        return (UserView) getHibernateTemplate().load(UserView.class, userId);
    }

    public Long payFee(FeeTransaction feeTransaction, Long userId) {
        feeTransaction.setUser(loadUser(userId));
        feeTransaction.setTransactionTime(DateUtil.getSystemDateTime());
        return (Long) getHibernateTemplate().save(feeTransaction);
    }
}
