package com.narendra.uuc.web.auth;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class UUCSuccessHandler implements AuthenticationSuccessHandler {
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_NEW_USER") || roles.contains("ROLE_MANAGE_USER_ROLE")) {
            response.sendRedirect("../user/list");
        } else if (roles.contains("ROLE_NEW_ROLE") || roles.contains("ROLE_MANAGE_ROLE_PERMISSIONS")) {
            response.sendRedirect("../role/list");
        }
    }
}
