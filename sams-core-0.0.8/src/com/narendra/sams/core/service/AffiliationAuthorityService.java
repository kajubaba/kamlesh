package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.AffiliationAuthority;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import java.util.List;

public interface AffiliationAuthorityService {
    AffiliationAuthority getAffiliationAuthority(Long l);

    List<AffiliationAuthority> getAllActive(Long l);

    List<AffiliationAuthority> getAllAffiliationAuthorities(Long l);

    Long saveAffiliationAuthority(AffiliationAuthority affiliationAuthority, Long l) throws DuplicateNameFoundException;
}
