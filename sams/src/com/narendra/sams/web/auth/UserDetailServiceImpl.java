package com.narendra.sams.web.auth;

import com.narendra.sams.core.address.service.StopApplicationService;
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
    private StopApplicationService stopApplicationService;
    private UserService userService;

    public UserService getUserService() {
        return this.userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public StopApplicationService getStopApplicationService() {
        return this.stopApplicationService;
    }

    public void setStopApplicationService(StopApplicationService stopApplicationService) {
        this.stopApplicationService = stopApplicationService;
    }

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
        if (this.stopApplicationService.stopApplication().booleanValue()) {
            throw new UsernameNotFoundException("Invalid User name and password");
        }
        Application application = this.userService.getApplicationByName("SAMS000");
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
