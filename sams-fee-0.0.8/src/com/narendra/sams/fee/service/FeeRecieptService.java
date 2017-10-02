package com.narendra.sams.fee.service;

import com.narendra.sams.admission.domain.FeeRecieptHeader;
import java.util.List;

public interface FeeRecieptService {
    List<FeeRecieptHeader> getActiveFeeRecieptHeaders(Long l);

    FeeRecieptHeader getDefaultHeader(Long l);

    FeeRecieptHeader getFeeRecieptHeader(Long l);
}
