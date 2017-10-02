package com.narendra.sams.web.admin.controller;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.BusFee;
import com.narendra.sams.core.domain.BusFeeInstallment;
import com.narendra.sams.core.domain.BusFeeInstallmentDetail;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.service.AcademicYearBusFeeService;
import com.narendra.sams.core.service.AcademicYearService;
import com.narendra.sams.web.admin.vo.BusFeeInstallmentVO;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.literal.AjaxStatus;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/admin/academicyear/bussfee"})
public class AcademicYearBusFeeController {
    @Autowired
    private AcademicYearBusFeeService academicYearBusFeeService;
    @Autowired
    private AcademicYearService academicYearService;
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private WebApplicationContext webApplicationContext;

    @RequestMapping(method = {RequestMethod.GET}, value = {"/defaultList"})
    public String busFeeSettingView(@RequestParam Long academicYearId, Model model) {
        this.logger.info("Preparing bus fee default listing view");
        model.addAttribute("busFeeList", this.academicYearBusFeeService.getAssigedBusFee(academicYearId));
        model.addAttribute("academicYearId", academicYearId);
        this.logger.info("Returning bus fee default listing view");
        return "admin_bus_fee_setting";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/export"})
    public void exportBusFee(@RequestParam Long academicYearId, Model model, HttpServletResponse response) throws Exception {
        AcademicYear academicYear = this.academicYearService.getAcademicYearById(academicYearId.longValue());
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Content-disposition", "attachment;filename=Bus_Fee_" + academicYear.getName() + ".xls");
        List<BusFee> busFeeList = this.academicYearBusFeeService.getAssigedBusFee(academicYearId);
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet(academicYear.getName() + " Bus Fee");
        Row row0 = sheet.createRow(0);
        wb.createCellStyle().setFillBackgroundColor(IndexedColors.SKY_BLUE.getIndex());
        row0.createCell(0).setCellValue("Bus Stop");
        row0.createCell(1).setCellValue("Distance (Km.)");
        row0.createCell(2).setCellValue("Fee (Rs.)");
        if (!(busFeeList == null || busFeeList.isEmpty())) {
            int rowCount = 1;
            for (BusFee busFee : busFeeList) {
                int rowCount2 = rowCount + 1;
                Row row = sheet.createRow(rowCount);
                Cell cell_0 = row.createCell(0);
                if (busFee.getBusStop().getName() != null) {
                    cell_0.setCellValue(busFee.getBusStop().getName());
                } else {
                    cell_0.setCellValue("");
                }
                Cell cell_1 = row.createCell(1);
                if (busFee.getBusStop().getDistance() != null) {
                    cell_1.setCellValue((double) busFee.getBusStop().getDistance().floatValue());
                } else {
                    cell_1.setCellValue("");
                }
                Cell cell_2 = row.createCell(2);
                if (busFee.getRs() != null) {
                    cell_2.setCellValue((double) busFee.getRs().longValue());
                    rowCount = rowCount2;
                } else {
                    cell_2.setCellValue("");
                    rowCount = rowCount2;
                }
            }
        }
        wb.write(response.getOutputStream());
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/update"})
    public String updateAcademicYearBusFee(Long[] busFeeIdArr, Long[] busFeeArr) {
        List<BusFee> busFeeList = new ArrayList();
        for (int i = 0; i < busFeeIdArr.length; i++) {
            BusFee busFee = new BusFee();
            busFee.setId(busFeeIdArr[i]);
            busFee.setRs(busFeeArr[i]);
            busFeeList.add(busFee);
        }
        this.academicYearBusFeeService.updateFeeChanges(busFeeList);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", AjaxStatus.OK);
        return jsonObject.toString();
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/leftBusStops"})
    public String busStopPopupView(@RequestParam Long academicYearId, Model model) {
        model.addAttribute("busStops", this.academicYearBusFeeService.getUnAssignedBusStops(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstitute().getId(), academicYearId));
        model.addAttribute("academicYearId", academicYearId);
        return "admin_bus_stop_popup";
    }

    @RequestMapping(method = {RequestMethod.POST}, value = {"/add"})
    public String addBusStopsInAcademicYear(Long[] busStopIdArr, Long academicYearId, Model model) {
        if (busStopIdArr != null) {
            AcademicYear academicYear = new AcademicYear();
            academicYear.setId(academicYearId);
            List<BusFee> busFeeList = new ArrayList();
            for (Long busStopId : busStopIdArr) {
                BusFee busFee = new BusFee();
                busFee.setAcademicYear(academicYear);
                BusStop busStop = new BusStop();
                busStop.setId(busStopId);
                busFee.setBusStop(busStop);
                busFeeList.add(busFee);
            }
            this.academicYearBusFeeService.addBusFee(busFeeList);
        }
        model.addAttribute("busFeeList", this.academicYearBusFeeService.getAssigedBusFee(academicYearId));
        model.addAttribute("academicYearId", academicYearId);
        return "admin_bus_fee_setting";
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/instl/{academicYearId}"})
    public String busInstallmentPopupView(@PathVariable Long academicYearId, Model model) {
        BusFeeInstallment busFeeInstallment = this.academicYearBusFeeService.getBusFeeInstallment(academicYearId);
        if (!(busFeeInstallment == null || busFeeInstallment.getBusFeeInstallmentDetails() == null || busFeeInstallment.getBusFeeInstallmentDetails().isEmpty())) {
            BusFeeInstallmentVO busFeeInstallmentVO = new BusFeeInstallmentVO();
            for (BusFeeInstallmentDetail busFeeInstallmentDetail : busFeeInstallment.getBusFeeInstallmentDetails()) {
                if (busFeeInstallmentDetail.getInstallment().getId().intValue() == Integer.valueOf(1).intValue()) {
                    busFeeInstallmentVO.setBusFeeFirstInstallment(busFeeInstallmentDetail);
                } else {
                    busFeeInstallmentVO.setBusFeeSecondInstallment(busFeeInstallmentDetail);
                }
            }
            model.addAttribute("busFeeInstallmentVO", busFeeInstallmentVO);
        }
        model.addAttribute("academicYearId", academicYearId);
        return "admin_bus_fee_installment_popup";
    }
}
