package com.narendra.sams.web.restws.core;

import com.narendra.sams.core.domain.AffiliationAuthority;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.AffiliationAuthorityService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.admission.vo.AffiliationAuthorityVO;
import com.narendra.sams.web.restws.form.AffiliationAuthorityForm;
import com.narendra.sams.web.restws.mapper.form.AffiliationAuthorityFormMapper;
import com.narendra.sams.web.restws.mapper.vo.AffiliationAuthorityVOMapper;
import com.narendra.sams.web.restws.vo.AjaxResponse;
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
@RequestMapping({"/ws/affiliation-authority"})
public class ManageAffiliationAuthorityRestController {
    @Autowired
    private AffiliationAuthorityService affiliationAuthorityService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save"})
    public AjaxResponse saveFeeHead(@RequestBody AffiliationAuthorityForm affiliationAuthorityForm) {
        AffiliationAuthority affiliationAuthority = AffiliationAuthorityFormMapper.prepareAffiliationAuthorityDomain(affiliationAuthorityForm);
        affiliationAuthority.setInstitute(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute());
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            ajaxResponse.setGeneratedId(this.affiliationAuthorityService.saveAffiliationAuthority(affiliationAuthority, LoggedinUserAssistant.getLoggedInUserId()));
            ajaxResponse.setStatus(AjaxStatus.OK.toString());
        } catch (DuplicateNameFoundException e) {
            ajaxResponse.setStatus(AjaxStatus.ERROR.toString());
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/delete/{affiliationAuthorityId}"})
    public void deleteAffiliationAuthority() {
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{affiliationAuthorityId}"})
    public AffiliationAuthorityVO getAffiliationAuthority(@PathVariable Long affiliationAuthorityId) {
        return AffiliationAuthorityVOMapper.prepareAffiliationAuthority(this.affiliationAuthorityService.getAffiliationAuthority(affiliationAuthorityId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<AffiliationAuthorityVO> listAffiliationAuthorities() {
        return AffiliationAuthorityVOMapper.prepareAffiliationAuthorityVOs(this.affiliationAuthorityService.getAllAffiliationAuthorities(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId()));
    }
}
