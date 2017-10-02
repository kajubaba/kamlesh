package com.narendra.uuc.security.service;

import com.narendra.uuc.core.domain.User;
import com.narendra.uuc.security.exception.DuplicateNameFoundException;
import java.util.List;

public interface UserManagerService {
    Long addUser(User user, Long l) throws DuplicateNameFoundException;

    User authenticateUser(String str, String str2);

    void changePassword(Long l, String str);

    List<User> getAllActiveUsers();

    List<User> getAllUsers();

    User getUser(Long l);

    List<User> getUsersOfRole(Long l);

    User loadByUsername(String str);

    void updateUser(User user, Long l, Long[] lArr) throws DuplicateNameFoundException;
}
