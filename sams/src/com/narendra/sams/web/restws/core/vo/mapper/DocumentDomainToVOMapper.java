package com.narendra.sams.web.restws.core.vo.mapper;

import com.narendra.sams.admission.domain.Document;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.core.form.DocumentForm;
import com.narendra.sams.web.restws.core.vo.DocumentVO;
import java.util.ArrayList;
import java.util.List;

public class DocumentDomainToVOMapper {
    public static DocumentVO mapToVO(Document document) {
        if (document == null) {
            return null;
        }
        DocumentVO documentVO = new DocumentVO();
        documentVO.setId(document.getId());
        documentVO.setName(document.getDocCategory());
        documentVO.setForAdmissionTYpe(document.getAdmissionType().getName());
        documentVO.setLastModifiedBy(document.getLastUpdatedBy().getFullName());
        documentVO.setLastModifiedOn(DateUtil.formatDate(document.getLastModifiedOn(), "dd-MMM-yyyy"));
        documentVO.setMandatory(document.getMandatory());
        return documentVO;
    }

    public static DocumentForm mapToForm(Document document) {
        if (document == null) {
            return null;
        }
        DocumentForm documentForm = new DocumentForm();
        documentForm.setId(document.getId());
        documentForm.setName(document.getDocCategory());
        documentForm.setAdmissionTypeId(document.getAdmissionType().getId());
        documentForm.setMandatory(document.getMandatory());
        return documentForm;
    }

    public static List<DocumentVO> mapToVOs(List<Document> documents) {
        if (documents == null) {
            return null;
        }
        List<DocumentVO> documentVOs = new ArrayList();
        for (Document document : documents) {
            documentVOs.add(mapToVO(document));
        }
        return documentVOs;
    }
}
