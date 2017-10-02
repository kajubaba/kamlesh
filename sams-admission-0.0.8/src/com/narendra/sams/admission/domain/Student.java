package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AdmissionScheme;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.domain.StudentCategory;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.domain.Enquiry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.collections.comparators.ReverseComparator;

public class Student {
    private String aadharNo;
    private AcademicYear academicYear;
    private AcademicYearClass academicYearClass;
    private Address address;
    private Set<Address> addresses;
    private Date admissionConfirmationDate;
    private String admissionConfirmed;
    private String admissionFormNo;
    private AdmissionScheme admissionScheme;
    private AdmissionType admissionType;
    private String birthPlace;
    private String bloodGroup;
    private BusStop busStop;
    private Set<ClassHistory> classHistories;
    private ClassSection classSection;
    private UserView createdBy;
    private Date createdDate;
    private Set<CustomizeInstallment> customizeInstallments;
    private Date dob;
    private String dobStr;
    private String emailId;
    private Enquiry enquiry;
    private String enrollmentNo;
    private String familyId;
    private Long fatherAnnualIncome;
    private String fatherContact1;
    private String fatherContact2;
    private String fatherName;
    private String fatherOccupation;
    private Set<FeeTransaction> feeTransactions;
    private String firstName;
    private Set<StudentGaurdian> gaurdians;
    private String gender;
    private Long id;
    private String imageName;
    private Institute institute;
    private Boolean isIdGenerated;
    private Boolean isLocked;
    private String languagesKnown;
    private String lastName;
    private Date lastRegistrationDate;
    private String middleName;
    private UserView modifiedBy;
    private Date modifiedDate;
    private Long motherAnnualIncome;
    private String motherContact1;
    private String motherContact2;
    private String motherName;
    private String motherOccupation;
    private String nationality;
    private BusStop oldBusStop;
    private String phone1;
    private String phone2;
    private String phone3;
    private String religion;
    private String samagraId;
    private Set<StudentBankDetails> studentBankDetails;
    private StudentCategory studentCategory;
    private String studentId;
    private StudentStatus studentStatus;
    private String subCaste;
    private String translatedCity;
    private String translatedFatherName;
    private String translatedStudentName;

    public AdmissionType getAdmissionType() {
        return this.admissionType;
    }

    public void setAdmissionType(AdmissionType admissionType) {
        this.admissionType = admissionType;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Set<ClassHistory> getClassHistories() {
        return this.classHistories;
    }

    public void setClassHistories(Set<ClassHistory> classHistories) {
        this.classHistories = classHistories;
    }

    public void copyProperties(Student student) {
        this.enrollmentNo = student.getEnrollmentNo();
        this.firstName = student.getFirstName();
        this.middleName = student.getMiddleName();
        this.lastName = student.getLastName();
        this.fatherName = student.getFatherName();
        this.motherName = student.getMotherName();
        this.dob = student.getDob();
        this.gender = student.getGender();
        this.phone1 = student.getPhone1();
        this.phone2 = student.getPhone2();
        this.phone3 = student.getPhone3();
        this.emailId = student.getEmailId();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public StudentCategory getStudentCategory() {
        return this.studentCategory;
    }

    public void setStudentCategory(StudentCategory studentCategory) {
        this.studentCategory = studentCategory;
    }

    public String getPhone1() {
        return this.phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return this.phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return this.phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Set<Address> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public AcademicYearClass getAcademicYearClass() {
        return this.academicYearClass;
    }

    public void setAcademicYearClass(AcademicYearClass academicYearClass) {
        this.academicYearClass = academicYearClass;
    }

    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnrollmentNo() {
        return this.enrollmentNo;
    }

    public void setEnrollmentNo(String enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    public UserView getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(UserView createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public UserView getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(UserView modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDobStr() {
        return this.dobStr;
    }

    public void setDobStr(String dobStr) {
        this.dobStr = dobStr;
    }

    public Set<FeeTransaction> getFeeTransactions() {
        return this.feeTransactions;
    }

    public void setFeeTransactions(Set<FeeTransaction> feeTransactions) {
        this.feeTransactions = feeTransactions;
    }

    public String getAdmissionConfirmed() {
        return this.admissionConfirmed;
    }

    public void setAdmissionConfirmed(String admissionConfirmed) {
        this.admissionConfirmed = admissionConfirmed;
    }

    public BusStop getBusStop() {
        return this.busStop;
    }

    public void setBusStop(BusStop busStop) {
        this.busStop = busStop;
    }

    public String getAdmissionFormNo() {
        return this.admissionFormNo;
    }

    public void setAdmissionFormNo(String admissionFormNo) {
        this.admissionFormNo = admissionFormNo;
    }

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public int hashCode() {
        return (this.id == null ? 0 : this.id.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Student)) {
            return false;
        }
        Student other = (Student) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
            return true;
        } else if (this.id.equals(other.id)) {
            return true;
        } else {
            return false;
        }
    }

    public Set<CustomizeInstallment> getCustomizeInstallments() {
        return this.customizeInstallments;
    }

    public void setCustomizeInstallments(Set<CustomizeInstallment> customizeInstallments) {
        this.customizeInstallments = customizeInstallments;
    }

    public StudentStatus getStudentStatus() {
        return this.studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    public long getCustomizedPayableFee(Long academicYearId, Long courseYearId, Date feeDueDate) {
        long fee = 0;
        if (!(this.customizeInstallments == null || this.customizeInstallments.isEmpty())) {
            for (CustomizeInstallment customizeInstallment : this.customizeInstallments) {
                if (customizeInstallment.getCustomizeInstallmentDetails() != null && !customizeInstallment.getCustomizeInstallmentDetails().isEmpty() && customizeInstallment.getAcademicYearClass().getAcademicYear().getId().equals(academicYearId) && customizeInstallment.getAcademicYearClass().getCourseYear().getId().equals(courseYearId)) {
                    if (customizeInstallment.getDueDate().before(feeDueDate) || customizeInstallment.getDueDate().equals(feeDueDate)) {
                        for (CustomizeInstallmentDetail customizeInstallmentDetail : customizeInstallment.getCustomizeInstallmentDetails()) {
                            if (customizeInstallmentDetail.getAmount() != null) {
                                fee += customizeInstallmentDetail.getAmount().longValue();
                            }
                        }
                    }
                }
            }
        }
        return fee;
    }

    public long getPaidFee(Long academicYearId, Long courseYearId, Date date) {
        Date endDate = DateUtil.makeEndDate(date);
        long paidFee = 0;
        if (!(this.feeTransactions == null || this.feeTransactions.isEmpty())) {
            for (FeeTransaction feeTransaction : this.feeTransactions) {
                if (feeTransaction.getFeeTransactionDetails() != null && !feeTransaction.getFeeTransactionDetails().isEmpty() && feeTransaction.getStudentClass().getAcademicYear().getId().equals(academicYearId) && feeTransaction.getStudentClass().getCourseYear().getId().equals(courseYearId)) {
                    if (feeTransaction.getTransactionTime().before(endDate) || feeTransaction.getTransactionTime().equals(endDate)) {
                        for (FeeTransactionDetail feeTransactionDetail : feeTransaction.getFeeTransactionDetails()) {
                            if (!(feeTransactionDetail.getAmount() == null || "Late Fee".equalsIgnoreCase(feeTransactionDetail.getFeeHead().getName()))) {
                                paidFee += feeTransactionDetail.getAmount().longValue();
                            }
                        }
                    }
                }
            }
        }
        return paidFee;
    }

    public Date getLatestDueDate() {
        if (this.customizeInstallments == null || this.customizeInstallments.isEmpty()) {
            return null;
        }
        List<CustomizeInstallment> ciList = new ArrayList(this.customizeInstallments);
        Collections.sort(ciList, new BeanComparator("dueDate", new ReverseComparator()));
        return ((CustomizeInstallment) ciList.get(0)).getDueDate();
    }

    public List<CustomizeInstallment> getInstallments(Long academicYearId) {
        List<CustomizeInstallment> cIs = new ArrayList();
        if (!(this.customizeInstallments == null || this.customizeInstallments.isEmpty())) {
            for (CustomizeInstallment customizeInstallment : this.customizeInstallments) {
                if (customizeInstallment.getAcademicYearClass().getAcademicYear().getId().equals(academicYearId)) {
                    cIs.add(customizeInstallment);
                }
            }
        }
        Collections.sort(cIs, new BeanComparator("installment.id", new NullComparator()));
        return cIs;
    }

    public ClassHistory getActiveClassHistory(Long academicYearId) {
        if (!(academicYearId == null || this.classHistories == null || this.classHistories.isEmpty())) {
            for (ClassHistory ch : this.classHistories) {
                if (academicYearId.equals(ch.getAcademicYear().getId()) && ch.getActiveClass().booleanValue()) {
                    return ch;
                }
            }
        }
        return null;
    }

    public ClassHistory getActiveClassByClassId(Long classId) {
        if (!(classId == null || this.classHistories == null || this.classHistories.isEmpty())) {
            for (ClassHistory ch : this.classHistories) {
                if (classId.equals(ch.getAcademicYearClass().getId()) && ch.getActiveClass().booleanValue()) {
                    return ch;
                }
            }
        }
        return null;
    }

    public ClassHistory getActiveClassHistoryByHistoryId(Long classHistoryId) {
        if (!(classHistoryId == null || this.classHistories == null || this.classHistories.isEmpty())) {
            for (ClassHistory ch : this.classHistories) {
                if (classHistoryId.equals(ch.getId()) && ch.getActiveClass().booleanValue()) {
                    return ch;
                }
            }
        }
        return null;
    }

    public String getMotherName() {
        return this.motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getImageName() {
        return this.imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public AdmissionType getAcademicYearAdmissionType(Long academicYearId) {
        for (ClassHistory classHistory : this.classHistories) {
            if (classHistory != null && academicYearId.equals(classHistory.getAcademicYear().getId()) && classHistory.getActiveClass().booleanValue()) {
                return classHistory.getAdmissionType();
            }
        }
        return null;
    }

    public AdmissionScheme getAcademicYearAdmissionScheme(Long academicYearId) {
        for (ClassHistory classHistory : this.classHistories) {
            if (classHistory != null && academicYearId.equals(classHistory.getAcademicYear().getId()) && classHistory.getActiveClass().booleanValue()) {
                return classHistory.getAdmissionScheme();
            }
        }
        return null;
    }

    public BusStop getAcademicYearBusStop(Long academicYearId) {
        for (ClassHistory classHistory : this.classHistories) {
            if (classHistory != null && academicYearId.equals(classHistory.getAcademicYear().getId()) && classHistory.getActiveClass().booleanValue()) {
                return classHistory.getBusStop();
            }
        }
        return null;
    }

    public Boolean getIsLocked() {
        return this.isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public BusStop getOldBusStop() {
        return this.oldBusStop;
    }

    public void setOldBusStop(BusStop oldBusStop) {
        this.oldBusStop = oldBusStop;
    }

    public String getFullName() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.firstName);
        if (this.middleName != null) {
            sb.append(" ").append(this.middleName);
        }
        if (this.lastName != null) {
            sb.append(" ").append(this.lastName);
        }
        return sb.toString();
    }

    public Address getLocalAddress() {
        if (this.addresses != null) {
            for (Address address : this.addresses) {
                if (AddressOf.LOCAL.equals(address.getAddressOf())) {
                    return address;
                }
            }
        }
        return null;
    }

    public Address getParentsAddress() {
        if (this.addresses != null) {
            for (Address address : this.addresses) {
                if (AddressOf.PARENTS.equals(address.getAddressOf())) {
                    return address;
                }
            }
        }
        return null;
    }

    public Address getGurdianAddress() {
        if (this.addresses != null) {
            for (Address address : this.addresses) {
                if (AddressOf.GAURDIAN.equals(address.getAddressOf())) {
                    return address;
                }
            }
        }
        return null;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFatherContact1() {
        return this.fatherContact1;
    }

    public void setFatherContact1(String fatherContact1) {
        this.fatherContact1 = fatherContact1;
    }

    public String getFatherContact2() {
        return this.fatherContact2;
    }

    public void setFatherContact2(String fatherContact2) {
        this.fatherContact2 = fatherContact2;
    }

    public String getMotherContact1() {
        return this.motherContact1;
    }

    public void setMotherContact1(String motherContact1) {
        this.motherContact1 = motherContact1;
    }

    public String getMotherContact2() {
        return this.motherContact2;
    }

    public void setMotherContact2(String motherContact2) {
        this.motherContact2 = motherContact2;
    }

    public String getReligion() {
        return this.religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getSamagraId() {
        return this.samagraId;
    }

    public void setSamagraId(String samagraId) {
        this.samagraId = samagraId;
    }

    public AdmissionScheme getAdmissionScheme() {
        return this.admissionScheme;
    }

    public void setAdmissionScheme(AdmissionScheme admissionScheme) {
        this.admissionScheme = admissionScheme;
    }

    public String getBirthPlace() {
        return this.birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getBloodGroup() {
        return this.bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getFamilyId() {
        return this.familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public String getSubCaste() {
        return this.subCaste;
    }

    public void setSubCaste(String subCaste) {
        this.subCaste = subCaste;
    }

    public String getAadharNo() {
        return this.aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getLanguagesKnown() {
        return this.languagesKnown;
    }

    public void setLanguagesKnown(String languagesKnown) {
        this.languagesKnown = languagesKnown;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Set<StudentGaurdian> getGaurdians() {
        return this.gaurdians;
    }

    public void setGaurdians(Set<StudentGaurdian> gaurdians) {
        this.gaurdians = gaurdians;
    }

    public String getFatherOccupation() {
        return this.fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public Long getFatherAnnualIncome() {
        return this.fatherAnnualIncome;
    }

    public void setFatherAnnualIncome(Long fatherAnnualIncome) {
        this.fatherAnnualIncome = fatherAnnualIncome;
    }

    public String getMotherOccupation() {
        return this.motherOccupation;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public Long getMotherAnnualIncome() {
        return this.motherAnnualIncome;
    }

    public void setMotherAnnualIncome(Long motherAnnualIncome) {
        this.motherAnnualIncome = motherAnnualIncome;
    }

    public Date getLastRegistrationDate() {
        return this.lastRegistrationDate;
    }

    public void setLastRegistrationDate(Date lastRegistrationDate) {
        this.lastRegistrationDate = lastRegistrationDate;
    }

    public Date getAdmissionConfirmationDate() {
        return this.admissionConfirmationDate;
    }

    public void setAdmissionConfirmationDate(Date admissionConfirmationDate) {
        this.admissionConfirmationDate = admissionConfirmationDate;
    }

    public ClassSection getClassSection() {
        return this.classSection;
    }

    public void setClassSection(ClassSection classSection) {
        this.classSection = classSection;
    }

    public Boolean getIsIdGenerated() {
        return this.isIdGenerated;
    }

    public void setIsIdGenerated(Boolean isIdGenerated) {
        this.isIdGenerated = isIdGenerated;
    }

    public String getTranslatedStudentName() {
        return this.translatedStudentName;
    }

    public void setTranslatedStudentName(String translatedStudentName) {
        this.translatedStudentName = translatedStudentName;
    }

    public String getTranslatedFatherName() {
        return this.translatedFatherName;
    }

    public void setTranslatedFatherName(String translatedFatherName) {
        this.translatedFatherName = translatedFatherName;
    }

    public String getTranslatedCity() {
        return this.translatedCity;
    }

    public void setTranslatedCity(String translatedCity) {
        this.translatedCity = translatedCity;
    }

    public Enquiry getEnquiry() {
        return this.enquiry;
    }

    public void setEnquiry(Enquiry enquiry) {
        this.enquiry = enquiry;
    }

    public Set<StudentBankDetails> getStudentBankDetails() {
        return this.studentBankDetails;
    }

    public void setStudentBankDetails(Set<StudentBankDetails> studentBankDetails) {
        this.studentBankDetails = studentBankDetails;
    }
}
