package com.narendra.sams.web.fee.controller;

import com.narendra.sams.admission.domain.CourseDiscount;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.CourseYear;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.core.service.CourseService;
import com.narendra.sams.fee.service.FeeReportService;
import com.narendra.sams.web.auth.UserSessionManager;
import java.io.IOException;
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
@RequestMapping({"/fee/report"})
public class AnnualFeeReportController {
    @Autowired
    private AcademicYearService academicYearService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private FeeReportService feeReportService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.GET}, value = {"/annual"})
    public String getAnnualReport(Long academicYearId, Model model) {
        List<AcademicYear> academicYears = this.academicYearService.getFeeAcademicYears(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId());
        if (academicYearId == null) {
            academicYearId = UserSessionManager.getUserSession(this.webApplicationContext).getAcademicYearOfAdmission().getId();
        }
        model.addAttribute("courseYearFeeSummaries", this.feeReportService.getAnnualFeeReport(academicYearId, StudentStatus.CONFIRMED));
        model.addAttribute("selectedAcademicYear", academicYearId);
        model.addAttribute("academicYears", academicYears);
        return "fee_summary_course_year_wise";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/discountgiven"})
    public String getDiscountedStudents(Long academicYearId, Long courseYearId, Model model) {
        List<CourseDiscount> students = this.feeReportService.getDiscountedStudents(academicYearId, courseYearId);
        AcademicYear academicYear = this.academicYearService.getAcademicYearById(academicYearId.longValue());
        CourseYear courseYear = this.courseService.loadCourseYear(courseYearId);
        model.addAttribute("students", students);
        model.addAttribute("academicYear", academicYear);
        model.addAttribute("courseYear", courseYear);
        return "discount_given_course_year_wise";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/discountgiven/excel"})
    public void getDiscountedStudentsInExcel(Long academicYearId, Long courseYearId, HttpServletResponse response) throws IOException {
        List<CourseDiscount> students = this.feeReportService.getDiscountedStudents(academicYearId, courseYearId);
        AcademicYear academicYear = this.academicYearService.getAcademicYearById(academicYearId.longValue());
        CourseYear courseYear = this.courseService.loadCourseYear(courseYearId);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Content-disposition", "attachment;filename=Student_Discount_Report_" + academicYear.getName() + ".xls");
        prepareExcel(students, academicYear, courseYear).write(response.getOutputStream());
    }

    private Workbook prepareExcel(List<CourseDiscount> students, AcademicYear academicYear, CourseYear courseYear) {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet(academicYear.getName() + "_" + courseYear.getCourse().getName() + "_" + courseYear.getName() + " Yr");
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
        cell_2.setCellValue("Total Fee (Rs.)");
        Cell cell_3 = row0.createCell(3);
        cell_3.setCellStyle(contentCellStyle);
        cell_3.setCellValue("Discount Given (Rs.)");
        Cell cell_4 = row0.createCell(4);
        cell_4.setCellStyle(contentCellStyle);
        cell_4.setCellValue("Payable Fee (Rs.)");
        if (students != null && (students == null || !students.isEmpty())) {
            int rowCount = 1;
            for (CourseDiscount studentDiscount : students) {
                if (!(studentDiscount.getDiscount() == null || studentDiscount.getDiscount().longValue() == 0)) {
                    int rowCount2 = rowCount + 1;
                    Row row = sheet.createRow(rowCount);
                    Cell col_0 = row.createCell(0);
                    col_0.setCellStyle(contentCellStyle);
                    col_0.setCellValue(studentDiscount.getStudentId());
                    Cell col_1 = row.createCell(1);
                    col_1.setCellStyle(contentCellStyle);
                    col_1.setCellValue(studentDiscount.getStudentFirstName() + " " + studentDiscount.getStudentLastName());
                    Cell col_2 = row.createCell(2);
                    col_2.setCellStyle(contentCellStyle);
                    col_2.setCellValue((double) (studentDiscount.getTotalFee().longValue() + studentDiscount.getDiscount().longValue()));
                    Cell col_3 = row.createCell(3);
                    col_3.setCellStyle(contentCellStyle);
                    col_3.setCellValue((double) studentDiscount.getDiscount().longValue());
                    Cell col_4 = row.createCell(4);
                    col_4.setCellStyle(contentCellStyle);
                    col_4.setCellValue((double) ((studentDiscount.getTotalFee().longValue() + studentDiscount.getDiscount().longValue()) - studentDiscount.getDiscount().longValue()));
                    rowCount = rowCount2;
                }
            }
        }
        return wb;
    }
}
