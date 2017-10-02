package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.InstallmentDAO;
import com.narendra.sams.core.domain.Installment;
import com.narendra.sams.core.service.InstallmentService;
import java.util.List;

public class InstallmentServiceImpl implements InstallmentService {
    private InstallmentDAO installmentDAO;

    public InstallmentDAO getInstallmentDAO() {
        return this.installmentDAO;
    }

    public void setInstallmentDAO(InstallmentDAO installmentDAO) {
        this.installmentDAO = installmentDAO;
    }

    public List<Installment> getAllActiveInstallments() {
        return this.installmentDAO.getAllActiveInstallments();
    }

    public List<Installment> getInstallments(Long installmentCount) {
        return this.installmentDAO.getInstallments(installmentCount);
    }
}
