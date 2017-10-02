package com.narendra.sams.admission.dao.impl;

import com.narendra.sams.admission.dao.StudentDocumentDAO;
import com.narendra.sams.admission.domain.Document;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentDocument;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StudentDocumentDAOImpl extends HibernateDaoSupport implements StudentDocumentDAO {
    public List<StudentDocument> getStudentDocuments(Long studentId) {
        if (studentId == null) {
            return null;
        }
        Criteria crt = getSession().createCriteria(StudentDocument.class);
        crt.add(Restrictions.eq("student.id", studentId));
        return crt.list();
    }

    public Long addStudentDocument(StudentDocument studentDocument, Long userId) {
        if (studentDocument.getDocument() != null) {
            Document document = (Document) getHibernateTemplate().get(Document.class, studentDocument.getDocument().getId());
            studentDocument.setDocument(document);
            studentDocument.setDocAcademicYear(document.getAcademicYear());
        }
        studentDocument.setUploadedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        studentDocument.setUploadedOn(DateUtil.getSystemDateTime());
        studentDocument.setStudent((Student) getHibernateTemplate().load(Student.class, studentDocument.getStudent().getId()));
        return (Long) getHibernateTemplate().save(studentDocument);
    }

    public Long updateStudentDocument(StudentDocument studentDocument, Long userId) {
        StudentDocument savedDocument = (StudentDocument) getHibernateTemplate().load(StudentDocument.class, studentDocument.getId());
        savedDocument.setUploadedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        savedDocument.setUploadedOn(DateUtil.getSystemDateTime());
        savedDocument.setComments(studentDocument.getComments());
        savedDocument.setDocName(studentDocument.getDocName());
        getHibernateTemplate().update(savedDocument);
        return savedDocument.getId();
    }

    public void updateDocumentPath(Long documentId, String newPath) {
        StudentDocument savedDocument = (StudentDocument) getHibernateTemplate().load(StudentDocument.class, documentId);
        savedDocument.setDocPath(newPath);
        getHibernateTemplate().update(savedDocument);
    }
}
