package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import java.util.List;

public interface FeeHeadDAO {
    Long addFeeHead(FeeHead feeHead, Long l) throws DuplicateNameFoundException;

    List<FeeHead> getAllActiveFeeHeads(Long l);

    List<FeeHead> getAllFeeHeads(Long l);

    FeeHead getBusFeeHead(Long l);

    FeeHead getFeeHead(Long l);

    FeeHead getLateFeeHead(Long l);

    Boolean isFeeHeadNameExist(Long l, String str);

    FeeHead loadById(Long l);

    FeeHead loadByName(Long l, String str);

    void updateFeeHead(FeeHead feeHead, Long l) throws DuplicateNameFoundException;
}
