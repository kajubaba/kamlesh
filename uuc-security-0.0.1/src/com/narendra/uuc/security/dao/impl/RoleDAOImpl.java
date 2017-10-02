package com.narendra.uuc.security.dao.impl;

import com.narendra.uuc.core.dao.UserDAO;
import com.narendra.uuc.core.domain.Privilege;
import com.narendra.uuc.core.domain.Role;
import com.narendra.uuc.core.domain.User;
import com.narendra.uuc.core.utils.DateUtil;
import com.narendra.uuc.security.dao.RoleDAO;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class RoleDAOImpl extends HibernateDaoSupport implements RoleDAO {
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return this.userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<Role> getAllRoles() {
        return getSession().createCriteria(Role.class).list();
    }

    public List<Role> getRoles(Long applicationId) {
        Criteria crt = getSession().createCriteria(Role.class);
        crt.add(Restrictions.eq("application.id", applicationId));
        return crt.list();
    }

    public Long addRole(Role role, Long userId) {
        User user = this.userDAO.loadUserById(userId);
        role.setCreatedBy(user);
        role.setModifiedBy(user);
        role.setCreatedDate(DateUtil.getSystemDateTime());
        role.setModifiedDate(DateUtil.getSystemDateTime());
        return (Long) getHibernateTemplate().save(role);
    }

    public Role getRole(Long roleId) {
        return (Role) getHibernateTemplate().get(Role.class, roleId);
    }

    public Boolean isRoleNameExist(String name) {
        Criteria roleCrt = getSession().createCriteria(Role.class);
        roleCrt.setProjection(Projections.property("id"));
        roleCrt.add(Restrictions.eq("name", name).ignoreCase());
        List<Object> list = roleCrt.list();
        if (list == null || list.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Role loadRoleByName(String name) {
        return (Role) getSession().createCriteria(Role.class).add(Restrictions.eq("name", name)).uniqueResult();
    }

    public void updateRole(Role role, Long userId) {
        Role loadedRole = loadRoleById(role.getId());
        loadedRole.copyEditableProperties(role);
        loadedRole.setModifiedDate(DateUtil.getSystemDateTime());
        loadedRole.setModifiedBy(this.userDAO.loadUserById(userId));
        getHibernateTemplate().update(loadedRole);
    }

    public Role loadRoleById(Long roleId) {
        return (Role) getHibernateTemplate().load(Role.class, roleId);
    }

    public List<Privilege> getPrivileges(Long applicationId) {
        Criteria privilegeCrt = getSession().createCriteria(Privilege.class);
        privilegeCrt.addOrder(Order.asc("displayName"));
        privilegeCrt.add(Restrictions.eq("application.id", applicationId));
        return privilegeCrt.list();
    }

    public void assignPrivilegesToRole(Long roleId, Long[] privilegesIds) {
        Role role = loadRoleById(roleId);
        if (privilegesIds == null || privilegesIds.length <= 0) {
            role.setPrivileges(null);
        } else {
            Criteria privilegeCrt = getSession().createCriteria(Privilege.class);
            privilegeCrt.add(Restrictions.in("id", privilegesIds));
            role.setPrivileges(new HashSet(privilegeCrt.list()));
        }
        getHibernateTemplate().update(role);
    }
}
