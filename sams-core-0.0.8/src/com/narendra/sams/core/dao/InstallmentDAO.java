package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.Installment;
import java.util.List;

public interface InstallmentDAO {
    List<Installment> getAllActiveInstallments();

    List<Installment> getInstallments(Long l);
}
