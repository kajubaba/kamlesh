package com.narendra.sams.web.restws.mapper.vo;

import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.vo.FeeHeadVO;
import java.util.ArrayList;
import java.util.List;

public class FeeHeadVOMapper {
    public static List<FeeHeadVO> prepareFeeHeadVOs(List<FeeHead> feeHeads) {
        List<FeeHeadVO> feeHeadVOs = new ArrayList();
        if (!(feeHeads == null || feeHeads.isEmpty())) {
            for (FeeHead feeHead : feeHeads) {
                feeHeadVOs.add(prepareFeeHeadVO(feeHead));
            }
        }
        return feeHeadVOs;
    }

    public static FeeHeadVO prepareFeeHeadVO(FeeHead feeHead) {
        if (feeHead == null) {
            return null;
        }
        FeeHeadVO feeHeadVO = new FeeHeadVO();
        feeHeadVO.setId(feeHead.getId());
        feeHeadVO.setName(feeHead.getName());
        feeHeadVO.setActive(feeHead.getActive());
        if (feeHead.getCreatedBy() != null) {
            feeHeadVO.setCreatedBy(feeHead.getCreatedBy().getFullName());
        }
        if (feeHead.getCreatedDate() != null) {
            feeHeadVO.setCreatedOn(DateUtil.formatDate(feeHead.getCreatedDate(), "dd-MMM-yyyy hh:mm a"));
        }
        if (feeHead.getModifiedBy() != null) {
            feeHeadVO.setModifiedBy(feeHead.getModifiedBy().getFullName());
        }
        if (feeHead.getModifiedDate() == null) {
            return feeHeadVO;
        }
        feeHeadVO.setModifiedOn(DateUtil.formatDate(feeHead.getModifiedDate(), "dd-MMM-yyyy hh:mm a"));
        return feeHeadVO;
    }
}
