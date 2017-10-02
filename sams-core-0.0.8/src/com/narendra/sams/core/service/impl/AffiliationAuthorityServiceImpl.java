package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.AffiliationAuthorityDAO;
import com.narendra.sams.core.domain.AffiliationAuthority;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.core.service.AffiliationAuthorityService;
import java.util.List;

public class AffiliationAuthorityServiceImpl implements AffiliationAuthorityService {
    private AffiliationAuthorityDAO affiliationAuthorityDAO;

    public AffiliationAuthorityDAO getAffiliationAuthorityDAO() {
        return this.affiliationAuthorityDAO;
    }

    public void setAffiliationAuthorityDAO(AffiliationAuthorityDAO affiliationAuthorityDAO) {
        this.affiliationAuthorityDAO = affiliationAuthorityDAO;
    }

    public List<AffiliationAuthority> getAllAffiliationAuthorities(Long institueId) {
        return this.affiliationAuthorityDAO.getAllAffiliationAuthorities(institueId);
    }

    public List<AffiliationAuthority> getAllActive(Long institueId) {
        return this.affiliationAuthorityDAO.getAllActive(institueId);
    }

    public AffiliationAuthority getAffiliationAuthority(Long id) {
        return this.affiliationAuthorityDAO.getAffiliationAuthority(id);
    }

    public Long saveAffiliationAuthority(AffiliationAuthority affiliationAuthority, Long userId) throws DuplicateNameFoundException {
        if (affiliationAuthority == null) {
            return null;
        }
        Long affiliationAuthorityId = affiliationAuthority.getId();
        if (affiliationAuthority.getId() != null) {
            AffiliationAuthority loadedAA = this.affiliationAuthorityDAO.getAffiliationAuthorityByDisplayName(affiliationAuthority.getInstitute().getId(), affiliationAuthority.getDisplayName());
            if (loadedAA == null || loadedAA.getId().equals(affiliationAuthority.getId())) {
                this.affiliationAuthorityDAO.updateAffiliationAuthority(affiliationAuthority, userId);
                return affiliationAuthorityId;
            }
            throw new DuplicateNameFoundException("Affiliation Authority [" + affiliationAuthority.getDisplayName() + "] already exist");
        } else if (!this.affiliationAuthorityDAO.isAffiliationAuthorityDisplayNameExists(affiliationAuthority.getInstitute().getId(), affiliationAuthority.getDisplayName()).booleanValue()) {
            return this.affiliationAuthorityDAO.addAffiliationAuthority(affiliationAuthority, userId);
        } else {
            throw new DuplicateNameFoundException("Affiliation Authority [" + affiliationAuthority.getDisplayName() + "] already exist");
        }
    }
}
