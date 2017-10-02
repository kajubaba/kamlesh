package com.narendra.sams.web.auth;

import com.narendra.sams.core.util.DateUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MainInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String[] parts = request.getRequestURI().toString().split("/");
        if (parts != null && parts.length > 1) {
            request.setAttribute("tabName", parts[2]);
            request.setAttribute("serverTime", DateUtil.getSystemDateTime());
        }
        return true;
    }
}
