package com.narendra.uuc.web.auth;

import com.narendra.uuc.core.domain.Application;
import com.narendra.uuc.core.domain.Privilege;
import com.narendra.uuc.core.domain.Role;
import com.narendra.uuc.core.domain.User;
import com.narendra.uuc.core.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService {
    private UserService userService;

    public UserService getUserService() {
        return this.userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
        Application application = this.userService.getApplicationByName("UUC000");
        User user = this.userService.getUserByName(userName, application.getId());
        if (user == null) {
            throw new UsernameNotFoundException("Invalid User name and password");
        } else if (user.getActive() == null || !(user.getActive() == null || user.getActive().booleanValue())) {
            throw new UsernameNotFoundException("Invalid User name and password");
        } else {
            List<GrantedAuthority> grantedAuthorities = new ArrayList();
            List<Role> userRoles = this.userService.getUserRoles(user.getId(), application.getId());
            if (userRoles != null) {
                for (Role role : userRoles) {
                    if (role.getPrivileges() != null) {
                        for (Privilege privilege : role.getPrivileges()) {
                            grantedAuthorities.add(new GrantedAuthorityImpl(privilege.getName()));
                        }
                    }
                }
            }
            if (!grantedAuthorities.isEmpty()) {
                return new UserDetail(user.getId(), user.getUserName(), user.getPassword(), grantedAuthorities, user.getFirstName(), user.getLastName());
            }
            throw new UsernameNotFoundException("Invalid User name and password");
        }
    }
}
