package com.narendra.sams.web.restws.core;

import com.narendra.sams.core.service.InstallmentService;
import com.narendra.sams.web.restws.mapper.vo.InstallmentVOMapper;
import com.narendra.sams.web.restws.vo.InstallmentVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/installment"})
public class InstallmentRestController {
    @Autowired
    private InstallmentService installmentService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/list"})
    public List<InstallmentVO> getInstallments() {
        return InstallmentVOMapper.prepareInstallmentVOs(this.installmentService.getAllActiveInstallments());
    }
}
