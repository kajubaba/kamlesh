package com.narendra.sams.enquiry.dao.impl;

import com.narendra.sams.core.address.dao.AddressDAO;
import com.narendra.sams.core.address.dao.UserViewDAO;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.StudentCategory;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.dao.EnquiryDAO;
import com.narendra.sams.enquiry.domain.AdvanceEnquirySearchParam;
import com.narendra.sams.enquiry.domain.ClasswiseEnquiryCount;
import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.enquiry.domain.EnquiryBriefInfo;
import com.narendra.sams.enquiry.domain.EnquiryCountData;
import com.narendra.sams.enquiry.domain.EnquiryStatus;
import com.narendra.sams.enquiry.domain.StatusWiseEnquiryCount;
import com.narendra.sams.enquiry.domain.StudentPrevClass;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class EnquiryDAOImpl extends HibernateDaoSupport implements EnquiryDAO {
    private AddressDAO addressDAO;
    private UserViewDAO userViewDAO;

    public List<StatusWiseEnquiryCount> getStatusWiseEnquiryCount(Long academicYearId) {
        List<StatusWiseEnquiryCount> enquiryCountList = null;
        Criteria crt = getSession().createCriteria(Enquiry.class);
        crt.createAlias("enquiryStatus", "enquiryStatus");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.setProjection(Projections.projectionList().add(Projections.groupProperty("enquiryStatus.id")).add(Projections.property("enquiryStatus.name")).add(Projections.count("enquiryStatus.id"), "enquryCount")).addOrder(Order.asc("enquryCount"));
        Iterator it = crt.list().iterator();
        if (it.hasNext()) {
            enquiryCountList = new ArrayList();
            while (it.hasNext()) {
                Object[] col = (Object[]) it.next();
                StatusWiseEnquiryCount statusWiseEnquiryCount = new StatusWiseEnquiryCount();
                statusWiseEnquiryCount.setAcademicSessionId(academicYearId);
                statusWiseEnquiryCount.setStatusId((Long) col[0]);
                statusWiseEnquiryCount.setStatusName((String) col[1]);
                statusWiseEnquiryCount.setEnquiryCount((Long) col[2]);
                enquiryCountList.add(statusWiseEnquiryCount);
            }
        }
        return enquiryCountList;
    }

    public List<Enquiry> getEnquiriesByStatus(Long academicYearId, Long statusId) {
        Criteria crt = getSession().createCriteria(Enquiry.class);
        crt.add(Restrictions.eq("enquiryStatus.id", statusId));
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.addOrder(Order.desc("createdDate"));
        return crt.list();
    }

    public List<Enquiry> getEnquiriesByClass(Long classId) {
        Criteria crt = getSession().createCriteria(Enquiry.class);
        crt.add(Restrictions.eq("academicYearClass.id", classId));
        crt.addOrder(Order.desc("createdDate"));
        return crt.list();
    }

    public Long getCountByStatusName(Long academicYearId, String statusName) {
        Criteria crt = getSession().createCriteria(Enquiry.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        if (statusName != null) {
            crt.createAlias("enquiryStatus", "enquiryStatus");
            crt.add(Restrictions.eq("enquiryStatus.name", statusName));
        }
        crt.setProjection(Projections.projectionList().add(Projections.countDistinct("id"), "count"));
        return (Long) crt.uniqueResult();
    }

    public List<Enquiry> getRecentEnquiries(Long academicSessionId) {
        Criteria crt = getSession().createCriteria(Enquiry.class);
        crt.add(Restrictions.eq("academicYear.id", academicSessionId));
        crt.addOrder(Order.desc("createdDate"));
        crt.setFirstResult(0);
        crt.setMaxResults(9);
        return crt.list();
    }

    public List<Enquiry> getEnquiries(Long academicSessionId, String statusId) {
        Criteria crt = getSession().createCriteria(Enquiry.class);
        crt.addOrder(Order.desc("createdDate"));
        crt.add(Restrictions.eq("academicYear.id", academicSessionId));
        if (statusId != null) {
            crt.add(Restrictions.eq("enquiryStatus.id", statusId));
        }
        return crt.list();
    }

    public UserViewDAO getUserViewDAO() {
        return this.userViewDAO;
    }

    public void setUserViewDAO(UserViewDAO userViewDAO) {
        this.userViewDAO = userViewDAO;
    }

    public AddressDAO getAddressDAO() {
        return this.addressDAO;
    }

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public Long addEnquiry(Enquiry enquiry, Long userId) {
        UserView user = this.userViewDAO.loadUser(userId);
        enquiry.setCreatedBy(user);
        enquiry.setModifiedBy(user);
        enquiry.setOwner(user);
        enquiry.setAssignee(user);
        enquiry.setCreatedDate(DateUtil.getSystemDateTime());
        enquiry.setModifiedDate(DateUtil.getSystemDateTime());
        if (enquiry.getAcademicYearClass() == null || enquiry.getAcademicYearClass().getId() == null) {
            enquiry.setAcademicYearClass(null);
        } else {
            enquiry.setAcademicYearClass((AcademicYearClass) getHibernateTemplate().load(AcademicYearClass.class, enquiry.getAcademicYearClass().getId()));
        }
        if (enquiry.getAcademicYear() == null || enquiry.getAcademicYear().getId() == null) {
            enquiry.setAcademicYear(null);
        } else {
            enquiry.setAcademicYear((AcademicYear) getHibernateTemplate().load(AcademicYear.class, enquiry.getAcademicYear().getId()));
        }
        if (enquiry.getStudentCategory() == null || enquiry.getId() == null) {
            enquiry.setStudentCategory(null);
        } else {
            enquiry.setStudentCategory((StudentCategory) getHibernateTemplate().load(StudentCategory.class, enquiry.getStudentCategory().getId()));
        }
        if (enquiry.getState() == null || enquiry.getState().getId() == null) {
            enquiry.setState(null);
        } else {
            enquiry.setState(this.addressDAO.loadState(enquiry.getState().getId()));
        }
        if (enquiry.getCountry() == null || enquiry.getCountry().getId() == null) {
            enquiry.setCountry(null);
        } else {
            enquiry.setCountry(this.addressDAO.loadCountry(enquiry.getCountry().getId()));
        }
        if (enquiry.getEnquiryStatus() == null || enquiry.getEnquiryStatus().getId() == null) {
            enquiry.setEnquiryStatus(null);
        } else {
            enquiry.setEnquiryStatus((EnquiryStatus) getHibernateTemplate().load(EnquiryStatus.class, enquiry.getEnquiryStatus().getId()));
        }
        return (Long) getHibernateTemplate().save(enquiry);
    }

    public Long addNewEnquiry(Enquiry enquiry, Long userid) {
        UserView user = this.userViewDAO.loadUser(userid);
        enquiry.setCreatedBy(user);
        enquiry.setModifiedBy(user);
        enquiry.setOwner(user);
        enquiry.setAssignee(user);
        enquiry.setCreatedDate(DateUtil.getSystemDateTime());
        enquiry.setModifiedDate(DateUtil.getSystemDateTime());
        if (enquiry.getAcademicYearClass() == null || enquiry.getAcademicYearClass().getId() == null) {
            enquiry.setAcademicYearClass(null);
        } else {
            enquiry.setAcademicYearClass((AcademicYearClass) getHibernateTemplate().load(AcademicYearClass.class, enquiry.getAcademicYearClass().getId()));
        }
        if (enquiry.getAcademicYear() == null || enquiry.getAcademicYear().getId() == null) {
            enquiry.setAcademicYear(null);
        } else {
            enquiry.setAcademicYear((AcademicYear) getHibernateTemplate().load(AcademicYear.class, enquiry.getAcademicYear().getId()));
        }
        if (enquiry.getStudentCategory() == null || enquiry.getId() == null) {
            enquiry.setStudentCategory(null);
        } else {
            enquiry.setStudentCategory((StudentCategory) getHibernateTemplate().load(StudentCategory.class, enquiry.getStudentCategory().getId()));
        }
        if (enquiry.getState() == null || enquiry.getState().getId() == null) {
            enquiry.setState(null);
        } else {
            enquiry.setState(this.addressDAO.loadState(enquiry.getState().getId()));
        }
        if (enquiry.getCountry() == null || enquiry.getCountry().getId() == null) {
            enquiry.setCountry(null);
        } else {
            enquiry.setCountry(this.addressDAO.loadCountry(enquiry.getCountry().getId()));
        }
        if (enquiry.getEnquiryStatus() == null || enquiry.getEnquiryStatus().getId() == null) {
            enquiry.setEnquiryStatus(null);
        } else {
            enquiry.setEnquiryStatus((EnquiryStatus) getHibernateTemplate().load(EnquiryStatus.class, enquiry.getEnquiryStatus().getId()));
        }
        return (Long) getHibernateTemplate().save(enquiry);
    }

    public List<Enquiry> getAllEnquiries(Long instituteId, Long academicYearId, Long responsibleUserId) {
        Criteria enqCriteria = getSession().createCriteria(Enquiry.class);
        enqCriteria.add(Restrictions.eq("academicYear.id", academicYearId));
        enqCriteria.add(Restrictions.eq("institute.id", instituteId));
        if (responsibleUserId != null) {
            enqCriteria.add(Restrictions.disjunction().add(Restrictions.eq("owner.id", responsibleUserId)).add(Restrictions.eq("assignee.id", responsibleUserId)));
        }
        enqCriteria.addOrder(Order.asc("studentFirstName")).addOrder(Order.asc("studentMiddleName")).addOrder(Order.asc("studentLastName"));
        return enqCriteria.list();
    }

    public List<Enquiry> getAllEnquiries(Long instituteId, Long academicYearId, Long responsibleUserId, String searchStr, String searchWise) {
        Criteria enqCriteria = getSession().createCriteria(Enquiry.class);
        if ("city".equals(searchWise)) {
            enqCriteria.add(Restrictions.ilike("address.city", searchStr.trim(), MatchMode.START));
        } else if ("teh".equals(searchWise)) {
            enqCriteria.add(Restrictions.ilike("address.teh", searchStr.trim(), MatchMode.START));
        } else if ("dist".equals(searchWise)) {
            enqCriteria.add(Restrictions.ilike("address.district", searchStr.trim(), MatchMode.START));
        } else if ("registered".equals(searchWise)) {
            enqCriteria.add(Restrictions.ne("registrationFee", Long.valueOf(0)));
        } else if ("non-registered".equals(searchWise)) {
            enqCriteria.add(Restrictions.eq("registrationFee", Long.valueOf(0)));
        } else if ("name".equals(searchWise)) {
            enqCriteria.add(Restrictions.ilike("studentFirstName", searchStr.trim(), MatchMode.START));
        }
        if (responsibleUserId != null) {
            enqCriteria.add(Restrictions.disjunction().add(Restrictions.eq("owner.id", responsibleUserId)).add(Restrictions.eq("assignee.id", responsibleUserId)));
        }
        enqCriteria.add(Restrictions.eq("academicYear.id", academicYearId));
        enqCriteria.add(Restrictions.eq("institute.id", instituteId));
        enqCriteria.addOrder(Order.asc("studentFirstName")).addOrder(Order.asc("studentMiddleName")).addOrder(Order.asc("studentLastName"));
        return enqCriteria.list();
    }

    public Enquiry getEnquiry(Long enquiryId) {
        return (Enquiry) getHibernateTemplate().get(Enquiry.class, enquiryId);
    }

    public void updateEnquiry(Enquiry enquiry, Long userid) {
        Enquiry loadedEnquiry = (Enquiry) getHibernateTemplate().load(Enquiry.class, enquiry.getId());
        if (enquiry.getAcademicYearClass() == null || enquiry.getAcademicYearClass().getId() == null) {
            loadedEnquiry.setAcademicYearClass(null);
        } else {
            loadedEnquiry.setAcademicYearClass((AcademicYearClass) getHibernateTemplate().load(AcademicYearClass.class, enquiry.getAcademicYearClass().getId()));
        }
        loadedEnquiry.setStudentFirstName(enquiry.getStudentFirstName());
        loadedEnquiry.setStudentMiddleName(enquiry.getStudentMiddleName());
        loadedEnquiry.setStudentLastName(enquiry.getStudentLastName());
        loadedEnquiry.setStudentContactNo(enquiry.getStudentContactNo());
        loadedEnquiry.setStudentGender(enquiry.getStudentGender());
        loadedEnquiry.setStudentDob(enquiry.getStudentDob());
        loadedEnquiry.getAddress().setLine1(enquiry.getAddress().getLine1());
        loadedEnquiry.getAddress().setLine2(enquiry.getAddress().getLine2());
        loadedEnquiry.getAddress().setCity(enquiry.getAddress().getCity());
        loadedEnquiry.getAddress().setZipCode(enquiry.getAddress().getZipCode());
        if (enquiry.getState() == null || enquiry.getState().getId() == null) {
            loadedEnquiry.setState(null);
        } else {
            loadedEnquiry.setState(this.addressDAO.loadState(enquiry.getState().getId()));
        }
        loadedEnquiry.setFatherContactNo(enquiry.getFatherContactNo());
        loadedEnquiry.setStudentFatherName(enquiry.getStudentFatherName());
        loadedEnquiry.setFatherOccupation(enquiry.getFatherOccupation());
        loadedEnquiry.setMotherContactNo(enquiry.getMotherContactNo());
        loadedEnquiry.setMotherName(enquiry.getMotherName());
        loadedEnquiry.setMotherOccupation(enquiry.getMotherOccupation());
        loadedEnquiry.setModifiedBy(this.userViewDAO.loadUser(userid));
        loadedEnquiry.setModifiedDate(DateUtil.getSystemDateTime());
        getHibernateTemplate().update(loadedEnquiry);
        StudentPrevClass studentPrevClass = loadedEnquiry.getStudentPrevClass();
        studentPrevClass.setClassName(enquiry.getStudentPrevClass().getClassName());
        studentPrevClass.setCity(enquiry.getStudentPrevClass().getCity());
        studentPrevClass.setBoard(enquiry.getStudentPrevClass().getBoard());
        studentPrevClass.setInstituteName(enquiry.getStudentPrevClass().getInstituteName());
        studentPrevClass.setStudentStatus(enquiry.getStudentPrevClass().getStudentStatus());
        studentPrevClass.setPercentage(enquiry.getStudentPrevClass().getPercentage());
    }

    public List<EnquiryCountData> getClassWiseEnquiryCount(Long instituteId, Long academicYearId, Long userId) {
        List<EnquiryCountData> enquiryCountDataList = null;
        Criteria enqCriteria = getSession().createCriteria(Enquiry.class).createAlias("academicYearClass", "academicYearClass");
        enqCriteria.add(Restrictions.eq("academicYear.id", academicYearId));
        enqCriteria.add(Restrictions.eq("institute.id", instituteId));
        if (userId != null) {
            enqCriteria.add(Restrictions.disjunction().add(Restrictions.eq("owner.id", userId)).add(Restrictions.eq("assignee.id", userId)));
        }
        enqCriteria.setProjection(Projections.projectionList().add(Projections.groupProperty("academicYearClass.id")).add(Projections.property("academicYearClass.displayName")).add(Projections.count("academicYearClass.id"), "enquryCount")).addOrder(Order.asc("enquryCount"));
        Iterator it = enqCriteria.list().iterator();
        if (it.hasNext()) {
            enquiryCountDataList = new ArrayList();
            while (it.hasNext()) {
                Object[] col = (Object[]) it.next();
                EnquiryCountData enquiryCountData = new EnquiryCountData();
                enquiryCountData.setDisplayName((String) col[1]);
                enquiryCountData.setId((Long) col[0]);
                enquiryCountData.setEnquiryCount((Long) col[2]);
                enquiryCountDataList.add(enquiryCountData);
            }
        }
        return enquiryCountDataList;
    }

    public List<ClasswiseEnquiryCount> getClasswiseEnquiryCount(Long academicYearId) {
        List<ClasswiseEnquiryCount> ClasswiseEnquiryCountList = null;
        Criteria enqCriteria = getSession().createCriteria(Enquiry.class).createAlias("academicYearClass", "academicYearClass");
        enqCriteria.add(Restrictions.eq("academicYear.id", academicYearId));
        enqCriteria.setProjection(Projections.projectionList().add(Projections.groupProperty("academicYearClass.id")).add(Projections.property("academicYearClass.displayName")).add(Projections.count("academicYearClass.id"), "enquryCount")).addOrder(Order.asc("enquryCount"));
        Iterator it = enqCriteria.list().iterator();
        if (it.hasNext()) {
            ClasswiseEnquiryCountList = new ArrayList();
            while (it.hasNext()) {
                Object[] col = (Object[]) it.next();
                ClasswiseEnquiryCount classwiseEnquiryCount = new ClasswiseEnquiryCount();
                classwiseEnquiryCount.setClassId((Long) col[0]);
                classwiseEnquiryCount.setClassName((String) col[1]);
                classwiseEnquiryCount.setEnquiryCount((Long) col[2]);
                classwiseEnquiryCount.setAcademicSessionId(academicYearId);
                ClasswiseEnquiryCountList.add(classwiseEnquiryCount);
            }
        }
        return ClasswiseEnquiryCountList;
    }

    public List<EnquiryCountData> getStatusWiseEnquiryCount(Long instituteId, Long academicYearId, Long userId) {
        List<EnquiryCountData> enquiryCountDataList = null;
        Criteria enqCriteria = getSession().createCriteria(Enquiry.class).createAlias("enquiryStatus", "enquiryStatus");
        if (userId != null) {
            enqCriteria.add(Restrictions.disjunction().add(Restrictions.eq("owner.id", userId)).add(Restrictions.eq("assignee.id", userId)));
        }
        enqCriteria.add(Restrictions.eq("academicYear.id", academicYearId));
        enqCriteria.add(Restrictions.eq("institute.id", instituteId));
        enqCriteria.setProjection(Projections.projectionList().add(Projections.groupProperty("enquiryStatus.id")).add(Projections.property("enquiryStatus.name")).add(Projections.count("enquiryStatus.id"), "enquryCount")).addOrder(Order.asc("enquryCount"));
        Iterator it = enqCriteria.list().iterator();
        if (it.hasNext()) {
            enquiryCountDataList = new ArrayList();
            while (it.hasNext()) {
                Object[] col = (Object[]) it.next();
                enquiryCountDataList.add(new EnquiryCountData((Long) col[0], (String) col[1], (Long) col[2]));
            }
        }
        return enquiryCountDataList;
    }

    public List<EnquiryCountData> getCityWiseEnquiryCount(Long instituteId, Long academicYearId, Long userId) {
        List<EnquiryCountData> enquiryCountDataList = null;
        Criteria enqCriteria = getSession().createCriteria(Enquiry.class);
        enqCriteria.add(Restrictions.eq("academicYear.id", academicYearId));
        enqCriteria.add(Restrictions.eq("institute.id", instituteId));
        if (userId != null) {
            enqCriteria.add(Restrictions.disjunction().add(Restrictions.eq("owner.id", userId)).add(Restrictions.eq("assignee.id", userId)));
        }
        enqCriteria.setProjection(Projections.projectionList().add(Projections.groupProperty("address.city")).add(Projections.count("address.city"), "enquryCount")).addOrder(Order.asc("enquryCount"));
        List list = enqCriteria.list();
        if (!(list == null || list.isEmpty())) {
            Iterator it = list.iterator();
            if (it.hasNext()) {
                enquiryCountDataList = new ArrayList();
                while (it.hasNext()) {
                    Object[] col = (Object[]) it.next();
                    enquiryCountDataList.add(new EnquiryCountData(null, (String) col[0], (Long) col[1]));
                }
            }
        }
        return enquiryCountDataList;
    }

    public List<Enquiry> getAllEnquiries(Long instituteId, String searchStr, String propertyName, Long responsibleUserId, Long academicYearId) {
        Criteria enqCrt = getSession().createCriteria(Enquiry.class);
        enqCrt.add(Restrictions.eq(propertyName, searchStr));
        enqCrt.add(Restrictions.eq("academicYear.id", academicYearId));
        enqCrt.add(Restrictions.eq("institute.id", instituteId));
        if (responsibleUserId != null) {
            enqCrt.add(Restrictions.disjunction().add(Restrictions.eq("owner.id", responsibleUserId)).add(Restrictions.eq("assignee.id", responsibleUserId)));
        }
        enqCrt.addOrder(Order.asc("studentFirstName")).addOrder(Order.asc("studentMiddleName")).addOrder(Order.asc("studentLastName"));
        return enqCrt.list();
    }

    public Boolean isEnquiryExist(Long instituteId, Long academicYearId, String studentFirstName, String phone1) {
        Criteria enqCrt = getSession().createCriteria(Enquiry.class);
        enqCrt.add(Restrictions.eq("academicYear.id", academicYearId));
        enqCrt.add(Restrictions.eq("institute.id", instituteId));
        List<Enquiry> enquiries = enqCrt.add(Restrictions.eq("studentFirstName", studentFirstName).ignoreCase()).add(Restrictions.eq("studentPhone1", phone1)).list();
        if (enquiries == null || enquiries.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public List<Enquiry> advanceSearch(Long instituteId, Long academicYearId, AdvanceEnquirySearchParam advanceEnquirySearchParam, Long userId) {
        Criteria enqCrt = getSession().createCriteria(Enquiry.class);
        enqCrt.add(Restrictions.eq("academicYear.id", academicYearId));
        enqCrt.add(Restrictions.eq("institute.id", instituteId));
        if (advanceEnquirySearchParam.getAcademicYearClassId() != null) {
            enqCrt.createAlias("academicYearClass", "academicYearClass");
            enqCrt.add(Restrictions.eq("academicYearClass.id", advanceEnquirySearchParam.getAcademicYearClassId()));
        } else if (advanceEnquirySearchParam.getCourseId() != null) {
            enqCrt.createAlias("academicYearClass", "academicYearClass");
            enqCrt.createAlias("academicYearClass.courseYear", "courseYear");
            enqCrt.createAlias("courseYear.course", "course");
            enqCrt.add(Restrictions.eq("course.id", advanceEnquirySearchParam.getCourseId()));
        } else if (advanceEnquirySearchParam.getAffiliationAuthoritId() != null) {
            enqCrt.createAlias("academicYearClass", "academicYearClass");
            enqCrt.createAlias("academicYearClass.courseYear", "courseYear");
            enqCrt.createAlias("courseYear.course", "course");
            enqCrt.createAlias("course.affiliatedTo", "affiliatedTo");
            enqCrt.add(Restrictions.eq("affiliatedTo.id", advanceEnquirySearchParam.getAffiliationAuthoritId()));
        }
        if (advanceEnquirySearchParam.getEnquiryStatus() != null && advanceEnquirySearchParam.getEnquiryStatus().getId().longValue() > Long.valueOf(0).longValue()) {
            enqCrt.add(Restrictions.eq("enquiryStatus.id", advanceEnquirySearchParam.getEnquiryStatus().getId()));
        }
        if (advanceEnquirySearchParam.getOwner() != null && advanceEnquirySearchParam.getOwner().getId().longValue() > Long.valueOf(0).longValue()) {
            enqCrt.add(Restrictions.eq("owner.id", advanceEnquirySearchParam.getOwner().getId()));
        }
        if (advanceEnquirySearchParam.getAssignee() != null && advanceEnquirySearchParam.getAssignee().getId().longValue() > Long.valueOf(0).longValue()) {
            enqCrt.add(Restrictions.eq("assignee.id", advanceEnquirySearchParam.getAssignee().getId()));
        }
        if (!(advanceEnquirySearchParam.getRegistrationNo() == null || advanceEnquirySearchParam.getRegistrationNo().trim().isEmpty())) {
            enqCrt.add(Restrictions.eq("registrationNo", advanceEnquirySearchParam.getRegistrationNo().trim()));
        }
        if (!(advanceEnquirySearchParam.getRegistered() == null || advanceEnquirySearchParam.getRegistered().trim().isEmpty() || !"true".equalsIgnoreCase(advanceEnquirySearchParam.getRegistered().trim()))) {
            enqCrt.add(Restrictions.ne("registrationFee", Long.valueOf(0)));
        }
        if (!(advanceEnquirySearchParam.getStudentFullName() == null || advanceEnquirySearchParam.getStudentFullName().isEmpty())) {
            String[] studentNames = advanceEnquirySearchParam.getStudentFullName().split(" ");
            if (studentNames != null && studentNames.length > 0) {
                if (studentNames.length == 1) {
                    enqCrt.add(Restrictions.eq("studentFirstName", studentNames[0].trim()).ignoreCase());
                } else if (studentNames.length == 2) {
                    enqCrt.add(Restrictions.eq("studentFirstName", studentNames[0].trim()).ignoreCase());
                    enqCrt.add(Restrictions.eq("studentLastName", studentNames[1].trim()).ignoreCase());
                } else {
                    enqCrt.add(Restrictions.eq("studentFirstName", studentNames[0].trim()).ignoreCase());
                    enqCrt.add(Restrictions.eq("studentMiddleName", studentNames[1].trim()).ignoreCase());
                    enqCrt.add(Restrictions.eq("studentLastName", studentNames[2].trim()).ignoreCase());
                }
            }
        }
        if (!(advanceEnquirySearchParam.getStudentGender() == null || advanceEnquirySearchParam.getStudentGender().trim().isEmpty())) {
            enqCrt.add(Restrictions.eq("studentGender", advanceEnquirySearchParam.getStudentGender().trim()).ignoreCase());
        }
        if (advanceEnquirySearchParam.getStudentCategory() != null && advanceEnquirySearchParam.getStudentCategory().getId().longValue() > Long.valueOf(0).longValue()) {
            enqCrt.add(Restrictions.eq("studentCategory.id", advanceEnquirySearchParam.getStudentCategory().getId()));
        }
        if (advanceEnquirySearchParam.getAddress() != null) {
            if (!advanceEnquirySearchParam.getAddress().getLine2().trim().isEmpty()) {
                enqCrt.add(Restrictions.eq("address.line2", advanceEnquirySearchParam.getAddress().getLine2().trim()).ignoreCase());
            }
            if (!advanceEnquirySearchParam.getAddress().getCity().trim().isEmpty()) {
                enqCrt.add(Restrictions.eq("address.city", advanceEnquirySearchParam.getAddress().getCity().trim()).ignoreCase());
            }
            if (!advanceEnquirySearchParam.getAddress().getTeh().trim().isEmpty()) {
                enqCrt.add(Restrictions.eq("address.teh", advanceEnquirySearchParam.getAddress().getTeh().trim()).ignoreCase());
            }
            if (!advanceEnquirySearchParam.getAddress().getDistrict().trim().isEmpty()) {
                enqCrt.add(Restrictions.eq("address.district", advanceEnquirySearchParam.getAddress().getDistrict().trim()).ignoreCase());
            }
        }
        if (advanceEnquirySearchParam.getState() != null && advanceEnquirySearchParam.getState().getId().longValue() > Long.valueOf(0).longValue()) {
            enqCrt.add(Restrictions.eq("state.id", advanceEnquirySearchParam.getState().getId()));
        }
        if (advanceEnquirySearchParam.getCountry() != null && advanceEnquirySearchParam.getCountry().getId().longValue() > Long.valueOf(0).longValue()) {
            enqCrt.add(Restrictions.eq("country.id", advanceEnquirySearchParam.getCountry().getId()));
        }
        if (advanceEnquirySearchParam.getStudentPrevClass() != null) {
            enqCrt.createAlias("studentPrevClass", "studentPrevClass");
            if (!advanceEnquirySearchParam.getStudentPrevClass().getClassName().trim().isEmpty()) {
                enqCrt.add(Restrictions.eq("studentPrevClass.className", advanceEnquirySearchParam.getStudentPrevClass().getClassName().trim()).ignoreCase());
            }
            if (!advanceEnquirySearchParam.getStudentPrevClass().getInstituteName().trim().isEmpty()) {
                enqCrt.add(Restrictions.eq("studentPrevClass.instituteName", advanceEnquirySearchParam.getStudentPrevClass().getInstituteName().trim()).ignoreCase());
            }
            if (!advanceEnquirySearchParam.getStudentPrevClass().getCity().trim().isEmpty()) {
                enqCrt.add(Restrictions.eq("studentPrevClass.city", advanceEnquirySearchParam.getStudentPrevClass().getCity().trim()).ignoreCase());
            }
            if (!advanceEnquirySearchParam.getStudentPrevClass().getBoard().trim().isEmpty()) {
                enqCrt.add(Restrictions.eq("studentPrevClass.board", advanceEnquirySearchParam.getStudentPrevClass().getBoard().trim()).ignoreCase());
            }
            if (!advanceEnquirySearchParam.getStudentPrevClass().getStudentStatus().trim().isEmpty()) {
                enqCrt.add(Restrictions.eq("studentPrevClass.studentStatus", advanceEnquirySearchParam.getStudentPrevClass().getStudentStatus().trim()).ignoreCase());
            }
        }
        Date enquiryActivityCreatedDateFrom = null;
        Date enquiryActivityCreatedDateTo = null;
        if (!advanceEnquirySearchParam.getActivityFromDateStr().trim().isEmpty()) {
            try {
                enquiryActivityCreatedDateFrom = DateUtil.parseDate(advanceEnquirySearchParam.getActivityFromDateStr().trim(), "dd-MMM-yyyy");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!advanceEnquirySearchParam.getActivityToDateStr().trim().isEmpty()) {
            try {
                enquiryActivityCreatedDateTo = DateUtil.parseDate(advanceEnquirySearchParam.getActivityToDateStr().trim(), "dd-MMM-yyyy");
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
        }
        if (!(enquiryActivityCreatedDateFrom == null && enquiryActivityCreatedDateTo == null && advanceEnquirySearchParam.getActivityType().trim().isEmpty())) {
            enqCrt.createAlias("enquiryActivities", "enquiryActivity");
            if (enquiryActivityCreatedDateFrom != null) {
                enqCrt.add(Restrictions.ge("enquiryActivity.createdDateTime", enquiryActivityCreatedDateFrom));
            }
            if (enquiryActivityCreatedDateTo != null) {
                enqCrt.add(Restrictions.le("enquiryActivity.createdDateTime", DateUtil.closeToNextDate(enquiryActivityCreatedDateTo)));
            }
            if (!advanceEnquirySearchParam.getActivityType().trim().isEmpty()) {
                enqCrt.add(Restrictions.eq("enquiryActivity.activityType", advanceEnquirySearchParam.getActivityType().trim()));
            }
        }
        if (userId != null) {
            enqCrt.add(Restrictions.disjunction().add(Restrictions.eq("owner.id", userId)).add(Restrictions.eq("assignee.id", userId)));
        }
        enqCrt.addOrder(Order.asc("studentFirstName")).addOrder(Order.asc("studentMiddleName")).addOrder(Order.asc("studentLastName"));
        enqCrt.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return enqCrt.list();
    }

    public List<Enquiry> getEnquiries(Collection<Long> classes) {
        Criteria crt = getSession().createCriteria(Enquiry.class);
        crt.add(Restrictions.in("academicYearClass.id", classes));
        return crt.list();
    }

    public List<Enquiry> getEnquiruesWithFormNo(Long academicSessionId, String formNo) {
        Criteria crt = getSession().createCriteria(Enquiry.class);
        crt.add(Restrictions.eq("academicYear.id", academicSessionId));
        crt.add(Restrictions.eq("formNo", formNo));
        return crt.list();
    }

    public Enquiry getEnquiryByFormNo(Long academicSessionId, String formNo) {
        Criteria crt = getSession().createCriteria(Enquiry.class);
        crt.add(Restrictions.eq("academicYear.id", academicSessionId));
        crt.add(Restrictions.eq("formNo", formNo));
        return (Enquiry) crt.uniqueResult();
    }

    public Enquiry getEnquiryByContactNo(Long academicSessionId, String contactNo) {
        Criteria crt = getSession().createCriteria(Enquiry.class);
        crt.add(Restrictions.eq("academicYear.id", academicSessionId));
        crt.add(Restrictions.eq("studentContactNo", contactNo));
        return (Enquiry) crt.uniqueResult();
    }

    public List<Enquiry> getEnquiriesByContactNo(Long academicSessionId, String contactNo, String contactOf) {
        Criteria crt = getSession().createCriteria(Enquiry.class);
        crt.add(Restrictions.eq("academicYear.id", academicSessionId));
        if ("Mother".equals(contactOf)) {
            crt.add(Restrictions.eq("motherContactNo", contactNo));
        } else if ("Father".equals(contactOf)) {
            crt.add(Restrictions.eq("fatherContactNo", contactNo));
        } else if ("Student".equals(contactOf)) {
            crt.add(Restrictions.eq("studentContactNo", contactNo));
        }
        return crt.list();
    }

    public List<String> getEnquiryCities(Long instituteId) {
        List<String> cities = null;
        Criteria crt = getSession().createCriteria(Enquiry.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.addOrder(Order.asc("address.city"));
        crt.setProjection(Projections.projectionList().add(Projections.distinct(Projections.property("address.city"))));
        List list = crt.list();
        if (!(list == null || list.isEmpty())) {
            Iterator it = list.iterator();
            if (it.hasNext()) {
                cities = new ArrayList();
                while (it.hasNext()) {
                    cities.add((String) it.next());
                }
            }
        }
        return cities;
    }

    public List<EnquiryBriefInfo> getEnquiryBrifInfo(Long academicSession, String searchString) {
        if (searchString == null) {
            return null;
        }
        String lastName = null;
        String[] names = searchString.split(" ");
        String firstName = names[0];
        if (names.length > 1) {
            lastName = names[1];
        }
        Criteria crt = getSession().createCriteria(Enquiry.class);
        crt.setProjection(Projections.projectionList().add(Projections.property("id")).add(Projections.property("studentFirstName")).add(Projections.property("studentLastName")).add(Projections.property("studentGender")).add(Projections.property("studentFatherName")));
        crt.add(Restrictions.eq("academicYear.id", academicSession));
        if (lastName != null) {
            crt.add(Restrictions.eq("studentFirstName", firstName).ignoreCase());
            crt.add(Restrictions.like("studentLastName", lastName, MatchMode.START).ignoreCase());
        } else {
            crt.add(Restrictions.disjunction().add(Restrictions.like("studentFirstName", firstName, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("studentLastName", firstName, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("studentContactNo", firstName, MatchMode.START).ignoreCase()).add(Restrictions.like("fatherContactNo", firstName, MatchMode.START).ignoreCase()).add(Restrictions.like("motherContactNo", firstName, MatchMode.START).ignoreCase()));
        }
        crt.addOrder(Order.asc("studentFirstName")).addOrder(Order.asc("studentLastName"));
        Iterator it = crt.list().iterator();
        if (!it.hasNext()) {
            return null;
        }
        List<EnquiryBriefInfo> students = new ArrayList();
        while (it.hasNext()) {
            Object[] col = (Object[]) it.next();
            students.add(new EnquiryBriefInfo((Long) col[0], (String) col[1], (String) col[2], (String) col[3], (String) col[4]));
        }
        return students;
    }

    public List<Enquiry> getEnquiriesByDate(Long academicSession, Date from, Date to) {
        Criteria crt = getSession().createCriteria(Enquiry.class);
        crt.add(Restrictions.eq("academicYear.id", academicSession));
        if (from != null) {
            crt.add(Restrictions.ge("createdDate", from));
        }
        if (to != null) {
            crt.add(Restrictions.le("createdDate", to));
        }
        return crt.list();
    }

    public List<Enquiry> getEnquiriesByFormIssueDate(Long academicSession, Date from, Date to) {
        Criteria crt = getSession().createCriteria(Enquiry.class);
        crt.add(Restrictions.eq("academicYear.id", academicSession));
        if (from != null) {
            crt.add(Restrictions.ge("formIssueDate", from));
        }
        if (to != null) {
            crt.add(Restrictions.le("formIssueDate", to));
        }
        return crt.list();
    }
}
