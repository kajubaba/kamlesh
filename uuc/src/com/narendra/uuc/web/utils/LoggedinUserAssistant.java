package com.narendra.uuc.web.utils;

import com.narendra.uuc.web.auth.UserDetail;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggedinUserAssistant {
    public static UserDetail getLoggedInUser() {
        return (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Long getLoggedInUserId() {
        return ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    public static Boolean hasPermission(String privilege) {
        if (((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities().contains(new GrantedAuthorityImpl(privilege))) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
