package com.narendra.sams.web.utils;

import com.narendra.sams.admission.domain.Address;
import com.narendra.sams.core.domain.AcademicYearClass;
import java.util.Set;

public class StudentInformationUtil {
    public static String getClassName(AcademicYearClass academicYearClass) {
        StringBuffer sb = new StringBuffer();
        if (academicYearClass.getCourseYear().getCourse().getDuration().shortValue() > (short) 1) {
            sb.append(academicYearClass.getCourseYear().getCourse().getDisplayName()).append(" , ").append(academicYearClass.getCourseYear().getName()).append(" Yr.");
        } else {
            sb.append(academicYearClass.getDisplayName());
        }
        return sb.toString();
    }

    public static String getFullName(String firstName, String middleName, String lastName) {
        StringBuffer sb = new StringBuffer();
        sb.append(firstName);
        if (middleName != null) {
            sb.append(" ").append(middleName);
        }
        if (lastName != null) {
            sb.append(" ").append(lastName);
        }
        return sb.toString();
    }

    public static String prepareContactNo(String phone1, String phone2) {
        StringBuffer contactNo = new StringBuffer("");
        Boolean contactNoFound = Boolean.FALSE;
        if (!(phone1 == null || phone1.isEmpty())) {
            contactNo.append(phone1);
            contactNoFound = Boolean.TRUE;
        }
        if (!(phone2 == null || phone2.isEmpty())) {
            if (contactNoFound.booleanValue()) {
                contactNo.append(", ").append(phone2);
            } else {
                contactNo.append(phone2);
            }
        }
        return contactNo.toString();
    }

    public static String prepareAddress(String line1, String line2, String city) {
        StringBuffer address = new StringBuffer("");
        Boolean line1Found = Boolean.FALSE;
        Boolean line2Found = Boolean.FALSE;
        if (!(line1 == null || line1.trim().isEmpty())) {
            address.append(line1);
            line1Found = Boolean.TRUE;
        }
        if (!(line2 == null || line2.trim().isEmpty())) {
            if (line1Found.booleanValue()) {
                address.append(" , ").append(line2);
            } else {
                address.append(line2);
            }
            line2Found = Boolean.TRUE;
        }
        if (!(city == null || city.trim().isEmpty())) {
            if (line1Found.booleanValue() || line2Found.booleanValue()) {
                address.append(" , ").append(city);
            } else {
                address.append(city);
            }
        }
        return address.toString();
    }

    public static String prepareStduentLocalAddress(Set<Address> addresses) {
        if (addresses != null) {
            for (Address add : addresses) {
                if (Address.ADDRESS_TYPE_LOCAL.equals(add.getAddressOf())) {
                    return prepareAddress(add.getLine1(), add.getLine2(), add.getCity());
                }
            }
        }
        return null;
    }
}
