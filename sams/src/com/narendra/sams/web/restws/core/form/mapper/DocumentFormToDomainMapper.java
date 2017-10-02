package com.narendra.sams.web.restws.core.form.mapper;

import com.narendra.sams.admission.domain.Document;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.web.restws.core.form.DocumentForm;

public class DocumentFormToDomainMapper {
    public static Document mapToDomain(DocumentForm documentForm) {
        if (documentForm == null) {
            return null;
        }
        Document document = new Document();
        document.setId(documentForm.getId());
        document.setDocCategory(documentForm.getName());
        document.setMandatory(documentForm.getMandatory());
        AdmissionType admissionType = new AdmissionType();
        admissionType.setId(documentForm.getAdmissionTypeId());
        document.setAdmissionType(admissionType);
        AcademicYear academicYear = new AcademicYear();
        academicYear.setId(documentForm.getAcademicSessionId());
        document.setAcademicYear(academicYear);
        return document;
    }
}
