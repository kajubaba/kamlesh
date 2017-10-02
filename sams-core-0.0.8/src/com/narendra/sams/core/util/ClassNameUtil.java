package com.narendra.sams.core.util;

import com.narendra.sams.core.domain.AcademicYearClass;

public class ClassNameUtil {
    public static String getClassName(AcademicYearClass academicYearClass) {
        StringBuffer sb = new StringBuffer();
        if (academicYearClass.getCourseYear().getCourse().getDuration().shortValue() > (short) 1) {
            sb.append(academicYearClass.getCourseYear().getCourse().getDisplayName()).append(" , ").append(academicYearClass.getCourseYear().getName()).append(" Yr.");
        } else {
            sb.append(academicYearClass.getDisplayName());
        }
        return sb.toString();
    }
}
