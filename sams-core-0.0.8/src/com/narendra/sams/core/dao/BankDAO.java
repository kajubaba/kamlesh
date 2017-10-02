package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.Bank;
import java.util.List;

public interface BankDAO {
    List<Bank> getActiveBanks(Long l);
}
