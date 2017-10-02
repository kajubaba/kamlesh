package com.narendra.sams.web.restws.academics.vo;

import com.narendra.sams.academics.domain.Attendance;
import java.util.List;

public class AttendanceVO {
    private List<Attendance> attendances;
    private Long termId;
    private Long workingDays;

    public Long getTermId() {
        return this.termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    public Long getWorkingDays() {
        return this.workingDays;
    }

    public void setWorkingDays(Long workingDays) {
        this.workingDays = workingDays;
    }

    public List<Attendance> getAttendances() {
        return this.attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }
}
