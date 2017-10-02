package com.narendra.sams.web.restws.admission;

import com.narendra.sams.admission.domain.Address;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentBankDetails;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.admission.vo.StudentVO;
import com.narendra.sams.web.utils.StudentInformationUtil;
import java.util.ArrayList;
import java.util.List;

public class StudentListVOMaker {
    public static List<StudentVO> prepareStudentListToDisplay(List<ClassHistory> classHistories) {
        List<StudentVO> students = null;
        if (classHistories != null) {
            students = new ArrayList();
            for (ClassHistory classHistory : classHistories) {
                StudentVO studentVO = new StudentVO();
                studentVO.setId(classHistory.getStudent().getId());
                studentVO.setStudentId(classHistory.getStudent().getStudentId());
                studentVO.setName(StudentInformationUtil.getFullName(classHistory.getStudent().getFirstName(), classHistory.getStudent().getMiddleName(), classHistory.getStudent().getLastName()));
                studentVO.setGender(classHistory.getStudent().getGender());
                studentVO.setGuardianName(classHistory.getStudent().getFatherName());
                studentVO.setCurrentClass(classHistory.getAcademicYearClass().getDisplayName());
                studentVO.setCurrentClassId(classHistory.getAcademicYearClass().getId());
                studentVO.setMotherName(classHistory.getStudent().getMotherName());
                studentVO.setCaste(classHistory.getStudent().getSubCaste());
                studentVO.setRelegion(classHistory.getStudent().getReligion());
                studentVO.setBirthPlace(classHistory.getStudent().getBirthPlace());
                studentVO.setFamilyId(classHistory.getStudent().getFamilyId());
                studentVO.setSamagraId(classHistory.getStudent().getSamagraId());
                studentVO.setAadharNo(classHistory.getStudent().getAadharNo());
                studentVO.setBloodGroup(classHistory.getStudent().getBloodGroup());
                studentVO.setSubCaste(classHistory.getStudent().getSubCaste());
                if (classHistory.getStudent().getStudentCategory() != null) {
                    studentVO.setCategory(classHistory.getStudent().getStudentCategory().getName());
                }
                StringBuffer studentContactNo = new StringBuffer("");
                StringBuffer fatherContactNo = new StringBuffer("");
                StringBuffer motherContactNo = new StringBuffer("");
                if (!(classHistory.getStudent().getPhone1() == null || classHistory.getStudent().getPhone1().isEmpty())) {
                    studentContactNo.append(classHistory.getStudent().getPhone1());
                }
                if (!(classHistory.getStudent().getPhone2() == null || classHistory.getStudent().getPhone2().isEmpty())) {
                    studentContactNo.append(", ").append(classHistory.getStudent().getPhone2());
                }
                if (!(classHistory.getStudent().getFatherContact1() == null || classHistory.getStudent().getFatherContact1().isEmpty())) {
                    fatherContactNo.append(classHistory.getStudent().getFatherContact1());
                }
                if (!(classHistory.getStudent().getFatherContact2() == null || classHistory.getStudent().getFatherContact2().isEmpty())) {
                    fatherContactNo.append(", ").append(classHistory.getStudent().getFatherContact2());
                }
                if (!(classHistory.getStudent().getMotherContact1() == null || classHistory.getStudent().getMotherContact1().isEmpty())) {
                    motherContactNo.append(classHistory.getStudent().getMotherContact1());
                }
                if (!(classHistory.getStudent().getMotherContact2() == null || classHistory.getStudent().getMotherContact2().isEmpty())) {
                    motherContactNo.append(", ").append(classHistory.getStudent().getMotherContact2());
                }
                studentVO.setStudentContactNo(studentContactNo.toString());
                studentVO.setFatherContactNo(fatherContactNo.toString());
                studentVO.setMotherContactNo(motherContactNo.toString());
                StringBuffer address = new StringBuffer("");
                if (classHistory.getStudent().getAddresses() != null) {
                    for (Address add : classHistory.getStudent().getAddresses()) {
                        if (Address.ADDRESS_TYPE_LOCAL.equals(add.getAddressOf())) {
                            if (!(add.getLine1() == null || add.getLine1().isEmpty())) {
                                address.append(add.getLine1()).append(" , ");
                            }
                            if (!(add.getLine2() == null || add.getLine2().isEmpty())) {
                                address.append(add.getLine2()).append(" , ");
                            }
                            address.append(add.getCity());
                            studentVO.setCity(add.getCity());
                        }
                    }
                }
                studentVO.setFullAddress(address.toString());
                if (classHistory.getLastRegistrationDate() != null) {
                    studentVO.setAdmissionRegistrationDate(DateUtil.formatDate(classHistory.getLastRegistrationDate(), "dd-MMM-yyyy"));
                }
                if (classHistory.getAdmissionConfirmDateTime() != null) {
                    studentVO.setAdmissionConfirmationDate(DateUtil.formatDate(classHistory.getAdmissionConfirmDateTime(), "dd-MMM-yyyy"));
                }
                studentVO.setAdmissionFormNo(classHistory.getAdmissionFormNo());
                if (classHistory.getStudent().getDob() != null) {
                    studentVO.setDob(DateUtil.formatDate(classHistory.getStudent().getDob(), "dd-MMM-yyyy"));
                }
                if (classHistory.getClassSection() != null) {
                    studentVO.setClassSection(classHistory.getClassSection().getSectionName());
                }
                if (classHistory.getStudentStatus() != null) {
                    studentVO.setStudentStatus(classHistory.getStudentStatus().getName());
                }
                if (classHistory.getBusStop() != null) {
                    studentVO.setBusStopId(classHistory.getBusStop().getId());
                    studentVO.setBusStop(classHistory.getBusStop().getName());
                }
                if (classHistory.getAdmissionScheme() != null) {
                    studentVO.setAdmissionScheme(classHistory.getAdmissionScheme().getName());
                }
                if (!(classHistory.getStudent().getEnrollmentNo() == null || classHistory.getStudent().getEnrollmentNo().isEmpty())) {
                    studentVO.setEnrollmentNo(classHistory.getStudent().getEnrollmentNo());
                }
                if (!(classHistory.getStudent().getStudentBankDetails() == null || classHistory.getStudent().getStudentBankDetails().isEmpty())) {
                    for (StudentBankDetails studentBankDetails : classHistory.getStudent().getStudentBankDetails()) {
                        studentVO.setBankAcNo(studentBankDetails.getBankAcNo());
                        studentVO.setBankBranch(studentBankDetails.getBranchName());
                        if (studentBankDetails.getBank() != null) {
                            studentVO.setBankName(studentBankDetails.getBank().getBankName());
                        }
                        studentVO.setBankIfsc(studentBankDetails.getIfsc());
                    }
                }
                students.add(studentVO);
            }
        }
        return students;
    }

    public static List<StudentVO> prepareStudentToDisplay(List<Student> students) {
        List<StudentVO> studentVOs = null;
        if (students != null) {
            studentVOs = new ArrayList();
            for (Student student : students) {
                StudentVO studentVO = new StudentVO();
                studentVO.setId(student.getId());
                studentVO.setStudentId(student.getStudentId());
                studentVO.setName(StudentInformationUtil.getFullName(student.getFirstName(), student.getMiddleName(), student.getLastName()));
                studentVO.setGender(student.getGender());
                studentVO.setGuardianName(student.getFatherName());
                studentVO.setCurrentClass(student.getAcademicYearClass().getDisplayName());
                studentVO.setMotherName(student.getMotherName());
                studentVO.setCaste(student.getSubCaste());
                studentVO.setRelegion(student.getReligion());
                studentVO.setBirthPlace(student.getBirthPlace());
                studentVO.setFamilyId(student.getFamilyId());
                studentVO.setSamagraId(student.getSamagraId());
                studentVO.setAadharNo(student.getAadharNo());
                studentVO.setBloodGroup(student.getBloodGroup());
                if (student.getStudentCategory() != null) {
                    studentVO.setCategory(student.getStudentCategory().getName());
                }
                StringBuffer studentContactNo = new StringBuffer("");
                StringBuffer fatherContactNo = new StringBuffer("");
                StringBuffer motherContactNo = new StringBuffer("");
                if (!(student.getPhone1() == null || student.getPhone1().isEmpty())) {
                    studentContactNo.append(student.getPhone1());
                }
                if (!(student.getPhone2() == null || student.getPhone2().isEmpty())) {
                    studentContactNo.append(", ").append(student.getPhone2());
                }
                if (!(student.getFatherContact1() == null || student.getFatherContact1().isEmpty())) {
                    fatherContactNo.append(student.getFatherContact1());
                }
                if (!(student.getFatherContact2() == null || student.getFatherContact2().isEmpty())) {
                    fatherContactNo.append(", ").append(student.getFatherContact2());
                }
                if (!(student.getMotherContact1() == null || student.getMotherContact1().isEmpty())) {
                    motherContactNo.append(student.getMotherContact1());
                }
                if (!(student.getMotherContact2() == null || student.getMotherContact2().isEmpty())) {
                    motherContactNo.append(", ").append(student.getMotherContact2());
                }
                studentVO.setStudentContactNo(studentContactNo.toString());
                studentVO.setFatherContactNo(fatherContactNo.toString());
                studentVO.setMotherContactNo(motherContactNo.toString());
                StringBuffer address = new StringBuffer("");
                if (student.getAddresses() != null) {
                    for (Address add : student.getAddresses()) {
                        if (Address.ADDRESS_TYPE_LOCAL.equals(add.getAddressOf())) {
                            if (!(add.getLine1() == null || add.getLine1().isEmpty())) {
                                address.append(add.getLine1()).append(" , ");
                            }
                            if (!(add.getLine2() == null || add.getLine2().isEmpty())) {
                                address.append(add.getLine2()).append(" , ");
                            }
                            address.append(add.getCity());
                            studentVO.setCity(add.getCity());
                        }
                    }
                }
                studentVO.setFullAddress(address.toString());
                if (student.getLastRegistrationDate() != null) {
                    studentVO.setAdmissionRegistrationDate(DateUtil.formatDate(student.getLastRegistrationDate(), "dd-MMM-yyyy"));
                }
                if (student.getAdmissionConfirmationDate() != null) {
                    studentVO.setAdmissionConfirmationDate(DateUtil.formatDate(student.getAdmissionConfirmationDate(), "dd-MMM-yyyy"));
                }
                studentVO.setAdmissionFormNo(student.getAdmissionFormNo());
                if (student.getDob() != null) {
                    studentVO.setDob(DateUtil.formatDate(student.getDob(), "dd-MMM-yyyy"));
                }
                if (student.getClassSection() != null) {
                    studentVO.setClassSection(student.getClassSection().getSectionName());
                }
                if (student.getStudentStatus() != null) {
                    studentVO.setStudentStatus(student.getStudentStatus().getName());
                }
                if (student.getBusStop() != null) {
                    studentVO.setBusStopId(student.getBusStop().getId());
                    studentVO.setBusStop(student.getBusStop().getName());
                }
                if (student.getAdmissionScheme() != null) {
                    studentVO.setAdmissionScheme(student.getAdmissionScheme().getName());
                }
                if (!(student.getEnrollmentNo() == null || student.getEnrollmentNo().isEmpty())) {
                    studentVO.setEnrollmentNo(student.getEnrollmentNo());
                }
                studentVOs.add(studentVO);
            }
        }
        return studentVOs;
    }
}
