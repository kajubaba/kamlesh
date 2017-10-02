package com.narendra.sams.web.restws.fee;

import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.CustomizeInstallment;
import com.narendra.sams.admission.domain.DaysOverdue;
import com.narendra.sams.admission.domain.FeeDiscount;
import com.narendra.sams.admission.domain.FeeRecieptHeader;
import com.narendra.sams.admission.domain.FeeTransaction;
import com.narendra.sams.admission.domain.FeeTransactionDetail;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearAdmissionScheme;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AcademicYearFee;
import com.narendra.sams.core.domain.AcademicYearFeeDetail;
import com.narendra.sams.core.domain.AcademicYearFeeInstallment;
import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusFeeInstallment;
import com.narendra.sams.core.domain.BusFeeInstallmentDetail;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.FeeHead;
import com.narendra.sams.core.domain.Installment;
import com.narendra.sams.core.service.AcademicYearAdmissionSchemeService;
import com.narendra.sams.core.service.AcademicYearBusFeeService;
import com.narendra.sams.core.service.AcademicYearFeeService;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.fee.domain.PayFeeReturn;
import com.narendra.sams.fee.service.CustomizeStudentFeeService;
import com.narendra.sams.fee.service.FeePaymentService;
import com.narendra.sams.fee.service.FeeRecieptService;
import com.narendra.sams.fee.service.PaidFeeService;
import com.narendra.sams.fee.service.StudentFeeService;
import com.narendra.sams.web.admission.controller.mapper.StudentFeeControllerMapper;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.fee.vo.ClassFeeDetailVO;
import com.narendra.sams.web.restws.fee.vo.FeeInstallmentVO;
import com.narendra.sams.web.restws.fee.vo.FeeTransactionVO;
import com.narendra.sams.web.restws.fee.vo.InstallmentHeadFee;
import com.narendra.sams.web.restws.fee.vo.PaidFeeVO;
import com.narendra.sams.web.restws.fee.vo.PaymentDetailVO;
import com.narendra.sams.web.restws.fee.vo.PaymentHistoryVO;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import com.narendra.sams.web.utils.StudentInformationUtil;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/fee/student"})
public class StudentFeeDetailRestController {
    @Autowired
    private AcademicYearAdmissionSchemeService academicYearAdmissionSchemeService;
    @Autowired
    private AcademicYearBusFeeService academicYearBusFeeService;
    @Autowired
    private AcademicYearFeeService academicYearFeeService;
    @Autowired
    private CustomizeStudentFeeService customizeStudentFeeService;
    @Autowired
    private FeePaymentService feePaymentService;
    @Autowired
    private FeeRecieptService feeRecieptService;
    @Autowired
    private PaidFeeService paidFeeService;
    @Autowired
    private StudentFeeControllerMapper studentFeeControllerMapper;
    @Autowired
    private StudentFeeService studentFeeService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{studentId}"})
    public ClassFeeDetailVO findClassAccountInfo(@PathVariable Long studentId, Long classHistoryId) {
        if (studentId == null) {
            return null;
        }
        Student student = this.studentService.getStudentById(studentId);
        if (classHistoryId == null) {
            return prepare(student.getActiveClassHistory(student.getAcademicYear().getId()), Boolean.TRUE);
        }
        return prepare(this.studentService.getClassHistory(classHistoryId), Boolean.TRUE);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/installment/{studentId}"})
    public PaymentDetailVO getInstallmentFeeForPayment(@PathVariable Long studentId, Long installmentId, Long ayFeeInstallmentId, Long custFeeInstallmentId, Long classHistoryId) {
        Student student = this.studentService.getStudentById(studentId);
        ClassHistory classHistory = this.studentService.getClassHistory(classHistoryId);
        BusStop studentBusStop = null;
        AcademicYearAdmissionScheme academicYearAdmissionScheme = null;
        if (classHistory.getAdmissionScheme() != null) {
            academicYearAdmissionScheme = this.academicYearAdmissionSchemeService.getAcademicYearAdmissionScheme(classHistory.getAcademicYear().getId(), classHistory.getAdmissionScheme().getId());
        }
        if (student.getBusStop() != null) {
            studentBusStop = student.getBusStop();
        }
        CustomizeInstallment customizeInstallment = null;
        AcademicYearFeeInstallment academicYearFeeInstallment = null;
        AcademicYear feeAcademicYear = null;
        BusFee busFee = null;
        BusFeeInstallment busFeeInstallment = null;
        BusFeeInstallmentDetail busFeeInstallmentDetail = null;
        List<FeeTransactionDetail> feeTransactionDetails = null;
        if (custFeeInstallmentId != null) {
            customizeInstallment = this.customizeStudentFeeService.getCustomizeInstallment(custFeeInstallmentId);
            if (customizeInstallment != null) {
                feeAcademicYear = customizeInstallment.getAcademicYearFee().getAcademicYear();
            }
            feeTransactionDetails = this.studentFeeService.getTransactionDetailsOfCustomizedInstallment(studentId, custFeeInstallmentId);
            busFeeInstallment = this.academicYearBusFeeService.getBusFeeInstallment(feeAcademicYear.getId());
        } else if (ayFeeInstallmentId != null) {
            academicYearFeeInstallment = this.academicYearFeeService.getAcademicYearFeeInstallment(ayFeeInstallmentId);
            if (academicYearFeeInstallment != null) {
                feeAcademicYear = academicYearFeeInstallment.getAcademicYearFee().getAcademicYear();
            }
            feeTransactionDetails = this.studentFeeService.getPaidFeeDetailsByAcdemicYearFeeInstallmentId(student.getId(), ayFeeInstallmentId);
            busFeeInstallment = this.academicYearBusFeeService.getBusFeeInstallment(feeAcademicYear.getId());
            if (studentBusStop != null) {
                busFee = this.academicYearBusFeeService.getBusFee(feeAcademicYear.getId(), studentBusStop.getId());
                if (busFee != null && BusFeeInstallment.SETUP_TYPE_PERCENTAGE.equals(busFeeInstallment.getSetupType())) {
                    busFeeInstallmentDetail = this.academicYearBusFeeService.getBusFeeInstallmentDetails(feeAcademicYear.getId(), installmentId);
                }
            }
        }
        List<InstallmentHeadFee> installmentDetailsVOs = this.studentFeeControllerMapper.prepareInstallmentNew(customizeInstallment, academicYearFeeInstallment, busFee, busFeeInstallment.getSetupType(), busFeeInstallmentDetail, UserSessionManager.getUserSession(this.webApplicationContext).getBusFeeHead(), feeTransactionDetails, this.studentFeeService.getDaysOverdue(student.getId(), feeAcademicYear.getId(), installmentId), UserSessionManager.getUserSession(this.webApplicationContext).getLateFeeHead(), academicYearAdmissionScheme);
        PaymentDetailVO paymentDetailVO = new PaymentDetailVO();
        paymentDetailVO.setInstallmentHeads(installmentDetailsVOs);
        paymentDetailVO.setFeeInstallmentId(ayFeeInstallmentId);
        paymentDetailVO.setCustInstallmentId(custFeeInstallmentId);
        paymentDetailVO.setInstallmentId(installmentId);
        paymentDetailVO.setStudentId(student.getId());
        paymentDetailVO.setPaymentClassId(classHistory.getAcademicYearClass().getId());
        paymentDetailVO.setPaymentDate(DateUtil.formatDate(DateUtil.getSystemDate(), "dd-MMM-yyyy"));
        PaymentDetailVO paymentDetailVO2 = paymentDetailVO;
        paymentDetailVO2.setReceiptHeaderId(this.feeRecieptService.getDefaultHeader(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()).getId());
        return paymentDetailVO;
    }

    public ClassFeeDetailVO prepare(ClassHistory classHistory, Boolean updateLateFee) {
        ClassFeeDetailVO classFeeDetail = new ClassFeeDetailVO();
        AcademicYearAdmissionScheme academicYearAdmissionScheme = null;
        if (classHistory.getAdmissionScheme() != null) {
            academicYearAdmissionScheme = this.academicYearAdmissionSchemeService.getAcademicYearAdmissionScheme(classHistory.getAcademicYear().getId(), classHistory.getAdmissionScheme().getId());
        }
        AcademicYearFee academicYearFee = this.academicYearFeeService.getAcademicYearFee(classHistory.getAcademicYearClass().getAcademicYear().getId(), classHistory.getAcademicYearClass().getCourseYear().getId(), classHistory.getAdmissionType().getId());
        if (academicYearFee == null) {
            return null;
        }
        if (academicYearFee.getAcademicYearFeeDetails() != null) {
            for (AcademicYearFeeDetail academicYearFeeDetail : academicYearFee.getAcademicYearFeeDetails()) {
                classFeeDetail.setAcademicFeeTotal(Long.valueOf(classFeeDetail.getAcademicFeeTotal().longValue() + academicYearFeeDetail.getAmount().longValue()));
            }
        }
        classFeeDetail.setClassName(classHistory.getAcademicYearClass().getDisplayName());
        classFeeDetail.setAcademicYearName(classHistory.getAcademicYear().getName());
        classFeeDetail.setClassHistoryId(classHistory.getId());
        Boolean isCustomized = Boolean.FALSE;
        AcademicYear feeAcademicYear = academicYearFee.getAcademicYear();
        BusStop feeBusStop = classHistory.getBusStop();
        BusFee busFee = null;
        BusFeeInstallment busFeeInstallment = null;
        if (feeBusStop != null) {
            busFee = this.academicYearBusFeeService.getBusFee(feeAcademicYear.getId(), feeBusStop.getId());
            busFeeInstallment = this.academicYearBusFeeService.getBusFeeInstallment(feeAcademicYear.getId());
            if (busFee != null) {
                classFeeDetail.setBusFee(busFee.getRs());
            }
        }
        List<CustomizeInstallment> customizeInstallments = this.studentFeeService.getCustomizeInstallments(classHistory.getStudent().getId(), academicYearFee.getId());
        List<FeeDiscount> feeDiscounts = null;
        if (!(customizeInstallments == null || customizeInstallments.isEmpty())) {
            isCustomized = Boolean.TRUE;
            feeDiscounts = this.customizeStudentFeeService.getFeeDiscounts(classHistory.getStudent().getId(), academicYearFee.getId());
        }
        List<FeeInstallmentVO> installmentsList = this.studentFeeControllerMapper.prepareInstallmentsNew(academicYearFee, customizeInstallments, busFee, busFeeInstallment, this.studentFeeService.getAllTransactionDetails(classHistory.getStudent().getId(), academicYearFee.getId(), isCustomized), this.studentFeeService.getDayOverdue(classHistory.getStudent().getId(), feeAcademicYear.getId()), academicYearAdmissionScheme);
        if (!isCustomized.booleanValue()) {
            for (FeeInstallmentVO feeInstallmentVO : installmentsList) {
                classFeeDetail.setAcademicFeeDiscount(Long.valueOf(classFeeDetail.getAcademicFeeDiscount().longValue() + feeInstallmentVO.getAdmissionSchemeDiscount().longValue()));
                classFeeDetail.setBusFeeDiscount(Long.valueOf(classFeeDetail.getBusFeeDiscount().longValue() + feeInstallmentVO.getAdmissionSchemeDiscountOnBusFee().longValue()));
            }
        } else if (feeDiscounts != null) {
            for (FeeDiscount feeDiscount : feeDiscounts) {
                if ("Bus Fee".equals(feeDiscount.getFeeHead().getName())) {
                    classFeeDetail.setBusFeeDiscount(Long.valueOf(classFeeDetail.getBusFeeDiscount().longValue() + feeDiscount.getAmount().longValue()));
                } else {
                    classFeeDetail.setAcademicFeeDiscount(Long.valueOf(classFeeDetail.getAcademicFeeDiscount().longValue() + feeDiscount.getAmount().longValue()));
                }
            }
        }
        calculateFeeDaysOverdue(installmentsList, classHistory, academicYearFee.getAcademicYear(), updateLateFee);
        classFeeDetail.setInstallmentFees(installmentsList);
        classFeeDetail.setFeeCustomized(isCustomized);
        return classFeeDetail;
    }

    private void calculateFeeDaysOverdue(List<FeeInstallmentVO> installments, ClassHistory classHistory, AcademicYear feeAcademicYear, Boolean updateLateFee) {
        if (installments != null) {
            List<DaysOverdue> daysOverdues = new ArrayList();
            for (FeeInstallmentVO studentInstallmentVO : installments) {
                DaysOverdue daysOverdue = new DaysOverdue();
                daysOverdue.setStudent(classHistory.getStudent());
                daysOverdue.setFeeAcademicYear(feeAcademicYear);
                Installment installment = new Installment();
                installment.setId(studentInstallmentVO.getInstallmentId());
                daysOverdue.setInstallment(installment);
                daysOverdue.setCalculate(studentInstallmentVO.getCalculateLateFee());
                daysOverdue.setDaysOverdue((int) studentInstallmentVO.calculateFeeDaysOverdue());
                if (studentInstallmentVO.getAcademicYearFeeInstallmentId() != null) {
                    AcademicYearFeeInstallment academicYearFeeInstallment = new AcademicYearFeeInstallment();
                    academicYearFeeInstallment.setId(studentInstallmentVO.getAcademicYearFeeInstallmentId());
                    daysOverdue.setAcademicYearFeeInstallment(academicYearFeeInstallment);
                }
                if (studentInstallmentVO.getCustFeeInstallmentId() != null) {
                    CustomizeInstallment customizeInstallment = new CustomizeInstallment();
                    customizeInstallment.setId(studentInstallmentVO.getCustFeeInstallmentId());
                    daysOverdue.setCustomizeInstallment(customizeInstallment);
                }
                daysOverdues.add(daysOverdue);
            }
            if (updateLateFee.booleanValue()) {
                this.studentFeeService.saveOrUpdateDaysOverdue(daysOverdues);
            }
        }
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/recentTransactions"})
    public List<FeeTransactionVO> getRecentTransactions() {
        return populateFeeTransaction(this.paidFeeService.getRecentFeeTransactions(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId()));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/total"})
    public PaidFeeVO paidFee() {
        Long paidFee = this.paidFeeService.getTotalPaidFee(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId());
        PaidFeeVO paidFeeVO = new PaidFeeVO();
        paidFeeVO.setName("Total paid fee");
        paidFeeVO.setAmount(paidFee);
        return paidFeeVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/today"})
    public PaidFeeVO paidToday() {
        Long paidFee = this.paidFeeService.getTodayPaidFee(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId());
        PaidFeeVO paidFeeVO = new PaidFeeVO();
        paidFeeVO.setName("today paid fee");
        paidFeeVO.setAmount(paidFee);
        return paidFeeVO;
    }

    private List<FeeTransactionVO> populateFeeTransaction(List<FeeTransaction> feeTransactions) {
        List<FeeTransactionVO> feeTransactionVOs = null;
        if (feeTransactions != null) {
            feeTransactionVOs = new ArrayList();
            for (FeeTransaction feeTransaction : feeTransactions) {
                FeeTransactionVO feeTransactionVO = new FeeTransactionVO();
                feeTransactionVO.setPaidFee(Long.valueOf(feeTransaction.getFeeSum()));
                feeTransactionVO.setReceiptNo(feeTransaction.getRecieptNo());
                feeTransactionVO.setStudentId(feeTransaction.getStudent().getStudentId());
                feeTransactionVO.setStudentName(feeTransaction.getStudent().getFullName());
                if (feeTransaction.getTransactionTime() != null) {
                    feeTransactionVO.setTransactionDateTime(DateUtil.formatDate(feeTransaction.getTransactionTime(), "dd-MMM-yyyy hh:mm a"));
                } else {
                    feeTransactionVO.setTransactionDateTime("");
                }
                feeTransactionVO.setTransactionId(feeTransaction.getId());
                feeTransactionVOs.add(feeTransactionVO);
            }
        }
        return feeTransactionVOs;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/payFee"})
    public PayFeeReturn payStudentFee(@RequestBody PaymentDetailVO paymentDetail) {
        FeeRecieptHeader feeRecieptHeader;
        Long instituteId = UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId();
        if (paymentDetail.getReceiptHeaderId() == null) {
            feeRecieptHeader = this.feeRecieptService.getDefaultHeader(instituteId);
        } else {
            feeRecieptHeader = this.feeRecieptService.getFeeRecieptHeader(paymentDetail.getReceiptHeaderId());
        }
        FeeTransaction feeTransaction = new FeeTransaction();
        if (paymentDetail.getInstallmentHeads() != null) {
            Student student = this.studentService.getStudentById(paymentDetail.getStudentId());
            Set<FeeTransactionDetail> feeTransactionDetails = new HashSet();
            for (InstallmentHeadFee installmentHeadFee : paymentDetail.getInstallmentHeads()) {
                if (!(installmentHeadFee.getPayable() == null || installmentHeadFee.getPayable().longValue() == 0)) {
                    FeeTransactionDetail feeTransactionDetail = new FeeTransactionDetail();
                    feeTransactionDetail.setFeeTransaction(feeTransaction);
                    FeeHead feeHead = new FeeHead();
                    feeHead.setId(installmentHeadFee.getFeeHeadId());
                    feeHead.setName(installmentHeadFee.getFeeHeadName());
                    feeTransactionDetail.setFeeHead(feeHead);
                    feeTransactionDetail.setAmount(installmentHeadFee.getPayable());
                    Installment installment = new Installment();
                    installment.setId(paymentDetail.getInstallmentId());
                    feeTransactionDetail.setInstallment(installment);
                    feeTransactionDetails.add(feeTransactionDetail);
                }
            }
            if (feeTransactionDetails.isEmpty()) {
                feeTransaction = null;
            } else {
                AcademicYearClass academicYearClass = new AcademicYearClass();
                academicYearClass.setId(paymentDetail.getPaymentClassId());
                feeTransaction.setStudent(student);
                feeTransaction.setStudentClass(academicYearClass);
                feeTransaction.setFeeRecieptHeader(feeRecieptHeader);
                feeTransaction.setFeeTransactionDetails(feeTransactionDetails);
                if (paymentDetail.getFeeInstallmentId() != null) {
                    AcademicYearFeeInstallment academicYearFeeInstallment = new AcademicYearFeeInstallment();
                    academicYearFeeInstallment.setId(paymentDetail.getFeeInstallmentId());
                    feeTransaction.setAcademicYearFeeInstallment(academicYearFeeInstallment);
                }
                if (paymentDetail.getCustInstallmentId() != null) {
                    CustomizeInstallment customizeInstallment = new CustomizeInstallment();
                    customizeInstallment.setId(paymentDetail.getCustInstallmentId());
                    feeTransaction.setCustomizeInstallment(customizeInstallment);
                }
            }
        }
        feeTransaction.setDepositedBy(paymentDetail.getDepositedBy());
        feeTransaction.setPaymentMode(paymentDetail.getPaymentMode());
        feeTransaction.setChequeDDNo(paymentDetail.getChequeDDNo());
        if (!(paymentDetail.getChequeDDDate() == null || paymentDetail.getChequeDDDate().isEmpty())) {
            try {
                feeTransaction.setChequeDDDate(DateUtil.parseDate(paymentDetail.getChequeDDDate(), "dd-MMM-yyyy"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        feeTransaction.setChequeDDBankName(paymentDetail.getChequeDDBankName());
        feeTransaction.setBankBranchName(paymentDetail.getBankBranchName());
        feeTransaction.setComments(paymentDetail.getComments());
        if (!(paymentDetail.getPaymentDate() == null || paymentDetail.getPaymentDate().isEmpty())) {
            try {
                feeTransaction.setPaymentDate(DateUtil.parseDate(paymentDetail.getPaymentDate(), "dd-MMM-yyyy"));
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
        }
        return this.feePaymentService.payFee(feeTransaction, LoggedinUserAssistant.getLoggedInUserId());
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/paymenthistory/{studentId}"})
    public List<PaymentHistoryVO> getStudentTransactions(@PathVariable Long studentId) {
        List<FeeTransaction> feeTransactions = this.studentFeeService.getFeeTransactions(studentId);
        List<PaymentHistoryVO> paymentHistoryVOs = null;
        if (feeTransactions != null) {
            paymentHistoryVOs = new ArrayList();
            for (FeeTransaction feeTransaction : feeTransactions) {
                PaymentHistoryVO paymentHistory = new PaymentHistoryVO();
                paymentHistory.setTransactionId(feeTransaction.getId());
                paymentHistory.setAcademicYear(feeTransaction.getAcademicYear().getName());
                paymentHistory.setPaidAmount(Long.valueOf(feeTransaction.getFeeSum()).toString());
                paymentHistory.setPaymentClass(feeTransaction.getStudentClass().getDisplayName());
                if (feeTransaction.getRecieptNo() != null) {
                    paymentHistory.setReceiptNo(feeTransaction.getRecieptNo().toString());
                } else {
                    paymentHistory.setReceiptNo("");
                }
                if (feeTransaction.getPaymentDate() != null) {
                    paymentHistory.setPaymentDate(DateUtil.formatDate(feeTransaction.getPaymentDate(), "dd-MMM-yyyy"));
                }
                if (feeTransaction.getCustomizeInstallment() != null) {
                    paymentHistory.setInstallment(feeTransaction.getCustomizeInstallment().getInstallment().getName().toString());
                } else if (feeTransaction.getAcademicYearFeeInstallment() != null) {
                    paymentHistory.setInstallment(feeTransaction.getAcademicYearFeeInstallment().getInstallment().getName().toString());
                }
                paymentHistory.setTransactionNo(feeTransaction.getTransactionId());
                paymentHistoryVOs.add(paymentHistory);
            }
        }
        return paymentHistoryVOs;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/feesummary/{studentId}"})
    private List<ClassFeeDetailVO> getAllClassFeeSummary(@PathVariable Long studentId) {
        List<ClassFeeDetailVO> allClassFee = null;
        Student student = this.studentService.getStudentById(studentId);
        if (student == null) {
            return null;
        }
        Set<ClassHistory> classHistories = student.getClassHistories();
        List<ClassHistory> classHistoryList = null;
        if (classHistories != null) {
            List classHistoryList2 = null;
            for (ClassHistory classHistory : classHistories) {
                if (classHistoryList2 == null) {
                    classHistoryList2 = new ArrayList();
                }
                classHistoryList2.add(classHistory);
            }
            if (classHistoryList2 != null) {
                Collections.sort(classHistoryList2, new ReverseComparator(new BeanComparator("academicYear.id", new NullComparator())));
            }
        }
        if (classHistoryList != null) {
            allClassFee = new ArrayList();
            for (ClassHistory classHistory2 : classHistoryList) {
                if (classHistory2.getActiveClass().booleanValue()) {
                    ClassFeeDetailVO classFeeDetailVO = prepare(classHistory2, Boolean.TRUE);
                    classFeeDetailVO.setClassName(StudentInformationUtil.getClassName(classHistory2.getAcademicYearClass()));
                    classFeeDetailVO.setAcademicYearName(classHistory2.getAcademicYear().getName());
                    if (classFeeDetailVO.getInstallmentFees() != null) {
                        allClassFee.add(classFeeDetailVO);
                    }
                }
            }
        }
        return allClassFee;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/totalDueFee/{studentId}"})
    private List<ClassFeeDetailVO> getTotalDueFee(@PathVariable Long studentId) {
        List<ClassFeeDetailVO> allClassFee = null;
        Student student = this.studentService.getStudentById(studentId);
        if (student == null) {
            return null;
        }
        Set<ClassHistory> classHistories = student.getClassHistories();
        List<ClassHistory> classHistoryList = null;
        if (classHistories != null) {
            List classHistoryList2 = null;
            for (ClassHistory classHistory : classHistories) {
                if (classHistoryList2 == null) {
                    classHistoryList2 = new ArrayList();
                }
                classHistoryList2.add(classHistory);
            }
            if (classHistoryList2 != null) {
                Collections.sort(classHistoryList2, new ReverseComparator(new BeanComparator("academicYear.id", new NullComparator())));
            }
        }
        if (classHistoryList != null) {
            allClassFee = new ArrayList();
            for (ClassHistory classHistory2 : classHistoryList) {
                if (classHistory2.getActiveClass().booleanValue()) {
                    ClassFeeDetailVO classFeeDetailVO = prepare(classHistory2, Boolean.FALSE);
                    classFeeDetailVO.setClassName(StudentInformationUtil.getClassName(classHistory2.getAcademicYearClass()));
                    classFeeDetailVO.setAcademicYearName(classHistory2.getAcademicYear().getName());
                    if (classFeeDetailVO.getInstallmentFees() != null) {
                        allClassFee.add(classFeeDetailVO);
                    }
                }
            }
        }
        return allClassFee;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/delete/feetransaction/{feeTransactionId}"})
    private AjaxResponse deleteFeeTransaction(@PathVariable Long feeTransactionId) {
        this.paidFeeService.deleteFeeTransaction(feeTransactionId);
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }
}
