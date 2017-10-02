package com.narendra.sams.web.restws.mapper.vo;

import com.narendra.sams.core.domain.Installment;
import com.narendra.sams.web.restws.vo.InstallmentVO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;

public class InstallmentVOMapper {
    public static List<InstallmentVO> prepareInstallmentVOs(List<Installment> installments) {
        List<InstallmentVO> installmentVOs = new ArrayList();
        if (!(installments == null || installments.isEmpty())) {
            for (Installment installment : installments) {
                installmentVOs.add(prepareInstallmentVO(installment));
            }
            Collections.sort(installmentVOs, new BeanComparator("displayOrder", new NullComparator()));
        }
        return installmentVOs;
    }

    public static InstallmentVO prepareInstallmentVO(Installment installment) {
        if (installment == null) {
            return null;
        }
        InstallmentVO installmentVO = new InstallmentVO();
        installmentVO.setId(installment.getId());
        installmentVO.setName(installment.getName());
        installmentVO.setActive(installment.getActive());
        installmentVO.setDisplayOrder(installment.getDisplayOrder());
        return installmentVO;
    }
}
