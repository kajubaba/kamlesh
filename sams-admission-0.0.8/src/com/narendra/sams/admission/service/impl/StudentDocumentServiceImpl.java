package com.narendra.sams.admission.service.impl;

import com.narendra.sams.admission.dao.DocumentDAO;
import com.narendra.sams.admission.dao.StudentDAO;
import com.narendra.sams.admission.dao.StudentDocumentDAO;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.Document;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentDocument;
import com.narendra.sams.admission.service.StudentDocumentService;
import java.util.ArrayList;
import java.util.List;

public class StudentDocumentServiceImpl implements StudentDocumentService {
    private DocumentDAO documentDAO;
    private StudentDAO studentDAO;
    private StudentDocumentDAO studentDocumentDAO;

    public StudentDocumentDAO getStudentDocumentDAO() {
        return this.studentDocumentDAO;
    }

    public void setStudentDocumentDAO(StudentDocumentDAO studentDocumentDAO) {
        this.studentDocumentDAO = studentDocumentDAO;
    }

    public DocumentDAO getDocumentDAO() {
        return this.documentDAO;
    }

    public void setDocumentDAO(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    public StudentDAO getStudentDAO() {
        return this.studentDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public List<StudentDocument> getStudentDocuments(Long studentId) {
        Student student = this.studentDAO.getStudentById(studentId);
        if (student == null) {
            return null;
        }
        List<Document> documentsToBeUploaded = new ArrayList();
        for (ClassHistory studentClassHistory : student.getClassHistories()) {
            if (studentClassHistory.getActiveClass().booleanValue()) {
                List<Document> docTemplates = this.documentDAO.getAcademicYearDocuments(studentClassHistory.getAcademicYear().getId(), studentClassHistory.getAdmissionType().getId());
                if (docTemplates != null) {
                    documentsToBeUploaded.addAll(docTemplates);
                }
            }
        }
        List<StudentDocument> studentDocuments = new ArrayList();
        List<StudentDocument> studentUploadedDocuments = this.studentDocumentDAO.getStudentDocuments(studentId);
        StudentDocument studentDocument;
        if (studentUploadedDocuments != null) {
            studentDocuments.addAll(studentUploadedDocuments);
            if (documentsToBeUploaded == null || documentsToBeUploaded.isEmpty()) {
                return studentDocuments;
            }
            for (Document documentToBeUploaded : documentsToBeUploaded) {
                Boolean flag = Boolean.FALSE;
                for (StudentDocument uploadedDocument : studentUploadedDocuments) {
                    if (uploadedDocument.getDocument() != null && uploadedDocument.getDocument().getId().equals(documentToBeUploaded.getId())) {
                        flag = Boolean.TRUE;
                        break;
                    }
                }
                if (!flag.booleanValue()) {
                    studentDocument = new StudentDocument();
                    studentDocument.setStudent(student);
                    studentDocument.setDocAcademicYear(documentToBeUploaded.getAcademicYear());
                    studentDocument.setDocument(documentToBeUploaded);
                    studentDocuments.add(studentDocument);
                }
            }
            return studentDocuments;
        } else if (documentsToBeUploaded == null || documentsToBeUploaded.isEmpty()) {
            return studentDocuments;
        } else {
            for (Document documentToBeUploaded2 : documentsToBeUploaded) {
                studentDocument = new StudentDocument();
                studentDocument.setStudent(student);
                studentDocument.setDocAcademicYear(documentToBeUploaded2.getAcademicYear());
                studentDocument.setDocument(documentToBeUploaded2);
                studentDocuments.add(studentDocument);
            }
            return studentDocuments;
        }
    }

    public Long saveStudentDocument(StudentDocument studentDocument, Long userId) {
        if (studentDocument.getId() == null) {
            return this.studentDocumentDAO.addStudentDocument(studentDocument, userId);
        }
        return this.studentDocumentDAO.updateStudentDocument(studentDocument, userId);
    }

    public void updateDocumentPath(Long documentId, String newPath) {
        this.studentDocumentDAO.updateDocumentPath(documentId, newPath);
    }
}
