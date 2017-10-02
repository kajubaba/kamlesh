package com.narendra.uuc.security.dao.impl;

import com.narendra.uuc.core.domain.Role;
import com.narendra.uuc.core.domain.User;
import com.narendra.uuc.core.utils.DateUtil;
import com.narendra.uuc.security.dao.UserManagerDAO;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserManagerDAOImpl extends HibernateDaoSupport implements UserManagerDAO {
    public User authenticateUser(String userName, String password) {
        return (User) getSession().createCriteria(User.class).add(Restrictions.eq("userName", userName)).add(Restrictions.eq("password", password)).uniqueResult();
    }

    public User loadByUsername(String userName) {
        return (User) getSession().createCriteria(User.class).add(Restrictions.eq("userName", userName)).uniqueResult();
    }

    public User load(Long userId) {
        return (User) getHibernateTemplate().load(User.class, userId);
    }

    public List<User> getAllActiveUsers() {
        return getSession().createCriteria(User.class).add(Restrictions.eq("active", Boolean.TRUE)).list();
    }

    public List<User> getAllUsers() {
        return getSession().createCriteria(User.class).list();
    }

    public Long addUser(User user, Long addedByUserId) {
        User loadedUser = load(addedByUserId);
        user.setCreatedBy(loadedUser);
        user.setModifiedBy(loadedUser);
        user.setCreatedDate(DateUtil.getSystemDateTime());
        user.setModifiedDate(DateUtil.getSystemDateTime());
        return (Long) getHibernateTemplate().save(user);
    }

    public Boolean isUsernameExist(String userName) {
        if (loadByUsername(userName) != null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void updateUser(User user, Long updateByUserId) {
        User loadedUser = load(user.getId());
        loadedUser.copyEditableProperties(user);
        loadedUser.setModifiedBy(load(updateByUserId));
        loadedUser.setModifiedDate(DateUtil.getSystemDateTime());
        getHibernateTemplate().update(loadedUser);
    }

    public void assignRolesToUser(Long userId, Long[] roleIds) {
        User user = load(userId);
        Criteria roleCrt = getSession().createCriteria(Role.class);
        roleCrt.add(Restrictions.in("id", roleIds));
        user.setRoles(new HashSet(roleCrt.list()));
        getHibernateTemplate().update(user);
    }

    public List<User> getUsersOfRole(Long roleId) {
        Criteria userCrt = getSession().createCriteria(User.class);
        userCrt.createAlias("roles", "role");
        userCrt.add(Restrictions.eq("role.id", roleId));
        return userCrt.list();
    }

    public void changePassword(Long userId, String newPassword) {
        load(userId).setPassword(newPassword);
    }
}
