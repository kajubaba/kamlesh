package com.narendra.sams.communication.dao;

import com.narendra.sams.communication.domain.InstituteSMSProvider;
import java.util.List;

public interface SmsProviderDAO {
    void addSMSProvider(InstituteSMSProvider instituteSMSProvider, Long l);

    InstituteSMSProvider getSMSProvider();

    InstituteSMSProvider getSMSProvider(Long l);

    List<InstituteSMSProvider> getSMSProviders(Long l);

    void updateSMSProvider(InstituteSMSProvider instituteSMSProvider, Long l);
}
