package com.narendra.sams.web.restws.fee;

import com.narendra.sams.admission.domain.FeeRecieptHeader;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.fee.service.FeeRecieptService;
import com.narendra.sams.fee.service.StudentFeeService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.fee.formbean.GenerateDueFeeNoticeForm;
import com.narendra.sams.web.restws.fee.vo.DueFeeNoticeInfoVO;
import com.narendra.sams.web.utils.DueFeeNoticeExcelGenerator;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/duefee/notice"})
public class DueFeeNoticeRestController {
    @Autowired
    private FeeRecieptService feeRecieptService;
    @Autowired
    private StudentFeeService studentFeeService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/noticeinfo"})
    public DueFeeNoticeInfoVO getDueFeeNoticeInfo() {
        DueFeeNoticeInfoVO dueFeeNoticeInfoVO = new DueFeeNoticeInfoVO();
        FeeRecieptHeader feeRecieptHeader = this.feeRecieptService.getDefaultHeader(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId());
        dueFeeNoticeInfoVO.setNoticeHeader(feeRecieptHeader.getHeader());
        dueFeeNoticeInfoVO.setNoticeSubHeader(feeRecieptHeader.getSubHeader());
        dueFeeNoticeInfoVO.setNoticeMessage("Dear Parents/Student, It is our humble request to you to pay due fee before/on due date.");
        dueFeeNoticeInfoVO.setNoticeName("Due Fee Details");
        dueFeeNoticeInfoVO.setAddressedBy("Account Dept.");
        dueFeeNoticeInfoVO.setNoticeGenerationDate(DateUtil.formatDate(DateUtil.getSystemDate(), "dd-MMM-yyyy"));
        dueFeeNoticeInfoVO.setNoticeType("installment");
        return dueFeeNoticeInfoVO;
    }

    @RequestMapping(method = {RequestMethod.POST}, value = {"/export"})
    public void exportDueFeeNotice(GenerateDueFeeNoticeForm generateDueFeeNoticeForm, HttpServletResponse response) throws IOException {
        Workbook wb;
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Content-disposition", "attachment;filename=Due-Fee-Notice.xlsx");
        Date dueDate = null;
        if (!"".equals(generateDueFeeNoticeForm.getDueDateStr())) {
            try {
                dueDate = DateUtil.makeEndDate(DateUtil.parseDate(generateDueFeeNoticeForm.getDueDateStr(), "dd-MMM-yyyy"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if ("installment".equals(generateDueFeeNoticeForm.getNoticeType())) {
            wb = DueFeeNoticeExcelGenerator.generateDueFeeNoticeInSpreadsheet(this.studentFeeService.getDueStudentsForNotice(generateDueFeeNoticeForm.getStudentIds(), generateDueFeeNoticeForm.getAcademicYearId(), dueDate), generateDueFeeNoticeForm);
        } else {
            wb = DueFeeNoticeExcelGenerator.generateDueFeeNoticeHeadWiseInSpreadsheet(this.studentFeeService.getDueStudentsHeadWiseForNotice(generateDueFeeNoticeForm.getStudentIds(), generateDueFeeNoticeForm.getAcademicYearId(), dueDate), generateDueFeeNoticeForm);
        }
        wb.write(response.getOutputStream());
    }
}
