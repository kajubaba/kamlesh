package com.narendra.sams.core.domain;

public class AcademicYearClass {
    private AcademicYear academicYear;
    private Boolean active;
    private CourseYear courseYear;
    private CourseYearSetting courseYearSetting;
    private String displayName;
    private Long id;
    private Short intake;
    private Short name;
    private String nextClassName;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getName() {
        return this.name;
    }

    public void setName(Short name) {
        this.name = name;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public CourseYear getCourseYear() {
        return this.courseYear;
    }

    public void setCourseYear(CourseYear courseYear) {
        this.courseYear = courseYear;
    }

    public CourseYearSetting getCourseYearSetting() {
        return this.courseYearSetting;
    }

    public void setCourseYearSetting(CourseYearSetting courseYearSetting) {
        this.courseYearSetting = courseYearSetting;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.id == null ? 0 : this.id.hashCode()) + 31) * 31;
        if (this.name != null) {
            i = this.name.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AcademicYearClass)) {
            return false;
        }
        AcademicYearClass other = (AcademicYearClass) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
            return true;
        } else if (this.name.equals(other.name)) {
            return true;
        } else {
            return false;
        }
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String prepareClassName() {
        StringBuffer sb = new StringBuffer();
        if (this.courseYear.getCourse().getDuration().shortValue() > (short) 1) {
            sb.append(this.courseYear.getCourse().getDisplayName()).append(" , ").append(this.courseYear.getName()).append(" Yr.");
        } else {
            sb.append(getDisplayName());
        }
        return sb.toString();
    }

    public String getNextClassName() {
        return this.nextClassName;
    }

    public void setNextClassName(String nextClassName) {
        this.nextClassName = nextClassName;
    }

    public Short getIntake() {
        return this.intake;
    }

    public void setIntake(Short intake) {
        this.intake = intake;
    }
}
