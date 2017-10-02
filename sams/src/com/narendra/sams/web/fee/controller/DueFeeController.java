package com.narendra.sams.web.fee.controller;

import com.narendra.sams.admission.domain.CourseYearFeeSummary;
import com.narendra.sams.admission.domain.StudentFee;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.CourseYear;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.core.service.CourseService;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.fee.service.FeeReportService;
import com.narendra.sams.web.auth.UserSessionManager;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/fee/due"})
public class DueFeeController {
    @Autowired
    private AcademicYearService academicYearService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private FeeReportService feeReportService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.GET})
    public String getDefaultSummarizeDueFeeReport(Model model) {
        Date dueDate = DateUtil.getSystemDate();
        List<AcademicYear> academicYears = this.academicYearService.getFeeAcademicYears(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId());
        model.addAttribute("courseYearFeeSummaries", this.feeReportService.getDueFeeReport(UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId(), dueDate, StudentStatus.CONFIRMED));
        model.addAttribute("dueDate", DateUtil.formatDate(dueDate, "dd-MMM-yyyy"));
        model.addAttribute("academicYearId", UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId());
        model.addAttribute("academicYears", academicYears);
        return "due_fee_summary_course_year_wise";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/summary"})
    public String getSummarizeDueFeeReport(Long academicYearId, Model model, String DueDateStr) {
        Date dueDate = null;
        List<AcademicYear> academicYears = this.academicYearService.getFeeAcademicYears(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId());
        if (!"".equals(DueDateStr)) {
            try {
                dueDate = DateUtil.makeEndDate(DateUtil.parseDate(DueDateStr, "dd-MMM-yyyy"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (dueDate != null) {
            model.addAttribute("courseYearFeeSummaries", this.feeReportService.getDueFeeReport(academicYearId, dueDate, StudentStatus.CONFIRMED));
        }
        model.addAttribute("academicYearId", academicYearId);
        model.addAttribute("dueDate", DueDateStr);
        model.addAttribute("academicYears", academicYears);
        return "due_fee_summary_course_year_wise";
    }

    @RequestMapping(method = {RequestMethod.POST}, value = {"/summary/export"})
    public void exportSummarizeDueFeeReport(Long academicYearId, String DueDateStr, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Content-disposition", "attachment;filename=due_fee_report_" + DueDateStr + ".xls");
        Date dueDate = null;
        if (!"".equals(DueDateStr)) {
            try {
                dueDate = DateUtil.makeEndDate(DateUtil.parseDate(DueDateStr, "dd-MMM-yyyy"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (dueDate != null) {
            prepareSummarizeDueFeeExcel(this.feeReportService.getDueFeeReport(academicYearId, dueDate, StudentStatus.CONFIRMED)).write(response.getOutputStream());
        }
    }

    private Workbook prepareSummarizeDueFeeExcel(List<CourseYearFeeSummary> courseYearFeeSummaries) {
        if (courseYearFeeSummaries == null || (courseYearFeeSummaries != null && courseYearFeeSummaries.isEmpty())) {
            return null;
        }
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Due-Fee-Summary");
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Calibry");
        CellStyle contentCellStyle = wb.createCellStyle();
        contentCellStyle.setFont(font);
        Row row0 = sheet.createRow(0);
        Cell cell_0 = row0.createCell(0);
        cell_0.setCellStyle(contentCellStyle);
        cell_0.setCellValue("Course");
        Cell cell_1 = row0.createCell(1);
        cell_1.setCellStyle(contentCellStyle);
        cell_1.setCellValue("Year");
        Cell cell_2 = row0.createCell(2);
        cell_2.setCellStyle(contentCellStyle);
        cell_2.setCellValue("No of Students");
        Cell cell_3 = row0.createCell(3);
        cell_3.setCellStyle(contentCellStyle);
        cell_3.setCellValue("Projected Fee");
        Cell cell_4 = row0.createCell(4);
        cell_4.setCellStyle(contentCellStyle);
        cell_4.setCellValue("Deposited Fee");
        Cell cell_5 = row0.createCell(5);
        cell_5.setCellStyle(contentCellStyle);
        cell_5.setCellValue("Remaining Fee");
        int rowCount = 1;
        long totalStudents = 0;
        long totalProjectedFee = 0;
        long totalDepositedFee = 0;
        for (CourseYearFeeSummary courseYearFeeSummary : courseYearFeeSummaries) {
            int rowCount2 = rowCount + 1;
            Row row = sheet.createRow(rowCount);
            Cell col_0 = row.createCell(0);
            col_0.setCellStyle(contentCellStyle);
            col_0.setCellValue(courseYearFeeSummary.getCourse().getDisplayName());
            Cell col_1 = row.createCell(1);
            col_1.setCellStyle(contentCellStyle);
            col_1.setCellValue((double) courseYearFeeSummary.getCourseYear().getName().shortValue());
            Cell col_2 = row.createCell(2);
            col_2.setCellStyle(contentCellStyle);
            col_2.setCellValue((double) courseYearFeeSummary.getDueFeeAdmittedStudent());
            totalStudents += courseYearFeeSummary.getDueFeeAdmittedStudent();
            Cell col_3 = row.createCell(3);
            col_3.setCellStyle(contentCellStyle);
            col_3.setCellValue((double) courseYearFeeSummary.getDueProjectedFee());
            totalProjectedFee += courseYearFeeSummary.getDueProjectedFee();
            Cell col_4 = row.createCell(4);
            col_4.setCellStyle(contentCellStyle);
            col_4.setCellValue((double) courseYearFeeSummary.getDepositedFee());
            totalDepositedFee += courseYearFeeSummary.getDepositedFee();
            Cell col_5 = row.createCell(5);
            col_5.setCellStyle(contentCellStyle);
            col_5.setCellValue((double) (courseYearFeeSummary.getDueProjectedFee() - courseYearFeeSummary.getDepositedFee()));
            rowCount = rowCount2;
        }
        if (0 == totalStudents) {
            return wb;
        }
        Row lastRow = sheet.createRow(rowCount);
        Cell lastRow_col_0 = lastRow.createCell(0);
        lastRow_col_0.setCellStyle(contentCellStyle);
        lastRow_col_0.setCellValue("Total");
        Cell lastRow_col_1 = lastRow.createCell(1);
        lastRow_col_1.setCellStyle(contentCellStyle);
        lastRow_col_1.setCellValue("");
        Cell lastRow_col_2 = lastRow.createCell(2);
        lastRow_col_2.setCellStyle(contentCellStyle);
        lastRow_col_2.setCellValue((double) totalStudents);
        Cell lastRow_col_3 = lastRow.createCell(3);
        lastRow_col_3.setCellStyle(contentCellStyle);
        lastRow_col_3.setCellValue((double) totalProjectedFee);
        Cell lastRow_col_4 = lastRow.createCell(4);
        lastRow_col_4.setCellStyle(contentCellStyle);
        lastRow_col_4.setCellValue((double) totalDepositedFee);
        Cell lastRow_col_5 = lastRow.createCell(5);
        lastRow_col_5.setCellStyle(contentCellStyle);
        lastRow_col_5.setCellValue((double) (totalProjectedFee - totalDepositedFee));
        return wb;
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/students"})
    public String getDueStudents(Model model, Long academicYearId, Long courseYearId, String feeDueDateStr) {
        Date feeDueDate = null;
        if (!"".equals(feeDueDateStr)) {
            try {
                feeDueDate = DateUtil.parseDate(feeDueDateStr, "dd-MMM-yyyy");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        AcademicYear academicYear = this.academicYearService.getAcademicYearById(academicYearId.longValue());
        CourseYear courseYear = this.courseService.loadCourseYear(courseYearId);
        model.addAttribute("academicYear", academicYear);
        model.addAttribute("courseYear", courseYear);
        model.addAttribute("feeDueDateStr", feeDueDateStr);
        model.addAttribute("studentFees", this.feeReportService.getDueStudents(academicYearId, courseYearId, feeDueDate, StudentStatus.CONFIRMED));
        return "due_fee_detail_course_year_wise";
    }

    @RequestMapping(method = {RequestMethod.POST}, value = {"/students/export"})
    public void exportDueStudents(Model model, Long academicYearId, Long courseYearId, String feeDueDateStr, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Content-disposition", "attachment;filename=due_student_report_" + feeDueDateStr + ".xls");
        Date feeDueDate = null;
        if (!"".equals(feeDueDateStr)) {
            try {
                feeDueDate = DateUtil.parseDate(feeDueDateStr, "dd-MMM-yyyy");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        prepareDueStudentsInExcel(this.feeReportService.getDueStudents(academicYearId, courseYearId, feeDueDate, StudentStatus.CONFIRMED)).write(response.getOutputStream());
    }

    private Workbook prepareDueStudentsInExcel(List<StudentFee> studentFees) {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Due-Students");
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Calibry");
        CellStyle contentCellStyle = wb.createCellStyle();
        contentCellStyle.setFont(font);
        Row row0 = sheet.createRow(0);
        Cell cell_0 = row0.createCell(0);
        cell_0.setCellStyle(contentCellStyle);
        cell_0.setCellValue("Student ID");
        Cell cell_1 = row0.createCell(1);
        cell_1.setCellStyle(contentCellStyle);
        cell_1.setCellValue("Student Name");
        Cell cell_2 = row0.createCell(2);
        cell_2.setCellStyle(contentCellStyle);
        cell_2.setCellValue("Contact No#");
        Cell cell_3 = row0.createCell(3);
        cell_3.setCellStyle(contentCellStyle);
        cell_3.setCellValue("Projected (Rs.)");
        Cell cell_4 = row0.createCell(4);
        cell_4.setCellStyle(contentCellStyle);
        cell_4.setCellValue("Deposited (Rs.)");
        Cell cell_5 = row0.createCell(5);
        cell_5.setCellStyle(contentCellStyle);
        cell_5.setCellValue("Due (Rs.)");
        Cell cell_6 = row0.createCell(6);
        cell_6.setCellStyle(contentCellStyle);
        cell_6.setCellValue("Advance Deposited (Rs.)");
        Cell cell_7 = row0.createCell(7);
        cell_7.setCellStyle(contentCellStyle);
        cell_7.setCellValue("Late Fee Deposited (Rs.)");
        if (studentFees != null && (studentFees == null || !studentFees.isEmpty())) {
            int rowCount = 1;
            long totalProjectedFee = 0;
            long totalDepositedFee = 0;
            long totalDueFee = 0;
            long totalAdvDeposited = 0;
            long totalLateFee = 0;
            for (StudentFee studentFee : studentFees) {
                int rowCount2 = rowCount + 1;
                Row row = sheet.createRow(rowCount);
                Cell col_0 = row.createCell(0);
                col_0.setCellStyle(contentCellStyle);
                col_0.setCellValue(studentFee.getStudentId());
                Cell col_1 = row.createCell(1);
                col_1.setCellStyle(contentCellStyle);
                col_1.setCellValue(studentFee.getStudentFirstName() + " " + studentFee.getStudentLastName());
                Cell col_2 = row.createCell(2);
                col_2.setCellStyle(contentCellStyle);
                if (studentFee.getStudentContactNo1() != null) {
                    col_2.setCellValue(studentFee.getStudentContactNo1());
                } else {
                    col_2.setCellValue("");
                }
                long projectedFee = 0;
                if (studentFee.getAdminFee() != null) {
                    projectedFee = 0 + studentFee.getAdminFee().longValue();
                }
                if (studentFee.getCustomizedFee() != null) {
                    projectedFee += studentFee.getCustomizedFee().longValue();
                }
                if (studentFee.getBusFee() != null) {
                    projectedFee += studentFee.getBusFee().longValue();
                }
                totalProjectedFee += projectedFee;
                Cell col_3 = row.createCell(3);
                col_3.setCellStyle(contentCellStyle);
                col_3.setCellValue((double) projectedFee);
                long depositedFee = studentFee.getPaidFee();
                totalDepositedFee += depositedFee;
                Cell col_4 = row.createCell(4);
                col_4.setCellStyle(contentCellStyle);
                col_4.setCellValue((double) depositedFee);
                long dueFee = projectedFee - depositedFee;
                totalDueFee += dueFee;
                Cell col_5 = row.createCell(5);
                col_5.setCellStyle(contentCellStyle);
                col_5.setCellValue((double) dueFee);
                long depositedInAdv = studentFee.getPaidFeeInAdvance();
                totalAdvDeposited += depositedInAdv;
                Cell col_6 = row.createCell(6);
                col_6.setCellStyle(contentCellStyle);
                col_6.setCellValue((double) depositedInAdv);
                long paidLateFee = studentFee.getPaidLateFee();
                totalLateFee += paidLateFee;
                Cell col_7 = row.createCell(7);
                col_7.setCellStyle(contentCellStyle);
                col_7.setCellValue((double) paidLateFee);
                rowCount = rowCount2;
            }
            Row lastRow = sheet.createRow(rowCount);
            Cell lastRow_col_0 = lastRow.createCell(0);
            lastRow_col_0.setCellStyle(contentCellStyle);
            lastRow_col_0.setCellValue("Total");
            Cell lastRow_col_1 = lastRow.createCell(1);
            lastRow_col_1.setCellStyle(contentCellStyle);
            lastRow_col_1.setCellValue("");
            Cell lastRow_col_2 = lastRow.createCell(2);
            lastRow_col_2.setCellStyle(contentCellStyle);
            lastRow_col_2.setCellValue("");
            Cell lastRow_col_3 = lastRow.createCell(3);
            lastRow_col_3.setCellStyle(contentCellStyle);
            lastRow_col_3.setCellValue((double) totalProjectedFee);
            Cell lastRow_col_4 = lastRow.createCell(4);
            lastRow_col_4.setCellStyle(contentCellStyle);
            lastRow_col_4.setCellValue((double) totalDepositedFee);
            Cell lastRow_col_5 = lastRow.createCell(5);
            lastRow_col_5.setCellStyle(contentCellStyle);
            lastRow_col_5.setCellValue((double) totalDueFee);
            Cell lastRow_col_6 = lastRow.createCell(6);
            lastRow_col_6.setCellStyle(contentCellStyle);
            lastRow_col_6.setCellValue((double) totalAdvDeposited);
            Cell lastRow_col_7 = lastRow.createCell(7);
            lastRow_col_7.setCellStyle(contentCellStyle);
            lastRow_col_7.setCellValue((double) totalLateFee);
        }
        return wb;
    }
}
