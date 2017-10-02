package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import java.util.List;

public interface FeeHeadService {
    List<FeeHead> getAllActiveFeeHeads(Long l);

    List<FeeHead> getAllFeeHeads(Long l);

    FeeHead getBusFeeHead(Long l);

    FeeHead getFeeHead(Long l);

    FeeHead getLateFeeHead(Long l);

    Long saveFeeHead(FeeHead feeHead, Long l) throws DuplicateNameFoundException;
}
