package com.narendra.sams.fee.service;

import com.narendra.sams.admission.domain.FeeTransaction;
import com.narendra.sams.fee.domain.PayFeeReturn;

public interface FeePaymentService {
    PayFeeReturn payFee(FeeTransaction feeTransaction, Long l);
}
