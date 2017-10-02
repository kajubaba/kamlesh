package com.narendra.sams.web.restws.core;

import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.FeeHeadService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.form.FeeHeadForm;
import com.narendra.sams.web.restws.mapper.form.FeeHeadFormMapper;
import com.narendra.sams.web.restws.mapper.vo.FeeHeadVOMapper;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.restws.vo.FeeHeadVO;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/fee-head"})
public class ManageFeeHeadRestController {
    @Autowired
    private FeeHeadService feeHeadService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxResponse saveFeeHead(@RequestBody FeeHeadForm feeHeadForm) {
        FeeHead feeHead = FeeHeadFormMapper.prepareFeeHeadDomain(feeHeadForm);
        feeHead.setInstitute(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute());
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            ajaxResponse.setGeneratedId(this.feeHeadService.saveFeeHead(feeHead, LoggedinUserAssistant.getLoggedInUserId()));
            ajaxResponse.setStatus(AjaxStatus.OK.toString());
        } catch (DuplicateNameFoundException e) {
            ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/delete/{feeHeadId}"})
    public void deleteFeeHead() {
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{feeHeadId}"})
    public FeeHeadVO getBusStop(@PathVariable Long feeHeadId) {
        return FeeHeadVOMapper.prepareFeeHeadVO(this.feeHeadService.getFeeHead(feeHeadId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<FeeHeadVO> listFeeHeads() {
        return FeeHeadVOMapper.prepareFeeHeadVOs(this.feeHeadService.getAllFeeHeads(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId()));
    }
}
