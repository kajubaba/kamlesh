package com.narendra.sams.web.restws.mapper.form;

import com.narendra.sams.core.domain.AffiliationAuthority;
import com.narendra.sams.web.restws.form.AffiliationAuthorityForm;

public class AffiliationAuthorityFormMapper {
    public static AffiliationAuthority prepareAffiliationAuthorityDomain(AffiliationAuthorityForm affiliationAuthorityForm) {
        if (affiliationAuthorityForm == null) {
            return null;
        }
        AffiliationAuthority affiliationAuthority = new AffiliationAuthority();
        affiliationAuthority.setId(affiliationAuthorityForm.getId());
        affiliationAuthority.setName(affiliationAuthorityForm.getName());
        affiliationAuthority.setDisplayName(affiliationAuthorityForm.getDisplayName());
        affiliationAuthority.setActive(affiliationAuthorityForm.getActive());
        return affiliationAuthority;
    }
}
