package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.AffiliationAuthority;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import java.util.List;

public interface AffiliationAuthorityDAO {
    Long addAffiliationAuthority(AffiliationAuthority affiliationAuthority, Long l) throws DuplicateNameFoundException;

    AffiliationAuthority getAffiliationAuthority(Long l);

    AffiliationAuthority getAffiliationAuthorityByDisplayName(Long l, String str);

    List<AffiliationAuthority> getAllActive(Long l);

    List<AffiliationAuthority> getAllAffiliationAuthorities(Long l);

    Boolean isAffiliationAuthorityDisplayNameExists(Long l, String str);

    AffiliationAuthority loadAffiliationAuthority(Long l);

    void updateAffiliationAuthority(AffiliationAuthority affiliationAuthority, Long l) throws DuplicateNameFoundException;
}
