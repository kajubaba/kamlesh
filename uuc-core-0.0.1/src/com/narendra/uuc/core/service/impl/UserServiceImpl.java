package com.narendra.uuc.core.service.impl;

import com.narendra.uuc.core.dao.UserDAO;
import com.narendra.uuc.core.domain.Application;
import com.narendra.uuc.core.domain.Role;
import com.narendra.uuc.core.domain.User;
import com.narendra.uuc.core.service.UserService;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return this.userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User loadUserById(Long userId) {
        return this.userDAO.loadUserById(userId);
    }

    public Application getApplicationByName(String applicationName) {
        return this.userDAO.getApplicationByName(applicationName);
    }

    public List<Application> getActiveApplications() {
        return this.userDAO.getActiveApplications();
    }

    public User getUserByName(String userName, Long applicationId) {
        return this.userDAO.getUserByName(userName, applicationId);
    }

    public List<Role> getUserRoles(Long userId, Long applicationId) {
        return this.userDAO.getUserRoles(userId, applicationId);
    }

    public List<User> getActiveUsers(Long applicationId) {
        return this.userDAO.getActiveUsers(applicationId);
    }
}
