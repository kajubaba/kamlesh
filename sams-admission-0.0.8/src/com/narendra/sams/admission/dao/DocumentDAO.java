package com.narendra.sams.admission.dao;

import com.narendra.sams.admission.domain.Document;
import java.util.Collection;
import java.util.List;

public interface DocumentDAO {
    void deleteDocument(Long l);

    List<Document> getAcademicYearDocuments(Long l, Short sh);

    List<Document> getAcademicYearDocuments(Collection<Long> collection);

    Document getDocument(Long l);

    List<Document> getDocuments(Long l);

    Boolean isDocumentNameExists(Long l, String str);

    Boolean isDocumentNameExists(Long l, String str, Long l2);

    Long saveDocument(Document document, Long l);

    void updateDocument(Document document, Long l);
}
