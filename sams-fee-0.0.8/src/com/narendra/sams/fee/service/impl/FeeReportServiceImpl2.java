package com.narendra.sams.fee.service.impl;

import com.narendra.sams.admission.domain.Address;
import com.narendra.sams.admission.domain.BusStopWiseFee;
import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.CourseDiscount;
import com.narendra.sams.admission.domain.CourseYearFeeSummary;
import com.narendra.sams.admission.domain.CourseYearwiseAdmissionCount;
import com.narendra.sams.admission.domain.CourseYearwiseDepositFee;
import com.narendra.sams.admission.domain.CourseYearwiseDiscount;
import com.narendra.sams.admission.domain.CourseYearwiseFee;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentFee;
import com.narendra.sams.admission.service.AdmissionListService;
import com.narendra.sams.admission.service.FeeAdjustedStudentService;
import com.narendra.sams.core.dao.AcademicYearBusFeeDAO;
import com.narendra.sams.core.dao.AcademicYearSettingDAO;
import com.narendra.sams.core.domain.AcademicYearAdmissionScheme;
import com.narendra.sams.core.domain.AcademicYearFeeInstallment;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusFeeDetail;
import com.narendra.sams.core.domain.BusFeeInstallment;
import com.narendra.sams.core.domain.CourseYearSetting;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.service.AcademicYearAdmissionSchemeService;
import com.narendra.sams.core.service.AcademicYearFeeService;
import com.narendra.sams.core.util.FeeDiscountCalculator;
import com.narendra.sams.fee.dao.FeeReportDAO;
import com.narendra.sams.fee.service.CustomizeStudentFeeService;
import com.narendra.sams.fee.service.FeeReportService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FeeReportServiceImpl2 implements FeeReportService {
    private AcademicYearAdmissionSchemeService academicYearAdmissionSchemeService;
    private AcademicYearBusFeeDAO academicYearBusFeeDAO;
    private AcademicYearFeeService academicYearFeeService;
    private AcademicYearSettingDAO academicYearSettingDAO;
    private AdmissionListService admissionListService;
    private CustomizeStudentFeeService customizeStudentFeeService;
    private FeeAdjustedStudentService feeAdjustedStudentService;
    private FeeReportDAO feeReportDAO;

    public AcademicYearFeeService getAcademicYearFeeService() {
        return this.academicYearFeeService;
    }

    public void setAcademicYearFeeService(AcademicYearFeeService academicYearFeeService) {
        this.academicYearFeeService = academicYearFeeService;
    }

    public CustomizeStudentFeeService getCustomizeStudentFeeService() {
        return this.customizeStudentFeeService;
    }

    public void setCustomizeStudentFeeService(CustomizeStudentFeeService customizeStudentFeeService) {
        this.customizeStudentFeeService = customizeStudentFeeService;
    }

    public FeeReportDAO getFeeReportDAO() {
        return this.feeReportDAO;
    }

    public void setFeeReportDAO(FeeReportDAO feeReportDAO) {
        this.feeReportDAO = feeReportDAO;
    }

    public AcademicYearBusFeeDAO getAcademicYearBusFeeDAO() {
        return this.academicYearBusFeeDAO;
    }

    public void setAcademicYearBusFeeDAO(AcademicYearBusFeeDAO academicYearBusFeeDAO) {
        this.academicYearBusFeeDAO = academicYearBusFeeDAO;
    }

    public AcademicYearSettingDAO getAcademicYearSettingDAO() {
        return this.academicYearSettingDAO;
    }

    public void setAcademicYearSettingDAO(AcademicYearSettingDAO academicYearSettingDAO) {
        this.academicYearSettingDAO = academicYearSettingDAO;
    }

    public AdmissionListService getAdmissionListService() {
        return this.admissionListService;
    }

    public void setAdmissionListService(AdmissionListService admissionListService) {
        this.admissionListService = admissionListService;
    }

    public AcademicYearAdmissionSchemeService getAcademicYearAdmissionSchemeService() {
        return this.academicYearAdmissionSchemeService;
    }

    public void setAcademicYearAdmissionSchemeService(AcademicYearAdmissionSchemeService academicYearAdmissionSchemeService) {
        this.academicYearAdmissionSchemeService = academicYearAdmissionSchemeService;
    }

    public FeeAdjustedStudentService getFeeAdjustedStudentService() {
        return this.feeAdjustedStudentService;
    }

    public void setFeeAdjustedStudentService(FeeAdjustedStudentService feeAdjustedStudentService) {
        this.feeAdjustedStudentService = feeAdjustedStudentService;
    }

    public List<StudentFee> getDueStudents(Long academicYearId, Long courseYearId, Date dueDate, Long studentStatus) {
        if (courseYearId == null) {
            return null;
        }
        Map<String, StudentFee> studentFeesMap = null;
        List<StudentFee> studentFees = null;
        List<String> adminStudents = null;
        List<String> custStudents = null;
        BusFeeInstallment busFeeInstallment = this.academicYearBusFeeDAO.getBusFeeInstallment(academicYearId);
        List<AcademicYearFeeInstallment> newAdmissionDueFeeInstallments = this.academicYearFeeService.getDueFeeInstallments(academicYearId, courseYearId, AdmissionType.NEW_ADMISSION_ID, dueDate);
        List<AcademicYearFeeInstallment> regAdmissionDueFeeInstallments = this.academicYearFeeService.getDueFeeInstallments(academicYearId, courseYearId, AdmissionType.REGULAR_ADMISSION_ID, dueDate);
        List<Student> students = this.admissionListService.getStudents(academicYearId, courseYearId, studentStatus);
        if (!(students == null || students.isEmpty())) {
            studentFeesMap = new HashMap();
            adminStudents = new ArrayList();
            for (Student student : students) {
                StudentFee studentFee = new StudentFee();
                studentFee.setStudentDbId(student.getId());
                studentFee.setStudentId(student.getStudentId());
                studentFee.setStudentFirstName(student.getFirstName());
                studentFee.setStudentMiddleName(student.getMiddleName());
                studentFee.setStudentLastName(student.getLastName());
                studentFee.setAdmissionType(student.getAcademicYearAdmissionType(academicYearId));
                studentFee.setAdmissionScheme(student.getAcademicYearAdmissionScheme(academicYearId));
                studentFee.setBusStop(student.getAcademicYearBusStop(academicYearId));
                studentFee.setFatherName(student.getFatherName());
                studentFee.setStudentContactNo1(student.getPhone1());
                studentFee.setStudentContactNo2(student.getPhone2());
                studentFee.setFatherContactNo1(student.getFatherContact1());
                studentFee.setFatherContactNo2(student.getFatherContact2());
                studentFee.setMotherContactNo1(student.getMotherContact1());
                studentFee.setMotherContactNo2(student.getMotherContact2());
                StringBuffer stringBuffer = new StringBuffer("");
                if (student.getAddresses() != null) {
                    for (Address add : student.getAddresses()) {
                        if (Address.ADDRESS_TYPE_LOCAL.equals(add.getAddressOf())) {
                            if (!(add.getLine1() == null || add.getLine1().isEmpty())) {
                                stringBuffer.append(add.getLine1()).append(" , ");
                            }
                            if (!(add.getLine2() == null || add.getLine2().isEmpty())) {
                                stringBuffer.append(add.getLine2()).append(" , ");
                            }
                            stringBuffer.append(add.getCity());
                        }
                    }
                }
                studentFee.setAddresss(stringBuffer.toString());
                studentFee.setClassHistory(student.getActiveClassHistory(academicYearId));
                studentFeesMap.put(studentFee.getStudentId(), studentFee);
                adminStudents.add(studentFee.getStudentId());
            }
        }
        if (studentFeesMap == null) {
            return null;
        }
        List<Student> cStudents = this.feeReportDAO.getCustomizedStudent(academicYearId, courseYearId, studentStatus);
        if (cStudents != null) {
            custStudents = new ArrayList();
            for (Student student2 : cStudents) {
                adminStudents.remove(student2.getStudentId());
                StudentFee studentFee = (StudentFee) studentFeesMap.get(student2.getStudentId());
                if (studentFee != null) {
                    studentFee.setIsFeeCustomized(Boolean.TRUE);
                }
                custStudents.add(student2.getStudentId());
            }
        }
        List<StudentFee> customizedStudents = this.feeReportDAO.getCustomizedStudentFees(academicYearId, courseYearId, dueDate, studentStatus);
        if (!(customizedStudents == null || customizedStudents.isEmpty())) {
            for (StudentFee customizeStudentFee : customizedStudents) {
                if (customizeStudentFee.getLatestDateDate() != null) {
                	StudentFee studentFee = (StudentFee) studentFeesMap.get(customizeStudentFee.getStudentId());
                    if (studentFee != null) {
                        studentFee.setCustomizedFee(customizeStudentFee.getCustomizedFee());
                        studentFee.setBusStop(null);
                    }
                }
            }
        }
        if (!(adminStudents == null || adminStudents.isEmpty())) {
            List<StudentFee> adminPaidInDueDate = this.feeReportDAO.getPaidFeeByStudents(adminStudents, academicYearId, courseYearId, dueDate, Boolean.FALSE, null, Boolean.FALSE);
            if (adminPaidInDueDate != null) {
                for (StudentFee fee : adminPaidInDueDate) {
                	StudentFee  studentFee = (StudentFee) studentFeesMap.get(fee.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidFee(fee.getFeePlaceHolder());
                    }
                }
            }
            List<StudentFee> adminPaidAdvance = this.feeReportDAO.getPaidFeeByStudents(adminStudents, academicYearId, courseYearId, dueDate, Boolean.FALSE, null, Boolean.TRUE);
            if (adminPaidAdvance != null) {
                for (StudentFee fee2 : adminPaidAdvance) {
                	StudentFee  studentFee = (StudentFee) studentFeesMap.get(fee2.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidFeeInAdvance(fee2.getFeePlaceHolder());
                    }
                }
            }
            List<StudentFee> adminPaidLateFees = this.feeReportDAO.getPaidFeeByStudents(adminStudents, academicYearId, courseYearId, dueDate, Boolean.FALSE, Boolean.TRUE, null);
            if (adminPaidLateFees != null) {
                for (StudentFee fee22 : adminPaidLateFees) {
                	StudentFee studentFee = (StudentFee) studentFeesMap.get(fee22.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidLateFee(fee22.getFeePlaceHolder());
                    }
                }
            }
        }
        if (customizedStudents != null) {
            List<StudentFee> custPaidInDueDate = this.feeReportDAO.getPaidFeeByStudents(custStudents, academicYearId, courseYearId, dueDate, Boolean.TRUE, null, Boolean.FALSE);
            if (custPaidInDueDate != null) {
                for (StudentFee fee222 : custPaidInDueDate) {
                	StudentFee studentFee = (StudentFee) studentFeesMap.get(fee222.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidFee(fee222.getFeePlaceHolder());
                    }
                }
            }
            List<StudentFee> custPaidAdvance = this.feeReportDAO.getPaidFeeByStudents(custStudents, academicYearId, courseYearId, dueDate, Boolean.TRUE, null, Boolean.TRUE);
            if (custPaidAdvance != null) {
                for (StudentFee fee2222 : custPaidAdvance) {
                	StudentFee studentFee = (StudentFee) studentFeesMap.get(fee2222.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidFeeInAdvance(fee2222.getFeePlaceHolder());
                    }
                }
            }
            List<StudentFee> custPaidLateFees = this.feeReportDAO.getPaidFeeByStudents(custStudents, academicYearId, courseYearId, dueDate, Boolean.TRUE, Boolean.TRUE, null);
            if (custPaidLateFees != null) {
                for (StudentFee fee22222 : custPaidLateFees) {
                	StudentFee studentFee = (StudentFee) studentFeesMap.get(fee22222.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidLateFee(fee22222.getFeePlaceHolder());
                    }
                }
            }
        }
        List<Long> courseYears = new ArrayList();
        courseYears.add(courseYearId);
        List<CourseYearwiseFee> courseYearwiseFees = getCourseYearwiseDueFee(academicYearId, courseYears, dueDate);
        CourseYearwiseFee courseFee = null;
        if (courseYearwiseFees != null && courseYearwiseFees.size() == 1) {
            courseFee = (CourseYearwiseFee) courseYearwiseFees.get(0);
        }
        if (studentFeesMap != null) {
            List<StudentFee> arrayList = new ArrayList(studentFeesMap.values());
        }
        List<BusFee> busFees = this.academicYearBusFeeDAO.getAssigedBusFee(academicYearId);
        if (courseFee == null) {
            return studentFees;
        }
        for (StudentFee studentFee2 : studentFees) {
            if (studentFee2.getIsFeeCustomized() == null) {
                List<AcademicYearFeeInstallment> academicYearFeeInstallments;
                AcademicYearAdmissionScheme academicYearAdmissionScheme = null;
                if (studentFee2.getAdmissionScheme() != null) {
                    academicYearAdmissionScheme = this.academicYearAdmissionSchemeService.getAcademicYearAdmissionScheme(academicYearId, studentFee2.getAdmissionScheme().getId());
                }
                if (AdmissionType.NEW_ADMISSION_ID.equals(studentFee2.getAdmissionType().getId())) {
                    studentFee2.setAdminFee(Long.valueOf(courseFee.getNewAdmissionFee()));
                } else if (AdmissionType.REGULAR_ADMISSION_ID.equals(studentFee2.getAdmissionType().getId())) {
                    studentFee2.setAdminFee(Long.valueOf(courseFee.getRegularAdmissionFee()));
                }
                StudentFee studentFee3;
                if (BusFeeInstallment.SETUP_TYPE_PERCENTAGE.equals(busFeeInstallment.getSetupType())) {
                    long feePercentage = this.feeReportDAO.getBusFeePercentage(academicYearId, courseYearId, studentFee2.getAdmissionType().getId(), dueDate);
                    if (!(busFees == null || busFees.isEmpty() || studentFee2.getBusStop() == null)) {
                        for (BusFee busFee : busFees) {
                            if (busFee.getBusStop().getId().equals(studentFee2.getBusStop().getId())) {
                                if (busFee.getRs() != null) {
                                    studentFee2.setBusFee(Long.valueOf((busFee.getRs().longValue() * feePercentage) / 100));
                                }
                                studentFee3 = studentFee2;
                                studentFee3.setBusFee(Long.valueOf(studentFee2.getBusFee().longValue() - FeeDiscountCalculator.calculateAdmissionSchemeDiscountOnBusFee(academicYearAdmissionScheme, studentFee2.getBusFee()).longValue()));
                            }
                        }
                    }
                } else if (!(!BusFeeInstallment.SETUP_TYPE_MANUAL.equals(busFeeInstallment.getSetupType()) || busFees == null || busFees.isEmpty() || studentFee2.getBusStop() == null)) {
                    for (BusFee busFee2 : busFees) {
                        if (studentFee2.getBusStop().getId().equals(busFee2.getBusStop().getId())) {
                            Long busFeeTotal = Long.valueOf(0);
                            academicYearFeeInstallments = null;
                            if (AdmissionType.NEW_ADMISSION_ID.equals(studentFee2.getAdmissionType().getId())) {
                                academicYearFeeInstallments = newAdmissionDueFeeInstallments;
                            } else if (AdmissionType.REGULAR_ADMISSION_ID.equals(studentFee2.getAdmissionType().getId())) {
                                academicYearFeeInstallments = regAdmissionDueFeeInstallments;
                            }
                            for (AcademicYearFeeInstallment academicYearFeeInstallment : academicYearFeeInstallments) {
                                for (BusFeeDetail busFeeDetail : busFee2.getBusFeeDetails()) {
                                    if (academicYearFeeInstallment.getInstallment().getId().equals(busFeeDetail.getInstallment().getId())) {
                                        if (busFeeDetail.getBusFee() != null) {
                                            busFeeTotal = Long.valueOf(busFeeTotal.longValue() + busFeeDetail.getFee().longValue());
                                        }
                                    }
                                }
                            }
                            studentFee2.setBusFee(busFeeTotal);
                            studentFee3 = studentFee2;
                            studentFee3.setBusFee(Long.valueOf(studentFee2.getBusFee().longValue() - FeeDiscountCalculator.calculateAdmissionSchemeDiscountOnBusFee(academicYearAdmissionScheme, studentFee2.getBusFee()).longValue()));
                        }
                    }
                }
                if (studentFee2.getAdmissionScheme() != null) {
                    long discount = 0;
                    academicYearFeeInstallments = null;
                    if (AdmissionType.NEW_ADMISSION_ID.equals(studentFee2.getAdmissionType().getId())) {
                        academicYearFeeInstallments = newAdmissionDueFeeInstallments;
                    } else if (AdmissionType.REGULAR_ADMISSION_ID.equals(studentFee2.getAdmissionType().getId())) {
                        academicYearFeeInstallments = regAdmissionDueFeeInstallments;
                    }
                    for (AcademicYearFeeInstallment academicYearFeeInstallment2 : academicYearFeeInstallments) {
                        discount += FeeDiscountCalculator.calculateAdmissionSchemeDiscount(academicYearAdmissionScheme, academicYearFeeInstallment2).longValue();
                    }
                    studentFee2.setAdminFee(Long.valueOf(studentFee2.getAdminFee().longValue() - discount));
                }
            }
        }
        return studentFees;
    }

    private List<StudentFee> prepareDueFeeStudents(List<Student> students, Long academicYearId, Long studentStatus, Date dueDate) {
        BusFeeInstallment busFeeInstallment = this.academicYearBusFeeDAO.getBusFeeInstallment(academicYearId);
        Map<String, StudentFee> studentFeesMap = null;
        List<StudentFee> studentFees = null;
        List<String> adminStudents = null;
        List<String> custStudents = null;
        if (!(students == null || students.isEmpty())) {
            studentFeesMap = new HashMap();
            adminStudents = new ArrayList();
            for (Student student : students) {
                StudentFee studentFee = new StudentFee();
                studentFee.setStudentDbId(student.getId());
                studentFee.setStudentId(student.getStudentId());
                studentFee.setStudentFirstName(student.getFirstName());
                studentFee.setStudentMiddleName(student.getMiddleName());
                studentFee.setStudentLastName(student.getLastName());
                studentFee.setAdmissionType(student.getAcademicYearAdmissionType(academicYearId));
                studentFee.setBusStop(student.getAcademicYearBusStop(academicYearId));
                studentFee.setFatherName(student.getFatherName());
                studentFee.setStudentContactNo1(student.getPhone1());
                studentFee.setStudentContactNo2(student.getPhone2());
                studentFee.setFatherContactNo1(student.getFatherContact1());
                studentFee.setFatherContactNo2(student.getFatherContact2());
                studentFee.setMotherContactNo1(student.getMotherContact1());
                studentFee.setMotherContactNo2(student.getMotherContact2());
                StringBuffer stringBuffer = new StringBuffer("");
                if (student.getAddresses() != null) {
                    for (Address add : student.getAddresses()) {
                        if (Address.ADDRESS_TYPE_LOCAL.equals(add.getAddressOf())) {
                            if (!(add.getLine1() == null || add.getLine1().isEmpty())) {
                                stringBuffer.append(add.getLine1()).append(" , ");
                            }
                            if (!(add.getLine2() == null || add.getLine2().isEmpty())) {
                                stringBuffer.append(add.getLine2()).append(" , ");
                            }
                            stringBuffer.append(add.getCity());
                        }
                    }
                }
                studentFee.setAddresss(stringBuffer.toString());
                studentFee.setClassHistory(student.getActiveClassHistory(academicYearId));
                studentFeesMap.put(studentFee.getStudentId(), studentFee);
                adminStudents.add(studentFee.getStudentId());
            }
        }
        List<Student> cStudents = this.feeReportDAO.getFeeAdjustedStudents(academicYearId, studentStatus);
        if (cStudents != null) {
            custStudents = new ArrayList();
            for (Student student2 : cStudents) {
                adminStudents.remove(student2.getStudentId());
                StudentFee studentFee = (StudentFee) studentFeesMap.get(student2.getStudentId());
                if (studentFee != null) {
                    studentFee.setIsFeeCustomized(Boolean.TRUE);
                }
                custStudents.add(student2.getStudentId());
            }
        }
        List<StudentFee> customizedStudents = this.feeReportDAO.getCustomizedStudentFees(academicYearId, dueDate, studentStatus);
        if (!(customizedStudents == null || customizedStudents.isEmpty())) {
            for (StudentFee customizeStudentFee : customizedStudents) {
                if (customizeStudentFee.getLatestDateDate() != null) {
                	StudentFee studentFee = (StudentFee) studentFeesMap.get(customizeStudentFee.getStudentId());
                    if (studentFee != null) {
                        studentFee.setCustomizedFee(customizeStudentFee.getCustomizedFee());
                        studentFee.setBusStop(null);
                    }
                }
            }
        }
        if (!(adminStudents == null || adminStudents.isEmpty())) {
            List<StudentFee> adminPaidInDueDate = this.feeReportDAO.getPaidFeeByStudents(adminStudents, academicYearId, null, dueDate, Boolean.FALSE, null, Boolean.FALSE);
            if (adminPaidInDueDate != null) {
                for (StudentFee fee : adminPaidInDueDate) {
                	StudentFee studentFee = (StudentFee) studentFeesMap.get(fee.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidFee(fee.getFeePlaceHolder());
                    }
                }
            }
            List<StudentFee> adminPaidAdvance = this.feeReportDAO.getPaidFeeByStudents(adminStudents, academicYearId, null, dueDate, Boolean.FALSE, null, Boolean.TRUE);
            if (adminPaidAdvance != null) {
                for (StudentFee fee2 : adminPaidAdvance) {
                	StudentFee  studentFee = (StudentFee) studentFeesMap.get(fee2.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidFeeInAdvance(fee2.getFeePlaceHolder());
                    }
                }
            }
            List<StudentFee> adminPaidLateFees = this.feeReportDAO.getPaidFeeByStudents(adminStudents, academicYearId, null, dueDate, Boolean.FALSE, Boolean.TRUE, null);
            if (adminPaidLateFees != null) {
                for (StudentFee fee22 : adminPaidLateFees) {
                	StudentFee studentFee = (StudentFee) studentFeesMap.get(fee22.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidLateFee(fee22.getFeePlaceHolder());
                    }
                }
            }
        }
        if (customizedStudents != null) {
            List<StudentFee> custPaidInDueDate = this.feeReportDAO.getPaidFeeByStudents(custStudents, academicYearId, null, dueDate, Boolean.TRUE, null, Boolean.FALSE);
            if (custPaidInDueDate != null) {
                for (StudentFee fee222 : custPaidInDueDate) {
                	StudentFee studentFee = (StudentFee) studentFeesMap.get(fee222.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidFee(fee222.getFeePlaceHolder());
                    }
                }
            }
            List<StudentFee> custPaidAdvance = this.feeReportDAO.getPaidFeeByStudents(custStudents, academicYearId, null, dueDate, Boolean.TRUE, null, Boolean.TRUE);
            if (custPaidAdvance != null) {
                for (StudentFee fee2222 : custPaidAdvance) {
                	StudentFee  studentFee = (StudentFee) studentFeesMap.get(fee2222.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidFeeInAdvance(fee2222.getFeePlaceHolder());
                    }
                }
            }
            List<StudentFee> custPaidLateFees = this.feeReportDAO.getPaidFeeByStudents(custStudents, academicYearId, null, dueDate, Boolean.TRUE, Boolean.TRUE, null);
            if (custPaidLateFees != null) {
                for (StudentFee fee22222 : custPaidLateFees) {
                	StudentFee studentFee = (StudentFee) studentFeesMap.get(fee22222.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidLateFee(fee22222.getFeePlaceHolder());
                    }
                }
            }
        }
        List<CourseYearwiseFee> courseYearwiseFees = getCourseYearwiseDueFee(academicYearId, null, dueDate);
        if (studentFeesMap != null) {
            List<StudentFee> arrayList = new ArrayList(studentFeesMap.values());
        }
        List<BusFee> busFees = this.academicYearBusFeeDAO.getAssigedBusFee(academicYearId);
        if (courseYearwiseFees != null) {
            for (StudentFee studentFee2 : studentFees) {
                if (studentFee2.getIsFeeCustomized() == null) {
                    CourseYearwiseFee courseFee = null;
                    for (CourseYearwiseFee courseYearwiseFee : courseYearwiseFees) {
                        if (studentFee2.getClassHistory().getActiveClass().booleanValue() && studentFee2.getClassHistory().getAcademicYear().getId().equals(courseYearwiseFee.getAcademicYearId()) && studentFee2.getClassHistory().getAcademicYearClass().getCourseYear().getCourse().getId().equals(courseYearwiseFee.getCourse().getId()) && studentFee2.getClassHistory().getAcademicYearClass().getCourseYear().getId().equals(courseYearwiseFee.getCourseYear().getId())) {
                            courseFee = courseYearwiseFee;
                            break;
                        }
                    }
                    if (courseFee != null) {
                        if (AdmissionType.NEW_ADMISSION_ID.equals(studentFee2.getAdmissionType().getId())) {
                            studentFee2.setAdminFee(Long.valueOf(courseFee.getNewAdmissionFee()));
                        } else if (AdmissionType.REGULAR_ADMISSION_ID.equals(studentFee2.getAdmissionType().getId())) {
                            studentFee2.setAdminFee(Long.valueOf(courseFee.getRegularAdmissionFee()));
                        }
                        if (BusFeeInstallment.SETUP_TYPE_PERCENTAGE.equals(busFeeInstallment.getSetupType())) {
                            long feePercentage = this.feeReportDAO.getBusFeePercentage(academicYearId, courseFee.getCourseYear().getId(), studentFee2.getAdmissionType().getId(), dueDate);
                            if (!(busFees == null || busFees.isEmpty() || studentFee2.getBusStop() == null)) {
                                for (BusFee busFee : busFees) {
                                    if (busFee.getBusStop().getId().equals(studentFee2.getBusStop().getId()) && busFee.getRs() != null) {
                                        studentFee2.setBusFee(Long.valueOf((busFee.getRs().longValue() * feePercentage) / 100));
                                    }
                                }
                            }
                        } else if (BusFeeInstallment.SETUP_TYPE_MANUAL.equals(busFeeInstallment.getSetupType())) {
                            List<AcademicYearFeeInstallment> newAdmissionDueFeeInstallments = this.academicYearFeeService.getDueFeeInstallments(academicYearId, courseFee.getCourseYear().getId(), AdmissionType.NEW_ADMISSION_ID, dueDate);
                            List<AcademicYearFeeInstallment> regAdmissionDueFeeInstallments = this.academicYearFeeService.getDueFeeInstallments(academicYearId, courseFee.getCourseYear().getId(), AdmissionType.REGULAR_ADMISSION_ID, dueDate);
                            if (!(busFees == null || busFees.isEmpty() || studentFee2.getBusStop() == null)) {
                                for (BusFee busFee2 : busFees) {
                                    if (studentFee2.getBusStop().getId().equals(busFee2.getBusStop().getId())) {
                                        Long busFeeTotal;
                                        if (AdmissionType.NEW_ADMISSION_ID.equals(studentFee2.getAdmissionType().getId())) {
                                            busFeeTotal = Long.valueOf(0);
                                            for (AcademicYearFeeInstallment academicYearFeeInstallment : newAdmissionDueFeeInstallments) {
                                                for (BusFeeDetail busFeeDetail : busFee2.getBusFeeDetails()) {
                                                    if (academicYearFeeInstallment.getInstallment().getId().equals(busFeeDetail.getInstallment().getId())) {
                                                        if (busFeeDetail.getBusFee() != null) {
                                                            busFeeTotal = Long.valueOf(busFeeTotal.longValue() + busFeeDetail.getFee().longValue());
                                                        }
                                                    }
                                                }
                                            }
                                            studentFee2.setBusFee(busFeeTotal);
                                        } else if (AdmissionType.REGULAR_ADMISSION_ID.equals(studentFee2.getAdmissionType().getId())) {
                                            busFeeTotal = Long.valueOf(0);
                                            for (AcademicYearFeeInstallment academicYearFeeInstallment2 : regAdmissionDueFeeInstallments) {
                                                for (BusFeeDetail busFeeDetail2 : busFee2.getBusFeeDetails()) {
                                                    if (academicYearFeeInstallment2.getInstallment().getId().equals(busFeeDetail2.getInstallment().getId())) {
                                                        if (busFeeDetail2.getBusFee() != null) {
                                                            busFeeTotal = Long.valueOf(busFeeTotal.longValue() + busFeeDetail2.getFee().longValue());
                                                        }
                                                    }
                                                }
                                            }
                                            studentFee2.setBusFee(busFeeTotal);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return studentFees;
    }

    public List<StudentFee> getDueStudents(Long academicYearId, Date dueDate, Long studentStatus) {
        if (academicYearId == null) {
            return null;
        }
        BusFeeInstallment busFeeInstallment = this.academicYearBusFeeDAO.getBusFeeInstallment(academicYearId);
        Map<String, StudentFee> studentFeesMap = null;
        List<StudentFee> studentFees = null;
        List<String> adminStudents = null;
        List<String> custStudents = null;
        List<Student> students = this.admissionListService.getStudents(academicYearId, studentStatus);
        if (!(students == null || students.isEmpty())) {
            studentFeesMap = new HashMap();
            adminStudents = new ArrayList();
            for (Student student : students) {
                StudentFee studentFee = new StudentFee();
                studentFee.setStudentDbId(student.getId());
                studentFee.setStudentId(student.getStudentId());
                studentFee.setStudentFirstName(student.getFirstName());
                studentFee.setStudentMiddleName(student.getMiddleName());
                studentFee.setStudentLastName(student.getLastName());
                studentFee.setAdmissionType(student.getAcademicYearAdmissionType(academicYearId));
                studentFee.setBusStop(student.getAcademicYearBusStop(academicYearId));
                studentFee.setAdmissionScheme(student.getAcademicYearAdmissionScheme(academicYearId));
                studentFee.setFatherName(student.getFatherName());
                studentFee.setStudentContactNo1(student.getPhone1());
                studentFee.setStudentContactNo2(student.getPhone2());
                studentFee.setFatherContactNo1(student.getFatherContact1());
                studentFee.setFatherContactNo2(student.getFatherContact2());
                studentFee.setMotherContactNo1(student.getMotherContact1());
                studentFee.setMotherContactNo2(student.getMotherContact2());
                StringBuffer stringBuffer = new StringBuffer("");
                if (student.getAddresses() != null) {
                    for (Address add : student.getAddresses()) {
                        if (Address.ADDRESS_TYPE_LOCAL.equals(add.getAddressOf())) {
                            if (!(add.getLine1() == null || add.getLine1().isEmpty())) {
                                stringBuffer.append(add.getLine1()).append(" , ");
                            }
                            if (!(add.getLine2() == null || add.getLine2().isEmpty())) {
                                stringBuffer.append(add.getLine2()).append(" , ");
                            }
                            stringBuffer.append(add.getCity());
                        }
                    }
                }
                studentFee.setAddresss(stringBuffer.toString());
                studentFee.setClassHistory(student.getActiveClassHistory(academicYearId));
                studentFeesMap.put(studentFee.getStudentId(), studentFee);
                adminStudents.add(studentFee.getStudentId());
            }
        }
        if (studentFeesMap == null) {
            return null;
        }
        List<Student> cStudents = this.feeReportDAO.getFeeAdjustedStudents(academicYearId, studentStatus);
        if (cStudents != null) {
            custStudents = new ArrayList();
            for (Student student2 : cStudents) {
                adminStudents.remove(student2.getStudentId());
                StudentFee studentFee = (StudentFee) studentFeesMap.get(student2.getStudentId());
                if (studentFee != null) {
                    studentFee.setIsFeeCustomized(Boolean.TRUE);
                }
                custStudents.add(student2.getStudentId());
            }
        }
        List<StudentFee> customizedStudents = this.feeReportDAO.getCustomizedStudentFees(academicYearId, dueDate, studentStatus);
        if (!(customizedStudents == null || customizedStudents.isEmpty())) {
            for (StudentFee customizeStudentFee : customizedStudents) {
                if (customizeStudentFee.getLatestDateDate() != null) {
                	StudentFee studentFee = (StudentFee) studentFeesMap.get(customizeStudentFee.getStudentId());
                    if (studentFee != null) {
                        studentFee.setCustomizedFee(customizeStudentFee.getCustomizedFee());
                        studentFee.setBusStop(null);
                    }
                }
            }
        }
        if (!(adminStudents == null || adminStudents.isEmpty())) {
            List<StudentFee> adminPaidInDueDate = this.feeReportDAO.getPaidFeeByStudents(adminStudents, academicYearId, null, dueDate, Boolean.FALSE, null, Boolean.FALSE);
            if (adminPaidInDueDate != null) {
                for (StudentFee fee : adminPaidInDueDate) {
                	StudentFee  studentFee = (StudentFee) studentFeesMap.get(fee.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidFee(fee.getFeePlaceHolder());
                    }
                }
            }
            List<StudentFee> adminPaidAdvance = this.feeReportDAO.getPaidFeeByStudents(adminStudents, academicYearId, null, dueDate, Boolean.FALSE, null, Boolean.TRUE);
            if (adminPaidAdvance != null) {
                for (StudentFee fee2 : adminPaidAdvance) {
                	StudentFee studentFee = (StudentFee) studentFeesMap.get(fee2.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidFeeInAdvance(fee2.getFeePlaceHolder());
                    }
                }
            }
            List<StudentFee> adminPaidLateFees = this.feeReportDAO.getPaidFeeByStudents(adminStudents, academicYearId, null, dueDate, Boolean.FALSE, Boolean.TRUE, null);
            if (adminPaidLateFees != null) {
                for (StudentFee fee22 : adminPaidLateFees) {
                	StudentFee studentFee = (StudentFee) studentFeesMap.get(fee22.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidLateFee(fee22.getFeePlaceHolder());
                    }
                }
            }
        }
        if (customizedStudents != null) {
            List<StudentFee> custPaidInDueDate = this.feeReportDAO.getPaidFeeByStudents(custStudents, academicYearId, null, dueDate, Boolean.TRUE, null, Boolean.FALSE);
            if (custPaidInDueDate != null) {
                for (StudentFee fee222 : custPaidInDueDate) {
                	StudentFee  studentFee = (StudentFee) studentFeesMap.get(fee222.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidFee(fee222.getFeePlaceHolder());
                    }
                }
            }
            List<StudentFee> custPaidAdvance = this.feeReportDAO.getPaidFeeByStudents(custStudents, academicYearId, null, dueDate, Boolean.TRUE, null, Boolean.TRUE);
            if (custPaidAdvance != null) {
                for (StudentFee fee2222 : custPaidAdvance) {
                	StudentFee  studentFee = (StudentFee) studentFeesMap.get(fee2222.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidFeeInAdvance(fee2222.getFeePlaceHolder());
                    }
                }
            }
            List<StudentFee> custPaidLateFees = this.feeReportDAO.getPaidFeeByStudents(custStudents, academicYearId, null, dueDate, Boolean.TRUE, Boolean.TRUE, null);
            if (custPaidLateFees != null) {
                for (StudentFee fee22222 : custPaidLateFees) {
                	StudentFee studentFee = (StudentFee) studentFeesMap.get(fee22222.getStudentId());
                    if (studentFee != null) {
                        studentFee.setPaidLateFee(fee22222.getFeePlaceHolder());
                    }
                }
            }
        }
        List<CourseYearwiseFee> courseYearwiseFees = getCourseYearwiseDueFee(academicYearId, null, dueDate);
        if (studentFeesMap != null) {
            List<StudentFee> arrayList = new ArrayList(studentFeesMap.values());
        }
        List<BusFee> busFees = this.academicYearBusFeeDAO.getAssigedBusFee(academicYearId);
        if (courseYearwiseFees == null) {
            return studentFees;
        }
        for (StudentFee studentFee2 : studentFees) {
            if (studentFee2.getStudentDbId().longValue() == 576) {
                System.out.println("Helloo..");
            }
            if (studentFee2.getIsFeeCustomized() == null) {
                CourseYearwiseFee courseFee = null;
                AcademicYearAdmissionScheme academicYearAdmissionScheme = null;
                if (studentFee2.getAdmissionScheme() != null) {
                    academicYearAdmissionScheme = this.academicYearAdmissionSchemeService.getAcademicYearAdmissionScheme(academicYearId, studentFee2.getAdmissionScheme().getId());
                }
                for (CourseYearwiseFee courseYearwiseFee : courseYearwiseFees) {
                    if (studentFee2.getClassHistory().getActiveClass().booleanValue() && studentFee2.getClassHistory().getAcademicYear().getId().equals(courseYearwiseFee.getAcademicYearId()) && studentFee2.getClassHistory().getAcademicYearClass().getCourseYear().getCourse().getId().equals(courseYearwiseFee.getCourse().getId()) && studentFee2.getClassHistory().getAcademicYearClass().getCourseYear().getId().equals(courseYearwiseFee.getCourseYear().getId())) {
                        courseFee = courseYearwiseFee;
                        break;
                    }
                }
                if (courseFee != null) {
                    List<AcademicYearFeeInstallment> academicYearFeeInstallments;
                    List<AcademicYearFeeInstallment> newAdmissionDueFeeInstallments = this.academicYearFeeService.getDueFeeInstallments(academicYearId, courseFee.getCourseYear().getId(), AdmissionType.NEW_ADMISSION_ID, dueDate);
                    List<AcademicYearFeeInstallment> regAdmissionDueFeeInstallments = this.academicYearFeeService.getDueFeeInstallments(academicYearId, courseFee.getCourseYear().getId(), AdmissionType.REGULAR_ADMISSION_ID, dueDate);
                    if (AdmissionType.NEW_ADMISSION_ID.equals(studentFee2.getAdmissionType().getId())) {
                        studentFee2.setAdminFee(Long.valueOf(courseFee.getNewAdmissionFee()));
                    } else if (AdmissionType.REGULAR_ADMISSION_ID.equals(studentFee2.getAdmissionType().getId())) {
                        studentFee2.setAdminFee(Long.valueOf(courseFee.getRegularAdmissionFee()));
                    }
                    StudentFee studentFee3;
                    if (BusFeeInstallment.SETUP_TYPE_PERCENTAGE.equals(busFeeInstallment.getSetupType())) {
                        long feePercentage = this.feeReportDAO.getBusFeePercentage(academicYearId, courseFee.getCourseYear().getId(), studentFee2.getAdmissionType().getId(), dueDate);
                        if (!(busFees == null || busFees.isEmpty() || studentFee2.getBusStop() == null)) {
                            for (BusFee busFee : busFees) {
                                if (busFee.getBusStop().getId().equals(studentFee2.getBusStop().getId())) {
                                    if (busFee.getRs() != null) {
                                        studentFee2.setBusFee(Long.valueOf((busFee.getRs().longValue() * feePercentage) / 100));
                                    }
                                    studentFee3 = studentFee2;
                                    studentFee3.setBusFee(Long.valueOf(studentFee2.getBusFee().longValue() - FeeDiscountCalculator.calculateAdmissionSchemeDiscountOnBusFee(academicYearAdmissionScheme, studentFee2.getBusFee()).longValue()));
                                }
                            }
                        }
                    } else if (!(!BusFeeInstallment.SETUP_TYPE_MANUAL.equals(busFeeInstallment.getSetupType()) || busFees == null || busFees.isEmpty() || studentFee2.getBusStop() == null)) {
                        for (BusFee busFee2 : busFees) {
                            if (studentFee2.getBusStop().getId().equals(busFee2.getBusStop().getId())) {
                                academicYearFeeInstallments = null;
                                Long busFeeTotal = Long.valueOf(0);
                                if (AdmissionType.NEW_ADMISSION_ID.equals(studentFee2.getAdmissionType().getId())) {
                                    academicYearFeeInstallments = newAdmissionDueFeeInstallments;
                                } else if (AdmissionType.REGULAR_ADMISSION_ID.equals(studentFee2.getAdmissionType().getId())) {
                                    academicYearFeeInstallments = regAdmissionDueFeeInstallments;
                                }
                                for (AcademicYearFeeInstallment academicYearFeeInstallment : academicYearFeeInstallments) {
                                    for (BusFeeDetail busFeeDetail : busFee2.getBusFeeDetails()) {
                                        if (academicYearFeeInstallment.getInstallment().getId().equals(busFeeDetail.getInstallment().getId())) {
                                            if (busFeeDetail.getBusFee() != null) {
                                                busFeeTotal = Long.valueOf(busFeeTotal.longValue() + busFeeDetail.getFee().longValue());
                                            }
                                        }
                                    }
                                }
                                if (studentFee2.getBusFee().longValue() == 0) {
                                    System.out.println(studentFee2.getBusFee());
                                }
                                studentFee2.setBusFee(busFeeTotal);
                                studentFee3 = studentFee2;
                                studentFee3.setBusFee(Long.valueOf(studentFee2.getBusFee().longValue() - FeeDiscountCalculator.calculateAdmissionSchemeDiscountOnBusFee(academicYearAdmissionScheme, studentFee2.getBusFee()).longValue()));
                            }
                        }
                    }
                    if (studentFee2.getAdmissionScheme() != null) {
                        long discount = 0;
                        academicYearFeeInstallments = null;
                        if (AdmissionType.NEW_ADMISSION_ID.equals(studentFee2.getAdmissionType().getId())) {
                            academicYearFeeInstallments = newAdmissionDueFeeInstallments;
                        } else if (AdmissionType.REGULAR_ADMISSION_ID.equals(studentFee2.getAdmissionType().getId())) {
                            academicYearFeeInstallments = regAdmissionDueFeeInstallments;
                        }
                        for (AcademicYearFeeInstallment academicYearFeeInstallment2 : academicYearFeeInstallments) {
                            discount += FeeDiscountCalculator.calculateAdmissionSchemeDiscount(academicYearAdmissionScheme, academicYearFeeInstallment2).longValue();
                        }
                        studentFee2.setAdminFee(Long.valueOf(studentFee2.getAdminFee().longValue() - discount));
                    }
                }
            }
        }
        return studentFees;
    }

    public List<CourseYearFeeSummary> getDueFeeReport(Long academicYearId, Date dueDate, Long studentStatus) {
        Map<Long, CourseYearFeeSummary> courseYearFeeSummaryMap = new LinkedHashMap();
        List<Long> courseYears = new ArrayList();
        List<CourseYearSetting> courseYearSettings = this.academicYearSettingDAO.getActiveCourseYearSettings(academicYearId);
        if (courseYearSettings != null) {
            for (CourseYearSetting courseYearSetting : courseYearSettings) {
                CourseYearFeeSummary courseYearFeeSummary = new CourseYearFeeSummary();
                courseYearFeeSummary.setCourseYear(courseYearSetting.getCourseYear());
                courseYearFeeSummary.setCourse(courseYearSetting.getCourseYear().getCourse());
                courseYearFeeSummaryMap.put(courseYearFeeSummary.getCourseYear().getId(), courseYearFeeSummary);
                courseYears.add(courseYearFeeSummary.getCourseYear().getId());
            }
        }
        if (courseYears.isEmpty()) {
            return null;
        }
        BusStopWiseFee busStopWiseFee;
        BusStopWiseFee bsf;
        List<CourseYearwiseFee> courseYearwiseFees = this.feeReportDAO.getCourseYearwiseFee(academicYearId, courseYears, dueDate);
        if (courseYearwiseFees != null) {
            for (CourseYearwiseFee courseYearwiseFee : courseYearwiseFees) {
            	CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(courseYearwiseFee.getCourseYear().getId());
                if (courseYearFeeSummary != null) {
                    courseYearFeeSummary.setNewAdmissionFee(courseYearwiseFee.getNewAdmissionFee());
                    courseYearFeeSummary.setRegularAdmissionFee(courseYearwiseFee.getRegularAdmissionFee());
                }
            }
        }
        List<CourseYearwiseAdmissionCount> admissionCounts = this.feeReportDAO.getAdmissionCount(academicYearId, courseYears, studentStatus, dueDate);
        if (admissionCounts != null) {
            for (CourseYearwiseAdmissionCount admissionCount : admissionCounts) {
            	CourseYearFeeSummary   courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(admissionCount.getCourseYear().getId());
                if (courseYearFeeSummary != null) {
                    courseYearFeeSummary.setNewAdmissionCount(admissionCount.getNewAdmissionCount());
                    courseYearFeeSummary.setRegularAdmissionCount(admissionCount.getRegularAdmissionCount());
                }
            }
        }
        List<CourseYearwiseAdmissionCount> customizeAdmissionCounts = this.feeReportDAO.getCustomizeAdmissionCount(academicYearId, courseYears, null, studentStatus);
        if (!(customizeAdmissionCounts == null || customizeAdmissionCounts.isEmpty())) {
            for (CourseYearwiseAdmissionCount customizeAdmission : customizeAdmissionCounts) {
            	CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(customizeAdmission.getCourseYear().getId());
                if (courseYearFeeSummary != null) {
                    if (0 != courseYearFeeSummary.getNewAdmissionCount()) {
                        courseYearFeeSummary.setNewAdmissionCount(courseYearFeeSummary.getNewAdmissionCount() - customizeAdmission.getNewAdmissionCount());
                    }
                    if (0 != courseYearFeeSummary.getRegularAdmissionCount()) {
                        courseYearFeeSummary.setRegularAdmissionCount(courseYearFeeSummary.getRegularAdmissionCount() - customizeAdmission.getRegularAdmissionCount());
                    }
                }
            }
        }
        List<CourseYearwiseAdmissionCount> inCustomizeAdmissionCounts = this.feeReportDAO.getCustomizeAdmissionCount(academicYearId, courseYears, dueDate, studentStatus);
        if (!(inCustomizeAdmissionCounts == null || inCustomizeAdmissionCounts.isEmpty())) {
            for (CourseYearwiseAdmissionCount inAdmissionCount : inCustomizeAdmissionCounts) {
            	CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(inAdmissionCount.getCourseYear().getId());
                if (courseYearFeeSummary != null) {
                    courseYearFeeSummary.setNewAdmissionCustomizeCount(inAdmissionCount.getNewAdmissionCount());
                    courseYearFeeSummary.setNewAdmissionCustomizeFee(inAdmissionCount.getNewAdmissionFee());
                    courseYearFeeSummary.setRegularAdmissionCustomizeCount(inAdmissionCount.getRegularAdmissionCount());
                    courseYearFeeSummary.setRegularAdmissionCustomizeFee(inAdmissionCount.getRegularAdmissionFee());
                }
            }
        }
        List<CourseYearwiseDepositFee> customizedDepositFees = this.feeReportDAO.getCourseYearwiseDepositFee(academicYearId, courseYears, dueDate, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, studentStatus);
        if (!(customizedDepositFees == null || customizedDepositFees.isEmpty())) {
            for (CourseYearwiseDepositFee courseYearwiseDepositFee : customizedDepositFees) {
            	CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(courseYearwiseDepositFee.getCourseYear().getId());
                if (!(courseYearFeeSummary == null || courseYearwiseDepositFee.getDepositedFee() == null)) {
                    courseYearFeeSummary.setDepositedFee(courseYearwiseDepositFee.getDepositedFee().longValue());
                }
            }
        }
        List<CourseYearwiseDepositFee> academicYearDepositFees = this.feeReportDAO.getCourseYearwiseDepositFee(academicYearId, courseYears, dueDate, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, studentStatus);
        if (!(academicYearDepositFees == null || academicYearDepositFees.isEmpty())) {
            for (CourseYearwiseDepositFee courseYearwiseDepositFee2 : academicYearDepositFees) {
            	CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(courseYearwiseDepositFee2.getCourseYear().getId());
                if (!(courseYearFeeSummary == null || courseYearwiseDepositFee2.getDepositedFee() == null)) {
                    courseYearFeeSummary.setDepositedFee(courseYearFeeSummary.getDepositedFee() + courseYearwiseDepositFee2.getDepositedFee().longValue());
                }
            }
        }
        List<CourseYearwiseDepositFee> advanceCustomizedDepositFees = this.feeReportDAO.getCourseYearwiseDepositFee(academicYearId, courseYears, dueDate, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, studentStatus);
        if (!(advanceCustomizedDepositFees == null || advanceCustomizedDepositFees.isEmpty())) {
            for (CourseYearwiseDepositFee courseYearwiseDepositFee22 : advanceCustomizedDepositFees) {
            	CourseYearFeeSummary  courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(courseYearwiseDepositFee22.getCourseYear().getId());
                if (!(courseYearFeeSummary == null || courseYearwiseDepositFee22.getDepositedFee() == null)) {
                    courseYearFeeSummary.setAdvanceDeposited(courseYearFeeSummary.getAdvanceDeposited() + courseYearwiseDepositFee22.getDepositedFee().longValue());
                }
            }
        }
        List<CourseYearwiseDepositFee> advanceAcademicDepositedFees = this.feeReportDAO.getCourseYearwiseDepositFee(academicYearId, courseYears, dueDate, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, studentStatus);
        if (!(advanceAcademicDepositedFees == null || advanceAcademicDepositedFees.isEmpty())) {
            for (CourseYearwiseDepositFee courseYearwiseDepositFee222 : advanceAcademicDepositedFees) {
            	CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(courseYearwiseDepositFee222.getCourseYear().getId());
                if (!(courseYearFeeSummary == null || courseYearwiseDepositFee222.getDepositedFee() == null)) {
                    courseYearFeeSummary.setAdvanceDeposited(courseYearFeeSummary.getAdvanceDeposited() + courseYearwiseDepositFee222.getDepositedFee().longValue());
                }
            }
        }
        List<CourseYearwiseDepositFee> depositedLateFee = this.feeReportDAO.getCourseYearwiseDepositFee(academicYearId, courseYears, null, Boolean.TRUE, null, Boolean.TRUE, studentStatus);
        if (!(depositedLateFee == null || depositedLateFee.isEmpty())) {
            for (CourseYearwiseDepositFee courseYearwiseDepositFee2222 : depositedLateFee) {
            	CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(courseYearwiseDepositFee2222.getCourseYear().getId());
                if (!(courseYearFeeSummary == null || courseYearwiseDepositFee2222.getDepositedFee() == null)) {
                    courseYearFeeSummary.setLateFeeDeposited(courseYearwiseDepositFee2222.getDepositedFee().longValue());
                }
            }
        }
        List<CourseYearwiseDepositFee> noncustDepositedLateFee = this.feeReportDAO.getCourseYearwiseDepositFee(academicYearId, courseYears, null, Boolean.FALSE, null, Boolean.TRUE, studentStatus);
        if (!(noncustDepositedLateFee == null || noncustDepositedLateFee.isEmpty())) {
            for (CourseYearwiseDepositFee courseYearwiseDepositFee22222 : noncustDepositedLateFee) {
            	CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(courseYearwiseDepositFee22222.getCourseYear().getId());
                if (!(courseYearFeeSummary == null || courseYearwiseDepositFee22222.getDepositedFee() == null)) {
                    courseYearFeeSummary.setLateFeeDeposited(courseYearFeeSummary.getLateFeeDeposited() + courseYearwiseDepositFee22222.getDepositedFee().longValue());
                }
            }
        }
        if (courseYears != null) {
            for (Long courseYearId : courseYears) {
                long newAdmissionBusFeePercentage = this.feeReportDAO.getBusFeePercentage(academicYearId, courseYearId, AdmissionType.NEW_ADMISSION_ID, dueDate);
                long regAdmissionBusFeePercentage = this.feeReportDAO.getBusFeePercentage(academicYearId, courseYearId, AdmissionType.REGULAR_ADMISSION_ID, dueDate);
                CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(courseYearId);
                if (courseYearFeeSummary != null) {
                    courseYearFeeSummary.setBusFeePercentage(newAdmissionBusFeePercentage);
                    courseYearFeeSummary.setRegularAdmissBusFeePercentage(regAdmissionBusFeePercentage);
                }
            }
        }
        List<BusStopWiseFee> busStopNewStudents = this.feeReportDAO.getBusStopWiseAllStudents(academicYearId, studentStatus, AdmissionType.NEW_ADMISSION_ID);
        if (!(busStopNewStudents == null || busStopNewStudents.isEmpty())) {
            for (BusStopWiseFee busStopWiseFee2 : busStopNewStudents) {
            	CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(busStopWiseFee2.getCourseYearId());
                if (courseYearFeeSummary != null) {
                    courseYearFeeSummary.getBusStopStudentCountMap().put(busStopWiseFee2.getBusStopId(), busStopWiseFee2);
                }
            }
        }
        List<BusStopWiseFee> busStopRegStudents = this.feeReportDAO.getBusStopWiseAllStudents(academicYearId, studentStatus, AdmissionType.REGULAR_ADMISSION_ID);
        if (!(busStopRegStudents == null || busStopRegStudents.isEmpty())) {
            for (BusStopWiseFee busStopWiseFee22 : busStopRegStudents) {
            	CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(busStopWiseFee22.getCourseYearId());
                if (courseYearFeeSummary != null) {
                    courseYearFeeSummary.getRegStopStudentCountMap().put(busStopWiseFee22.getBusStopId(), busStopWiseFee22);
                }
            }
        }
        List<BusStopWiseFee> custNewStudentsAdoptedBus = this.feeReportDAO.getBusStopWiseCustomizedStudents(academicYearId, studentStatus, AdmissionType.NEW_ADMISSION_ID);
        if (!(custNewStudentsAdoptedBus == null || custNewStudentsAdoptedBus.isEmpty())) {
            for (BusStopWiseFee busStopWiseFee222 : custNewStudentsAdoptedBus) {
            	CourseYearFeeSummary  courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(busStopWiseFee222.getCourseYearId());
                if (courseYearFeeSummary != null) {
                    bsf = (BusStopWiseFee) courseYearFeeSummary.getBusStopStudentCountMap().get(busStopWiseFee222.getBusStopId());
                    if (!(bsf == null || bsf.getStudentCount() == null || 0 == bsf.getStudentCount().longValue())) {
                        bsf.setStudentCount(Long.valueOf(bsf.getStudentCount().longValue() - busStopWiseFee222.getStudentCount().longValue()));
                    }
                }
            }
        }
        List<BusStopWiseFee> custRegStudentsAdoptedBus = this.feeReportDAO.getBusStopWiseCustomizedStudents(academicYearId, studentStatus, AdmissionType.REGULAR_ADMISSION_ID);
        if (!(custRegStudentsAdoptedBus == null || custRegStudentsAdoptedBus.isEmpty())) {
            for (BusStopWiseFee busStopWiseFee2222 : custRegStudentsAdoptedBus) {
            	CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(busStopWiseFee2222.getCourseYearId());
                if (courseYearFeeSummary != null) {
                    bsf = (BusStopWiseFee) courseYearFeeSummary.getRegStopStudentCountMap().get(busStopWiseFee2222.getBusStopId());
                    if (!(bsf == null || bsf.getStudentCount() == null || 0 == bsf.getStudentCount().longValue())) {
                        bsf.setStudentCount(Long.valueOf(bsf.getStudentCount().longValue() - busStopWiseFee2222.getStudentCount().longValue()));
                    }
                }
            }
        }
        List<BusFee> busFees = this.academicYearBusFeeDAO.getAssigedBusFee(academicYearId);
        List<CourseYearFeeSummary> arrayList = new ArrayList(courseYearFeeSummaryMap.values());
        if (busFees == null || busFees.isEmpty()) {
            return arrayList;
        }
        for (BusFee busFee : busFees) {
            for (CourseYearFeeSummary courseYearFeeSummary2 : arrayList) {
            	BusStopWiseFee  busStopWiseFee2222 = (BusStopWiseFee) courseYearFeeSummary2.getBusStopStudentCountMap().get(busFee.getBusStop().getId());
                BusStopWiseFee regBusStopWiseFee = (BusStopWiseFee) courseYearFeeSummary2.getRegStopStudentCountMap().get(busFee.getBusStop().getId());
                if (busStopWiseFee2222 != null) {
                    busStopWiseFee2222.setBusFee(busFee.getRs());
                }
                if (regBusStopWiseFee != null) {
                    regBusStopWiseFee.setBusFee(busFee.getRs());
                }
            }
        }
        return arrayList;
    }

    private List<CourseYearwiseFee> getCourseYearwiseDueFee(Long academicYearId, Collection<Long> courseYears, Date dueDate) {
        return this.feeReportDAO.getCourseYearwiseFee(academicYearId, courseYears, dueDate);
    }

    public List<CourseYearFeeSummary> getAnnualFeeReport(Long academicYearId, Long studnetStatus) {
        Map<Long, CourseYearFeeSummary> courseYearFeeSummaryMap = new LinkedHashMap();
        List<Long> courseYears = new ArrayList();
        List<CourseYearSetting> courseYearSettings = this.academicYearSettingDAO.getActiveCourseYearSettings(academicYearId);
        if (courseYearSettings != null) {
            for (CourseYearSetting courseYearSetting : courseYearSettings) {
                CourseYearFeeSummary courseYearFeeSummary = new CourseYearFeeSummary();
                courseYearFeeSummary.setCourseYear(courseYearSetting.getCourseYear());
                courseYearFeeSummary.setCourse(courseYearSetting.getCourseYear().getCourse());
                courseYearFeeSummaryMap.put(courseYearFeeSummary.getCourseYear().getId(), courseYearFeeSummary);
                courseYears.add(courseYearFeeSummary.getCourseYear().getId());
            }
        }
        if (courseYears.isEmpty()) {
            return null;
        }
        BusStopWiseFee busStopWiseFee;
        List<CourseYearwiseFee> courseYearwiseFees = this.feeReportDAO.getCourseYearwiseFee(academicYearId, courseYears, null);
        if (courseYearwiseFees != null) {
            for (CourseYearwiseFee courseYearwiseFee : courseYearwiseFees) {
            	CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(courseYearwiseFee.getCourseYear().getId());
                if (courseYearFeeSummary != null) {
                    courseYearFeeSummary.setNewAdmissionFee(courseYearwiseFee.getNewAdmissionFee());
                    courseYearFeeSummary.setRegularAdmissionFee(courseYearwiseFee.getRegularAdmissionFee());
                }
            }
        }
        List<CourseYearwiseAdmissionCount> admissionCounts = this.feeReportDAO.getAdmissionCount(academicYearId, courseYears, studnetStatus, null);
        if (admissionCounts != null) {
            for (CourseYearwiseAdmissionCount admissionCount : admissionCounts) {
            	CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(admissionCount.getCourseYear().getId());
                if (courseYearFeeSummary != null) {
                    courseYearFeeSummary.setNewAdmissionCount(admissionCount.getNewAdmissionCount());
                    courseYearFeeSummary.setRegularAdmissionCount(admissionCount.getRegularAdmissionCount());
                }
            }
        }
        List<CourseYearwiseDiscount> discounts = this.feeReportDAO.getCourseYearwiseDiscount(academicYearId, courseYears, studnetStatus);
        if (discounts != null) {
            for (CourseYearwiseDiscount discount : discounts) {
            	CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(discount.getCourseYear().getId());
                if (!(courseYearFeeSummary == null || discount.getDiscountGiven() == null)) {
                    courseYearFeeSummary.setDiscoutGiven(discount.getDiscountGiven().longValue());
                }
            }
        }
        List<CourseYearwiseDepositFee> depositFees = this.feeReportDAO.getCourseYearwiseDepositFee(academicYearId, courseYears, studnetStatus, Boolean.TRUE, Boolean.FALSE);
        if (!(depositFees == null || depositFees.isEmpty())) {
            for (CourseYearwiseDepositFee courseYearwiseDepositFee : depositFees) {
            	CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(courseYearwiseDepositFee.getCourseYear().getId());
                if (!(courseYearFeeSummary == null || courseYearwiseDepositFee.getDepositedFee() == null)) {
                    courseYearFeeSummary.setDepositedFee(courseYearwiseDepositFee.getDepositedFee().longValue());
                }
            }
        }
        List<CourseYearwiseDepositFee> academicYearDepositFees = this.feeReportDAO.getCourseYearwiseDepositFee(academicYearId, courseYears, studnetStatus, Boolean.FALSE, Boolean.FALSE);
        if (!(academicYearDepositFees == null || academicYearDepositFees.isEmpty())) {
            for (CourseYearwiseDepositFee courseYearwiseDepositFee2 : academicYearDepositFees) {
            	CourseYearFeeSummary  courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(courseYearwiseDepositFee2.getCourseYear().getId());
                if (!(courseYearFeeSummary == null || courseYearwiseDepositFee2.getDepositedFee() == null)) {
                    courseYearFeeSummary.setDepositedFee(courseYearFeeSummary.getDepositedFee() + courseYearwiseDepositFee2.getDepositedFee().longValue());
                }
            }
        }
        List<CourseYearwiseDepositFee> cLateFees = this.feeReportDAO.getCourseYearwiseDepositFee(academicYearId, courseYears, studnetStatus, Boolean.TRUE, Boolean.TRUE);
        if (!(cLateFees == null || cLateFees.isEmpty())) {
            for (CourseYearwiseDepositFee lateFee : cLateFees) {
            	CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(lateFee.getCourseYear().getId());
                if (!(courseYearFeeSummary == null || lateFee.getDepositedFee() == null)) {
                    courseYearFeeSummary.setLateFeeDeposited(lateFee.getDepositedFee().longValue());
                }
            }
        }
        List<CourseYearwiseDepositFee> ncLateFees = this.feeReportDAO.getCourseYearwiseDepositFee(academicYearId, courseYears, studnetStatus, Boolean.FALSE, Boolean.TRUE);
        if (!(ncLateFees == null || ncLateFees.isEmpty())) {
            for (CourseYearwiseDepositFee lateFee2 : ncLateFees) {
            	CourseYearFeeSummary courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(lateFee2.getCourseYear().getId());
                if (!(courseYearFeeSummary == null || lateFee2.getDepositedFee() == null)) {
                    courseYearFeeSummary.setLateFeeDeposited(courseYearFeeSummary.getLateFeeDeposited() + lateFee2.getDepositedFee().longValue());
                }
            }
        }
        List<BusStopWiseFee> busStopAllStudents = this.feeReportDAO.getBusStopWiseAllStudents(academicYearId, studnetStatus, null);
        if (!(busStopAllStudents == null || busStopAllStudents.isEmpty())) {
            for (BusStopWiseFee busStopWiseFee2 : busStopAllStudents) {
            	CourseYearFeeSummary   courseYearFeeSummary = (CourseYearFeeSummary) courseYearFeeSummaryMap.get(busStopWiseFee2.getCourseYearId());
                if (courseYearFeeSummary != null) {
                    courseYearFeeSummary.getBusStopStudentCountMap().put(busStopWiseFee2.getBusStopId(), busStopWiseFee2);
                    courseYearFeeSummary.setBusFeePercentage(100);
                }
            }
        }
        List<BusFee> busFees = this.academicYearBusFeeDAO.getAssigedBusFee(academicYearId);
        List<CourseYearFeeSummary> arrayList = new ArrayList(courseYearFeeSummaryMap.values());
        if (busFees == null || busFees.isEmpty()) {
            return arrayList;
        }
        for (BusFee busFee : busFees) {
            for (CourseYearFeeSummary courseYearFeeSummary2 : arrayList) {
            	BusStopWiseFee busStopWiseFee2 = (BusStopWiseFee) courseYearFeeSummary2.getBusStopStudentCountMap().get(busFee.getBusStop().getId());
                if (busStopWiseFee2 != null) {
                    busStopWiseFee2.setBusFee(busFee.getRs());
                }
            }
        }
        return arrayList;
    }

    public List<CourseDiscount> getDiscountedStudents(Long academicYearId, Long courseYearId) {
        List<CourseDiscount> customizedStudents = this.feeReportDAO.getCustomizedStudentFees(academicYearId, courseYearId, StudentStatus.CONFIRMED);
        Map<String, CourseDiscount> discountedStudents = null;
        if (!(customizedStudents == null || customizedStudents.isEmpty())) {
            discountedStudents = new HashMap();
            for (CourseDiscount courseDiscount : customizedStudents) {
                discountedStudents.put(courseDiscount.getStudentId(), courseDiscount);
            }
        }
        if (discountedStudents == null) {
            return null;
        }
        List<CourseDiscount> discounts = this.feeReportDAO.getDiscountGiven(academicYearId, courseYearId);
        if (!(discounts == null || discounts.isEmpty())) {
            for (CourseDiscount courseDiscount2 : discounts) {
                CourseDiscount studentDiscount = (CourseDiscount) discountedStudents.get(courseDiscount2.getStudentId());
                if (studentDiscount != null) {
                    studentDiscount.setDiscount(courseDiscount2.getDiscount());
                }
            }
        }
        return new ArrayList(discountedStudents.values());
    }

    public List<Date> getUniqueInstallmentDueDates(Long academicYearId) {
        List<Date> academicDueDates = this.academicYearFeeService.getAcademicInstallmentDueDates(academicYearId);
        List<Date> adjustedDueDates = this.customizeStudentFeeService.getAdjustedInstallmentDueDates(academicYearId);
        Set<Date> uniqueDueDates = new LinkedHashSet();
        if (!(academicDueDates == null || academicDueDates.isEmpty())) {
            uniqueDueDates.addAll(academicDueDates);
        }
        if (!(adjustedDueDates == null || adjustedDueDates.isEmpty())) {
            uniqueDueDates.addAll(adjustedDueDates);
        }
        if (uniqueDueDates.isEmpty()) {
            return null;
        }
        List<Date> dueDateList = new ArrayList(uniqueDueDates);
        Collections.sort(dueDateList);
        return dueDateList;
    }

    private void getUnderSchemeAdmissions(Long academicSessionId, Long studentStatusId, CourseYearFeeSummary courseYearFeeSummary, List<AcademicYearFeeInstallment> newFeeInstallments, List<AcademicYearFeeInstallment> regFeeInstallments) {
        List<AcademicYearAdmissionScheme> academicSessionAdmisionSchemes = this.academicYearAdmissionSchemeService.getAdmissionSchemes(academicSessionId);
        if (academicSessionAdmisionSchemes != null) {
            List<ClassHistory> underSchemeAdmissions = this.admissionListService.getUnderSchemeAdmissions(academicSessionId, studentStatusId, null);
            List<ClassHistory> underSchemeAdmissionsWithoutFeeAdjusments = new ArrayList();
            if (underSchemeAdmissions != null) {
                List<Student> feeAdjustedAdmissions = this.feeAdjustedStudentService.getStudentsWhoseFeeIsCustomized(academicSessionId, studentStatusId);
                if (feeAdjustedAdmissions != null) {
                    for (ClassHistory classHistory : underSchemeAdmissions) {
                        Boolean found = Boolean.FALSE;
                        for (Student student : feeAdjustedAdmissions) {
                            if (classHistory.getStudent().getId().equals(student.getId())) {
                                found = Boolean.TRUE;
                                break;
                            }
                        }
                        if (!found.booleanValue()) {
                            underSchemeAdmissionsWithoutFeeAdjusments.add(classHistory);
                        }
                    }
                } else {
                    underSchemeAdmissions.addAll(underSchemeAdmissions);
                }
            }
            if (!underSchemeAdmissionsWithoutFeeAdjusments.isEmpty()) {
                for (ClassHistory classHistory2 : underSchemeAdmissionsWithoutFeeAdjusments) {
                    if (academicSessionId.equals(classHistory2.getAcademicYear().getId()) && courseYearFeeSummary.getCourseYear().getId().equals(classHistory2.getAcademicYearClass().getCourseYear().getId())) {
                        Long count;
                        if (AdmissionType.NEW_ADMISSION_ID.equals(classHistory2.getAdmissionType().getId())) {
                            count = (Long) courseYearFeeSummary.getNewAdmissionsUnderScheme().get(classHistory2.getAdmissionScheme().getId());
                            if (count == null) {
                                courseYearFeeSummary.getNewAdmissionsUnderScheme().put(classHistory2.getAdmissionScheme().getId(), Long.valueOf(1));
                            } else {
                                courseYearFeeSummary.getNewAdmissionsUnderScheme().put(classHistory2.getAdmissionScheme().getId(), Long.valueOf(count.longValue() + 1));
                            }
                        } else {
                            count = (Long) courseYearFeeSummary.getRegAdmissionsUnderScheme().get(classHistory2.getAdmissionScheme().getId());
                            if (count == null) {
                                courseYearFeeSummary.getRegAdmissionsUnderScheme().put(classHistory2.getAdmissionScheme().getId(), Long.valueOf(1));
                            } else {
                                courseYearFeeSummary.getRegAdmissionsUnderScheme().put(classHistory2.getAdmissionScheme().getId(), Long.valueOf(count.longValue() + 1));
                            }
                        }
                    }
                }
            }
            long feeDiscountTotal = 0;
            for (AcademicYearAdmissionScheme academicYearAdmissionScheme : academicSessionAdmisionSchemes) {
                if (courseYearFeeSummary.getNewAdmissionsUnderScheme().get(academicYearAdmissionScheme.getAdmissionScheme().getId()) != null) {
                    long newDiscountForOneStudent = 0;
                    for (AcademicYearFeeInstallment academicYearFeeInstallment : newFeeInstallments) {
                        newDiscountForOneStudent += FeeDiscountCalculator.calculateAdmissionSchemeDiscount(academicYearAdmissionScheme, academicYearFeeInstallment).longValue();
                    }
                    feeDiscountTotal += ((Long) courseYearFeeSummary.getNewAdmissionsUnderScheme().get(academicYearAdmissionScheme.getAdmissionScheme().getId())).longValue() * newDiscountForOneStudent;
                }
                if (courseYearFeeSummary.getRegAdmissionsUnderScheme().get(academicYearAdmissionScheme.getAdmissionScheme().getId()) != null) {
                    long regDiscountForOneStudent = 0;
                    for (AcademicYearFeeInstallment academicYearFeeInstallment2 : regFeeInstallments) {
                        regDiscountForOneStudent += FeeDiscountCalculator.calculateAdmissionSchemeDiscount(academicYearAdmissionScheme, academicYearFeeInstallment2).longValue();
                    }
                    feeDiscountTotal += ((Long) courseYearFeeSummary.getRegAdmissionsUnderScheme().get(academicYearAdmissionScheme.getAdmissionScheme().getId())).longValue() * regDiscountForOneStudent;
                }
            }
            courseYearFeeSummary.setUnderSchemeDiscountOnFee(feeDiscountTotal);
        }
    }
}
