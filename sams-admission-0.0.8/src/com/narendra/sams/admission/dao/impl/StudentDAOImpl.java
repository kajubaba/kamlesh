package com.narendra.sams.admission.dao.impl;

import com.narendra.sams.admission.dao.StudentDAO;
import com.narendra.sams.admission.domain.Address;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.GaurdianAddress;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentBankDetails;
import com.narendra.sams.admission.domain.StudentGaurdian;
import com.narendra.sams.admission.domain.StudentParentsInformation;
import com.narendra.sams.admission.domain.StudentPersonalInformation;
import com.narendra.sams.admission.domain.StudentQuick;
import com.narendra.sams.admission.domain.StudentTranslation;
import com.narendra.sams.admission.utils.AddressType;
import com.narendra.sams.core.address.dao.AddressDAO;
import com.narendra.sams.core.address.domain.Country;
import com.narendra.sams.core.address.domain.State;
import com.narendra.sams.core.dao.AcademicYearDAO;
import com.narendra.sams.core.domain.AcademicYearAdmissionCount;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AdmissionScheme;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.Bank;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.StudentCategory;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StudentDAOImpl extends HibernateDaoSupport implements StudentDAO {
    private AcademicYearDAO academicYearDAO;
    private AddressDAO addressDAO;

    public AcademicYearDAO getAcademicYearDAO() {
        return this.academicYearDAO;
    }

    public void setAcademicYearDAO(AcademicYearDAO academicYearDAO) {
        this.academicYearDAO = academicYearDAO;
    }

    public AddressDAO getAddressDAO() {
        return this.addressDAO;
    }

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public UserView loadUser(Long userId) {
        return (UserView) getHibernateTemplate().load(UserView.class, userId);
    }

    public Student getStudentById(Long id) {
        return (Student) getHibernateTemplate().get(Student.class, id);
    }

    public AcademicYearAdmissionCount loadAcademicYearAdmissionCount(Long academicYearId) {
        Criteria crt = getSession().createCriteria(AcademicYearAdmissionCount.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        return (AcademicYearAdmissionCount) crt.uniqueResult();
    }

    public ClassHistory getClassHistory(Long id) {
        return (ClassHistory) getHibernateTemplate().get(ClassHistory.class, id);
    }

    public List<StudentQuick> findStudentsByChars(String chars, Long institueId) {
        if (chars == null) {
            return null;
        }
        String lastName = null;
        String[] names = chars.split(" ");
        String firstName = names[0];
        if (names.length > 1) {
            lastName = names[1];
        }
        Criteria crt = getSession().createCriteria(Student.class);
        crt.setProjection(Projections.projectionList().add(Projections.property("id")).add(Projections.property("studentId")).add(Projections.property("firstName")).add(Projections.property("lastName")).add(Projections.property("gender")).add(Projections.property("fatherName")));
        crt.add(Restrictions.eq("institute.id", institueId));
        if (lastName != null) {
            crt.add(Restrictions.eq("firstName", firstName).ignoreCase());
            crt.add(Restrictions.like("lastName", lastName, MatchMode.START).ignoreCase());
        } else {
            crt.add(Restrictions.disjunction().add(Restrictions.like("firstName", firstName, MatchMode.START).ignoreCase()).add(Restrictions.like("studentId", firstName, MatchMode.START).ignoreCase()));
        }
        crt.add(Restrictions.disjunction().add(Restrictions.eq("studentStatus.id", StudentStatus.CONFIRMED)).add(Restrictions.eq("studentStatus.id", StudentStatus.TEMPORARY)).add(Restrictions.eq("studentStatus.id", StudentStatus.TEMPORARY_RENEWAL)));
        crt.addOrder(Order.asc("firstName")).addOrder(Order.asc("lastName"));
        Iterator it = crt.list().iterator();
        if (!it.hasNext()) {
            return null;
        }
        List<StudentQuick> students = new ArrayList();
        while (it.hasNext()) {
            Object[] col = (Object[]) it.next();
            students.add(new StudentQuick((Long) col[0], (String) col[1], (String) col[2], (String) col[3], (String) col[4], (String) col[5]));
        }
        return students;
    }

    public void updatePersonalInformation(Student student, Long userId) {
        Student loadedStudent = getStudentById(student.getId());
        loadedStudent.setModifiedBy(loadUser(userId));
        loadedStudent.setModifiedDate(DateUtil.getSystemDateTime());
        loadedStudent.setFirstName(student.getFirstName());
        loadedStudent.setMiddleName(student.getMiddleName());
        loadedStudent.setLastName(student.getLastName());
        loadedStudent.setAdmissionFormNo(student.getAdmissionFormNo());
        if (student.getAcademicYearClass() != null) {
            loadedStudent.setAcademicYearClass((AcademicYearClass) getHibernateTemplate().load(AcademicYearClass.class, student.getAcademicYearClass().getId()));
        }
        loadedStudent.setGender(student.getGender());
        loadedStudent.setEmailId(student.getEmailId());
        loadedStudent.setDob(student.getDob());
        loadedStudent.setEnrollmentNo(student.getEnrollmentNo());
        loadedStudent.setPhone1(student.getPhone1());
        if (student.getAdmissionScheme() != null) {
            loadedStudent.setAdmissionScheme((AdmissionScheme) getHibernateTemplate().load(AdmissionScheme.class, student.getAdmissionScheme().getId()));
        } else {
            loadedStudent.setAdmissionScheme(null);
        }
        if (student.getStudentCategory() != null) {
            loadedStudent.setStudentCategory((StudentCategory) getHibernateTemplate().load(StudentCategory.class, student.getStudentCategory().getId()));
        } else {
            loadedStudent.setStudentCategory(null);
        }
        if (student.getBusStop() != null) {
            loadedStudent.setBusStop((BusStop) getHibernateTemplate().load(BusStop.class, student.getBusStop().getId()));
        } else {
            loadedStudent.setBusStop(null);
        }
        Address currentAddress = loadedStudent.getLocalAddress();
        currentAddress.setLine1(student.getAddress().getLine1());
        currentAddress.setLine2(student.getAddress().getLine2());
        currentAddress.setCity(student.getAddress().getCity());
        currentAddress.setZipCode(student.getAddress().getZipCode());
        if (student.getAddress().getState() != null) {
            currentAddress.setState((State) getHibernateTemplate().load(State.class, student.getAddress().getState().getId()));
        } else {
            currentAddress.setState(null);
        }
        if (student.getAddress().getCountry() != null) {
            currentAddress.setCountry((Country) getHibernateTemplate().load(Country.class, student.getAddress().getCountry().getId()));
        } else {
            currentAddress.setCountry(null);
        }
        loadedStudent.setFatherName(student.getFatherName());
        loadedStudent.setMotherName(student.getMotherName());
        loadedStudent.setFatherContact1(student.getFatherContact1());
        loadedStudent.setSamagraId(student.getSamagraId());
        ClassHistory classHistory = loadedStudent.getActiveClassHistory(loadedStudent.getAcademicYear().getId());
        classHistory.setAdmissionFormNo(loadedStudent.getAdmissionFormNo());
        classHistory.setAcademicYearClass(loadedStudent.getAcademicYearClass());
        classHistory.setBusStop(loadedStudent.getBusStop());
        classHistory.setAdmissionScheme(loadedStudent.getAdmissionScheme());
        getHibernateTemplate().update(loadedStudent);
    }

    public Long addGaurdian(StudentGaurdian studentGaurdian, Long userId) {
        if (userId != null) {
            studentGaurdian.setLastUpdatedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        }
        studentGaurdian.setLasUpdatedDateTime(DateUtil.getSystemDateTime());
        return (Long) getHibernateTemplate().save(studentGaurdian);
    }

    public void updateGaurdian(StudentGaurdian studentGaurdian, Long userId) {
        StudentGaurdian loadedGaurdian = (StudentGaurdian) getHibernateTemplate().load(StudentGaurdian.class, studentGaurdian.getId());
        loadedGaurdian.setName(studentGaurdian.getName());
        loadedGaurdian.setDob(null);
        loadedGaurdian.setAnnualIncome(studentGaurdian.getAnnualIncome());
        loadedGaurdian.setOccupation(studentGaurdian.getOccupation());
        loadedGaurdian.setContactNo1(studentGaurdian.getContactNo1());
        loadedGaurdian.setContactNo2(studentGaurdian.getContactNo2());
        loadedGaurdian.setGender("");
        loadedGaurdian.setRelationWithStudent(studentGaurdian.getRelationWithStudent());
        loadedGaurdian.setLastUpdatedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        loadedGaurdian.setLasUpdatedDateTime(DateUtil.getSystemDateTime());
        Set<GaurdianAddress> addresses = loadedGaurdian.getAddresses();
        if (addresses != null) {
            GaurdianAddress gaurdianAddress = null;
            Iterator it = addresses.iterator();
            if (it.hasNext()) {
                gaurdianAddress = (GaurdianAddress) it.next();
            }
            if (gaurdianAddress != null) {
                GaurdianAddress newAddress = null;
                if (studentGaurdian.getAddresses() != null) {
                    for (GaurdianAddress newAddress2 : studentGaurdian.getAddresses()) {
                    }
                }
                if (newAddress != null) {
                    gaurdianAddress.setLine1(newAddress.getLine1());
                    gaurdianAddress.setLine2(newAddress.getLine2());
                    gaurdianAddress.setCity(newAddress.getCity());
                    gaurdianAddress.setTeh("");
                    gaurdianAddress.setDistrict("");
                    gaurdianAddress.setZipCode(newAddress.getZipCode());
                    gaurdianAddress.setAddressType(AddressType.CURRENT);
                    if (newAddress.getState() == null || newAddress.getState().getId() == null) {
                        gaurdianAddress.setState(null);
                    } else {
                        gaurdianAddress.setState((State) getHibernateTemplate().load(State.class, newAddress.getState().getId()));
                    }
                }
            }
        }
    }

    public void updateStudentPersonalInformation(StudentPersonalInformation studentPersonalInformation, Long userId) {
        Student student = (Student) getHibernateTemplate().load(Student.class, studentPersonalInformation.getStudentId());
        student.setStudentId(studentPersonalInformation.getStudentAssignedId());
        student.setModifiedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        student.setModifiedDate(DateUtil.getSystemDateTime());
        student.setFirstName(studentPersonalInformation.getFirstName());
        student.setMiddleName(studentPersonalInformation.getMiddleName());
        student.setLastName(studentPersonalInformation.getLastName());
        student.setGender(studentPersonalInformation.getGender());
        student.setSubCaste(studentPersonalInformation.getSubCaste());
        if (studentPersonalInformation.getCategoryId() != null) {
            student.setStudentCategory((StudentCategory) getHibernateTemplate().load(StudentCategory.class, studentPersonalInformation.getCategoryId()));
        } else {
            student.setStudentCategory(null);
        }
        student.setPhone1(studentPersonalInformation.getMobileNo1());
        student.setPhone2(studentPersonalInformation.getMobileNo2());
        student.setDob(studentPersonalInformation.getDob());
        student.setBirthPlace(studentPersonalInformation.getBirthPlace());
        student.setEmailId(studentPersonalInformation.getEmail());
        student.setSamagraId(studentPersonalInformation.getSamagraId());
        student.setFamilyId(studentPersonalInformation.getFamilyId());
        student.setAadharNo(studentPersonalInformation.getAadharNo());
        student.setReligion(studentPersonalInformation.getReligion());
        student.setBloodGroup(studentPersonalInformation.getBloodGroup());
        student.setNationality(studentPersonalInformation.getNationality());
        student.setLanguagesKnown(studentPersonalInformation.getLanguagesKnown());
        student.setEnrollmentNo(studentPersonalInformation.getEnrollmentNo());
        if (studentPersonalInformation.getAddress() != null) {
            Address studentAddress = (Address) getHibernateTemplate().load(Address.class, studentPersonalInformation.getAddress().getId());
            studentAddress.updateNewAddress(studentPersonalInformation.getAddress());
            if (studentPersonalInformation.getAddress().getState().getId() != null) {
                studentAddress.setState((State) getHibernateTemplate().load(State.class, studentPersonalInformation.getAddress().getState().getId()));
            } else {
                studentAddress.setState(null);
            }
            getHibernateTemplate().update(studentAddress);
        }
        getHibernateTemplate().update(student);
    }

    public void updateStudentParentsInformation(StudentParentsInformation studentParentsInformation, Long userId) {
        Student student = (Student) getHibernateTemplate().load(Student.class, studentParentsInformation.getStudentId());
        student.setModifiedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        student.setModifiedDate(DateUtil.getSystemDateTime());
        student.setFatherName(studentParentsInformation.getFatherName());
        student.setMotherName(studentParentsInformation.getMotherName());
        student.setFatherOccupation(studentParentsInformation.getFatherOccupation());
        student.setMotherOccupation(studentParentsInformation.getMotherOccupation());
        student.setFatherAnnualIncome(studentParentsInformation.getFatherAnnulaIncome());
        student.setMotherAnnualIncome(studentParentsInformation.getMotherAnnulaIncome());
        student.setFatherContact1(studentParentsInformation.getFatherContactNo1());
        student.setFatherContact2(studentParentsInformation.getFatherContactNo2());
        student.setMotherContact1(studentParentsInformation.getMotherContactNo1());
        student.setMotherContact2(studentParentsInformation.getMotherContactNo2());
        getHibernateTemplate().update(student);
        if (studentParentsInformation.getAddress() != null) {
            Address studentAddress;
            if (studentParentsInformation.getAddress().getId() != null) {
                studentAddress = (Address) getHibernateTemplate().load(Address.class, studentParentsInformation.getAddress().getId());
            } else {
                studentAddress = new Address();
                studentAddress.setAddressOf(Address.ADDRESS_TYPE_PARENT);
            }
            studentAddress.updateNewAddress(studentParentsInformation.getAddress());
            if (studentParentsInformation.getAddress().getState().getId() != null) {
                studentAddress.setState((State) getHibernateTemplate().load(State.class, studentParentsInformation.getAddress().getState().getId()));
            } else {
                studentAddress.setState(null);
            }
            studentAddress.setStudent(student);
            getHibernateTemplate().saveOrUpdate(studentAddress);
        }
    }

    public void updateStudentBankAccountDetails(StudentBankDetails studentBankDetails) {
        if (studentBankDetails.getId() != null) {
            StudentBankDetails loadedBankDetails = (StudentBankDetails) getHibernateTemplate().load(StudentBankDetails.class, studentBankDetails.getId());
            loadedBankDetails.setBranchName(studentBankDetails.getBranchName());
            loadedBankDetails.setIfsc(studentBankDetails.getIfsc());
            loadedBankDetails.setBank((Bank) getHibernateTemplate().load(Bank.class, studentBankDetails.getBank().getId()));
            loadedBankDetails.setBankAcNo(studentBankDetails.getBankAcNo());
            getHibernateTemplate().update(loadedBankDetails);
            return;
        }
        getHibernateTemplate().save(studentBankDetails);
    }

    public StudentBankDetails getStudentBankDetails(Long studentId) {
        Criteria crt = getSession().createCriteria(StudentBankDetails.class);
        crt.add(Restrictions.eq("student.id", studentId));
        return (StudentBankDetails) crt.uniqueResult();
    }

    public Long updateStudentBankDetails(StudentBankDetails studentBankDetails, Long userId) {
        if (studentBankDetails.getId() != null) {
            StudentBankDetails loadedBankDetails = (StudentBankDetails) getHibernateTemplate().load(StudentBankDetails.class, studentBankDetails.getId());
            loadedBankDetails.setBranchName(studentBankDetails.getBranchName());
            loadedBankDetails.setIfsc(studentBankDetails.getIfsc());
            if (studentBankDetails.getBank() != null) {
                loadedBankDetails.setBank((Bank) getHibernateTemplate().load(Bank.class, studentBankDetails.getBank().getId()));
            } else {
                loadedBankDetails.setBank(null);
            }
            loadedBankDetails.setBankAcNo(studentBankDetails.getBankAcNo());
            loadedBankDetails.setLastModifiedDateTime(DateUtil.getSystemDateTime());
            loadedBankDetails.setModifiedByUser((UserView) getHibernateTemplate().load(UserView.class, userId));
            getHibernateTemplate().update(loadedBankDetails);
            return loadedBankDetails.getId();
        }
        studentBankDetails.setLastModifiedDateTime(DateUtil.getSystemDateTime());
        studentBankDetails.setModifiedByUser((UserView) getHibernateTemplate().load(UserView.class, userId));
        if (studentBankDetails.getBank() != null) {
            studentBankDetails.setBank((Bank) getHibernateTemplate().load(Bank.class, studentBankDetails.getBank().getId()));
        } else {
            studentBankDetails.setBank(null);
        }
        return (Long) getHibernateTemplate().save(studentBankDetails);
    }

    public void updateImageName(Long studentId, String imageName) {
        Student student = (Student) getHibernateTemplate().load(Student.class, studentId);
        student.setImageName(imageName);
        getHibernateTemplate().update(student);
    }

    public void updateStudentTranslations(Collection<StudentTranslation> studentTranslations) {
        if (studentTranslations != null) {
            for (StudentTranslation studentTranslation : studentTranslations) {
                updateStudentTranslation(studentTranslation);
            }
        }
    }

    public void updateStudentTranslation(StudentTranslation studentTranslation) {
        if (studentTranslation != null) {
            Student student = (Student) getHibernateTemplate().load(Student.class, studentTranslation.getId());
            student.setTranslatedStudentName(studentTranslation.getTranslatedName());
            student.setTranslatedFatherName(studentTranslation.getTranslatedFatherName());
            student.setTranslatedCity(studentTranslation.getTranslatedCity());
            getHibernateTemplate().update(student);
        }
    }

    public Student getStudent(Long academicSessionId, String formNo) {
        Criteria crt = getSession().createCriteria(Student.class);
        crt.add(Restrictions.eq("academicYear.id", academicSessionId));
        crt.add(Restrictions.eq("admissionFormNo", formNo));
        crt.add(Restrictions.eq("admissionType.id", AdmissionType.NEW_ADMISSION_ID));
        return (Student) crt.uniqueResult();
    }
}
