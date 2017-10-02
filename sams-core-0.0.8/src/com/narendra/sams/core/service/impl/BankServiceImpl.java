package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.BankDAO;
import com.narendra.sams.core.domain.Bank;
import com.narendra.sams.core.service.BankService;
import java.util.List;

public class BankServiceImpl implements BankService {
    private BankDAO bankDAO;

    public BankDAO getBankDAO() {
        return this.bankDAO;
    }

    public void setBankDAO(BankDAO bankDAO) {
        this.bankDAO = bankDAO;
    }

    public List<Bank> getActiveBanks(Long instituteId) {
        return this.bankDAO.getActiveBanks(instituteId);
    }
}
