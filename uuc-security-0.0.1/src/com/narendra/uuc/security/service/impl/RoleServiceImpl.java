package com.narendra.uuc.security.service.impl;

import com.narendra.uuc.core.domain.Privilege;
import com.narendra.uuc.core.domain.Role;
import com.narendra.uuc.security.dao.RoleDAO;
import com.narendra.uuc.security.exception.DuplicateNameFoundException;
import com.narendra.uuc.security.service.RoleService;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    private RoleDAO roleDAO;

    public RoleDAO getRoleDAO() {
        return this.roleDAO;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public List<Role> getAllRoles() {
        return this.roleDAO.getAllRoles();
    }

    public List<Role> getRoles(Long applicationId) {
        return this.roleDAO.getRoles(applicationId);
    }

    public Long addRole(Role role, Long userId) throws DuplicateNameFoundException {
        if (!this.roleDAO.isRoleNameExist(role.getName()).booleanValue()) {
            return this.roleDAO.addRole(role, userId);
        }
        throw new DuplicateNameFoundException("Role name ['" + role.getName() + "'] already exist");
    }

    public Role getRole(Long roleId) {
        return this.roleDAO.getRole(roleId);
    }

    public void updateRole(Role role, Long[] privilegeIds, Long userId) throws DuplicateNameFoundException {
        Role loadedRole = this.roleDAO.loadRoleByName(role.getName());
        if (loadedRole == null || loadedRole.getId().equals(role.getId())) {
            this.roleDAO.updateRole(role, userId);
            this.roleDAO.assignPrivilegesToRole(role.getId(), privilegeIds);
            return;
        }
        throw new DuplicateNameFoundException("Role name ['" + role.getName() + "'] already exists");
    }

    public List<Privilege> getPrivileges(Long applicationId) {
        return this.roleDAO.getPrivileges(applicationId);
    }
}
