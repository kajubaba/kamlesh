package com.narendra.sams.academics.domain;

public class Attendance {
    private Long attendance;
    private Long attendanceId;
    private String fatherName;
    private Long studentDBId;
    private String studentGender;
    private String studentId;
    private String studentName;
    private String studentSection;

    public Long getAttendanceId() {
        return this.attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Long getStudentDBId() {
        return this.studentDBId;
    }

    public void setStudentDBId(Long studentDBId) {
        this.studentDBId = studentDBId;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getStudentSection() {
        return this.studentSection;
    }

    public void setStudentSection(String studentSection) {
        this.studentSection = studentSection;
    }

    public String getStudentGender() {
        return this.studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public Long getAttendance() {
        return this.attendance;
    }

    public void setAttendance(Long attendance) {
        this.attendance = attendance;
    }
}
