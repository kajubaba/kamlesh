package com.narendra.uuc.core.dao.impl;

import com.narendra.uuc.core.dao.UserDAO;
import com.narendra.uuc.core.domain.Application;
import com.narendra.uuc.core.domain.Role;
import com.narendra.uuc.core.domain.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {
    public User loadUserById(Long userId) {
        return (User) getHibernateTemplate().load(User.class, userId);
    }

    public Application getApplicationByName(String applicationName) {
        return (Application) getSession().createCriteria(Application.class).add(Restrictions.eq("name", applicationName)).uniqueResult();
    }

    public List<Application> getActiveApplications() {
        return getSession().createCriteria(Application.class).add(Restrictions.eq("active", Boolean.TRUE)).list();
    }

    public User getUserByName(String userName, Long applicationId) {
        Criteria crt = getSession().createCriteria(User.class);
        crt.createAlias("roles", "role");
        crt.createAlias("role.application", "application");
        crt.setFetchMode("role", FetchMode.JOIN);
        crt.add(Restrictions.eq("userName", userName)).add(Restrictions.eq("application.id", applicationId));
        return (User) crt.uniqueResult();
    }

    public List<Role> getUserRoles(Long userId, Long applicationId) {
        Criteria crt = getSession().createCriteria(Role.class);
        crt.createAlias("privileges", "privilege");
        crt.createAlias("users", "user");
        crt.createAlias("application", "application");
        crt.add(Restrictions.eq("user.id", userId)).add(Restrictions.eq("application.id", applicationId)).add(Restrictions.eq("active", Boolean.TRUE));
        crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return crt.list();
    }

    public List<User> getActiveUsers(Long applicationId) {
        Criteria crt = getSession().createCriteria(User.class);
        crt.createAlias("roles", "role");
        crt.add(Restrictions.eq("role.application.id", applicationId));
        return crt.list();
    }
}
