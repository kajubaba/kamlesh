package com.narendra.sams.web.utils;

import com.narendra.sams.admission.domain.FeeTransaction;

public class FeeTransactionClassNameUtils {
    public static String getClassName(FeeTransaction feeTransaction) {
        StringBuffer sb = new StringBuffer();
        if (feeTransaction.getStudentClass().getCourseYear().getCourse().getDuration().shortValue() > (short) 1) {
            sb.append(feeTransaction.getStudentClass().getCourseYear().getCourse().getDisplayName()).append(" , ").append(feeTransaction.getStudentClass().getCourseYear().getName()).append(" Yr.");
        } else {
            sb.append(feeTransaction.getStudentClass().getDisplayName());
        }
        return sb.toString();
    }
}
