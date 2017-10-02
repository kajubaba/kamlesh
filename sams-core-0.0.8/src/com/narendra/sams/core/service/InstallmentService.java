package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.Installment;
import java.util.List;

public interface InstallmentService {
    List<Installment> getAllActiveInstallments();

    List<Installment> getInstallments(Long l);
}
