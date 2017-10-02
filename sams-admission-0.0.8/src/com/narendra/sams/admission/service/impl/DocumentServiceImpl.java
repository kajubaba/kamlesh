package com.narendra.sams.admission.service.impl;

import com.narendra.sams.admission.dao.DocumentDAO;
import com.narendra.sams.admission.domain.Document;
import com.narendra.sams.admission.service.DocumentService;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import java.util.List;

public class DocumentServiceImpl implements DocumentService {
    private DocumentDAO documentDAO;

    public DocumentDAO getDocumentDAO() {
        return this.documentDAO;
    }

    public void setDocumentDAO(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    public void deleteDocument(Long documentId) {
        if (documentId != null) {
            this.documentDAO.deleteDocument(documentId);
        }
    }

    public Document getDocument(Long documentId) {
        if (documentId == null) {
            return null;
        }
        return this.documentDAO.getDocument(documentId);
    }

    public List<Document> getDocuments(Long academicSessionId) {
        if (academicSessionId == null) {
            return null;
        }
        return this.documentDAO.getDocuments(academicSessionId);
    }

    public Long saveDocument(Document document, Long userId) throws DuplicateNameFoundException {
        if (document == null || userId == null) {
            return null;
        }
        if (!this.documentDAO.isDocumentNameExists(document.getAcademicYear().getId(), document.getDocCategory()).booleanValue()) {
            return this.documentDAO.saveDocument(document, userId);
        }
        throw new DuplicateNameFoundException("Document Name ['" + document.getDocCategory() + "'] already exists");
    }

    public void updateDocument(Document document, Long userId) throws DuplicateNameFoundException {
        if (document != null && userId != null && document.getId() != null) {
            if (this.documentDAO.isDocumentNameExists(document.getAcademicYear().getId(), document.getDocCategory(), document.getId()).booleanValue()) {
                throw new DuplicateNameFoundException("Document Name ['" + document.getDocCategory() + "'] already exists");
            }
            this.documentDAO.updateDocument(document, userId);
        }
    }
}
