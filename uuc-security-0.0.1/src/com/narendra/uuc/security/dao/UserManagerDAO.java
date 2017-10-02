package com.narendra.uuc.security.dao;

import com.narendra.uuc.core.domain.User;
import java.util.List;

public interface UserManagerDAO {
    Long addUser(User user, Long l);

    void assignRolesToUser(Long l, Long[] lArr);

    User authenticateUser(String str, String str2);

    void changePassword(Long l, String str);

    List<User> getAllActiveUsers();

    List<User> getAllUsers();

    List<User> getUsersOfRole(Long l);

    Boolean isUsernameExist(String str);

    User load(Long l);

    User loadByUsername(String str);

    void updateUser(User user, Long l);
}
