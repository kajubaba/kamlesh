package com.narendra.sams.web.apipermission;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/api"})
public class APIPermissionRestController {
    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/permissions"})
    public List<String> getUserPermissions(Principal principal) {
        List<String> permissions = new ArrayList();
        for (GrantedAuthority authority : ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities()) {
            permissions.add(authority.getAuthority());
        }
        return permissions;
    }
}
