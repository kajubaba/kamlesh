package com.narendra.sams.communication.service.impl;

import com.narendra.sams.communication.dao.SmsProviderDAO;
import com.narendra.sams.communication.domain.InstituteSMSProvider;
import com.narendra.sams.communication.service.SmsProviderService;
import java.util.List;

public class SmsProviderServiceImpl implements SmsProviderService {
    private SmsProviderDAO smsProviderDAO;

    public SmsProviderDAO getSmsProviderDAO() {
        return this.smsProviderDAO;
    }

    public void setSmsProviderDAO(SmsProviderDAO smsProviderDAO) {
        this.smsProviderDAO = smsProviderDAO;
    }

    public void saveSMSProvider(InstituteSMSProvider instituteSMSProvider, Long userId) {
        if (instituteSMSProvider != null) {
            if (instituteSMSProvider.getId() == null) {
                this.smsProviderDAO.addSMSProvider(instituteSMSProvider, userId);
            } else {
                this.smsProviderDAO.updateSMSProvider(instituteSMSProvider, userId);
            }
        }
    }

    public InstituteSMSProvider getSMSProvider(Long id) {
        if (id == null) {
            return null;
        }
        return this.smsProviderDAO.getSMSProvider(id);
    }

    public List<InstituteSMSProvider> getSMSProviders(Long instituteId) {
        if (instituteId == null) {
            return null;
        }
        return this.smsProviderDAO.getSMSProviders(instituteId);
    }

    public InstituteSMSProvider getSMSProvider() {
        return this.smsProviderDAO.getSMSProvider();
    }
}
