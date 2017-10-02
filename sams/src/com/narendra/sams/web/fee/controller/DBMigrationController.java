package com.narendra.sams.web.fee.controller;

import com.narendra.sams.fee.service.StudentFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class DBMigrationController {
    @Autowired
    private StudentFeeService studentFeeService;

    @RequestMapping(method = {RequestMethod.GET}, value = {"ft"})
    public void migrateFeeTransactionTable() {
        this.studentFeeService.updateFeeTransactionTable();
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"cit"})
    public void migrateCustomizeInstallmentTable() {
        this.studentFeeService.updateCustomizeInstallmenetTable();
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"cict"})
    public void updateCustomizeInstallmenetCommentsTable() {
        this.studentFeeService.updateCustomizeInstallmenetCommentsTable();
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"fdt"})
    public void updateFeeDiscountTable() {
        this.studentFeeService.updateFeeDiscountTable();
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"fdot"})
    public void updateDaysOverdueTable() {
        this.studentFeeService.updateDaysOverdueTable();
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"admn"})
    public void updateDaysOverdueTable(Long ayid) {
        this.studentFeeService.saveAdmissiondate(ayid);
    }
}
