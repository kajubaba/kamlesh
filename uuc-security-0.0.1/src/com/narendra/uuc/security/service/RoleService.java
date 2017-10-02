package com.narendra.uuc.security.service;

import com.narendra.uuc.core.domain.Privilege;
import com.narendra.uuc.core.domain.Role;
import com.narendra.uuc.security.exception.DuplicateNameFoundException;
import java.util.List;

public interface RoleService {
    Long addRole(Role role, Long l) throws DuplicateNameFoundException;

    List<Role> getAllRoles();

    List<Privilege> getPrivileges(Long l);

    Role getRole(Long l);

    List<Role> getRoles(Long l);

    void updateRole(Role role, Long[] lArr, Long l) throws DuplicateNameFoundException;
}
