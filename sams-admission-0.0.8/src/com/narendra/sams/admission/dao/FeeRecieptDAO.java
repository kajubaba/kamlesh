package com.narendra.sams.admission.dao;

import com.narendra.sams.admission.domain.FeeRecieptHeader;
import java.util.List;

public interface FeeRecieptDAO {
    List<FeeRecieptHeader> getActiveFeeRecieptHeaders(Long l);

    FeeRecieptHeader getDefaultHeader(Long l);

    FeeRecieptHeader getFeeRecieptHeader(Long l);
}
