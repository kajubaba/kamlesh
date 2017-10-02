package com.narendra.sams.web.restws.fee;

import com.narendra.sams.admission.domain.CourseYearFeeSummary;
import com.narendra.sams.admission.domain.StudentFee;
import com.narendra.sams.core.domain.CourseYear;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.core.service.CourseService;
import com.narendra.sams.core.service.StudentStatusService;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.fee.service.FeeReportService;
import com.narendra.sams.transportation.domain.Route;
import com.narendra.sams.transportation.service.BusOnRouteService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.fee.vo.AcademicYearDueFeeReportVO;
import com.narendra.sams.web.restws.fee.vo.ClassDueFeeVO;
import com.narendra.sams.web.restws.fee.vo.DateVO;
import com.narendra.sams.web.restws.fee.vo.DueFeeStudentsOfClassVO;
import com.narendra.sams.web.restws.fee.vo.StudentDueFeeVO;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/feereport"})
public class DueFeeRestController {
    @Autowired
    private AcademicYearService academicYearService;
    @Autowired
    private BusOnRouteService busOnRouteService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private FeeReportService feeReportService;
    @Autowired
    private StudentStatusService studentStatusService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/due"})
    public AcademicYearDueFeeReportVO getDueFeeReport(Long academicYearId, String dueDateStr, Long admissionStatus) {
        Date dueDate = null;
        if (academicYearId == null) {
            academicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        }
        if (dueDateStr == null || dueDateStr.isEmpty()) {
            dueDate = getLatestDueDate(academicYearId);
        } else {
            try {
                dueDate = DateUtil.makeEndDate(DateUtil.parseDate(dueDateStr, "dd-MMM-yyyy"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<CourseYearFeeSummary> courseYearFeeSummaries = null;
        if (dueDate != null) {
            courseYearFeeSummaries = this.feeReportService.getDueFeeReport(academicYearId, dueDate, admissionStatus);
        }
        AcademicYearDueFeeReportVO academicYearDueFeeReportVO = new AcademicYearDueFeeReportVO();
        academicYearDueFeeReportVO.setAcademicYearId(academicYearId);
        if (dueDate != null) {
            academicYearDueFeeReportVO.setDueDate(DateUtil.formatDate(dueDate, "dd-MMM-yyyy"));
        }
        String dueDateStr1 = null;
        if (dueDate != null) {
            dueDateStr1 = DateUtil.formatDate(dueDate, "dd-MMM-yyyy");
        }
        academicYearDueFeeReportVO.setClassDueFees(prepareDueFeeReportVO(courseYearFeeSummaries, academicYearId, dueDateStr1, admissionStatus));
        return academicYearDueFeeReportVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/due/students"})
    public DueFeeStudentsOfClassVO getDueStudents(Long academicYearId, Long courseYearId, String dueDateStr, Long admissionStatus, Boolean showOnlyDue) {
        DueFeeStudentsOfClassVO dueFeeStudentsOfClassVO = new DueFeeStudentsOfClassVO();
        if (academicYearId == null) {
            academicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        }
        Date dueDate = null;
        if (dueDateStr == null || dueDateStr.isEmpty()) {
            dueDate = getLatestDueDate(academicYearId);
        } else {
            try {
                dueDate = DateUtil.makeEndDate(DateUtil.parseDate(dueDateStr, "dd-MMM-yyyy"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        dueFeeStudentsOfClassVO.setAcademicYear(this.academicYearService.getAcademicYearById(academicYearId.longValue()).getName());
        CourseYear courseYear = this.courseService.loadCourseYear(courseYearId);
        if (courseYear.getCourse().getDuration().shortValue() == (short) 1) {
            dueFeeStudentsOfClassVO.setClassName(courseYear.getCourse().getDisplayName());
        } else {
            dueFeeStudentsOfClassVO.setClassName(new StringBuilder(String.valueOf(courseYear.getCourse().getDisplayName())).append(" , ").append(courseYear.getName()).append(" Yr.").toString());
        }
        dueFeeStudentsOfClassVO.setDueDate(dueDateStr);
        dueFeeStudentsOfClassVO.setAdmissionStatus(this.studentStatusService.getStaudentStatus(admissionStatus).getName());
        List<StudentFee> studentFees = null;
        if (dueDate != null) {
            studentFees = this.feeReportService.getDueStudents(academicYearId, courseYearId, dueDate, admissionStatus);
        }
        dueFeeStudentsOfClassVO.setStudents(prepareDueFeeStudentsVO(dueFeeStudentsOfClassVO, studentFees, showOnlyDue, academicYearId));
        return dueFeeStudentsOfClassVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/due/allstudents"})
    public DueFeeStudentsOfClassVO getAllDueStudents(Long academicYearId, String dueDateStr, Long studentStatus, Boolean showOnlyDue) {
        DueFeeStudentsOfClassVO dueFeeStudentsOfClassVO = new DueFeeStudentsOfClassVO();
        Date dueDate = null;
        if (academicYearId == null) {
            academicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        }
        if (dueDateStr == null || dueDateStr.isEmpty()) {
            dueDate = getLatestDueDate(academicYearId);
        } else {
            try {
                dueDate = DateUtil.makeEndDate(DateUtil.parseDate(dueDateStr, "dd-MMM-yyyy"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        dueFeeStudentsOfClassVO.setAcademicYear(this.academicYearService.getAcademicYearById(academicYearId.longValue()).getName());
        dueFeeStudentsOfClassVO.setDueDate(DateUtil.formatDate(dueDate, "dd-MMM-yyyy"));
        List<StudentFee> studentFees = null;
        if (dueDate != null) {
            studentFees = this.feeReportService.getDueStudents(academicYearId, dueDate, studentStatus);
        }
        dueFeeStudentsOfClassVO.setStudents(prepareDueFeeStudentsVO(dueFeeStudentsOfClassVO, studentFees, showOnlyDue, academicYearId));
        return dueFeeStudentsOfClassVO;
    }

    private List<StudentDueFeeVO> prepareDueFeeStudentsVO(DueFeeStudentsOfClassVO dueFeeStudentsOfClassVO, List<StudentFee> studentFees, Boolean showOnlyDue, Long academicSessionId) {
        List<StudentDueFeeVO> feeDueStudentVOs = new ArrayList();
        long payableFeeSum = 0;
        long paidFeeSum = 0;
        long unpaidFeeSum = 0;
        if (!(studentFees == null || studentFees.isEmpty())) {
            for (StudentFee studentFee : studentFees) {
                StudentDueFeeVO feeDueStudentVO = new StudentDueFeeVO();
                feeDueStudentVO.setStudentDbId(studentFee.getStudentDbId());
                feeDueStudentVO.setStudentId(studentFee.getStudentId());
                feeDueStudentVO.setStudentFullName(studentFee.getStudentFirstName() + " " + studentFee.getStudentLastName());
                feeDueStudentVO.setStudentContactNo1(studentFee.getStudentContactNo1());
                feeDueStudentVO.setTotalFee((studentFee.getAdminFee().longValue() + studentFee.getCustomizedFee().longValue()) + studentFee.getBusFee().longValue());
                feeDueStudentVO.setPaidFee(studentFee.getPaidFee());
                feeDueStudentVO.setUnPaidFee(feeDueStudentVO.getTotalFee() - feeDueStudentVO.getPaidFee());
                feeDueStudentVO.setPaidFeeInAdvance(studentFee.getPaidFeeInAdvance());
                feeDueStudentVO.setPaidLateFee(studentFee.getPaidLateFee());
                if (studentFee.getClassHistory() != null) {
                    feeDueStudentVO.setClassHistoryId(studentFee.getClassHistory().getId());
                    feeDueStudentVO.setStudentClass(studentFee.getClassHistory().getAcademicYearClass().prepareClassName());
                }
                feeDueStudentVO.setFatherName(studentFee.getFatherName());
                feeDueStudentVO.setFatherContactNo1(studentFee.getFatherContactNo1());
                feeDueStudentVO.setFatherContactNo2(studentFee.getFatherContactNo2());
                feeDueStudentVO.setMotherContactNo1(studentFee.getMotherContactNo1());
                feeDueStudentVO.setMotherContactNo2(studentFee.getMotherContactNo2());
                feeDueStudentVO.setAddresss(studentFee.getAddresss());
                feeDueStudentVO.setBusStopName(studentFee.getBusStopName());
                feeDueStudentVO.setBusStopInOtherLanguage(studentFee.getBusStopInOtherLanguage());
                payableFeeSum += feeDueStudentVO.getTotalFee();
                paidFeeSum += feeDueStudentVO.getPaidFee();
                unpaidFeeSum += feeDueStudentVO.getUnPaidFee();
                if (!showOnlyDue.booleanValue()) {
                    feeDueStudentVOs.add(feeDueStudentVO);
                } else if (feeDueStudentVO.getUnPaidFee() > 0) {
                    feeDueStudentVOs.add(feeDueStudentVO);
                }
                List<Route> routes = this.busOnRouteService.getStudentRoutes(studentFee.getStudentDbId(), academicSessionId);
                if (((routes != null ? 1 : 0) & (routes.isEmpty() ? 0 : 1)) != 0) {
                    if (routes.size() != 1) {
                        for (Route route : routes) {
                            if (route.getAcademicYearVehicle() != null) {
                                if (Route.ROUTE_TYPE_ARRIVAL.equals(route.getType()) && route.getAcademicYearVehicle() != null) {
                                    feeDueStudentVO.setArrivalBusName(route.getAcademicYearVehicle().getVehicle().getVehicleName());
                                } else if (Route.ROUTE_TYPE_DEPARTUTE.equals(route.getType()) && route.getAcademicYearVehicle() != null) {
                                    feeDueStudentVO.setDepartureBusName(route.getAcademicYearVehicle().getVehicle().getVehicleName());
                                }
                            }
                        }
                    } else if (((Route) routes.get(0)).getAcademicYearVehicle() != null) {
                        if (Route.ROUTE_TYPE_ARRIVAL.equals(((Route) routes.get(0)).getType())) {
                            feeDueStudentVO.setArrivalBusName(((Route) routes.get(0)).getAcademicYearVehicle().getVehicle().getVehicleName());
                        } else {
                            feeDueStudentVO.setDepartureBusName(((Route) routes.get(0)).getAcademicYearVehicle().getVehicle().getVehicleName());
                        }
                    }
                }
                feeDueStudentVO.setStudentNameInOtherLanguage(studentFee.getStudentNameInOtherLanguage());
                feeDueStudentVO.setFatherNameInOtherLanguage(studentFee.getFatherNameInOtherLanguage());
            }
        }
        dueFeeStudentsOfClassVO.setPayableFeeSum(payableFeeSum);
        dueFeeStudentsOfClassVO.setPaidFeeSum(paidFeeSum);
        dueFeeStudentsOfClassVO.setUnpaidFeeSum(unpaidFeeSum);
        return feeDueStudentVOs;
    }

    private List<ClassDueFeeVO> prepareDueFeeReportVO(List<CourseYearFeeSummary> classFees, Long academicYearId, String dueDate, Long admissionStatus) {
        List<ClassDueFeeVO> dueFeeReportVOs = new ArrayList();
        if (!(classFees == null || classFees.isEmpty())) {
            for (CourseYearFeeSummary classFee : classFees) {
                ClassDueFeeVO dueFeeReportVO = new ClassDueFeeVO();
                if (classFee.getCourse().getDuration().shortValue() == (short) 1) {
                    dueFeeReportVO.setClassName(classFee.getCourse().getDisplayName());
                } else {
                    dueFeeReportVO.setClassName(new StringBuilder(String.valueOf(classFee.getCourse().getDisplayName())).append(" , ").append(classFee.getCourseYear().getName()).append(" Yr.").toString());
                }
                dueFeeReportVO.setProjectedFee(classFee.getDueProjectedFee() + classFee.getBusFee());
                dueFeeReportVO.setPayableBusFee(classFee.getBusFee());
                dueFeeReportVO.setPaidFee(classFee.getDepositedFee());
                dueFeeReportVO.setUnpaidFee(dueFeeReportVO.getProjectedFee() - dueFeeReportVO.getPaidFee());
                dueFeeReportVO.setPaidAdvance(classFee.getAdvanceDeposited());
                dueFeeReportVO.setPaidLateFee(classFee.getLateFeeDeposited());
                dueFeeReportVO.setTotalPaid((dueFeeReportVO.getPaidFee() + dueFeeReportVO.getPaidAdvance()) + dueFeeReportVO.getPaidLateFee());
                dueFeeReportVO.setAcademicYearId(academicYearId);
                dueFeeReportVO.setCourseYearId(classFee.getCourseYear().getId());
                dueFeeReportVO.setDueDate(dueDate);
                dueFeeReportVO.setAdmissionStatus(admissionStatus);
                dueFeeReportVOs.add(dueFeeReportVO);
            }
        }
        return dueFeeReportVOs;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/duefee/duedates"})
    public List<DateVO> getUniqueFeeDueDates(Long academicYearId) {
        if (academicYearId == null) {
            academicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        }
        List<DateVO> dueDates = new ArrayList();
        List<Date> dates = this.feeReportService.getUniqueInstallmentDueDates(academicYearId);
        if (dates != null) {
            for (Date date : dates) {
                dueDates.add(new DateVO(DateUtil.formatDate(date, "dd-MMM-yyyy")));
            }
        }
        return dueDates;
    }

    private Date getLatestDueDate(Long academicYearId) {
        List<Date> dates = this.feeReportService.getUniqueInstallmentDueDates(academicYearId);
        Date todaysDate = DateUtil.getSystemDate();
        if (!(dates == null || dates.isEmpty())) {
            if (dates.size() == 1) {
                return (Date) dates.get(0);
            }
            if (((Date) dates.get(dates.size() - 1)).equals(todaysDate) || todaysDate.after((Date) dates.get(dates.size() - 1))) {
                return (Date) dates.get(dates.size() - 1);
            }
            if (todaysDate.equals(dates.get(0)) || todaysDate.before((Date) dates.get(0))) {
                return (Date) dates.get(0);
            }
            int i = dates.size() - 1;
            while (i >= 0) {
                if (((Date) dates.get(i)).before(todaysDate) || ((Date) dates.get(i)).equals(todaysDate)) {
                    return (Date) dates.get(i);
                }
                i--;
            }
        }
        return null;
    }
}
