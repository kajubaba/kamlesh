package com.narendra.sams.admission.service;

import com.narendra.sams.admission.domain.StudentDocument;
import java.util.List;

public interface StudentDocumentService {
    List<StudentDocument> getStudentDocuments(Long l);

    Long saveStudentDocument(StudentDocument studentDocument, Long l);

    void updateDocumentPath(Long l, String str);
}
