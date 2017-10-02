package com.narendra.uuc.security.service.impl;

import com.narendra.uuc.core.domain.User;
import com.narendra.uuc.security.dao.UserManagerDAO;
import com.narendra.uuc.security.exception.DuplicateNameFoundException;
import com.narendra.uuc.security.service.UserManagerService;
import java.util.List;

public class UserManagerServiceImpl implements UserManagerService {
    private UserManagerDAO userManagerDAO;

    public UserManagerDAO getUserManagerDAO() {
        return this.userManagerDAO;
    }

    public void setUserManagerDAO(UserManagerDAO userManagerDAO) {
        this.userManagerDAO = userManagerDAO;
    }

    public User authenticateUser(String userName, String password) {
        return this.userManagerDAO.authenticateUser(userName, password);
    }

    public User loadByUsername(String userName) {
        return this.userManagerDAO.loadByUsername(userName);
    }

    public List<User> getAllActiveUsers() {
        return this.userManagerDAO.getAllActiveUsers();
    }

    public List<User> getAllUsers() {
        return this.userManagerDAO.getAllUsers();
    }

    public Long addUser(User user, Long addedByUserId) throws DuplicateNameFoundException {
        if (this.userManagerDAO.isUsernameExist(user.getUserName()).booleanValue()) {
            throw new DuplicateNameFoundException("User name ['" + user.getUserName() + "'] already exist");
        }
        if (user.getActive() == null) {
            user.setActive(Boolean.FALSE);
        }
        return this.userManagerDAO.addUser(user, addedByUserId);
    }

    public void updateUser(User user, Long updateByUserId, Long[] roleIds) throws DuplicateNameFoundException {
        User loadedUser = this.userManagerDAO.loadByUsername(user.getUserName());
        if (loadedUser == null || loadedUser.getId().equals(user.getId())) {
            if (user.getActive() == null) {
                user.setActive(Boolean.FALSE);
            }
            this.userManagerDAO.updateUser(user, updateByUserId);
            if (roleIds != null && roleIds.length > 0) {
                this.userManagerDAO.assignRolesToUser(user.getId(), roleIds);
                return;
            }
            return;
        }
        throw new DuplicateNameFoundException("User name ['" + user.getUserName() + "'] already exists");
    }

    public User getUser(Long userId) {
        return this.userManagerDAO.load(userId);
    }

    public List<User> getUsersOfRole(Long roleId) {
        return this.userManagerDAO.getUsersOfRole(roleId);
    }

    public void changePassword(Long userId, String newPassword) {
        this.userManagerDAO.changePassword(userId, newPassword);
    }
}
