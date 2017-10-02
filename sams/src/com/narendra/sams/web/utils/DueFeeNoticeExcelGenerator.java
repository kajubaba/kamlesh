package com.narendra.sams.web.utils;

import com.narendra.sams.admission.domain.HeadWiseDueFee;
import com.narendra.sams.admission.domain.StudentDueFee;
import com.narendra.sams.admission.domain.StudentDueFeeHeadWise;
import com.narendra.sams.admission.domain.StudentDueInstallment;
import com.narendra.sams.web.restws.fee.formbean.GenerateDueFeeNoticeForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DueFeeNoticeExcelGenerator {
    public static final short GAP_ROW_HEIGHT = (short) 5;
    public static final short HEADER_ROW_HEIGHT = (short) 30;
    public static final int ROW_SPACING_IN_NOTICE = 2;
    public static final short SMALL_FONT_SIZE = (short) 8;
    public static final String STANDARD_FONT = "Calibri";
    public static final short STANDARD_FONT_SIZE = (short) 11;
    public static final short STANDARD_HEADER_FONT_SIZE = (short) 13;
    public static final short STANDARD_ROW_HEIGHT = (short) 20;
    public static final short STANDARD_SUB_HEADER_FONT_SIZE = (short) 9;
    public static final int START_COLUMN_INDEX = 0;
    public static final int START_ROW_INDEX = 1;

    public static Workbook generateDueFeeNoticeHeadWiseInSpreadsheet(List<StudentDueFeeHeadWise> dueFeeNoticeModels, GenerateDueFeeNoticeForm generateDueFeeNoticeForm) throws IOException {
        Workbook wb = new XSSFWorkbook();
        XSSFSheet sheet = (XSSFSheet) wb.createSheet("Due Fee Notice");
        int rowIndex = 1;
        if (!(dueFeeNoticeModels == null || dueFeeNoticeModels.isEmpty())) {
            for (StudentDueFeeHeadWise dueFeeNoticeModel : dueFeeNoticeModels) {
                rowIndex = (prepareStudentDueFeeHeadWiseNotice(dueFeeNoticeModel, wb, sheet, rowIndex, generateDueFeeNoticeForm) + 1) + 2;
            }
        }
        sheet.setColumnWidth(0, 3825);
        sheet.setColumnWidth(1, 3825);
        sheet.setColumnWidth(2, 3825);
        sheet.setColumnWidth(3, 3825);
        sheet.setColumnWidth(4, 3825);
        return wb;
    }

    public static Workbook generateDueFeeNoticeInSpreadsheet(List<StudentDueFee> dueFeeNoticeModels, GenerateDueFeeNoticeForm generateDueFeeNoticeForm) throws IOException {
        Workbook wb = new XSSFWorkbook();
        XSSFSheet sheet = (XSSFSheet) wb.createSheet("Due Fee Notice");
        int rowIndex = 1;
        if (!(dueFeeNoticeModels == null || dueFeeNoticeModels.isEmpty())) {
            for (StudentDueFee dueFeeNoticeModel : dueFeeNoticeModels) {
                rowIndex = (prepareStudentDueFeeNotice(dueFeeNoticeModel, wb, sheet, rowIndex, generateDueFeeNoticeForm) + 1) + 2;
            }
        }
        sheet.setColumnWidth(0, 3825);
        sheet.setColumnWidth(1, 3825);
        sheet.setColumnWidth(2, 3825);
        sheet.setColumnWidth(3, 3825);
        sheet.setColumnWidth(4, 3825);
        return wb;
    }

    public static int prepareStudentDueFeeHeadWiseNotice(StudentDueFeeHeadWise dueFeeNoticeModel, Workbook wb, Sheet sheet, int rowIndex, GenerateDueFeeNoticeForm generateDueFeeNoticeForm) {
        Font stndardFont = wb.createFont();
        stndardFont.setFontHeightInPoints((short) 11);
        stndardFont.setFontName(STANDARD_FONT);
        CellStyle centerAlignFontStyle = wb.createCellStyle();
        centerAlignFontStyle.setAlignment((short) 2);
        centerAlignFontStyle.setFont(stndardFont);
        Row row_1 = sheet.createRow((short) rowIndex);
        row_1.setHeightInPoints(30.0f);
        Cell row_1_cell_1 = row_1.createCell(0);
        row_1_cell_1.setCellValue(generateDueFeeNoticeForm.getNoticeHeader());
        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setAlignment((short) 2);
        headerCellStyle.setVerticalAlignment((short) 1);
        Font headerFont = wb.createFont();
        headerFont.setFontHeightInPoints((short) 13);
        headerFont.setFontName(STANDARD_FONT);
        headerCellStyle.setFont(headerFont);
        row_1_cell_1.setCellStyle(headerCellStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 5));
        rowIndex++;
        Cell row_2_cell_1 = sheet.createRow((short) rowIndex).createCell(0);
        row_2_cell_1.setCellValue(generateDueFeeNoticeForm.getNoticeSubHeader());
        CellStyle subHeaderCellStyle = wb.createCellStyle();
        subHeaderCellStyle.setAlignment((short) 2);
        subHeaderCellStyle.setVerticalAlignment((short) 1);
        Font subHeaderFont = wb.createFont();
        subHeaderFont.setFontHeightInPoints((short) 9);
        subHeaderFont.setFontName(STANDARD_FONT);
        subHeaderCellStyle.setFont(subHeaderFont);
        row_2_cell_1.setCellStyle(subHeaderCellStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 5));
        rowIndex++;
        Row row_3 = sheet.createRow((short) rowIndex);
        row_3.setHeightInPoints(20.0f);
        CellStyle studentNameClassStyle = wb.createCellStyle();
        studentNameClassStyle.setAlignment((short) 3);
        studentNameClassStyle.setFont(stndardFont);
        Cell row_3_cell_1 = row_3.createCell(0);
        row_3_cell_1.setCellValue("Student Name : ");
        row_3_cell_1.setCellStyle(studentNameClassStyle);
        int colIndex = 0 + 1;
        row_3.createCell(colIndex).setCellValue(dueFeeNoticeModel.getStudentName());
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, colIndex, 3));
        colIndex += 2;
        row_3.createCell(colIndex).setCellValue("");
        colIndex++;
        Cell row_3_cell_4 = row_3.createCell(colIndex);
        row_3_cell_4.setCellValue("StudentI ID :");
        row_3_cell_4.setCellStyle(studentNameClassStyle);
        row_3.createCell(colIndex + 1).setCellValue(dueFeeNoticeModel.getStudentId());
        rowIndex++;
        Row row_4 = sheet.createRow((short) rowIndex);
        row_4.setHeightInPoints(20.0f);
        int colIndex2 = 0 + 1;
        Cell row_4_cell_1 = row_4.createCell(0);
        row_4_cell_1.setCellValue("Stduent Class : ");
        row_4_cell_1.setCellStyle(studentNameClassStyle);
        row_4.createCell(colIndex2).setCellValue(dueFeeNoticeModel.getStudentClass());
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, colIndex2, 3));
        colIndex = colIndex2 + 2;
        row_4.createCell(colIndex).setCellValue("");
        colIndex++;
        Cell row_4_cell_4 = row_4.createCell(colIndex);
        row_4_cell_4.setCellValue("Session :");
        row_4_cell_4.setCellStyle(studentNameClassStyle);
        row_4.createCell(colIndex + 1).setCellValue(dueFeeNoticeModel.getAcademicYear());
        rowIndex++;
        Row row_5 = sheet.createRow((short) rowIndex);
        row_5.setHeightInPoints(30.0f);
        colIndex2 = 0 + 1;
        row_5.createCell(0).setCellValue("");
        Cell row_5_cell_2 = row_5.createCell(colIndex2);
        row_5_cell_2.setCellValue(generateDueFeeNoticeForm.getNoticeName());
        CellStyle dueFeeDetailsStyle = wb.createCellStyle();
        dueFeeDetailsStyle.setFont(stndardFont);
        dueFeeDetailsStyle.setAlignment((short) 2);
        dueFeeDetailsStyle.setVerticalAlignment((short) 1);
        row_5_cell_2.setCellStyle(dueFeeDetailsStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, colIndex2, 3));
        rowIndex++;
        CellStyle tableHeadingStyle = wb.createCellStyle();
        tableHeadingStyle.setFont(stndardFont);
        tableHeadingStyle.setBorderTop((short) 1);
        tableHeadingStyle.setBorderBottom((short) 1);
        tableHeadingStyle.setFont(stndardFont);
        tableHeadingStyle.setAlignment((short) 2);
        tableHeadingStyle.setVerticalAlignment((short) 1);
        int rowIndex2 = rowIndex + 1;
        Row row_6 = sheet.createRow((short) rowIndex);
        row_6.setHeightInPoints(20.0f);
        colIndex2 = 0 + 1;
        Cell row_6_cell_1 = row_6.createCell(0);
        row_6_cell_1.setCellValue("Fee Head");
        row_6_cell_1.setCellStyle(tableHeadingStyle);
        colIndex = colIndex2 + 1;
        Cell row_6_cell_2 = row_6.createCell(colIndex2);
        row_6_cell_2.setCellValue("");
        row_6_cell_2.setCellStyle(tableHeadingStyle);
        colIndex2 = colIndex + 1;
        Cell row_6_cell_3 = row_6.createCell(colIndex);
        row_6_cell_3.setCellValue("Fee");
        row_6_cell_3.setCellStyle(tableHeadingStyle);
        colIndex = colIndex2 + 1;
        Cell row_6_cell_4 = row_6.createCell(colIndex2);
        row_6_cell_4.setCellValue("Paid");
        row_6_cell_4.setCellStyle(tableHeadingStyle);
        Cell row_6_cell_5 = row_6.createCell(colIndex);
        row_6_cell_5.setCellValue("Unpaid");
        row_6_cell_5.setCellStyle(tableHeadingStyle);
        rowIndex = prepareHeadWiseRows(wb, sheet, rowIndex2, dueFeeNoticeModel.getHeadWiseDueFees());
        Row grandTotalRow = sheet.createRow((short) rowIndex);
        grandTotalRow.createCell(0).setCellValue("Grand Total");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 3));
        Cell grandTotalCell = grandTotalRow.createCell(4);
        CellStyle grandTotalCellStyle = wb.createCellStyle();
        grandTotalCellStyle.setAlignment((short) 2);
        grandTotalCell.setCellStyle(grandTotalCellStyle);
        Long total = Long.valueOf(0);
        if (!(dueFeeNoticeModel.getHeadWiseDueFees() == null || dueFeeNoticeModel.getHeadWiseDueFees().isEmpty())) {
            for (HeadWiseDueFee headWiseDueFee : dueFeeNoticeModel.getHeadWiseDueFees()) {
                total = Long.valueOf(total.longValue() + headWiseDueFee.getDue().longValue());
            }
        }
        grandTotalCell.setCellValue((double) total.longValue());
        rowIndex++;
        if (((generateDueFeeNoticeForm.getNoticeMessage() != null ? 1 : 0) & (generateDueFeeNoticeForm.getNoticeMessage().isEmpty() ? 0 : 1)) != 0) {
            sheet.createRow((short) rowIndex).setHeightInPoints(5.0f);
            rowIndex++;
            Row row_7 = sheet.createRow((short) rowIndex);
            row_7.setHeightInPoints(30.0f);
            Cell row_7_cell_1 = row_7.createCell(0);
            row_7_cell_1.setCellValue(generateDueFeeNoticeForm.getNoticeMessage());
            CellStyle commentsStyle = wb.createCellStyle();
            commentsStyle.setAlignment((short) 1);
            commentsStyle.setVerticalAlignment((short) 0);
            commentsStyle.setFont(stndardFont);
            commentsStyle.setWrapText(true);
            row_7_cell_1.setCellStyle(commentsStyle);
            sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 5));
            rowIndex++;
        }
        sheet.createRow((short) rowIndex).setHeightInPoints(5.0f);
        Font smallFont = wb.createFont();
        smallFont.setFontHeightInPoints((short) 8);
        smallFont.setFontName(STANDARD_FONT);
        CellStyle smallFontStyle = wb.createCellStyle();
        smallFontStyle.setFont(smallFont);
        rowIndex++;
        Row row_9 = sheet.createRow((short) rowIndex);
        Cell row_9_cell_1 = row_9.createCell(0);
        row_9_cell_1.setCellValue("* Late fee is not calcated in above due fee details");
        row_9_cell_1.setCellStyle(smallFontStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 3));
        colIndex = 0 + 4;
        Cell row_9_cell_2 = row_9.createCell(colIndex);
        row_9_cell_2.setCellValue(generateDueFeeNoticeForm.getAddressedBy());
        row_9_cell_2.setCellStyle(centerAlignFontStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, colIndex, 5));
        rowIndex++;
        Row row_10 = sheet.createRow((short) rowIndex);
        Cell row_10_cell_1 = row_10.createCell(0);
        row_10_cell_1.setCellValue("For more details please contact to institute");
        row_10_cell_1.setCellStyle(smallFontStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 3));
        colIndex = 0 + 4;
        Cell row_10_cell_2 = row_10.createCell(colIndex);
        row_10_cell_2.setCellValue("(" + generateDueFeeNoticeForm.getNoticeGenerationDate() + ")");
        row_10_cell_2.setCellStyle(centerAlignFontStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, colIndex, 5));
        return rowIndex;
    }

    public static int prepareStudentDueFeeNotice(StudentDueFee dueFeeNoticeModel, Workbook wb, Sheet sheet, int rowIndex, GenerateDueFeeNoticeForm generateDueFeeNoticeForm) {
        Font stndardFont = wb.createFont();
        stndardFont.setFontHeightInPoints((short) 11);
        stndardFont.setFontName(STANDARD_FONT);
        CellStyle centerAlignFontStyle = wb.createCellStyle();
        centerAlignFontStyle.setAlignment((short) 2);
        centerAlignFontStyle.setFont(stndardFont);
        Row row_1 = sheet.createRow((short) rowIndex);
        row_1.setHeightInPoints(30.0f);
        Cell row_1_cell_1 = row_1.createCell(0);
        row_1_cell_1.setCellValue(generateDueFeeNoticeForm.getNoticeHeader());
        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setAlignment((short) 2);
        headerCellStyle.setVerticalAlignment((short) 1);
        Font headerFont = wb.createFont();
        headerFont.setFontHeightInPoints((short) 13);
        headerFont.setFontName(STANDARD_FONT);
        headerCellStyle.setFont(headerFont);
        row_1_cell_1.setCellStyle(headerCellStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 5));
        rowIndex++;
        Cell row_2_cell_1 = sheet.createRow((short) rowIndex).createCell(0);
        row_2_cell_1.setCellValue(generateDueFeeNoticeForm.getNoticeSubHeader());
        CellStyle subHeaderCellStyle = wb.createCellStyle();
        subHeaderCellStyle.setAlignment((short) 2);
        subHeaderCellStyle.setVerticalAlignment((short) 1);
        Font subHeaderFont = wb.createFont();
        subHeaderFont.setFontHeightInPoints((short) 9);
        subHeaderFont.setFontName(STANDARD_FONT);
        subHeaderCellStyle.setFont(subHeaderFont);
        row_2_cell_1.setCellStyle(subHeaderCellStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 5));
        rowIndex++;
        Row row_3 = sheet.createRow((short) rowIndex);
        row_3.setHeightInPoints(20.0f);
        CellStyle studentNameClassStyle = wb.createCellStyle();
        studentNameClassStyle.setAlignment((short) 3);
        studentNameClassStyle.setFont(stndardFont);
        Cell row_3_cell_1 = row_3.createCell(0);
        row_3_cell_1.setCellValue("Student Name : ");
        row_3_cell_1.setCellStyle(studentNameClassStyle);
        int colIndex = 0 + 1;
        row_3.createCell(colIndex).setCellValue(dueFeeNoticeModel.getStudentName());
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, colIndex, 3));
        colIndex += 2;
        row_3.createCell(colIndex).setCellValue("");
        colIndex++;
        Cell row_3_cell_4 = row_3.createCell(colIndex);
        row_3_cell_4.setCellValue("StudentI ID :");
        row_3_cell_4.setCellStyle(studentNameClassStyle);
        row_3.createCell(colIndex + 1).setCellValue(dueFeeNoticeModel.getStudentId());
        rowIndex++;
        Row row_4 = sheet.createRow((short) rowIndex);
        row_4.setHeightInPoints(20.0f);
        int colIndex2 = 0 + 1;
        Cell row_4_cell_1 = row_4.createCell(0);
        row_4_cell_1.setCellValue("Stduent Class : ");
        row_4_cell_1.setCellStyle(studentNameClassStyle);
        row_4.createCell(colIndex2).setCellValue(dueFeeNoticeModel.getStudentClass());
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, colIndex2, 3));
        colIndex = colIndex2 + 2;
        row_4.createCell(colIndex).setCellValue("");
        colIndex++;
        Cell row_4_cell_4 = row_4.createCell(colIndex);
        row_4_cell_4.setCellValue("Session :");
        row_4_cell_4.setCellStyle(studentNameClassStyle);
        row_4.createCell(colIndex + 1).setCellValue(dueFeeNoticeModel.getAcademicYear());
        rowIndex++;
        Row row_5 = sheet.createRow((short) rowIndex);
        row_5.setHeightInPoints(30.0f);
        colIndex2 = 0 + 1;
        row_5.createCell(0).setCellValue("");
        Cell row_5_cell_2 = row_5.createCell(colIndex2);
        row_5_cell_2.setCellValue(generateDueFeeNoticeForm.getNoticeName());
        CellStyle dueFeeDetailsStyle = wb.createCellStyle();
        dueFeeDetailsStyle.setFont(stndardFont);
        dueFeeDetailsStyle.setAlignment((short) 2);
        dueFeeDetailsStyle.setVerticalAlignment((short) 1);
        row_5_cell_2.setCellStyle(dueFeeDetailsStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, colIndex2, 3));
        rowIndex++;
        CellStyle tableHeadingStyle = wb.createCellStyle();
        tableHeadingStyle.setFont(stndardFont);
        tableHeadingStyle.setBorderTop((short) 1);
        tableHeadingStyle.setBorderBottom((short) 1);
        tableHeadingStyle.setFont(stndardFont);
        tableHeadingStyle.setAlignment((short) 2);
        tableHeadingStyle.setVerticalAlignment((short) 1);
        int rowIndex2 = rowIndex + 1;
        Row row_6 = sheet.createRow((short) rowIndex);
        row_6.setHeightInPoints(20.0f);
        colIndex2 = 0 + 1;
        Cell row_6_cell_1 = row_6.createCell(0);
        row_6_cell_1.setCellValue("Installment");
        row_6_cell_1.setCellStyle(tableHeadingStyle);
        colIndex = colIndex2 + 1;
        Cell row_6_cell_2 = row_6.createCell(colIndex2);
        row_6_cell_2.setCellValue("Due Date");
        row_6_cell_2.setCellStyle(tableHeadingStyle);
        colIndex2 = colIndex + 1;
        Cell row_6_cell_3 = row_6.createCell(colIndex);
        row_6_cell_3.setCellValue("Fee");
        row_6_cell_3.setCellStyle(tableHeadingStyle);
        colIndex = colIndex2 + 1;
        Cell row_6_cell_4 = row_6.createCell(colIndex2);
        row_6_cell_4.setCellValue("Paid");
        row_6_cell_4.setCellStyle(tableHeadingStyle);
        Cell row_6_cell_5 = row_6.createCell(colIndex);
        row_6_cell_5.setCellValue("Unpaid");
        row_6_cell_5.setCellStyle(tableHeadingStyle);
        rowIndex = prepareRowsForDueInstallments(wb, sheet, rowIndex2, dueFeeNoticeModel.getDueInstallments());
        Row grandTotalRow = sheet.createRow((short) rowIndex);
        grandTotalRow.createCell(0).setCellValue("Grand Total");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 3));
        Cell grandTotalCell = grandTotalRow.createCell(4);
        CellStyle grandTotalCellStyle = wb.createCellStyle();
        grandTotalCellStyle.setAlignment((short) 2);
        grandTotalCell.setCellStyle(grandTotalCellStyle);
        Long total = Long.valueOf(0);
        if (!(dueFeeNoticeModel.getDueInstallments() == null || dueFeeNoticeModel.getDueInstallments().isEmpty())) {
            for (StudentDueInstallment studentDueInstallment : dueFeeNoticeModel.getDueInstallments()) {
                total = Long.valueOf(total.longValue() + studentDueInstallment.getUnpaidFee().longValue());
            }
        }
        grandTotalCell.setCellValue((double) total.longValue());
        rowIndex++;
        if (((generateDueFeeNoticeForm.getNoticeMessage() != null ? 1 : 0) & (generateDueFeeNoticeForm.getNoticeMessage().isEmpty() ? 0 : 1)) != 0) {
            sheet.createRow((short) rowIndex).setHeightInPoints(5.0f);
            rowIndex++;
            Row row_7 = sheet.createRow((short) rowIndex);
            row_7.setHeightInPoints(30.0f);
            Cell row_7_cell_1 = row_7.createCell(0);
            row_7_cell_1.setCellValue(generateDueFeeNoticeForm.getNoticeMessage());
            CellStyle commentsStyle = wb.createCellStyle();
            commentsStyle.setAlignment((short) 1);
            commentsStyle.setVerticalAlignment((short) 0);
            commentsStyle.setFont(stndardFont);
            commentsStyle.setWrapText(true);
            row_7_cell_1.setCellStyle(commentsStyle);
            sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 5));
            rowIndex++;
        }
        sheet.createRow((short) rowIndex).setHeightInPoints(5.0f);
        Font smallFont = wb.createFont();
        smallFont.setFontHeightInPoints((short) 8);
        smallFont.setFontName(STANDARD_FONT);
        CellStyle smallFontStyle = wb.createCellStyle();
        smallFontStyle.setFont(smallFont);
        rowIndex++;
        Row row_9 = sheet.createRow((short) rowIndex);
        Cell row_9_cell_1 = row_9.createCell(0);
        row_9_cell_1.setCellValue("* Late fee is not calcated in above due fee details");
        row_9_cell_1.setCellStyle(smallFontStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 3));
        colIndex = 0 + 4;
        Cell row_9_cell_2 = row_9.createCell(colIndex);
        row_9_cell_2.setCellValue(generateDueFeeNoticeForm.getAddressedBy());
        row_9_cell_2.setCellStyle(centerAlignFontStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, colIndex, 5));
        rowIndex++;
        Row row_10 = sheet.createRow((short) rowIndex);
        Cell row_10_cell_1 = row_10.createCell(0);
        row_10_cell_1.setCellValue("For more details please contact to institute");
        row_10_cell_1.setCellStyle(smallFontStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 3));
        colIndex = 0 + 4;
        Cell row_10_cell_2 = row_10.createCell(colIndex);
        row_10_cell_2.setCellValue("(" + generateDueFeeNoticeForm.getNoticeGenerationDate() + ")");
        row_10_cell_2.setCellStyle(centerAlignFontStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, colIndex, 5));
        return rowIndex;
    }

    public static int prepareRowsForDueInstallments(Workbook wb, Sheet sheet, int rowIndex, List<StudentDueInstallment> dueFeeNoticeInstallmentDetails) {
        if (dueFeeNoticeInstallmentDetails != null) {
            CellStyle lastRowStyle = wb.createCellStyle();
            CreationHelper createHelper = wb.getCreationHelper();
            lastRowStyle.setBorderBottom((short) 1);
            lastRowStyle.setAlignment((short) 2);
            CellStyle lastRowDateStyle = wb.createCellStyle();
            lastRowDateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MMM-yyyy"));
            lastRowDateStyle.setBorderBottom((short) 1);
            lastRowDateStyle.setAlignment((short) 2);
            CellStyle middleCellStyle = wb.createCellStyle();
            middleCellStyle.setAlignment((short) 2);
            CellStyle dateCellStyle = wb.createCellStyle();
            dateCellStyle.setAlignment((short) 2);
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MMM-yyyy"));
            int rowCounter = 0;
            for (StudentDueInstallment installmentDetail : dueFeeNoticeInstallmentDetails) {
                rowCounter++;
                Row intallmentRow = sheet.createRow((short) rowIndex);
                intallmentRow.setHeightInPoints(20.0f);
                int colIndex = 0 + 1;
                Cell cell_1 = intallmentRow.createCell(0);
                cell_1.setCellValue(installmentDetail.getInstallment());
                int colIndex2 = colIndex + 1;
                Cell cell_2 = intallmentRow.createCell(colIndex);
                cell_2.setCellValue(installmentDetail.getDueDate());
                colIndex = colIndex2 + 1;
                Cell cell_3 = intallmentRow.createCell(colIndex2);
                cell_3.setCellValue((double) installmentDetail.getFee().longValue());
                colIndex2 = colIndex + 1;
                Cell cell_4 = intallmentRow.createCell(colIndex);
                cell_4.setCellValue((double) installmentDetail.getPaidFee().longValue());
                Cell cell_5 = intallmentRow.createCell(colIndex2);
                cell_5.setCellValue((double) installmentDetail.getUnpaidFee().longValue());
                if (rowCounter == dueFeeNoticeInstallmentDetails.size()) {
                    cell_1.setCellStyle(lastRowStyle);
                    cell_2.setCellStyle(lastRowDateStyle);
                    cell_3.setCellStyle(lastRowStyle);
                    cell_4.setCellStyle(lastRowStyle);
                    cell_5.setCellStyle(lastRowStyle);
                } else {
                    cell_1.setCellStyle(middleCellStyle);
                    cell_2.setCellStyle(dateCellStyle);
                    cell_3.setCellStyle(middleCellStyle);
                    cell_4.setCellStyle(middleCellStyle);
                    cell_5.setCellStyle(middleCellStyle);
                }
                rowIndex++;
            }
        }
        return rowIndex;
    }

    public static int prepareHeadWiseRows(Workbook wb, Sheet sheet, int rowIndex, List<HeadWiseDueFee> dueFeeNoticeInstallmentDetails) {
        if (dueFeeNoticeInstallmentDetails != null) {
            CellStyle lastRowStyle = wb.createCellStyle();
            CreationHelper createHelper = wb.getCreationHelper();
            lastRowStyle.setBorderBottom((short) 1);
            lastRowStyle.setAlignment((short) 2);
            CellStyle lastRowDateStyle = wb.createCellStyle();
            lastRowDateStyle.setBorderBottom((short) 1);
            lastRowDateStyle.setAlignment((short) 2);
            lastRowDateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MMM-yyyy"));
            CellStyle middleCellStyle = wb.createCellStyle();
            middleCellStyle.setAlignment((short) 2);
            CellStyle dateCellStyle = wb.createCellStyle();
            dateCellStyle.setAlignment((short) 2);
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MMM-yyyy"));
            int feeHeadCounter = 0;
            for (HeadWiseDueFee installmentDetail : dueFeeNoticeInstallmentDetails) {
                feeHeadCounter++;
                if (installmentDetail.getDue().longValue() != 0) {
                    Row intallmentRow = sheet.createRow((short) rowIndex);
                    intallmentRow.setHeightInPoints(20.0f);
                    int colIndex = 0 + 1;
                    Cell cell_1 = intallmentRow.createCell(0);
                    cell_1.setCellValue(installmentDetail.getHeadName());
                    int colIndex2 = colIndex + 1;
                    Cell cell_2 = intallmentRow.createCell(colIndex);
                    cell_2.setCellValue("");
                    colIndex = colIndex2 + 1;
                    Cell cell_3 = intallmentRow.createCell(colIndex2);
                    cell_3.setCellValue((double) installmentDetail.getTotal().longValue());
                    colIndex2 = colIndex + 1;
                    Cell cell_4 = intallmentRow.createCell(colIndex);
                    cell_4.setCellValue((double) installmentDetail.getPaid().longValue());
                    Cell cell_5 = intallmentRow.createCell(colIndex2);
                    cell_5.setCellValue((double) installmentDetail.getDue().longValue());
                    if (feeHeadCounter == dueFeeNoticeInstallmentDetails.size()) {
                        cell_1.setCellStyle(lastRowStyle);
                        cell_2.setCellStyle(lastRowDateStyle);
                        cell_3.setCellStyle(lastRowStyle);
                        cell_4.setCellStyle(lastRowStyle);
                        cell_5.setCellStyle(lastRowStyle);
                    } else {
                        cell_1.setCellStyle(middleCellStyle);
                        cell_2.setCellStyle(dateCellStyle);
                        cell_3.setCellStyle(middleCellStyle);
                        cell_4.setCellStyle(middleCellStyle);
                        cell_5.setCellStyle(middleCellStyle);
                    }
                    rowIndex++;
                }
            }
        }
        return rowIndex;
    }

    public static void main(String[] args) throws IOException {
        DueFeeNoticeModel dueFeeNoticeModel = new DueFeeNoticeModel();
        dueFeeNoticeModel.setHeader("Sardar Patel Internation School");
        dueFeeNoticeModel.setSubHeader("Chole Road Mandleshwar MP.");
        dueFeeNoticeModel.setStudentName("Narendra Patidar");
        dueFeeNoticeModel.setStudentClass("Second Standard");
        dueFeeNoticeModel.setStudentId("234");
        dueFeeNoticeModel.setAcademicYear("2015-2016");
        DueFeeNoticeInstallmentDetail installment1 = new DueFeeNoticeInstallmentDetail();
        installment1.setInstallment("1");
        installment1.setDueDate("Jul-10-2016");
        installment1.setFee(Long.valueOf(12000));
        installment1.setPaid(Long.valueOf(7000));
        installment1.setUnpaid(Long.valueOf(5000));
        DueFeeNoticeInstallmentDetail installment2 = new DueFeeNoticeInstallmentDetail();
        installment2.setInstallment("2");
        installment2.setDueDate("Jul-12-2016");
        installment2.setFee(Long.valueOf(12000));
        installment2.setPaid(Long.valueOf(7000));
        installment2.setUnpaid(Long.valueOf(5000));
        List<DueFeeNoticeInstallmentDetail> installments = new ArrayList();
        installments.add(installment1);
        installments.add(installment2);
        dueFeeNoticeModel.setDueInstallments(installments);
        dueFeeNoticeModel.setComments("Dear parents, It's our huble request tp you to pay due fee on/before June-21-2016");
        List<DueFeeNoticeModel> dueFeeNoticeModels = new ArrayList();
        dueFeeNoticeModels.add(dueFeeNoticeModel);
        dueFeeNoticeModels.add(dueFeeNoticeModel);
        dueFeeNoticeModels.add(dueFeeNoticeModel);
        dueFeeNoticeModels.add(dueFeeNoticeModel);
        dueFeeNoticeModels.add(dueFeeNoticeModel);
    }
}
