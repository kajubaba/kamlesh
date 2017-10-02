package com.narendra.sams.admission.service;

import com.narendra.sams.admission.domain.Document;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import java.util.List;

public interface DocumentService {
    void deleteDocument(Long l);

    Document getDocument(Long l);

    List<Document> getDocuments(Long l);

    Long saveDocument(Document document, Long l) throws DuplicateNameFoundException;

    void updateDocument(Document document, Long l) throws DuplicateNameFoundException;
}
