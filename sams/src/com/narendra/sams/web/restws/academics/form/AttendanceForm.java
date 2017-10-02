package com.narendra.sams.web.restws.academics.form;

import com.narendra.sams.academics.domain.Attendance;
import java.util.List;

public class AttendanceForm {
    private List<Attendance> attendances;
    private Long classId;
    private Long termId;

    public Long getClassId() {
        return this.classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getTermId() {
        return this.termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    public List<Attendance> getAttendances() {
        return this.attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }
}
