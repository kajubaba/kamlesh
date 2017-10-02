package com.narendra.sams.web.restws.mapper.form;

import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.web.restws.form.FeeHeadForm;

public class FeeHeadFormMapper {
    public static FeeHead prepareFeeHeadDomain(FeeHeadForm feeHeadForm) {
        if (feeHeadForm == null) {
            return null;
        }
        FeeHead feeHead = new FeeHead();
        feeHead.setId(feeHeadForm.getId());
        feeHead.setName(feeHeadForm.getName());
        feeHead.setActive(feeHeadForm.getActive());
        return feeHead;
    }
}
