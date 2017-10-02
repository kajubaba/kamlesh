package com.narendra.sams.admission.dao;

import com.narendra.sams.admission.domain.StudentDocument;
import java.util.List;

public interface StudentDocumentDAO {
    Long addStudentDocument(StudentDocument studentDocument, Long l);

    List<StudentDocument> getStudentDocuments(Long l);

    void updateDocumentPath(Long l, String str);

    Long updateStudentDocument(StudentDocument studentDocument, Long l);
}
