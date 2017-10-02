package com.narendra.sams.web.restws.form;

import com.narendra.sams.web.restws.vo.AcademicSessionBusStopVO;
import java.util.List;

public class AcademicSesionBusStopFeeForm {
    private List<AcademicSessionBusStopVO> academicSessionBusFees;

    public List<AcademicSessionBusStopVO> getAcademicSessionBusFees() {
        return this.academicSessionBusFees;
    }

    public void setAcademicSessionBusFees(List<AcademicSessionBusStopVO> academicSessionBusFees) {
        this.academicSessionBusFees = academicSessionBusFees;
    }
}
