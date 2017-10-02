package com.narendra.sams.web.restws.enquiry;

import com.narendra.sams.web.restws.enquiry.vo.UserVO;
import com.narendra.sams.web.utils.ServletContextManager;
import com.narendra.uuc.core.domain.User;
import com.narendra.uuc.core.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/enquiry/user"})
public class EnquiryUserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<UserVO> getUsers() {
        List<User> users = this.userService.getActiveUsers(ServletContextManager.getApplication().getId());
        List<UserVO> userVOs = new ArrayList();
        if (users != null) {
            for (User user : users) {
                UserVO userVO = new UserVO();
                userVO.setId(user.getId());
                userVO.setName(user.getFirstName() + " " + user.getLastName());
                userVOs.add(userVO);
            }
        }
        return userVOs;
    }
}
