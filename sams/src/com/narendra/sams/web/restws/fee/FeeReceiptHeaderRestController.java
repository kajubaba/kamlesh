package com.narendra.sams.web.restws.fee;

import com.narendra.sams.admission.domain.FeeRecieptHeader;
import com.narendra.sams.fee.service.FeeRecieptService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.fee.vo.FeeReceiptHeaderVO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/feereceiptheader"})
public class FeeReceiptHeaderRestController {
    @Autowired
    private FeeRecieptService feeRecieptService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/getall"})
    public List<FeeReceiptHeaderVO> getAllActiveFeeReceiptHeaders() {
        List<FeeReceiptHeaderVO> feeReceiptHeaderVOs = new ArrayList();
        List<FeeRecieptHeader> feeRecieptHeaders = this.feeRecieptService.getActiveFeeRecieptHeaders(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId());
        if (feeRecieptHeaders != null) {
            for (FeeRecieptHeader feeRecieptHeader : feeRecieptHeaders) {
                FeeReceiptHeaderVO feeReceiptHeaderVO = new FeeReceiptHeaderVO();
                feeReceiptHeaderVO.setId(feeRecieptHeader.getId());
                feeReceiptHeaderVO.setHeader(feeRecieptHeader.getHeader());
                feeReceiptHeaderVO.setSubHeader(feeRecieptHeader.getSubHeader());
                feeReceiptHeaderVO.setDisplayName(feeRecieptHeader.getDisplayName());
                feeReceiptHeaderVO.setIsDefault(feeRecieptHeader.getIsDefault());
                feeReceiptHeaderVOs.add(feeReceiptHeaderVO);
            }
        }
        return feeReceiptHeaderVOs;
    }
}
