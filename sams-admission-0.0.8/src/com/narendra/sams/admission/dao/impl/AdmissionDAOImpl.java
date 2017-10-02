package com.narendra.sams.admission.dao.impl;

import com.narendra.sams.admission.dao.AdmissionDAO;
import com.narendra.sams.admission.domain.Address;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.core.address.dao.AddressDAO;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AdmissionScheme;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.StudentCategory;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AdmissionDAOImpl extends HibernateDaoSupport implements AdmissionDAO {
    private AddressDAO addressDAO;

    public AddressDAO getAddressDAO() {
        return this.addressDAO;
    }

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    private UserView loadUser(Long userId) {
        return (UserView) getHibernateTemplate().load(UserView.class, userId);
    }

    public Long admitStudentTemporarily(Student student) {
        return (Long) getHibernateTemplate().save(student);
    }

    public void renewStudent(Student student, Long userId) {
        updateStudentInformation(student, userId);
        Student loadedStudent = (Student) getHibernateTemplate().get(Student.class, student.getId());
        if (student.getBusStop() == null || student.getBusStop().getId() == null) {
            loadedStudent.setBusStop(null);
        } else {
            loadedStudent.setBusStop((BusStop) getHibernateTemplate().load(BusStop.class, student.getBusStop().getId()));
        }
        loadedStudent.setAcademicYearClass((AcademicYearClass) getHibernateTemplate().load(AcademicYearClass.class, student.getAcademicYearClass().getId()));
        loadedStudent.setAcademicYear((AcademicYear) getHibernateTemplate().load(AcademicYear.class, student.getAcademicYear().getId()));
        loadedStudent.setAdmissionFormNo(student.getAdmissionFormNo());
        AdmissionType admissionType = new AdmissionType();
        admissionType.setId(AdmissionType.REGULAR_ADMISSION_ID);
        loadedStudent.setAdmissionType(admissionType);
        StudentStatus studentStatus = new StudentStatus();
        studentStatus.setId(StudentStatus.TEMPORARY_RENEWAL);
        loadedStudent.setStudentStatus(studentStatus);
        UserView user = loadUser(userId);
        ClassHistory classHistory = new ClassHistory();
        classHistory.setAdmissionFormNo(loadedStudent.getAdmissionFormNo());
        classHistory.setAcademicYear(loadedStudent.getAcademicYear());
        classHistory.setAcademicYearClass(loadedStudent.getAcademicYearClass());
        classHistory.setActiveClass(Boolean.TRUE);
        classHistory.setAdmissionType(loadedStudent.getAdmissionType());
        classHistory.setStudentStatus(loadedStudent.getStudentStatus());
        classHistory.setBusStop(loadedStudent.getBusStop());
        classHistory.setUser(user);
        classHistory.setModifiedDate(loadedStudent.getModifiedDate());
        classHistory.setStudent(student);
        loadedStudent.getClassHistories().add(classHistory);
        getHibernateTemplate().update(loadedStudent);
    }

    public void renewStudent(Long studentId, String formNo, Long renewalClassId, Long admissionSchemeId, Long busStopId, Long userId) {
        Student loadedStudent = (Student) getHibernateTemplate().get(Student.class, studentId);
        if (busStopId != null) {
            loadedStudent.setBusStop((BusStop) getHibernateTemplate().load(BusStop.class, busStopId));
        } else {
            loadedStudent.setBusStop(null);
        }
        if (admissionSchemeId != null) {
            loadedStudent.setAdmissionScheme((AdmissionScheme) getHibernateTemplate().load(AdmissionScheme.class, admissionSchemeId));
        }
        AcademicYearClass academicYearClass = (AcademicYearClass) getHibernateTemplate().load(AcademicYearClass.class, renewalClassId);
        loadedStudent.setAcademicYearClass(academicYearClass);
        loadedStudent.setAcademicYear(academicYearClass.getAcademicYear());
        loadedStudent.setLastRegistrationDate(DateUtil.getSystemDateTime());
        loadedStudent.setAdmissionFormNo(formNo);
        AdmissionType admissionType = new AdmissionType();
        admissionType.setId(AdmissionType.REGULAR_ADMISSION_ID);
        loadedStudent.setAdmissionType(admissionType);
        StudentStatus studentStatus = new StudentStatus();
        studentStatus.setId(StudentStatus.TEMPORARY_RENEWAL);
        loadedStudent.setStudentStatus(studentStatus);
        UserView user = loadUser(userId);
        ClassHistory classHistory = new ClassHistory();
        classHistory.setAdmissionFormNo(loadedStudent.getAdmissionFormNo());
        classHistory.setAcademicYear(loadedStudent.getAcademicYear());
        classHistory.setAcademicYearClass(loadedStudent.getAcademicYearClass());
        classHistory.setActiveClass(Boolean.TRUE);
        classHistory.setAdmissionType(loadedStudent.getAdmissionType());
        classHistory.setStudentStatus(loadedStudent.getStudentStatus());
        classHistory.setBusStop(loadedStudent.getBusStop());
        classHistory.setUser(user);
        classHistory.setModifiedDate(loadedStudent.getModifiedDate());
        classHistory.setStudent(loadedStudent);
        classHistory.setAdmissionScheme(loadedStudent.getAdmissionScheme());
        classHistory.setLastRegistrationDate(loadedStudent.getLastRegistrationDate());
        loadedStudent.getClassHistories().add(classHistory);
        getHibernateTemplate().update(loadedStudent);
    }

    private void updateStudentInformation(Student student, Long userId) {
        Student loadedStudent = (Student) getHibernateTemplate().get(Student.class, student.getId());
        loadedStudent.setModifiedBy(loadUser(userId));
        loadedStudent.setModifiedDate(DateUtil.getSystemDateTime());
        loadedStudent.copyProperties(student);
        if (!(student.getStudentCategory() == null || student.getStudentCategory().getId() == null)) {
            loadedStudent.setStudentCategory((StudentCategory) getHibernateTemplate().load(StudentCategory.class, student.getStudentCategory().getId()));
        }
        student.getAddresses();
        Address localAddress = loadedStudent.getLocalAddress();
        if (localAddress != null) {
            localAddress.setLine1(student.getAddress().getLine1());
            localAddress.setLine2(student.getAddress().getLine2());
            localAddress.setCity(student.getAddress().getCity());
            localAddress.setTeh(student.getAddress().getTeh());
            localAddress.setDistrict(student.getAddress().getDistrict());
            localAddress.setZipCode(student.getAddress().getZipCode());
            if (student.getAddress().getState() == null || student.getAddress().getState().getId() == null) {
                localAddress.setState(null);
            } else {
                localAddress.setState(this.addressDAO.loadState(student.getAddress().getState().getId()));
            }
            if (student.getAddress().getCountry() == null || student.getAddress().getCountry().getId() == null) {
                localAddress.setCountry(null);
            } else {
                localAddress.setCountry(this.addressDAO.loadCountry(student.getAddress().getCountry().getId()));
            }
        }
        if (StudentStatus.TEMPORARY.equals(loadedStudent.getStudentStatus().getId()) || StudentStatus.TEMPORARY_RENEWAL.equals(loadedStudent.getStudentStatus().getId())) {
            loadedStudent.setAcademicYearClass(student.getAcademicYearClass());
            if (student.getBusStop() == null || student.getBusStop().getId() == null) {
                loadedStudent.setBusStop(null);
            } else {
                loadedStudent.setBusStop(student.getBusStop());
            }
            loadedStudent.setAdmissionFormNo(student.getAdmissionFormNo());
            ClassHistory classHistory = loadedStudent.getActiveClassHistory(loadedStudent.getAcademicYear().getId());
            classHistory.setAcademicYearClass(student.getAcademicYearClass());
            if (student.getBusStop() == null || student.getBusStop().getId() == null) {
                classHistory.setBusStop(null);
            } else {
                classHistory.setBusStop(student.getBusStop());
            }
            classHistory.setAdmissionFormNo(student.getAdmissionFormNo());
        }
        getHibernateTemplate().update(loadedStudent);
    }
}
