package com.narendra.sams.admission.service.impl;

import com.narendra.sams.admission.dao.AdmissionDAO;
import com.narendra.sams.admission.dao.StudentDAO;
import com.narendra.sams.admission.domain.Address;
import com.narendra.sams.admission.domain.AddressOf;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.service.AdmissionService;
import com.narendra.sams.admission.service.StudentActivityLogService;
import com.narendra.sams.core.address.dao.UserViewDAO;
import com.narendra.sams.core.address.domain.Country;
import com.narendra.sams.core.address.domain.State;
import com.narendra.sams.core.dao.AcademicYearDAO;
import com.narendra.sams.core.domain.AcademicYearAdmissionCount;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AdmissionServiceImpl implements AdmissionService {
    private AcademicYearDAO academicYearDAO;
    private AdmissionDAO admissionDAO;
    private StudentActivityLogService studentActivityLogService;
    private StudentDAO studentDAO;
    private UserViewDAO userViewDAO;

    public StudentDAO getStudentDAO() {
        return this.studentDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public UserViewDAO getUserViewDAO() {
        return this.userViewDAO;
    }

    public void setUserViewDAO(UserViewDAO userViewDAO) {
        this.userViewDAO = userViewDAO;
    }

    public StudentActivityLogService getStudentActivityLogService() {
        return this.studentActivityLogService;
    }

    public void setStudentActivityLogService(StudentActivityLogService studentActivityLogService) {
        this.studentActivityLogService = studentActivityLogService;
    }

    public AdmissionDAO getAdmissionDAO() {
        return this.admissionDAO;
    }

    public void setAdmissionDAO(AdmissionDAO admissionDAO) {
        this.admissionDAO = admissionDAO;
    }

    public AcademicYearDAO getAcademicYearDAO() {
        return this.academicYearDAO;
    }

    public void setAcademicYearDAO(AcademicYearDAO academicYearDAO) {
        this.academicYearDAO = academicYearDAO;
    }

    public Long admitStudentTemporarily(Student student, String idPrefix, Long userId) {
        UserView user = this.userViewDAO.loadUser(userId);
        Date systemDate = DateUtil.getSystemDateTime();
        student.setCreatedBy(user);
        student.setModifiedBy(user);
        student.setCreatedDate(systemDate);
        student.setModifiedDate(systemDate);
        student.setLastRegistrationDate(systemDate);
        AdmissionType admissionType = new AdmissionType();
        admissionType.setId(AdmissionType.NEW_ADMISSION_ID);
        student.setAdmissionType(admissionType);
        StudentStatus studentStatus = new StudentStatus();
        studentStatus.setId(StudentStatus.TEMPORARY);
        student.setStudentStatus(studentStatus);
        Set<ClassHistory> classHistories = new HashSet();
        ClassHistory classHistory = new ClassHistory();
        classHistory.setAdmissionFormNo(student.getAdmissionFormNo());
        classHistory.setAcademicYear(student.getAcademicYear());
        classHistory.setAcademicYearClass(student.getAcademicYearClass());
        classHistory.setActiveClass(Boolean.TRUE);
        classHistory.setAdmissionType(student.getAdmissionType());
        classHistory.setStudentStatus(student.getStudentStatus());
        classHistory.setBusStop(student.getBusStop());
        classHistory.setUser(student.getCreatedBy());
        classHistory.setModifiedDate(systemDate);
        classHistory.setLastRegistrationDate(systemDate);
        classHistory.setStudent(student);
        if (student.getAdmissionScheme() != null) {
            classHistory.setAdmissionScheme(student.getAdmissionScheme());
        }
        classHistories.add(classHistory);
        student.setClassHistories(classHistories);
        Set<Address> addresses = new HashSet();
        Address localAddress = new Address();
        localAddress.setLine1(student.getAddress().getLine1());
        localAddress.setLine2(student.getAddress().getLine2());
        localAddress.setCity(student.getAddress().getCity());
        localAddress.setTeh(student.getAddress().getTeh());
        localAddress.setDistrict(student.getAddress().getDistrict());
        localAddress.setZipCode(student.getAddress().getZipCode());
        localAddress.setAddressOf(AddressOf.LOCAL);
        if (student.getAddress().getState() == null || student.getAddress().getState().getId() == null) {
            localAddress.setState(null);
        } else {
            State state = new State();
            state.setId(student.getAddress().getState().getId());
            localAddress.setState(state);
        }
        Country country = new Country();
        country.setId(Long.valueOf(1));
        localAddress.setCountry(country);
        Address parentsAddress = new Address();
        parentsAddress.setAddressOf(AddressOf.PARENTS);
        parentsAddress.setStudent(student);
        Address gaurdianAddress = new Address();
        gaurdianAddress.setAddressOf(AddressOf.GAURDIAN);
        gaurdianAddress.setStudent(student);
        addresses.add(localAddress);
        addresses.add(parentsAddress);
        addresses.add(gaurdianAddress);
        student.setAddresses(addresses);
        student.setStudentId(prepareTemporaryStudentId(student.getAcademicYear().getId(), idPrefix));
        Long studentId = this.admissionDAO.admitStudentTemporarily(student);
        this.studentActivityLogService.addRegistrationActivity(studentId, student.getAcademicYearClass().getId(), student.getAcademicYear().getId(), userId, "");
        return studentId;
    }

    public Long addStudent(Student student, Long userId) {
        UserView user = this.userViewDAO.loadUser(userId);
        Date systemDate = DateUtil.getSystemDateTime();
        student.setCreatedBy(user);
        student.setModifiedBy(user);
        student.setCreatedDate(systemDate);
        student.setModifiedDate(systemDate);
        student.setLastRegistrationDate(systemDate);
        Set<ClassHistory> classHistories = new HashSet();
        ClassHistory classHistory = new ClassHistory();
        classHistory.setAdmissionFormNo(student.getAdmissionFormNo());
        classHistory.setAcademicYear(student.getAcademicYear());
        classHistory.setAcademicYearClass(student.getAcademicYearClass());
        classHistory.setActiveClass(Boolean.TRUE);
        classHistory.setAdmissionType(student.getAdmissionType());
        classHistory.setStudentStatus(student.getStudentStatus());
        classHistory.setBusStop(student.getBusStop());
        classHistory.setUser(student.getCreatedBy());
        classHistory.setModifiedDate(systemDate);
        classHistory.setLastRegistrationDate(systemDate);
        classHistory.setAdmissionConfirmDateTime(student.getAdmissionConfirmationDate());
        classHistory.setStudent(student);
        if (student.getAdmissionScheme() != null) {
            classHistory.setAdmissionScheme(student.getAdmissionScheme());
        }
        classHistories.add(classHistory);
        student.setClassHistories(classHistories);
        Set<Address> addresses = new HashSet();
        Address localAddress = new Address();
        localAddress.setAddressOf(AddressOf.LOCAL);
        Country country = new Country();
        country.setId(Long.valueOf(1));
        localAddress.setCountry(country);
        if (student.getAddress() != null) {
            localAddress.setLine1(student.getAddress().getLine1());
            localAddress.setLine2(student.getAddress().getLine2());
            localAddress.setCity(student.getAddress().getCity());
            localAddress.setTeh(student.getAddress().getTeh());
            localAddress.setDistrict(student.getAddress().getDistrict());
            localAddress.setZipCode(student.getAddress().getZipCode());
            if (student.getAddress().getState() == null || student.getAddress().getState().getId() == null) {
                localAddress.setState(null);
            } else {
                State state = new State();
                state.setId(student.getAddress().getState().getId());
                localAddress.setState(state);
            }
        }
        Address parentsAddress = new Address();
        parentsAddress.setAddressOf(AddressOf.PARENTS);
        parentsAddress.setStudent(student);
        Address gaurdianAddress = new Address();
        gaurdianAddress.setAddressOf(AddressOf.GAURDIAN);
        gaurdianAddress.setStudent(student);
        addresses.add(localAddress);
        addresses.add(parentsAddress);
        addresses.add(gaurdianAddress);
        student.setAddresses(addresses);
        return this.admissionDAO.admitStudentTemporarily(student);
    }

    public void renewStudent(Student student, Long userId) {
        this.admissionDAO.renewStudent(student, userId);
    }

    public void renewStudent(Long studentId, String formNo, Long renewalClassId, Long admissionSchemeId, Long busStopId, Long userId) {
        Student student = this.studentDAO.getStudentById(studentId);
        Long oldClassId = student.getAcademicYearClass().getId();
        this.admissionDAO.renewStudent(studentId, formNo, renewalClassId, admissionSchemeId, busStopId, userId);
        this.studentActivityLogService.addAdmissionRenewActivity(studentId, renewalClassId, student.getAcademicYear().getId(), oldClassId, userId, "");
    }

    public void renewStudents(Collection<Long> studentIds, Long renewalClassId, Boolean copyBusStop, Boolean copyAdmissionScheme, Long userId) {
        if (studentIds != null) {
            for (Long studentId : studentIds) {
                Student student = this.studentDAO.getStudentById(studentId);
                Long oldClassId = student.getAcademicYearClass().getId();
                Long newBusStop = null;
                Long newAdmissionScheme = null;
                if (copyBusStop.booleanValue() && student.getBusStop() != null) {
                    newBusStop = student.getBusStop().getId();
                }
                if (copyAdmissionScheme.booleanValue() && student.getAdmissionScheme() != null) {
                    newAdmissionScheme = student.getAdmissionScheme().getId();
                }
                this.admissionDAO.renewStudent(studentId, null, renewalClassId, newAdmissionScheme, newBusStop, userId);
                this.studentActivityLogService.addAdmissionRenewActivity(studentId, renewalClassId, student.getAcademicYear().getId(), oldClassId, userId, "");
            }
        }
    }

    private String prepareTemporaryStudentId(Long academicYearId, String idPrefix) {
        String studentId = idPrefix;
        synchronized (this) {
            AcademicYearAdmissionCount academicYearAdmissionCount = this.studentDAO.loadAcademicYearAdmissionCount(academicYearId);
            long count = academicYearAdmissionCount.getTempAdmissionCount().longValue() + 1;
            studentId = new StringBuilder(String.valueOf(studentId)).append(this.academicYearDAO.getAcademicYearById(academicYearId.longValue()).getName().split("-")[0].trim()).toString();
            if (count < 10) {
                studentId = new StringBuilder(String.valueOf(studentId)).append("000").append(count).toString();
            } else if (count < 100) {
                studentId = new StringBuilder(String.valueOf(studentId)).append("00").append(count).toString();
            } else if (count < 1000) {
                studentId = new StringBuilder(String.valueOf(studentId)).append("0").append(count).toString();
            } else {
                studentId = new StringBuilder(String.valueOf(studentId)).append(count).toString();
            }
            academicYearAdmissionCount.setTempAdmissionCount(Long.valueOf(count));
        }
        return studentId;
    }
}
