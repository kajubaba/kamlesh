package com.narendra.uuc.core.service;

import com.narendra.uuc.core.domain.Application;
import com.narendra.uuc.core.domain.Role;
import com.narendra.uuc.core.domain.User;
import java.util.List;

public interface UserService {
    List<Application> getActiveApplications();

    List<User> getActiveUsers(Long l);

    Application getApplicationByName(String str);

    User getUserByName(String str, Long l);

    List<Role> getUserRoles(Long l, Long l2);

    User loadUserById(Long l);
}
