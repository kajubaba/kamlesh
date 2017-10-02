package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.Bank;
import java.util.List;

public interface BankService {
    List<Bank> getActiveBanks(Long l);
}
