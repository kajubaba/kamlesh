package com.narendra.uuc.security.dao;

import com.narendra.uuc.core.domain.Privilege;
import com.narendra.uuc.core.domain.Role;
import java.util.List;

public interface RoleDAO {
    Long addRole(Role role, Long l);

    void assignPrivilegesToRole(Long l, Long[] lArr);

    List<Role> getAllRoles();

    List<Privilege> getPrivileges(Long l);

    Role getRole(Long l);

    List<Role> getRoles(Long l);

    Boolean isRoleNameExist(String str);

    Role loadRoleById(Long l);

    Role loadRoleByName(String str);

    void updateRole(Role role, Long l);
}
