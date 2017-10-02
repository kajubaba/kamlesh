package com.narendra.sams.admission.dao.impl;

import com.narendra.sams.admission.dao.DocumentDAO;
import com.narendra.sams.admission.domain.Document;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DocumentDAOImpl extends HibernateDaoSupport implements DocumentDAO {
    public List<Document> getAcademicYearDocuments(Collection<Long> academicYearIds) {
        if (academicYearIds == null || academicYearIds.isEmpty()) {
            return null;
        }
        Criteria crt = getSession().createCriteria(Document.class);
        crt.add(Restrictions.in("academicYear.id", academicYearIds));
        return crt.list();
    }

    public List<Document> getAcademicYearDocuments(Long academicYearid, Short admissionTypeId) {
        Criteria crt = getSession().createCriteria(Document.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearid));
        crt.add(Restrictions.eq("admissionType.id", admissionTypeId));
        return crt.list();
    }

    public void deleteDocument(Long documentId) {
        getHibernateTemplate().delete(getHibernateTemplate().load(Document.class, documentId));
    }

    public Document getDocument(Long documentId) {
        return (Document) getHibernateTemplate().get(Document.class, documentId);
    }

    public List<Document> getDocuments(Long academicSessionId) {
        Criteria crt = getSession().createCriteria(Document.class);
        crt.add(Restrictions.eq("academicYear.id", academicSessionId));
        crt.addOrder(Order.asc("docCategory"));
        return crt.list();
    }

    public Long saveDocument(Document document, Long userId) {
        document.setLastModifiedOn(DateUtil.getSystemDateTime());
        document.setLastUpdatedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        return (Long) getHibernateTemplate().save(document);
    }

    public void updateDocument(Document document, Long userId) {
        Document persistDocument = (Document) getHibernateTemplate().load(Document.class, document.getId());
        persistDocument.setDocCategory(document.getDocCategory());
        persistDocument.setMandatory(document.getMandatory());
        persistDocument.setLastModifiedOn(DateUtil.getSystemDateTime());
        persistDocument.setLastUpdatedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        persistDocument.setAdmissionType((AdmissionType) getHibernateTemplate().load(AdmissionType.class, document.getAdmissionType().getId()));
        getHibernateTemplate().saveOrUpdate(persistDocument);
    }

    public Boolean isDocumentNameExists(Long academicSessionId, String documentName) {
        Criteria crt = getSession().createCriteria(Document.class);
        crt.setProjection(Projections.property("id"));
        crt.add(Restrictions.eq("docCategory", documentName).ignoreCase()).add(Restrictions.eq("academicYear.id", academicSessionId));
        List<Object> list = crt.list();
        if (list == null || list.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean isDocumentNameExists(Long academicSessionId, String documentName, Long exceptDocumentId) {
        Criteria crt = getSession().createCriteria(Document.class);
        crt.setProjection(Projections.property("id"));
        crt.add(Restrictions.eq("docCategory", documentName).ignoreCase()).add(Restrictions.eq("academicYear.id", academicSessionId)).add(Restrictions.ne("id", exceptDocumentId));
        List<Object> list = crt.list();
        if (list == null || list.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
