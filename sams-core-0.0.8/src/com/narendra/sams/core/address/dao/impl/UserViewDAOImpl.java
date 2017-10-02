package com.narendra.sams.core.address.dao.impl;

import com.narendra.sams.core.address.dao.UserViewDAO;
import com.narendra.sams.core.domain.UserView;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserViewDAOImpl extends HibernateDaoSupport implements UserViewDAO {
    public UserView loadUser(Long userId) {
        return (UserView) getHibernateTemplate().load(UserView.class, userId);
    }
}
