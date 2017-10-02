package com.narendra.sams.communication.service;

import com.narendra.sams.communication.domain.InstituteSMSProvider;
import java.util.List;

public interface SmsProviderService {
    InstituteSMSProvider getSMSProvider();

    InstituteSMSProvider getSMSProvider(Long l);

    List<InstituteSMSProvider> getSMSProviders(Long l);

    void saveSMSProvider(InstituteSMSProvider instituteSMSProvider, Long l);
}
