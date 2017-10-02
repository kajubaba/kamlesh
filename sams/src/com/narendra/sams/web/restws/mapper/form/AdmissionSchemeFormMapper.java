package com.narendra.sams.web.restws.mapper.form;

import com.narendra.sams.core.domain.AdmissionScheme;
import com.narendra.sams.web.restws.form.AdmissionSchemeForm;

public class AdmissionSchemeFormMapper {
    public static AdmissionScheme prepareAdmissionSchemeDomain(AdmissionSchemeForm admissionSchemeForm) {
        if (admissionSchemeForm == null) {
            return null;
        }
        AdmissionScheme admissionScheme = new AdmissionScheme();
        admissionScheme.setId(admissionSchemeForm.getSchemeId());
        admissionScheme.setName(admissionSchemeForm.getSchemeName());
        admissionScheme.setActive(admissionSchemeForm.getActive());
        return admissionScheme;
    }
}
