package com.narendra.sams.web.restws.mapper.form;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.restws.form.AcademicSessionForm;
import java.text.ParseException;

public class AcademicSessionFormMapper {
    public static AcademicYear prepareAcademicSessionDomain(AcademicSessionForm academicSessionForm) {
        if (academicSessionForm == null) {
            return null;
        }
        AcademicYear academicYear = new AcademicYear();
        academicYear.setId(academicSessionForm.getId());
        academicYear.setName(academicSessionForm.getName());
        academicYear.setOrderNo(academicSessionForm.getOrderNo());
        if (!(academicSessionForm.getFrom() == null || academicSessionForm.getFrom().isEmpty())) {
            try {
                academicYear.setFromDate(DateUtil.parseDate(academicSessionForm.getFrom(), "dd-MMM-yyyy"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (academicSessionForm.getTo() == null || academicSessionForm.getTo().isEmpty()) {
            return academicYear;
        }
        try {
            academicYear.setToDate(DateUtil.parseDate(academicSessionForm.getTo(), "dd-MMM-yyyy"));
            return academicYear;
        } catch (ParseException e2) {
            e2.printStackTrace();
            return academicYear;
        }
    }
}
