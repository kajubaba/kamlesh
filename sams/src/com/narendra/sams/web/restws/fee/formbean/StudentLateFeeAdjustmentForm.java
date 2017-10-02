package com.narendra.sams.web.restws.fee.formbean;

import com.narendra.sams.web.restws.fee.vo.InstallmentLateFeeVO;
import java.util.ArrayList;
import java.util.List;

public class StudentLateFeeAdjustmentForm {
    private List<InstallmentLateFeeVO> installmentLateFees = new ArrayList();

    public List<InstallmentLateFeeVO> getInstallmentLateFees() {
        return this.installmentLateFees;
    }

    public void setInstallmentLateFees(List<InstallmentLateFeeVO> installmentLateFees) {
        this.installmentLateFees = installmentLateFees;
    }
}
