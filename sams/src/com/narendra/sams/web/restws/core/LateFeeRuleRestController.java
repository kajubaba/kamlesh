package com.narendra.sams.web.restws.core;

import com.narendra.sams.core.service.LateFeeRuleService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.mapper.vo.LateFeeRuleVOMapper;
import com.narendra.sams.web.restws.vo.LateFeeRuleVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/latefeerule"})
public class LateFeeRuleRestController {
    @Autowired
    private LateFeeRuleService lateFeeRuleService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<LateFeeRuleVO> getBusStops() {
        return LateFeeRuleVOMapper.prepareLateFeeRuleVOs(this.lateFeeRuleService.getAllLateFeeRules(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }
}
