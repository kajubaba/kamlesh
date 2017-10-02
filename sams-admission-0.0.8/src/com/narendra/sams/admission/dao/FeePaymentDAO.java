package com.narendra.sams.admission.dao;

import com.narendra.sams.admission.domain.FeeTransaction;

public interface FeePaymentDAO {
    Long payFee(FeeTransaction feeTransaction, Long l);
}
