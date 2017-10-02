package com.narendra.sams.web.restws.mapper.vo;

import com.narendra.sams.core.domain.AffiliationAuthority;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.admission.vo.AffiliationAuthorityVO;
import java.util.ArrayList;
import java.util.List;

public class AffiliationAuthorityVOMapper {
    public static List<AffiliationAuthorityVO> prepareAffiliationAuthorityVOs(List<AffiliationAuthority> affiliationAuthorities) {
        List<AffiliationAuthorityVO> affiliationAuthorityVOs = new ArrayList();
        if (!(affiliationAuthorities == null || affiliationAuthorities.isEmpty())) {
            for (AffiliationAuthority affiliationAuthority : affiliationAuthorities) {
                affiliationAuthorityVOs.add(prepareAffiliationAuthority(affiliationAuthority));
            }
        }
        return affiliationAuthorityVOs;
    }

    public static AffiliationAuthorityVO prepareAffiliationAuthority(AffiliationAuthority affiliationAuthority) {
        if (affiliationAuthority == null) {
            return null;
        }
        AffiliationAuthorityVO affiliationAuthorityVO = new AffiliationAuthorityVO();
        affiliationAuthorityVO.setId(affiliationAuthority.getId());
        affiliationAuthorityVO.setName(affiliationAuthority.getName());
        affiliationAuthorityVO.setActive(affiliationAuthority.getActive());
        affiliationAuthorityVO.setDisplayName(affiliationAuthority.getDisplayName());
        if (affiliationAuthority.getCreatedBy() != null) {
            affiliationAuthorityVO.setCreatedBy(affiliationAuthority.getCreatedBy().getFullName());
        }
        if (affiliationAuthority.getCreatedDate() != null) {
            affiliationAuthorityVO.setCreatedOn(DateUtil.formatDate(affiliationAuthority.getCreatedDate(), "dd-MMM-yyyy hh:mm a"));
        }
        if (affiliationAuthority.getModifiedBy() != null) {
            affiliationAuthorityVO.setModifiedBy(affiliationAuthority.getModifiedBy().getFullName());
        }
        if (affiliationAuthority.getModifiedDate() == null) {
            return affiliationAuthorityVO;
        }
        affiliationAuthorityVO.setModifiedOn(DateUtil.formatDate(affiliationAuthority.getModifiedDate(), "dd-MMM-yyyy hh:mm a"));
        return affiliationAuthorityVO;
    }
}
