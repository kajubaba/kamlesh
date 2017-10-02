package com.narendra.sams.web.restws.common;

import com.narendra.sams.core.domain.Bank;
import com.narendra.sams.core.service.BankService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.common.vo.BankVO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/bank"})
public class BankRestController {
    @Autowired
    private BankService bankService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<BankVO> getActiveBanks() {
        return prepareBankListVO(this.bankService.getActiveBanks(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    private List<BankVO> prepareBankListVO(List<Bank> banks) {
        List<BankVO> bankVOList = new ArrayList();
        if (banks != null) {
            for (Bank bank : banks) {
                BankVO bankVO = new BankVO();
                bankVO.setId(bank.getId());
                bankVO.setBankName(bank.getBankName());
                bankVOList.add(bankVO);
            }
        }
        return bankVOList;
    }
}
